package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for EAN-13 barcode generation
 * Follows Single Responsibility Principle (SRP)
 */
public class EAN13GeneratorImpl extends AbstractCodeGenerator {

    public EAN13GeneratorImpl() {
        super(CodeFormat.EAN_13, BarcodeFormat.EAN_13);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        EAN13Writer writer = new EAN13Writer();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        // EAN-13 requires exactly 12 or 13 digits
        String trimmed = data.trim();
        return (trimmed.length() == 12 || trimmed.length() == 13) && 
               trimmed.chars().allMatch(Character::isDigit);
    }
}
