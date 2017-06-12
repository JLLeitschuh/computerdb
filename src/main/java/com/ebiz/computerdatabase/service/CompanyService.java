package com.ebiz.computerdatabase.service;

import com.ebiz.computerdatabase.dto.CompanyDTO;
import com.ebiz.computerdatabase.exception.DAOException;
import com.ebiz.computerdatabase.mapper.CompanyMapper;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.persistence.dao.CompanyDao;
import com.ebiz.computerdatabase.persistence.dao.ComputerDao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ebiz.computerdatabase.log.LoggerSing.logger;

@Service
public class CompanyService {


	@Autowired
	CompanyDao companyDao;

	@Autowired
	ComputerDao computerDao;

	/**
	 * find company into db by id.
	 * @param strId .
	 * @return CompanyDTO corresponding to object company with id "strId"
	 * @throws DAOException .
	 */

	public CompanyEntity findCompanyById(String strId) {

		try {
			if (strId != null && StringUtils.isNumeric(strId)) {
				int id = Integer.parseInt(strId);
				return companyDao.find(id);
			}
		} catch (DAOException e) {
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
		} catch (DAOException e) {
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
	 * @throws DAOException .
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCompanyId(String id) {

		try {
			computerDao.deleteComputerFromCompany(Integer.parseInt(id));
			companyDao.deleteCompanyId(Integer.parseInt(id));
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}
	

}
