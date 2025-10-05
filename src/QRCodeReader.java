import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * QR Code Reader using ZXing library
 */
public class QRCodeReader {

    /**
     * Read and decode QR Code from an image file
     * 
     * @param filePath Path to the QR code image file
     * @return Decoded text from the QR code
     * @throws IOException
     * @throws NotFoundException
     */
    public static String readQRCode(String filePath) throws IOException, NotFoundException {
        
        File file = new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(file);

        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(bufferedImage)));

        MultiFormatReader reader = new MultiFormatReader();
        Result result = reader.decode(binaryBitmap);

        return result.getText();
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
