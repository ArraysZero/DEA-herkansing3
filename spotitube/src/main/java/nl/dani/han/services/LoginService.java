package nl.dani.han.services;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.dtos.LoginDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.LoginException;

public class LoginService {

	private LoginDAO loginDAO = new LoginDAO();

	public LoginDTO login(UserDTO user) throws LoginException {
		if (user.equals(loginDAO.getUser(user))) {
			return new LoginDTO(generateToken(), user.getUser());
		} else {
			throw new LoginException("login failed");
		}
	}

	private String generateToken() {
		String token = (int) (Math.random() * 10000) + "-"
				+ (int) (Math.random() * 10000) + "-"
				+ (int) (Math.random() * 10000)
				+ "-" + (int) (Math.random() * 10000);

		// TODO add token to existing tokens
		return token;
	}

	public boolean tokenExists(String token) {
		return true; // TODO implement
	}
}