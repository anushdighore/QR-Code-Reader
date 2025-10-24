package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.UPCAWriter;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for UPC-A barcode generation
 * Follows Single Responsibility Principle (SRP)
 */
public class UPCAGeneratorImpl extends AbstractCodeGenerator {

    public UPCAGeneratorImpl() {
        super(CodeFormat.UPC_A, BarcodeFormat.UPC_A);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        UPCAWriter writer = new UPCAWriter();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }
        // UPC-A requires exactly 11 or 12 digits
        String trimmed = data.trim();
        return (trimmed.length() == 11 || trimmed.length() == 12) && 
               trimmed.chars().allMatch(Character::isDigit);
    }
}
