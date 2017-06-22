package com.ebiz.computerdatabase.mapper;

import com.ebiz.computerdatabase.dto.ComputerDTO;
import com.ebiz.computerdatabase.entities.Company;
import com.ebiz.computerdatabase.entities.Computer;
import com.ebiz.computerdatabase.exception.BadRequestException;


import java.util.ArrayList;
import java.util.List;

public class ComputerDTOMapper {



	public static Computer DtoToComputer(ComputerDTO computerDTO) throws NullPointerException {

		Computer computer = new Computer();
		computer.setId(computerDTO.getId());
		computer.setName(computerDTO.getName());
		computer.setIntroduced(computerDTO.getIntroduced());
		computer.setDiscontinued(computerDTO.getDiscontinued());

		Company company = new Company();
		company.setId(computerDTO.getCompanyId());
		company.setName(computerDTO.getCompanyName());
		computer.setCompany(company);

		return computer;

	}

	public static ComputerDTO createComputerDTO(Computer computer) throws NullPointerException {

		ComputerDTO.ComputerDTOBuilder computerBuilder = new ComputerDTO.ComputerDTOBuilder();
		computerBuilder
				.id(computer.getId())
				.name(computer.getName())
				.introduced(computer.getIntroduced())
				.discontinued(computer.getDiscontinued());
				if(computer.getCompany()!=null){
					computerBuilder
							.companyId(computer.getCompany().getId())
							.companyName(computer.getCompany().getName());

				}

		return computerBuilder.build();

	}



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

	public static ComputerDTO mapOldAndNewComputer(Computer oldComputer, ComputerDTO newComputer){

		if(oldComputer == null){
			throw new BadRequestException("Computer doesn't exist");
		}
		if(newComputer.getName()==null){
			newComputer.setName(oldComputer.getName());
		}
		if(newComputer.getIntroduced() == null){
			newComputer.setIntroduced(oldComputer.getIntroduced());
		}
		if(newComputer.getDiscontinued() == null){
			newComputer.setDiscontinued(oldComputer.getDiscontinued());
		}
		if(newComputer.getCompanyId()<=0){
			if(oldComputer.getCompany()!=null){
				newComputer.setCompanyId(oldComputer.getCompany().getId());
				newComputer.setCompanyName(oldComputer.getCompany().getName());
			}

		}

		return newComputer;

	}
	

}
