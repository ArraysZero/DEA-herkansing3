package nl.dani.han.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.TrackException;
import nl.dani.han.services.TrackService;

public class TrackResourceTest {

	@Mock
	private TrackService mockTrackService;

	@InjectMocks
	private TrackResource sut;

	@BeforeEach
	public void setup() {
		System.out.println("setup test");
		mockTrackService = mock(TrackService.class);

		System.out.println(mockTrackService);
		sut = new TrackResource();
		System.out.println(sut.getTrackService());
	}

	@AfterEach
	public void close() {
		// nothing yet
	}

	@Test
	public void getAvailableTracksTestSucceeds() throws TrackException {
		// arrange
		TrackListDTO expected = mock(TrackListDTO.class);
		when(mockTrackService.getAvailableTracks(anyInt())).thenReturn(expected);

		// act
		// var actual = sut.getAvailableTracks();

		// assert
		// assertEquals(200, actual.getStatus());
		// assertEquals(expected, actual.getEntity());
	}

	@Test
	public void getAvailableTracksTestFails() throws TrackException {
		// arrange
		var expected = mock(TrackException.class);
		when(mockTrackService.getAvailableTracks(anyInt())).thenThrow(expected);

		// act
		// var actual = sut.getAvailableTracks();

		// assert
		// assertEquals(200, actual.getStatus());
		// assertEquals(expected, actual.getEntity());
	}
}