package seleniumtest;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.ebiz.computerdatabase.log.LoggerSing;

public class ComputerSeleniumTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddComputerSelenium() throws Exception {

		driver.get(baseUrl + "/computerdatabase/dashboard");

		// keep total computer number found
		WebElement div = driver.findElement(By.id("homeTitle"));

		int numberComputerBefore = Integer.parseInt(div.getText().replaceAll("\\D+", ""));
		// Look for computers which name contain "Selenium"
		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("selenium");
		driver.findElement(By.id("searchsubmit")).click();

		div = driver.findElement(By.id("homeTitle"));

		// keep number computer found with searching by name
		int numberItemBefore = Integer.parseInt(div.getText().replaceAll("\\D+", ""));

		driver.findElement(By.id("addComputer")).click();

		long timeStamp = System.currentTimeMillis();
		// Set value to add new computer
		driver.findElement(By.id("name")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("name")).sendKeys("Test Selenium" + timeStamp);

		driver.findElement(By.id("introduced")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("introduced")).sendKeys("01-01-2012");

		driver.findElement(By.id("discontinued")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("discontinued")).sendKeys("01-01-2012");
		new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("IBM");

		// add computer
		driver.findElement(By.id("addComputer")).click();
		// back to dashboard
		driver.findElement(By.linkText("Cancel")).click();

		div = driver.findElement(By.id("homeTitle"));
		int numberComputerAfterAdd = Integer.parseInt(div.getText().replaceAll("\\D+", ""));

		// Compare number of total computer before and after adding. It must be
		// increment by 1
		assertEquals(numberComputerBefore + 1, numberComputerAfterAdd);

		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("selenium");
		driver.findElement(By.id("searchsubmit")).click();

		div = driver.findElement(By.id("homeTitle"));
		// Compare number of computer found with name selenium before and after
		// adding. It must be increment by 1
		int numberItemAfter = Integer.parseInt(div.getText().replaceAll("\\D+", ""));

		assertEquals(numberItemBefore + 1, numberItemAfter);
		testEditComputerSelenium(timeStamp);

	}

	/**
	 * edit computer test.
	 * @param timeStamp .
	 * @throws Exception .
	 */
	public void testEditComputerSelenium(long timeStamp) throws Exception {

		driver.get(baseUrl + "/computerdatabase/dashboard");

		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("Test Selenium" + timeStamp);
		driver.findElement(By.id("searchsubmit")).click();

		// Search element and test if element is found.
		WebElement div = driver.findElement(By.id("homeTitle"));
		int numberComputerFound = Integer.parseInt(div.getText().replaceAll("\\D+", ""));
		assertEquals(numberComputerFound, 1);

		// search element and edit name .
		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("Selenium");
		driver.findElement(By.id("searchsubmit")).click();
		driver.findElement(By.linkText("Test Selenium" + timeStamp)).click();
		driver.findElement(By.id("name")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("name")).sendKeys("Selenium" + timeStamp);
		driver.findElement(By.id("edit")).click();
		driver.findElement(By.linkText("Cancel")).click();
		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("Selenium" + timeStamp);
		driver.findElement(By.id("searchsubmit")).click();

		div = driver.findElement(By.id("homeTitle"));
		numberComputerFound = Integer.parseInt(div.getText().replaceAll("\\D+", ""));

		// Search element and test if element is found. after changing
		assertEquals(numberComputerFound, 1);

		testDeleteComputerSelenium(timeStamp);
	}

	/**
	 * test delete object.
	 * @param timeStamp .
	 */
	public void testDeleteComputerSelenium(long timeStamp) {

		driver.get(baseUrl + "/computerdatabase/dashboard");

		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("Selenium" + timeStamp);
		driver.findElement(By.id("searchsubmit")).click();

		// Search object to delete and test if exists only once into database
		WebElement div = driver.findElement(By.id("homeTitle"));
		int numberComputerFound = Integer.parseInt(div.getText().replaceAll("\\D+", ""));
		assertEquals(numberComputerFound, 1);

		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.xpath("(//input[@name='cb'])[1]")).click();
		driver.findElement(By.xpath("//a[@id='deleteSelected']/i")).click();
		assertTrue(
				closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected computers[\\s\\S]$"));

		// Search object to test if it have been deleted
		driver.findElement(By.id("searchbox")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("searchbox")).sendKeys("Selenium" + timeStamp);
		driver.findElement(By.id("searchsubmit")).click();
		div = driver.findElement(By.id("homeTitle"));
		numberComputerFound = Integer.parseInt(div.getText().replaceAll("\\D+", ""));
		assertEquals(numberComputerFound, 0);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
