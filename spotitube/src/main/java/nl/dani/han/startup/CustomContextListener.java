package nl.dani.han.startup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import nl.dani.han.database.DataAccess;
import nl.dani.han.dtos.UserDTO;
import nl.dani.han.exceptions.DataAccessException;

@WebListener
public class CustomContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try (var connection = new DataAccess().connect()) {

			createBatch(connection);
			dataBatch(connection);
		} catch (SQLException | IOException e) {
			// do nothing
		}
		// TODO Auto-generated method stub
		ServletContextListener.super.contextInitialized(sce);
	}

	private ArrayList<String> readFromInputStream(InputStream inputStream)
			throws IOException {
		ArrayList<String> batch = new ArrayList<>();
		// StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				batch.add(line);
				// resultStringBuilder.append(line).append("\n");
			}
		}
		return batch;
	}

	private void createBatch(Connection connection) throws SQLException, IOException {
		var stmt = connection.createStatement();

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("create.sql");
		var batch = readFromInputStream(inputStream);

		for (int i = 0; i < batch.size(); i++) {
			stmt.addBatch(batch.get(i));
		}

		stmt.executeBatch();
	}

	private void dataBatch(Connection connection) throws SQLException, IOException {
		var stmt = connection.createStatement();

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("data.sql");
		var batch = readFromInputStream(inputStream);

		for (int i = 0; i < batch.size(); i++) {
			stmt.addBatch(batch.get(i));
		}

		stmt.executeBatch();
	}

}