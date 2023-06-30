package nl.dani.han.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;

public class LoginDAOTest {

	// mocks
	private final String MOCK_USER = "user";
	private final String MOCK_PASS = "passwd";
	private UserDTO mockUser;

	LoginDAO sut;

	@BeforeEach
	void setup() throws SQLException, IOException {
		sut = new LoginDAO();
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getUserTestSucceeds() throws DataAccessException, SQLException {
		// arrange
		// setup data

		// act
		var actual = sut.getUser(mockUser);

		// assert
		assertEquals(MOCK_USER, actual.getUser());
		assertEquals(MOCK_PASS, actual.getPassword());
	}

	@Test
	public void getUserTestNull() throws DataAccessException, SQLException {
		// arrange
		// setup data

		// act
		var actual = sut.getUser(mockUser);

		// assert
		assertNull(actual);
	}

	@Test
	public void getUserTokenTestSucceeds() throws DataAccessException, SQLException {
		// arrange
		// setup data

		// act
		var actual = sut.getUser(mockUser);

		// assert
		assertEquals(MOCK_USER, actual.getUser());
		assertEquals(MOCK_PASS, actual.getPassword());
	}

	@Test
	public void getUserTokenTestNull() throws DataAccessException, SQLException {
		// arrange
		// setup data

		// act
		var actual = sut.getUser(mockUser);

		// assert
		assertNull(actual);
	}
}