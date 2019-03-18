package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements Runnable {
    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;

    public ChatClient(Socket client) throws IOException {
        this.socket = client;
        scanner = new Scanner(client.getInputStream());
        writer = new PrintWriter(client.getOutputStream());
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1138);
        ChatClient client = new ChatClient(socket);
        new Thread(client).start();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String message = scanner.nextLine();
            client.writer.println(message);
            client.writer.flush();
        }

    }

    @Override
    public void run() {
        while(true) {
            String message = scanner.nextLine();
            System.out.println(message);
        }
    }
}
