package calculator;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandlerThread implements Runnable {
    private final Socket client;
    private final Calculator calculator;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public ClientHandlerThread(Socket client, Calculator calculator)
            throws IOException {
        this.client = client;
        this.calculator = calculator;

        InputStream input = client.getInputStream();
        InputStreamReader iReader = new InputStreamReader(input);
        reader = new BufferedReader(iReader);
        writer = new PrintWriter(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            String request = reader.readLine();
            String[] tokens = request.split(" ");
            if (tokens.length != 3) {
                writer.println("error bad request");
            } else {
                try {
                    float operand1 = Float.parseFloat(tokens[1]);
                    float operand2 = Float.parseFloat(tokens[2]);
                    writer.println("result " + calculator.calculate(tokens[0],
                            operand1, operand2));
                } catch (Exception iae) {
                    writer.println("error " + iae.getLocalizedMessage());
                }
            }
            writer.flush();
            client.close();
        } catch (IOException e) {
            // squash
        }
    }
}
