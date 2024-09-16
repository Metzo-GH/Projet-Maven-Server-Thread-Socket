package fr.lille.alom;

import java.io.IOException;

public class HelloWorldServlet implements Servlet {

    @Override
    public void doGet(Request r) throws IOException {
        String response = "HTTP/1.0 200 OK\r\n" +
                          "Content-Type: text/plain\r\n" +
                          "Content-Length: 12\r\n" +
                          "\r\n" +
                          "Hello World!";
        r.out.write(response.getBytes());
    }

    @Override
    public void doPost(Request r) throws IOException {
        doGet(r);
    }
}
