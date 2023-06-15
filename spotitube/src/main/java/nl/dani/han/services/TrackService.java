package nl.dani.han.services;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.TrackException;

public class TrackService {

	private PlaylistDAO playlistDAO = new PlaylistDAO();
	private TrackDAO trackDAO = new TrackDAO();

	public TrackListDTO getAvailableTracks(int playlistId) throws TrackException {
		var playlistTracks = playlistDAO.getTracks(playlistId);
		var allTracks = trackDAO.getAllTracks();
		return trackDAO.compareLists(allTracks, playlistTracks);
	}
}