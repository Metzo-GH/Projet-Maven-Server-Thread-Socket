package fr.lille.alom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class App {
    private static final int PORT = 8080;
    private static final ConcurrentLinkedQueue<Request> requestQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        // Enregistrer les servlets
        ServletFactory.getInstance().registerServlet("hello", new HelloWorldServlet());

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            // Start a thread to handle requests from the queue
            new Thread(new RequestHandlerThread()).start();

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ConnectionHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ConnectionHandler implements Runnable {
        private final Socket socket;

        public ConnectionHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Request request = new Request(socket);
                requestQueue.add(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class RequestHandlerThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                Request request = requestQueue.poll();
                if (request != null) {
                    try {
                        handleRequest(request);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            request.s.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        private void handleRequest(Request request) throws IOException {
            try {
                new RequestAnalyzer().handleRequest(request);
            } catch (CanNotCompleteTheRequestException e) {
                new ErrorRequestHandler().handleRequest(request);
            }
        }
    }
}
