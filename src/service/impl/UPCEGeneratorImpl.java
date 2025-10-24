package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.UPCEWriter;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for UPC-E barcode generation
 * Follows Single Responsibility Principle (SRP)
 */
public class UPCEGeneratorImpl extends AbstractCodeGenerator {

    public UPCEGeneratorImpl() {
        super(CodeFormat.UPC_E, BarcodeFormat.UPC_E);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        UPCEWriter writer = new UPCEWriter();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        // UPC-E requires exactly 6, 7, or 8 digits
        String trimmed = data.trim();
        return (trimmed.length() >= 6 && trimmed.length() <= 8) && 
               trimmed.chars().allMatch(Character::isDigit);
    }
}
