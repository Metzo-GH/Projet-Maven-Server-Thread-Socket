package fr.lille.alom;
import java.io.IOException;

/** Sends an HTTP 404 in response to the request */
public class ErrorRequestHandler  {
  public void handleRequest (Request r) throws IOException {
    r.out.write("HTTP/1.0 404 Not Found\n\n".getBytes());
    r.out.write("Comanche: document not found.".getBytes());
  }
}
