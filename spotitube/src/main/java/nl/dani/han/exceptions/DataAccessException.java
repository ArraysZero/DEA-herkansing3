package nl.dani.han.exceptions;

public class DataAccessException extends Exception {

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}