package nl.dani.han.daos;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.LoginException;

public class LoginDAO {

	public UserDTO getUser(UserDTO user) throws LoginException {
		try (var connection = DataAccess.connect()) {
			String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUser());
			stmt.setString(2, user.getPassword());
			var result = stmt.executeQuery();
			if (result.next()) {
				return new UserDTO(result.getNString("name"), result.getNString("password"));
			} else {
				throw new LoginException("username or password incorrect");
			}
		} catch (SQLException | IOException e) {
			throw new LoginException(e.getCause());
		}
	}

	public UserDTO getUserToken(String token) throws LoginException {
		try (var connection = DataAccess.connect()) {
			String sql = "SELECT * FROM user WHERE token = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			var result = stmt.executeQuery();
			if (result.next()) {
				return new UserDTO(result.getNString("name"), result.getNString("password"));
			} else {
				return null;
			}
		} catch (SQLException | IOException e) {
			throw new LoginException(e.getMessage());
		}
	}

	public void addToken(String user, String token) throws LoginException {
		try (var connection = DataAccess.connect()) {
			String sql = "UPDATE user SET token = ? WHERE user = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			stmt.setString(1, user);
			stmt.executeQuery();
		} catch (SQLException | IOException e) {
			throw new LoginException(e.getCause());
		}
	}
}