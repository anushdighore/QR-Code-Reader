package factory;

import model.CodeFormat;
import service.ICodeReader;
import service.impl.MultiFormatReaderImpl;
import service.impl.QRCodeReaderImpl;

/**
 * Factory class for creating code readers
 * Follows the Factory Method pattern and Dependency Inversion Principle (DIP)
 */
public class CodeReaderFactory {
    
    /**
     * Create a code reader based on the specified format
     * If format is null, returns a multi-format reader
     * 
     * @param format The code format, or null for multi-format
     * @return ICodeReader instance
     */
    public static ICodeReader createReader(CodeFormat format) {
        if (format == null || format != CodeFormat.QR_CODE) {
            // Use multi-format reader for barcodes and when format is unspecified
            return new MultiFormatReaderImpl();
        }
        
        // Use specific QR code reader for QR codes
        return new QRCodeReaderImpl();
    }
    
    /**
     * Create a multi-format reader that can read any supported format
     * 
     * @return ICodeReader instance
     */
    public static ICodeReader createMultiFormatReader() {
        return new MultiFormatReaderImpl();
    }
}
