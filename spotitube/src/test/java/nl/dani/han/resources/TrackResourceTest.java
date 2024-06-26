package nl.dani.han.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.TrackService;

public class TrackResourceTest {

	private final String MOCKTOKEN = "mock-token";
	private final int MOCKID = 0;

	@Mock
	private TrackService mockTrackService;

	@Mock
	private LoginService mockLoginService;

	@InjectMocks
	private TrackResource sut;

	@BeforeEach
	public void setup() {
		mockTrackService = mock(TrackService.class);
		mockLoginService = mock(LoginService.class);

		sut = new TrackResource();

		sut.setTrackService(mockTrackService);
		sut.setAuthentication(mockLoginService);
	}

	@AfterEach
	public void close() {
		// nothing yet
	}

	@Test
	public void getAvailableTracksTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		TrackListDTO expected = mock(TrackListDTO.class);
		when(mockLoginService.tokenExists(MOCKTOKEN)).thenReturn(true);
		when(mockTrackService.getAvailableTracks(anyInt())).thenReturn(expected);

		// act
		var actual = sut.getAvailableTracks(MOCKTOKEN, MOCKID);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(expected, actual.getEntity());
	}

	@Test
	public void getAvailableTracksUnhappyInvalidToken() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginService.tokenExists(anyString())).thenReturn(false);
//		when(mockPlaylistService.getAllPlaylists(anyString())).thenReturn(mockPlayListList);

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.getAvailableTracks(MOCKTOKEN, MOCKID));
	}
}