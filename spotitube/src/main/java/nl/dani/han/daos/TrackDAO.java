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

	public TrackListDTO compareLists(TrackListDTO base, TrackListDTO compared) {
		ArrayList<TrackDTO> tracks = new ArrayList<>();
		for (int i = base.getTracks().size() - 1; i >= 0; i--) {
			boolean inCompared = false;
			for (int n = 0; n < compared.getTracks().size(); n++) {
				if (base.getTracks().get(i).equals(compared.getTracks().get(n))) {
					inCompared = true;
					break;
				}
			}
			if (!inCompared) {
				tracks.add(base.getTracks().get(i));
			}
		}

		return new TrackListDTO(tracks);
	}
}