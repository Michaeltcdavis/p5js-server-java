import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
    private static int _PORT = 9991;

    public SimpleServer() {
    }

    public SimpleServer(int port) {
        _PORT = port;
    }

    public static void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(_PORT)) {

            Socket connection = serverSocket.accept();

            InputStream is = connection.getInputStream();
            Scanner scanner = new Scanner(is, "UTF-8");

            OutputStream os = connection.getOutputStream();
            PrintWriter serverOut = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);

            serverOut.println("Hello World");

            boolean done = false;

            while (!done && scanner.hasNextLine()) {
                String Line = scanner.nextLine();
                serverOut.println("message received: " + Line);

                if (Line.toLowerCase().equals("exit"))
                    done = true;
            }

        } catch (Exception e) {
            throw e;
        }
    }
}
