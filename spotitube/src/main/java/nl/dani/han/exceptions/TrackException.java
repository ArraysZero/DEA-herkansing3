package nl.dani.han.exceptions;

public class TrackException extends Exception {

	public TrackException(String message) {
		super(message);
	}

	public TrackException(Throwable cause) {
		super(cause);
	}
}