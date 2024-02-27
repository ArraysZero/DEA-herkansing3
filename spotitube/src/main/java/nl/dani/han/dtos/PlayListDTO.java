package nl.dani.han.dtos;

import java.util.List;

public class PlayListDTO extends DataTransferObject {
	private int id;
	private String name;
	private boolean owner;
	private TrackListDTO tracks;

	public PlayListDTO() {
	}

	public PlayListDTO(int id, String name, boolean owner, TrackListDTO tracks) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.tracks = tracks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public TrackListDTO getTracks() {
		return tracks;
	}

	public void setTracks(TrackListDTO tracks) {
		this.tracks = tracks;
	}

}