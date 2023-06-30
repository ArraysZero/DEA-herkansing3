package nl.dani.han.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.PlaylistException;

public class PlaylistDAOTest {

	@Mock
	TrackDAO mockTrackDAO;

	@InjectMocks
	PlaylistDAO sut;

	@BeforeEach
	void setup() {
		mockTrackDAO = mock(TrackDAO.class);

		sut = new PlaylistDAO();
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getAllPlaylistsTestSucceeds() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// var actual = sut.getAllPlaylists();

		// // assert
		// assertNotEquals(0, actual.getPlaylists().size());
	}

	@Test
	public void getPlaylistIdTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// var actual = sut.getPlaylistId(0);

		// // assert
		// assertEquals(0, actual.getClass());
	}

	@Test
	public void getAllPlaylistsTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// var actual = sut.getTracks(0);

		// // assert
		// assertNotEquals(0, actual.getTracks().size());
	}

	@Test
	public void deletePlaylistTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// sut.deletePlaylist(0);

		// // assert
	}

	@Test
	public void addPlaylistTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// sut.addPlaylist(null);

		// // assert
	}

	@Test
	public void addTrackToPlaylistTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// sut.addTrackToPlaylist(0, 0);

		// // assert
	}

	@Test
	public void changePlaylistNameTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// sut.changePlaylistName(0, null);

		// // assert
	}

	@Test
	public void deleteTrackFromPlaylistTest() throws PlaylistException, DataAccessException {
		// arrange
		// setup data

		// act
		// sut.deleteTrackFromPlaylist(0, 0);

		// // assert
	}
}