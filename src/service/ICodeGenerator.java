package service;

import com.google.zxing.WriterException;
import model.CodeFormat;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface for code generation operations
 * Follows the Interface Segregation Principle (ISP)
 */
public interface ICodeGenerator {
    
    /**
     * Generate a code image and save it to a file
     * 
     * @param data     The data to encode
     * @param filePath The output file path
     * @param width    Width of the image
     * @param height   Height of the image
     * @return The absolute path to the generated file
     * @throws WriterException If encoding fails
     * @throws IOException     If file writing fails
     */
    Path generateCode(String data, String filePath, int width, int height) 
            throws WriterException, IOException;
    
    /**
     * Get the format this generator supports
     * 
     * @return The CodeFormat
     */
    CodeFormat getFormat();
    
    /**
     * Validate if the data is suitable for this code format
     * 
     * @param data The data to validate
     * @return true if valid, false otherwise
     */
    boolean validateData(String data);
}
