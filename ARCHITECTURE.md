# Architecture Documentation

## Overview
This project follows **Object-Oriented Programming (OOP) principles** and **industry-standard design patterns** to provide a flexible and maintainable QR Code and Barcode generation/reading system.

## Design Patterns Used

### 1. **Factory Pattern**
- **Location**: `factory` package
- **Classes**: `CodeGeneratorFactory`, `CodeReaderFactory`
- **Purpose**: Encapsulates object creation logic, allowing easy addition of new code formats
- **Benefits**: 
  - Loose coupling between client code and concrete implementations
  - Single point of modification when adding new formats
  - Follows the Dependency Inversion Principle (DIP)

### 2. **Template Method Pattern**
- **Location**: `service` package
- **Classes**: `AbstractCodeGenerator`, `AbstractCodeReader`
- **Purpose**: Defines the skeleton of algorithms while allowing subclasses to override specific steps
- **Benefits**:
  - Code reuse through inheritance
  - Consistent workflow across all implementations
  - Easy to add new code formats by extending base classes

### 3. **Facade Pattern**
- **Location**: Root package
- **Classes**: `QRCodeGenerator`, `QRCodeReader`
- **Purpose**: Provides backward compatibility with existing code while using the new architecture
- **Benefits**:
  - Maintains API compatibility
  - Simplifies migration to new architecture
  - Hides complexity from legacy code

### 4. **Strategy Pattern** (Implicit)
- **Location**: `service.impl` package
- **Purpose**: Different encoding/decoding strategies for different code formats
- **Benefits**: Interchangeable algorithms at runtime

## SOLID Principles Applied

### Single Responsibility Principle (SRP)
- Each class has one reason to change
- `QRCodeGeneratorImpl` only handles QR code generation
- `Code128GeneratorImpl` only handles Code 128 barcode generation
- Each reader implementation handles one specific format

### Open/Closed Principle (OCP)
- System is open for extension (new formats) but closed for modification
- Adding a new barcode format requires:
  1. Creating a new generator class extending `AbstractCodeGenerator`
  2. Adding the format to `CodeFormat` enum
  3. Updating the factory - no changes to existing code

### Liskov Substitution Principle (LSP)
- All concrete generators can be substituted for `ICodeGenerator`
- All concrete readers can be substituted for `ICodeReader`
- Polymorphism works correctly throughout the system

### Interface Segregation Principle (ISP)
- `ICodeGenerator` and `ICodeReader` are focused interfaces
- Clients depend only on the methods they use
- No fat interfaces with unused methods

### Dependency Inversion Principle (DIP)
- High-level modules (GUI) depend on abstractions (`ICodeGenerator`, `ICodeReader`)
- Low-level modules (concrete implementations) also depend on abstractions
- No direct dependencies on concrete classes in client code

## Package Structure

```
src/
├── model/
│   └── CodeFormat.java              # Enum for supported formats
├── service/
│   ├── ICodeGenerator.java          # Generator interface
│   ├── ICodeReader.java             # Reader interface
│   ├── AbstractCodeGenerator.java   # Base generator class
│   ├── AbstractCodeReader.java      # Base reader class
│   └── impl/
│       ├── QRCodeGeneratorImpl.java
│       ├── Code128GeneratorImpl.java
│       ├── Code39GeneratorImpl.java
│       ├── EAN13GeneratorImpl.java
│       ├── EAN8GeneratorImpl.java
│       ├── UPCAGeneratorImpl.java
│       ├── UPCEGeneratorImpl.java
│       ├── QRCodeReaderImpl.java
│       └── MultiFormatReaderImpl.java
├── factory/
│   ├── CodeGeneratorFactory.java    # Factory for generators
│   └── CodeReaderFactory.java       # Factory for readers
├── QRCodeAppGUI.java                # Main GUI application
├── QRCodeGenerator.java             # Facade for backward compatibility
└── QRCodeReader.java                # Facade for backward compatibility
```

## Supported Formats

### QR Codes
- **Format**: QR_CODE
- **Use Case**: URLs, text, contact information
- **Validation**: Any non-empty text

### Barcodes

#### Code 128
- **Use Case**: General-purpose, high-density barcode
- **Validation**: ASCII characters (0-127)

#### Code 39
- **Use Case**: Industrial, inventory management
- **Validation**: 0-9, A-Z, and special characters (-, ., space, $, /, +, %)

#### EAN-13
- **Use Case**: Retail products (European Article Number)
- **Validation**: 12 or 13 digits

#### EAN-8
- **Use Case**: Small retail products
- **Validation**: 7 or 8 digits

#### UPC-A
- **Use Case**: Retail products in North America
- **Validation**: 11 or 12 digits

#### UPC-E
- **Use Case**: Small retail products (compressed UPC)
- **Validation**: 6, 7, or 8 digits

## Adding a New Format

To add a new barcode format:

1. **Add to CodeFormat enum**:
```java
NEW_FORMAT("Display Name", "default_filename.png")
```

2. **Create Generator Implementation**:
```java
public class NewFormatGeneratorImpl extends AbstractCodeGenerator {
    public NewFormatGeneratorImpl() {
        super(CodeFormat.NEW_FORMAT, BarcodeFormat.NEW_FORMAT);
    }
    
    @Override
    protected BitMatrix encode(String data, int width, int height) 
            throws WriterException {
        // Implementation
    }
    
    @Override
    public boolean validateData(String data) {
        // Validation logic
    }
}
```

3. **Update Factory**:
```java
case NEW_FORMAT -> new NewFormatGeneratorImpl();
```

## Usage Examples

### Using the Factory Pattern (Recommended)
```java
// Generate a Code 128 barcode
ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_128);
Path path = generator.generateCode("ABC123", "barcode.png", 400, 200);

// Read any format
ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
String decoded = reader.readCode("barcode.png");
```

### Using the Facade (Backward Compatible)
```java
// Still works with existing code
Path path = QRCodeGenerator.generateQRCode("Hello", "qr.png", 300, 300);
String text = QRCodeReader.readQRCode("qr.png");
```

## Benefits of This Architecture

1. **Maintainability**: Clear separation of concerns, easy to locate and fix bugs
2. **Extensibility**: New formats can be added without modifying existing code
3. **Testability**: Each component can be tested independently
4. **Reusability**: Common functionality is in base classes
5. **Flexibility**: Easy to swap implementations or add features
6. **Industry Standard**: Follows well-known design patterns and SOLID principles

## Future Enhancements

- Add configuration options (error correction level, margins, colors)
- Implement caching for frequently generated codes
- Add batch processing capabilities
- Support for additional formats (PDF417, Data Matrix, Aztec)
- Export to multiple image formats (JPEG, SVG, PDF)
