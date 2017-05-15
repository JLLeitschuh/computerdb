package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

	Page page = new Page();

	/**
	 * constructor.
	 */
	public ComputerService() {

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
		}
		return computerDTOList;

	}

	/**
	 * get computer List from pageNumber.
	 * @param pageNumber page selected by user
	 * @param itemPerPage .
	 * @return List of computerDTO corresponding to page "pageNumber"
	 */
	public List<ComputerDTO> getComputerFromTo(String pageNumber, String itemPerPage) {

		if (page.getComputerList() == null) {

			page.setComputerList(getComputers());
		}
		if (itemPerPage != null) {
			page.setNumberItemPage(Integer.parseInt(itemPerPage));
		}
		if (pageNumber == null) {
			this.page.setCurentPage(1);
		} else {
			this.page.setCurentPage(Integer.parseInt(pageNumber));
		}
		// calculate begin and end index array
		int from = this.page.getCurrentPage() * this.page.getNumberItemPerPage();
		int to = from + this.page.getNumberItemPerPage();

		if (to >= page.getComputerList().size()) {
			to = page.getComputerList().size() - 1;
		}
		return computerDTOList.subList(from, to);
	}

	public Page getPage() {

		return this.page;

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
