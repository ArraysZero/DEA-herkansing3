package nl.dani.han.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.dtos.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.exceptions.DataAccessException;

import java.util.ArrayList;
//import nl.dani.han.exceptions.PlaylistException;

public class PlaylistServiceTest {

	// mocks
	private final int MOCK_ID = 0;
	private final String MOCK_TOKEN = "MOCK-TOKEN";
	private TrackDTO mockTrack;
	private TrackListDTO mockTrackList;
	private PlayListDTO mockPlayList;
	private UserDTO mockUserDTO;
	private PlayListListDTO mockPlayListList;
    private PlaylistDataDTO mockPlaylistData;
    private PlaylistListDataDTO mockPlaylistListData;

	@Mock
	PlaylistDAO mockPlaylistDAO;

	@Mock
	TrackDAO mockTrackDAO;

	@Mock
	LoginDAO mockLoginDAO;

	@InjectMocks
	PlaylistService sut;

	@BeforeEach
	void setup() {
		mockPlaylistDAO = mock(PlaylistDAO.class);
		mockTrackDAO = mock(TrackDAO.class);
		mockLoginDAO = mock(LoginDAO.class);

		sut = new PlaylistService();
		sut.setPlaylistDAO(mockPlaylistDAO);
		sut.setTrackDAO(mockTrackDAO);
		sut.setLoginDAO(mockLoginDAO);

		mockPlayListList = mock(PlayListListDTO.class);
		mockPlayList = mock(PlayListDTO.class);
		mockTrackList = mock(TrackListDTO.class);
		mockTrack = mock(TrackDTO.class);
		mockUserDTO = mock(UserDTO.class);
        mockPlaylistData = new PlaylistDataDTO(0, "name", "name", mockTrackList);
        mockPlaylistListData = new PlaylistListDataDTO(new ArrayList<>());
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getAllPlaylistsTestSucceeds() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getPlaylists()).thenReturn(mockPlaylistListData);
		when(mockLoginDAO.getUserToken(anyString())).thenReturn(mockUserDTO);
		when(mockUserDTO.getUser()).thenReturn("user");

		// act
		var actual = sut.getAllPlaylists(MOCK_TOKEN);

		// assert
		assertEquals(mockPlayListList.getPlaylists().size(), 0);
	}

	@Test
	public void deletePlaylistTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getPlaylists()).thenReturn(mockPlaylistListData);

		// act
		var actual = sut.deletePlaylist(MOCK_TOKEN, MOCK_ID);

		// assert
		assertEquals(mockPlayListList.getPlaylists().size(), 0);
	}

	@Test
	public void addPlaylistTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getPlaylists()).thenReturn(mockPlaylistListData);
		when(mockLoginDAO.getUserToken(anyString())).thenReturn(mockUserDTO);
		when(mockUserDTO.getUser()).thenReturn("user");


		// act
		var actual = sut.addPlaylist(MOCK_TOKEN, mockPlayList);

		// assert
		assertEquals(mockPlayListList.getPlaylists().size(), 0);
	}

	@Test
	public void editPlaylistTest() throws DataAccessException {
		// arrange
		when(mockPlaylistDAO.getPlaylists()).thenReturn(mockPlaylistListData);

		// act
		var actual = sut.editPlaylist(MOCK_TOKEN, mockPlayList);

		// assert
		assertEquals(mockPlayListList.getPlaylists().size(), 0);
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