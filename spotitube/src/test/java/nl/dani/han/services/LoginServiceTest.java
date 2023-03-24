package nl.dani.han.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.dani.han.daos.DataAccessException;
import nl.dani.han.daos.TokenDAO;
import nl.dani.han.daos.UserDAO;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.ServiceException;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
	private final String DUMMY_TOKEN = "dummy_token-123";
	private final String DUMMY_USER = "dummy_user";
	private final String DUMMY_PASSWORD = "dummy_pass";

	@Mock
	UserDAO userDAO;

	@Mock
	TokenDAO tokenDAO;

	@InjectMocks
	LoginService sut; // system under test

	@BeforeEach
	void setup() {

	}

	@Test
	void loginSucceeds() throws ServiceException, DataAccessException {

		when(userDAO.userExists(anyString(), anyString())).thenReturn(true);
		when(tokenDAO.generateToken(anyString())).thenReturn(DUMMY_TOKEN);

		String returnVal = sut.login(DUMMY_USER, DUMMY_PASSWORD);

		assertEquals(DUMMY_TOKEN, returnVal);
	}

	@Test
	void loginFails() throws DataAccessException {
		when(userDAO.userExists(anyString(), anyString())).thenReturn(false);

		Exception exception = assertThrows(ServiceException.class, ()
				-> sut.login(DUMMY_USER, DUMMY_PASSWORD));

		assertEquals("password or username incorrect", exception.getMessage());
	}
}