package service.impl;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import service.AbstractCodeReader;

/**
 * Concrete implementation for multi-format code reading
 * Can read QR codes and various barcode formats
 * Follows Single Responsibility Principle (SRP)
 */
public class MultiFormatReaderImpl extends AbstractCodeReader {

    public MultiFormatReaderImpl() {
        super(null); // null indicates multi-format support
    }

    @Override
    protected Result decode(BinaryBitmap binaryBitmap) throws NotFoundException {
        MultiFormatReader reader = new MultiFormatReader();
        return reader.decode(binaryBitmap);
    }
}
