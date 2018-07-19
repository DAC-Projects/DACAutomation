package com.dac.main;

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
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BasePage {
	
	public static SimpleDateFormat engDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
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
		Long a = 0L;	
		
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
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String dateFormat = engDateFormat.format(date);
		return dateFormat.toString();
	}
	
	public static String getDateNTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date();
		String dateFormat = sdf.format(date);
		return dateFormat.toString();
	}

}
