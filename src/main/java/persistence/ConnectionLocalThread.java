package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class ConnectionLocalThread {

	private static final ThreadLocal<Connection> thread = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			try {
				HikariConfig config = new HikariConfig(ConnectionSingleton.DB_PROPERTIES);
				config.setConnectionTimeout(5000);

				DataSource datasource = new HikariDataSource(config);
				return datasource.getConnection();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			return null;
		}
	};


	public static Connection getConnection() {

		return thread.get();
	}

}
