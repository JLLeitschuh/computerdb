package servicetest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.NeverWantedButInvoked;

import dao.ComputerDao;
import dto.ComputerDTO;
import exception.DTOException;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import model.ComputerEntity;
import service.ComputerService;
import ui.Page;

public class ComputerTestMockitoTest {

	/**
	 * setUp.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * test ComputerEntity return methods.
	 */
	@Test
	public void testComputer() {
		ComputerEntity computerEntity = Mockito.mock(ComputerEntity.class);

		Mockito.when(computerEntity.getId()).thenReturn(43);
		Mockito.when(computerEntity.getName()).thenReturn("name");

		assertEquals(computerEntity.getId(), 43);
		assertEquals(computerEntity.getName(), "name");

	}

	/**
	 * test ComputerDTO methods .
	 */
	@Test
	public void testComputerDTO() {

		// mock

		ComputerDTO computerDTO = Mockito.mock(ComputerDTO.class);

		Mockito.when(computerDTO.getId()).thenReturn(43);
		Mockito.when(computerDTO.getName()).thenReturn("name");
		Mockito.when(computerDTO.getCompanyId()).thenReturn(1);
		Mockito.when(computerDTO.getIntroduced()).thenReturn("1955-01-01");
		Mockito.when(computerDTO.getDiscontinued()).thenReturn("1955-01-01");

		assertEquals(computerDTO.getId(), 43);
		assertEquals(computerDTO.getName(), "name");
		assertEquals(computerDTO.getCompanyId(), 1);
		assertEquals(computerDTO.getIntroduced(), "1955-01-01");
		assertEquals(computerDTO.getDiscontinued(), "1955-01-01");

		// spy
		ComputerDTO.ComputerDTOBuilder computerDTOBuilder = Mockito.mock(ComputerDTO.ComputerDTOBuilder.class);
		ComputerDTO computerDTOspy = Mockito.spy(new ComputerDTO(computerDTOBuilder));
		Mockito.doReturn("name").when(computerDTOspy).getName();

		assertEquals(computerDTOspy.getName(), "name");

	}

	@Mock
	ComputerService computerService;

	@Mock
	ComputerDao computerDao;
	@InjectMocks
	ComputerService computerService2;
	@Mock
	Page page;

	/**
	 * test computer DAO.
	 * @throws DTOException .
	 */
	@Test
	public void testComputerDAO() throws DTOException {

		List<ComputerEntity> userMap = new ArrayList<>();
		ComputerDao dao = Mockito.mock(ComputerDao.class);
		Mockito.when(dao.update(Mockito.any(ComputerEntity.class))).thenAnswer(i -> {
			ComputerEntity computer = (ComputerEntity) i.getArguments()[0];
			userMap.add(computer.getId(), computer);
			return null;
		});
		Mockito.when(dao.find(Mockito.any(Integer.class))).thenAnswer(i -> {
			int id = (int) i.getArguments()[0];
			return userMap.get(id);
		});
	}

}
