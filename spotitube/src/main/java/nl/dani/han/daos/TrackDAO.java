package nl.dani.han.daos;

import java.util.ArrayList;
import java.util.List;

import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;

public class TrackDAO {
	public TrackListDTO getAllTracks() { // TODO implement
		ArrayList<TrackDTO> tracks = new ArrayList<>();
		tracks.add(new TrackDTO(1, "cant be touched", "Roy Jones", 400, "unknown", 1, "unknows", "hip hop", false));
		return new TrackListDTO(tracks);
	}

	public TrackDTO getTrackId(int id) {
		return new TrackDTO(id, "cant be touched", "Roy Jones", 400, "unknown", 1, "unknows", "hip hop", false); // TODO

		// TODO implement
	}
}