package services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ComputerDao;
import dto.ComputerDTO;
import model.ComputerEntity;
import ui.Page;

public class ComputerService {

	ComputerDao computerDao;
	CompanyService companyService;
	List<ComputerDTO> computerDTOList;
	String research = "";
	Logger logger;

	/**
	 * constructor.
	 */
	public ComputerService() {
		// PropertyConfigurator.configure("/main/resources/log4j.properties");
		logger = LoggerFactory.getLogger(getClass());
		companyService = new CompanyService();
		computerDao = new ComputerDao();

	}

	/**
	 * insert new computer into db.
	 * @param computerEntity .
	 */
	public void insertComputer(ComputerEntity computerEntity) {

		computerDao.create(computerEntity);
	}

	/**
	 * get computer by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 */
	public ComputerEntity getComputerById(String strId) {

		if (StringUtils.isNumeric(strId)) {
			int id = Integer.parseInt(strId);
			return computerDao.find(id);
		}

		return null;
	}

	/**
	 * update computer into db.
	 * @param computerEntity .
	 * @return Computer which been update
	 */
	public ComputerEntity update(ComputerEntity computerEntity) {

		return computerDao.update(computerEntity);

	}

	/**
	 * get Computers list.
	 * @return list of computerDTO
	 */
	public List<ComputerEntity> getComputers() {

		return computerDao.getAll();

	}

	/**
	 * get computer List from pageNumber.
	 * @param start .
	 * @param itemPerPage .
	 * @param researchString .
	 * @return List of computerDTO corresponding to page "pageNumber"
	 */
	public List<ComputerEntity> getComputers(int start, String itemPerPage, String researchString) {

		List<ComputerEntity> computerEntities = null;
		if (itemPerPage == null) {
			computerEntities = computerDao.getComputers(start, 10, researchString);
		} else if (StringUtils.isNumeric(itemPerPage)) {
			computerEntities = computerDao.getComputers(start, Integer.parseInt(itemPerPage), researchString);
		}
		return computerEntities;
	}

	/**
	 * get total item of table.
	 * @param researchString .
	 * @return integer
	 */
	public int getTotalItem(String researchString) {

		return computerDao.getCount(researchString);
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
