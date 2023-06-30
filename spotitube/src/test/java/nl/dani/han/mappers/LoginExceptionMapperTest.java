package nl.dani.han.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import nl.dani.han.exceptions.LoginException;
import nl.dani.han.exceptions.mappers.LoginExceptionMapper;

public class LoginExceptionMapperTest {

	LoginException mockException = mock(LoginException.class);

	LoginExceptionMapper sut = new LoginExceptionMapper();

	@Test
	public void toResponseTest() {
		var actual = sut.toResponse(mockException);

		assertEquals(401, actual.getStatus());
	
	}
}