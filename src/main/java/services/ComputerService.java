package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ComputerDao;
import dto.CompanyDTO;
import dto.ComputerDTO;
import mappers.DataMapper;
import model.CompanyEntity;
import model.ComputerEntity;
import ui.Page;

public class ComputerService {

	ComputerDao computerDao;
	CompanyService companyService;
	List<ComputerDTO> computerDTOList;
	String research = "";

	Page page = new Page();
	Logger logger;

	/**
	 * constructor.
	 */
	public ComputerService() {

		// PropertyConfigurator.configure("/main/resources/log4j.properties");
		logger = LoggerFactory.getLogger(getClass());
		companyService = new CompanyService();
		computerDao = new ComputerDao();
		page = new Page();

	}

	/**
	 * insert new computer into db.
	 * @param name .
	 * @param introduced .
	 * @param discontinued .
	 * @param companyId .
	 */
	public void insertComputer(String name, String introduced, String discontinued, String companyId) {

		LocalDate introducedLocalDate = DataMapper.convertStringToDate(introduced);
		LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(introduced);

		CompanyDTO companyDTO = companyService.findCompanyById(companyId);
		ComputerEntity computerEntity = new ComputerEntity.ComputerBuilder().name(name).introduced(introducedLocalDate)
				.discontinued(discontinuedLocalDate)
				.company(new CompanyEntity(companyDTO.getId(), companyDTO.getName())).build();

		computerDao.create(computerEntity);
	}

	/**
	 * get computerDTO by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 */
	public ComputerDTO getComputerById(String strId) {

		int id = Integer.parseInt(strId);
		return computerDao.createComputerDTO(computerDao.find(id));
	}

	/**
	 * update computer into db.
	 * @param id .
	 * @param name .
	 * @param introduced .
	 * @param discontinued .
	 * @param companyId .
	 * @return ComputerDTO which been update
	 */
	public ComputerDTO update(String id, String name, String introduced, String discontinued, String companyId) {

		LocalDate introducedLocalDate = DataMapper.convertStringToDate(introduced);
		LocalDate discontinuedLocalDate = DataMapper.convertStringToDate(introduced);

		CompanyDTO companyDTO = companyService.findCompanyById(companyId);
		ComputerEntity computerEntity = new ComputerEntity.ComputerBuilder().id(Integer.parseInt(id)).name(name)
				.introduced(introducedLocalDate).discontinued(discontinuedLocalDate)
				.company(new CompanyEntity(companyDTO.getId(), companyDTO.getName())).build();
		return computerDao.createComputerDTO(computerDao.update(computerEntity));

	}

	/**
	 * get Computers list.
	 * @return list of computerDTO
	 */
	public List<ComputerDTO> getComputers() {

		List<ComputerEntity> computerList = computerDao.getAll();

		computerDTOList = new ArrayList<>();
		for (ComputerEntity computer : computerList) {

			computerDTOList.add(computerDao.createComputerDTO(computer));
			logger.info("Name Computer " + computer.getName());
		}
		return computerDTOList;

	}

	/**
	 * get computer List from pageNumber.
	 * @param pageNumber page selected by user
	 * @param itemPerPage .
	 * @param research .
	 * @return List of computerDTO corresponding to page "pageNumber"
	 */
	public List<ComputerDTO> getComputerFromTo(String pageNumber, String itemPerPage, String research) {

		if (research == null) {
			page.setNumberOfTotalItem(computerDao.getCount());
		} else {
			page.setNumberOfTotalItem(computerDao.getCountResearch(research));
		}

		if (itemPerPage != null && StringUtils.isNumeric(itemPerPage)) {
			this.page.setNumberItemPage(Integer.parseInt(itemPerPage));
		}
		if (pageNumber != null && StringUtils.isNumeric(pageNumber)) {
			this.page.setCurrentPage(Integer.parseInt(pageNumber));
		}
		if (research != null) {

			if (!this.research.equalsIgnoreCase(research)) {

				this.page.setCurrentPage(1);
				System.out.println("page " + this.page.getCurrentPage() );

			}
			this.research = research;
		}

		// calculate begin and end index array
		int from = (this.page.getCurrentPage() - 1) * this.page.getNumberItemPerPage();

		System.out.println("total number page  " + page.getNumberOfTotalItem() + " from " + from + " itemperpage "
				+ this.page.getNumberItemPerPage());

		return getComputers(from, this.page.getNumberItemPerPage(), this.research);
	}

	public Page getPage() {
		return this.page;
	}

	/**
	 * get computer list with limits and research.
	 * @param from .
	 * @param limit .
	 * @param strSearch .
	 * @return list of ComputerDTO with research
	 */
	public List<ComputerDTO> getComputers(int from, int limit, String strSearch) {

		List<ComputerEntity> computerList;
		if (strSearch != null) {
			computerList = computerDao.getSearchElementWithLimits(from, limit, strSearch);
		} else {
			computerList = computerDao.getElementWithLimits(from, limit);
		}

		System.out.println("computerListSize " + computerList.size());
		computerDTOList = new ArrayList<>();
		for (ComputerEntity computer : computerList) {

			computerDTOList.add(computerDao.createComputerDTO(computer));
			logger.info("Name Computer " + computer.getName());
		}
		return computerDTOList;
	}

	/**
	 * delete computer with id strId.
	 * @param strId .
	 */
	public void deleteComputer(String strId) {

		int id = Integer.parseInt(strId);
		computerDao.delete(id);

	}

}
