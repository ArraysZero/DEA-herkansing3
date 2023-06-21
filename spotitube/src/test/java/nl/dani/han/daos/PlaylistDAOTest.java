package nl.dani.han.daos;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
}