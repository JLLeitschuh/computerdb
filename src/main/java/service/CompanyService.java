package service;

import java.io.Closeable;
import static persistence.dao.DaoUtils.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static log.LoggerSing.*;

import com.mysql.jdbc.StringUtils;

import persistence.ConnectionSingleton;
import persistence.dao.CompanyDao;
import persistence.dao.ComputerDao;
import dto.CompanyDTO;
import exception.DTOException;
import log.LoggerSing;
import mapper.CompanyMapper;
import model.CompanyEntity;
import static persistence.dao.DaoUtils.*;

public class CompanyService {

	CompanyDao companyDao;
	ComputerDao computerDao;
	LoggerSing logger = new LoggerSing(this.getClass());
	private static final CompanyService COMPANY_SERVICE = new CompanyService();

	/**
	 * Company Service constructor.
	 * @throws DTOException .
	 */
	private CompanyService(){
		companyDao = CompanyDao.getCompanyDao();
		computerDao =ComputerDao.getComputerDao();
	}
	
	public static CompanyService getCompanyService(){
		return COMPANY_SERVICE;
	}

	/**
	 * find company into db by id.
	 * @param strId .
	 * @return CompanyDTO corresponding to object company with id "strId"
	 * @throws DTOException .
	 */

	public CompanyEntity findCompanyById(String strId) throws DTOException {

		if (strId != null && StringUtils.isStrictlyNumeric(strId)) {
			int id = Integer.parseInt(strId);
			return companyDao.find(id);
		}
		return null;

	}

	/**
	 *  get companyDTO company list.
	 * @return list of companyDTO corresponding to companies object into db
	 * @throws DTOException .
	 */
	public List<CompanyDTO> getCompanies() throws DTOException {

		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		List<CompanyEntity> companies = companyDao.getAll();

		for (CompanyEntity company : companies) {
			companyDTOs.add(CompanyMapper.createCompanyDTO(company));
		}

		return companyDTOs;
	}
	
	/**
	 * 
	 * @param id
	 * @throws DTOException
	 */
	public void deleteCompanyId(String id) throws DTOException {

		Connection connect = ConnectionSingleton.getInstance().getConnection();
		List<String> computerIdList = computerDao.getComputerFromCompany(Integer.parseInt(id));
		try {
			connect.setAutoCommit(false);
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		try {
			computerDao.deleteComputers(computerIdList.toArray(new String[0]), connect);
			companyDao.deleteCompanyId(Integer.parseInt(id), connect);
			try {
				connect.commit();
			} catch (SQLException e) {
				logger.logError(e.getMessage());
				throw new DTOException(e.getMessage());
			}
		} catch (DTOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				logger.logError(e.getMessage());
				throw new DTOException(e1.getMessage());
			}

		} finally {
			closeConnection(connect);
		}

	}

}
