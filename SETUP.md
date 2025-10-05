# QR Code Reader - Setup Guide

## Prerequisites

### 1. Java Development Kit (JDK)

- **Required:** JDK 17 or higher
- **Download:** https://adoptium.net/

Check if Java is installed:

```cmd
java -version
javac -version
```

### 2. Maven Build Tool

You have two options:

#### Option A: Maven Daemon (Recommended - Faster Builds)

Maven Daemon (mvnd) is a faster alternative to Maven.

**Install via Chocolatey:**

```cmd
choco install maven-mvnd
```

**Or download manually:**

- Download from: https://github.com/apache/maven-mvnd/releases
- Extract to a folder (e.g., `C:\maven-mvnd`)
- Add `C:\maven-mvnd\bin` to your PATH

#### Option B: Apache Maven (Standard)

**Install via Chocolatey:**

```cmd
choco install maven
```

**Or download manually:**

- Download from: https://maven.apache.org/download.cgi
- Extract to a folder (e.g., `C:\apache-maven`)
- Add `C:\apache-maven\bin` to your PATH

**Verify installation:**

```cmd
mvn -version
# or
mvnd --version
```

## Building the Project

### First-time Setup

1. Open command prompt in the project folder
2. Run the build:
   ```cmd
   build.bat
   ```

This will:

- Download all dependencies (ZXing library)
- Compile the source code
- Create an executable JAR file

### Build Output

The build creates:

- `target/classes/` - Compiled `.class` files
- `target/qr-code-reader.jar` - Executable JAR with all dependencies

## Running the Application

### Option 1: Run the JAR file

```cmd
run-jar.bat
```

or

```cmd
java -jar target\qr-code-reader.jar
```

### Option 2: Run via Maven (for development)

```cmd
run-maven.bat
```

## Project Structure

```
QR-Code-Reader/
├── src/                    # Source code
│   ├── QRCodeAppGUI.java  # Main GUI application
│   ├── QRCodeApp.java     # CLI application (optional)
│   ├── QRCodeGenerator.java
│   └── QRCodeReader.java
├── target/                 # Build output (created by Maven)
│   ├── classes/           # Compiled .class files
│   └── qr-code-reader.jar # Executable JAR
├── pom.xml                # Maven configuration
├── build.bat              # Build script
├── run-jar.bat            # Run packaged JAR
├── run-maven.bat          # Run via Maven
└── README.md              # Documentation
```

## Maven Commands

### Clean and build

```cmd
mvn clean package
# or with Maven Daemon
mvnd clean package
```

### Run without building JAR

```cmd
mvn exec:java -Dexec.mainClass="QRCodeAppGUI"
```

### Clean only

```cmd
mvn clean
```

### Compile only

```cmd
mvn compile
```

## Troubleshooting

### Maven not found

- Make sure Maven is installed and in your PATH
- Restart your command prompt after installation
- Check with: `mvn -version` or `mvnd --version`

### Java version error

- Ensure JDK 17 or higher is installed
- Check with: `java -version`
- Update `JAVA_HOME` environment variable if needed

### Build fails

1. Check your internet connection (Maven downloads dependencies)
2. Delete the `target` folder and rebuild
3. Run: `mvn clean install -U` to force update dependencies

## Benefits of Maven

✅ **Automatic dependency management** - No need to manually download JARs  
✅ **Standardized project structure** - Industry standard  
✅ **Easy builds** - One command to build everything  
✅ **Fat JAR creation** - Single executable file with all dependencies  
✅ **Reproducible builds** - Same result on any machine  
✅ **IDE integration** - Works with IntelliJ IDEA, Eclipse, VS Code

## Next Steps

1. Run `build.bat` to build the project
2. Run `run-jar.bat` to start the application
3. See `README.md` for usage instructions
