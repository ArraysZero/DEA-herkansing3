package nl.dani.han.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
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
//import nl.dani.han.exceptions.PlaylistException;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.PlaylistService;

public class PlaylistResourceTest {

	private final String MOCK_TOKEN = "MOCK-TOKEN";
	private final int mockId = 1;
	private PlayListListDTO mockPlayListList;
	private LoginException mockLoginException;
	private DataAccessException mockDataException;
//	private PlaylistException mockPlaylistException;
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
		mockDataException = mock(DataAccessException.class);
//		mockPlaylistException = mock(PlaylistException.class);
		mockPlayListDTO = mock(PlayListDTO.class);
		mockTrackListDTO = mock(TrackListDTO.class);
		mockTrackDTO = mock(TrackDTO.class);

		sut = new PlaylistResource();
		sut.setPlaylistService(mockPlaylistService);
		sut.setAuthentication(mockLoginService);
	}

	@Test
	public void getAllPlaylistsTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.getAllPlaylists(MOCK_TOKEN)).thenReturn(mockPlayListList);

		// act
		var actual = sut.getAllPlaylists(MOCK_TOKEN);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test
	public void getAllPlaylistsTestUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.getAllPlaylists(MOCK_TOKEN));
	}

	@Test
	public void getAllPlaylistsTestUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(MOCK_TOKEN)).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getAllPlaylists(MOCK_TOKEN));
	}

	@Test
	public void deletePlaylistTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.deletePlaylist(MOCK_TOKEN, mockId)).thenReturn(mockPlayListList);

		// act
		var actual = sut.deletePlaylist(MOCK_TOKEN, mockId);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test
	public void deletePlaylistTestUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.deletePlaylist(MOCK_TOKEN, mockId));
	}

	@Test
	public void deletePlaylistTestUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.deletePlaylist(MOCK_TOKEN, mockId));
	}

	@Test
	public void addPlaylistTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.addPlaylist(MOCK_TOKEN, mockPlayListDTO)).thenReturn(mockPlayListList);

		// act
		var actual = sut.addPlaylist(MOCK_TOKEN, mockPlayListDTO);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test
	public void addPlaylistUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.addPlaylist(MOCK_TOKEN, mockPlayListDTO));
	}

	@Test
	public void addPlaylistUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.addPlaylist(MOCK_TOKEN, mockPlayListDTO));
	}

	@Test
	public void editPlaylistTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.editPlaylist(MOCK_TOKEN, mockPlayListDTO)).thenReturn(mockPlayListList);

		// act
		var actual = sut.editPlaylist(MOCK_TOKEN, mockId, mockPlayListDTO);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockPlayListList, actual.getEntity());
	}

	@Test
	public void editPlaylistUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.editPlaylist(MOCK_TOKEN, mockId, mockPlayListDTO));
	}

	@Test
	public void editPlaylistUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.addPlaylist(MOCK_TOKEN, mockPlayListDTO));
	}

	@Test
	public void getTrackListPlaylistIdTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.getTrackList(mockId)).thenReturn(mockTrackListDTO);

		// act
		var actual = sut.getTrackListPlaylistId(MOCK_TOKEN, mockId);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockTrackListDTO, actual.getEntity());
	}

	@Test
	public void getTrackListPlaylistIdUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.getTrackListPlaylistId(MOCK_TOKEN, mockId));
	}

	@Test
	public void getTrackListPlaylistIdUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.getTrackListPlaylistId(MOCK_TOKEN, mockId));
	}

	@Test
	public void addTrackPlaylistIdTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.addTrack(mockId, mockTrackDTO)).thenReturn(mockTrackListDTO);

		// act
		var actual = sut.addTrackPlaylistId(MOCK_TOKEN, mockId, mockTrackDTO);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockTrackListDTO, actual.getEntity());
	}

	@Test
	public void addTrackPlaylistIdUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.addTrackPlaylistId(MOCK_TOKEN, mockId, mockTrackDTO));
	}

	@Test
	public void addTrackPlaylistIdUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.addTrackPlaylistId(MOCK_TOKEN, mockId, mockTrackDTO));
	}

	@Test
	public void deleteTrackPlaylistIdTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(MOCK_TOKEN)).thenReturn(true);
		when(mockPlaylistService.deleteTrack(mockId, mockId)).thenReturn(mockTrackListDTO);

		// act
		var actual = sut.deleteTrackPlaylistId(MOCK_TOKEN, mockId, mockId);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(mockTrackListDTO, actual.getEntity());
	}

	@Test
	public void deleteTrackPlaylistIdUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.deleteTrackPlaylistId(MOCK_TOKEN, mockId, mockId));
	}

	@Test
	public void deleteTrackPlaylistIdUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenThrow(mockDataException);
		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.deleteTrackPlaylistId(MOCK_TOKEN, mockId, mockId));
	}
}