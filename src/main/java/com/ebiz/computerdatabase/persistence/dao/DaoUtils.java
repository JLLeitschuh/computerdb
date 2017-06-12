package com.ebiz.computerdatabase.persistence.dao;



import com.ebiz.computerdatabase.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUtils {

	/**
	 * close all statement and resultSet.
	 * @param resultSet .
	 * @param statement .
	 */
	public static void close(ResultSet resultSet, Statement statement) {

		try {
			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {

			if (resultSet != null) {
				resultSet.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void autoCommit(Connection connect, boolean autoCommit) throws DAOException {
		try {
			connect.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			throw new DAOException("autocommit failed " + e.getMessage());
		}
	}

	public static void commit(Connection connect) {
		try {
			connect.commit();
			connect.setAutoCommit(true);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void closeConnection(Connection connect) {
		try {
			
			if (connect.getAutoCommit()) {
				System.out.println("Connection :Close Connection ");
				connect.close();

			}else{
				System.out.println("Connection :don't close ");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void rollback(Connection connect) {

		try {
			connect.rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
