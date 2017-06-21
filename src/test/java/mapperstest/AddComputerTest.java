package mapperstest;

import static org.junit.Assert.*;

import java.util.List;

import com.ebiz.computerdatabase.entities.Computer;
import org.junit.Test;

import com.ebiz.computerdatabase.dto.ComputerDTO.ComputerDTOBuilder;
import com.ebiz.computerdatabase.service.ComputerService;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Component
public class AddComputerTest {


	ComputerService computerService;

	@Test
	public void addComputer() {

		int computerTotal = 0;

		long timeStamp = System.currentTimeMillis();
		// Set value to add new computer

		String computerName = "Test Selenium" + timeStamp;

		int computerNameCount = searchComputer(computerName);
		ComputerDTOBuilder dtoBuilder = new ComputerDTOBuilder();
		dtoBuilder.name(computerName).introduced("01-01-2012").discontinued("01-01-2012").companyId(1);
		computerService.insertComputer(dtoBuilder.build());
		int computerTotalAfter = 0;

		int computerNameCountAfter = searchComputer(computerName);
		assertEquals(computerTotalAfter, computerTotal + 1);
		assertEquals(computerNameCountAfter, computerNameCount + 1);

		String id = "";

		List<Computer> list = computerService.getComputers();
		for (Computer computer : list) {
			if (computer.getName().equalsIgnoreCase(computerName)) {
				id = String.valueOf(computer.getId());
			}
		}

		//computerService.deleteComputer(new String[] { id });
		computerNameCountAfter = searchComputer(computerName);
		assertEquals(computerNameCountAfter, computerNameCount);

	}

	public int searchComputer(String name) {

		int computerTotal = 0;


		return computerTotal;
	}
}
