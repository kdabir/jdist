# System Test Application

A modern Java Swing application for testing system installations and verifying Java functionality.

## Features

- 🎨 **Modern UI** with native look and feel
- 🌙 **Dark/Light mode** support
- ✅ **System validation** tests
- 🚀 **Cross-platform** compatibility
- 📦 **No Java installation** required (native packages)

## Quick Start

### Option 1: Native Installers (Recommended)
Download the appropriate installer for your platform:

- **macOS**: `System Test-1.0.0.dmg`
- **Windows**: `System Test-1.0.0.msi`
- **Linux**: `system-test_1.0.0_amd64.deb` or `system-test-1.0.0-1.x86_64.rpm`

### Option 2: JAR File
If you have Java installed:
```bash
java -jar system-test-fat-1.0.0-all.jar
```

### Option 3: From Source
```bash
./gradlew run
```

## How to Use

1. **Launch** the application
2. **Enter your name** in the text field
3. **Click "Run Test"** to verify your system
4. **View results** - success means your installation is working correctly

## System Requirements

### Native Packages (No Java Required)
- **macOS**: 10.15+ (Catalina or later)
- **Windows**: Windows 10+ (64-bit)
- **Linux**: Ubuntu 18.04+, CentOS 7+, or equivalent

### JAR File (Java Required)
- **Java**: 21+ (OpenJDK or Oracle JDK)
- **Memory**: 512MB RAM minimum
- **Disk**: 50MB free space

## Building from Source

### Prerequisites
- Java 24+ (with preview features)
- Gradle 9.1+

### Build Commands
```bash
# Build the application
./gradlew build

# Create native installers
./gradlew packageMac      # macOS .dmg
./gradlew packageWindows  # Windows .msi
./gradlew packageLinux    # Linux .deb/.rpm

# Create all packages
./gradlew packageAll

# Create fat JAR
./gradlew fatJar
```

## Distribution Files

After building, you'll find distribution files in `build/distributions/`:

- `System Test-1.0.0.dmg` - macOS installer
- `System Test-1.0.0.msi` - Windows installer
- `system-test_1.0.0_amd64.deb` - Linux Debian package
- `system-test-1.0.0-1.x86_64.rpm` - Linux RPM package
- `system-test-1.0.0.zip` - Portable ZIP with JAR
- `system-test-fat-1.0.0-all.jar` - Fat JAR (requires Java)

## Troubleshooting

### Common Issues

**Application won't start:**
- Ensure you have the correct Java version (21+)
- Check system requirements for native packages
- Verify file permissions

**Test fails:**
- Check Java installation
- Verify system resources
- Try running as administrator (Windows/Linux)

**UI looks incorrect:**
- Update your system's Java version
- Check display scaling settings
- Try the dark/light mode toggle

## Technical Details

- **Framework**: Java Swing with modern UI components
- **Java Version**: 24 (with preview features)
- **Build Tool**: Gradle 9.1
- **Packaging**: jpackage for native installers
- **Architecture**: Cross-platform (x86_64)

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues and questions:
1. Check the troubleshooting section
2. Verify system requirements
3. Test with the provided system test
4. Create an issue with system details
