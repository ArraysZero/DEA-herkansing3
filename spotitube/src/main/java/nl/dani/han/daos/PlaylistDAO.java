package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.PlayListDTO;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.PlaylistException;

public class PlaylistDAO {
	TrackDAO trackDAO = new TrackDAO();

	public PlayListListDTO getAllPlaylists() throws PlaylistException {

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
			throw new PlaylistException(e.getCause());
		}
	}

	public PlayListDTO getPlaylistId(int id) throws PlaylistException {
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
				throw new PlaylistException("playlist does not exist");
			}
			// return resultList;
		} catch (SQLException | IOException e) {
			throw new PlaylistException(e.getCause());
		}
	}

	public TrackListDTO getTracks(int id) throws PlaylistException {
		try (Connection connection = DataAccess.connect()) {
			var resultList = new TrackListDTO();
			resultList.setTracks(new ArrayList<>());
			String sql = "SELECT * FROM trackOnPlaylist WHERE playlist = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			var result = stmt.executeQuery();
			while (result.next()) {
				resultList.getTracks()
						.add(new TrackDTO(result.getInt("id"), result.getString("title"), result.getString("performer"),
								result.getInt("duration"), result.getString("album"), result.getInt("playcount"),
								result.getString("plucationDate"),
								result.getString("description"), result.getBoolean("offline")));
			}
			return resultList;
		} catch (SQLException | IOException e) {
			throw new PlaylistException(e.getCause());
		}
	}

	public void deletePlaylist(int id) throws PlaylistException {
		try (Connection connection = DataAccess.connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "DELETE FROM playlist WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeQuery();
		} catch (SQLException | IOException e) {
			throw new PlaylistException(e.getCause());
		}
	}
}