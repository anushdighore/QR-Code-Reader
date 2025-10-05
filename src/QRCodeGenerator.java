import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * QR Code Generator using ZXing library
 */
public class QRCodeGenerator {

    /**
     * Generate QR Code and save it as an image file
     * 
     * @param data     The text/data to encode in QR code
     * @param filePath The output file path (e.g., "qrcode.png")
     * @param width    Width of the QR code image
     * @param height   Height of the QR code image
     * @throws WriterException
     * @throws IOException
     */
    public static Path generateQRCode(String data, String filePath, int width, int height)
            throws WriterException, IOException {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        
        return path.toAbsolutePath();
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
