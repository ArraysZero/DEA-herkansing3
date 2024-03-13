package nl.dani.han.exceptions;

public class TrackException extends RuntimeException {

	public TrackException(String message) {
		super(message);
	}

	public TrackException(Throwable cause) {
		super(cause);
	}
}