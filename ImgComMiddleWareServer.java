import java.io.*;
import java.net.*;

public class ImgComMiddleWareServer {
    public static void main(String[] args) {
        int port = 5500;

        try {

            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started");
            Socket client = server.accept();

            InputStream cliInpStream = client.getInputStream();

            int count = 1;

            FileOutputStream imgOP = new FileOutputStream("./RecievedImage/" + count++ + ".jpg");

            byte[] buffer = new byte[4096];
            int bytesRead = 0;

            while((bytesRead = cliInpStream.read(buffer)) != -1) {
                imgOP.write(buffer, 0, bytesRead);
            }

            imgOP.close();
            cliInpStream.close();
            System.out.println("Images saved at ./RecievedImage/" + (count - 1) + ".jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}