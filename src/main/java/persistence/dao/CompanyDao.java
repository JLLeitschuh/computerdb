package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.CompanyDTO;
import exception.DTOException;
import mapper.CompanyMapper;
import model.CompanyEntity;
import model.ComputerEntity;
import persistence.ConnectionSingleton;

import static persistence.dao.DaoUtils.*;

public class CompanyDao {
	public static final String COMPANY_TABLE_NAME = "company";

	/**
	 * DAO constructor .
	 * @throws DTOException .
	 */
	public CompanyDao() throws DTOException {

	}

	/**
	 * find company with specific id.
	 * @param id for company to find
	 * @return CompanyEntity
	 * @throws DTOException .
	 */

	public CompanyEntity find(int id) throws DTOException {
		// TODO Auto-generated method stub

		Connection connect = ConnectionSingleton.getInstance().getConnection();
		CompanyEntity companyModel = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			preparedStatement = (PreparedStatement) connect
					.prepareStatement("SELECT * FROM " + COMPANY_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.first()) {

				companyModel = CompanyMapper.createCompany(resultSet);
			}
			return companyModel;
		} catch (SQLException e) {

			throw new DTOException(e.getMessage());

		} finally {
			close(preparedStatement, resultSet, null);
			try {
				connect.close();
			} catch (SQLException e) {
				throw new DTOException(e.getMessage());
			}
		}

	}

	/**
	 * get all company from company table.
	 * @return list of companies
	 * @throws DTOException .
	 */

	public List<CompanyEntity> getAll() throws DTOException {

		Connection connect = ConnectionSingleton.getInstance().getConnection();
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
			return companyList;
		} catch (SQLException e) {

			throw new DTOException(e.getMessage());
		} finally {
			close(null, resultSet, statement);
			closeConnection(connect);
		}

	}

	public void deleteCompanyId(int companyId) throws DTOException {

		Connection connect = ConnectionSingleton.getInstance().getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			throw new DTOException(e1.getMessage());

		}
		try {
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("DELETE  FROM " + CompanyDao.COMPANY_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, companyId);
			preparedStatement.executeUpdate();
			connect.commit();

		} catch (SQLException e) {

			throw new DTOException(e.getMessage());
		} finally {
			close(preparedStatement, resultSet, null);
		}

	}

}
