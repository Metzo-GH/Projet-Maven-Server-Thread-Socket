package fr.lille.alom;

import java.io.IOException;

public class HelloWorldServlet implements Servlet {

	@Override
	public void doGet(Request r) throws IOException {
		r.out.write("HTTP/1.0 200 OK\n".getBytes());
	    r.out.write("\n".getBytes());    
	    r.out.write("Hello World!".getBytes());    
	}

	@Override
	public void doPost(Request r) throws IOException {
		doGet(r);
	}

}
