package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.CompanyModel;

public class CompanyDao extends DAO<CompanyModel>{



	public static final String COMPANY_TABLE_NAME= "company";

	@Override
	public CompanyModel find(int id) {
		// TODO Auto-generated method stub
		CompanyModel companyModel = null;
		try {
			


			preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT * FROM "+COMPANY_TABLE_NAME +" WHERE id =?");
			preparedStatement.setInt(1,id);

			resultSet = preparedStatement.executeQuery();;

			
			if(resultSet.first()){
				int companyId = resultSet.getInt(1);
				String name = resultSet.getString(2);
				companyModel= new CompanyModel(companyId, name);
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
	public void create(CompanyModel obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public CompanyModel update(CompanyModel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompanyModel obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAll() {
		
		
		try {
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+COMPANY_TABLE_NAME);
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);

				System.out.println("Value " + new CompanyModel(id,name).toString());	 

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}
