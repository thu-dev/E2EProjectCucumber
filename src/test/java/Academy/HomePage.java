package Academy;

import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.ForgotPassword;
import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.base;

public class HomePage extends base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String Username, String Password, String Text) throws IOException {
		driver.get(prop.getProperty("url"));

		// one is inheritance
		// creating object to that class and invoke methods of it
		LandingPage l = new LandingPage(driver);
		l.getLogin().click(); //equal to driver.findElement(By.cssSelector())
//		LoginPage lp = l.getLogin();  
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);
		
		log.info(Text);

		lp.getLogin().click();
		
		// change to PageObject
		ForgotPassword fp = lp.forgotPassword();
		fp.getEmail().sendKeys("123@gmail.com");
		fp.sendMedInstruction().click();
	
	}
	
	@DataProvider
	public Object[][] getData() {
		// Row stand for how many different data type test should run
		// Column stand for how many values per each test
		
		// Array size is 1
		// 0, 1
		Object[][] data = new Object[2][3];
		

		// Oth row
		data[0][0] ="unrestricteduser@em.com";
		data[0][1] ="123456@em";
		data[0][2] ="Restricted User";

		// 1th row
		data[1][0] ="restricteduser@em.com";
		data[1][1] ="9876543";
		data[1][2] ="Non Restricted User";
		
		return data;

	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
