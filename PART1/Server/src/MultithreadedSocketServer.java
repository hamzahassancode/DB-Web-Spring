import Repository.SecureConnection;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class MultithreadedSocketServer {
    public static void main(String[] args) throws Exception {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            SecureConnection secure=new SecureConnection();
            // Connect to a database
            Connection connection = DriverManager.getConnection(secure.getUrl(), secure.getUser(), secure.getPass());
            System.out.println("Database connected");

            ServerSocket server=new ServerSocket(8888);
            int counter=0;
            System.out.println("Server started ....");

            while(true){
                counter++;
                //server accept the client connection request
                Socket serverClient=server.accept();
                System.out.println(" >> " + "Client No:" + counter + " started!");
                //send the request to a separate thread
                ServerClientThread sct = new ServerClientThread(serverClient,counter,connection);
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
