import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;


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

        String root = new File("src/main/public").getCanonicalPath();
        URI uri = ex.getRequestURI();
        File file = new File(root + uri.getPath()).getCanonicalFile();

        OutputStream os = ex.getResponseBody();
        if (!file.getPath().startsWith(root)) {
            // Suspected path traversal attack: reject with 403 error.
            String response = "403 (Forbidden)\n";
            ex.sendResponseHeaders(403, response.length());
            os.write(response.getBytes());
        } else if (!file.isFile()) {
            // Object does not exist or is not a file: reject with 404 error.
            String response = "404 (Not Found)\n";
            ex.sendResponseHeaders(404, response.length());
            os.write(response.getBytes());
        } else {
            // Object exists and is a file: accept with response code 200.
            ex.sendResponseHeaders(200, 0);
            FileInputStream fs = new FileInputStream(file);
            final byte[] buffer = new byte[0x10000];
            int count;
            while ((count = fs.read(buffer)) >= 0) {
                os.write(buffer, 0, count);
            }
            fs.close();
        }
        os.close();
    }

}
