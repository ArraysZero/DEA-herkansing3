package nl.dani.han.exceptions;

public class LoginException extends RuntimeException {

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}
}