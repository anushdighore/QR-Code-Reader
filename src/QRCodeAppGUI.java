import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import factory.CodeGeneratorFactory;
import factory.CodeReaderFactory;
import model.CodeFormat;
import service.ICodeGenerator;
import service.ICodeReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Graphical interface for the QR Code Generator and Reader application.
 */
public class QRCodeAppGUI extends JFrame {

    private final JTextArea inputDataArea = new JTextArea(5, 30);
    private final JComboBox<CodeFormat> formatComboBox = new JComboBox<>(CodeFormat.values());
    private final JTextField outputPathField = new JTextField("qrcode.png", 25);
    private final JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(300, 100, 1000, 50));
    private final JLabel generateStatusLabel = new JLabel(" ");
    private final JLabel previewLabel = new JLabel("No code generated yet", SwingConstants.CENTER);
    private final JButton openOutputButton = new JButton("Open Image");
    private Path lastGeneratedPath;

    private final JTextField inputFileField = new JTextField(25);
    private final JTextArea decodedTextArea = new JTextArea(6, 30);
    private final JLabel readStatusLabel = new JLabel(" ");
    private final JLabel detectedFormatLabel = new JLabel(" ");

    public QRCodeAppGUI() {
        super("QR Code & Barcode Studio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Add listener to update default filename when format changes
        formatComboBox.addActionListener(e -> updateDefaultFilename());

        
        previewLabel.setOpaque(true);
        previewLabel.setBackground(new Color(250, 250, 250));
        previewLabel.setBorder(BorderFactory.createDashedBorder(new Color(200, 200, 200), 3, 5));
        previewLabel.setPreferredSize(new Dimension(240, 240));
        previewLabel.setVerticalAlignment(SwingConstants.CENTER);
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        previewLabel.setFont(previewLabel.getFont().deriveFont(Font.ITALIC));

        openOutputButton.setEnabled(false);
        openOutputButton.addActionListener(e -> openGeneratedFile());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(0, 20, 20, 20));
        tabbedPane.addTab("Generate", createGeneratePanel());
        tabbedPane.addTab("Read", createReadPanel());

        JPanel root = new JPanel(new BorderLayout());
        root.add(createHeader(), BorderLayout.NORTH);
        root.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(root);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createGeneratePanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;

        JLabel intro = new JLabel("Select format and enter data to encode.");
        intro.setFont(intro.getFont().deriveFont(Font.BOLD, intro.getFont().getSize() + 1f));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 3;
        formPanel.add(intro, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = row;
        formPanel.add(new JLabel("Format"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        formatComboBox.setToolTipText("Select the code format to generate");
        formPanel.add(formatComboBox, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(new JLabel("Data"), gbc);

        inputDataArea.setLineWrap(true);
        inputDataArea.setWrapStyleWord(true);
        inputDataArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(6, 6, 6, 6)));
        JScrollPane inputScroll = new JScrollPane(inputDataArea);
        inputScroll.setPreferredSize(new Dimension(360, 150));
        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        formPanel.add(inputScroll, gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(new JLabel("Output file"), gbc);

        gbc.gridx = 1;
        outputPathField.setToolTipText("Enter the filename for the generated QR code (PNG)");
        formPanel.add(outputPathField, gbc);

        JButton browseOutputButton = new JButton("Browse…");
        browseOutputButton.addActionListener(e -> chooseOutputFile());
        gbc.gridx = 2;
        formPanel.add(browseOutputButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = ++row;
        formPanel.add(new JLabel("Size (px)"), gbc);

        gbc.gridx = 1;
        formPanel.add(sizeSpinner, gbc);

        JLabel sizeHint = new JLabel("(100 - 1000 pixels)");
        sizeHint.setFont(sizeHint.getFont().deriveFont(Font.PLAIN, 11f));
        sizeHint.setForeground(new Color(120, 120, 120));
        gbc.gridx = 2;
        formPanel.add(sizeHint, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setOpaque(false);
        JButton generateButton = new JButton("Generate QR Code");
        generateButton.addActionListener(e -> handleGenerate());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearGenerateForm());
        buttonPanel.add(generateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(openOutputButton);

        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        formPanel.add(buttonPanel, gbc);

        generateStatusLabel.setForeground(new Color(0, 102, 0));
        gbc.gridy = ++row;
        formPanel.add(generateStatusLabel, gbc);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(createPreviewPanel(), BorderLayout.EAST);

        return panel;
    }

    private JPanel createPreviewPanel() {
        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setOpaque(false);
        previewPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)),
                "Code Preview", TitledBorder.CENTER, TitledBorder.TOP));
        previewPanel.add(previewLabel, BorderLayout.CENTER);

        JLabel hint = new JLabel("Preview updates after generation", SwingConstants.CENTER);
        hint.setForeground(new Color(120, 120, 120));
        hint.setBorder(new EmptyBorder(8, 8, 12, 8));
        previewPanel.add(hint, BorderLayout.SOUTH);
        return previewPanel;
    }

    private JPanel createReadPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 0;

        JLabel intro = new JLabel("Select an image to decode QR Code or Barcode.");
        intro.setFont(intro.getFont().deriveFont(Font.BOLD, intro.getFont().getSize() + 1f));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 3;
        formPanel.add(intro, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = row;
        formPanel.add(new JLabel("Image file"), gbc);

        gbc.gridx = 1;
        inputFileField.setToolTipText("Choose an image file that contains a QR code");
        formPanel.add(inputFileField, gbc);

        JButton browseInputButton = new JButton("Browse…");
        browseInputButton.addActionListener(e -> chooseInputFile());
        gbc.gridx = 2;
        formPanel.add(browseInputButton, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setOpaque(false);
        JButton readButton = new JButton("Read Code");
        readButton.addActionListener(e -> handleRead());
        JButton copyButton = new JButton("Copy Text");
        copyButton.addActionListener(e -> copyDecodedText());
        buttonPanel.add(readButton);
        buttonPanel.add(copyButton);

        gbc.gridx = 0;
        gbc.gridy = ++row;
        gbc.gridwidth = 3;
        formPanel.add(buttonPanel, gbc);

        decodedTextArea.setEditable(false);
        decodedTextArea.setLineWrap(true);
        decodedTextArea.setWrapStyleWord(true);
        decodedTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(8, 8, 8, 8)));
        decodedTextArea.setBackground(new Color(252, 252, 252));
        JScrollPane resultScroll = new JScrollPane(decodedTextArea);
        resultScroll.setPreferredSize(new Dimension(500, 180));
        resultScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                "Decoded Content"));

        gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        formPanel.add(resultScroll, gbc);

        readStatusLabel.setForeground(new Color(0, 102, 0));
        gbc.gridy = ++row;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(readStatusLabel, gbc);

        detectedFormatLabel.setForeground(new Color(0, 102, 153));
        detectedFormatLabel.setFont(detectedFormatLabel.getFont().deriveFont(Font.ITALIC));
        gbc.gridy = ++row;
        formPanel.add(detectedFormatLabel, gbc);

        panel.add(formPanel, BorderLayout.CENTER);
        return panel;
    }

    private void handleGenerate() {
        generateStatusLabel.setText(" ");
        generateStatusLabel.setForeground(new Color(0, 102, 0));
        openOutputButton.setEnabled(false);
        lastGeneratedPath = null;
        String data = inputDataArea.getText().trim();

        if (data.isEmpty()) {
            showGenerateError("Data cannot be empty.");
            return;
        }

        CodeFormat selectedFormat = (CodeFormat) formatComboBox.getSelectedItem();
        if (selectedFormat == null) {
            selectedFormat = CodeFormat.QR_CODE;
        }

        String filename = outputPathField.getText().trim();
        if (filename.isEmpty()) {
            filename = selectedFormat.getDefaultFileName();
            outputPathField.setText(filename);
        }

        int size = (int) sizeSpinner.getValue();

        try {
            ICodeGenerator generator = CodeGeneratorFactory.createGenerator(selectedFormat);
            
            if (!generator.validateData(data)) {
                showGenerateError("Invalid data for " + selectedFormat.getDisplayName() + 
                                " format. Please check the format requirements.");
                return;
            }
            
            Path savedPath = generator.generateCode(data, filename, size, size);
            generateStatusLabel.setForeground(new Color(0, 102, 0));
            generateStatusLabel.setText("Saved to: " + savedPath);
            lastGeneratedPath = savedPath;
            updatePreview(savedPath);
            openOutputButton.setEnabled(canOpenGeneratedFile());
            JOptionPane.showMessageDialog(this,
                    selectedFormat.getDisplayName() + " generated successfully!\nSaved to: " + savedPath,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (WriterException | IOException e) {
            showGenerateError("Failed to generate " + selectedFormat.getDisplayName() + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            showGenerateError(e.getMessage());
        }
    }

    private void handleRead() {
        readStatusLabel.setText(" ");
        readStatusLabel.setForeground(new Color(0, 102, 0));
        detectedFormatLabel.setText(" ");
        decodedTextArea.setText("");

        String filePath = inputFileField.getText().trim();
        if (filePath.isEmpty()) {
            showReadError("Please select an image file.");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            showReadError("File does not exist: " + filePath);
            return;
        }

        try {
            ICodeReader reader = CodeReaderFactory.createMultiFormatReader();
            String decoded = reader.readCode(filePath);
            
            // Try to detect the format by reading the result metadata
            String formatInfo = detectFormatFromFile(filePath);
            
            decodedTextArea.setText(decoded);
            readStatusLabel.setForeground(new Color(0, 102, 0));
            readStatusLabel.setText("✓ Code decoded successfully (" + decoded.length() + " characters)");
            
            if (formatInfo != null && !formatInfo.isEmpty()) {
                detectedFormatLabel.setText("Detected format: " + formatInfo);
            }
        } catch (IOException e) {
            showReadError("Unable to read file: " + e.getMessage());
        } catch (NotFoundException e) {
            showReadError("No QR code or barcode found in the selected image.");
        }
    }

    private void copyDecodedText() {
        String text = decodedTextArea.getText();
        if (text == null || text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No decoded text to copy.",
                    "Copy Text",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(new StringSelection(text), null);
            readStatusLabel.setText("✓ Text copied to clipboard!");
            readStatusLabel.setForeground(new Color(0, 102, 0));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to copy text: " + e.getMessage(),
                    "Copy Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void chooseOutputFile() {
        JFileChooser chooser = createFileChooser("Save QR Code", JFileChooser.SAVE_DIALOG);
        chooser.setSelectedFile(new File(outputPathField.getText().trim()));
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = ensurePngExtension(chooser.getSelectedFile());
            outputPathField.setText(selected.getAbsolutePath());
        }
    }

    private void chooseInputFile() {
        JFileChooser chooser = createFileChooser("Select QR Code Image", JFileChooser.OPEN_DIALOG);
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            inputFileField.setText(selected.getAbsolutePath());
        }
    }

    private JFileChooser createFileChooser(String title, int dialogType) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        chooser.setDialogType(dialogType);
        chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        return chooser;
    }

    private File ensurePngExtension(File file) {
        if (!file.getName().toLowerCase().endsWith(".png")) {
            return new File(file.getParentFile(), file.getName() + ".png");
        }
        return file;
    }

    private void clearGenerateForm() {
        inputDataArea.setText("");
        outputPathField.setText("qrcode.png");
        sizeSpinner.setValue(300);
        generateStatusLabel.setText(" ");
        lastGeneratedPath = null;
        openOutputButton.setEnabled(false);
        clearPreview();
    }

    private void showGenerateError(String message) {
        generateStatusLabel.setForeground(new Color(150, 0, 0));
        generateStatusLabel.setText(message);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        lastGeneratedPath = null;
        openOutputButton.setEnabled(false);
        clearPreview();
    }

    private void showReadError(String message) {
        readStatusLabel.setForeground(new Color(150, 0, 0));
        readStatusLabel.setText(message);
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(18, 24, 12, 24));
        header.setOpaque(false);

        JLabel title = new JLabel("QR Code & Barcode Studio");
        title.setFont(title.getFont().deriveFont(Font.BOLD, title.getFont().getSize() + 6f));
        header.add(title, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Generate and decode QR codes and barcodes in one place");
        subtitle.setForeground(new Color(110, 110, 110));
        header.add(subtitle, BorderLayout.SOUTH);

        return header;
    }

    private void updatePreview(Path imagePath) {
        if (imagePath == null || !Files.exists(imagePath)) {
            clearPreview();
            return;
        }
        try {
            BufferedImage image = ImageIO.read(imagePath.toFile());
            if (image == null) {
                clearPreview();
                return;
            }
            int target = 240;
            int size = Math.max(image.getWidth(), image.getHeight());
            double scale = size > target ? (double) target / size : 1.0;
            int w = (int) Math.round(image.getWidth() * scale);
            int h = (int) Math.round(image.getHeight() * scale);
            Image scaled = image.getScaledInstance(Math.max(w, 60), Math.max(h, 60), Image.SCALE_SMOOTH);
            previewLabel.setIcon(new ImageIcon(scaled));
            previewLabel.setText(null);
        } catch (IOException e) {
            previewLabel.setIcon(null);
            previewLabel.setText("Preview unavailable");
        }
    }

    private void clearPreview() {
        previewLabel.setIcon(null);
        previewLabel.setText("No code generated yet");
    }
    
    /**
     * Update the default filename based on selected format
     */
    private void updateDefaultFilename() {
        CodeFormat selectedFormat = (CodeFormat) formatComboBox.getSelectedItem();
        if (selectedFormat != null) {
            String currentPath = outputPathField.getText().trim();
            // Only update if it's still the default or empty
            if (currentPath.isEmpty() || currentPath.matches(".*\\.(png)$")) {
                outputPathField.setText(selectedFormat.getDefaultFileName());
            }
        }
    }
    
    /**
     * Detect the barcode format from the image file
     */
    private String detectFormatFromFile(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(file);
            
            if (bufferedImage == null) {
                return null;
            }

            com.google.zxing.BinaryBitmap binaryBitmap = new com.google.zxing.BinaryBitmap(
                    new com.google.zxing.common.HybridBinarizer(
                            new com.google.zxing.client.j2se.BufferedImageLuminanceSource(bufferedImage)));

            com.google.zxing.MultiFormatReader reader = new com.google.zxing.MultiFormatReader();
            com.google.zxing.Result result = reader.decode(binaryBitmap);
            
            return result.getBarcodeFormat().toString();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean canOpenGeneratedFile() {
        return lastGeneratedPath != null
                && Files.exists(lastGeneratedPath)
                && Desktop.isDesktopSupported()
                && Desktop.getDesktop().isSupported(Desktop.Action.OPEN);
    }

    private void openGeneratedFile() {
        if (!canOpenGeneratedFile()) {
            JOptionPane.showMessageDialog(this,
                    "File cannot be opened on this system.",
                    "Open File",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Desktop.getDesktop().open(lastGeneratedPath.toFile());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to open file: " + e.getMessage(),
                    "Open File",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // Fall back to default if Nimbus unavailable
        }
        
        SwingUtilities.invokeLater(() -> {
            QRCodeAppGUI app = new QRCodeAppGUI();
            app.setVisible(true);
        });
    }
}
