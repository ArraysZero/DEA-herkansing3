package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.dani.han.dtos.UserDTO;

public class UserDAO {

	public boolean userExists(String username, String password) throws DataAccessException {

		return true; // ik voeg later nog een db toe eerst alles werkend krijgen
		// UserDTO user;
		// try (
		// Connection connection = new DatabaseConnection().getConnection();
		// PreparedStatement prepstmt = connection
		// .prepareStatement("SELECT * FROM users WHERE username=? AND password=?");) {
		// prepstmt.setString(0, username);
		// prepstmt.setString(1, password);

		// ResultSet result = prepstmt.executeQuery();

		// if (result.next()) {
		// return true;
		// } else {
		// return false;
		// }
		// // return (result.next() && result.getString("password") == password);
		// } catch (SQLException | IOException e) {
		// // voor productie software log de error
		// e.printStackTrace();
		// throw new DataAccessException("an unexpected error occured");
		// }
	}

	public UserDTO getUser(String username, String password) {
		return new UserDTO(username, password);
	}

	public void addUser(String username, String password) {

	}

	public void deleteUser(String username) {

	}
}