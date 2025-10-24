package service.impl;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.qrcode.QRCodeReader;
import model.CodeFormat;
import service.AbstractCodeReader;

/**
 * Concrete implementation for QR Code reading
 * Follows Single Responsibility Principle (SRP)
 */
public class QRCodeReaderImpl extends AbstractCodeReader {

    public QRCodeReaderImpl() {
        super(CodeFormat.QR_CODE);
    }

    @Override
    protected Result decode(BinaryBitmap binaryBitmap) throws NotFoundException {
        QRCodeReader reader = new QRCodeReader();
        try {
            return reader.decode(binaryBitmap);
        } catch (ChecksumException | FormatException e) {
            // Convert to NotFoundException for consistent error handling
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
