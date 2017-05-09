package mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import model.ComputerEntity;

public class ComputerTestMockitoTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void TestComputer(){
		ComputerEntity computerEntity = Mockito.mock(ComputerEntity.class);
		
		Mockito.when(computerEntity.getId()).thenReturn(43);
		Mockito.when(computerEntity.getName()).thenReturn("name");
		
		assertEquals(computerEntity.getId(), 43);
		assertEquals(computerEntity.getName(), "name");
		
	}
	
	
	
}
