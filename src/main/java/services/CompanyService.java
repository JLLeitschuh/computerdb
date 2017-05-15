package services;

import java.util.ArrayList;
import java.util.List;

import dao.CompanyDao;
import dto.CompanyDTO;
import model.CompanyEntity;

public class CompanyService {

	CompanyDao companyDao;
	
	public CompanyService(){
		companyDao = new CompanyDao();
	}
	
	public CompanyDTO findCompanyById(String strId){
		
		int id = Integer.parseInt(strId);
		return companyDao.createCompanyDTO(companyDao.find(id));
		
	}
	
	public List<CompanyDTO> getCompanies(){
		
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		List<CompanyEntity> companies =companyDao.getAll();
		
		for(CompanyEntity company : companies){
			companyDTOs.add(companyDao.createCompanyDTO(company));
		}
		
		return companyDTOs;
	}
	
}
