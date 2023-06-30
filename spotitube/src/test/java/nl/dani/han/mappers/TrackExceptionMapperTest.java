package nl.dani.han.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import nl.dani.han.exceptions.TrackException;
import nl.dani.han.exceptions.mappers.TrackExceptionMapper;

public class TrackExceptionMapperTest {

	TrackException mockException = mock(TrackException.class);

	TrackExceptionMapper sut = new TrackExceptionMapper();

	@Test
	public void toResponseTest() {
		var actual = sut.toResponse(mockException);

		assertEquals(403, actual.getStatus());
	}
}