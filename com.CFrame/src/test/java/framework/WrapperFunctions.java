package framework;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.time.Duration;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import pageObjects.GetPageObject;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.core.runtime.CucumberExecutionContext;



public class WrapperFunctions
{
	private static StepBase objStepBase = new StepBase();
	static By by;
	private static WebDriver driver = objStepBase.getDriver();

	/**
	 * @Method: waitForElementToBeClickable
	 * @Description: This is wrapper method wait for element to be click able
	 * @param locator - By identification of element
	 * @param waitInSeconds - wait time  
	 */
	public void waitForElementToBeClickable(By locator, int waitInSeconds) 
	{
		try 
		{
			Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(waitInSeconds)).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} 
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	// Element highlighter code
	public static void highLightElement(WebElement element)throws Exception
	{
		try {
			if(System.getProperty("test.highlightElements").equals("true")){
				JavascriptExecutor js=(JavascriptExecutor)driver; 
				//for(int i=0;i<2;i++){
					js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 2px solid green;');", element);
					Thread.sleep(50);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
					//Thread.sleep(50);
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForPageToLoad() 
	{
		try 
		{
			ExpectedCondition<Boolean> expect =  null;
			Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(System.getProperty("test.pageLoadTimeout")))).ignoring(StaleElementReferenceException.class);
			//Condition to check page load complete
			if(StepBase.testPlatform.equals("desktop")||System.getProperty("test.AppType").equals("webapp")){
				expect = new ExpectedCondition<Boolean>(){ public Boolean apply(WebDriver dr)
				{
					return  ((JavascriptExecutor)dr).executeScript("return document.readyState").equals("complete");
				}
				};
				wait.until(expect);
			}

		} 
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method: waitForElementPresence
	 * @Description: This is wrapper method wait for element presence
	 * @param locator - By identification of element
	 * @param waitInSeconds - wait time 
	 */
	public boolean waitForElementPresence(By locator, int waitInSeconds) 
	{
		try 
		{
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSeconds)).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} 
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: waitForElementPresence
	 * @Description: This is wrapper method wait for element presence
	 * @param locator - By identification of element
	 * @param waitInSeconds - wait time 
	 */
	public boolean waitForElementPresence(By locator) 
	{
		try 
		{

			int timeOut = Integer.parseInt(System.getProperty("test.pageLoadTimeout"));
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} 
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: checkElement_Existance
	 * @Description: This is wrapper method to check the existence of any web element on the page
	 * @param locator - By identification of element
	 * @param waitInSeconds - wait time 
	 * @return - true if element present  
	 */
	public boolean checkElementExistence(By locator, int... sTimeInSecond)
	{
		try 
		{
			WebDriverWait wait = null;
			if(sTimeInSecond.length > 0)
			{
				wait = new WebDriverWait(driver,Duration.ofSeconds(sTimeInSecond[0]));
			}
			else
			{
				wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			WebElement ele= driver.findElement(locator);
			return ele.isDisplayed();
		}
		catch(Exception exception)
		{
			return false;
		}
	}

	/**
	 * @Method: click
	 * @Description: This is wrapper method to click on web element 
	 * @param locator - By identification of element
	 * @return - true if click successful
	 */
	public boolean clickByJS(By locator) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		try
		{
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", webElement);
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: doubleClick
	 * @Description: This is wrapper method used for doubleClick on element
	 * @param locator - By identification of element
	 * @return - true if double click successful
	 */
	public boolean doubleClick(By locator)
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		try
		{
			Actions actionBuilder = new Actions(driver);
			actionBuilder.doubleClick(webElement).build().perform();
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: setText
	 * @Description: This is wrapper method to set text for input element
	 * @param locator - By identification of element
	 * @param fieldValue - field value as string 
	 * @return - true if text entered successfully
	 */
	public boolean setText(By locator, String fieldValue) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		try
		{
			// replace the text
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", webElement);
			webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			webElement.sendKeys(Keys.DELETE);
			webElement.clear();
			webElement.sendKeys(fieldValue);
			//webElement.sendKeys(Keys.TAB);
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: getText
	 * @Description: This is wrapper method to get text form input elements
	 * @param locator - By identification of element
	 * @param textBy - get text by value attribute (set textBy as value)/ by visible text (set textBy as text)
	 * @return - text as string
	 */
	public String getText(By locator, String textBy) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		try
		{
			String strText = "";
			if(textBy.equals("value"))
				strText = webElement.getAttribute("value");
			else if(textBy.equalsIgnoreCase("text"))
				strText = webElement.getText();
			return strText; 
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method: selectCheckBox
	 * @Description: This is wrapper method select/deselect checkbox
	 * @param locator - By identification of element
	 * @param status - select/deselect 
	 */
	public boolean selectCheckBox(By locator, boolean status) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		try
		{
			if(webElement.getAttribute("type").equals("checkbox"))   
			{
				if((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				return true;
			}
			else
				return false;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: isCheckBoxSelected
	 * @Description: This is wrapper checkbox is selected or not
	 * @param locator - By identification of element
	 */
	public boolean isCheckBoxSelected(By locator, boolean status) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		boolean state = false;
		try
		{
			if(webElement.getAttribute("type").equals("checkbox"))   
				state = webElement.isSelected();

			return state;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: selectRadio
	 * @Description: This is wrapper method select/deselect radio button
	 * @param locator - By identification of element
	 * @param status - select/deselect 
	 
	 */
	public boolean selectRadioButton(By locator, boolean status)
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = driver.findElement(locator);
		try
		{
			if(webElement.getAttribute("type").equals("radio"))   
			{
				if((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				return true;
			}
			else
				return false;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: mouseHover
	 * @Description: This is wrapper method used for Mouse Hovering to the element
	 * @param locator - By identification of element
	 */
	public boolean mouseHover(By locator)
	{
		WebElement webElement = driver.findElement(locator);
		try
		{
			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(webElement).build().perform();
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	



	/**
	 * @Method: clickByAction
	 * @Description: This is wrapper method used for Mouse Hovering to the element
	 * @param locator - By identification of element
	 */
	public boolean clickByAction(By locator)
	{
		WebElement webElement = driver.findElement(locator);
		try
		{
			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(webElement).click().build().perform();

			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	public boolean scroll(String scrollType,String target)
	{
		try{
			JavascriptExecutor executor = (JavascriptExecutor)driver;

			switch(scrollType){
			case "top":
				executor.executeScript("window.scrollTo(0,0)");
			case "bottom":
				executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				break;
			case "element":
				waitForElementPresence(GetPageObject.OR_GetElement(target));
				//System.out.println("Scorlling to Element"+GetPageObject.OR_GetElement(target));
				executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(GetPageObject.OR_GetElement(target)));
				break;
			case "coordinates":
				String[] coordinates = target.split(",");
				String x = coordinates[0];
				String y = coordinates[1];
				executor.executeScript("window.scrollBy("+x+","+y+")");
				break;
			case "horizontal":
				EventFiringWebDriver test= new EventFiringWebDriver(driver);
				String[] Scroll_Ele = HashMapContainer.getPO(target).split(",");
				String Element_scroll = Scroll_Ele[1];
				int x_cordinate=700;
				for(int i=0;i<=16;i++){
					test.executeScript("document.querySelector('"+Element_scroll+"').scrollLeft="+x_cordinate);
				    x_cordinate=x_cordinate+300;
				    System.out.println(x_cordinate);
				    Thread.sleep(1000);
				}
				//test.executeScript("document.querySelector('"+Element_scroll+"').scrollLeft=700");
				//executor.executeScript("window.scrollTo(0, document.body.scrollWidth)");
				//document.querySelectorAll("[class='ag-body-viewport']")[1].scrollLeft=1235
				break;
			}
			return true;
		}catch(Exception exception){
			exception.printStackTrace();
			return false;
		}
	}


	/**
	 * @Method: switchToWindowUsingTitle
	 * @Description: This is wrapper method used switch to window using the given title
	 * @param locator - Window title
	 */
	public boolean switchToWindowUsingTitle(String windowTitle)
	{
		try
		{
			String mainWindowHandle = driver.getWindowHandle();
			Set<String> openWindows = driver.getWindowHandles();

			if (!openWindows.isEmpty()) 
			{
				for (String windows : openWindows) 
				{
					String window = driver.switchTo().window(windows).getTitle();
					if (windowTitle.equals(window)) 
						return true;
					else 
						driver.switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: selectCheckBox
	 * @Description: This is wrapper method select drop down element
	 * @param locator - By identification of element
	 * @param option - drop down element (user may specify text/value/index)
	 * @param selectType - select dorp down element by Text/Value/Index
	 */
	public boolean selectDropDownOption(By locator, String option, String... selectType) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			WebElement webElement = driver.findElement(locator);
			Select sltDropDown = new Select(webElement);

			if(selectType.length > 0 && !selectType[0].equals(""))
			{
				if(selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.selectByValue(option);
				else if(selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.selectByVisibleText(option);
				else if(selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));

				return true;
			}
			else
			{
				// Web elements from dropdown list 
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for(WebElement weOptions : options)  
				{  
					if (weOptions.getText().trim().equals(option))
					{
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
					}
					else
						iIndex++;
					if(blnOptionAvailable)
						break;
				}
				if(blnOptionAvailable)
					return true;
				else
					return false;
			}
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: getSelectedValueFormDropDown
	 * @Description: This is wrapper method select drop down element
	 * @param locator - By identification of element
	 */
	public String getSelectedValueFormDropDown(By locator) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			Select selectDorpDown = new Select(driver.findElement(locator));
			String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
			return selectedDorpDownValue;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}

	}
	/**
	 * @Method: selectRadioButtonForSpecificColumn
	 * @Description: This is wrapper method to select radio button from table with respect to particular column content
	 * @param locator - By identification of element (table with all rows)
	 * @param columnContent - String column content
	 * @columnNumberForRadio - integer column number for radio button
	 */
	public boolean selectRadioButtonForSpecificColumn(By locator, String columnContent, int columnNumberForRadio) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			List<WebElement> weResultTable = (List<WebElement>) driver.findElements(locator);
			for(WebElement weRow : weResultTable)
			{
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td"));
				for(WebElement weColumn : weColumns)
				{
					if(weColumn.getText().trim().equals(columnContent))
					{
						WebElement webElement = weRow.findElement(By.xpath(".//td['" + columnNumberForRadio + "']/input[@type='radio']"));
						JavascriptExecutor executor = (JavascriptExecutor)objStepBase.getDriver();
						executor.executeScript("arguments[0].click();", webElement);
						webElement.click();
						Thread.sleep(1000L);
						webElement.click();
						webElement.click();
					}
				}
			}
			return true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: selectCheckBoxForSpecificColumn
	 * @Description: This is wrapper method to select chechbox from table with respect to particular column content
	 * @param locator - By identification of element (table with all rows)
	 * @param columnContent - String column content
	 * @columnNumberForRadio - integer column number for radio button
	 */
	public boolean selectCheckBoxForSpecificColumn(By locator, String columnContent, int columnNumberForRadio) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			List<WebElement> weResultTable = (List<WebElement>) driver.findElements(locator);
			for(WebElement weRow : weResultTable)
			{
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td"));
				for(WebElement weColumn : weColumns)
				{
					if(weColumn.getText().trim().equals(columnContent))
						weRow.findElement(By.xpath(".//td['" + columnNumberForRadio + "']/span/input[@type='checkbox']")).click();
				}
			}
			return true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}


	
	/**
	 * @Method: verifyTableContent
	 * @Description: 
	 * @param locator - By identification of element (table with all rows)
	 * @param columnHeader - String column header
	 * @param columnContent - String column content
	 */
	public boolean verifyTableContent(By locator, String columnHeader, String columnContent) 
	{
		Hashtable<String , String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		boolean blnverify = false;
		try
		{
			waitForElementPresence(locator, 10);
			WebElement weResultTable = driver.findElement(locator);

			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for(WebElement weColumnHeader : weColumnsHeaders)
			{
				String strHeader = weColumnHeader.getText().trim();
				if(!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber ++;
			}

			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for(WebElement weRow : weRows)
			{
				WebElement weExceptedClm = weRow.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeader) + "]"));
				if(weExceptedClm.getText().trim().contains(columnContent))
					blnverify = true;
			}

			return blnverify;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method: verifyTableContentAndCheckSelected
	 * @Description: 
	 * @param locator - By identification of element (table with all rows)
	 * @param columnHeader - String column header
	 * @param columnContent - String column content
	 */
	public boolean verifyTableContentAndCheckSelected(By locator, String columnHeader, String columnContent, int checkboxColumnNumber) 
	{
		Hashtable<String , String> dataColumnHeader = new Hashtable<String, String>();
		int intColumnNumber = 1;
		boolean blnverify = false;
		try
		{
			waitForElementPresence(locator, 10);
			WebElement weResultTable = driver.findElement(locator);

			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			for(WebElement weColumnHeader : weColumnsHeaders)
			{
				String strHeader = weColumnHeader.getText().trim();
				if(!strHeader.equals(""))
					dataColumnHeader.put(strHeader, String.valueOf(intColumnNumber));
				intColumnNumber ++;
			}

			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			for(WebElement weRow : weRows)
			{
				WebElement weExceptedClm = weRow.findElement(By.xpath(".//td[" + dataColumnHeader.get(columnHeader) + "]"));
				if(weExceptedClm.getText().trim().contains(columnContent))
				{
					WebElement weCheckBox = weRow.findElement(By.xpath(".//td[" + checkboxColumnNumber + "]/span/input[@type='checkbox']"));
					boolean blnIsSelected = weCheckBox.isSelected();
					if(blnIsSelected)
					{
						blnverify = true;
					}
				}
			}
			return blnverify;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	public static By setLocator(String locatorType,String locator){


		switch(locatorType.toLowerCase()){

		case "id":
			by = By.id(locator);
			break;

		case "classname":
			by = By.className(locator);
			break;

		case "name":
			by = By.name(locator);
			break;

		case "linktext":
			by = By.linkText(locator);
			break;

		case "partiallinktext":
			by = By.partialLinkText(locator);
			break;

		case "cssselector":
			by = By.cssSelector(locator);
			break;

		case "xpath":
			by = By.xpath(locator);
			break;

		case "tagname":
			by = By.tagName(locator);
			break;

		}		
		return by;
	}


	/**
	 * @Method: view table details
	 * @Description: 
	 * @param locator - By identification of table element
	 */
	public boolean viewTableContent(By locator) 
	{
		try
		{
			WebElement weResultTable = driver.findElement(locator);

			int intHeaderNumber = 1;
			List<WebElement> weColumnsHeaders = weResultTable.findElements(By.xpath(".//thead/tr/th"));
			System.out.println("************************* Table Header details *************");
			for(WebElement weColumnHeader : weColumnsHeaders)
			{
				System.out.println("Table Header * " + intHeaderNumber + " *--->" + weColumnHeader.getText());
				intHeaderNumber++;
			}

			List<WebElement> weRows = weResultTable.findElements(By.xpath(".//tbody/tr"));
			int intRowNum = 1 ;
			System.out.println("************************* Table content details *************");
			for(WebElement weRow : weRows)
			{
				System.out.println("**********Row Number  " + intRowNum + " *************");
				int intClmNum = 1 ;  
				List<WebElement> weClmns = weRow.findElements(By.xpath(".//td"));
				for(WebElement weClmn : weClmns)
				{
					System.out.println("Column Number---->" + intClmNum);
					System.out.println("Column Text-------->" + weClmn.getText());
					intClmNum++;
				}
				intRowNum++;
			}
			return true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}	
	
	/**
	 * @Method: Send Key 
	 * @Description: You can send -pageUp/pageDown/ESC/Enter/Tab
	 * @param Key - Keys
	 */
	public boolean sendkey(String key) {
		try {
						
			Actions action = new Actions(driver);

			switch(key){
			case "pageUp":
				action.sendKeys(Keys.PAGE_UP).click().build().perform();
			case "pageDown":
				action.sendKeys(Keys.PAGE_DOWN).click().build().perform();
				break;
			case "ESC":
				action.sendKeys(Keys.ESCAPE).click().build().perform();
				break;
			case "Enter":
				action.sendKeys(Keys.ENTER).click().build().perform();
				break;
			case "Tab":
				action.sendKeys(Keys.TAB).click().build().perform();
				break;
						}
			return true;
		}catch(Exception exception){
			exception.printStackTrace();
			return false;
		}
	}
	/**
	 * @Method: generateRandom 
	 * @Description: This method will return random email/string/number.
	 * @param  - type=email/string/number
	 * @return Random string
	 */

	public String generateRandom(String type ){
		String generatedRandom = null;
		try{
			switch(type){
		case "email":
			String generatedString = RandomStringUtils.randomAlphabetic(6);
			generatedRandom="QAAuto_"+generatedString+"@kore.com";
			break;
		case "string":
			String generatedStr = RandomStringUtils.randomAlphabetic(6);
			double randVal = (Math.random()) * 100;
			int generatednum = (int) randVal;
			generatedRandom="QAAuto_"+generatedStr+generatednum;
			break;
		case "number":
			double randVal1 = (Math.random()) * 10000;
			int randomId = (int) randVal1;
			generatedRandom = Integer.toString(randomId);
			break;
				}
			

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return generatedRandom;
	}
	
	/**
	 * @Method:  Zoom
	 * @Description: You can Zoom the page by percentage
	 * @param Key - percentage
	 */
	
    public static void zoom_window(int percentage)
    {
    	try{
 		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.body.style.zoom = '"+percentage+"%';");
		
    	}catch(Exception e) {
			e.printStackTrace();
    	}
    }
	
	/**
	 * @Method:  CloseTab
	 * @Description: You can close a tab in browser
	 * @param Key - N/A
	 */
    public boolean CloseTab() {
		try {
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));		
			return true;
		}catch(Exception exception){
			exception.printStackTrace();
			return false;
		}
	}
    
	/**
	 * @Method:  PS_scroll
	 * @Description: To scroll for Perfect scroll bar
	 * @param Key - scrollType= Is used to tell scroll Top/Bottom/Element to find
	 * 				scrollel= Its the class object of the PS scroll .it changes page to page.
	 * 				Target= the element to find.
	 */
    public boolean PS_scroll(String scrollType,String scrollel)
	{
		try{
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			String[] Scroll_Ele = HashMapContainer.getPO(scrollel).split(",");
			String Element_scroll = Scroll_Ele[1];

			switch(scrollType){
			case "top":
				String scrollelement="$('."+Element_scroll+"').scrollTop(0)";
				executor.executeScript(scrollelement);
			case "bottom":
				String scrollelem="$('."+Element_scroll+"').scrollTop($('."+Element_scroll+"')[0].scrollHeight)";
				executor.executeScript(scrollelem);
				break;

			}
			return true;
		}catch(Exception exception){
			exception.printStackTrace();
			return false;
		}
	}
    
	/**
	 * @Method:  scroll_Vertical_till_find_element
	 * @Description: To scroll for Perfect scroll bar
	 * @param Key - scrollType= Is used to tell scroll Top/Bottom/Element to find
	 * 				scrollel= Its the class object of the PS scroll .it changes page to page.
	 * 				Target= the element to find.
	 */

	public static void scroll_Vertical_till_find_element(String scrollel, String element) throws Exception {
		
	try{
	int Y_cordinate =0;
	String[] Scroll_Ele = HashMapContainer.getPO(scrollel).split(",");
	String Element_scroll = Scroll_Ele[1];
	JavascriptExecutor test= (JavascriptExecutor)driver;
	for(int i=0;i<=16;i++){
	Boolean elementexist= driver.findElements(GetPageObject.OR_GetElement(element)).size() > 0;
	if(elementexist){
	StepBase.embedScreenshot();
	System.out.println("Element is displayed");
	break;
	}else{
	String scrollelement="$('."+Element_scroll+"').scrollTop($('."+Element_scroll+"')[0].scroll(0,200))";
	test.executeScript("scrollelement");
	Y_cordinate=Y_cordinate+200;
	Thread.sleep(1000);
	
			}
		}

		}catch(Exception e){
		e.printStackTrace();
		throw new Exception("Element Not found on page!");
		}
	}
    
}

