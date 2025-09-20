# System Test Suite - Multi-Module Distribution Demo

A comprehensive demonstration of Java application distribution across multiple platforms and formats. This project showcases three different application types (CLI, Desktop GUI, Web Service) with various distribution mechanisms including GraalVM native images, jlink custom JVMs, and jpackage installers.

## 🏗️ Project Structure

```
system-test-suite/
├── shared/           # Common utilities and UI components
├── cli/              # Command-line interface application
├── desktop/          # Desktop GUI application (Swing)
├── service/          # Web service with HTML UI
└── build.gradle      # Multi-module Gradle configuration
```

## 🚀 Modules

### 1. **Shared Module** (`com.example.shared`)
- Common system test logic
- UI theme management
- Modern UI components
- Utility functions

### 2. **CLI Module** (`com.example.cli`)
- Command-line interface using Picocli
- Quick and comprehensive test modes
- Async test execution
- Verbose output options

### 3. **Desktop Module** (`com.example.desktop`)
- Native macOS-styled Swing GUI
- Dark/light theme support
- Real-time test execution
- Modern UI components

### 4. **Service Module** (`com.example.service`)
- **Hello World Web Service** using Javalin
- **Beautiful HTML interface** with animations and modern design
- **RESTful API endpoints** for system testing
- **Static file serving** from `/web` directory
- **Native app distribution** with embedded JVM
- **Automatic browser opening** - behaves like a desktop app

## 📦 Distribution Formats

### Native Images (GraalVM)
- **Ultra-fast startup** (sub-second)
- **No JVM required** on target systems
- **Platform-specific** binaries
- **Smaller memory footprint**
- ⚠️ **Complex setup required** - May need additional configuration for reflection and dynamic loading

### JLink Images
- **Custom JVM** with only required modules
- **Reduced size** compared to full JDK
- **Platform-specific** distributions

### jpackage Installers
- **Native installers** (.dmg, .msi, .deb)
- **Embedded JVM** for zero-dependency deployment
- **System integration** (desktop shortcuts, menus)

### Fat JARs
- **Single executable** JAR files
- **Cross-platform** compatibility
- **Requires Java** installation

## 🛠️ Build Commands

### Build All Modules
```bash
./gradlew buildAll
```

### Create Native Images
```bash
./gradlew createAllNativeImages
```

### Create JLink Images
```bash
./gradlew createAllJlinkImages
```

### Package Everything
```bash
./gradlew packageAll
```

### Module-Specific Packaging
```bash
# CLI module
./gradlew packageCli

# Desktop module
./gradlew packageDesktop

# Service module
./gradlew packageService
```

### Generate Distribution Report
```bash
./gradlew distributionReport
```

## 🎯 Usage Examples

### CLI Application
```bash
# Quick test
./gradlew :cli:run --args="John --quick"

# Comprehensive test
./gradlew :cli:run --args="John --verbose"

# Async test
./gradlew :cli:run --args="John --async"
```

### Desktop Application
```bash
./gradlew :desktop:run
```

### Hello World Web Service (Desktop App)
```bash
# Run with Gradle (automatically opens browser)
./gradlew :service:run

# Or run as native app (after building jlink image)
cd service && ./run-hello-service.sh

# Browser opens automatically to http://localhost:8080
# Hello World UI: http://localhost:8080/hello.html
```

## 📊 Generated Artifacts

### CLI Module
- `system-test-cli` (native image) ⚠️ *Requires GraalVM configuration*
- `cli/build/jlink/` (jlink image) ✅
- Fat JAR with dependencies ✅

### Desktop Module
- `System Test Desktop-1.0.0.dmg` (macOS) ✅
- `System Test Desktop-1.0.0.msi` (Windows) ✅
- `System Test Desktop-1.0.0.deb` (Linux) ✅
- `system-test-desktop` (native image) ⚠️ *Swing compatibility issues*
- `desktop/build/jlink/` (jlink image) ✅

### Service Module (Hello World Web Service)
- `Hello World Service-1.0.0.dmg` (macOS) ✅
- `Hello World Service-1.0.0.msi` (Windows) ✅
- `Hello World Service-1.0.0.deb` (Linux) ✅
- `system-test-service` (native image) ⚠️ *Requires GraalVM configuration*
- `service/build/jlink/` (jlink image - 91MB) ✅
- `service/run-hello-service.sh` (native app runner) ✅

## 🔧 Technical Features

### Java Modules (JPMS)
- **Automatic module-info.java generation**
- **Dependency detection** and validation
- **Modular JAR** creation

### Modern Java Features
- **Java 24** with preview features
- **Lambda expressions** and method references
- **Stream API** for data processing
- **Optional** for null safety
- **Switch expressions** and text blocks
- **String interpolation** with `.formatted()`

### Cross-Platform Support
- **macOS** (Intel & Apple Silicon)
- **Windows** (x64)
- **Linux** (x64)

## 🌐 Web Service API

### Endpoints
- `GET /` - Web UI interface
- `GET /health` - Health check
- `GET /status` - System information
- `GET /test/quick?name=<name>` - Quick test
- `POST /test` - Comprehensive test

### Example Usage
```bash
# Health check
curl http://localhost:8080/health

# Quick test
curl "http://localhost:8080/test/quick?name=John"

# Comprehensive test
curl -X POST http://localhost:8080/test \
  -H "Content-Type: application/json" \
  -d '{"name":"John"}'
```

## 📈 Performance Comparison

| Distribution Type | Startup Time | Size | Dependencies |
|------------------|--------------|------|--------------|
| Native Image     | ~50ms        | ~15MB| None         |
| JLink Image      | ~200ms       | ~40MB| None         |
| jpackage         | ~500ms       | ~80MB| None         |
| Fat JAR          | ~1s          | ~5MB | Java 24+     |

## 🎨 UI Features

### Desktop Application
- **Native macOS styling** with system integration
- **Dark/Light theme** toggle
- **Modern UI components** with consistent styling
- **Real-time validation** and feedback
- **Responsive layout** with proper spacing

### Web Interface
- **Modern responsive design** with gradient backgrounds
- **Interactive test forms** with real-time feedback
- **System status dashboard** with live updates
- **API documentation** with example usage
- **Mobile-friendly** responsive layout

## 🔍 System Tests

The application performs comprehensive system validation:

1. **Input validation** - Name length and format
2. **System properties** - OS and Java version detection
3. **Memory availability** - Minimum memory requirements
4. **Threading capability** - Concurrent execution testing
5. **File system access** - Temporary directory access
6. **UI responsiveness** - Event handling validation
7. **Theme system** - Color and font management

## 📝 Requirements

- **Java 24+** (with preview features)
- **Gradle 9.1+**
- **GraalVM** (for native images)
- **jpackage** (for native installers)

## 🚀 Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd system-test-suite
   ```

2. **Build all modules**
   ```bash
   ./gradlew buildAll
   ```

3. **Run applications**
   ```bash
   # CLI
   ./gradlew :cli:run --args="YourName --quick"
   
   # Desktop
   ./gradlew :desktop:run
   
   # Service
   ./gradlew :service:run
   ```

4. **Create distributions**
   ```bash
   ./gradlew packageAll
   ```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

This is a demonstration project showcasing Java distribution mechanisms. Feel free to use it as a reference for your own projects!

---

**Built with ❤️ using Java 24, Gradle, GraalVM, and modern distribution tools.**