package nl.dani.han.services;

import javax.inject.Inject;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
//import nl.dani.han.exceptions.PlaylistException;


public class TrackService {

	@Inject
	private PlaylistDAO playlistDAO;

	@Inject
	private TrackDAO trackDAO;

	public TrackListDTO getAvailableTracks(int playlistId) throws DataAccessException {

		var playlistTracks = playlistDAO.getTracks(playlistId);
		var allTracks = trackDAO.getTracks();
		return trackDAO.compareLists(allTracks, playlistTracks);
	}

	public void setPlaylistDAO(PlaylistDAO playlistDAO) {
		this.playlistDAO = playlistDAO;
	}

	public void setTrackDAO(TrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}
}