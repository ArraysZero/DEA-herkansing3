package nl.dani.han.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import nl.dani.han.database.DataAccess;

@Path("/health")
public class HealthResource {

	@GET
	public Response healthCheck() {

		try (Connection connection = new DataAccess().connect()) {
			createBatch(connection);
			dataBatch(connection);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.status(Response.Status.OK).entity("nieuwe").build();
	}

	private ArrayList<String> readFromInputStream(InputStream inputStream)
			throws IOException {
		ArrayList<String> batch = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				batch.add(line);
			}
		}
		return batch;
	}

	private void createBatch(Connection connection) throws SQLException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("create.sql");
		var batch = readFromInputStream(inputStream);

		var stmt = connection.createStatement();

		for (int i = 0; i < batch.size(); i++) {
			stmt.addBatch(batch.get(i));
		}

		stmt.executeBatch();
	}

	private void dataBatch(Connection connection) throws IOException, SQLException {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("data.sql");
		var batch = readFromInputStream(inputStream);

		var stmt = connection.createStatement();

		for (int i = 0; i < batch.size(); i++) {
			stmt.addBatch(batch.get(i));
		}

		stmt.executeBatch();
	}
}