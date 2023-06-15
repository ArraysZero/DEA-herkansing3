package nl.dani.han.services;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.PlaylistException;

public class PlaylistService {

	PlaylistDAO playlistDAO = new PlaylistDAO();

	public PlayListListDTO getAllPlaylists() throws PlaylistException {
		return playlistDAO.getAllPlaylists();
	}

	public PlayListListDTO deletePlaylist(int id) throws PlaylistException { // TODO: implement
		return playlistDAO.getAllPlaylists();
	}

	public PlayListListDTO addPlaylist(PlayListDTO playlist) throws PlaylistException { // TODO implement
		return playlistDAO.getAllPlaylists();
	}

	public PlayListListDTO editPlaylist(int id, PlayListDTO playlist) throws PlaylistException { // TODO implement
		return playlistDAO.getAllPlaylists();
	}

	public TrackListDTO getTrackList(int id) throws PlaylistException { // TODO implement
		return playlistDAO.getTracks(id);
	}
}