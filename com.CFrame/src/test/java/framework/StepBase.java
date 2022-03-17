package framework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import cucumber.api.Scenario;
import io.cucumber.java.Scenario;
import java.net.URL;

/**
 * @ScriptName : StepBase
 * @Description : This class contains generic functionalities like
 *              setup/teardown test environment

 */
public class StepBase {
	// Local Variables
	protected static WebDriver driver;
	protected static Process process;
	protected static Process webkitprocess;
	protected static WebDriverWait webDriverWait;
	protected static Scenario crScenario;
	static DesiredCapabilities capabilities = null; 
	public static String testPlatform;
	public static String testBrowser;

	static String service_url;
	

	//BrowserStack credentials
		final static String BS_USERNAME = "";
		final static String BS_AUTOMATE_KEY = "";
		final static String BrowserStackURL = "";

	/**
	 * @Method: setUp
	 * @Description: This method is used to initialize test execution
	 *               environment 

	 * @throws Exception 
	 */


	public static void setScenario(Scenario cScenario) throws Exception {
		crScenario = cScenario;
	}

	public static void setUp(String Platform, String Browser) throws Exception {
		try {
			testPlatform = Platform;
			testBrowser = Browser;

			//if (Platform.equalsIgnoreCase("desktop")) {
			if (Platform.equalsIgnoreCase("desktop")) {
				switch(Browser.toLowerCase())
				{
				case "chrome":
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/java/resources/chromedriver.exe");
					ChromeOptions Option= new ChromeOptions();//DesiredCapabilities.chrome();
				    Option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					driver = new ChromeDriver(new ChromeOptions());
					driver.manage().window().maximize();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/test/java/resources/geckodriver.exe");
					System.out.println("Executing test on Firefox browser");
					driver = new FirefoxDriver();
					//driver.manage().window().maximize();
					break;
				case "ie":
					System.out.println("Executing test on Internet Explorer browser");
					//driver = new InternetExplorerDriver();
					driver.manage().window().maximize();
					break;
				case "safari":
					capabilities = new DesiredCapabilities();
					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					System.out.println("Executing test on Safari browser");
					//driver = new SafariDriver(capabilities);
					driver.manage().window().maximize();
					break;
				
				default:
					System.out.println("Provide valid browser choice in config file!");
					break;
				}

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(System.getProperty("test.implicitlyWait"))));
				//driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(System.getProperty("test.pageLoadTimeout")), TimeUnit.SECONDS);
			}	


		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * @Method: getDriver
	 * @Description: This method returns driver instance.
	 * @return : Driver instance
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * @Method: tearDown
	 * @Description: this method is used to close the driver instance.

	 */
	public static void tearScenario(Scenario scenario) {
		try {
			if (scenario.isFailed()) {
				StepBase.embedScreenshot();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}	

	public static void tearDown() {
		try{
			if(driver!=null)
			{
				if(testPlatform.equals("desktop")||System.getProperty("test.AppType").equals("webapp"))
				{
					driver.manage().deleteAllCookies();
				}
				driver.quit();
				driver=null;
				Thread.sleep(10000);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method: embedScreenshot Description: This method attach screenshot to the
	 * cucumber report.
	 */
	public static void embedScreenshot() {
		try{
			Thread.sleep(1000);
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			crScenario.attach(screenshot, "image/png",""); // Stick it to HTML report
		}
		catch(Exception e){
			System.out.println("Exception: "+e);
		}
	}

	public static void embedProvidedScreenshot(byte[] screenshot) {
		try{
			Thread.sleep(1000);
			crScenario.attach(screenshot, "image/png",""); // Stick it to HTML report
		}
		catch(Exception e){
			System.out.println("Exception: "+e);
		}
	}
}
