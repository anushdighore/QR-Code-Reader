package service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import model.CodeFormat;
import service.AbstractCodeGenerator;

/**
 * Concrete implementation for QR Code generation
 * Follows Single Responsibility Principle (SRP)
 */
public class QRCodeGeneratorImpl extends AbstractCodeGenerator {

    public QRCodeGeneratorImpl() {
        super(CodeFormat.QR_CODE, BarcodeFormat.QR_CODE);
    }

    @Override
    protected BitMatrix encode(String data, int width, int height) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        return writer.encode(data, barcodeFormat, width, height);
    }

    @Override
    public boolean validateData(String data) {
        // QR codes can encode any text, but should not be empty
        return data != null && !data.trim().isEmpty();
    }
}
