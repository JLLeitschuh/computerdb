package service;

import static persistence.dao.DaoUtils.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import static log.LoggerSing.*;

import persistence.ConnectionSingleton;
import persistence.dao.CompanyDao;
import persistence.dao.ComputerDao;
import dto.CompanyDTO;
import exception.DTOException;
import mapper.CompanyMapper;
import model.CompanyEntity;
import static persistence.dao.DaoUtils.*;

public class CompanyService {

	CompanyDao companyDao;
	ComputerDao computerDao;

	private static final CompanyService COMPANY_SERVICE = new CompanyService();

	/**
	 * Company Service constructor.
	 * @throws DTOException .
	 */
	private CompanyService() {
		companyDao = CompanyDao.getCompanyDao();
		computerDao = ComputerDao.getComputerDao();
	}

	public static CompanyService getCompanyService() {
		return COMPANY_SERVICE;
	}

	/**
	 * find company into db by id.
	 * @param strId .
	 * @return CompanyDTO corresponding to object company with id "strId"
	 * @throws DTOException .
	 */

	public CompanyEntity findCompanyById(String strId) {

		try {
			if (strId != null && StringUtils.isNumeric(strId)) {
				int id = Integer.parseInt(strId);
				return companyDao.find(id);
			}
		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return null;

	}

	/**
	 *  get companyDTO company list.
	 * @return list of companyDTO corresponding to companies object into db
	 */
	public List<CompanyDTO> getCompanies()  {

		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		List<CompanyEntity> companies;
		try {
			companies = companyDao.getAll();
		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		for (CompanyEntity company : companies) {
			companyDTOs.add(CompanyMapper.createCompanyDTO(company));
		}

		return companyDTOs;
	}

	/**
	 * delete Company .
	 * @param id .
	 * @throws DTOException .
	 */
	public void deleteCompanyId(String id) {

		Connection connect = null;

		try {
			connect = ConnectionSingleton.getInstance().getConnection();
			connect.setAutoCommit(false);
		} catch (SQLException e1) {
			logger.error(e1.getMessage());
			throw new RuntimeException(e1.getMessage());
		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		try {
			computerDao.deleteComputerFromCompany(Integer.parseInt(id));
			companyDao.deleteCompanyId(Integer.parseInt(id));
			commit(connect);
		} catch (DTOException e) {
			rollback(connect);
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			closeConnection(connect);
		}

	}

}
