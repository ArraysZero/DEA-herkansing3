package nl.dani.han.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

class DatabaseConnection {
	private final String URL;
	private final String USER;
	private final String PASSWORD;
	private final String DRIVER;

	public DatabaseConnection() throws IOException {

		InputStream in = getClass().getResourceAsStream("/database.properties");
		Properties properties = new Properties();
		properties.load(in);
		in.close();
		this.URL = properties.getProperty("jdbc.url");
		this.USER = properties.getProperty("jdbc.username");
		this.PASSWORD = properties.getProperty("jdbc.password");
		this.DRIVER = properties.getProperty("jdbc.driverClassName");
	}

	public Connection getConnection() throws SQLException {

		try {
			Class.forName(this.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
	}
}