package computerdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.CompanyModel;
import model.ComputerModel;

public class MySQLConnectDB {

	
	private Statement statement = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	
	public static final String COMPUTER_TABLE_NAME= "computer";
	public static final String COMPANY_TABLE_NAME= "company";
	Connection connect;

	public MySQLConnectDB(){
		connect = MySQLConnectionSingleton.getInstance().getConnection();
	}




	/**
	 * close all items
	 */
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


	
	/**
	 * display all data from tablename
	 * @param tableName
	 */
	public void displayTableData(String tableName){

		try {
			
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+tableName);
			if(tableName.equalsIgnoreCase(COMPUTER_TABLE_NAME)){
				displayComputerResult(resultSet);
			}else{
				displayCompanyResult(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

	}
	
	/**
	 * 
	 * @param id
	 * @return true company exist 
	 */

	public boolean getCompanyById(int id){
		try {

			
			preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT * FROM "+COMPANY_TABLE_NAME +" WHERE id =?");
			preparedStatement.setInt(1,id);

			resultSet = preparedStatement.executeQuery();;

			if(!resultSet.next()){
				return false;
			}else{
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

		return true; 

	}


	/**
	 * display all computers from table "computer"
	 * @param resultSet
	 * @throws SQLException
	 */
	public void displayComputerResult(ResultSet resultSet) throws SQLException {
		//         Now get some metadata from the database
		// Result set get the result of the SQL query

		while (resultSet.next()) {

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			Timestamp introduced = resultSet.getTimestamp(3);
			Timestamp discontinued = resultSet.getTimestamp(4);
			int companyId = resultSet.getInt(5);

			System.out.println("Value " + new ComputerModel(id,name,introduced,discontinued,companyId).toString());	 

		}

	}

	/**
	 * display all companies from table "company"
	 * @param resultSet
	 * @throws SQLException
	 */
	public void displayCompanyResult(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {

			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);

			System.out.println("Value " + new CompanyModel(id,name).toString());	 

		}

	}

	/**
	 * insert now computer into computer tbale
	 * @param computer
	 */
	public void insertComputer(ComputerModel computer){

		try {
			

			preparedStatement = (PreparedStatement) connect.prepareStatement("insert into " +COMPUTER_TABLE_NAME +" values (default, ?, ?, ?, ?)");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2,computer.getIntroduced());
			preparedStatement.setTimestamp(3, computer.getDiscontinued());
			preparedStatement.setInt(4, computer.getCompanyId());
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}


	}

	/**
	 * get ComputerModel with id "id"
	 * @param id
	 * @return null if computer doesn't exist
	 */

	public ComputerModel getComputerById(int id){
		ComputerModel computer = null;
		try {

			
			
			preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT * FROM "+COMPUTER_TABLE_NAME+" WHERE id =?");
			preparedStatement.setInt(1,id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int idComputer = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Timestamp introduced = resultSet.getTimestamp(3);
				Timestamp discontinued = resultSet.getTimestamp(4);
				int companyId = resultSet.getInt(5);	
				computer = new ComputerModel(idComputer,name,introduced,discontinued,companyId);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return computer;
	}

	/**
	 * display computer detail with id "id"
	 * @param id
	 */

	public void displayComputerById(int id){

		ComputerModel computerModel = getComputerById(id);

		if(computerModel!=null){

			System.out.println("Computer Details : "+ computerModel.toString());
		}else{
			System.out.println("Computer doesn't exists");
		}

	}
	
	/**
	 * update one computer data 
	 * @param computer
	 */
	public void updateComputer(ComputerModel computer){

		try {

			
			preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " +COMPUTER_TABLE_NAME +
					" SET name = ?,introduced =?, discontinued=?,company_id=?"+
					" WHERE id =?");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2,computer.getIntroduced());
			preparedStatement.setTimestamp(3, computer.getDiscontinued());
			preparedStatement.setInt(4, computer.getCompanyId());
			preparedStatement.setInt(5, computer.getId());
			preparedStatement.executeUpdate();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

	}
	
	/**
	 * delete computer from database "computer"
	 * @param computer
	 */
	public void deleteComputer(ComputerModel computer){

		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("Delete From " +COMPUTER_TABLE_NAME +" WHERE id =?");
			preparedStatement.setInt(1, computer.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

	}




}
