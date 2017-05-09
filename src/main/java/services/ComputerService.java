package services;

import java.time.LocalDate;

import dao.ComputerDao;
import mappers.DataMapper;
import model.ComputerEntity;

public class ComputerService {

	ComputerDao computerDao;
	CompanyService companyService;
	
	public ComputerService(){
		
		computerDao = new ComputerDao();
	}
	
	public void insertComputer(String name, String introduced, String discontinued,String companyId ){
		
		LocalDate introducedLocalDate = DataMapper.convertStringToDate(introduced);
		LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(introduced);
		int iCompanyId = Integer.parseInt(companyId);
		ComputerEntity computerEntity = new ComputerEntity.ComputerBuilder().name(name).introduced(introducedLocalDate).discontinued(discontinuedLocalDate).company(companyService.findCompanyById(iCompanyId)).build();
		
		computerDao.create(computerEntity);
	}
	
}
