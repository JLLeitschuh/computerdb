package mapperstest;

import org.junit.Assert;
import org.junit.Test;

import mappers.DataMapper;

public class DataMapperTest {
	
	@Test
    public void convertStringToDateTest() {
		DataMapper.convertStringToDate("1955-01-01");
		Assert.assertNotNull(DataMapper.convertStringToDate("1955-01-01"));
		Assert.assertNotNull(DataMapper.convertStringToDate("1975-01-01"));
				
    }
	
	@Test(expected=java.time.format.DateTimeParseException.class)
	public void convertStringToDateTestException() {
		DataMapper.convertStringToDate("test");
	}

}
