@echo off
REM System Test Application Distribution Script for Windows
REM This script creates distribution packages for Windows

echo 🚀 Building System Test Application Distribution Packages
echo ==================================================

REM Clean previous builds
echo 🧹 Cleaning previous builds...
gradlew.bat clean

REM Build the application
echo 🔨 Building application...
gradlew.bat build

REM Create fat JAR
echo 📦 Creating fat JAR...
gradlew.bat fatJar

REM Create distribution ZIP
echo 📦 Creating distribution ZIP...
gradlew.bat distZip

REM Create Windows package
echo 🪟 Creating Windows package...
gradlew.bat packageWindows

echo.
echo 🎉 Distribution packages created successfully!
echo 📁 Check build\distributions\ for your packages:
dir build\distributions\

echo.
echo 📋 Available packages:
echo   • System Test-1.0.0.msi (Windows installer, no Java required)
echo   • system-test-1.0.0.zip (portable, requires Java)
echo   • system-test-fat-1.0.0-all.jar (fat JAR, requires Java)
echo.
echo 📖 See README.md for installation instructions
pause
