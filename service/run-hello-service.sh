#!/bin/bash

# Hello World Service Runner
# This script runs the Hello World Service as a native application

echo "🚀 Starting Hello World Service..."
echo "=================================="

# Check if jlink image exists
if [ -d "build/jlink" ]; then
    echo "📦 Using JLink image (custom JVM)"
    echo "🌐 Service will be available at: http://localhost:8080"
    echo "👋 Hello World UI: http://localhost:8080/hello.html"
    echo ""
    echo "Press Ctrl+C to stop the service"
    echo ""
    
    # Run using jlink image
    build/jlink/bin/java --module com.example.service/com.example.service.ServiceApp
else
    echo "📦 Using Gradle run task"
    echo "🌐 Service will be available at: http://localhost:8080"
    echo "👋 Hello World UI: http://localhost:8080/hello.html"
    echo ""
    echo "Press Ctrl+C to stop the service"
    echo ""
    
    # Run using Gradle
    ./gradlew :service:run
fi
