package services;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.event.SubstituteLoggingEvent;

import dao.ComputerDao;
import mappers.DataMapper;
import model.ComputerEntity;
import ui.Page;

public class ComputerService {

	ComputerDao computerDao;
	CompanyService companyService;
	ArrayList<ComputerEntity> computerList;
	Page page = new Page();

	public ComputerService(){

		computerDao = new ComputerDao();
		page = new Page();
		
	}

	public void insertComputer(String name, String introduced, String discontinued,String companyId ){

		LocalDate introducedLocalDate = DataMapper.convertStringToDate(introduced);
		LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(introduced);
	
		ComputerEntity computerEntity = new ComputerEntity.ComputerBuilder().name(name).introduced(introducedLocalDate).discontinued(discontinuedLocalDate).company(companyService.findCompanyById(companyId)).build();

		computerDao.create(computerEntity);
	}

	public ComputerEntity getComputerById(String strId){

		int id = Integer.parseInt(strId);
		return computerDao.find(id);
	}

	public ComputerEntity update(String name, String introduced, String discontinued,String companyId ){

		LocalDate introducedLocalDate = DataMapper.convertStringToDate(introduced);
		LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(introduced);
		
		
		ComputerEntity computerEntity = new ComputerEntity.ComputerBuilder().name(name).introduced(introducedLocalDate).discontinued(discontinuedLocalDate).company(companyService.findCompanyById(companyId)).build();			
		return computerDao.update(computerEntity);


	}
	
	
	public List<ComputerEntity> getComputers(){
		
		computerList = computerDao.getAll();
		return computerList;
		
	}
	
	public List<ComputerEntity> getComputerFromTo(String pageNumber,String itemPerPage){
		
		if(page.getComputerList()==null){
			
			page.setComputerList(getComputers());
		}
		if(itemPerPage!=null){
			page.setNumberItemPage(Integer.parseInt(itemPerPage));
		}
		if(pageNumber==null){
			this.page.setCurentPage(1); 
		}else{
			this.page.setCurentPage(Integer.parseInt(pageNumber));
		}	
		//calculate begin and end index array
		int from = this.page.getCurrentPage() * this.page.getNumberItemPerPage();
		int to = from + this.page.getNumberItemPerPage();
		
		if(to >= page.getComputerList().size()){
			to = page.getComputerList().size() -1;
		}
		return computerList.subList(from, to);
	}
	
	public Page getPage(){
				
		return this.page;
		
	}
	
	
	
	
	
	public void deleteComputer(String strId){
		
		int id = Integer.parseInt(strId);
		computerDao.delete(id);
			
	}

}
