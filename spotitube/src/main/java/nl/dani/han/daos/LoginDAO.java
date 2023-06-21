package nl.dani.han.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.LoginException;

public class LoginDAO {

	public UserDTO getUser(UserDTO user) throws LoginException {
		DataAccess data = new DataAccess();
		try (var connection = data.connect()) {
			String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUser());
			stmt.setString(2, user.getPassword());
			var result = stmt.executeQuery();
			if (result.first()) {
				return new UserDTO(result.getNString("name"), result.getNString("password"));
			} else {
				throw new LoginException("username or password incorrect");
			}
		} catch (SQLException e) {
			throw new LoginException(e.getCause());
		}
	}

	public UserDTO getUserToken(String token) throws LoginException {
		DataAccess data = new DataAccess();
		try (var connection = data.connect()) {
			String sql = "SELECT * FROM user WHERE token = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			var result = stmt.executeQuery();
			if (result.first()) {
				return new UserDTO(result.getNString("name"), result.getNString("password"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new LoginException(e.getCause());
		}
	}

	public void addToken(String user, String token) throws LoginException {
		DataAccess data = new DataAccess();
		try (var connection = data.connect()) {
			String sql = "UPDATE user SET token = ? WHERE user = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			stmt.setString(1, user);
			stmt.executeQuery();
		} catch (SQLException e) {
			throw new LoginException(e.getCause());
		}
	}
}