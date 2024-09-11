package fr.lille.alom;

/** Signals that an HTTP request can not be completed */
public class CanNotCompleteTheRequestException extends RuntimeException {
	
	private static final long serialVersionUID = -7708312211880240315L;

	public CanNotCompleteTheRequestException(String msg) {
		super(msg);
	}
}
