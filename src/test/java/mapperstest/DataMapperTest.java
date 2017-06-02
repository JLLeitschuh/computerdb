package mapperstest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import mapper.DataMapper;

public class DataMapperTest {

	/**
	 * setUp mockito initialization.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * test data mapper.
	 */
	@Test
	public void convertStringToDateTest() {
		DataMapper.convertStringToDate("01-01-2012");
		Assert.assertNotNull(DataMapper.convertStringToDate("01-01-2012"));
		Assert.assertNotNull(DataMapper.convertStringToDate("01-01-2012"));
	}

}
