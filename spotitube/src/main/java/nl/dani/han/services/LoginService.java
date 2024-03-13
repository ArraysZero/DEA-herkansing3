package nl.dani.han.services;

import javax.inject.Inject;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.dtos.LoginDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;

public class LoginService {

	@Inject
	private LoginDAO loginDAO;

	public LoginDTO login(UserDTO user) throws LoginException, DataAccessException {
		if (loginDAO.getUser(user) != null) {
			return new LoginDTO(generateToken(user.getUser()), user.getUser());
		} else {
			throw new LoginException("username or password incorrect");
		}
	}

	private String generateToken(String user) throws LoginException, DataAccessException {
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

	public boolean tokenExists(String token) throws DataAccessException {
		if (loginDAO.getUserToken(token) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
}