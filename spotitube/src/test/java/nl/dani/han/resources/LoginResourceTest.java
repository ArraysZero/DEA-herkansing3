package nl.dani.han.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.dtos.LoginDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;
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
		sut.setLoginService(mockLoginService);
	}

	@AfterEach
	public void shut() {
		// nog niets
	}

	@Test
	public void loginSucceedsTest() throws LoginException, DataAccessException {
		// arrange
		UserDTO mockUser = new UserDTO(TESTUSER, TESTPASS);
		LoginDTO expected = new LoginDTO(TESTTOKEN, TESTUSER);
		when(mockLoginService.login(mockUser)).thenReturn(expected);

		// act
		var actual = sut.login(mockUser);

		// assert
		assertEquals(200, actual.getStatus());
		assertEquals(expected, actual.getEntity());
	}

	@Test
	public void loginUnhappyLoginException() throws LoginException, DataAccessException {
		// arrange
		UserDTO mockUser = new UserDTO(TESTUSER, TESTPASS);
		LoginDTO expected = new LoginDTO(TESTTOKEN, TESTUSER);
		when(mockLoginService.login(mockUser)).thenThrow(new LoginException("username or password incorrect"));

		// act

		// assert
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.login(mockUser));
	}

	@Test
	public void loginUnhappyDataAccessException() throws LoginException, DataAccessException {
		// arrange
		UserDTO mockUser = new UserDTO(TESTUSER, TESTPASS);
		LoginDTO expected = new LoginDTO(TESTTOKEN, TESTUSER);
		when(mockLoginService.login(mockUser)).thenThrow(new DataAccessException(""));

		// act

		// assert
		Exception exception = assertThrows(DataAccessException.class, ()
				-> sut.login(mockUser));
	}
}