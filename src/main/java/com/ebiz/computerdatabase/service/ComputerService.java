package com.ebiz.computerdatabase.service;

import com.ebiz.computerdatabase.dto.CompanyDTO;
import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Company;
import com.ebiz.computerdatabase.entities.Computer;
import com.ebiz.computerdatabase.exception.BadRequestException;
import com.ebiz.computerdatabase.log.LoggerSing;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;


import com.ebiz.computerdatabase.persistence.dao.CompanyDao;
import com.ebiz.computerdatabase.persistence.dao.ComputerDao;

import com.ebiz.computerdatabase.validator.ComputerDTOValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ComputerService {

	@Autowired
	private CompanyDao companyDao;
    @Autowired
	private ComputerDao computerDao;



	Logger logger =LoggerFactory.getLogger(getClass());



	public void insertComputer(ComputerDTO computerDTO) {

		ComputerDTOValidator.validArgument(computerDTO);
		if(companyDao.findOne(computerDTO.getCompanyId())!=null) {
			computerDao.save(ComputerDTOMapper.DtoToComputer(computerDTO));
		}else{
			throw new BadRequestException("CompanyId not valid");
		}
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public Computer getComputerById(String strId) {

			if (StringUtils.isNumeric(strId)) {
				return computerDao.findComputerById(Integer.parseInt(strId));
			}
			return null;

	}


	public boolean update(ComputerDTO computerDTO) {

		LoggerSing.logger.error("Id computer "+computerDTO.getId());
		if(computerDTO.getId()<= 0){
			throw new BadRequestException("Id is required");
		}
		Computer oldComputer = computerDao.findComputerById(computerDTO.getId());
		LoggerSing.logger.error("computer Old "+oldComputer);
		computerDTO = ComputerDTOMapper.mapOldAndNewComputer(oldComputer,computerDTO);
		ComputerDTOValidator.validArgument(computerDTO);
		computerDao.save(ComputerDTOMapper.DtoToComputer(computerDTO));
		return true;

	}



	public List<Computer> getComputers() {

		return computerDao.findAll();

	}

	@Transactional
	public Page<Computer> getPage(PageRequest pageRequest,String name) {

		if(name == null){
			return computerDao.findAll(pageRequest);
		}else{
			return computerDao.findComputerByNameContainingOrCompanyNameContaining(name, name,pageRequest);
		}

	}




	@Transactional
	public void deleteComputer(List<Integer> computerId) {

		computerDao.deleteByIdIn(computerId);
		computerDao.flush();

	}

}
