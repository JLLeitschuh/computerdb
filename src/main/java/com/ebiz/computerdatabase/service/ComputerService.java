package com.ebiz.computerdatabase.service;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.model.Page;
import com.ebiz.computerdatabase.model.PageRequest;
import com.ebiz.computerdatabase.persistence.dao.ComputerDao;
import com.ebiz.computerdatabase.exception.DTOException;
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
    @Autowired
	private CompanyService companyService;

	List<ComputerDTO> computerDTOList;
	private static final ComputerService COMPUTER_SERVICE = new ComputerService();
	String research = "";
	Logger logger =LoggerFactory.getLogger(getClass());


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
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertComputer(HttpServletRequest request) {

		try {
			String computerName = request.getParameter("name");
			String introduced = request.getParameter("introduced");
			String discontinued = request.getParameter("discontinued");
			String companyId = request.getParameter("companyId");

			ComputerDTO.ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
			computerDTOBuilder.name(computerName).introduced(introduced).discontinued(discontinued);
			CompanyEntity company = companyService.findCompanyById(companyId);
			if (company != null) {
				computerDTOBuilder.companyId(company.getId());
				computerDTOBuilder.companyName(company.getName());
			}
			computerDao.create(ComputerDTOMapper.createComputer(computerDTOBuilder.build()));
		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
	}

	/**
	 * get computer by id.
	 * @param strId .
	 * @return ComputerDTO corresponding to computer object with id strId
	 * @throws DTOException .
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
		} catch (DTOException e) {

			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

		}
	}

	/**
	 * update computer into db.
	 * @return Computer which been update
	 * @throws DTOException .
	 */
	public boolean update(HttpServletRequest request) {


		try {
			String computerId = request.getParameter("id");
			String computerName = request.getParameter("name");
			String introduced = request.getParameter("introduced");
			String discontinued = request.getParameter("discontinued");
			String companyId = request.getParameter("companyId");


			logger.info("computerId Resquest" + computerId);

			ComputerDTO.ComputerDTOBuilder computerDTOBuilder = ComputerDTO.getComputerDtoBuilder();
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
			return success;

		} catch (DTOException e) {

			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());

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


		List<ComputerEntity> list = null;
		try {
			list = computerDao.getAll();

		} catch (DTOException e) {
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

		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
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
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteComputer(String[] computerIdString) {

		try {
			computerDao.deleteComputers(computerIdString);

		} catch (DTOException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

}
