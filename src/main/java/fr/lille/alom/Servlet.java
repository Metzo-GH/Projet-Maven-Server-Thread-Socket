package fr.lille.alom;

import java.io.IOException;

public interface Servlet {

	public void doGet(Request request) throws IOException;
	
	public void doPost(Request request) throws IOException;
	
}
