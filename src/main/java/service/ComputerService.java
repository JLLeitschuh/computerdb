package service;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistence.ConnectionSingleton;
import persistence.dao.ComputerDao;
import persistence.dao.DaoUtils;
import dto.ComputerDTO;
import exception.DTOException;
import mapper.ComputerDTOMapper;
import model.ComputerEntity;
import model.Page;
import model.PageRequest;

import static persistence.dao.DaoUtils.*;

public class ComputerService {

	ComputerDao computerDao;
	CompanyService companyService;
	List<ComputerDTO> computerDTOList;
	private static final ComputerService COMPUTER_SERVICE = new ComputerService();
	String research = "";
	Logger logger;

	public static ComputerService getComputerService(){
		return COMPUTER_SERVICE;
	}
	/**
	 * constructor.
	 * @throws DTOException .
	 */
	private ComputerService() {
		// PropertyConfigurator.configure("/main/resources/log4j.properties");
		logger = LoggerFactory.getLogger(getClass());
		companyService = CompanyService.getCompanyService();
		computerDao = ComputerDao.getComputerDao();

	}

	/**
	 * insert new computer into db.
	 * @param computerEntity .
	 * @throws DTOException .
	 */
	public void insertComputer(ComputerEntity computerEntity) throws DTOException {

		computerDao.create(computerEntity);
	}

	/**
	 * get computer by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 * @throws DTOException .
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
	 * @throws DTOException .
	 */
	public boolean update(ComputerEntity computerEntity) throws DTOException {

		return computerDao.update(computerEntity);

	}

	/**
	 * get Computers list.
	 * @return list of computerDTO
	 * @throws DTOException .
	 */
	public List<ComputerEntity> getComputers() throws DTOException {

		return computerDao.getAll();

	}

	public Page<ComputerDTO> getPage(PageRequest pageRequest) {

		Connection connection = null;
		try {
			connection = ConnectionSingleton.getInstance().getConnection();
			Page<ComputerDTO> page = new Page<ComputerDTO>();

			autoCommit(connection, false);
			int numberTotalPage = computerDao.getCount(pageRequest.getResearch(), connection);
			page.setNumberTotalItems(numberTotalPage, pageRequest);
			int start = (pageRequest.getPage() - 1) * pageRequest.getItemNumber();
			List<ComputerEntity> computerEntities = computerDao.getComputers(start, pageRequest, connection);
			page.setItems(ComputerDTOMapper.createComputerDTOList(computerEntities));
			commit(connection);
			return page;

		} catch (DTOException e) {

			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				closeConnection(connection);
			} catch (DTOException e) {

				e.printStackTrace();
			}
		}

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
	 * @param computerIdString .
	 * @throws DTOException .
	 */
	public void deleteComputer(String[] computerIdString) throws DTOException {
		Connection connect = ConnectionSingleton.getInstance().getConnection();
		try {
			computerDao.deleteComputers(computerIdString, connect);
		} catch (DTOException e) {

		} finally {

		}

	}

}
