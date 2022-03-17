package pageObjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import org.openqa.selenium.By;
import framework.HashMapContainer;
import framework.StepBase;
import framework.WrapperFunctions;

/**
 * @ScriptName    : PageObjectAccess
 * @Description   : This class contains Page Object mapping logic to support .csv page object repository. (Can be modified to additionally support Excel / Access DB / DB object repository storage)   
                
 */

public class GetPageObject{
	static ClassLoader cl;
	static Class<?> cls;
	public static BufferedReader OR;
	static By by;
	static String[] LocatorType;
	static String[] urlParameter;
	static String url;
	static String ClassPath;
	public static String OR_GetURL(String app) {
		try{
		if(System.getProperty("test.PageObjectMode").equalsIgnoreCase("csv"))
		{	
			GetPageObject.ReadCSV(app);
			urlParameter = HashMapContainer.getPO(app).split(",");
			url = urlParameter[1];
			System.out.println("URL: "+url);
			
		}else if (System.getProperty("test.PageObjectMode").equalsIgnoreCase("class")){
			GetPageObject.ReadPOClass(app);
			url = HashMapContainer.getPO("url");
			System.out.println("URL: "+url);
		}
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return url.toString().trim();
	}
	
	public static String OR_Load_Page_object(String app) {
		try{
		if(System.getProperty("test.PageObjectMode").equalsIgnoreCase("csv"))
		{	
			GetPageObject.ReadCSV(app);
			System.out.println(app+" Page object loaded");
			
		}else if (System.getProperty("test.PageObjectMode").equalsIgnoreCase("class")){
			GetPageObject.ReadPOClass(app);
			System.out.println(app+" Page object loaded");
		
		}
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return app;
	
	}

	public static By OR_GetElement(String element){
		try{
			String Locator = HashMapContainer.getPO(element);
			if(System.getProperty("test.PageObjectMode").equalsIgnoreCase("csv"))
			{	
				LocatorType = Locator.split(",",2);
			}else if (System.getProperty("test.PageObjectMode").equalsIgnoreCase("class")){
				System.out.println("Locator: "+Locator);
				LocatorType = Locator.split("=",2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Verify entries in PageObject data in your \".csv\" or Class file!: "+element);
		}
		return WrapperFunctions.setLocator(LocatorType[0].toString().trim(), LocatorType[1].toString().trim());
	}
	
	public void add(String Element){
		System.out.println("Element "+Element);
		String[] ElementRef = Element.split("::");
		System.out.println("ElemenetRef0 "+ElementRef[0]);
		System.out.println("ElemenetRef1 "+ElementRef[1]);
		HashMapContainer.addPO(ElementRef[0], ElementRef[1]);
	}

	public static void ReadCSV(String fileName){	
		try{
			String sCSVPath = "";
			//Based on device Type
			if(StepBase.testPlatform.equalsIgnoreCase("desktop")){
				sCSVPath = "./src/test/java/pageObjects/WebApp/"+fileName+".csv";
			}
			
			File ORFile = new File(sCSVPath);
			FileInputStream fis = new FileInputStream(ORFile);
			//DataInputStream dis = new DataInputStream(fis);
			OR = new BufferedReader(new InputStreamReader(fis , Charset.forName("windows-1252"))); 

			OR.readLine();

			while(OR.ready()){
				String ORLines = OR.readLine();
				String[] LocatorName = ORLines.split(",",2);
				HashMapContainer.addPO(LocatorName[0].toString().trim(), LocatorName[1].toString().trim());
			}
			System.out.println("Page Objects load completed!");
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void ReadPOClass(String className)throws Exception{	
		try {
			
			//Based on device Type
			if(StepBase.testPlatform.equalsIgnoreCase("desktop")){
				ClassPath = "pageObjects.WebApp.";
			}

			

			Class<?> cls = Class.forName(ClassPath+className);
			Object obj = cls.newInstance();
			Class<?> noparams[] = {};
			Method method = cls.getDeclaredMethod("loadallPageObjects", noparams);
			method.invoke(obj);
		}catch (Exception e ) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
