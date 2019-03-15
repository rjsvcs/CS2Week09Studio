package tcpip;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        Socket sock = new Socket("localhost", 1337);
        System.out.println("Connected!");
        OutputStream outputStream = sock.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("Hello!");
        writer.flush();
        System.out.println("Sent! Waiting for response...");
        InputStream inputStream = sock.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        System.out.println("received: " + scanner.nextLine());
    }
}
