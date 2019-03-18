package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1138);
        List<ChatClientHandler> handlers = new ArrayList<>();

        while(true) {
            Socket client = server.accept();
            ChatClientHandler handler = new ChatClientHandler(client, handlers);
            handlers.add(handler);
            new Thread(handler).start();
        }
    }
}
