import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1337);
        System.out.println("Waiting for client...");
        Socket client = server.accept();
        System.out.println("Connected!");
        // receive
        InputStream inputStream = client.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        String line = scanner.nextLine();
        System.out.println("received: " + line);
        // echo back
        OutputStream outputStream = client.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(line);
        writer.flush();
    }
}
