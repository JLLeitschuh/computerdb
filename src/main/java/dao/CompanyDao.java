package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import mappers.CompanyMapper;
import model.CompanyEntity;

public class CompanyDao extends IDao<CompanyEntity>{



	public static final String COMPANY_TABLE_NAME= "company";

	/**
	 * find company with specific id
	 */
	@Override
	public CompanyEntity find(int id) {
		// TODO Auto-generated method stub
		CompanyEntity companyModel = null;
		try {

			preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT * FROM "+COMPANY_TABLE_NAME +" WHERE id =?");
			preparedStatement.setInt(1,id);
			resultSet = preparedStatement.executeQuery();;

			if(resultSet.first()){
				
				companyModel= CompanyMapper.createCompany(resultSet);
			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

		return companyModel;
	}

	@Override
	public void create(CompanyEntity obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public CompanyEntity update(CompanyEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompanyEntity obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * get all company from company table
	 */
	@Override
	public List<CompanyEntity> getAll() {

		ArrayList<CompanyEntity> companyList = new ArrayList<>();

		try {
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+COMPANY_TABLE_NAME);
			while (resultSet.next()) {

				CompanyEntity companyEntity = CompanyMapper.createCompany(resultSet);
				companyList.add(companyEntity);
				System.out.println("Value " + companyEntity.toString());	 

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companyList;
		// TODO Auto-generated method stub

	}

}
