# Quick Start Guide - Java QR Code Project

## ✅ Setup Complete!

Your Java QR Code Studio is ready with both a desktop GUI and optional CLI utilities.

## 🚀 Launch the Desktop App

```batch
run.bat
```

- Opens the Swing-based **QR Code Studio**
- Use the **Generate** tab to create PNG QR codes
- Use the **Read** tab to decode existing QR images

## ⚙️ Optional Command-Line Tools

These still work exactly as before and are great for automation:

```batch
# Generate a sample QR code
java -cp ".;lib\*" QRCodeGenerator

# Decode a QR code image
java -cp ".;lib\*" QRCodeReader

# (Legacy) text-based menu
java -cp ".;lib\*" QRCodeApp
```

## 📁 Project Structure

```
java-qr-project/
├── lib/
│   ├── core-3.5.3.jar          ✅ Downloaded
│   └── javase-3.5.3.jar        ✅ Downloaded
├── src/
│   ├── QRCodeGenerator.java    ✅ Compiled
│   ├── QRCodeReader.java       ✅ Compiled
│   ├── QRCodeApp.java          ✅ Compiled (CLI)
│   └── QRCodeAppGUI.java       ✅ Compiled (Swing GUI)
├── download-libs.bat            ✅ Used
├── compile.bat                  ✅ Used
├── run.bat                      ⭐ Launches desktop app
└── README.md
```

## 🎯 Test Results

✅ **Libraries Downloaded**: core-3.5.3.jar, javase-3.5.3.jar  
✅ **Compilation**: Successful  
✅ **QR Generation**: Working (`sample_qrcode.png` created)  
✅ **QR Reading**: Working (decoded: https://github.com)  
✅ **GUI Smoke Test**: Window launches without errors

## 💡 Usage Examples

### In the GUI:

- **Generate:** type or paste text, choose a filename (PNG) and size, click **Generate QR Code**
- **Read:** pick an existing PNG, click **Read QR Code**, copy the decoded text from the results box

## 🔧 Maintenance Commands

**Re-download libraries:**

```batch
download-libs.bat
```

**Re-compile project:**

```batch
compile.bat
```

## ⚡ Direct Java Commands

If you prefer command-line:

**Compile:**

```batch
javac -cp "lib\*" -d . src\*.java
```

**Run GUI:**

```batch
java -cp ".;lib\*" QRCodeAppGUI
```

**Run CLI menu (optional):**

```batch
java -cp ".;lib\*" QRCodeApp
```

## 🎓 For Your Capstone

This project demonstrates:

- ✅ Java programming
- ✅ Object-oriented design
- ✅ External library integration (ZXing)
- ✅ File I/O operations
- ✅ Image processing
- ✅ Exception handling
- ✅ User input validation
- ✅ Desktop UI development (Swing)

## 📝 Next Steps for Enhancement

1. Add barcode symbology support (EAN, Code128, etc.)
2. Provide live preview/drag-and-drop
3. Batch processing (read multiple images)
4. Add database integration or certificate tracking
5. Create web interface using Spring Boot
6. Add PDF export functionality

## 🐛 Troubleshooting

**Error: ClassNotFoundException**

- Run `download-libs.bat` again
- Make sure JAR files are in `lib/` folder

**Error: File not found**

- Check image path is correct
- Use absolute path if needed

**Error: No QR code found**

- Ensure image contains a valid QR code
- Check image is not corrupted or too blurry

---

**Ready to use!** Double-click `run.bat` 🎉
