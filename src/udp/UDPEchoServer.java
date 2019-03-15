package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1337);

        byte[] buffer = new byte[10240];
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        socket.receive(incoming);

        String request = new String(buffer, 0, incoming.getLength());
        System.out.println(request);

        byte[] toSend = request.getBytes();
        DatagramPacket outgoing = new DatagramPacket(toSend, toSend.length);
        outgoing.setAddress(InetAddress.getByName("localhost"));
        outgoing.setPort(7331);
        socket.send(outgoing);
    }
}
