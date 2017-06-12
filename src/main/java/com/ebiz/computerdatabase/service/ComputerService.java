package com.ebiz.computerdatabase.service;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.model.Page;
import com.ebiz.computerdatabase.model.PageRequest;
import com.ebiz.computerdatabase.persistence.dao.ComputerDao;
import com.ebiz.computerdatabase.exception.DAOException;
import static com.ebiz.computerdatabase.persistence.dao.DaoUtils.*;



import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class ComputerService {

    @Autowired
	private ComputerDao computerDao;


	Logger logger =LoggerFactory.getLogger(getClass());


	/**
	 * insert new computer into db.
	 * @param computerDTO .
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertComputer(ComputerDTO computerDTO) {

		try {
			computerDao.create(ComputerDTOMapper.createComputer(computerDTO));
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
	}

	/**
	 * get computer by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 * @throws DAOException .
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ComputerEntity getComputerById(String strId) {
		try {

			if (StringUtils.isNumeric(strId)) {
				int id = Integer.parseInt(strId);
				ComputerEntity computerEntity = computerDao.find(id);
				return computerEntity;
			}
			return null;
		} catch (DAOException e) {

			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
	}

	/**
	 * update computer into db.
	 * @return Computer which been update
	 * @throws DAOException .
	 */
	public boolean update(ComputerDTO computerDTO) {

		try {
			boolean success = computerDao.update(ComputerDTOMapper.createComputer(computerDTO));
			return success;

		} catch (DAOException e) {

			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}

	}

	/**
	 * update computer into db.
	 * @param computerEntity .
	 * @return Computer which been update
	 * @throws DAOException .
	 */
	public boolean update(ComputerEntity computerEntity) {

		try {
			return computerDao.update(computerEntity);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * get Computers list.
	 * @return list of computerDTO
	 * @throws DAOException .
	 */
	public List<ComputerEntity> getComputers() {


		List<ComputerEntity> list = null;
		try {
			list = computerDao.getAll();

		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

		return list;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<ComputerDTO> getPage(PageRequest pageRequest) {
		try {

			Page<ComputerDTO> page = new Page<ComputerDTO>();
			int numberTotalPage = computerDao.getCount(pageRequest.getResearch());
			page.setNumberTotalItems(numberTotalPage, pageRequest);
			int start = (pageRequest.getPage() - 1) * pageRequest.getItemNumber();
			List<ComputerEntity> computerEntities = computerDao.getComputers(start, pageRequest);
			page.setItems(ComputerDTOMapper.createComputerDTOList(computerEntities));

			return page;

		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * get total item of table.
	 * @param researchString .
	 * @return integer
	 * @throws DAOException .
	 */
	public int getTotalItem(String researchString) throws DAOException {

		return computerDao.getCount(researchString);
	}

	/**
	 * delete computer with id strId.
	 * @param computerIdString .
	 * @throws DAOException .
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteComputer(String[] computerIdString) {

		try {
			computerDao.deleteComputers(computerIdString);

		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

}
