package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;
import nl.dani.han.exceptions.PlaylistException;

public class PlaylistDAO {

	@Inject
	private TrackDAO trackDAO;

	public PlayListListDTO getAllPlaylists() throws PlaylistException, DataAccessException {

		try (Connection connection = DataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM playlist";
			PreparedStatement stmt = connection.prepareStatement(sql);
			var result = stmt.executeQuery();
			while (result.next()) {
				resultList.getPlaylists().add(
						new PlayListDTO(result.getInt("id"), result.getString("name"), false,
								getTracks(result.getInt("id")))); // TODO check if owner
			}
			return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public PlayListDTO getPlaylistId(int id) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
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
			// return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public TrackListDTO getTracks(int id) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
			var resultList = new TrackListDTO();
			resultList.setTracks(new ArrayList<>());
			String sql = "SELECT * FROM trackOnPlaylist WHERE playlistid = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			var result = stmt.executeQuery();
			while (result.next()) {
				resultList.getTracks()
						.add(trackDAO.getTrackId(result.getInt("trackid")));
			}
			return resultList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void deletePlaylist(int id) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
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

	public void addPlaylist(PlayListDTO playList) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "INSERT INTO playlist (id, name, owner) VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, playList.getId());
			stmt.setString(2, playList.getName());
			stmt.setString(3, "owner");
			stmt.execute();

			for (int i = 0; i < playList.getTracks().getTracks().size(); i++) {
				addTrackToPlaylist(playList.getId(), playList.getTracks().getTracks().get(i).getId());
			}
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void addTrackToPlaylist(int playlist, int track) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
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

	public void changePlaylistName(int playlist, String name) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
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

	public void deleteTrackFromPlaylist(int playlist, int track) throws PlaylistException, DataAccessException {
		try (Connection connection = DataAccess.connect()) {
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
}