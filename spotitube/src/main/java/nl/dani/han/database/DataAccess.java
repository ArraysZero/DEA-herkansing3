package nl.dani.han.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import nl.dani.han.App;
import nl.dani.han.daos.DataInterface;
import nl.dani.han.exceptions.DataAccessException;

public class DataAccess implements DataInterface {

	@Override
	public Connection connect() throws SQLException, IOException, DataAccessException {
		Properties prop = new Properties();
		prop.load(App.class.getClassLoader().getResourceAsStream("database.properties"));
		String URL = prop.getProperty("jdbc.url");
		String user = prop.getProperty("user");
		String passwd = prop.getProperty("pass");
		try {
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException(e.getMessage());
		}

	}

}