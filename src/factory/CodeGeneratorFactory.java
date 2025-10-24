package factory;

import model.CodeFormat;
import service.ICodeGenerator;
import service.impl.*;

/**
 * Factory class for creating code generators
 * Follows the Factory Method pattern and Dependency Inversion Principle (DIP)
 */
public class CodeGeneratorFactory {
    
    /**
     * Create a code generator based on the specified format
     * 
     * @param format The code format
     * @return ICodeGenerator instance
     * @throws IllegalArgumentException if format is not supported
     */
    public static ICodeGenerator createGenerator(CodeFormat format) {
        if (format == null) {
            throw new IllegalArgumentException("Code format cannot be null");
        }
        
        return switch (format) {
            case QR_CODE -> new QRCodeGeneratorImpl();
            case CODE_128 -> new Code128GeneratorImpl();
            case CODE_39 -> new Code39GeneratorImpl();
            case EAN_13 -> new EAN13GeneratorImpl();
            case EAN_8 -> new EAN8GeneratorImpl();
            case UPC_A -> new UPCAGeneratorImpl();
            case UPC_E -> new UPCEGeneratorImpl();
        };
    }
    
    /**
     * Get all supported formats
     * 
     * @return Array of supported CodeFormat values
     */
    public static CodeFormat[] getSupportedFormats() {
        return CodeFormat.values();
    }
}
