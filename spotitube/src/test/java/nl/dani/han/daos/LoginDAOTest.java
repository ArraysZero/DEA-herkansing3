package nl.dani.han.daos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.dani.han.exceptions.LoginException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;

public class LoginDAOTest {

	// mocks
	private final String MOCK_USER = "user";
	private final String MOCK_PASS = "passwd";
	private final String MOCK_TOKEN = "token-token";
	private UserDTO mockUser;
	private Connection mockConnection;
	private PreparedStatement mockStatement;
	private ResultSet mockResultSet;

	@Mock
	DataAccess mockDataAccess;

	@InjectMocks
	private LoginDAO sut;

	@BeforeEach
	void setup() throws SQLException, IOException {
		mockDataAccess = mock(DataAccess.class);
		mockUser = mock(UserDTO.class);
		mockConnection = mock(Connection.class);
		mockStatement = mock(PreparedStatement.class);
		mockResultSet = mock(ResultSet.class);

		sut = new LoginDAO();
		sut.setDataAccess(mockDataAccess);
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getUserTestSucceeds() throws DataAccessException, SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getNString("name")).thenReturn(MOCK_USER);
		when(mockResultSet.getNString("password")).thenReturn(MOCK_PASS);

		// act
		var actual = sut.getUser(mockUser);

		// assert
		assertEquals(MOCK_USER, actual.getUser());
		assertEquals(MOCK_PASS, actual.getPassword());
	}

	@Test
	public void getUserTestUnhappyNULL() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(false);

		// act
		var actual = sut.getUser(mockUser);

		// assert
		assertNull(actual);
	}

	@Test
	public void getUserTestUnhappySQLException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act

		// arrange
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getUser(mockUser));
	}

	@Test
	public void getUserTokenSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getNString("name")).thenReturn(MOCK_USER);
		when(mockResultSet.getNString("password")).thenReturn(MOCK_PASS);

		// act
		var actual = sut.getUserToken(MOCK_TOKEN);

		// assert
		assertEquals(MOCK_USER, actual.getUser());
		assertEquals(MOCK_PASS, actual.getPassword());
	}

	@Test
	public void getUserTokenUnhappyNULL() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(false);

		// act
		var actual = sut.getUserToken(MOCK_TOKEN);

		// assert
		assertNull(actual);
	}

	@Test
	public void getUserTokenUnhappySQLException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act

		// arrange
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getUserToken(MOCK_TOKEN));
	}

	@Test
	public void addTokenSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(true);

		// act
		boolean actual = sut.addToken(MOCK_USER, MOCK_TOKEN);

		// arrange
		assertTrue(actual);
	}

	@Test
	public void addTokenUnhappyFalse() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(false);

		// act
		boolean actual = sut.addToken(MOCK_USER, MOCK_TOKEN);

		// arrange
		assertFalse(actual);
	}

	@Test
	public void addTokenUnhappySQLException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenThrow(SQLException.class);

		// act
//		boolean actual = sut.addToken(MOCK_USER, MOCK_TOKEN);

		// arrange
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.addToken(MOCK_USER, MOCK_TOKEN));
	}

}