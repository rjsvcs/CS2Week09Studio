package chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ChatClient(Socket client) throws IOException {
        this.socket = client;
        InputStream input = client.getInputStream();
        InputStreamReader iReader = new InputStreamReader(input);
        reader = new BufferedReader(iReader);
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
        try {
            while (true) {
                String message = reader.readLine();
                System.out.println(message);
            }
        } catch(IOException exception) {
            System.err.println("Something went wrong!");
            exception.printStackTrace();
        }
    }
}
