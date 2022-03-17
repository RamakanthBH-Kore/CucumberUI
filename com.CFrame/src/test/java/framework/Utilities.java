package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;


/**
 * @ScriptName    : Utilities
 * @Description   : This class contains   
                 
 */
public class Utilities 
{
	private StepBase objStepBase = new StepBase();

	/**
	 * Method: takeScreenshot
	 * Description: 
	 * @param timeInMilliseconds

	 */
	public static String takeScreenshot(WebDriver driver) 
	{
		try 
		{
			Thread.sleep(10000);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String SSPath= System.getProperty("user.dir") + "/src/test/java/com/testresult/ScreenShots/"+getRequiredDate(0, "yyyy_MM_dd_hh", null)+"/screenshot_"+ getRequiredDate(0, "yyyy_MM_dd_hh_mm_ss", null) +".png";
			FileUtils.copyFile(scrFile, new File(SSPath));
			return SSPath;
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method: waitFor
	 * Description: Waits for the specified amount of [timeInMilliseconds].
	 * @param timeInMilliseconds
	 */
	public void waitFor(final Long timeInMilliseconds)
	{
		try 
		{
			Thread.sleep(timeInMilliseconds);
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method: getRequiredDate
	 * Description: This method will give require date
	 * @param incrementDateByDays Number by which user want increase date 
	 * @param sExpectedDateFormat - User expected date format
	 * 		eg. 9 april 2018 --- dd/MM/yyyy -> 09/04/2018, dd-MM-yyyy -> 09-04-2018
	 * @param timeZoneId - Time Zone
	 */
	public static String getRequiredDate (int incrementDays, String expectedDateFormat, String timeZoneId)
	{
		try 
		{
			DateFormat dateFormat;
			Calendar calendar = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(expectedDateFormat);
			if(timeZoneId != null && ! timeZoneId.equals(""))
				dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			calendar.add(Calendar.DAY_OF_MONTH, incrementDays);
			Date tomorrow = calendar.getTime();
			String formattedDate = dateFormat.format(tomorrow);
			return formattedDate;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method: ConvertDateFormat
	 * Description: This method will Convert given date format to require format
	 * @param givenDate is String  which user want's to convert
	 * @param givenDateFormat (string e.g.MMM-dd-yyyy) is the existing date format givenDate
	 * @param ExpectedDateFormat - User expected date format
	 * 		eg. 9 april 2018 --- dd/MM/yyyy -> 09/04/2018, dd-MM-yyyy -> 09-04-2018
	 */	
	public static String ConvertDateFormat (String givenDate,String givenDateFormat, String expectedDateFormat)
{
	try 
	{
		DateFormat dateFormat;
		Calendar calendar = Calendar.getInstance();
		dateFormat = new SimpleDateFormat(givenDateFormat);
		Date date = (Date)dateFormat.parse(givenDate);
		dateFormat = new SimpleDateFormat(expectedDateFormat);
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}
	catch (Exception e) 
	{
		e.printStackTrace();
		return null;
	}
}

	/**
	 * Method: copyFileUsingStream
	 * Description: 
	 * @param timeInMilliseconds
	 */
	public void copyFileUsingStream(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;
		try 
		{
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();

		} 
		finally 
		{
			is.close();
			os.close();
		}
	}

	/**
	 * Method: waitForPageLoad
	 * Description: timeInSeconds for the specified amount of [timeInSeconds].
	 */
	public void waitForPageLoad()
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(objStepBase.getDriver(),Duration.ofSeconds(180));
			final JavascriptExecutor javascript = (JavascriptExecutor) 
					(objStepBase.getDriver() instanceof JavascriptExecutor ? objStepBase.getDriver() : null);
			/*
	      wait.until(new ExpectedCondition<Boolean>()
	          {
	        @Override
	        public Boolean apply(WebDriver d)
	        {
	          boolean outcome = Boolean.parseBoolean(javascript
	              .executeScript("return jQuery.active == 0")
	              .toString());
	          return outcome;
	        }
	          });

			 */   
			wait.until(new ExpectedCondition<Boolean>() 
			{
				@Override
				public Boolean apply(WebDriver d)
				{
					return javascript.executeScript("return document.readyState").equals("complete");
				}
			});

			objStepBase.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// objStepBase.getDriver().manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method: waitForAjaxCallComplete
	 * Description: timeInSeconds for the specified amount of [timeInSeconds].
	 */
	public void waitForAjaxCallComplete()
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(objStepBase.getDriver(), Duration.ofSeconds(180));
			final JavascriptExecutor javascript = (JavascriptExecutor) 
					(objStepBase.getDriver() instanceof JavascriptExecutor ? objStepBase.getDriver() : null);

			wait.until(new ExpectedCondition<Boolean>()
			{
				@Override
				public Boolean apply(WebDriver d)
				{
					boolean outcome = Boolean.parseBoolean(javascript
							.executeScript("return jQuery.active == 0")
							.toString());
					return outcome;
				}
			});
		}
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}


	public static void resizeImage(File inputImagePath, File outputImagePath, int scaledWidth, int scaledHeight)throws IOException {
		// reads input image
		BufferedImage inputImage = ImageIO.read(inputImagePath);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		
		System.out.println("Resize completed : "+ outputImagePath);

		/* 
       // extracts extension of output file
       String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
		 */

		// writes to output file
		ImageIO.write(outputImage, "png", outputImagePath);
	}

	/**
	 * Method: ImageComparison
	 * Description: timeInSeconds for the specified amount of [timeInSeconds].

	 */
	public static float compareImage(File ActualImage, File BaseLinedImage) {
		float percentage = 0;
		try {
			// take buffer data from both image files //
			BufferedImage biA = ImageIO.read(ActualImage);
			DataBuffer dbA = biA.getData().getDataBuffer();
			int sizeA = dbA.getSize();
			BufferedImage biB = ImageIO.read(BaseLinedImage);
			DataBuffer dbB = biB.getData().getDataBuffer();
			
			int sizeB = dbB.getSize();
			int count = 0;
			// compare data-buffer objects //
			if (sizeA == sizeB) {
				for (int i = 0; i < sizeA; i++) {
					if (dbA.getElem(i) == dbB.getElem(i)) {
						count = count + 1;
					}
				}
				percentage = (count * 100) / sizeA;
				percentage = 100 - percentage;
			} else {
				percentage = -1;
				System.out.println("Both the images are not of same size"+ percentage);
				throw new Exception("Both the images are not of same size");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return percentage;
	}


    public static BufferedImage getDifferenceImage(BufferedImage  ActualImage, BufferedImage  BaseLinedImage) throws Exception{

        BufferedImage img1=ActualImage;
        BufferedImage img2=BaseLinedImage;
			
			// convert images to pixel arrays...
			final int w = img1.getWidth(),
			        h = img1.getHeight(), 
			        highlight = Color.RED.getRGB();
			final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
			final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
			//compare pixel by pixel of img1 to img2. Highlight img1's pixels which are different.
			for (int i = 0; i < p1.length; i++) {
			 if (p1[i] != p2[i]) {
			        p1[i] = highlight;
			 }
			}
			// save img1's pixels to a new BufferedImage, and return it...
			// (May require TYPE_INT_ARGB)
			final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			out.setRGB(0, 0, w, h, p1, 0, w);
			return out;
    }
    
	/**
	 * Method: decryptedPwd
	 * Description: To decrypt the Basic64 password.
	 * @param : encryptedPassword its a string to be decrypted.
	 */
    public static String decryptedPwd(String encryptedPassword) {
    	
    	//String pass="";
    	//String encodedBytes = Base64.getEncoder().encodeToString(pass.getBytes());
    	String decryptedPassword;
    	byte[] decryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);
    	decryptedPassword = new String(decryptedPasswordBytes);
    	return decryptedPassword;
    	}
	
	public void postapi() throws IOException
	{
		String endpoint ="";
		File requestFile = new File(System.getProperty("user.dir")+"//Request//SupplierByCity.xml");
		HttpClient cilent = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(endpoint);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type","text/xml");
		HttpResponse response = cilent.execute(post);
		System.out.println(response.getStatusLine().getStatusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line=br.readLine())!=null)
		{
			sb.append(line);
		}
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//Response//ResponseSupplierCity.xml");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
	}
	
	public void getResponse() throws ClientProtocolException, IOException
	{
		String endpoint="";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(endpoint);
		HttpResponse response = client.execute(request);
		BufferedReader buffread = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line="";
		StringBuffer sb = new StringBuffer();
		while((line=buffread.readLine())!=null)
		{
			sb.append(line);
		}
		System.out.println(response.getStatusLine().getStatusCode());
		//System.out.println(sb);c
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir")+"//Response//googleMap.json");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
	}



}
