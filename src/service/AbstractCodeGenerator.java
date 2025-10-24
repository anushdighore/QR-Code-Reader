package service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import model.CodeFormat;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Abstract base class for code generators
 * Follows the Template Method pattern and Open/Closed Principle
 */
public abstract class AbstractCodeGenerator implements ICodeGenerator {
    
    protected final CodeFormat format;
    protected final BarcodeFormat barcodeFormat;

    protected AbstractCodeGenerator(CodeFormat format, BarcodeFormat barcodeFormat) {
        this.format = format;
        this.barcodeFormat = barcodeFormat;
    }

    @Override
    public Path generateCode(String data, String filePath, int width, int height) 
            throws WriterException, IOException {
        
        if (!validateData(data)) {
            throw new IllegalArgumentException("Invalid data for " + format.getDisplayName());
        }
        
        BitMatrix bitMatrix = encode(data, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        
        return path.toAbsolutePath();
    }

    @Override
    public CodeFormat getFormat() {
        return format;
    }

    /**
     * Template method for encoding data
     * Subclasses can override for custom encoding logic
     */
    protected abstract BitMatrix encode(String data, int width, int height) 
            throws WriterException;
}
