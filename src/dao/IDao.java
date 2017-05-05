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
	 * Permet de récupérer un objet via son ID
	 * @param id
	 * @return
	 */
	public abstract T find(int id);
	
	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract void create(T obj);
	
	/**
	 * Permet de mettre à jour les données d'une entrée dans la base 
	 * @param obj
	 */
	public abstract T update(T obj);
	
	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract void delete(T obj);
	
	public abstract List<T>getAll();
	
	public void close(){
		try {
			
			if(statement!=null){
				statement.close();
			}
			if(preparedStatement!=null){
				preparedStatement.close();
			}
			if(resultSet!=null){
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

