import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class P5HttpHandler implements HttpHandler {

    public P5HttpHandler() {
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {

        InputStream is = ex.getRequestBody();
        while (is.read() != -1) {
            is.skip(0x1000);
        }
        is.close();

        Headers h =ex.getResponseHeaders();
        h.set("content-type", "text/html");

        OutputStream os = ex.getResponseBody();
        FileInputStream fs = new FileInputStream("src/main/public/index.html");
        ex.sendResponseHeaders(200, 0);
        final byte[] buffer = new byte[0x1000];
        int count;
        while ((count = fs.read(buffer)) >= 0) {
            os.write(buffer, 0, count);
        }
        fs.close();
        os.close();
    }

}
