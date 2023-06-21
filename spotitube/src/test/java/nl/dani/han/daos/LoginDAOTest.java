package nl.dani.han.daos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class LoginDAOTest {

	LoginDAO sut;

	@BeforeEach
	void setup() {
		sut = new LoginDAO();
	}

	@AfterEach
	void shut() {
		// nog niets
	}
}