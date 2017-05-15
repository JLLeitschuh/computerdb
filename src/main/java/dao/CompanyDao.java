package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import dto.CompanyDTO;
import mappers.CompanyMapper;
import model.CompanyEntity;

public class CompanyDao extends IDao<CompanyEntity> {
	public static final String COMPANY_TABLE_NAME = "company";

	/**
	 * find company with specific id.
	 * @param id for company to find
	 * @return CompanyEntity
	 */
	@Override
	public CompanyEntity find(int id) {
		// TODO Auto-generated method stub
		CompanyEntity companyModel = null;
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
			close();
		}

		return companyModel;
	}

	/**
	 * Create company obj.
	 * @param obj .
	 */
	@Override
	public void create(CompanyEntity obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * update company object.
	 * @param obj corresponding to updated company
	 * @return updated company entity
	 */
	@Override
	public CompanyEntity update(CompanyEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
	@Override
	public List<CompanyEntity> getAll() {

		ArrayList<CompanyEntity> companyList = new ArrayList<>();

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
		}
		return companyList;
		// TODO Auto-generated method stub

	}

}
