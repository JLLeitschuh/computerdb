package com.ebiz.computerdatabase.persistence.dao;

import com.ebiz.computerdatabase.exception.DAOException;

import com.ebiz.computerdatabase.mapper.ComputerMapper;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.model.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.util.*;


@Repository
public class ComputerDao extends IDao {

	public static final String COMPUTER_TABLE_NAME = "computer";

	@Autowired
	@Resource(name = "dataSource")
	private DataSource dataSource;


	/**
	 * find computer with specific id.
	 * @param id .
	 * @return computer entity find
	 * @throws DAOException .
	 */
	public ComputerEntity find(int id) throws DAOException {

		return usingConnection(jdbcTemplateObject -> {
			ComputerEntity computerEntity = jdbcTemplateObject.queryForObject("SELECT * FROM " + COMPUTER_TABLE_NAME + " cmp LEFT JOIN "
					+ CompanyDao.COMPANY_TABLE_NAME + " cmpy ON cmpy.id = cmp.company_id  WHERE cmp.id =?",new Object[]{id},new ComputerMapper());
			return  computerEntity;

		});
	}

	/**
	 * create new computer into computer table.
	 * @param computer .
	 * @throws DAOException .
	 */

	public void create(ComputerEntity computer) throws DAOException {

		usingConnection(jdbcTemplateObject -> {
			jdbcTemplateObject.update("insert into " + COMPUTER_TABLE_NAME + " values (default, ?, ?, ?, ?)",new Object[] {computer.getName(), computer.getIntroduced(), computer.getDiscontinued(),computer.getCompanyId()});
			return true;
		});

	}

	/**
	 * update computer into computer table.
	 * @param computer .
	 * @return update computer;
	 * @throws DAOException .
	 */

	public boolean update(ComputerEntity computer) throws DAOException {

		return usingConnection(jdbcTemplateObject -> {

			int count = jdbcTemplateObject.update("UPDATE " + COMPUTER_TABLE_NAME
					+ " SET name = ?,introduced =?, discontinued=?,company_id=?" + " WHERE id =?",new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(),computer.getCompanyId(),computer.getId()});

			return count > 0 ? true : false;


		});

	}

	/**
	 * delete item list. private method because there is no gestion of rollback if something went wrong. Not supposed ti be used directly.
	 * @param companyId .
	 * @throws DAOException .
	 */
	public void deleteComputerFromCompany(int companyId) throws DAOException {
		usingConnection(jdbcTemplateObject -> {
			jdbcTemplateObject.update("Delete From " + COMPUTER_TABLE_NAME + " WHERE company_id =?",companyId);
			return true;

		});
	}

	/**
	 * delete item list. private method because there is no gestion of rollback if something went wrong. Not supposed ti be used directly.
	 * @param idComputerList .
	 * @throws DAOException .
	 */
	public void deleteComputers(String[] idComputerList) throws DAOException {

		usingConnection(jdbcTemplateObject -> {

			for(String companyId:idComputerList){
				jdbcTemplateObject.update("Delete From " + COMPUTER_TABLE_NAME + " WHERE id = ?",companyId);
			}
			/*List<String> params = Arrays.asList(idComputerList);
			Map idComputerMap = Collections.singletonMap("idComputers", params);

			jdbcTemplateObject.update("Delete From " + COMPUTER_TABLE_NAME + " WHERE id = :idComputers",idComputerMap);*/

			return true;

		});

	}


	/**
	 * get number verything okof item into computer db.
	 * @param research .
	 * @return item count into computer table
	 * @throws DAOException .
	 */
	public int getCount(String research) throws DAOException {

		return usingConnection(jdbcTemplateObject -> {

			String researchStr =research==null?"" : research;
			int count = jdbcTemplateObject.queryForObject("SELECT Count(*) FROM " + COMPUTER_TABLE_NAME + " WHERE name LIKE ?",new Object[]{"%"+researchStr+"%"}, Integer.class);
			return count;
		});

	}

	/**
	 * display all computer details.
	 * @return computer list
	 * @throws DAOException .
	 */
	public List<ComputerEntity> getAll() throws DAOException {

		return usingConnection(jdbcTemplateObject -> {

			List<ComputerEntity> computerList = jdbcTemplateObject.query("SELECT * FROM " + COMPUTER_TABLE_NAME + " cmp LEFT JOIN "
					+ CompanyDao.COMPANY_TABLE_NAME + " cmpy ON cmpy.id = cmp.company_id ",new ComputerMapper());
			return computerList;

		});

	}

	/**
	 * get computers on database with begin and end limit.
	 * @param start .
	 * @return list of computer between begin and end
	 * @throws DAOException .
	 */
	public List<ComputerEntity> getComputers(int start, PageRequest pageRequest)
			throws DAOException {

		return usingConnection(jdbcTemplateObject-> {

			List<ComputerEntity> computerList;
			String researchString = pageRequest.getSearch();
			String orderby = pageRequest.getOrderBy();
			int offset = pageRequest.getItemNumber();
			int sort = pageRequest.getSort();
			StringBuilder query = new StringBuilder("SELECT * FROM " + COMPUTER_TABLE_NAME + " cmp LEFT JOIN "
					+ CompanyDao.COMPANY_TABLE_NAME + " cmpy ON cmpy.id = cmp.company_id ");
			if (pageRequest.getSearch() != null) {
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

			// Check if researching exist to set parameter on the right order
			if (researchString != null) {
				computerList = jdbcTemplateObject.query(query.toString(),new Object[]{"%" + researchString + "%",start,offset},new ComputerMapper());

			} else {
				computerList = jdbcTemplateObject.query(query.toString(),new Object[]{start,offset},new ComputerMapper());
			}

			return computerList;

		});
	}

}
