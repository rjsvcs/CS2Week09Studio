package tcpip;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class Listener {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12347);
        Socket client = server.accept();
        System.out.println("Connected!");
    }
}
