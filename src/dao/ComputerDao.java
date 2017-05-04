package dao;

import java.sql.SQLException;
import java.time.LocalDate;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.CompanyEntity;
import model.ComputerEntity;

public class ComputerDao extends IDao<ComputerEntity> {

	public static final String COMPUTER_TABLE_NAME= "computer";

	/**
	 * find computer with specific id
	 */
	@Override
	public ComputerEntity find(int id) {

		ComputerEntity computer = null;
		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT * FROM "+COMPUTER_TABLE_NAME+" WHERE id =?");
			preparedStatement.setInt(1,id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int idComputer = resultSet.getInt(1);
				String name = resultSet.getString(2);
				LocalDate introduced = resultSet.getDate(3).toLocalDate();
				LocalDate discontinued = resultSet.getDate(4).toLocalDate();
				int companyId = resultSet.getInt(5);
				IDao<CompanyEntity> companydao = new CompanyDao();
				CompanyEntity companyModel = companydao.find(companyId);
				computer = new ComputerEntity.ComputerBuilder().id(idComputer).name(name).introduced(introduced).discontinued(discontinued).company(companyModel).build();
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
	 * create new computer into computer table
	 */
	@Override
	public void create(ComputerEntity computer) {
		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("insert into " +COMPUTER_TABLE_NAME +" values (default, ?, ?, ?, ?)");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,computer.getIntroduced()==null ? null : computer.getIntroduced().toString());
			preparedStatement.setString(3, computer.getDiscontinued()==null ? null : computer.getDiscontinued().toString());
			preparedStatement.setInt(4, computer.getCompanyEntity().getId());
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}finally{
			close();
		}

	}
	/**
	 * update computer into computer table
	 */
	@Override
	public ComputerEntity update(ComputerEntity computer) {

		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " +COMPUTER_TABLE_NAME +
					" SET name = ?,introduced =?, discontinued=?,company_id=?"+
					" WHERE id =?");
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setString(2,computer.getIntroduced()==null ? null : computer.getIntroduced().toString());
			preparedStatement.setString(3, computer.getDiscontinued()==null ? null : computer.getDiscontinued().toString());
			preparedStatement.setInt(4, computer.getCompanyEntity().getId());			
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

	/**
	 * delete computer from computer table
	 */
	@Override
	public void delete(ComputerEntity computer) {
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


	/**
	 * display all computer details 
	 */
	@Override
	public void getAll() {

		try {
			IDao<CompanyEntity> companydao = new CompanyDao();
			
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+COMPUTER_TABLE_NAME);
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				
				LocalDate introduced = resultSet.getDate(3)==null ?null:resultSet.getDate(3).toLocalDate();
				LocalDate discontinued = resultSet.getDate(4)==null ?null:resultSet.getDate(4).toLocalDate();
				int companyId = resultSet.getInt(5);
				CompanyEntity companyModel = companydao.find(companyId);
				System.out.println("Value " + new ComputerEntity.ComputerBuilder().id(id).name(name).introduced(introduced).discontinued(discontinued).company(companyModel).build().toString());	 

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

}
