import com.google.zxing.WriterException;
import factory.CodeGeneratorFactory;
import model.CodeFormat;
import service.ICodeGenerator;

import java.io.IOException;
import java.nio.file.Path;

/**
 * QR Code Generator using ZXing library
 * This class now acts as a Facade for the new OOP architecture
 * Maintains backward compatibility with existing code
 */
public class QRCodeGenerator {

    /**
     * Generate QR Code and save it as an image file
     * 
     * @param data     The text/data to encode in QR code
     * @param filePath The output file path (e.g., "qrcode.png")
     * @param width    Width of the QR code image
     * @param height   Height of the QR code image
     * @return The absolute path to the generated file
     * @throws WriterException If encoding fails
     * @throws IOException     If file writing fails
     */
    public static Path generateQRCode(String data, String filePath, int width, int height)
            throws WriterException, IOException {
        
        // Use the new OOP architecture via factory pattern
        ICodeGenerator generator = CodeGeneratorFactory.createGenerator(CodeFormat.QR_CODE);
        return generator.generateCode(data, filePath, width, height);
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        try {
            // Example usage
            String data = "https://github.com";
            String filePath = "sample_qrcode.png";
            int width = 300;
            int height = 300;

            Path outputPath = generateQRCode(data, filePath, width, height);

            System.out.println("✓ QR Code generated successfully: " + outputPath);

        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
