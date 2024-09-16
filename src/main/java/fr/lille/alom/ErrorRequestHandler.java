package fr.lille.alom;

import java.io.IOException;

/** Sends an HTTP 404 in response to the request */
public class ErrorRequestHandler {
    public void handleRequest(Request r) throws IOException {
        String response = "HTTP/1.0 404 Not Found\r\n" +
                          "Content-Type: text/plain\r\n" +
                          "Content-Length: 29\r\n" +
                          "\r\n" +
                          "Comanche: document not found.";
        r.out.write(response.getBytes());
    }
}
