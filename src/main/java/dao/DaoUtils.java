package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DaoUtils {

	/**
	 * close all statement and resultSet.
	 * @param preparedStatement .
	 * @param resultSet .
	 * @param statement .
	 */
	public static void close(PreparedStatement preparedStatement, ResultSet resultSet, Statement statement) {

		try {
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			if (resultSet != null) {
				resultSet.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
