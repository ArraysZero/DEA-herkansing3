package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;

import javax.inject.Inject;

public class LoginDAO{

	@Inject
	private DataAccess dataAccess;

	public UserDTO getUser(UserDTO user) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUser());
			stmt.setString(2, user.getPassword());
			var result = stmt.executeQuery();
			if (result.next()) {
				return new UserDTO(result.getNString("name"), result.getNString("password"));
			} else {
				return null;
			}
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public UserDTO getUserToken(String token) throws DataAccessException {
		try (var connection = dataAccess.connect()) {
			String sql = "SELECT * FROM users WHERE token = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			var result = stmt.executeQuery();
			if (result.next()) {
				return new UserDTO(result.getNString("name"), result.getNString("password"));
			} else {
				return null;
			}
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public boolean addToken(String user, String token) throws LoginException, DataAccessException {
		try (var connection = dataAccess.connect()) {
			String sql = "UPDATE users SET token = ? WHERE name = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			stmt.setString(2, user);
			return stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
}