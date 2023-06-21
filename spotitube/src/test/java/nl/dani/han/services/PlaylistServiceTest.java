package nl.dani.han.services;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;

public class PlaylistServiceTest {

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
	}

	@AfterEach
	void shut() {
		// nog niets
	}
}