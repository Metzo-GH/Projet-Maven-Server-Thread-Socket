package fr.lille.alom;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Parses the requested URL to extract parameters, then delegates the call to
 * another request handler (e.g. {@link FileRequestHandler})
 *
 */
public class RequestAnalyzer {
    /** implicit contract: must not be null */
    private FileRequestHandler fileRequestHandler = new FileRequestHandler();
    private ServletRequestHandler servletRequestHandler = new ServletRequestHandler();

    /**
     * implicit contract: may be null protected by if (l != null) below
     */
    private Basicl l = new Basicl();

    public void handleRequest(Request r) throws IOException {
        r.in = r.s.getInputStream();
        String requestLine = new LineNumberReader(new InputStreamReader(r.in)).readLine();
        if (l != null) {
            l.log(requestLine);
        }

        if (requestLine == null || requestLine.isEmpty()) {
            throw new CanNotCompleteTheRequestException("Invalid request: " + requestLine);
        }

        String[] requestParts = requestLine.split(" ");
        if (requestParts.length < 2) {
            throw new CanNotCompleteTheRequestException("Invalid request: " + requestLine);
        }

        RequestType type = RequestType.valueOf(requestParts[0]);
        if (type == null) {
            throw new CanNotCompleteTheRequestException("Invalid request type: " + requestParts[0]);
        }

        r.type = type;
        r.url = requestParts[1];

        try {
            if (r.url.startsWith("/servlet")) {
                servletRequestHandler.handleRequest(r);
            } else {
                fileRequestHandler.handleRequest(r);
            }
        } catch (CanNotCompleteTheRequestException e) {
            new ErrorRequestHandler().handleRequest(r);
        }
    }
}
