import com.google.zxing.NotFoundException;
import factory.CodeReaderFactory;
import service.ICodeReader;

import java.io.IOException;

/**
 * QR Code Reader using ZXing library
 * This class now acts as a Facade for the new OOP architecture
 * Maintains backward compatibility with existing code
 */
public class QRCodeReader {

    /**
     * Read and decode QR Code from an image file
     * Now supports multiple formats including barcodes
     * 
     * @param filePath Path to the QR code/barcode image file
     * @return Decoded text from the code
     * @throws IOException       If file reading fails
     * @throws NotFoundException If no code is found
     */
    public static String readQRCode(String filePath) throws IOException, NotFoundException {
        // Use the new OOP architecture via factory pattern
        // Using multi-format reader to support both QR codes and barcodes
        ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
        return reader.readCode(filePath);
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        try {
            String filePath = "sample_qrcode.png";
            String decodedText = readQRCode(filePath);
            
            System.out.println("============================================================");
            System.out.println("QR Code Reader");
            System.out.println("============================================================");
            System.out.println("File: " + filePath);
            System.out.println("Decoded Text: " + decodedText);
            System.out.println("============================================================");
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (NotFoundException e) {
            System.err.println("No QR Code found in the image!");
        }
    }
}
