package serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;

import dao.ComputerDao;
import dto.ComputerDTO;
import mappers.DataMapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.CompanyEntity;
import model.ComputerEntity;
import services.ComputerService;

public class ComputerTestMockitoTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testComputer(){
		ComputerEntity computerEntity = Mockito.mock(ComputerEntity.class);

		Mockito.when(computerEntity.getId()).thenReturn(43);
		Mockito.when(computerEntity.getName()).thenReturn("name");

		assertEquals(computerEntity.getId(), 43);
		assertEquals(computerEntity.getName(), "name");

	}

	@Mock
	ComputerService computerService;
	@Test
	public void testComputerService(){

		List<ComputerDTO> list = new ArrayList<ComputerDTO>();
		ComputerDTO.ComputerDTOBuilder computerBuilder= new ComputerDTO.ComputerDTOBuilder().name("Test");
		list.add(computerBuilder.build());

		Mockito.when(computerService.getComputers()).thenReturn(list);
		assertEquals(computerService.getComputers().get(0).getName(),"Test");

		ComputerDTO.ComputerDTOBuilder computerBuilder2= new ComputerDTO.ComputerDTOBuilder().introduced("1966-01-01");
		list.add(computerBuilder2.build());

		Mockito.when(computerService.getComputers()).thenReturn(list);

		assertEquals(computerService.getComputers().get(1).getIntroduced(),"1966-01-01");
		assertEquals(computerService.getComputers().size(),2);

		
		
	
	}
	
	
	@Mock
	ComputerDao computerDao;
	@InjectMocks 
	ComputerService computerService2;
	@Test
	public void testServiceMethodeCaller(){
		
		computerService2.getComputers();
		Mockito.verify(computerDao).getAll();

		computerService2.getComputerById("1");
		Mockito.verify(computerDao).find(1);
	}

	@Test
	public void testComputerDAO(){
		
		List<ComputerEntity> userMap = new ArrayList<>();
		ComputerDao dao = Mockito.mock(ComputerDao.class);
		Mockito.when(dao.update(Mockito.any(ComputerEntity.class))).thenAnswer(i -> {
				ComputerEntity computer=  (ComputerEntity) i.getArguments()[0];
			    userMap.add(computer.getId(), computer);
			    return null;
		});
		Mockito.when(dao.find(Mockito.any(Integer.class))).thenAnswer(i -> {
		    int id = (int) i.getArguments()[0];
		    return userMap.get(id);
		});	
	}



}
