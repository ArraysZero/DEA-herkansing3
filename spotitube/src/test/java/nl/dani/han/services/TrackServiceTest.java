package nl.dani.han.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
//import nl.dani.han.exceptions.PlaylistException;
import nl.dani.han.exceptions.TrackException;

public class TrackServiceTest {

	// mocks
	private final int MOCK_ID = 0;
	private TrackListDTO mockTrackList;
//	private PlaylistException mockException;

	@Mock
	PlaylistDAO mockPlaylistDAO;

	@Mock
	TrackDAO mockTrackDAO;

	@InjectMocks
	TrackService sut;

	@BeforeEach
	void setup() {
		mockPlaylistDAO = mock(PlaylistDAO.class);
		mockTrackDAO = mock(TrackDAO.class);

		sut = new TrackService();
		sut.setPlaylistDAO(mockPlaylistDAO);
		sut.setTrackDAO(mockTrackDAO);

		mockTrackList = mock(TrackListDTO.class);
//		mockException = mock(PlaylistException.class);
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void getAvailableTracksTestSucceeds() throws TrackException, DataAccessException {
		// arrange
		when(mockPlaylistDAO.getTracks(MOCK_ID)).thenReturn(mockTrackList);
		when(mockTrackDAO.getTracks()).thenReturn(mockTrackList);
		when(mockTrackDAO.compareLists(mockTrackList, mockTrackList)).thenReturn(mockTrackList);

		// act
		var actual = sut.getAvailableTracks(MOCK_ID);

		// assert
		assertEquals(mockTrackList, actual);
	}
}