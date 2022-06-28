package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.portalHomePage;
import resources.base;

public class stepDefinition extends base {

	@Given("Initialize the browser with chrome")
	public void initialize_the_browser_with_chrome() throws Throwable {
		driver = initializeDriver();
	}

	@Given("Navigate to {string} site")
	public void navigate_to_site(String string) throws Throwable {
		driver.get(string);
	}

	@Given("Click on login link in home page to land on sign in Page")
	public void click_on_login_link_in_home_page_to_land_on_sign_in_page() throws Throwable {
		LandingPage l = new LandingPage(driver);

		if (l.getPopUpSize() > 0) {
			l.getPopUp().click();
		}

		l.getLogin().click();
	}

//	@When("User enters {string} and {string} and logs in")
//	public void user_enters_and_and_logs_in(String string, String string2) throws Throwable {
//		LoginPage lp = new LoginPage(driver);
//		lp.getEmail().sendKeys(string);
//		lp.getPassword().sendKeys(string2);
//		lp.getLogin().click();
//	}

    @When("^User enters (.+) and (.+) and logs in$")
    public void user_enters_and_and_logs_in(String username, String password) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getLogin().click();
	}

	@Then("Verify that user is succesfully logged in")
	public void verify_that_user_is_succesfully_logged_in() throws Throwable {
		portalHomePage p = new portalHomePage(driver);
		Assert.assertTrue(p.getsearchBox().isDisplayed());
	}
	
	@And("^close browser$")
    public void close_browser() throws Throwable {
        driver.quit();
    }

}
