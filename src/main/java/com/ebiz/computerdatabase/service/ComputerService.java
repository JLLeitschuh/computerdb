package com.ebiz.computerdatabase.service;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Computer;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;


import com.ebiz.computerdatabase.persistence.dao.ComputerDao;
import com.ebiz.computerdatabase.exception.DAOException;
import static com.ebiz.computerdatabase.persistence.dao.DaoUtils.*;


import com.ebiz.computerdatabase.persistence.dao.ComputerRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


@Service
public class ComputerService {

    @Autowired
	private ComputerDao computerDao;

	@Autowired
	private ComputerRepository computerRepository;


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
	public List<Computer> getComputers() {

		return computerRepository.findAll();

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Computer> getPage(PageRequest pageRequest,String name) {

		if(name == null){
			return computerRepository.findAll(pageRequest);
		}else{
			return computerRepository.findComputerByNameContainingOrCompanyNameContaining(name, name,pageRequest);
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
	 * @param computerId
	 * @throws DAOException .
	 */
	@Transactional
	public void deleteComputer(List<Integer> computerId) {

		computerRepository.deleteByIdIn(computerId);
		computerRepository.flush();

		//computerDao.deleteComputers(computerIdString);


	}

}
