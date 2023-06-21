package nl.dani.han.daos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TrackDAOTest {

	TrackDAO sut;

	@BeforeEach
	void setup() {
		sut = new TrackDAO();
	}

	@AfterEach
	void shut() {
		// nog niets
	}
}