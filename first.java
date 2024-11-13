import java.net.*;
import java.io.*;

public class first{

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    public first(int port){
        // start server and wait for connection of client/s
        try{
            server = new ServerSocket(port);
            System.err.println("Server Started");

            socket = server.accept();
            System.out.println("Client Connected");

            //need to take data from client
            //blank for data input in respected form
            /*in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));*/
            
            // blank for checking all data transferred
            
        }
    }
    public static void main(String[] args) {

    }
}