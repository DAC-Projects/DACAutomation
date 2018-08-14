package com.dac.main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.BaseTest;
import resources.DateFormats;


public class BasePage {
	
	public WebDriver driver;
	public Actions action;
	public Select select;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
	}
	
	
	
	
	public boolean checkFileSizeIncrsd(long initialSize, int timeout, File dir) {
		
		WebDriverWait dwnldwait = new WebDriverWait(driver, timeout);
			   
			   ExpectedCondition<Boolean> chkFileDownld = new ExpectedCondition<Boolean>() {
				      public Boolean apply(WebDriver driver) {
				        
				          return (initialSize < dir.listFiles().length);
				      }
				    };
				    System.out.println("directory size is "+ dir.listFiles().length);
				    return dwnldwait.until(chkFileDownld);
	}
	
	/**
	 * @param browser
	 * @param downloadBTN
	 * @throws InterruptedException 
	 * clicks the webelement, wait for timeout specified in browser specified
	 */
	public void download(String browser, WebElement downloadBTN, int timeout) throws InterruptedException {
	
	
		File dwnldDir = new File("./downloads");
		long initialSize = dwnldDir.listFiles().length;  
		System.out.println("directory size is "+ initialSize);// check file size
			  		
  
						   
		if("chrome".equalsIgnoreCase(BaseTest.browser)) {
			clickelement(downloadBTN);
			 Thread.sleep(1000);
		}
		else  {
			try
			{
				 clickelement(downloadBTN);
				 Thread.sleep(4000);
			     Robot robot = new Robot();
			     robot.setAutoDelay(250);
			     robot.keyPress(KeyEvent.VK_ALT);
			     robot.keyPress(KeyEvent.VK_S);
			     Thread.sleep(1000);
			     robot.keyRelease(KeyEvent.VK_ALT);
			     if("firefox".equalsIgnoreCase(BaseTest.browser))
			     robot.keyPress(KeyEvent.VK_ENTER);
			     
			}
			catch (AWTException e)
			{
			    e.printStackTrace();
			}
		}
		
	if(checkFileSizeIncrsd(initialSize, timeout, dwnldDir))
		System.out.println("File Successfully Downloaded");
	 else 
		Assert.fail("File not downloaded in "+ timeout +"seconds");
	
			
	}
	
	
	/**
	 * files(pdf, Excel, word, image etc) to upload and that file should present in "FilesToUpload" folder.
	 * Before using this method we have to use "clickElement" of BasePage
	 * 
	 * @param fileNameWithExtension
	 * @throws IOException
	 */
	public void upload(String fileNameWithExtension) throws IOException {
		File logoPath=new File(".\\filesToUpload\\"+fileNameWithExtension);
		String filepath=logoPath.getAbsolutePath();
		String filepathexe = "./UploadFile.exe";
		Runtime.getRuntime().exec(filepathexe+" "+filepath);
	}
	
	public void clickelement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			if(element.isDisplayed() & element.isEnabled()) {
				try {
					action = new Actions(driver);
					action.moveToElement(element).click(element).perform();
				}
				catch(WebDriverException e) {
					element.click();					
				}
			}
		}
		catch(NoSuchElementException e) {
			Assert.fail("element "+ element+" NOT found");
		}
	}
	
	public static String minusDays(String langCode, String countryCode, int days_Amount) {
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.DATE, -days_Amount);
		String finalDate = DateFormats.dateFormat(langCode, countryCode).format(c.getTime());
		return finalDate;
	}
	
	public static String addDays(String langCode, String countryCode, int days_Amount) {
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.DATE, days_Amount);

		String finalDate = DateFormats.dateFormat(langCode, countryCode).format(c.getTime());
		return finalDate;
	}
	
	public void waitUntilLoad(int timeSec) {
		try {
	    new WebDriverWait(driver, timeSec).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		}catch (Exception e) {
			Assert.fail("Page failed to load in " +timeSec+" seconds");
		}
	} 
	
	/**
	 * To get and store the value/text of a field 	*/
	public String getClipboardContents(WebElement element) throws UnsupportedFlavorException, IOException {
		String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));
		element.sendKeys(Keys.CONTROL+"a");
		element.sendKeys(copy);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String eleText = (String) contents.getTransferData(DataFlavor.stringFlavor);
		//System.out.println(eleText);
		return eleText;
}
	
	public static String getLastModifiedFile(String dirPath) throws InterruptedException{
		Thread.sleep(4000);
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile.getName();
	}
	

	
	public static String getFileNames_Dir(String folderPath) {
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		String fileName = "";
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	 fileName = listOfFiles[i].getName();
		    	 System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		return fileName;
	}
	
	
	public void verifyText(WebElement e,String eText) {
		String aText=e.getText().trim();
		System.out.println(aText);
		Assert.assertEquals(aText, eText);
	}
	

	public void scrollByElement(WebElement element) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int yLoc = element.getLocation().getY()-10;
		int xLoc = element.getLocation().getX();
		js.executeScript("window.scrollTo("+xLoc+", "+yLoc+")");
	}
	
	
	public static String getDate() {
		Date date = new Date();
		String dateFormat = new SimpleDateFormat("dd-MM-yyyy").format(date);
		return dateFormat.toString();
	}
	
	
	
	
	public static String getDateNTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date();
		String dateFormat = sdf.format(date);
		return dateFormat.toString();
	}

	
	
	/**retrun true if alert is present 
	 * @return
	 */
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   
	}   
	
	
	
	/**waits for an element for specified timeout
	 * @param elemnt
	 * @param timeSec
	 */
	public boolean waitForElement(WebElement elemnt,int timeSec) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, timeSec);
		if(wait.until(ExpectedConditions.visibilityOf(elemnt)) != null|| false) {
			scrollByElement(elemnt);
			return true;
		}else return false;
		
	}catch (TimeoutException e) {
		Assert.fail("Element did not load in the specified timeout");
		return false;
	}
	}
	
	public String[][] readTable(WebElement table) {

		List<WebElement> allRows = table.findElements(By.tagName("tr")); 
		String[][] rowResults = new String[allRows.size()][];
		int i=0;
		String[] cellValues;
		for (WebElement row : allRows) { 
			
		    List<WebElement> cells = row.findElements(By.tagName("td")); 
		    if (cells.size()==0)
		    	cells = row.findElements(By.tagName("th"));
		    
		    cellValues = new String[cells.size()];
		    int j=0;
		    for (WebElement cell : cells) { 
		    	
		    	if(cell.getText()!=null)
		    	{
		    		cellValues[j]= cell.getText().replaceAll("[^\\p{Print}]", "").toString();
		    		
		    	}else cellValues[j] = "";
		    	
		    	j++;
		    }
		    rowResults[i] = cellValues;
		    
		    System.out.println(Arrays.toString(rowResults[i]));
		    
		    i++;
		}
		System.out.println("\n");
		return rowResults;
	}
}
