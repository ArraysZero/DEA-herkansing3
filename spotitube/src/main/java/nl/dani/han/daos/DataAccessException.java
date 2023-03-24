package nl.dani.han.daos;

public class DataAccessException extends Exception {

	public DataAccessException(Throwable cause) {
		super(cause);
	}

	public DataAccessException(String message) {
		super(message);
	}
}