package services;

import java.util.ArrayList;
import java.util.List;

import dao.CompanyDao;
import dto.CompanyDTO;
import model.CompanyEntity;

public class CompanyService {

	CompanyDao companyDao;

	/**
	 * Company Service constructor.
	 */
	public CompanyService() {
		companyDao = new CompanyDao();
	}
	/**
	 * find company into db by id.
	 * @param strId .
	 * @return CompanyDTO corresponding to object company with id "strId"
	 */

	public CompanyDTO findCompanyById(String strId) {

		int id = Integer.parseInt(strId);
		return companyDao.createCompanyDTO(companyDao.find(id));

	}

	/**
	 *  get companyDTO company list.
	 * @return list of companyDTO corresponding to companies object into db
	 */
	public List<CompanyDTO> getCompanies() {

		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		List<CompanyEntity> companies = companyDao.getAll();

		for (CompanyEntity company : companies) {
			companyDTOs.add(companyDao.createCompanyDTO(company));
		}

		return companyDTOs;
	}

}
