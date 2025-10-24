# Barcode Support Guide

## Overview
This application now supports **7 different code formats** including QR codes and 6 barcode types. Each format has specific use cases and data requirements.

## Supported Formats

### 1. QR Code
- **Best For**: URLs, text, contact information, WiFi credentials
- **Data Requirements**: Any text (no restrictions)
- **Typical Size**: 300x300 pixels
- **Example Data**: 
  - `https://github.com`
  - `Hello World!`
  - `BEGIN:VCARD\nFN:John Doe\nEND:VCARD`

### 2. Code 128
- **Best For**: General-purpose, shipping labels, inventory
- **Data Requirements**: ASCII characters (0-127)
- **Typical Size**: 400x200 pixels
- **Example Data**: 
  - `ABC-123456`
  - `SHIP-2024-001`
  - `Product-XYZ`

### 3. Code 39
- **Best For**: Industrial applications, inventory management
- **Data Requirements**: 
  - Uppercase letters (A-Z)
  - Digits (0-9)
  - Special characters: `-`, `.`, space, `$`, `/`, `+`, `%`
- **Typical Size**: 400x200 pixels
- **Example Data**: 
  - `CODE-39`
  - `ITEM-12345`
  - `ABC 123`
- **Note**: Lowercase letters are NOT supported

### 4. EAN-13
- **Best For**: Retail products (European Article Number)
- **Data Requirements**: Exactly 12 or 13 digits
- **Typical Size**: 400x200 pixels
- **Example Data**: 
  - `5901234123457`
  - `978014300723` (ISBN)
- **Note**: Check digit is automatically calculated if you provide 12 digits

### 5. EAN-8
- **Best For**: Small retail products
- **Data Requirements**: Exactly 7 or 8 digits
- **Typical Size**: 300x150 pixels
- **Example Data**: 
  - `12345678`
  - `9638507`

### 6. UPC-A
- **Best For**: Retail products in North America
- **Data Requirements**: Exactly 11 or 12 digits
- **Typical Size**: 400x200 pixels
- **Example Data**: 
  - `012345678905`
  - `72527273070`

### 7. UPC-E
- **Best For**: Small retail products (compressed UPC)
- **Data Requirements**: 6, 7, or 8 digits
- **Typical Size**: 300x150 pixels
- **Example Data**: 
  - `01234565`
  - `123456`

## Using the GUI

### Generating Codes

1. **Select Format**: Choose from the dropdown menu
2. **Enter Data**: Type your data (validation happens automatically)
3. **Set Size**: Adjust the size slider (100-1000 pixels)
4. **Generate**: Click "Generate Code" button
5. **Preview**: See the result in the preview pane
6. **Save**: File is automatically saved with format-specific name

### Reading Codes

1. **Browse**: Click "Browse" to select an image
2. **Read**: Click "Read Code" button
3. **View Results**: 
   - Decoded text appears in the text area
   - Format type is displayed below
   - Character count is shown

## Programmatic Usage

### Example 1: Generate a Code 128 Barcode

```java
import factory.CodeGeneratorFactory;
import model.CodeFormat;
import service.ICodeGenerator;

ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_128);
Path path = generator.generateCode("ABC-123", "barcode.png", 400, 200);
System.out.println("Saved to: " + path);
```

### Example 2: Generate an EAN-13 Barcode

```java
ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.EAN_13);

// Validate before generating
if (generator.validateData("5901234123457")) {
    Path path = generator.generateCode("5901234123457", "ean13.png", 400, 200);
}
```

### Example 3: Read Any Format

```java
import factory.CodeReaderFactory;
import service.ICodeReader;

ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
String decoded = reader.readCode("barcode.png");
System.out.println("Decoded: " + decoded);
```

### Example 4: Batch Generation

```java
String[] products = {"5901234123457", "5901234123464", "5901234123471"};

ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.EAN_13);

for (int i = 0; i < products.length; i++) {
    String filename = "product_" + i + ".png";
    generator.generateCode(products[i], filename, 400, 200);
}
```

## Validation Rules

Each format has built-in validation:

```java
ICodeGenerator generator = CodeGeneratorFactory.createGenerator(format);

if (!generator.validateData(data)) {
    System.out.println("Invalid data for " + format.getDisplayName());
}
```

### Common Validation Errors

| Format | Error | Solution |
|--------|-------|----------|
| Code 39 | Lowercase letters | Convert to uppercase |
| EAN-13 | Wrong length | Must be 12 or 13 digits |
| EAN-8 | Wrong length | Must be 7 or 8 digits |
| UPC-A | Wrong length | Must be 11 or 12 digits |
| Code 128 | Non-ASCII | Use only ASCII characters |

## Size Recommendations

| Format | Minimum | Recommended | Maximum |
|--------|---------|-------------|---------|
| QR Code | 100x100 | 300x300 | 1000x1000 |
| Code 128 | 200x100 | 400x200 | 800x400 |
| Code 39 | 200x100 | 400x200 | 800x400 |
| EAN-13 | 200x100 | 400x200 | 600x300 |
| EAN-8 | 150x75 | 300x150 | 500x250 |
| UPC-A | 200x100 | 400x200 | 600x300 |
| UPC-E | 150x75 | 300x150 | 500x250 |

## Tips and Best Practices

### For Generation
1. **Use appropriate sizes**: Barcodes are typically wider than tall (2:1 ratio)
2. **Validate first**: Always check data validity before generating
3. **Choose the right format**: Use retail formats (EAN, UPC) for products
4. **Test scanning**: Verify generated codes with a real scanner

### For Reading
1. **Good image quality**: Use clear, well-lit images
2. **Proper alignment**: Ensure barcode is not skewed
3. **Sufficient contrast**: Black bars on white background work best
4. **Adequate resolution**: At least 200 DPI for reliable scanning

### Common Use Cases

**Inventory Management**: Use Code 128 or Code 39
```java
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_128);
gen.generateCode("ITEM-2024-001", "inventory.png", 400, 200);
```

**Retail Products**: Use EAN-13 or UPC-A
```java
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.EAN_13);
gen.generateCode("5901234123457", "product.png", 400, 200);
```

**URLs and Text**: Use QR Code
```java
ICodeGenerator gen = CodeGeneratorFactory.createGenerator(CodeFormat.QR_CODE);
gen.generateCode("https://example.com", "qr.png", 300, 300);
```

## Troubleshooting

### "Invalid data for format"
- Check the validation rules for your chosen format
- Ensure data meets length and character requirements

### "No code found in image"
- Verify image quality and contrast
- Ensure the code is not damaged or distorted
- Try increasing image resolution

### "Failed to generate code"
- Check disk space
- Verify write permissions for output directory
- Ensure data is not empty

## Running Examples

To see all formats in action:

```bash
# Compile and run the example
mvn clean compile
mvn exec:java -Dexec.mainClass="BarcodeExample"
```

This will generate sample codes for all supported formats and demonstrate reading them back.
