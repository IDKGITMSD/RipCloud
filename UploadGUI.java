import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class UploadGUI extends JFrame {
    private JLabel imageLabel;
    private JButton chooseFileButton;
    private JButton uploadButton;
    private File selectedFile;
    // Make that all the operations are logged in OutputLog.txt

    public UploadGUI() {
        setTitle("Image Uploader");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Image display panel
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(imageLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        chooseFileButton = new JButton("Choose File");
        uploadButton = new JButton("Upload");

        buttonPanel.add(chooseFileButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(uploadButton);
        add(buttonPanel, BorderLayout.EAST);

        // Choose File button action
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(UploadGUI.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile);
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = imageIcon.getImage().getScaledInstance(250, 300, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(image));
                }
            }
        });

        // Upload button action
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    uploadImage(selectedFile);
                } else {
                    JOptionPane.showMessageDialog(UploadGUI.this, "Please choose an image first.");
                }
            }
        });
    }

    private void uploadImage(File file) {
        try (Socket socket = new Socket("localhost", 5500);
             FileInputStream fileInputStream = new FileInputStream(file);
             OutputStream outputStream = socket.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            JOptionPane.showMessageDialog(this, "Image uploaded successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error uploading image.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UploadGUI client = new UploadGUI();
            client.setVisible(true);
        });
    }
}
