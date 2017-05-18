package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import dto.CompanyDTO;
import mappers.CompanyMapper;
import model.CompanyEntity;
import persistance.MySQLConnectionSingleton;

import static dao.DaoUtils.*;

public class CompanyDao {
	public static final String COMPANY_TABLE_NAME = "company";
	Connection connect;

	public CompanyDao(){
		connect = MySQLConnectionSingleton.getInstance().getConnection();
	}
	/**
	 * find company with specific id.
	 * @param id for company to find
	 * @return CompanyEntity
	 */

	public CompanyEntity find(int id) {
		// TODO Auto-generated method stub
		CompanyEntity companyModel = null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT * FROM " + COMPANY_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.first()) {

				companyModel = CompanyMapper.createCompany(resultSet);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(preparedStatement, resultSet, null);
		}

		return companyModel;
	}

	/**
	 * delete company with id ID.
	 * @param id .
	 */
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	/**
	 * create companyDTO object from companyEntity object.
	 * @param company company which will be map to DTO
	 * @return CompanyDto corresponding to company parameter
	 */
	public CompanyDTO createCompanyDTO(CompanyEntity company) {

		return new CompanyDTO(company.getId(), company.getName());
	}

	/**
	 * create Company Object with companyDTO.
	 * @param companyDTO DTO to company entity .
	 * @return new CompanyEntity
	 */
	public CompanyEntity createCompany(CompanyDTO companyDTO) {

		return new CompanyEntity(companyDTO.getId(), companyDTO.getName());
	}

	/**
	 * get all company from company table.
	 * @return list of companies
	 */

	public List<CompanyEntity> getAll() {

		ArrayList<CompanyEntity> companyList = new ArrayList<>();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = (Statement) connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM " + COMPANY_TABLE_NAME);
			while (resultSet.next()) {

				CompanyEntity companyEntity = CompanyMapper.createCompany(resultSet);
				companyList.add(companyEntity);
				System.out.println("Value " + companyEntity.toString());

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, resultSet, statement);
		}
		return companyList;
		// TODO Auto-generated method stub

	}

}
