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

	// public DataAccess() {
	// Properties prop = new Properties();
	// try {
	// prop.load(App.class.getClassLoader().getResourceAsStream("database.properties"));
	// URL = prop.getProperty("url");
	// user = prop.getProperty("user");
	// passwd = prop.getProperty("pass");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static Connection connect() throws SQLException, IOException {
		Properties prop = new Properties();
		prop.load(App.class.getClassLoader().getResourceAsStream("database.properties"));
		String URL = prop.getProperty("url");
		String user = prop.getProperty("user");
		String passwd = prop.getProperty("pass");
		return DriverManager.getConnection(URL);

	}

}