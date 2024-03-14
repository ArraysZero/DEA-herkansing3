package nl.dani.han.daos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.dani.han.database.DataAccess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TrackDAOTest {

	@Mock
	DataAccess mockDataAccess;

	Connection mockConnection;
	PreparedStatement mockStatement;
	ResultSet mockResult;

	final int MOCK_ID = 0;
	final String MOCK_NAME = "MOCK NAME";

	@InjectMocks
	TrackDAO sut;

	@BeforeEach
	void setup() {
		mockDataAccess = mock(DataAccess.class);
		mockConnection = mock(Connection.class);
		mockStatement = mock(PreparedStatement.class);
		mockResult = mock(ResultSet.class);

		sut = new TrackDAO();
		sut.setDataAccess(mockDataAccess);
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getAllTracksTestSucceeds() throws DataAccessException, SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(true, false);
		when(mockResult.getString(anyString())).thenReturn(MOCK_NAME);
		when(mockResult.getInt(anyString())).thenReturn(MOCK_ID);
		when(mockResult.getBoolean(anyString())).thenReturn(true);

		// act
		var actual = sut.getTracks();

		// assert
		assertEquals(actual.getTracks().size(), 1);
	}

	@Test
	public void getAllTracksUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act
//		var actual = sut.getTracks();

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getTracks());
	}

	@Test
	public void getTrackSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(true, false);
		when(mockResult.getString(anyString())).thenReturn(MOCK_NAME);
		when(mockResult.getInt(anyString())).thenReturn(MOCK_ID);
		when(mockResult.getBoolean(anyString())).thenReturn(true);

		// act
		var actual = sut.getTrack(MOCK_ID);

		// assert
		assertEquals(MOCK_ID, actual.getId());
	}

	@Test
	public void getTrackUnhappyNULL() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(false);

		// act
		var actual = sut.getTrack(MOCK_ID);

		// assert
		assertNull(actual);
	}

	@Test
	public void getTrackUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act
//		var actual = sut.getTracks();

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getTrack(MOCK_ID));
	}
}