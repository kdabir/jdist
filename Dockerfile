# Multi-stage Docker build for cross-platform distribution
FROM openjdk:24-jdk-slim as builder

# Install build tools
RUN apt-get update && apt-get install -y \
    gradle \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the application
RUN gradle build fatJar

# Stage 2: Create distribution packages
FROM openjdk:24-jdk-slim as packager

# Install jpackage and build tools
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Copy built JAR from builder stage
COPY --from=builder /app/build/libs/system-test-fat-1.0.0-all.jar /app/

# Create distribution directory
RUN mkdir -p /app/distributions

# Create a simple launcher script
RUN echo '#!/bin/bash\njava -jar /app/system-test-fat-1.0.0-all.jar "$@"' > /app/launch.sh && \
    chmod +x /app/launch.sh

# Copy distribution files
COPY README.md LICENSE /app/

# Create final distribution
RUN cd /app && \
    zip -r distributions/system-test-1.0.0-portable.zip \
        system-test-fat-1.0.0-all.jar \
        launch.sh \
        README.md \
        LICENSE

# Final stage: Runtime image
FROM openjdk:24-jre-slim

# Install minimal runtime dependencies
RUN apt-get update && apt-get install -y \
    libx11-6 \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

# Copy application
COPY --from=builder /app/build/libs/system-test-fat-1.0.0-all.jar /app/
COPY README.md /app/

# Create launcher
RUN echo '#!/bin/bash\njava -jar /app/system-test-fat-1.0.0-all.jar "$@"' > /app/launch.sh && \
    chmod +x /app/launch.sh

WORKDIR /app

# Expose port (if needed for future web features)
EXPOSE 8080

# Default command
CMD ["./launch.sh"]
