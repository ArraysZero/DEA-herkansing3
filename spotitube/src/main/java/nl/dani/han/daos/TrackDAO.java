package nl.dani.han.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.PlayListListDTO;
import nl.dani.han.dtos.TrackDTO;
import nl.dani.han.dtos.TrackListDTO;
import nl.dani.han.exceptions.DataAccessException;

public class TrackDAO {

	public TrackListDTO getTracks() throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM Track";
			PreparedStatement stmt = connection.prepareStatement(sql);
			var result = stmt.executeQuery();

			TrackListDTO trackList = new TrackListDTO(new ArrayList<>());
			while (result.next()) {
				trackList.getTracks().add(new TrackDTO(result.getInt("id"),
						result.getString("title"),
						result.getString("performer"),
						result.getInt("duration"),
						result.getString("album"),
						result.getInt("playcount"),
						result.getString("publicationDate"),
						result.getString("description"),
						result.getBoolean("offlineAvailable")));
			}
			return trackList;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public TrackDTO getTrack(int id) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "SELECT * FROM Track WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			var result = stmt.executeQuery();

			TrackDTO track = null;
			while (result.next()) {
				track = new TrackDTO(result.getInt("id"),
						result.getString("title"),
						result.getString("performer"),
						result.getInt("duration"),
						result.getString("album"),
						result.getInt("playcount"),
						result.getString("publicationDate"),
						result.getString("description"),
						result.getBoolean("offlineAvailable"));
			}
			return track;
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public void deleteTrack(int id) throws DataAccessException {
		try (Connection connection = new DataAccess().connect()) {
			PlayListListDTO resultList = new PlayListListDTO();
			resultList.setPlaylists(new ArrayList<>());
			String sql = "DELETE FROM track WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException | IOException e) {
			throw new DataAccessException(e.getMessage());
		}
	}

	public TrackListDTO compareLists(TrackListDTO base, TrackListDTO compared) {
		ArrayList<TrackDTO> tracks = new ArrayList<>();
		for (int i = base.getTracks().size() - 1; i >= 0; i--) {
			boolean inCompared = false;
			for (int n = 0; n < compared.getTracks().size(); n++) {
				if (base.getTracks().get(i).equals(compared.getTracks().get(n))) {
					inCompared = true;
					break;
				}
			}
			if (!inCompared) {
				tracks.add(base.getTracks().get(i));
			}
		}

		return new TrackListDTO(tracks);
	}
}