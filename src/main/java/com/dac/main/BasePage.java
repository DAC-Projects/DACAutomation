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
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.POM_CF.BaseTest_CF;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.DateFormats;

/**
 * @author wasim
 *
 */
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
	
	public BasePage() {}
	
	public void waitUntilLoad(WebDriver driver) {

	    //WebDriverWait wait = new WebDriverWait(driver, 30);

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          // no jQuery present
	          return true;
	        }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
	        .toString().equals("complete");
	      }
	    };

	  wait.until(jQueryLoad);wait.until(jsLoad);
	}
	
	/*
	public void verifyPageIsDisplayed(WebDriver driver,String eResult) {
		String sETO=AutoUtil.getProperty(IAutoConst.CONFIG_PATH, "ETO");
		long ETO=Long.parseLong(sETO);
		WebDriverWait wait=new WebDriverWait(driver,ETO);
		try {
			Reporter.log(eResult,true);
			wait.until(ExpectedConditions.titleIs(eResult));
			Reporter.log("PASS: Expected Page is Displayed",true);
		}
		catch(Exception e) {
			Reporter.log("FAIL: Expected Page is NOT Displayed",true);
			Assert.fail();
		}
	}
	
	*/
	
	
	/**
	 * @param browser
	 * @param downloadBTN
	 * @throws InterruptedException 
	 */
	public void download(String browser, WebElement downloadBTN) throws InterruptedException {
		if("chrome".equalsIgnoreCase(BaseTest.browser)) {
			clickelement(downloadBTN, driver);
		}
		else {
			try
			{
				 clickelement(downloadBTN, driver);
				 Thread.sleep(2000);
			     Robot robot = new Robot();
			     robot.setAutoDelay(250);
			     robot.keyPress(KeyEvent.VK_ALT);
			     robot.keyPress(KeyEvent.VK_S);
			     robot.keyRelease(KeyEvent.VK_ALT);
			     robot.keyPress(KeyEvent.VK_ENTER);
			}
			catch (AWTException e)
			{
			    e.printStackTrace();
			}
		}
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
	
	public void clickelement(WebElement element, WebDriver driver) {
		wait = new WebDriverWait(driver, 35);
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
			BaseTest.logger.log(LogStatus.INFO, "element "+ element+" NOT found");
			printexcep(e);
		}
	}
	
	public void printexcep(Exception e) {
		try {
			throw new Exception(e);			
		}
		catch(Exception ex) {
			ex.printStackTrace();
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
	

	public void scrollByElement(WebElement element,WebDriver driver) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int yLoc = element.getLocation().getY();
		int xLoc = element.getLocation().getX();
		js.executeScript("window.scrollTo("+xLoc+", "+yLoc+")");
		//js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
/*	protected void uploadFile(String fileName, String extension) {
		
		File uploadingFilePath =new File("./"+fileName+extension);
		String fileAbsPath=uploadingFilePath.getAbsolutePath();
		
		AutoItX x=new AutoItX();
		x.winWaitActive("Open");
		x.controlFocus("Open", "", "Edit1");
		x.ControlSetText("Open", "", "Edit1", fileAbsPath);
		x.controlClick("Open", "", "Button1");
	}*/
	
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
	    }   // catch 
	}   // isAlertPresent()
}
