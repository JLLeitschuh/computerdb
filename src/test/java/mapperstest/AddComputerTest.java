package mapperstest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.ebiz.computerdatabase.dto.ComputerDTO.ComputerDTOBuilder;
import com.ebiz.computerdatabase.exception.DTOException;
import com.ebiz.computerdatabase.mapper.ComputerDTOMapper;
import com.ebiz.computerdatabase.model.ComputerEntity;
import com.ebiz.computerdatabase.service.ComputerService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:WEB-INF/spring/applicationContext.xml")
@Component
public class AddComputerTest {

	@Autowired
	ComputerService computerService;

	@Test
	public void addComputer() {

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
