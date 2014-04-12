package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionImpl implements DBConnection {
	private String database, user, pass;

	public DBConnectionImpl(String database, String user, String pass) {

		this.database = database;
		this.user = user;
		this.pass = pass;

	}

	@Override
	public Connection connectToDatabaseOrDie() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/" + database;
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			Logger logger = Logger.getLogger(AmigoDataAccessObjectImpl.class
					.getName());
			logger.log(Level.SEVERE, e.getMessage(), e);
			System.exit(1);
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(AmigoDataAccessObjectImpl.class
					.getName());
			logger.log(Level.SEVERE, e.getMessage(), e);
			System.exit(2);
		}
		return conn;
	}
}
