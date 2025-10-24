package model;

/**
 * Enum representing supported code formats
 * Follows the Type-Safe Enum pattern
 */
public enum CodeFormat {
    QR_CODE("QR Code", "qrcode.png"),
    CODE_128("Code 128", "barcode_128.png"),
    CODE_39("Code 39", "barcode_39.png"),
    EAN_13("EAN-13", "barcode_ean13.png"),
    EAN_8("EAN-8", "barcode_ean8.png"),
    UPC_A("UPC-A", "barcode_upca.png"),
    UPC_E("UPC-E", "barcode_upce.png");

    private final String displayName;
    private final String defaultFileName;

    CodeFormat(String displayName, String defaultFileName) {
        this.displayName = displayName;
        this.defaultFileName = defaultFileName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDefaultFileName() {
        return defaultFileName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
