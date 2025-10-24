package service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import model.CodeFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Abstract base class for code readers
 * Follows the Template Method pattern
 */
public abstract class AbstractCodeReader implements ICodeReader {
    
    protected final CodeFormat format;

    protected AbstractCodeReader(CodeFormat format) {
        this.format = format;
    }

    @Override
    public String readCode(String filePath) throws IOException, NotFoundException {
        File file = new File(filePath);
        BufferedImage bufferedImage = ImageIO.read(file);
        
        if (bufferedImage == null) {
            throw new IOException("Unable to read image file");
        }

        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(bufferedImage)));

        Result result = decode(binaryBitmap);
        return result.getText();
    }

    @Override
    public CodeFormat getFormat() {
        return format;
    }

    /**
     * Template method for decoding
     * Subclasses can override for custom decoding logic
     */
    protected abstract Result decode(BinaryBitmap binaryBitmap) throws NotFoundException;
}
