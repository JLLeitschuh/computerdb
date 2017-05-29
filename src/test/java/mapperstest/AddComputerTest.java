package mapperstest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dto.ComputerDTO.ComputerDTOBuilder;
import exception.DTOException;
import mapper.ComputerDTOMapper;
import model.ComputerEntity;
import service.ComputerService;

public class AddComputerTest {

	@Test
	public void addComputer() {
		ComputerService computerService = ComputerService.getComputerService();
		int computerTotal = 0;

		try {
			computerTotal = computerService.getTotalItem(null);
		} catch (DTOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long timeStamp = System.currentTimeMillis();
		// Set value to add new computer

		String computerName = "Test Selenium" + timeStamp;

		int computerNameCount = searchComputer(computerName);
		ComputerDTOBuilder dtoBuilder = new ComputerDTOBuilder();
		dtoBuilder.name(computerName).introduced("01-01-2012").discontinued("01-01-2012").companyId(1).companyName("");
		computerService.insertComputer(ComputerDTOMapper.createComputer(dtoBuilder.build()));
		int computerTotalAfter = 0;
		try {
			computerTotalAfter = computerService.getTotalItem("");
		} catch (DTOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int computerNameCountAfter = searchComputer(computerName);
		assertEquals(computerTotalAfter, computerTotal + 1);
		assertEquals(computerNameCountAfter, computerNameCount + 1);

		String id = "";

		List<ComputerEntity> list = computerService.getComputers();
		for (ComputerEntity computerEntity : list) {
			if (computerEntity.getName().equalsIgnoreCase(computerName)) {
				id = String.valueOf(computerEntity.getId());
			}
		}

		computerService.deleteComputer(new String[] { id });
		computerNameCountAfter = searchComputer(computerName);
		assertEquals(computerNameCountAfter, computerNameCount);

	}

	public int searchComputer(String name) {
		ComputerService computerService = ComputerService.getComputerService();
		int computerTotal = 0;
		try {
			computerTotal = computerService.getTotalItem(name);
		} catch (DTOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computerTotal;
	}
}
