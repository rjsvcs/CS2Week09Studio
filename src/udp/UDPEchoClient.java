package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPEchoClient {
    public static void main(String[] args) throws IOException {
        // create the byte[] to send
        String hello = "Hello, world!";
        byte[] data = hello.getBytes();

        // create the packet to transmit to localhost on port 1337
        DatagramPacket packet = new DatagramPacket(data, data.length,
                InetAddress.getByName("localhost"), 1337);

        // create the socket and send the packet
        DatagramSocket sock = new DatagramSocket(7331);
        sock.send(packet);

        // reuse the packet to receive the response
        sock.receive(packet);

        // get the buffer from the packet and the number of useful bytes
        byte[] received = packet.getData();
        int length = packet.getLength();

        // create a string an dprint the response
        String response = new String(data, 0, length);
        System.out.println(response);
    }
}
