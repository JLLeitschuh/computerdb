package computerdb;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MySQLConnectionSingleton {
	
	private static final String DATABASE_NAME = "computer-database-db";

	private static final String URL="jdbc:mysql://localhost/";
	private static final String USER_NAME= "admincdb";
	private static final String PWD= "qwerty1234";
	
	//generate singleton which is loaded at the begining
	private static MySQLConnectionSingleton instance = new MySQLConnectionSingleton();
	
	Connection connect;
	private  MySQLConnectionSingleton() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection(URL+DATABASE_NAME+"?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull",USER_NAME,PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static MySQLConnectionSingleton getInstance(){
		return instance;
	}
	public Connection getConnection(){
		return connect;
		
	}

}
