package nl.dani.han.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.dtos.LoginDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.LoginException;

public class LoginServiceTest {

	// mocks
	UserDTO mockUser;
	LoginDTO mockLogin;

	@Mock
	LoginDAO mockLoginDAO;

	@InjectMocks
	LoginService sut;

	@BeforeEach
	void setup() {
		mockLoginDAO = mock(LoginDAO.class);

		sut = new LoginService();
		sut.setLoginDAO(mockLoginDAO);

		mockUser = mock(UserDTO.class);
		mockLogin = mock(LoginDTO.class);
	}

	@AfterEach
	void shut() {
		// nog niets
	}

	@Test
	public void loginTestSucceeds() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginDAO.getUser(mockUser)).thenReturn(mockUser);

		// act
		var actual = sut.login(mockUser);

		// assert
		assertEquals(mockLogin.getUser(), actual.getUser());
	}

	@Test
	public void loginTestLoginException() throws LoginException, DataAccessException {
		// arrange
		when(mockLoginDAO.getUser(mockUser)).thenReturn(null);

		// act
		Exception exception = assertThrows(LoginException.class, ()
				-> sut.login(mockUser));
	}

	@Test
	public void tokenExistsTestSucceeds() throws LoginException, DataAccessException {
		// arrange

		// act
		var actual = sut.tokenExists("");

		// assert
	}
}