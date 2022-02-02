package nl.han.dani.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import nl.han.dani.exceptions.DatabaseException;

public class DataConn{
	private static final String PROPERTY_LOCATION = "/database.properties";

	public Connection getConnection() throws DatabaseException {
		try {
			var properties = loadProperties();
			Class.forName(properties.getProperty("db.driver")); //com.mysql.cj.jdbc.Driver
			return DriverManager.getConnection(properties.getProperty("db.url"),
					properties.getProperty("db.user"), properties.getProperty("db.password"));
		} catch (ClassNotFoundException | SQLException e) {
			throw new DatabaseException(e);
		}
	}

	private Properties loadProperties() throws DatabaseException {
		var properties = new Properties();
		try (
				var inputStream = this.getClass().getResourceAsStream(PROPERTY_LOCATION)
		) {
			properties.load(inputStream);
		} catch (Exception e) {
			throw new DatabaseException("could not load properties");
		}
		return properties;
	}
}
