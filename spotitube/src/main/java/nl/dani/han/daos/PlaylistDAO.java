package nl.dani.han.daos;

import java.util.ArrayList;
import java.util.List;

import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;

public class PlaylistDAO {

	public PlayListListDTO getAllPlaylists() {
		ArrayList<PlayListDTO> playlists = new ArrayList<>();
		playlists.add(getPlaylistId(0));
		return new PlayListListDTO(playlists, 0); // TODO implement
	}

	public PlayListDTO getPlaylistId(int id) {
		return new PlayListDTO(id, "mijn epische playlist", false, getTracks());
	}

	private List<TrackDTO> getTracks() { // moet een lijst van id's krijgen na der hand
		var trackAccess = new TrackDAO();

		return trackAccess.getAllTracks(); // TODO implement
	}
}