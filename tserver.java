import java.io.*;
import java.net.*;
public class tserver {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080); // Listen on port 100
            System.out.println("Server is listening for incoming connections...");
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept incoming client connections
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                // Create streams for reading and writing data
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter writer = new PrintWriter(outputStream, true);
                // Receive the file name from the client
                String fileName = reader.readLine();
                System.out.println("Received file name: " + fileName);
                // Specify where to save the received file
                String savePath = "server_received_" + fileName;
                try (FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("File received and saved as: " + savePath);
                }
                writer.println("File received successfully.");
                System.out.println("Client disconnected.");
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
