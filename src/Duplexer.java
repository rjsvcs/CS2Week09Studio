import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * A helper class that wraps a {@link Socket} and provides convenience methods
 * for sending and receiving data as strings. Assumes that strings are
 * terminated with a newline.
 */
public class Duplexer implements AutoCloseable {
    /**
     * The {@link Socket} used to send and receive data.
     */
    private final Socket sock;

    /**
     * The {@link Scanner} used to receive data.
     */
    private final Scanner scanner;

    /**
     * The {@link PrintWriter} used to send data.
     */
    private final PrintWriter writer;

    /**
     * Create a new {@link Duplexer} to wrap the specified {@link Socket}.
     *
     * @param sock The {@link Socket} used to send and receive data.
     * @throws IOException If there is a problem creating the new Duplexer.
     */
    public Duplexer(Socket sock) throws IOException {
        this.sock = sock;
        scanner = new Scanner(sock.getInputStream());
        writer = new PrintWriter(sock.getOutputStream());
    }

    /**
     * Sends the specified string of data. This method assumes that the data
     * must be terminated with a newline.
     *
     * @param data The data to send; a newline will be added.
     */
    public void send(String data) {
        writer.println(data);
        writer.flush();
    }

    /**
     * Returns the next line of text data received.
     *
     * @return The next line of text data.
     */
    public String receive() {
        return scanner.nextLine();
    }

    /**
     * Attempts to close the wrapped {@link Socket}.
     *
     * @throws IOException If there is an error closing the socket.
     */
    @Override
    public void close() throws IOException {
        scanner.close();
        writer.close();
        sock.close();
    }
}
