# Contributing to QR Code Studio

Thank you for your interest in contributing to QR Code Studio! This document provides guidelines and instructions for contributing to the project.

## 🚀 Getting Started

### Prerequisites

- **Java 21 LTS** or higher
- **Maven 3.6.0+** (or Maven Daemon for faster builds)
- **Git** for version control
- A Java IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

### Setting Up Development Environment

1. **Fork the repository**

   ```bash
   # Fork on GitHub, then clone your fork
   git clone https://github.com/YOUR-USERNAME/QR-Code-Reader.git
   cd QR-Code-Reader
   ```

2. **Add upstream remote**

   ```bash
   git remote add upstream https://github.com/anushdighore/QR-Code-Reader.git
   ```

3. **Build the project**

   ```bash
   mvn clean package
   ```

4. **Run tests**

   ```bash
   mvn test
   ```

5. **Launch the application**
   ```bash
   java -jar target/qr-code-reader.jar
   ```

---

## 📝 Development Workflow

### 1. Create a Feature Branch

Always create a new branch for your work:

```bash
git checkout -b feature/your-feature-name
# or
git checkout -b fix/bug-description
```

Branch naming conventions:

- `feature/` - New features
- `fix/` - Bug fixes
- `docs/` - Documentation updates
- `refactor/` - Code refactoring
- `test/` - Test additions/improvements

### 2. Make Your Changes

- Write clean, readable code
- Follow existing code style and conventions
- Add comments for complex logic
- Update documentation if needed

### 3. Test Your Changes

```bash
# Run all tests
mvn test

# Build and test the JAR
mvn clean package
java -jar target/qr-code-reader.jar
```

### 4. Commit Your Changes

Write clear, descriptive commit messages:

```bash
git add .
git commit -m "feat: Add batch QR code generation feature"
```

Commit message format:

- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `style:` - Code style changes (formatting, etc.)
- `refactor:` - Code refactoring
- `test:` - Test additions or changes
- `chore:` - Build process or auxiliary tool changes

### 5. Push and Create Pull Request

```bash
git push origin feature/your-feature-name
```

Then create a Pull Request on GitHub with:

- Clear title describing the change
- Detailed description of what and why
- Screenshots (if UI changes)
- Reference any related issues

---

## 🏗️ Project Structure

```
QR-Code-Reader/
├── src/
│   ├── QRCodeGenerator.java    # Core generation logic
│   ├── QRCodeReader.java       # Core reading logic
│   ├── QRCodeApp.java          # CLI interface
│   └── QRCodeAppGUI.java       # Swing GUI (main class)
├── target/                     # Build output (git ignored)
├── pom.xml                     # Maven configuration
└── *.bat                       # Convenience scripts
```

### Key Files

- **`pom.xml`** - Maven dependencies and build configuration
- **`QRCodeAppGUI.java`** - Main entry point for the GUI application
- **`QRCodeGenerator.java`** - Utility for QR code generation using ZXing
- **`QRCodeReader.java`** - Utility for QR code decoding

---

## 💡 Contribution Ideas

### Features

- [ ] Add QR code customization (colors, error correction levels)
- [ ] Implement batch processing for multiple QR codes
- [ ] Add QR code history/favorites management
- [ ] Export to different formats (SVG, PDF, EPS)
- [ ] Add webcam support for live QR scanning
- [ ] Implement drag-and-drop for image loading
- [ ] Add vCard QR code generation wizard
- [ ] Create dark mode theme

### Improvements

- [ ] Add unit tests for core functionality
- [ ] Improve error handling and user feedback
- [ ] Add internationalization (i18n) support
- [ ] Optimize image processing performance
- [ ] Add keyboard shortcuts for common actions
- [ ] Improve accessibility (screen readers, etc.)

### Documentation

- [ ] Add code examples and tutorials
- [ ] Create video walkthrough
- [ ] Add API documentation (Javadoc)
- [ ] Improve inline code comments

---

## 🎨 Code Style Guidelines

### Java Conventions

- **Indentation:** 4 spaces (no tabs)
- **Line length:** Max 120 characters
- **Naming:**
  - Classes: `PascalCase`
  - Methods: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`
  - Variables: `camelCase`
- **Braces:** Opening brace on same line
- **Comments:** Use Javadoc for public methods

### Example

```java
/**
 * Generates a QR code from the given data.
 *
 * @param data The text to encode
 * @param width Width in pixels
 * @param height Height in pixels
 * @return Path to the generated image
 * @throws WriterException If encoding fails
 */
public static Path generateQRCode(String data, int width, int height)
        throws WriterException {
    // Implementation
}
```

---

## 🧪 Testing Guidelines

### Writing Tests

- Place tests in `src/test/java/`
- Test file naming: `ClassNameTest.java`
- Use JUnit 5 for unit tests

Example:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QRCodeGeneratorTest {
    @Test
    void testGenerateQRCode() {
        // Arrange
        String data = "https://example.com";

        // Act
        Path result = QRCodeGenerator.generateQRCode(data, "test.png", 300, 300);

        // Assert
        assertTrue(Files.exists(result));
    }
}
```

---

## 📦 Building and Releasing

### Maven Build

```bash
# Development build
mvn clean package

# Production build (with all validations)
mvn clean verify

# Build without tests (quick)
mvn clean package -DskipTests
```

### Creating a Release

1. Update version in `pom.xml`
2. Update `CHANGELOG.md` (if exists)
3. Create release tag: `git tag -a v1.0.0 -m "Release version 1.0.0"`
4. Push tag: `git push origin v1.0.0`
5. Create GitHub Release with changelog

---

## 🐛 Reporting Issues

When reporting bugs, please include:

- **Environment:** OS, Java version, Maven version
- **Steps to reproduce:** Clear, numbered steps
- **Expected behavior:** What should happen
- **Actual behavior:** What actually happens
- **Screenshots/Logs:** If applicable
- **Error messages:** Full stack traces

### Issue Template

```markdown
**Environment:**

- OS: Windows 11
- Java: 21.0.1
- Maven: 3.9.5

**Steps to Reproduce:**

1. Launch application
2. Click "Generate QR Code"
3. Enter text "test"
4. Click Generate

**Expected:** QR code should be created
**Actual:** Error dialog appears

**Error Message:**
```

java.io.IOException: Cannot write to file

```

```

---

## 📄 License

By contributing, you agree that your contributions will be licensed under the Apache License 2.0, the same license as the project.

---

## 🤝 Code of Conduct

- Be respectful and inclusive
- Provide constructive feedback
- Focus on what is best for the project
- Show empathy towards other contributors

---

## 📞 Questions?

- **Issues:** Use GitHub Issues for bug reports and feature requests
- **Discussions:** Use GitHub Discussions for questions and ideas
- **Email:** Contact maintainer for private concerns

---

## 🙏 Acknowledgments

Thank you for contributing to QR Code Studio! Every contribution, no matter how small, helps make this project better.

**Happy Coding! 🚀**
