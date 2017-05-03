package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.CompanyModel;
import model.ComputerModel;

public class ComputerDao extends DAO<ComputerModel> {

	public static final String COMPUTER_TABLE_NAME= "computer";

	@Override
	public ComputerModel find(int id) {

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
				DAO<CompanyModel> companydao = new CompanyDao();
				CompanyModel companyModel = companydao.find(companyId);
				computer = new ComputerModel(idComputer,name,introduced,discontinued,companyModel);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return computer;
	}

	@Override
	public void create(ComputerModel computer) {
		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("insert into " +COMPUTER_TABLE_NAME +" values (default, ?, ?, ?, ?)");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2,computer.getIntroduced());
			preparedStatement.setTimestamp(3, computer.getDiscontinued());
			preparedStatement.setInt(4, computer.getCompanyModel().getId());
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}finally{
			close();
		}

	}

	@Override
	public ComputerModel update(ComputerModel computer) {

		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " +COMPUTER_TABLE_NAME +
					" SET name = ?,introduced =?, discontinued=?,company_id=?"+
					" WHERE id =?");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setTimestamp(2,computer.getIntroduced());
			preparedStatement.setTimestamp(3, computer.getDiscontinued());		
			preparedStatement.setInt(4, computer.getCompanyModel().getId());			
			preparedStatement.setInt(5, computer.getId());
			preparedStatement.executeUpdate();

			computer = find(computer.getId());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return computer;
	}

	@Override
	public void delete(ComputerModel computer) {
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



	@Override
	public void getAll() {

		try {
			DAO<CompanyModel> companydao = new CompanyDao();
			
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+COMPUTER_TABLE_NAME);
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Timestamp introduced = resultSet.getTimestamp(3);
				Timestamp discontinued = resultSet.getTimestamp(4);
				int companyId = resultSet.getInt(5);
				CompanyModel companyModel = companydao.find(companyId);
				System.out.println("Value " + new ComputerModel(id,name,introduced,discontinued,companyModel).toString());	 

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
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
