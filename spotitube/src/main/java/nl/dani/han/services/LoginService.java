package nl.dani.han.services;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.dtos.LoginDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.LoginException;

public class LoginService {

	private LoginDAO loginDAO = new LoginDAO();

	public LoginDTO login(UserDTO user) throws LoginException {
		if (user.equals(loginDAO.getUser(user))) {
			return new LoginDTO(generateToken(user.getUser()), user.getUser());
		} else {
			throw new LoginException("username or password incorrect");
		}
	}

	private String generateToken(String user) throws LoginException {
		String token;
		do {
			token = (int) (Math.random() * 10000) + "-"
					+ (int) (Math.random() * 10000) + "-"
					+ (int) (Math.random() * 10000)
					+ "-" + (int) (Math.random() * 10000);
		} while (tokenExists(token));

		loginDAO.addToken(user, token);

		return token;
	}

	public boolean tokenExists(String token) throws LoginException {
		if (loginDAO.getUserToken(token) != null) {
			return true;
		} else {
			return false;
		}
	}
}