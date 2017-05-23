package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import exception.DTOException;

public class ConnectionSingleton {

	public static final String DB_PROPERTIES = "/db.properties";

	// generate singleton which is loaded at the begining
	private static ConnectionSingleton instance = new ConnectionSingleton();
	private static final ThreadLocal<Connection> THREAD = new ThreadLocal<Connection>();

	private DataSource datasource;

	/**
	 * Singleton constructor.
	 */
	private ConnectionSingleton() {

		HikariConfig config = new HikariConfig(DB_PROPERTIES);
		config.setConnectionTimeout(5000);

		datasource = new HikariDataSource(config);

	}

	public static ConnectionSingleton getInstance() {
		return instance;
	}

	/**
	 * get Hikari connection.
	 * @return connection
	 * @throws DTOException .
	 */
	public Connection getConnection() throws DTOException {
		/*try {
			if (THREAD.get() == null) {
				THREAD.set(datasource.getConnection());
			}
		} catch (SQLException e1) {
			throw new DTOException("Connection failed " + e1.getMessage());
		}

		return THREAD.get();*/
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			throw new DTOException("Connection failed " + e.getMessage());
		}
	}

}
