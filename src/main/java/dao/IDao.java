package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import persistance.MySQLConnectionSingleton;

public abstract class IDao<T> {

	protected Statement statement = null;
	protected ResultSet resultSet = null;
	protected PreparedStatement preparedStatement = null;
	protected Connection connect = MySQLConnectionSingleton.getInstance().getConnection();

	/**
	 * get Object with id ID.
	 * @param id object id to find
	 * @return T object
	 */
	public abstract T find(int id);

	/**
	 * Permet de créer une entrée dans la base de données par rapport à un objet.
	 * @param obj .
	 */
	public abstract void create(T obj);

	/**
	 * Permet de mettre à jour les données d'une entrée dans la base.
	 * @param obj .
	 * @return T updated object.
	 */
	public abstract T update(T obj);

	/**
	 * Delete item into db.
	 * @param id corresponding to object to delete
	 */
	public abstract void delete(int id);


	public abstract List<T> getAll();

	public void close() {
		try {

			if (statement != null) {
				statement.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
