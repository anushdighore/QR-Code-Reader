import java.nio.file.Path;
import java.util.Scanner;

/**
 * Main application for QR Code Generator and Reader
 */
public class QRCodeApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            displayMenu();
            System.out.print("\nEnter your choice (1-3): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    generateQRCode(scanner);
                    break;
                    
                case "2":
                    readQRCode(scanner);
                    break;
                    
                case "3":
                    System.out.println("\nThank you for using QR Code Generator/Reader!");
                    System.out.println("Goodbye! 👋");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("\n❌ Invalid choice! Please enter 1, 2, or 3.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n============================================================");
        System.out.println("  QR CODE GENERATOR/READER");
        System.out.println("============================================================");
        System.out.println("\n1. Generate QR Code");
        System.out.println("2. Read QR Code from Image");
        System.out.println("3. Exit");
        System.out.println("\n============================================================");
    }
    
    private static void generateQRCode(Scanner scanner) {
        System.out.println("\n--- QR Code Generator ---");
        
        System.out.print("Enter text/URL to encode: ");
        String data = scanner.nextLine().trim();
        
        if (data.isEmpty()) {
            System.out.println("Error: Data cannot be empty!");
            return;
        }
        
        System.out.print("Enter output filename (default: qrcode.png): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = "qrcode.png";
        } else if (!filename.endsWith(".png")) {
            filename += ".png";
        }
        
        System.out.print("Enter size (default: 300): ");
        String sizeStr = scanner.nextLine().trim();
        int size = sizeStr.isEmpty() ? 300 : Integer.parseInt(sizeStr);
        
        try {
            Path outputPath = QRCodeGenerator.generateQRCode(data, filename, size, size);
            System.out.println("Success! QR Code saved to: " + outputPath);
        } catch (Exception e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
        }
    }
    
    private static void readQRCode(Scanner scanner) {
        System.out.println("\n--- QR Code Reader ---");
        
        System.out.print("Enter image file path: ");
        String filePath = scanner.nextLine().trim();
        
        if (filePath.isEmpty()) {
            System.out.println("Error: File path cannot be empty!");
            return;
        }
        
        try {
            String decodedText = QRCodeReader.readQRCode(filePath);
            
            System.out.println("\n============================================================");
            System.out.println("QR Code Found!");
            System.out.println("============================================================");
            System.out.println("File: " + filePath);
            System.out.println("Decoded Text: " + decodedText);
            System.out.println("============================================================");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
