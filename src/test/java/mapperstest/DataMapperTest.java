package mapperstest;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


import mappers.DataMapper;



public class DataMapperTest {
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
    public void convertStringToDateTest() {
		DataMapper.convertStringToDate("1955-01-01");
		Assert.assertNotNull(DataMapper.convertStringToDate("1955-01-01"));
		Assert.assertNotNull(DataMapper.convertStringToDate("1975-01-01"));
				
    }
	
	

}