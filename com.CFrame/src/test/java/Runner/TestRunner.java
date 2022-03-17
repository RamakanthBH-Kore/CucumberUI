package Runner;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

//import com.vimalselvam.cucumber.listener.ExtentCucumberFormatter;
//import com.vimalselvam.cucumber.listener.Reporter;

import framework.HashMapContainer;
import framework.JsonReader;
import framework.StepBase;
import io.cucumber.junit.Cucumber;	
//import com.cucumber.listener.ExtentCucumberFormatter;
//import cucumber.api.CucumberOptions;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//strict = true,"com.cucumber.listener.ExtentCucumberFormatter:",
@CucumberOptions(
		dryRun = false,
		plugin = {
				"pretty",
				"html:src/test/java/testresult/cucumber-report/index.html",
				"junit:src/test/java/testresult/cucumber-report/cucumber.xml",
				"json:src/test/java/testresult/cucumber-report/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				},
		
		features = { "src/test/java/features" }, 
		glue = { "Hooks","stepDefinitions" }, 
		tags = "@CreateNewBot and not @ignore",
		monochrome = true
		)
//@RunWith(Cucumber.class)
@RunWith(ExtendedCucumberRunner.class)
public class TestRunner{
	
	static String Platform = "desktop";
	static String Browser = "chrome";
	//static ExtentReports extent;
	
	@BeforeSuite
	public static void setUp() {
		// Add your pre-processing steps here
		try {
			//Load configuration file
			Properties objConfig = new Properties();
			objConfig.load(new FileInputStream(	System.getProperty("user.dir") + "/src/test/java/Cucumber/Config.properties"));
			objConfig.setProperty("os.name", System.getProperty("os.name"));
			System.setProperty("test.PostScenarioTearDown", objConfig.getProperty("test.PostScenarioTearDown"));
			System.setProperty("test.TrialRun", objConfig.getProperty("test.TrialRun"));
			System.setProperty("test.highlightElements", objConfig.getProperty("test.highlighElements"));
			System.setProperty("test.PageObjectMode", objConfig.getProperty("test.PageObjectMode"));
			System.setProperty("test.implicitlyWait", objConfig.getProperty("test.implicitlyWait"));
			System.setProperty("test.pageLoadTimeout", objConfig.getProperty("test.pageLoadTimeout"));
						
			if(Platform.equals("desktop")){
				StepBase.setUp(Platform,Browser);	
			}else{
				System.out.println("Enter valid platform choice: desktop");
			}

				System.out.println("Initiating Extent Reporter...");
				String reportConfigPath =System.getProperty("user.dir")+"\\src\\test\\java\\Cucumber\\extent-config.xml";
				
				// Initiates the extent report and generates the output in the output/Run_/report.html file by default. //
				//ExtentCucumberFormatter.initiateExtentCucumberFormatter();

				// Loads config.xml for report
				//ExtentCucumberFormatter.addSystemInfo("Browser Name", Browser);
				//ExtentCucumberFormatter.addSystemInfo("Platform", Platform);
				//ExtentCucumberFormatter.addSystemInfo("Selenium version", "3.141.59");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public static void tearDown() {
		try {
			//Reporter.loadXMLConfig(new File("src/test/java/Cucumber/extent-config.xml"));
			StepBase.tearDown();	
			if(!System.getProperty("test.TrialRun").equalsIgnoreCase("true"))
			{

				HashMapContainer.ClearHM();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
