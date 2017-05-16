package seleniumtest;


import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumTest {
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
	public void testSelenium() throws Exception {
		driver.get(baseUrl + "/computerdatabase/dashboard");

		driver.findElement(By.cssSelector("a > span")).click();
		driver.findElement(By.cssSelector("a > span")).click();
		driver.findElement(By.cssSelector("a > span")).click();
		driver.findElement(By.cssSelector("a > span")).click();
		driver.findElement(By.linkText("1")).click();
		driver.findElement(By.linkText("3")).click();

		driver.findElement(By.linkText("«")).click();
		driver.findElement(By.linkText("«")).click();
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("»")).click();
		driver.findElement(By.linkText("4")).click();
		driver.findElement(By.linkText("2")).click();
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
