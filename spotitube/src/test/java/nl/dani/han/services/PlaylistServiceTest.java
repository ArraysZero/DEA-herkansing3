package nl.dani.han.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
//import nl.dani.han.exceptions.PlaylistException;

public class PlaylistServiceTest {

	// mocks
	private final int MOCK_ID = 0;
	private TrackDTO mockTrack;
	private TrackListDTO mockTrackList;
	private PlayListDTO mockPlayList;
	private PlayListListDTO mockPlayListList;

	@Mock
	PlaylistDAO mockPlaylistDAO;

	@Mock
	TrackDAO mockTrackDAO;

	@InjectMocks
	PlaylistService sut;

	@BeforeEach
	void setup() {
		mockPlaylistDAO = mock(PlaylistDAO.class);
		mockTrackDAO = mock(TrackDAO.class);

		sut = new PlaylistService();
		sut.setPlaylistDAO(mockPlaylistDAO);
		sut.setTrackDAO(mockTrackDAO);

		mockPlayListList = mock(PlayListListDTO.class);
		mockPlayList = mock(PlayListDTO.class);
		mockTrackList = mock(TrackListDTO.class);
		mockTrack = mock(TrackDTO.class);
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getAllPlaylistsTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getAll()).thenReturn(mockPlayListList);

		// act
		var actual = sut.getAllPlaylists();

		// assert
		assertEquals(mockPlayListList, actual);
	}

	@Test
	public void deletePlaylistTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getAll()).thenReturn(mockPlayListList);

		// act
		var actual = sut.deletePlaylist(MOCK_ID);

		// assert
		assertEquals(mockPlayListList, actual);
	}

	@Test
	public void addPlaylistTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getAll()).thenReturn(mockPlayListList);

		// act
		var actual = sut.addPlaylist(mockPlayList);

		// assert
		assertEquals(mockPlayListList, actual);
	}

	@Test
	public void editPlaylistTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getAll()).thenReturn(mockPlayListList);

		// act
		var actual = sut.editPlaylist(mockPlayList);

		// assert
		assertEquals(mockPlayListList, actual);
	}

	@Test
	public void getTrackListTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getTracks(anyInt())).thenReturn(mockTrackList);

		// act
		var actual = sut.getTrackList(MOCK_ID);

		// assert
		assertEquals(mockTrackList, actual);
	}

	@Test
	public void addTrackTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getTracks(anyInt())).thenReturn(mockTrackList);

		// act
		var actual = sut.addTrack(MOCK_ID, mockTrack);

		// assert
		assertEquals(mockTrackList, actual);
	}

	@Test
	public void deleteTrackTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getTracks(anyInt())).thenReturn(mockTrackList);

		// act
		var actual = sut.deleteTrack(MOCK_ID, 0);

		// assert
		assertEquals(mockTrackList, actual);
	}
}