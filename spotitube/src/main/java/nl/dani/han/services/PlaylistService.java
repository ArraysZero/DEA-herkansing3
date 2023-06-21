package nl.dani.han.services;

import javax.inject.Inject;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.daos.TrackDAO;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.PlaylistException;

public class PlaylistService {

	@Inject
	PlaylistDAO playlistDAO = new PlaylistDAO();

	@Inject
	TrackDAO trackDAO = new TrackDAO();

	public PlayListListDTO getAllPlaylists() throws PlaylistException {
		return playlistDAO.getAllPlaylists();
	}

	public PlayListListDTO deletePlaylist(int id) throws PlaylistException {
		playlistDAO.deletePlaylist(id);
		return playlistDAO.getAllPlaylists();
	}

	public PlayListListDTO addPlaylist(PlayListDTO playlist) throws PlaylistException {
		playlistDAO.addPlaylist(playlist);
		return playlistDAO.getAllPlaylists();
	}

	public PlayListListDTO editPlaylist(PlayListDTO playlist) throws PlaylistException {
		playlistDAO.changePlaylistName(playlist.getId(), playlist.getName());
		return playlistDAO.getAllPlaylists();
	}

	public TrackListDTO getTrackList(int id) throws PlaylistException {
		return playlistDAO.getTracks(id);
	}

	public TrackListDTO addTrack(int id, TrackDTO track) throws PlaylistException {
		playlistDAO.addTrackToPlaylist(id, track.getId());
		return playlistDAO.getTracks(id);
	}

	public TrackListDTO deleteTrack(int playlist, int track) throws PlaylistException {
		playlistDAO.deleteTrackFromPlaylist(playlist, track);
		return playlistDAO.getTracks(playlist);
	}
}