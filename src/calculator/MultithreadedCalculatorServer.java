package calculator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculator server implementation that supports multiple concurrent clients.
 */
public class MultithreadedCalculatorServer {
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
     * The server loop. Waits for a client to connect, creates a handler
     * thread for that client, and then goes back to waiting for the next
     * client.
     *
     * @param args Ignored.
     * @throws IOException If there are any problems opening a socket.
     */
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator(OPERATIONS);
        ServerSocket server = new ServerSocket(8010);

        while(true) {
            Socket client = server.accept();
            ClientHandlerThread handler =
                    new ClientHandlerThread(client, calculator);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }
}
