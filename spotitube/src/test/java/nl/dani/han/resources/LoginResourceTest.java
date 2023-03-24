package nl.dani.han.resources;

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
import nl.dani.han.requests.LoginRequest;
import nl.dani.han.services.LoginService;
import nl.dani.han.services.ServiceException;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {
	private final String DUMMY_TOKEN = "dummy_token-123";
	private final String DUMMY_USER = "dummy_user";
	private final String DUMMY_PASSWORD = "dummy_pass";

	private LoginRequest request;

	@Mock
	LoginService loginService;

	// @InjectMocks
	LoginResource loginResource;

	@BeforeEach
	void setUp() {
		loginResource = new LoginResource();
		request = new LoginRequest();
		request.user = DUMMY_USER;
		request.password = DUMMY_PASSWORD;
	}

	@Test
	void loginSucceeds() throws ServiceException, DataAccessException {
		when(loginService.login(anyString(), anyString())).thenReturn(DUMMY_TOKEN);

		Response response = loginResource.login(request);

		response.getStatus();
		assertEquals(200, response.getStatus());
		assertEquals(DUMMY_TOKEN, response.getEntity().toString());
	}

	@Test
	void loginResultsInto405() throws ServiceException, DataAccessException {
		when(loginService.login(anyString(), anyString())).thenThrow(new
		ServiceException("password or username incorrect"));

		Response response = loginResource.login(request);

		response.getStatus();
		assertEquals(401, response.getStatus());
		assertEquals("password or username incorrect",
		response.getEntity().toString());
	}
}