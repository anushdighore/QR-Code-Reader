package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for Code 39 barcode generation
 * Follows Single Responsibility Principle (SRP)
 */
public class Code39GeneratorImpl extends AbstractCodeGenerator {

    private static final String VALID_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";

    public Code39GeneratorImpl() {
        super(CodeFormat.CODE_39, BarcodeFormat.CODE_39);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        Code39Writer writer = new Code39Writer();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        // Code 39 supports specific characters only
        return data.chars().allMatch(c -> VALID_CHARS.indexOf(c) >= 0);
    }
}
