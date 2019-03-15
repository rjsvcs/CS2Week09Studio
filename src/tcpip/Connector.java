package tcpip;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("bobby.rit.edu", 33075);
        System.out.println("Connected!");
    }
}
