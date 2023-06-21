package nl.dani.han.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.dtos.LoginDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.LoginException;
import nl.dani.han.services.LoginService;

public class LoginResourceTest {

	@Mock
	LoginService mockLoginService;

	@InjectMocks
	private LoginResource sut;

	private final String TESTUSER = "TESTUSER";
	private final String TESTPASS = "TESTPASS";
	private final String TESTTOKEN = "TESTTOKEN";

	@BeforeEach
	public void setup() {
		mockLoginService = mock(LoginService.class);
		sut = new LoginResource();
	}

	@AfterEach
	public void clsoe() {
		// nog niets
	}

	@Test
	public void loginSucceedsTest() throws LoginException {
		// arrange
		UserDTO mockUser = new UserDTO(TESTUSER, TESTPASS);
		LoginDTO expected = new LoginDTO(TESTTOKEN, TESTUSER);
		when(mockLoginService.login(mockUser)).thenReturn(expected);

		// act
		// var actual = sut.login(mockUser);

		// assert
		// assertEquals(200, actual.getStatus());
		// assertEquals(expected, actual.getEntity());
	}

	@Test
	public void loginFailsTest() throws LoginException {
		// arrange
		var mockUser = mock(UserDTO.class);
		var expected = mock(LoginException.class);

		when(mockLoginService.login(mockUser)).thenThrow(expected);

		// act
		// var actual = sut.login(mockUser);

		// assert
		// assertEquals(500, actual.getStatus());
		// assertEquals(expected.getMessage(), actual.getEntity());
	}
}