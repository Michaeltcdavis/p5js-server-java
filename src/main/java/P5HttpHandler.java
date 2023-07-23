import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


public class P5HttpHandler implements HttpHandler {

    public P5HttpHandler() {
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {
        String response = "Hello World";
        ex.sendResponseHeaders(200, response.length());
        OutputStream os = ex.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
