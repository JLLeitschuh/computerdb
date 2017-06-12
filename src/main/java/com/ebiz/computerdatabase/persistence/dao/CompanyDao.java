package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.exception.DAOException;
import com.ebiz.computerdatabase.mapper.CompanyMapper;
import com.ebiz.computerdatabase.mapper.ComputerMapper;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class CompanyDao extends IDao{

	public static final String COMPANY_TABLE_NAME = "company";


	@Autowired
	@Resource(name = "dataSource")
	private DataSource dataSource;


	/**
	 * find company with specific id.
	 * @param id for company to find
	 * @return CompanyEntity
	 * @throws DAOException .
	 */

	public CompanyEntity find(int id) throws DAOException {
		// TODO Auto-generated method stub

		return usingConnection(jdbcTemplateObject -> {

			CompanyEntity companyEntity = jdbcTemplateObject.queryForObject("SELECT * FROM " + COMPANY_TABLE_NAME + " WHERE id =?",
					new Object[]{id}, new CompanyMapper());

			return companyEntity;

		});

	}

	/**
	 * get all company from company table.
	 * @return list of companies
	 * @throws DAOException .
	 */

	public List<CompanyEntity> getAll() throws DAOException {

		return usingConnection(jdbcTemplateObject->{

			List<CompanyEntity> companyList = jdbcTemplateObject.query("SELECT * FROM " + COMPANY_TABLE_NAME, new CompanyMapper());
			return companyList;

		});

	}

	/**R
	 * delete Company with id "companyId".
	 * @param companyId .
	 * @throws DAOException .
	 */

	public void deleteCompanyId(int companyId) throws DAOException {

		usingConnection(jdbcTemplateObject -> {

			jdbcTemplateObject.update("DELETE  FROM " + CompanyDao.COMPANY_TABLE_NAME + " WHERE id =?",companyId);
			return true;

		});

	}

}
