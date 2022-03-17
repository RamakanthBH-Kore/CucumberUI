package stepDefinitions;


import java.util.List;import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;
import org.junit.Assert;

import framework.HashMapContainer;
import framework.StepBase;
import framework.WrapperFunctions;
import pageObjects.GetPageObject;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.datatable.DataTable;

public class AGgridtable {

	

	static WrapperFunctions wrapFunc = new WrapperFunctions();
	static StepBase sb = new StepBase();
	
	private static String[][] TableValue = new String[100][100]; ;
	private static int cell_count;
	private static int Column_lenth;
	private static String cellcount="start_cellval";
	private static String celtext;
	
	
	public static WebDriver driver;

		//Xpath of gridcell should be in CVS 
		@Then("^I like to find the value exist '(.*)' in React ag-grid table '(.*)'$")
		public static void Find_value_AGtable(String ExpValue,String tableA){
			try{
					
					String header="header";
					driver = sb.getDriver();
					
		        	//To locate columns(cells) of that specific row.
					List<WebElement> Columns_row = driver.findElements(GetPageObject.OR_GetElement(tableA));
					//To calculate no of columns(cells) In that specific row.
					int columns_count = Columns_row.size();
				     List<WebElement> col_len = driver.findElements(GetPageObject.OR_GetElement(header));
				       Column_lenth=col_len.size();
				       int counter=0;
				       cell_count=0;
					
					//Loop will execute till the last cell of that specific row.
						for (int column=0; column<columns_count; column++){
							//To retrieve text from that specific cell.
							String celtext = Columns_row.get(column).getText();
				        	if (counter>=Column_lenth) {
				        		counter=0;
				        		cell_count=column;
				        	}
				        	
				        	 if (celtext.equalsIgnoreCase(ExpValue)) {
				                Assert.assertEquals("I Compare "+celtext+" with expected value "+ExpValue,ExpValue,celtext);//Columns_row.get(column).click();
				            	System.out.println("The Actual Value "+celtext+" matched with Expected "+ExpValue);
				            	
								HashMapContainer.addIN(cellcount, cell_count);
				                 break;
				            }
				            counter++;
					}
		            
					
		       
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new CucumberException(e.getMessage(), e);	
			}
		}
		
		@Then("^I compare expected data from table '(.*)'$")
		public static void ProcessTab_AG(String table_cell){
			try{
				String header="header";
				int Ccount = HashMapContainer.getIN(cellcount);
				System.out.println(Ccount);
		       List<WebElement> col_len = driver.findElements(GetPageObject.OR_GetElement(header));
		       Column_lenth=col_len.size();
		       List<WebElement> Elements_count = driver.findElements(GetPageObject.OR_GetElement(table_cell));
		       int counter=0;

		        for (int colu = Ccount; colu < (Column_lenth+Ccount); colu++) {
		        	
		        	 celtext = Elements_count.get(colu).getText();
		        	 String head= col_len.get(counter).getText();
			        	counter++;
		        	 //System.out.println(celtext+"  "+head);
		        	 String ExpectedValue=HashMapContainer.getDT(head);
		        	 Assert.assertEquals(celtext, ExpectedValue,"Expected Value did not match Actual Value in table");
		        }
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new CucumberException(e.getMessage(), e);	
			}
		}
		
		@Then("^I store header and value in HashmapDT with below data$")
		public void i_store_header_and_value_in_HashmapDT_with_below_data(DataTable dt) {
			
			try{
				List<List<String>> list = dt.asLists(String.class);
			
				int Header_len=list.get(0).size();
				System.out.println(Header_len);

				for(int i=1; i<list.size(); i++) { //i starts from 1 because i=0 represents the header
				
					for(int j=0; j<Header_len; j++){
						String Value=list.get(i).get(j);
						String head=list.get(0).get(j);
						HashMapContainer.addDT(head, Value);
						}
					
					}
			}catch (Exception e) {
				e.printStackTrace();
				throw new CucumberException(e.getMessage(), e);	
			}
		}
		
		@Then("^I compare expected table header value in table header '(.*)'$")
		public static void compare_exp_table_header(String header,DataTable  ExpectedValue){
			try{
				driver = sb.getDriver();
				 
		       List<WebElement> col_len = driver.findElements(GetPageObject.OR_GetElement(header));
		       Column_lenth=col_len.size();       
		       List<List<String>> data = ExpectedValue.asLists(String.class);   
		       
		       int counter=0;
		       int headrcounter=1;

		        for (int colu = 0; colu < (Column_lenth); colu++) {
		        	
		        	 String Exptext = data.get(headrcounter).get(0);
		        	 String head= col_len.get(colu).getText();
			        	counter++;
			        	headrcounter++;
		        	 System.out.println("Expected is "+Exptext+" Actual is "+head+" value");
		        	
		        	 Assert.assertEquals("Expected Value did not match Actual Value in table",Exptext,head);
		      
		        }
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new CucumberException(e.getMessage(), e);	
			}
		}
}
