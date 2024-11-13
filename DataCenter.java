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

