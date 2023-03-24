package nl.dani.han.services;

public class ServiceException extends Exception {

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message) {
		super(message);
	}
}