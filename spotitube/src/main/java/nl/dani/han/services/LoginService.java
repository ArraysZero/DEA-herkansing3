package nl.dani.han.services;

import javax.inject.Inject;

import nl.dani.han.daos.DataAccessException;
import nl.dani.han.daos.TokenDAO;
import nl.dani.han.daos.UserDAO;

public class LoginService {

	@Inject
	UserDAO userDAO;

	@Inject
	TokenDAO tokenDAO;

	public String login(String username, String password) throws ServiceException, DataAccessException {
		// if (userDAO == null) {
		// userDAO = new UserDAO();
		// tokenDAO = new TokenDAO();
		// }
		if (userDAO.userExists(username, password)) {
			return tokenDAO.generateToken(username);
		}

		throw new ServiceException("password or username incorrect");
	}
}