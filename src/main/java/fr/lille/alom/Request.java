package fr.lille.alom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * An A simplified view of a network HTTP request, one URL, some input and a
 * channel for output.
 */
public class Request {
	public Socket s;
	public InputStream in;
	public OutputStream out;
	public String url;
	public RequestType type;

	public Request(Socket s) throws IOException {
		this.s = s;
		out = s.getOutputStream();
	}

	public Socket getSocket() {
		return this.s;
	}
}
