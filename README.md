# Java QR Code Studio

A desktop (Swing) and command-line toolkit that uses the ZXing library to generate and read QR codes.

## Features

- ✅ Point-and-click desktop app (Swing) for QR generation and decoding
- ✅ Custom PNG output size and save location
- ✅ Instant decoding of existing QR images with status feedback
- ✅ Command-line utilities remain available for automation and scripting

## Requirements

- Java 8 or higher
- ZXing (Zebra Crossing) library

## Setup Instructions

### Step 1: Download ZXing Library

You need to download the following JAR files from ZXing:

1. **core-3.5.3.jar** - Core ZXing library
2. **javase-3.5.3.jar** - Java SE extensions

**Option A: Direct Download Links**

- Go to: https://repo1.maven.org/maven2/com/google/zxing/
- Download:
  - `core/3.5.3/core-3.5.3.jar`
  - `javase/3.5.3/javase-3.5.3.jar`

**Option B: Maven Repository**

- Visit: https://mvnrepository.com/artifact/com.google.zxing/core/3.5.3
- Visit: https://mvnrepository.com/artifact/com.google.zxing/javase/3.5.3

### Step 2: Place JAR files

Place both JAR files in the `lib/` directory:

```
java-qr-project/
├── lib/
│   ├── core-3.5.3.jar
│   └── javase-3.5.3.jar
├── src/
│   ├── QRCodeGenerator.java
│   ├── QRCodeReader.java
│   ├── QRCodeApp.java          (legacy CLI)
│   └── QRCodeAppGUI.java       (Swing GUI)
```

### Step 3: Compile the Project

**Windows:**

```bash
javac -cp "lib\*" -d . src\*.java
```

**Linux/Mac:**

```bash
javac -cp "lib/*" -d . src/*.java
```

### Step 4: Launch the Desktop App

**Windows:**

```bash
java -cp ".;lib\*" QRCodeAppGUI
```

**Linux/Mac:**

```bash
java -cp ".:lib/*" QRCodeAppGUI
```

> 💡 Tip: On Windows you can simply double-click `run.bat` to compile (after the first run) and launch the GUI.

## Quick Test

### Generate a QR Code:

**Windows:**

```bash
java -cp ".;lib\*" QRCodeGenerator
```

**Linux/Mac:**

```bash
java -cp ".:lib/*" QRCodeGenerator
```

This will create `sample_qrcode.png` with "https://github.com" encoded.

### Read a QR Code:

**Windows:**

```bash
java -cp ".;lib\*" QRCodeReader
```

**Linux/Mac:**

```bash
java -cp ".:lib/*" QRCodeReader
```

The command-line tools are useful for quick automation or testing, while the GUI offers a user-friendly experience.

## Usage

### Desktop (Swing) Application

1. **Generate Tab**

- Enter text/URL, choose the output file (PNG) and size (100–1000 px)
- Click **Generate QR Code**
- The status bar and a dialog confirm where the file was saved

2. **Read Tab**

- Pick an existing QR image (PNG)
- Click **Read QR Code**
- Decoded text appears instantly in the results panel

### Command-Line Utilities (Optional)

- `QRCodeGenerator` – quick generation for scripts and automation
- `QRCodeReader` – decode QR images from the terminal
- `QRCodeApp` – legacy text-based menu retained for reference

## Code Structure

### QRCodeGenerator.java

- Generates QR codes from text
- Saves as PNG images
- Customizable size

### QRCodeReader.java

- Reads QR codes from image files
- Supports PNG, JPG, and other image formats
- Decodes and returns the text content

### QRCodeApp.java

- Legacy command-line menu (still available for terminal use)

### QRCodeAppGUI.java

- Swing desktop application (tabs for Generate/Read)
- Integrated file pickers, validation, and status messaging
- Uses the same generator/reader utilities under the hood

## Troubleshooting

### ClassNotFoundException

Make sure the JAR files are in the `lib/` directory and you're using the correct classpath.

### File not found

Ensure the image path is correct and the file exists.

### No QR code found

The image might not contain a valid QR code, or it might be too blurry.

## License

This project uses ZXing (Apache License 2.0)

## Author

Created for Capstone Project
