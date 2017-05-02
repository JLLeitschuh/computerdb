package computerdb;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.CompanyModel;
import model.ComputerModel;

public class MySQLConnectDB {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	private static final String DATABASE_NAME = "computer-database-db";
	public static final String COMPUTER_TABLE_NAME= "computer";
	public static final String COMPANY_TABLE_NAME= "company";
	private static final String URL="jdbc:mysql://localhost/";
	private static final String USER_NAME= "admincdb";
	private static final String PWD= "qwerty1234";




	public void connectToDb() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection(URL+DATABASE_NAME+"?autoReconnect=true&useSSL=false&&zeroDateTimeBehavior=convertToNull",USER_NAME,PWD);


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close(){
		try {
			connect.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void displayResultTable(String tableName){

		try {
			connectToDb();
			statement = (Statement) connect.createStatement();
			// Result set get the result of the SQL query
			
			System.out.println("Computer : "+getComputerById(42).getName());
			resultSet = statement.executeQuery("SELECT * FROM "+tableName);
			/*if(tableName.equalsIgnoreCase(COMPUTER_TABLE_NAME)){
				displayComputerResult(resultSet);
			}else{
				displayCompanyResult(resultSet);
			}*/
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

	}
	

	
	
	

	public void displayComputerResult(ResultSet resultSet) throws SQLException {
		//         Now get some metadata from the database
		// Result set get the result of the SQL query

		while(resultSet.next()){

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String introduced = resultSet.getString(3);
			String discontinued = resultSet.getString(4);
			int companyId = resultSet.getInt(5);

			System.out.println("Value " + new ComputerModel(id,name,introduced,discontinued,companyId).toString());	 

		}

	}

	public void displayCompanyResult(ResultSet resultSet) throws SQLException {
		//         Now get some metadata from the database
		// Result set get the result of the SQL query


		while(resultSet.next()){

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			
			System.out.println("Value " + new CompanyModel(id,name).toString());	 

		}

	}
	
	public void insertComputer(ComputerModel computer){
		
		 try {
			 
			preparedStatement = (PreparedStatement) connect.prepareStatement("insert into " +COMPUTER_TABLE_NAME +" values (default, ?, ?, ?, ?)");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,computer.getIntroduced());
			preparedStatement.setString(3, computer.getDiscontinued());
			preparedStatement.setInt(4, computer.getCompanyId());
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public ComputerModel getComputerById(int id){
		ComputerModel computer = null;
		try {
			resultSet = statement.executeQuery("SELECT * FROM "+COMPUTER_TABLE_NAME +" WHERE id ="+id);
			while(resultSet.next()){
				int idComputer = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String introduced = resultSet.getString(3);
				String discontinued = resultSet.getString(4);
				int companyId = resultSet.getInt(5);	
				computer = new ComputerModel(idComputer,name,introduced,discontinued,companyId);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
		
	}
	public void updateComputer(ComputerModel computer){

		 try {
			 
			preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " +COMPUTER_TABLE_NAME +
																			" SET name = ?,introduced =?, discontinued=?,company_id=?"+
																			" WHERE id ="+ computer.getId());
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,computer.getIntroduced());
			preparedStatement.setString(3, computer.getDiscontinued());
			preparedStatement.setInt(4, computer.getCompanyId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




}
