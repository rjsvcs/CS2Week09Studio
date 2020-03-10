package tcpip;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(13377);
        System.out.println("Waiting for client...");
        Socket client = server.accept();
        System.out.println("Connected!");
        // receive
        InputStream inputStream = client.getInputStream();
        InputStreamReader iReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(iReader);
        String line = reader.readLine();
        System.out.println("received: " + line);
        // echo back
        OutputStream outputStream = client.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(line);
        writer.flush();
    }
}
