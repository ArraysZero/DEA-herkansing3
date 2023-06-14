package nl.dani.han.services;

import nl.dani.han.daos.PlaylistDAO;
import nl.dani.han.dtos.PlayListListDTO;

public class PlaylistService {

	PlaylistDAO playlistDAO = new PlaylistDAO();

	public PlayListListDTO getAllPlaylists() {
		return playlistDAO.getAllPlaylists();
	}
}