# Quick Reference - QR Code & Barcode Studio

## Supported Formats

| Format | Use Case | Data Requirements | Example |
|--------|----------|-------------------|---------|
| QR Code | URLs, text, general | Any text | `https://example.com` |
| Code 128 | Shipping, inventory | ASCII (0-127) | `ABC-123456` |
| Code 39 | Industrial | 0-9, A-Z, `-. $/+%` | `ITEM-123` |
| EAN-13 | Retail products | 12-13 digits | `5901234123457` |
| EAN-8 | Small products | 7-8 digits | `12345678` |
| UPC-A | US retail | 11-12 digits | `012345678905` |
| UPC-E | Small US products | 6-8 digits | `01234565` |

## Code Examples

### Generate QR Code
```java
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.QR_CODE);
Path path = gen.generateCode("Hello World", "qr.png", 300, 300);
```

### Generate Barcode
```java
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_128);
Path path = gen.generateCode("ABC-123", "barcode.png", 400, 200);
```

### Read Any Format
```java
ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
String decoded = reader.readCode("image.png");
```

### Validate Data
```java
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.EAN_13);
if (gen.validateData("5901234123457")) {
    gen.generateCode("5901234123457", "ean13.png", 400, 200);
}
```

## GUI Usage

1. **Generate Tab**
   - Select format from dropdown
   - Enter data
   - Set size (100-1000px)
   - Click "Generate Code"

2. **Read Tab**
   - Browse for image
   - Click "Read Code"
   - View decoded text and format

## Class Hierarchy

```
ICodeGenerator (interface)
    ↑
AbstractCodeGenerator
    ↑
    ├── QRCodeGeneratorImpl
    ├── Code128GeneratorImpl
    ├── Code39GeneratorImpl
    ├── EAN13GeneratorImpl
    ├── EAN8GeneratorImpl
    ├── UPCAGeneratorImpl
    └── UPCEGeneratorImpl

ICodeReader (interface)
    ↑
AbstractCodeReader
    ↑
    ├── QRCodeReaderImpl
    └── MultiFormatReaderImpl
```

## Factory Methods

```java
// Get generator for specific format
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_128);

// Get all supported formats
CodeFormat[] formats = CodeGeneratorFactory.getSupportedFormats();

// Get multi-format reader
ICodeReader reader = CodeReaderFactory.createMultiFormatReader();

// Get format-specific reader
ICodeReader reader = CodeReaderFactory.createReader(CodeFormat.QR_CODE);
```

## Common Validation Errors

| Format | Error | Fix |
|--------|-------|-----|
| Code 39 | Lowercase letters | Use uppercase only |
| EAN-13 | Wrong length | Use 12-13 digits |
| Code 128 | Non-ASCII | Use ASCII only |
| All numeric | Letters in data | Use digits only |

## Recommended Sizes

| Format | Width | Height |
|--------|-------|--------|
| QR Code | 300 | 300 |
| Code 128 | 400 | 200 |
| Code 39 | 400 | 200 |
| EAN-13 | 400 | 200 |
| EAN-8 | 300 | 150 |
| UPC-A | 400 | 200 |
| UPC-E | 300 | 150 |

## Build & Run

```bash
# Build
mvn clean package

# Run GUI
java -jar target/qr-code-reader.jar

# Run examples
mvn exec:java -Dexec.mainClass="BarcodeExample"
```

## Key Files

- **GUI**: `QRCodeAppGUI.java`
- **Factories**: `factory/CodeGeneratorFactory.java`, `factory/CodeReaderFactory.java`
- **Formats**: `model/CodeFormat.java`
- **Examples**: `BarcodeExample.java`
- **Docs**: `ARCHITECTURE.md`, `BARCODE_GUIDE.md`

## Design Patterns Used

- ✅ Factory Pattern - Object creation
- ✅ Template Method - Workflow definition
- ✅ Facade Pattern - Backward compatibility
- ✅ Strategy Pattern - Format-specific logic

## SOLID Principles

- ✅ Single Responsibility
- ✅ Open/Closed
- ✅ Liskov Substitution
- ✅ Interface Segregation
- ✅ Dependency Inversion
