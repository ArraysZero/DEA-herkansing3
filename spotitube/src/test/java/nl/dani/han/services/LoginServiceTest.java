package nl.dani.han.services;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nl.dani.han.daos.LoginDAO;

public class LoginServiceTest {

	@Mock
	LoginDAO mockLoginDAO;

	@InjectMocks
	LoginService sut;

	@BeforeEach
	void setup() {
		mockLoginDAO = mock(LoginDAO.class);

		sut = new LoginService();
	}

	@AfterEach
	void shut() {
		// nog niets
	}
}