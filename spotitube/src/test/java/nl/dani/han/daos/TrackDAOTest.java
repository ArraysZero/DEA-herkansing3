package nl.dani.han.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.TrackException;

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

	@Test
	public void getAllTracksTestSucceeds() throws DataAccessException {
		// arrange
		// set data

		// act
		// var actual = sut.getAllTracks();

		// // assert
		// assertNotEquals(0, actual.getTracks().size());
	}

	@Test
	public void getTrackIdTestSucceeds() throws DataAccessException {
		// arrange
		// set data

		// act
		// var actual = sut.getTrackId(0);

		// // assert
		// assertNotEquals(0, actual.getId());
	}

	@Test
	public void getTrackIdTestNull() throws DataAccessException {
		// arrange
		// set data

		// act
		// var actual = sut.getTrackId(0);

		// // assert
		// assertNull(actual);
	}

	@Test
	public void compareListsTestBaselargerThanCompare() {
		// arrange
		var track1 = new TrackDTO(0, null, null, 0, null, 0, null, null, false);
		var track2 = new TrackDTO(1, null, null, 0, null, 0, null, null, false);
		var trackList1 = new TrackListDTO(new ArrayList<>());
		var trackList2 = new TrackListDTO(new ArrayList<>());
		trackList1.getTracks().add(track1);
		trackList1.getTracks().add(track2);
		trackList2.getTracks().add(track1);

		// act
		var actual = sut.compareLists(trackList1, trackList2);

		// assert
		assertEquals(track2, actual.getTracks().get(0));
		assertEquals(1, actual.getTracks().size());
	}

	@Test
	public void compareListsTestEqualLists() {
		// arrange
		var track1 = new TrackDTO(0, null, null, 0, null, 0, null, null, false);
		var track2 = new TrackDTO(1, null, null, 0, null, 0, null, null, false);
		var trackList1 = new TrackListDTO(new ArrayList<>());
		var trackList2 = new TrackListDTO(new ArrayList<>());
		trackList1.getTracks().add(track1);
		trackList1.getTracks().add(track2);
		trackList2.getTracks().add(track1);
		trackList2.getTracks().add(track2);

		// act
		var actual = sut.compareLists(trackList1, trackList2);

		// assert
		assertEquals(0, actual.getTracks().size());
	}

	@Test
	public void compareListsTestComparedLargerThanBase() {
		// arrange
		var track1 = new TrackDTO(0, null, null, 0, null, 0, null, null, false);
		var track2 = new TrackDTO(1, null, null, 0, null, 0, null, null, false);
		var trackList1 = new TrackListDTO(new ArrayList<>());
		var trackList2 = new TrackListDTO(new ArrayList<>());
		trackList1.getTracks().add(track1);
		trackList2.getTracks().add(track1);
		trackList2.getTracks().add(track2);

		// act
		var actual = sut.compareLists(trackList1, trackList2);

		// assert
		assertEquals(0, actual.getTracks().size());
	}
}