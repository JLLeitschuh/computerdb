package service;

import java.sql.Connection;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistence.ConnectionSingleton;
import persistence.dao.ComputerDao;
import persistence.dao.DaoUtils;
import dto.ComputerDTO;
import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import mapper.ComputerDTOMapper;
import model.CompanyEntity;
import model.ComputerEntity;
import model.Page;
import model.PageRequest;

import static log.LoggerSing.logger;
import static persistence.dao.DaoUtils.*;

public class ComputerService {

	ComputerDao computerDao;
	CompanyService companyService;
	List<ComputerDTO> computerDTOList;
	private static final ComputerService COMPUTER_SERVICE = new ComputerService();
	String research = "";
	Logger logger;

	public static ComputerService getComputerService() {
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
	public void insertComputer(ComputerEntity computerEntity) {

		try {

			computerDao.create(computerEntity);
		} catch (DTOException dtoException) {
			logger.error(dtoException.getMessage());
			throw new RuntimeException(dtoException.getMessage());
		}
	}

	/**
	 * insert new computer into db.
	 * @param request .
	 */
	public void insertComputer(HttpServletRequest request) {

		Connection connection = null;
		try {
			connection = ConnectionSingleton.getInstance().getConnection();
			autoCommit(connection, false);

			String computerName = request.getParameter("name");
			String introduced = request.getParameter("introduced");
			String discontinued = request.getParameter("discontinued");
			String companyId = request.getParameter("companyId");

			ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
			computerDTOBuilder.name(computerName).introduced(introduced).discontinued(discontinued);

			CompanyEntity company = null;

			// look for company into database.

			company = companyService.findCompanyById(companyId);
			logger.info("insert name = " + computerName + " introduced = " + introduced + " discontinued = "
					+ discontinued + " companyId = " + companyId);
			if (company != null) {
				computerDTOBuilder.companyId(company.getId());
				computerDTOBuilder.companyName(company.getName());
			}
			logger.info("company name  = "+computerDTOBuilder.build().getCompanyName() +  " companyId = " + computerDTOBuilder.build().getCompanyId());
			computerDao.create(ComputerDTOMapper.createComputer(computerDTOBuilder.build()));
			commit(connection);

		} catch (DTOException e) {
			rollback(connection);
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		} finally {
			closeConnection(connection);

		}
	}

	/**
	 * get computer by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 * @throws DTOException .
	 */
	public ComputerEntity getComputerById(String strId) {

		Connection connection = null;
		try {
			connection = ConnectionSingleton.getInstance().getConnection();
			autoCommit(connection, false);
			if (StringUtils.isNumeric(strId)) {
				int id = Integer.parseInt(strId);
				ComputerEntity computerEntity = computerDao.find(id);
				commit(connection);
				return computerEntity;

			}

			return null;
		} catch (DTOException e) {
			rollback(connection);
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		} finally {
			closeConnection(connection);

		}
	}

	/**
	 * update computer into db.
	 * @param computerEntity .
	 * @return Computer which been update
	 * @throws DTOException .
	 */
	public boolean update(HttpServletRequest request) {

		Connection connection = null;
		try {
			connection = ConnectionSingleton.getInstance().getConnection();
			autoCommit(connection, false);

			String computerId = request.getParameter("id");
			String computerName = request.getParameter("name");
			String introduced = request.getParameter("introduced");
			String discontinued = request.getParameter("discontinued");
			String companyId = request.getParameter("companyId");
				
			String computername_before = request.getParameter("name_before");
			String introduced_before = request.getParameter("introduced_before");
			String discontinued_before = request.getParameter("discontinued_before");
			String companyId_before = request.getParameter("company_before");

			logger.info("computerId Resquest" + computerId);

			ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
			// test computer Id before edit computer
			if (computerId != null && StringUtils.isNumeric(computerId)) {

				computerDTOBuilder.id(Integer.parseInt(computerId));
			}
			
			computerDTOBuilder.name(computerName).introduced(introduced).discontinued(discontinued);

			CompanyEntity company = null;

			// find corresponding company corresponding to company Id

			company = companyService.findCompanyById(companyId);

			if (company != null) {
				computerDTOBuilder.companyId(company.getId());
				computerDTOBuilder.companyName(company.getName());
			}
			boolean success = computerDao.update(ComputerDTOMapper.createComputer(computerDTOBuilder.build()));
			commit(connection);
			return success;

		} catch (DTOException e) {
			rollback(connection);
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		} finally {
			closeConnection(connection);

		}

	}

	/**
	 * update computer into db.
	 * @param computerEntity .
	 * @return Computer which been update
	 * @throws DTOException .
	 */
	public boolean update(ComputerEntity computerEntity) {

		try {
			return computerDao.update(computerEntity);
		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * get Computers list.
	 * @return list of computerDTO
	 * @throws DTOException .
	 */
	public List<ComputerEntity> getComputers() {

		Connection connection = null;
		List<ComputerEntity> list = null;
		try {
			connection = ConnectionSingleton.getInstance().getConnection();
			autoCommit(connection, false);

			list = computerDao.getAll();
			commit(connection);

		} catch (DTOException e) {

			rollback(connection);
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			closeConnection(connection);
		}

		return list;

	}

	public Page<ComputerDTO> getPage(PageRequest pageRequest) {

		Connection connection = null;
		try {
			connection = ConnectionSingleton.getInstance().getConnection();
			Page<ComputerDTO> page = new Page<ComputerDTO>();

			autoCommit(connection, false);
			int numberTotalPage = computerDao.getCount(pageRequest.getResearch(), connection);
			logger.info("number Item " + numberTotalPage);
			page.setNumberTotalItems(numberTotalPage, pageRequest);
			int start = (pageRequest.getPage() - 1) * pageRequest.getItemNumber();
			List<ComputerEntity> computerEntities = computerDao.getComputers(start, pageRequest, connection);
			page.setItems(ComputerDTOMapper.createComputerDTOList(computerEntities));
			commit(connection);
			return page;

		} catch (DTOException e) {
			rollback(connection);
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			closeConnection(connection);

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
	public void deleteComputer(String[] computerIdString) {
		Connection connect = null;
		try {
			connect = ConnectionSingleton.getInstance().getConnection();
			autoCommit(connect, false);
			computerDao.deleteComputers(computerIdString, connect);
			commit(connect);
		} catch (DTOException e) {

			rollback(connect);

			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			closeConnection(connect);
		}

	}

}
