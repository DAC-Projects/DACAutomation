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
import java.util.concurrent.TimeUnit;

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

import resources.CurrentState;
import resources.DateFormats;

/***/
public class BasePage {

  public WebDriver driver;
  public Actions action;
  public Select select;
  public WebDriverWait wait;
  public JavascriptExecutor js;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 35);
    action = new Actions(driver);
    js = (JavascriptExecutor) driver;
  }

  
  /**
   * This method is used to wait the execution till web page gets completely loaded but  
   * in few cases where java script waiting code cannot be work as expected		
   * 
   * @param driver : for which driver need to wait till page's script get loaded		*/
  public void waitUntilLoad(WebDriver driver) {

    // WebDriverWait wait = new WebDriverWait(driver, 30);
    try {
      // wait for jQuery to load
      ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver driver) {
          try {
            return ((Long) ((JavascriptExecutor) driver)
                .executeScript("return jQuery.active") == 0);
          } catch (Exception e) {
            // no jQuery present
            return true;
          }
        }
      };

      // wait for Javascript to load
      ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver driver) {
          return ((JavascriptExecutor) driver)
              .executeScript("return document.readyState").toString()
              .equals("complete");
        }
      };

      wait.until(jQueryLoad);
      wait.until(jsLoad);
    } catch (TimeoutException e) {
      throw new AssertionError("page did not load within specified timeout");
    }
  }

  /**
   * This method is used to check whether downloaded file is saved completely in specific directory
   * 
   * @param	initialSize : current size of the directory before downloading a file
   * @param timeout : waiting time to download the file
   * @param dir : directory where the file will download			 */
  public void checkFileSizeIncrsd(long initialSize, int timeout, File dir) {

    WebDriverWait dwnldwait = new WebDriverWait(driver, timeout);

    ExpectedCondition<Boolean> chkFileDownld = new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {

        return (initialSize < dir.listFiles().length);
      }
    };
    System.out.println("directory size is " + dir.listFiles().length);

    if(!dwnldwait.until(chkFileDownld)) {
    	throw new TimeoutException("File not downloaded within "+timeout+" seconds.");
    }
  }

  /**
   * This method used to download the file in any browser.
   * 
   * @param browser : On which browser downloading the file
   * @param downloadBTN : webelement to download the file
   * @param timeout : waiting time to download the file
   * @throws InterruptedException
   */
  public synchronized void download(String browser, WebElement downloadBTN, int timeout)
      throws InterruptedException {

    File dwnldDir = new File("./downloads");
    long initialSize = dwnldDir.listFiles().length;
    System.out.println("directory size is " + initialSize);// check file size

      try {
        clickelement(downloadBTN);
        Thread.sleep(4000);
        Robot robot = new Robot();
        robot.setAutoDelay(5000);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_S);
        if ("firefox".equalsIgnoreCase(CurrentState.getBrowser()))
          robot.keyPress(KeyEvent.VK_ENTER);

      } catch (AWTException e) {
        e.printStackTrace();
      }

    checkFileSizeIncrsd(initialSize, timeout, dwnldDir);

  }

  /**
   * files(pdf, Excel, word, image etc) to upload and that file should present
   * in "FilesToUpload" folder. Before using this method we have to use
   * "clickElement" of BasePage
   * 
   * @param fileNameWithExtension : pass the file name with extension to upload from the project's "FilesToUpload" directory (ex: logo.jpeg)
   * @throws IOException
   */
  public void upload(String fileNameWithExtension) throws IOException {
    File logoPath = new File(".\\filesToUpload\\" + fileNameWithExtension);
    String filepath = logoPath.getAbsolutePath();
    String filepathexe = "./UploadFile.exe";
    Runtime.getRuntime().exec(filepathexe + " " + filepath);
  }

  /**
   * To click on a button using actions class or click() method based on support by the browsers and elements	
   * 
   * @param element : element/button to click		*/
  public void clickelement(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
     	try {
    	      if (element.isDisplayed() & element.isEnabled()) {
    	        try {
    	          action = new Actions(driver);
    	          action.moveToElement(element).perform();
    	          action.moveToElement(element).click(element).perform();
    	        } catch (WebDriverException e) {
    	          element.click();
    	        }
    	      }
    	    } catch (NoSuchElementException e) {
    	      Assert.fail("element " + element + " NOT found");
    	    }
    
  }

  /**
   * To get the specific reduced date from today's date in specific format based on the country and language code
   * 
   * @return the date in specific date format based on passed contry code and language code
   * @param langCode : Language code, date format to be in specific format for
   * 				   English : en , 	 german  : de , 	spanish : es
   * 				   french  : fr , 	 italian : it ,		swedish : sv
   * 
   * @param countryCode : Country code could be case sensitive, date format to be in specific format for
   * 				      unitedStates    : US ,   german          : DE , 	spanish(Spain) : ES
   * 				      spanish(Mexico) : MX ,   french(France)  : FR ,   french(Canada) : CA
   * 				      italian         : IT ,   swedish : SE
   * 
   * @param days_Amount : number of days to reduce from today's date 
   * 				
   * */
  public static String minusDays(String langCode, String countryCode,
      int days_Amount) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, -days_Amount);
    String finalDate = DateFormats.dateFormat(langCode, countryCode)
        .format(c.getTime());
    return finalDate;
  }

  /**
   * To get the specific add days from today's date in specific format based on the country and language code
   * 
   * @return the date in specific date format based on passed contry code and language code
   * @param langCode : Language code, date format to be in specific format for
   * 				   English : en , 	 german  : de , 	spanish : es
   * 				   french  : fr , 	 italian : it ,		swedish : sv
   * 
   * @param countryCode : Country code could be case sensitive, date format to be in specific format for
   * 				      unitedStates    : US ,   german          : DE , 	spanish(Spain) : ES
   * 				      spanish(Mexico) : MX ,   french(France)  : FR ,   french(Canada) : CA
   * 				      italian         : IT ,   swedish : SE
   * 
   * @param days_Amount : number of days to add to the today's date 
   * 				
   * */
  public static String addDays(String langCode, String countryCode,
      int days_Amount) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, days_Amount);

    String finalDate = DateFormats.dateFormat(langCode, countryCode)
        .format(c.getTime());
    return finalDate;
  }

  /**
   * To get and store the value/text of a field
   * For disabled elements it will throw InvalidElementStateException.
   */
  public String getClipboardContents(WebElement element)
      throws UnsupportedFlavorException, IOException {
	  String eleText = "";
	  if(element.isEnabled()) {
		  String copy = Keys.chord(Keys.CONTROL, Keys.chord("c"));
		  element.sendKeys(Keys.CONTROL + "a");
		  element.sendKeys(copy);
		  Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		  Transferable contents = clipboard.getContents(null);
		  eleText = (String) contents.getTransferData(DataFlavor.stringFlavor);
		  // System.out.println(eleText);
	  }
	  else {
		  //code to add in future to resolve the issue with copy text from disabled elements
	  }
    return eleText;
  }

  /**
   * This method used to get the last modified file name in a specific folder
   * 
   * @param dirPath : pass the directory path in which folder to get the last modified file name	*/
  public static String getLastModifiedFile(String dirPath)
      throws InterruptedException {
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

  /**
   * This method is used to print all the files and directory names present in a specific folder path
   * and return the last entry file name
   * 
   * @param folderPath : pass a folder path(string format) in which to print the files and directory names 		*/
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

  /**
   * This method is used to verify the text in web page whether it matches with the expected text or not
   * including the verification of case sensitive
   * 
   * Used for weblet testing, tool tip text verification etc. 		*/
  public void verifyText(WebElement e, String eText) {
    String aText = e.getText().trim();
    System.out.println(aText);
    Assert.assertEquals(aText, eText);
  }

  /**
   * This method will helps to scroll the web page till the element
   * 
   * @param element : to scroll the web page till the specific web element		*/
  public void scrollByElement(WebElement element) {

    JavascriptExecutor js = (JavascriptExecutor) driver;
    int yLoc = element.getLocation().getY() - 10;
    int xLoc = element.getLocation().getX();
    js.executeScript("window.scrollTo(" + xLoc + ", " + yLoc + ")");

  }

  /**
   * This method is used to get the today's date in IST format ie. dd-mm-yyyy 		*/
  public static String getDate() {
    Date date = new Date();
    String dateFormat = new SimpleDateFormat("dd-MM-yyyy").format(date);
    return dateFormat.toString();
  }

  /**
   * This method is used to get the today's date and current execution state time in IST format ie. dd-mm-yyyy HH:mm 		*/
  public static String getDateNTime() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    Date date = new Date();
    String dateFormat = sdf.format(date);
    return dateFormat.toString();
  }

  /**
   * retrun true if alert is present
   * 
   * @return
   */
  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } // try
    catch (NoAlertPresentException Ex) {
      return false;
    }
  }

  /**
   * waits for an element for specified timeout
   * 
   * @param elemnt  : element to check whether is it displayed or not in DOM
   * @param timeSec : pass the waiting time to check whether element is displayed or not up to certain seconds
   */
  public boolean waitForElement(WebElement elemnt, int timeSec) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeSec);
    /*  if ((wait.until(ExpectedConditions.visibilityOf(elemnt)) != null)
          || (wait.until(ExpectedConditions.visibilityOf(elemnt)) != false) {
        return true;
      } else
        return false;*/

       return wait.until(ExpectedConditions.visibilityOf(elemnt)) == elemnt ? true : false;
    } catch (TimeoutException e) {
      Assert.fail("Element did not load in the specified timeout");
      return false;
    }
  }

  /**
   * This method to print the UI table data in Console
   * 
   * @param table : webelement of UI table for reading the table
   */
  public String[][] readTable(WebElement table) {
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    List<WebElement> allRows = table.findElements(By.tagName("tr"));
    String[][] rowResults = new String[allRows.size()][];
    int i = 0;
    String[] cellValues;
    for (WebElement row : allRows) {

      List<WebElement> cells = row.findElements(By.tagName("td"));
      if (cells.size() == 0)
        cells = row.findElements(By.tagName("th"));

      cellValues = new String[cells.size()];
      int j = 0;
      for (WebElement cell : cells) {

        if (cell.getText() != null) {
          cellValues[j] = cell.getText().replaceAll("[^\\p{Print}]", "")
              .toString();

        } else
          cellValues[j] = "";

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
