package nl.dani.han.resources;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.exceptions.PlaylistException;
import nl.dani.han.services.PlaylistService;

public class PlaylistResourceTest {

	@Mock
	private PlaylistService mockPlaylistService;

	@InjectMocks
	private PlaylistResource sut;

	@BeforeEach
	public void setup() {
		mockPlaylistService = mock(PlaylistService.class);

		sut = new PlaylistResource();
	}

	@Test
	public void getAllPlaylistsTestSucceeds() throws PlaylistException {
		// arrange
		when(mockPlaylistService.getAllPlaylists()).thenReturn(null);

		// act
		// var actual = sut.getAllPlaylists();

		// assert
	}
}