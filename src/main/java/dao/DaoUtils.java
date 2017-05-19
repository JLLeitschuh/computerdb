package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.DTOException;

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

	public static void autoCommit(Connection connect) throws DTOException{
		try {
			connect.commit();
		} catch (SQLException e) {
			throw new DTOException("autocommit failed "+e.getMessage());
		}
	}
	public static void closeConnection(Connection connect) throws DTOException {
		try {
			connect.close();
		} catch (SQLException e) {
			throw new DTOException("close failed "+e.getMessage());
		}
	}

	public static void rollback(Connection connect) throws DTOException {

		try {
			connect.rollback();
		} catch (SQLException e) {
			throw new DTOException("rollback failed "+e.getMessage());
		}
	}

}
