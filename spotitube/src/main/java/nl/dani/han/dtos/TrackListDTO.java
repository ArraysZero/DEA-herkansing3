package nl.dani.han.dtos;

import java.util.List;

public class TrackListDTO extends DataTransferObject {
	private List<TrackDTO> tracks;

	public TrackListDTO() {
	}

	public TrackListDTO(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}

}