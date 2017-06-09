package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.exception.DTOException;
import com.ebiz.computerdatabase.mapper.CompanyMapper;
import com.ebiz.computerdatabase.model.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.ebiz.computerdatabase.persistence.dao.DaoUtils.close;
import static com.ebiz.computerdatabase.log.LoggerSing.logger;


@Repository
public class CompanyDao {

	public static final String COMPANY_TABLE_NAME = "company";


	@Autowired
	@Resource(name = "dataSource")
	private DataSource dataSource;
	

	/**
	 * find company with specific id.
	 * @param id for company to find
	 * @return CompanyEntity
	 * @throws DTOException .
	 */

	public CompanyEntity find(int id) throws DTOException {
		// TODO Auto-generated method stub

		Connection connect = DataSourceUtils.getConnection(dataSource);
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
			logger.error(e.getMessage());
			throw new DTOException(e.getMessage());

		} finally {
			close(resultSet, preparedStatement);
			DataSourceUtils.releaseConnection(connect, dataSource);
		}

	}

	/**
	 * get all company from company table.
	 * @return list of companies
	 * @throws DTOException .
	 */

	public List<CompanyEntity> getAll() throws DTOException {

		Connection connect = DataSourceUtils.getConnection(dataSource);;
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
			logger.error(e.getMessage());
			throw new DTOException(e.getMessage());
		} finally {
			close(resultSet, statement);
			DataSourceUtils.releaseConnection(connect, dataSource);;
		}

	}

	/**
	 * delete Company with id "companyId".
	 * @param companyId .
	 * @throws DTOException .
	 */

	public void deleteCompanyId(int companyId) throws DTOException {

		Connection connect = DataSourceUtils.getConnection(dataSource);;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = (PreparedStatement) connect
					.prepareStatement("DELETE  FROM " + CompanyDao.COMPANY_TABLE_NAME + " WHERE id =?");
			preparedStatement.setInt(1, companyId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DTOException(e.getMessage());
		} finally {
			close(resultSet, preparedStatement);
			DataSourceUtils.releaseConnection(connect, dataSource);;
		}

	}

}
