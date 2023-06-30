package nl.dani.han.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.mappers.DataAccessExceptionMapper;

public class DataAccessExceptionMapperTest {

	DataAccessException mockException = mock(DataAccessException.class);

	DataAccessExceptionMapper sut = new DataAccessExceptionMapper();

	@Test
	public void toResponseTest() {
		// arrange

		// act
		var actual = sut.toResponse(mockException);

		// assert
		assertEquals(500, actual.getStatus());
	}
}