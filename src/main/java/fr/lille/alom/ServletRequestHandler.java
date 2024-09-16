package fr.lille.alom;

import java.io.IOException;

/** Find and execute the servlet bind on the url */
public class ServletRequestHandler {
    public void handleRequest(Request r) throws IOException {
        // Split url by /
        String[] urls = r.url.split("/");
        if (urls.length == 0) {
            throw new CanNotCompleteTheRequestException("no such servlet found");
        }
        // Getting servlet by name used in URL, i.e. GET /servletName/...
        Servlet servlet = ServletFactory.getInstance().getServletByName(urls[0]);
        if (servlet != null) {
            servlet.doGet(r);
        } else {
            throw new CanNotCompleteTheRequestException("no such servlet found");
        }
    }
}
