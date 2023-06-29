package nl.dani.han.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;
import nl.dani.han.exceptions.PlaylistException;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.PlaylistService;

public class PlaylistResourceTest {

	private final String mockToken = "MOCK-TOKEN";
	private final int mockId = 1;
	private PlayListListDTO mockPlayListList;
	private LoginException mockLoginException;
	private PlaylistException mockPlaylistException;
	private PlayListDTO mockPlayListDTO;
	private TrackListDTO mockTrackListDTO;
	private TrackDTO mockTrackDTO;

	@Mock
	private PlaylistService mockPlaylistService;

	@Mock
	private LoginService mockLoginService;

	@InjectMocks
	private PlaylistResource sut;

	@BeforeEach
	public void setup() {
		mockPlaylistService = mock(PlaylistService.class);
		mockLoginService = mock(LoginService.class);
		mockPlayListList = mock(PlayListListDTO.class);
		mockLoginException = mock(LoginException.class);
		mockPlaylistException = mock(PlaylistException.class);
		mockPlayListDTO = mock(PlayListDTO.class);
		mockTrackListDTO = mock(TrackListDTO.class);
		mockTrackDTO = mock(TrackDTO.class);

		sut = new PlaylistResource();
		sut.setPlaylistService(mockPlaylistService);
		sut.setAuthentication(mockLoginService);
	}

	@Test
	public void getAllPlaylistsTestSucceeds() throws PlaylistException, LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.getAllPlaylists()).thenReturn(mockPlayListList);

		// act
		var actual = sut.getAllPlaylists(mockToken);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test
	public void deletePlaylistTestSucceeds() throws LoginException, PlaylistException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.deletePlaylist(mockId)).thenReturn(mockPlayListList);

		// act
		var actual = sut.deletePlaylist(mockToken, mockId);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test
	public void addPlaylistTestSucceeds() throws LoginException, PlaylistException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.addPlaylist(mockPlayListDTO)).thenReturn(mockPlayListList);

		// act
		var actual = sut.addPlaylist(mockToken, mockPlayListDTO);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test 
	public void editPlaylistTestSucceeds() throws PlaylistException, LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.editPlaylist(mockPlayListDTO)).thenReturn(mockPlayListList);

		// act
		var actual = sut.editPlaylist(mockToken, mockId, mockPlayListDTO);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test 
	public void getTrackListPlaylistIdTestSucceeds() throws LoginException, PlaylistException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.getTrackList(mockId)).thenReturn(mockTrackListDTO);

		// act
		var actual = sut.getTrackListPlaylistId(mockToken, mockId);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockTrackListDTO, actual.getEntity());
	}

	@Test
	public void addTrackPlaylistIdTestSucceeds() throws LoginException, PlaylistException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.addTrack(mockId, mockTrackDTO)).thenReturn(mockTrackListDTO);

		// act
		var actual = sut.addTrackPlaylistId(mockToken, mockId, mockTrackDTO);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockTrackListDTO, actual.getEntity());
	}

	@Test
	public void deleteTrackPlaylistIdTestSucceeds() throws LoginException, PlaylistException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(mockToken)).thenReturn(true);
		when(mockPlaylistService.deleteTrack(mockId, mockId)).thenReturn(mockTrackListDTO);

		// act
		var actual = sut.deleteTrackPlaylistId(mockToken, mockId, mockId);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockTrackListDTO, actual.getEntity());
	}
}