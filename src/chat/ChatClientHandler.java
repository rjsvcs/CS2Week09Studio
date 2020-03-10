package chat;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatClientHandler implements Runnable {

    private Socket client;
    private List<ChatClientHandler> handlers;
    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClientHandler(Socket client, List<ChatClientHandler> handlers) throws IOException {
        this.client = client;
        this.handlers = handlers;
        InputStream input = client.getInputStream();
        InputStreamReader iReader = new InputStreamReader(input);
        reader = new BufferedReader(iReader);
        writer = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = reader.readLine();
                for (ChatClientHandler handler : handlers) {
                    handler.sendMessage(message);
                }
            }
        } catch(IOException ioe) {
            System.err.println("Something went wrong!");
            ioe.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println("<< " + message);
        writer.flush();
    }
}
