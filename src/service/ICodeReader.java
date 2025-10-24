package service;

import com.google.zxing.NotFoundException;
import model.CodeFormat;

import java.io.IOException;

/**
 * Interface for code reading operations
 * Follows the Interface Segregation Principle (ISP)
 */
public interface ICodeReader {
    
    /**
     * Read and decode a code from an image file
     * 
     * @param filePath Path to the image file
     * @return Decoded text
     * @throws IOException       If file reading fails
     * @throws NotFoundException If no code is found
     */
    String readCode(String filePath) throws IOException, NotFoundException;
    
    /**
     * Get the format this reader supports
     * 
     * @return The CodeFormat, or null if multi-format
     */
    CodeFormat getFormat();
}
