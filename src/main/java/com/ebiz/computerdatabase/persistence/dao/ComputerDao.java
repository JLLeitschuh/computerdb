package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.exception.DTOException;
import com.ebiz.computerdatabase.mapper.ComputerMapper;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.model.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;


import com.zaxxer.hikari.HikariDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.ebiz.computerdatabase.persistence.dao.DaoUtils.*;
import static com.ebiz.computerdatabase.log.LoggerSing.*;

@Repository
public class ComputerDao extends IDao {

	public static final String COMPUTER_TABLE_NAME = "computer";

	@Autowired
	@Resource(name = "dataSource")
	private DataSource dataSource;

	@Autowired
	ComputerMapper computerMapper;



	/**
	 * find computer with specific id.
	 * @param id .
	 * @return computer entity find
	 * @throws DTOException .
	 */
	public ComputerEntity find(int id) throws DTOException {

		return usingConnection(connect -> {
			ResultSet resultSet = null;
			PreparedStatement preparedStatement = null;
			ComputerEntity computer = null;
			try {

				preparedStatement = (PreparedStatement) connect
						.prepareStatement("SELECT * FROM " + COMPUTER_TABLE_NAME + " WHERE id =?");
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					computer = computerMapper.createComputer(resultSet);
				}
				return computer;

			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());
			} finally {
				close(resultSet, preparedStatement);
			}

		});
	}

	/**
	 * create new computer into computer table.
	 * @param computer .
	 * @throws DTOException .
	 */

	public void create(ComputerEntity computer) throws DTOException {

		usingConnection(connect -> {
			PreparedStatement preparedStatement = null;
			try {

				preparedStatement = (PreparedStatement) connect
						.prepareStatement("insert into " + COMPUTER_TABLE_NAME + " values (default, ?, ?, ?, ?)");
				preparedStatement.setString(1, computer.getName());
				preparedStatement.setString(2,
						computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
				preparedStatement.setString(3,
						computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
				if (computer.getCompanyEntity() != null) {
					preparedStatement.setInt(4, computer.getCompanyEntity().getId());
				}
				preparedStatement.executeUpdate();
				return true;

			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());

			} finally {
				close(null, preparedStatement);
			}
		});

	}

	/**
	 * update computer into computer table.
	 * @param computer .
	 * @return update computer;
	 * @throws DTOException .
	 */

	public boolean update(ComputerEntity computer) throws DTOException {

		return usingConnection(connect -> {
			PreparedStatement preparedStatement = null;
			try {

				preparedStatement = (PreparedStatement) connect.prepareStatement("UPDATE " + COMPUTER_TABLE_NAME
						+ " SET name = ?,introduced =?, discontinued=?,company_id=?" + " WHERE id =?");

				preparedStatement.setString(1, computer.getName());
				preparedStatement.setString(2,
						computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
				preparedStatement.setString(3,
						computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
				if (computer.getCompanyEntity() != null) {
					preparedStatement.setInt(4, computer.getCompanyEntity().getId());
				} else {
					preparedStatement.setString(4, null);
				}
				preparedStatement.setInt(5, computer.getId());
				int count = preparedStatement.executeUpdate();

				return count > 0 ? true : false;

			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());
			} finally {
				close(null, preparedStatement);
			}
		});

	}

	/**
	 * delete computer from computer table.
	 * @param idComputerList .
	 * @throws DTOException .
	 */
	public void deleteComputers(String[] idComputerList) throws DTOException {

		delete(idComputerList);

	}

	/**
	 * delete item list. private method because there is no gestion of rollback if something went wrong. Not supposed ti be used directly.
	 * @param companyId .
	 * @throws DTOException .
	 */
	public void deleteComputerFromCompany(int companyId) throws DTOException {
		usingConnection(connect -> {
			PreparedStatement preparedStatement = null;

			try {
				preparedStatement = (PreparedStatement) connect
						.prepareStatement("Delete From " + COMPUTER_TABLE_NAME + " WHERE company_id =?");
				preparedStatement.setInt(1, companyId);
				preparedStatement.executeUpdate();
				return true;
			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());
			} finally {
				close(null, preparedStatement);

			}
		});
	}

	/**
	 * delete item list. private method because there is no gestion of rollback if something went wrong. Not supposed ti be used directly.
	 * @param idComputerList .
	 * @throws DTOException .
	 */
	private void delete(String[] idComputerList) throws DTOException {

		usingConnection(connect -> {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {

				for (String id : idComputerList) {
					preparedStatement = (PreparedStatement) connect
							.prepareStatement("Delete From " + COMPUTER_TABLE_NAME + " WHERE id =?");
					preparedStatement.setString(1, id);
					preparedStatement.executeUpdate();

				}
				return true;
			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());
			} finally {
				close(resultSet, preparedStatement);
			}
		});

	}


	/**
	 * get number verything okof item into computer db.
	 * @param research .
	 * @return item count into computer table
	 * @throws DTOException .
	 */
	public int getCount(String research) throws DTOException {

		return usingConnection(connect -> {
			ResultSet resultSet = null;
			PreparedStatement preparedStatement = null;

			StringBuilder query = new StringBuilder("SELECT Count(*) FROM " + COMPUTER_TABLE_NAME);
			if (research != null) {
				query.append(" Where name like ?");
			}
			try {
				preparedStatement = (PreparedStatement) connect.prepareStatement(query.toString());
				if (research != null) {
					preparedStatement.setString(1, "%" + research + "%");
				}
				resultSet = preparedStatement.executeQuery();
				resultSet.first();
				return resultSet.getInt(1);
			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());
			} finally {
				close(resultSet, preparedStatement);
			}});

	}

	/**
	 * display all computer details.
	 * @return computer list
	 * @throws DTOException .
	 */
	public ArrayList<ComputerEntity> getAll() throws DTOException {

		return usingConnection(connect -> {
			ResultSet resultSet = null;
			Statement statement = null;
			ArrayList<ComputerEntity> computerList = new ArrayList<>();
			try {
				statement = (Statement) connect.createStatement();
				resultSet = statement.executeQuery("SELECT * FROM " + COMPUTER_TABLE_NAME);

				while (resultSet.next()) {
					ComputerEntity computerEntity = computerMapper.createComputer(resultSet);
					computerList.add(computerEntity);
				}
				return computerList;
			} catch (SQLException e) {
				logger.error(e.toString());
				throw new DTOException(e.getMessage());
			} finally {
				close(resultSet, statement);
			}
		});

	}

	/**
	 * get computers on database with begin and end limit.
	 * @param start .
	 * @return list of computer between begin and end
	 * @throws DTOException .
	 */
	public List<ComputerEntity> getComputers(int start, PageRequest pageRequest)
			throws DTOException {

		return usingConnection(connect -> {

			ArrayList<ComputerEntity> computerList = new ArrayList<>();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String researchString = pageRequest.getResearch();
			String orderby = pageRequest.getOrderBy();
			int offset = pageRequest.getItemNumber();
			int sort = pageRequest.getSort();

			try {
				StringBuilder query = new StringBuilder("SELECT * FROM " + COMPUTER_TABLE_NAME + " cmp LEFT JOIN "
						+ CompanyDao.COMPANY_TABLE_NAME + " cmpy ON cmpy.id = cmp.company_id ");
				if (pageRequest.getResearch() != null) {
					query.append(" WHERE cmp.name like ?");
				}
				if (orderby != null) {
					query.append(" Order By " + orderby);
					if (sort == 0) {
						query.append(" ASC ");
					} else {
						query.append(" DESC ");
					}

				}
				query.append(" Limit ?,? ");

				logger.info(query.toString());

				preparedStatement = (PreparedStatement) connect.prepareStatement(query.toString());

				// Check if researching exist to set parameter on the right order
				if (researchString != null) {
					preparedStatement.setString(1, "%" + researchString + "%");
					preparedStatement.setInt(2, start);
					preparedStatement.setInt(3, offset);

				} else {
					preparedStatement.setInt(1, start);
					preparedStatement.setInt(2, offset);

				}
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ComputerEntity computerEntity = computerMapper.createComputer(resultSet);
					computerList.add(computerEntity);
				}
				return computerList;
			} catch (SQLException e) {

				logger.error(e.getMessage());
				throw new DTOException(e.getMessage());

			} finally {
				close(resultSet, preparedStatement);

			}
		});
	}

}
