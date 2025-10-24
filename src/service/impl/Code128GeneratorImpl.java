package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for Code 128 barcode generation
 * Follows Single Responsibility Principle (SRP)
 */
public class Code128GeneratorImpl extends AbstractCodeGenerator {

    public Code128GeneratorImpl() {
        super(CodeFormat.CODE_128, BarcodeFormat.CODE_128);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        Code128Writer writer = new Code128Writer();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        // Code 128 can encode ASCII characters
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        // Check if all characters are valid ASCII
        return data.chars().allMatch(c -> c >= 0 && c <= 127);
    }
}
