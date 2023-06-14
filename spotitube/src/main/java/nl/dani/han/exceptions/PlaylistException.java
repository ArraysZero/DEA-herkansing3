package nl.dani.han.exceptions;

public class PlaylistException extends Exception {

	public PlaylistException(String message) {
		super(message);
	}

	public PlaylistException(Throwable cause) {
		super(cause);
	}
}