package tcpip;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        Socket sock = new Socket("localhost", 13377);
        System.out.println("Connected!");
        OutputStream outputStream = sock.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("Hello!");
        writer.flush();
        System.out.println("Sent! Waiting for response...");
        InputStream input = sock.getInputStream();
        InputStreamReader iReader = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(iReader);
        System.out.println("received: " + reader.readLine());
    }
}
