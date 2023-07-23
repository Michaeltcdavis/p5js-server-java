import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        int PORT = 9992;

        InetSocketAddress host = new InetSocketAddress("localhost", PORT);

        HttpServer server = HttpServer.create(host, 0);

        server.createContext("/", new P5HttpHandler());

        try {
            server.start();
            System.out.println("server is running on port: " + PORT);
        } catch (Exception e) {
            System.err.println("server failed to start");
            e.printStackTrace();
        }
    }
}
