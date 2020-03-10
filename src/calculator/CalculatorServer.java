package calculator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provides a simple, single-threaded implementation of a calculator server.
 * Allows one client to connect and perform a single operation.
 */
public class CalculatorServer {
    /**
     * The supported calculator operations.
     */
    private static final List<BinaryOperation> OPERATIONS = new ArrayList<>(7);
    static {
        OPERATIONS.add(new Addition());
        OPERATIONS.add(new Subtraction());
        OPERATIONS.add(new Multiplication());
        OPERATIONS.add(new Division());
        OPERATIONS.add(new FloorDivision());
        OPERATIONS.add(new Exponent());
    }

    /**
     * The main server code. Allows one client to connect and send a single
     * request.
     *
     * @param args Ignored.
     * @throws IOException If there are any problems communicating with the
     * client.
     */
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator(OPERATIONS);
        ServerSocket server = new ServerSocket(8009);

        Socket client = server.accept();
        InputStream input = client.getInputStream();
        InputStreamReader iReader = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(iReader);
        PrintWriter writer = new PrintWriter(client.getOutputStream());
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
    }
}
