package nl.dani.han.daos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.TrackDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.exceptions.DataAccessException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import nl.dani.han.exceptions.PlaylistException;

public class PlaylistDAOTest {

	@Mock
	TrackDAO mockTrackDAO;
    @Mock
    DataAccess mockDataAccess;

    Connection mockConnection;
    PreparedStatement mockStatement;
    ResultSet mockResult;
    PlayListDTO mockPlaylist;

    final int MOCK_ID = 0;
    final String MOCK_USER = "MOCK USER";


	@InjectMocks
	PlaylistDAO sut;

	@BeforeEach
	void setup() {
		mockTrackDAO = mock(TrackDAO.class);
        mockDataAccess = mock(DataAccess.class);
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResult = mock(ResultSet.class);
        mockPlaylist = mock(PlayListDTO.class);

		sut = new PlaylistDAO();
        sut.setDataAccess(mockDataAccess);
        sut.setTrackDAO(mockTrackDAO);
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getAllPlaylistsSucceeds() throws DataAccessException, SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(true, false, false);

		// act
		var actual = sut.getPlaylists();

		// assert
		assertEquals(actual.getPlaylists().size(), 1);
	}

	@Test
	public void getAllPlaylistsUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act
//		var actual = sut.getPlaylists();

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getPlaylists());
	}

	@Test
	public void getPlaylistByIdSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(true, false);
		when(mockResult.getString(anyString())).thenReturn(MOCK_USER);

		// act
		var actual = sut.getPlaylistById(MOCK_ID);

		// assert
		assertEquals(actual.getId(), MOCK_ID);
	}

	@Test
	public void getPlaylistByIdUnhappyNULL() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(false);

		// act
		var actual = sut.getPlaylistById(MOCK_ID);

		// assert
		assertNull(actual);
	}

	@Test
	public void getPlaylistByIdUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act
//		var actual = sut.getPlaylistById(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getPlaylistById(MOCK_ID));
	}

	@Test
	public void getTracksSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResult);
		when(mockResult.next()).thenReturn(true, false);
		when(mockResult.getInt(anyString())).thenReturn(MOCK_ID);
		when(mockTrackDAO.getTrack(anyInt())).thenReturn(new TrackDTO());

		// act
		var actual = sut.getTracks(MOCK_ID);

		// assert
		assertEquals(1, actual.getTracks().size());
	}

	@Test
	public void getTracksUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenThrow(SQLException.class);

		// act
//		var actual = sut.getPlaylistById(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getTracks(MOCK_ID));
	}

	@Test
	public void deletePlaylistSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(true);

		// act
		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		assertTrue(actual);
	}

	@Test
	public void deletePlaylistUnhappyFalse() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(false);

		// act
		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		assertFalse(actual);
	}

	@Test
	public void deletePlaylistUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenThrow(SQLException.class);

		// act
//		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.deletePlaylist(MOCK_ID));
	}

	@Test
	public void addPlaylistSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(true);

		// act
		var actual = sut.addPlaylist(new PlayListDTO(), MOCK_USER);

		// assert
		assertTrue(actual);
	}

	@Test
	public void addPlaylistUnhappyFalse() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(false);

		// act
		var actual = sut.addPlaylist(new PlayListDTO(), MOCK_USER);

		// assert
		assertFalse(actual);
	}

	@Test
	public void addPlaylistUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenThrow(SQLException.class);

		// act
//		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.addPlaylist(new PlayListDTO(), MOCK_USER));
	}

	@Test
	public void addTrackToPlaylistSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(true);

		// act
		var actual = sut.addTrackToPlaylist(MOCK_ID, MOCK_ID);

		// assert
		assertTrue(actual);
	}

	@Test
	public void addTrackToPlaylistUnhappyFalse() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(false);

		// act
		var actual = sut.addTrackToPlaylist(MOCK_ID, MOCK_ID);

		// assert
		assertFalse(actual);
	}

	@Test
	public void addTrackToPlaylistUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenThrow(SQLException.class);

		// act
//		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.addTrackToPlaylist(MOCK_ID, MOCK_ID));
	}

	@Test
	public void changePlaylistNameSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(true);

		// act
		var actual = sut.changePlaylistName(MOCK_ID, MOCK_USER);

		// assert
		assertTrue(actual);
	}

	@Test
	public void changePlaylistNameUnhappyFalse() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(false);

		// act
		var actual = sut.changePlaylistName(MOCK_ID, MOCK_USER);

		// assert
		assertFalse(actual);
	}

	@Test
	public void changePlaylistNameUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenThrow(SQLException.class);

		// act
//		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.changePlaylistName(MOCK_ID, MOCK_USER));
	}

	@Test
	public void deleteTrackFromPlaylistSuccess() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(true);

		// act
		var actual = sut.deleteTrackFromPlaylist(MOCK_ID, MOCK_ID);

		// assert
		assertTrue(actual);
	}

	@Test
	public void deleteTrackFromPlaylistUnhappyFalse() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenReturn(false);

		// act
		var actual = sut.deleteTrackFromPlaylist(MOCK_ID, MOCK_ID);

		// assert
		assertFalse(actual);
	}

	@Test
	public void deleteTrackFromPlaylistUnhappyDataAccessException() throws SQLException, IOException {
		// arrange
		when(mockDataAccess.connect()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
		when(mockStatement.execute()).thenThrow(SQLException.class);

		// act
//		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.deleteTrackFromPlaylist(MOCK_ID, MOCK_ID));
	}

}