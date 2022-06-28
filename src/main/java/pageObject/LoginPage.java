package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
//	By email = By.cssSelector("[id='user_email']");
//	By password = By.id("user_password");
	By email = By.cssSelector("[id*='email']");
	By password = By.cssSelector("[id*='password']");
//	By login = By.cssSelector("[value='Log In']");
	By login = By.cssSelector("[type='submit']");
	By forgotPassword = By.cssSelector("[href*='password/new']");
	By popup=By.xpath("//button[text()='NO THANKS']");

	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public ForgotPassword forgotPassword() {
		driver.findElement(forgotPassword).click();
		ForgotPassword fp = new ForgotPassword(driver);
		return fp;
	}

	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getLogin() {
		return driver.findElement(login);
	}
	

	
}
