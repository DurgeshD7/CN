import java.io.*;
import java.net.*;
public class tclient{
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 8080); // Connect to server on localhost and port 100
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Provide the path to the file you want to send
            System.out.print("Enter the file path to send: ");
            String filePath = reader.readLine();
            File fileToSend = new File(filePath);     
            if (!fileToSend.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }     
            // Send the file name to the server
            writer.println(fileToSend.getName());    
            try (FileInputStream fileInputStream = new FileInputStream(fileToSend)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }  
            // Wait for the server response
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String serverResponse = serverReader.readLine();
            System.out.println("Server response: " + serverResponse); 
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
