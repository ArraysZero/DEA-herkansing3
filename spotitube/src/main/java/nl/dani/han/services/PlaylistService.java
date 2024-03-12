package nl.dani.han.services;

import javax.inject.Inject;

import nl.dani.han.daos.LoginDAO;
import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.*;
import nl.dani.han.exceptions.DataAccessException;

import java.util.ArrayList;

public class PlaylistService {

	@Inject
	private PlaylistDAO playlistDAO;

	@Inject
	private TrackDAO trackDAO;

	@Inject
	private LoginDAO loginDAO;

	public PlayListListDTO getAllPlaylists(String token) throws DataAccessException {
		var playlists = setOwnerForPlaylistList(token, playlistDAO.getPlaylists());
		playlists.setLength(getLength(playlists));
		return playlists;
	}

	public PlayListListDTO deletePlaylist(String token, int id) throws DataAccessException {
		playlistDAO.deletePlaylist(id);
		return getAllPlaylists(token);
	}

	public PlayListListDTO addPlaylist(String token, PlayListDTO playlist) throws DataAccessException {
		playlist.setId(genId());
		playlistDAO.addPlaylist(playlist, loginDAO.getUserToken(token).getUser());
		return getAllPlaylists(token);
	}

	public PlayListListDTO editPlaylist(String token, PlayListDTO playlist) throws DataAccessException {
		playlistDAO.changePlaylistName(playlist.getId(), playlist.getName());
		return getAllPlaylists(token);
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

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	private PlayListListDTO setOwnerForPlaylistList(String token, PlaylistListDataDTO playlists) throws DataAccessException {
		var playlistlist = new PlayListListDTO(new ArrayList<>(), 0);

		for (int i = 0; i < playlists.getPlaylists().size(); i++) {
			playlistlist.getPlaylists().add(setOwner(token, playlists.getPlaylists().get(i)));
		}

		return playlistlist;
	}

	private PlayListDTO setOwner(String token, PlaylistDataDTO playList) throws DataAccessException {
		return new PlayListDTO(playList.getId(), playList.getName(),
				playList.getOwner().equals(loginDAO.getUserToken(token).getUser()), playList.getTracks());
	}

	private int genId() throws DataAccessException {
		int id;
		do {
			id= (int) (Math.random() * 10000);
		} while (playlistDAO.getPlaylistById(id) != null);

		return id;
	}

	private int getLength(PlayListListDTO playlists) {
		int length = 0;
		for (int i = 0; i < playlists.getPlaylists().size(); i++) {
			for (int j = 0; j < playlists.getPlaylists().get(i).getTracks().getTracks().size(); j++) {
				length += playlists.getPlaylists().get(i).getTracks().getTracks().get(j).getDuration();
			}
		}
		return length;
	}
}