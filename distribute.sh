#!/bin/bash

# System Test Application Distribution Script
# This script creates distribution packages for all platforms

set -e

echo "🚀 Building System Test Application Distribution Packages"
echo "=================================================="

# Clean previous builds
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Build the application
echo "🔨 Building application..."
./gradlew build

# Create fat JAR
echo "📦 Creating fat JAR..."
./gradlew fatJar

# Create distribution ZIP
echo "📦 Creating distribution ZIP..."
./gradlew distZip

# Detect current platform and create appropriate package
PLATFORM=$(uname -s)
case $PLATFORM in
    Darwin*)
        echo "🍎 Creating macOS package..."
        ./gradlew packageMac
        echo "✅ macOS .dmg created in build/distributions/"
        ;;
    Linux*)
        echo "🐧 Creating Linux package..."
        ./gradlew packageLinux
        echo "✅ Linux .deb/.rpm created in build/distributions/"
        ;;
    CYGWIN*|MINGW*|MSYS*)
        echo "🪟 Creating Windows package..."
        ./gradlew packageWindows
        echo "✅ Windows .msi created in build/distributions/"
        ;;
    *)
        echo "❓ Unknown platform: $PLATFORM"
        echo "Creating generic packages..."
        ./gradlew distZip
        ;;
esac

echo ""
echo "🎉 Distribution packages created successfully!"
echo "📁 Check build/distributions/ for your packages:"
ls -la build/distributions/

echo ""
echo "📋 Available packages:"
echo "  • Native installer for your platform (no Java required)"
echo "  • system-test-1.0.0.zip (portable, requires Java)"
echo "  • system-test-fat-1.0.0-all.jar (fat JAR, requires Java)"
echo ""
echo "📖 See README.md for installation instructions"
