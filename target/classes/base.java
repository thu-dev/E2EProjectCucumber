package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {
	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();

		// System.getProperty("user.dir");
//		FileInputStream("/Users/thu/seleniumtraining/E2EProject/src/main/java/resources/data.properties");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/data.properties");

		prop.load(fis);

		// Maven command in terminal : mvn test -Dbrowser=chrome
// Activate this code to run on Jenkins
//		String browserName = System.getProperty("browser"); 

// Activate this code to run on computer

		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.contains("chrome")) {

			// execute in chrome
			System.setProperty("webdriver.chrome.driver", "/Users/thu/Grid/chromedriver");
			ChromeOptions options = new ChromeOptions();

			// run head less code
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/thu/Grid/geckodriver");
			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "/Users/thu/Grid/msedgedriver");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));

		return destinationFile;
	}
}
