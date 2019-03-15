package basics;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GooglePrinter {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.google.com");
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        while(scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
}
