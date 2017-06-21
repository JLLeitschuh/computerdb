package com.ebiz.computerdatabase.service;

import com.ebiz.computerdatabase.entities.Company;
import com.ebiz.computerdatabase.exception.DAOException;
import com.ebiz.computerdatabase.persistence.dao.CompanyDao;

import com.ebiz.computerdatabase.persistence.dao.ComputerDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public Company findCompanyById(String strId) {

			if (strId != null && StringUtils.isNumeric(strId)) {
				int id = Integer.parseInt(strId);
				return companyDao.findOne(id);
			}

		return null;
	}

	/**
	 *  get companyDTO company list.
	 * @return list of companyDTO corresponding to companies object into db
	 */
	public List<Company> getCompanies()  {
		return companyDao.findAll();
	}

	/**
	 * delete Company .
	 * @param id .
	 * @throws DAOException .
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteCompanyId(String id) {

		companyDao.deleteById(Integer.parseInt(id));
		companyDao.flush();

	}
	

}
