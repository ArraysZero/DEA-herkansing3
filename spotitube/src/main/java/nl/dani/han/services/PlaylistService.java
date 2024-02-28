package nl.dani.han.services;

import javax.inject.Inject;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;
//import nl.dani.han.exceptions.PlaylistException;

public class PlaylistService {

	@Inject
	private PlaylistDAO playlistDAO; // = new PlaylistDAO();

	@Inject
	private TrackDAO trackDAO; // = new TrackDAO();

	@Inject
	private LoginDAO loginDAO;

	public PlayListListDTO getAllPlaylists(String token) throws DataAccessException {
//		return playlistDAO.getAll();
		return setOwnerForPlaylistList(token, playlistDAO.getPlaylists());
	}

	public PlayListListDTO deletePlaylist(String token, int id) throws DataAccessException {
		playlistDAO.deletePlaylist(id);
		return setOwnerForPlaylistList(token, playlistDAO.getPlaylists());
	}

	public PlayListListDTO addPlaylist(String token, PlayListDTO playlist) throws DataAccessException {
		playlist.setId(genId());
		playlistDAO.addPlaylist(playlist, loginDAO.getUserToken(token).getUser());
		return setOwnerForPlaylistList(token, playlistDAO.getPlaylists());
	}

	public PlayListListDTO editPlaylist(String token, PlayListDTO playlist) throws DataAccessException {
		playlistDAO.changePlaylistName(playlist.getId(), playlist.getName());
		return setOwnerForPlaylistList(token, playlistDAO.getPlaylists());
	}

	public TrackListDTO getTrackList(int id) throws DataAccessException {
		return playlistDAO.getTracks(id);
	}

	public TrackListDTO addTrack(int id, TrackDTO track) throws DataAccessException {
		playlistDAO.addTrackToPlaylist(id, track.getId());
		return playlistDAO.getTracks(id);
	}

	public TrackListDTO deleteTrack(int playlist, int track) throws DataAccessException {
		playlistDAO.deleteTrackFromPlaylist(playlist, track);
		return playlistDAO.getTracks(playlist);
	}

	public void setPlaylistDAO(PlaylistDAO playlistDAO) {
		this.playlistDAO = playlistDAO;
	}

	public void setTrackDAO(TrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}

	private PlayListListDTO setOwnerForPlaylistList(String token, PlayListListDTO playlists) throws DataAccessException {
		for (int i = 0; i < playlists.getPlaylists().size(); i++) {
			setOwner(token, playlists.getPlaylists().get(i));
		}

		return playlists;
	}

	private PlayListDTO setOwner(String token, PlayListDTO playList) throws DataAccessException {
		if (playlistDAO.getOwner(playList.getId()).equals(loginDAO.getUserToken(token).getUser())) {
			playList.setOwner(true);
		}

		return playList;
	}

	private int genId() throws DataAccessException {
		int id;
		do {
			id= (int) (Math.random() * 10000);
		} while (playlistDAO.getPlaylistById(id) != null);

		return id;
	}
}