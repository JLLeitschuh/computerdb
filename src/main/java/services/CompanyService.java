package services;

import java.util.List;

import dao.CompanyDao;
import model.CompanyEntity;

public class CompanyService {

	CompanyDao companyDao;
	
	public CompanyService(){
		companyDao = new CompanyDao();
	}
	
	public CompanyEntity findCompanyById(int id){
		
		
		return companyDao.find(id);
		
	}
	
	public List<CompanyEntity> getCompanies(){
		
		return companyDao.getAll();
	}
	
}
