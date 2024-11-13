import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DataCenter {
    


// method receive image 
// then method to store image recieved as filepath in mysql. and access it when request is recieved assume image


    public void createpath() {
        try {
            // Step 1: Load an existing image. here we further have to load from middleware in form of bytes or maybe just get this code to run after recv imag code
            BufferedImage image = ImageIO.read(new File("C:/MSD/college submission/sem5/Distributed Systems Lab/RipCloud/TestingImages/Screenshot 2024-10-15 144433.png"));

            // Step 2: Define the new file path and save the image
            long timestampMs = System.currentTimeMillis(); // this var timestampMs stores current time in ms since epoch 
            String timestampMsStr = String.valueOf(timestampMs); // this var timestampMsStr stores the string value of timestampMs  
            File outputFile = new File("C:/Users/admin/Desktop/DataCen_Mahim/" + timestampMsStr + ".png"); //C:/Users/admin/Desktop/DataCen_Mahim/timestampMs.png
            ImageIO.write(image, "png", outputFile);  // Save the image as PNG

            // Step 3: Get and print the new file path
            String newFilePath = outputFile.getAbsolutePath();
            System.out.println("Image saved at: " + newFilePath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());}
    }
    public static void main(String[] args) {
        DataCenter dc = new DataCenter();
        dc.createpath();
    }
}

/*;
import java.net.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCenterServer {
    private static final int PORT = 5600;
    private static final String SAVE_DIRECTORY = "./DataCenterImages/";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Data Center Server started, waiting for middleware...");

            // Accept connection from Middleware
            Socket middlewareSocket = serverSocket.accept();
            System.out.println("Middleware connected.");

            // Receive the username from Middleware
            BufferedReader reader = new BufferedReader(new InputStreamReader(middlewareSocket.getInputStream()));
            String username = reader.readLine();
            System.out.println("Username received from middleware: " + username);

            // Generate a timestamp-based filename with username
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = SAVE_DIRECTORY + username + "_" + timestamp + ".jpg";

            // Ensure the directory exists
            Files.createDirectories(Paths.get(SAVE_DIRECTORY));

            // Receive the image file from Middleware and save it
            InputStream middlewareInputStream = middlewareSocket.getInputStream();
            try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = middlewareInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("Image saved as " + filename);
            }

            // Close connections
            reader.close();
            middlewareInputStream.close();
            middlewareSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/