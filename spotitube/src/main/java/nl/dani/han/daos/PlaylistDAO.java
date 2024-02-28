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
	private TrackDAO trackDAO;

	public PlayListListDTO getPlaylists() throws DataAccessException {

		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM playlist";
			PreparedStatement stmt = connection.prepareStatement(sql);
			var result = stmt.executeQuery();
			while (result.next()) {
				resultList.getPlaylists().add(
						new PlayListDTO(result.getInt("id"), result.getString("name"), false,
								getTracks(result.getInt("id"))));
			}
			return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public PlayListDTO getPlaylistById(int id) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM playlist WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			var result = stmt.executeQuery();
			if (result.next()) {
				return new PlayListDTO(id, result.getString("name"), false, getTracks(id));
			} else {
				return null;
			}
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public TrackListDTO getTracks(int id) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
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

	public void deletePlaylist(int id) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "DELETE FROM playlist WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void addPlaylist(PlayListDTO playList, String owner) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "INSERT INTO playlist (id, name, owner) VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playList.getId());
			stmt.setString(2, playList.getName());
			stmt.setString(3, owner);
			stmt.execute();

		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void addTrackToPlaylist(int playlist, int track) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "INSERT INTO trackOnPlaylist (playlistid, trackid) VALUES (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playlist);
			stmt.setInt(2, track);
			stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void changePlaylistName(int playlist, String name) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "UPDATE playlist SET name = ? WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(2, playlist);
			stmt.setString(1, name);
			stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void deleteTrackFromPlaylist(int playlist, int track) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "DELETE FROM trackOnPlaylist WHERE playlistid = ? AND trackid = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playlist);
			stmt.setInt(2, track);
			stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public String getOwner(int id) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM playlist WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			var result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("owner");
			} else {
				return null;
			}
			// return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}
}