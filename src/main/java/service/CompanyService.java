package service;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;

import persistence.dao.CompanyDao;
import dto.CompanyDTO;
import exception.DTOException;
import mapper.CompanyMapper;
import model.CompanyEntity;

public class CompanyService {

	CompanyDao companyDao;

	/**
	 * Company Service constructor.
	 * @throws DTOException .
	 */
	public CompanyService() throws DTOException {
		companyDao = new CompanyDao();
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

}
