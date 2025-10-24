import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import factory.CodeGeneratorFactory;
import factory.CodeReaderFactory;
import model.CodeFormat;
import service.ICodeGenerator;
import service.ICodeReader;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Example demonstrating barcode generation and reading capabilities
 * Shows how to use the new OOP architecture
 */
public class BarcodeExample {

    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("QR Code & Barcode Studio - Examples");
        System.out.println("=".repeat(70));
        System.out.println();

        // Example 1: Generate and read QR Code
        demonstrateQRCode();
        
        // Example 2: Generate and read Code 128
        demonstrateCode128();
        
        // Example 3: Generate and read EAN-13
        demonstrateEAN13();
        
        // Example 4: Generate and read Code 39
        demonstrateCode39();
        
        // Example 5: Multi-format reading
        demonstrateMultiFormatReading();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("All examples completed successfully!");
        System.out.println("=".repeat(70));
    }

    private static void demonstrateQRCode() {
        System.out.println("Example 1: QR Code");
        System.out.println("-".repeat(70));
        
        try {
            String data = "https://github.com/QR-Code-Reader";
            ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.QR_CODE);
            
            Path path = generator.generateCode(data, "example_qrcode.png", 300, 300);
            System.out.println("✓ Generated QR Code: " + path);
            
            ICodeReader reader = CodeReaderFactory.createReader(CodeFormat.QR_CODE);
            String decoded = reader.readCode(path.toString());
            System.out.println("✓ Decoded: " + decoded);
            System.out.println("✓ Match: " + data.equals(decoded));
            
        } catch (WriterException | IOException | NotFoundException e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demonstrateCode128() {
        System.out.println("Example 2: Code 128 Barcode");
        System.out.println("-".repeat(70));
        
        try {
            String data = "ABC-123456";
            ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_128);
            
            if (!generator.validateData(data)) {
                System.err.println("✗ Invalid data for Code 128");
                return;
            }
            
            Path path = generator.generateCode(data, "example_code128.png", 400, 200);
            System.out.println("✓ Generated Code 128: " + path);
            
            ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
            String decoded = reader.readCode(path.toString());
            System.out.println("✓ Decoded: " + decoded);
            System.out.println("✓ Match: " + data.equals(decoded));
            
        } catch (WriterException | IOException | NotFoundException e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demonstrateEAN13() {
        System.out.println("Example 3: EAN-13 Barcode");
        System.out.println("-".repeat(70));
        
        try {
            String data = "5901234123457"; // Valid EAN-13
            ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.EAN_13);
            
            if (!generator.validateData(data)) {
                System.err.println("✗ Invalid data for EAN-13 (must be 12-13 digits)");
                return;
            }
            
            Path path = generator.generateCode(data, "example_ean13.png", 400, 200);
            System.out.println("✓ Generated EAN-13: " + path);
            
            ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
            String decoded = reader.readCode(path.toString());
            System.out.println("✓ Decoded: " + decoded);
            
        } catch (WriterException | IOException | NotFoundException e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demonstrateCode39() {
        System.out.println("Example 4: Code 39 Barcode");
        System.out.println("-".repeat(70));
        
        try {
            String data = "CODE-39";
            ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_39);
            
            if (!generator.validateData(data)) {
                System.err.println("✗ Invalid data for Code 39");
                return;
            }
            
            Path path = generator.generateCode(data, "example_code39.png", 400, 200);
            System.out.println("✓ Generated Code 39: " + path);
            
            ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
            String decoded = reader.readCode(path.toString());
            System.out.println("✓ Decoded: " + decoded);
            System.out.println("✓ Match: " + data.equals(decoded));
            
        } catch (WriterException | IOException | NotFoundException e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
        System.out.println();
    }

    private static void demonstrateMultiFormatReading() {
        System.out.println("Example 5: Multi-Format Reading");
        System.out.println("-".repeat(70));
        System.out.println("The MultiFormatReader can automatically detect and read");
        System.out.println("QR codes and various barcode formats from images.");
        System.out.println();
        
        System.out.println("Supported formats:");
        for (CodeFormat format : CodeFormat.values()) {
            System.out.println("  • " + format.getDisplayName());
        }
        System.out.println();
    }

    /**
     * Demonstrates validation for different formats
     */
    public static void demonstrateValidation() {
        System.out.println("Format Validation Examples:");
        System.out.println("-".repeat(70));
        
        // EAN-13 validation
        ICodeGenerator ean13 = CodeGeneratorFactory.createGenerator(CodeFormat.EAN_13);
        System.out.println("EAN-13 '1234567890123': " + ean13.validateData("1234567890123")); // true
        System.out.println("EAN-13 'ABC': " + ean13.validateData("ABC")); // false
        
        // Code 39 validation
        ICodeGenerator code39 = CodeGeneratorFactory.createGenerator(CodeFormat.CODE_39);
        System.out.println("Code 39 'ABC-123': " + code39.validateData("ABC-123")); // true
        System.out.println("Code 39 'abc': " + code39.validateData("abc")); // false (lowercase not allowed)
        
        System.out.println();
    }
}
