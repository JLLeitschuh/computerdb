package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Computer;
import com.ebiz.computerdatabase.model.CompanyEntity;
import com.ebiz.computerdatabase.model.ComputerEntity;


import java.util.ArrayList;
import java.util.List;

public class ComputerDTOMapper {

	/**
	 * create ComputerDTO corresponding to computerEntity.
	 * @param computer entity.
	 * @return ComputerDTO map with parameter computer
	 */
	public static ComputerDTO createComputerDTO(ComputerEntity computer) {

		ComputerDTO.ComputerDTOBuilder computerDTOBuilder = new ComputerDTO.ComputerDTOBuilder();

		if (computer != null) {
			computerDTOBuilder.id(computer.getId()).name(computer.getName());

			if (computer.getIntroduced() != null) {
				computerDTOBuilder.introduced(computer.getIntroduced().toString());
			}
			if (computer.getDiscontinued() != null) {
				computerDTOBuilder.discontinued(computer.getDiscontinued().toString());
			}

			computerDTOBuilder.companyId(computer.getCompanyId());
			computerDTOBuilder.companyName(computer.getCompanyName());
		}

		return computerDTOBuilder.build();
	}

	/**
	 * create computer from computerDTO.
	 * @param computerDTO .
	 * @return Computer
	 * @throws NullPointerException .
	 */

	public static ComputerEntity createComputer(ComputerDTO computerDTO) throws NullPointerException {

		ComputerEntity.ComputerBuilder computerBuilder = ComputerEntity.computerBuilder();

		if (computerDTO.getName() == null || computerDTO.getName().trim().isEmpty()) {
			throw new NullPointerException();
		}
		computerBuilder.id(computerDTO.getId()).name(computerDTO.getName());
		computerBuilder.introduced(DataMapper.convertStringToDate(computerDTO.getIntroduced()));
		computerBuilder.discontinued(DataMapper.convertStringToDate(computerDTO.getDiscontinued()));
		computerBuilder.companyId(computerDTO.getCompanyId());


		return computerBuilder.build();

	}

	public static ComputerDTO createComputerDTO(Computer computer) throws NullPointerException {

		ComputerDTO.ComputerDTOBuilder computerBuilder = new ComputerDTO.ComputerDTOBuilder();
		computerBuilder.id(computer.getId()).introduced(computer.getIntroduced()).discontinued(computer.getDiscontinued()).companyId(computer.getCompany().getId()).companyName(computer.getCompany().getName());

		return computerBuilder.build();

	}


	/**
	 * create computerDTO list from computer list.
	 * @param computerList .
	 * @return ComputerDTO List
	 */
	/*public static List<ComputerDTO> createComputerDTOList(List<ComputerEntity> computerList) {
		ArrayList<ComputerDTO> computerDTOList = new ArrayList<ComputerDTO>();

		for (ComputerEntity computer : computerList) {
			computerDTOList.add(createComputerDTO(computer));
		}

		return computerDTOList;

	}*/


	/**
	 * create computerDTO list from computer list.
	 * @param computerList .
	 * @return ComputerDTO List
	 */
	public static List<ComputerDTO> createComputerDTOList(List<Computer> computerList) {
		ArrayList<ComputerDTO> computerDTOList = new ArrayList<ComputerDTO>();

		for (Computer computer : computerList) {
			computerDTOList.add(createComputerDTO(computer));
		}

		return computerDTOList;

	}
	

}
