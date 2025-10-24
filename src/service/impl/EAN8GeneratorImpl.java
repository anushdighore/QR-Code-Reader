package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN8Writer;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for EAN-8 barcode generation
 * Follows Single Responsibility Principle (SRP)
 */
public class EAN8GeneratorImpl extends AbstractCodeGenerator {

    public EAN8GeneratorImpl() {
        super(CodeFormat.EAN_8, BarcodeFormat.EAN_8);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        EAN8Writer writer = new EAN8Writer();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        // EAN-8 requires exactly 7 or 8 digits
        String trimmed = data.trim();
        return (trimmed.length() == 7 || trimmed.length() == 8) && 
               trimmed.chars().allMatch(Character::isDigit);
    }
}
