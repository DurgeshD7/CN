import java.io.*;
import java.net.*;
public class jyclient {
    public static void main(String[] args) {
        try {
            Socket cs = new Socket("localhost", 1900);
            System.out.println("Connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            String serverMessage = in.readLine(); 
            System.out.println("Server response: " + serverMessage);
            in.close(); 
            cs.close(); 
        } 
        catch (IOException e) { 
            e.printStackTrace();
        }
    }
}
