<div align="center">

# 🎯 QR Code Studio

### Modern Desktop Application for QR Code Generation & Reading

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![ZXing](https://img.shields.io/badge/ZXing-3.5.3-blue?style=for-the-badge)](https://github.com/zxing/zxing)
[![License](https://img.shields.io/badge/License-Apache%202.0-green?style=for-the-badge)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20Mac-lightgrey?style=for-the-badge)]()

**A powerful, user-friendly desktop application built with Java Swing for generating and decoding QR codes with live preview and modern UI.**

[Features](#-features) • [Installation](#-installation) • [Usage](#-usage) • [Screenshots](#-screenshots) • [Development](#-development)

---

</div>

## ✨ Features

### 🎨 Modern GUI Experience

- **Live QR Preview** - See generated QR codes instantly in a 240x240px preview pane
- **Nimbus Look & Feel** - Professional cross-platform UI design
- **Split-Panel Layout** - Form on the left, preview on the right for optimal workflow
- **One-Click Actions** - Open generated images, copy decoded text to clipboard
- **Smart Validation** - Real-time feedback with character counts and helpful error messages

### 🚀 Generation Capabilities

- ✅ Generate QR codes from any text or URL
- ✅ Customizable size (100-1000 pixels)
- ✅ PNG output with custom file names
- ✅ Instant visual preview after generation
- ✅ Open image directly in default viewer

### 📖 Reading & Decoding

- ✅ Decode QR codes from PNG, JPG, and other image formats
- ✅ Copy decoded text to clipboard with one click
- ✅ Character count display
- ✅ Detailed success/error feedback

### 🖥️ Command-Line Interface

- ✅ CLI tools for automation and scripting
- ✅ Batch processing support
- ✅ Legacy menu interface included

---

## 📋 Requirements

- **Java 21 LTS** or higher
- **Maven** (or Maven Daemon for faster builds)
- Windows, Linux, or macOS

> **Note:** All dependencies (ZXing 3.5.3) are managed automatically by Maven via `pom.xml`. No manual library downloads needed!

---

## 🚀 Installation

### Quick Start (Windows)

1. **Clone the repository**

   ```bash
   git clone https://github.com/anushdighore/QR-Code-Reader.git
   cd QR-Code-Reader
   ```

2. **Build the project**

   ```bash
   build.bat
   ```

   Maven will automatically download ZXing dependencies and compile everything.

3. **Run the application**
   ```bash
   run-jar.bat
   ```
   The GUI launches instantly! 🎉

### Alternative: Run with Maven

```bash
# Build and run in one command
run-maven.bat

# Or manually
mvn clean package
java -jar target/qr-code-reader.jar
```

### Linux/Mac Setup

1. **Clone and build**

   ```bash
   git clone https://github.com/anushdighore/QR-Code-Reader.git
   cd QR-Code-Reader
   mvn clean package
   ```

2. **Run the application**
   ```bash
   java -jar target/qr-code-reader.jar
   ```

### Project Structure

```
QR-Code-Reader/
├── src/
│   ├── QRCodeGenerator.java    # QR generation utility
│   ├── QRCodeReader.java       # QR reading utility
│   ├── QRCodeApp.java          # Legacy CLI interface
│   └── QRCodeAppGUI.java       # Modern Swing GUI
├── target/
│   ├── classes/                # Compiled bytecode
│   └── qr-code-reader.jar      # Executable JAR (with dependencies)
├── pom.xml                     # Maven configuration (manages ZXing)
├── build.bat                   # Windows build script
├── run-jar.bat                 # Windows run script (JAR)
└── run-maven.bat               # Windows run script (Maven)
```

> **Maven automatically handles:**
>
> - ZXing core 3.5.3
> - ZXing JavaSE 3.5.3
> - All transitive dependencies
> - Building fat JAR with maven-shade-plugin

---

## 💻 Usage

### Desktop Application (Recommended)

**Launch the GUI:**

```bash
# Windows (using JAR)
run-jar.bat

# Or with Maven
run-maven.bat

# Or manually
# Linux/Mac
java -jar target/qr-code-reader.jar
```

#### Generate Tab

1. Enter your text or URL in the data field
2. Choose output file name and location
3. Set the desired size (100-1000 pixels)
4. Click **Generate QR Code**
5. Preview appears automatically
6. Click **Open Image** to view in default viewer

#### Read Tab

1. Click **Browse** to select a QR code image
2. Click **Read QR Code**
3. Decoded text appears in the results area
4. Click **Copy Text** to copy to clipboard

### Command-Line Interface (Optional)

The application also includes standalone CLI utilities for automation:

**Run from compiled classes:**

```bash
# Generate a sample QR code
java -cp target/classes QRCodeGenerator

# Read a QR code image
java -cp target/classes QRCodeReader sample_qrcode.png
```

**Or use Maven to run:**

```bash
# Run the main GUI application
mvn exec:java -Dexec.mainClass="QRCodeAppGUI"
```

---

## 📸 Screenshots

### Generate Tab - Live Preview

![Generate Tab](docs/screenshots/generate-tab.png)
_Enter text, customize settings, and see live preview_

### Read Tab - Decode & Copy

![Read Tab](docs/screenshots/read-tab.png)
_Decode QR codes and copy text with one click_

> **Note:** Screenshots coming soon! The application features a modern Nimbus UI with split-panel layout, live preview, and intuitive controls.

---

## 🏗️ Development

### Project Structure

```
QR-Code-Reader/
├── src/
│   ├── QRCodeGenerator.java    # QR code generation utility
│   ├── QRCodeReader.java       # QR code reading utility
│   ├── QRCodeApp.java          # Legacy CLI menu interface
│   └── QRCodeAppGUI.java       # Modern Swing desktop application
├── lib/
│   ├── core-3.5.3.jar          # ZXing core library
│   └── javase-3.5.3.jar        # ZXing JavaSE extensions
├── target/
│   ├── classes/                # Compiled .class files
│   └── qr-code-reader.jar      # Executable JAR with dependencies
├── pom.xml                     # Maven configuration
├── build.bat                   # Windows build script
├── run.bat                     # Windows run script
└── README.md                   # This file
```

### Key Components

#### `pom.xml` - Maven Configuration

```xml
<dependencies>
    <dependency>
        <groupId>com.google.zxing</groupId>
        <artifactId>core</artifactId>
        <version>3.5.3</version>
    </dependency>
    <dependency>
        <groupId>com.google.zxing</groupId>
        <artifactId>javase</artifactId>
        <version>3.5.3</version>
    </dependency>
</dependencies>
```

Maven automatically downloads and manages all dependencies - no manual setup required!

#### `QRCodeGenerator.java`

- Generates QR codes from text/URLs
- Saves as PNG with customizable dimensions
- Returns absolute path of generated file
- Used by both GUI and CLI

#### `QRCodeReader.java`

- Decodes QR codes from image files
- Supports PNG, JPG, GIF, and other formats
- Returns decoded text string
- Handles errors gracefully

#### `QRCodeAppGUI.java`

- Modern Swing desktop application
- **Generate Tab:** Split layout with live preview panel
- **Read Tab:** File browser with copy-to-clipboard
- Nimbus Look & Feel for professional appearance
- Integrated error handling and user feedback

#### `QRCodeApp.java`

- Legacy command-line menu system
- Maintained for backwards compatibility
- Useful for terminal-based workflows

### Building with Maven

```bash
# Clean and build (recommended)
mvn clean package

# Quick build without cleaning
mvn package

# Build with verbose output
mvn clean package -X
```

The `maven-shade-plugin` automatically creates a fat JAR (`target/qr-code-reader.jar`) with all dependencies bundled.

---

## 🛠️ Troubleshooting

### Common Issues

| Issue                           | Solution                                                      |
| ------------------------------- | ------------------------------------------------------------- |
| `Maven build fails`             | Run `mvn clean install -U` to force update dependencies       |
| `File not found`                | Verify image path exists and is accessible                    |
| `No QR code found`              | Image may not contain valid QR code or is too blurry          |
| `UnsupportedOperationException` | Check Java version (requires Java 21+)                        |
| GUI doesn't launch              | Verify Java: `java -version` and rebuild: `mvn clean package` |
| `JAVA_HOME not set`             | Set environment variable to your JDK installation path        |

### Maven Troubleshooting

```bash
# Force refresh dependencies
mvn clean install -U

# Skip tests if they're failing
mvn clean package -DskipTests

# Clear Maven cache
mvn dependency:purge-local-repository

# Check Maven version (need 3.6.0+)
mvn -version
```

### Debug Mode

Run with verbose Maven output:

```bash
mvn clean package -X
```

Run application with logging:

```bash
java -Djava.util.logging.level=FINE -jar target/qr-code-reader.jar
```

### Platform-Specific Notes

**Windows:**

- Use `build.bat` and `run-jar.bat` for convenience
- PowerShell: May need `.\build.bat` instead of `build.bat`
- Maven Daemon (mvnd) recommended for faster builds

**Linux/Mac:**

- Make scripts executable: `chmod +x build.bat run-jar.bat`
- Or use Maven directly: `mvn clean package && java -jar target/qr-code-reader.jar`

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Ideas for Contributions

- [ ] Add more QR code customization options (colors, logos)
- [ ] Implement batch processing for multiple QR codes
- [ ] Add QR code history/favorites
- [ ] Export to different formats (SVG, PDF)
- [ ] Add QR code validation and error correction levels
- [ ] Implement drag-and-drop for image loading
- [ ] Add internationalization (i18n) support

---

## 📄 License

This project is licensed under the **Apache License 2.0** - see the [LICENSE](LICENSE) file for details.

### Third-Party Libraries

- **ZXing** (Zebra Crossing) - Apache License 2.0
  - Copyright © ZXing Authors
  - https://github.com/zxing/zxing

---

## 👨‍💻 Author

**Anush Dighore**

- GitHub: [@anushdighore](https://github.com/anushdighore)
- Repository: [QR-Code-Reader](https://github.com/anushdighore/QR-Code-Reader)

---

## 🎓 Acknowledgments

- Created as part of a Capstone Project
- Built with [ZXing](https://github.com/zxing/zxing) library
- Inspired by the need for a simple, offline QR code tool
- Special thanks to the open-source community

---

## 📊 Project Stats

![GitHub repo size](https://img.shields.io/github/repo-size/anushdighore/QR-Code-Reader?style=flat-square)
![GitHub last commit](https://img.shields.io/github/last-commit/anushdighore/QR-Code-Reader?style=flat-square)
![GitHub stars](https://img.shields.io/github/stars/anushdighore/QR-Code-Reader?style=social)

---

<div align="center">

**Made with ❤️ using Java and Swing**

**[⬆ Back to Top](#-qr-code-studio)**

</div>
