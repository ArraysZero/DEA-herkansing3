package nl.dani.han.daos;

public class TokenDAO {

	public String generateToken(String username) {
		String token = username + (Math.random() * 1000);

		// store token

		return token;
	}

	public boolean tokenExists(String token) {
		return false;
	}
}