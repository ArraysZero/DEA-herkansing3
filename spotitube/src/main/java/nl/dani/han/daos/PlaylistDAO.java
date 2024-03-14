package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Inject;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.*;
import nl.dani.han.exceptions.DataAccessException;

public class PlaylistDAO{

	@Inject
	private DataAccess dataAccess;
	@Inject
	private TrackDAO trackDAO;


	public PlaylistListDataDTO getPlaylists() throws DataAccessException {

		try (Connection connection = dataAccess.connect()) {
//			PlayListListDTO resultList = new PlayListListDTO();
			PlaylistListDataDTO resultList = new PlaylistListDataDTO(new ArrayList<>());
//			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM playlist";
			PreparedStatement stmt = connection.prepareStatement(sql);
			var result = stmt.executeQuery();
			while (result.next()) {
				resultList.getPlaylists().add(
						new PlaylistDataDTO(result.getInt("id"), result.getString("name"), result.getString("owner"),
								getTracks(result.getInt("id"))));
			}
			return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public PlaylistDataDTO getPlaylistById(int id) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM playlist WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			var result = stmt.executeQuery();
			if (result.next()) {
				return new PlaylistDataDTO(id, result.getString("name"), result.getString("owner"), getTracks(id));
			} else {
				return null;
			}
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public TrackListDTO getTracks(int id) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			var resultList = new TrackListDTO();
			resultList.setTracks(new ArrayList<>());
			String sql = "SELECT * FROM trackOnPlaylist WHERE playlistid = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			var result = stmt.executeQuery();
			while (result.next()) {
				resultList.getTracks()
						.add(trackDAO.getTrack(result.getInt("trackid")));
			}
			return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public boolean deletePlaylist(int id) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "DELETE FROM playlist WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			return stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public boolean addPlaylist(PlayListDTO playList, String owner) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "INSERT INTO playlist (id, name, owner) VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playList.getId());
			stmt.setString(2, playList.getName());
			stmt.setString(3, owner);
			return stmt.execute();

		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public boolean addTrackToPlaylist(int playlist, int track) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "INSERT INTO trackOnPlaylist (playlistid, trackid) VALUES (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playlist);
			stmt.setInt(2, track);
			return stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public boolean changePlaylistName(int playlist, String name) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "UPDATE playlist SET name = ? WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(2, playlist);
			stmt.setString(1, name);
			return stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public boolean deleteTrackFromPlaylist(int playlist, int track) throws DataAccessException {
		try (Connection connection = dataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "DELETE FROM trackOnPlaylist WHERE playlistid = ? AND trackid = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playlist);
			stmt.setInt(2, track);
			return stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public void setTrackDAO(TrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}
}