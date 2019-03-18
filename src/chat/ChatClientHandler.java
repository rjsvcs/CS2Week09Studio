package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatClientHandler implements Runnable {

    private Socket client;
    private List<ChatClientHandler> handlers;
    private Scanner scanner;
    private PrintWriter writer;

    public ChatClientHandler(Socket client, List<ChatClientHandler> handlers) throws IOException {
        this.client = client;
        this.handlers = handlers;
        scanner = new Scanner(client.getInputStream());
        writer = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run() {
        while(true) {
            String message = scanner.nextLine();
            for(ChatClientHandler handler : handlers) {
                handler.sendMessage(message);
            }
        }
    }

    public void sendMessage(String message) {
        writer.println("<< " + message);
        writer.flush();
    }
}
