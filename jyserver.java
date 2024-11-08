import java.io.*;
import java.net.*;
public class jyserver {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1900);
            System.out.println("Server is listening on port 1900");
            Socket cs = ss.accept(); 
            System.out.println("Client is connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
            String clientMessage = in.readLine(); 
            System.out.println("Client Says: " + clientMessage); 
            System.out.println("Server received: " + clientMessage); 
            in.close();
            out.close();
            cs.close();
            ss.close();
        } 
        catch (IOException e) { 
            e.printStackTrace();
        }
    }
}
