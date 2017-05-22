package service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ComputerDao;
import dto.ComputerDTO;
import exception.DTOException;
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
	 * @throws DTOException 
	 */
	public ComputerService() throws DTOException {
		// PropertyConfigurator.configure("/main/resources/log4j.properties");
		logger = LoggerFactory.getLogger(getClass());
		companyService = new CompanyService();
		computerDao = new ComputerDao();

	}

	/**
	 * insert new computer into db.
	 * @param computerEntity .
	 * @throws DTOException 
	 */
	public void insertComputer(ComputerEntity computerEntity) throws DTOException {

		computerDao.create(computerEntity);
	}

	/**
	 * get computer by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 * @throws DTOException 
	 */
	public ComputerEntity getComputerById(String strId) throws DTOException {

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
	 * @throws DTOException 
	 */
	public boolean update(ComputerEntity computerEntity) throws DTOException {

		return computerDao.update(computerEntity);

	}

	/**
	 * get Computers list.
	 * @return list of computerDTO
	 * @throws DTOException 
	 */
	public List<ComputerEntity> getComputers() throws DTOException {

		return computerDao.getAll();

	}

	/**
	 * get computer List from pageNumber.
	 * @param start .
	 * @param itemPerPage .
	 * @param researchString .
	 * @return List of computerDTO corresponding to page "pageNumber"
	 * @throws DTOException .
	 */
	public List<ComputerEntity> getComputers(int start, String itemPerPage, String researchString, String orderBy)
			throws DTOException {

		List<ComputerEntity> computerEntities = null;
		if (itemPerPage == null) {
			computerEntities = computerDao.getComputers(start, 10, researchString, orderBy);
		} else if (StringUtils.isNumeric(itemPerPage)) {
			computerEntities = computerDao.getComputers(start, Integer.parseInt(itemPerPage), researchString, orderBy);
		}
		return computerEntities;
	}

	/**
	 * get total item of table.
	 * @param researchString .
	 * @return integer
	 * @throws DTOException .
	 */
	public int getTotalItem(String researchString) throws DTOException {

		return computerDao.getCount(researchString);
	}

	/**
	 * delete computer with id strId.
	 * @param strId .
	 * @throws DTOException 
	 */
	public void deleteComputer(String[] computerIdString) throws DTOException {

		computerDao.deleteComputers(computerIdString);
	}

}
