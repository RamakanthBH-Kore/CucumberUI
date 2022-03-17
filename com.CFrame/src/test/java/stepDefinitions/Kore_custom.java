package stepDefinitions;
import framework.HashMapContainer;
import framework.StepBase;
import framework.Utilities;
import framework.WrapperFunctions;
import io.cucumber.core.exception.CucumberException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import framework.SQLConnector;
import framework.FrameworkExceptions;
import stepDefinitions.CommonSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.GetPageObject;

public class Kore_custom {
	
	static WrapperFunctions wrapFunc = new WrapperFunctions();
	static StepBase sb = new StepBase();
	public static WebDriver driver= sb.getDriver();
	static Utilities util = new Utilities();
	
	@Then("^I wait for innertext '(.*)' untill element '(.*)' found$")
    public static void I_wait_Element_is_loaded(String aText, String element){
        try{
        	int time=30;
            for (int i=0; i<time; i++){

                CommonSteps.I_get_Attribute_innertext_from(element);
                String actualText =HashMapContainer.get(element);
                
                 if (!actualText.equals(aText))
                    {
                     Thread.sleep(2000);
                     i++;
					 System.out.println("The Actual text is "+actualText);
					 System.out.println("The Expected text is "+aText);
                    }
             else{
                System.out.println("The Actual text is "+actualText+" equals expected "+aText );
                break;
                }
            }
        }catch(Exception e){
                //e.printStackTrace();
                //throw new CucumberException(e.getMessage(), e);
            }
        }
	

	@Then("^I get system date$")
	public static String Date_gen() {
		 
		 // Create object of SimpleDateFormat class and decide the format
		 DateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy h:mm:ss a");
		 DateFormat TimeFormat = new SimpleDateFormat("hh.mm aa");
		 
		 //get current date time with Date()
		 Date date = new Date();
		 
		 // Now format the date
		 String date1= dateFormat.format(date);
		 String TimeString = TimeFormat.format(date).toString();
		 // Print the Date
		  System.out.println("Current date and time is " +date1);
		 return date1;

		 
		 }
	
	@Then("^I Import the Type of file '(.*)' and its in '(.*)'$")
	public static void Import_file(String File_typ, String filename )
	{
		try{
		
		switch(File_typ){

		case "Bot_Definition":
			WebElement fileInput = driver.findElement(By.xpath("//input[@id='importBotDefinition']"));
			fileInput.sendKeys(System.getProperty("user.dir") +"/src/test/java/resources/BOT/"+filename+"");
			break;

		case "Bot_Config":
			WebElement fileInput1 = driver.findElement(By.xpath("//input[@id='importConfig']"));
			fileInput1.sendKeys(System.getProperty("user.dir") +"/src/test/java/resources/BOT/"+filename+"");
			break;
			
		case "Custom_Script":
			WebElement fileInput2 = driver.findElement(By.xpath("//input[@id='botFuctionFile']"));
			fileInput2.sendKeys(System.getProperty("user.dir") +"/src/test/java/resources/BOT/"+filename+"");
			break;
			 
			}		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	@Then("^I compare responces message '(.*)' from Channel type '(.*)'$")
	public static void FB_compare_Msg(String aText, String bot_typ) {
		try{
			String element = "";
			switch(bot_typ){

				case "FB":
					element="(//*[@data-testid=\"message-container\"]/div[1]/div[1]/div[1]/span/div/div[1]/div/div)";
					break;

				case "Talk2Bot":
					element="(//span[@class='simpleMsg'])";
					break;
					}
								
			int value = driver.findElements(By.xpath(element)).size();
			String xp=element+"["+value+"]";
			String actualText = driver.findElement(By.xpath(xp)).getAttribute("innerText");
			 System.out.println("The text is " +actualText);

			 if(actualText.contains(aText))
			 	{
				 System.out.println("The Keywor is found ");
				}else{
					Assert.fail(actualText+" Did not match expected.");
					throw new Exception("String is not Displayed!");
					
				}
					
	    	}catch(Exception e) {
				e.printStackTrace();
	    	}
	}
	
	@Then("^Stored date '(.*)' compared from Channel type '(.*)' in expected format '(.*)'$")
	public static void compare_Stored_Msg(String hmap, String bot_typ, String expFormat) {
		try{
			String element = "";
			String aText=HashMapContainer.get(hmap);
			String dateformat=HashMapContainer.get(hmap+"dataformat");
			String aDate=Utilities.ConvertDateFormat(aText,dateformat, expFormat);
			//System.out.println("The text is " +aDate);
			switch(bot_typ){

				case "FB":
					element="(//*[@data-testid=\"message-container\"]/div[1]/div[1]/div[1]/span/div/div[1]/div/div)";
					break;

				case "Talk2Bot":
					element="(//span[@class='simpleMsg'])";
					break;
					}
								
			int value = driver.findElements(By.xpath(element)).size();
			String xp=element+"["+value+"]";
			String actualText = driver.findElement(By.xpath(xp)).getAttribute("innerText");
			 //System.out.println("The actual Date is " +actualText);

			 if(actualText.contains(aDate))
			 	{
				 System.out.println("The Date is found in given responce");
				}else{
					Assert.fail(actualText+" Did not contain "+aDate);
					throw new Exception(aDate+" String is not Displayed!");
					
				}
					
	    	}catch(Exception e) {
				e.printStackTrace();
	    	}
	}
}
