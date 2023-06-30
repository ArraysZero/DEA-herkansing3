package nl.dani.han.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import nl.dani.han.App;

public class DataAccess {
	private String URL;
	private String user;
	private String passwd;

	public static Connection connect() throws SQLException, IOException {
		Properties prop = new Properties();
		prop.load(App.class.getClassLoader().getResourceAsStream("database.properties"));
		String URL = prop.getProperty("jdbc.url");
		String user = prop.getProperty("user");
		String passwd = prop.getProperty("pass");
		try {
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}