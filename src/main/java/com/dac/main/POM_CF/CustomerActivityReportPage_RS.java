package com.dac.main.POM_CF;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;

import resources.ExcelTestDataHandler;
import resources.IAutoconst;
import resources.formatConvert;

public class CustomerActivityReportPage_RS extends BasePage{

	public ArrayList<String> tableCellValues = new ArrayList<String>();
	
	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait wait;
	JavascriptExecutor js;
	SoftAssert assertValue;
	
	public CustomerActivityReportPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
		assertValue= new SoftAssert();
		
	}
	
	@FindBy(id="btnDownload")
	private WebElement downloadReportBTN;
	
	@FindBy(xpath="//div[contains(@class,'campaignLocation')]/b")
	private WebElement campName;
	
	@FindBy(id="customerActivityTable_info")
	private WebElement noOfEntriesReviewTable;
	
	@FindBy(xpath="//*[@id='customerActivityTable']/tbody")
	private WebElement tableBodyLocate;
	
	@FindBy(xpath="//*[@id='customerActivityTable']/tbody/tr")
	private List<WebElement> tableRows;
	
	@FindBy(id="customerActivityTable")
	private WebElement ReportTableHeaders;
	
	@FindBy(xpath="//*[@id='customerActivityTable']/tbody/tr/td[1]")
	private List<WebElement> noTableRows;
	
	@FindBy(xpath="//*[@class='stars']//span")
	private List<WebElement> allStarRating;
	
	@FindBy(xpath="//*[@class='stars']")
	private WebElement starRating;
	
	@FindBy(xpath="//*[@id='page-content']/h2")
	private WebElement custActPageName;
	
	@FindBy(xpath="//*[@id='page-content']/p")
	private WebElement custActPageDesc;
	
	@FindBy(xpath="(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath="(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	
	/**
	 * page Name = Customer Activity Report*/
	public void verifyPageName(String pageName) {
		verifyText(custActPageName, pageName);
	}
	
	/**
	 * page Description = This report identifies the customer activity for a specific campaign.*/
	public void verifyPageDesc(String pageDesc) {
		verifyText(custActPageDesc, pageDesc);
	}
	
	/**
	 * @eText : Expected camp name Text tend to display in customer activity page 
	 * note : eText should be Case Sensitive*/
	public void verifyCampName(String eText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		verifyText(campName, eText);
	}
	
	/**
	 * BTNtext = Download Report*/
	public void verifyDownloadBTNtext(String BTNtext) {
		if(downloadReportBTN.isDisplayed()) {
			verifyText(downloadReportBTN, BTNtext);
		}
		else {
			System.out.println("Download Report Button is NOT displayed");
		}
	}
	
	public void clickDownloadReport() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		downloadReportBTN.click();
		Thread.sleep(5000);
		//String filename = getLastModifiedFile("./downloads");
		System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
	}
	
	public void getTableValues() {
		
		System.out.println("total values : "+tableCellValues.size());
		for(int i=0; i<tableCellValues.size();i++) {
			System.out.println(tableCellValues.get(i));
		}
	}
	
	public void compareXlData_UIdata() throws Exception {
		
		List < WebElement > Columns_row = ReportTableHeaders.findElements(By.tagName("th"));
		int col_count = Columns_row.size();
		//System.out.println("col_count : "+col_count);
		String fileName = BasePage.getLastModifiedFile("./downloads");
		System.out.println("file name : "+fileName);
		String newfilename = new formatConvert("./downloads/"+fileName).convertFile("xlsx");
		new ExcelTestDataHandler("./downloads/"+newfilename, "Sheet0").deleteRows();
		int xlRowCount=new ExcelTestDataHandler("./downloads/"+newfilename, "Sheet0").getRowCount();
		//System.out.println("xlRowCount : "+xlRowCount);
		int count = 0;
		for(int i=0;i<xlRowCount;i++) {
			for(int j=0;j<col_count;j++) {
				String cellValue = new ExcelTestDataHandler("./downloads/"+newfilename, "Sheet0").getCellValue(i, j);
				String uiTableCellValue = tableCellValues.get(count);
				if(uiTableCellValue.equals(cellValue)) {
					//assertValue.assertTrue(true, uiTableCellValue+" is matches with Downloaded Excel value : "+cellValue);
					Assert.assertTrue(true, uiTableCellValue+" is matches with Downloaded Excel value : "+cellValue);
				}
				else {
					//assertValue.assertTrue(false, uiTableCellValue+" is NOT matches with Downloaded Excel value : "+cellValue);
					Assert.assertTrue(false, uiTableCellValue+" is NOT matches with Downloaded Excel value : "+cellValue);
				}
				count++;
			}
		}
	}
	
	
/*	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().contains(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
	private boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}*/
	
	public void verifyOrderOfTableHeader(String[] tableHeaderData) {
		
		List < WebElement > Columns_row = tableBodyLocate.findElements(By.tagName("td"));
		int columns_count = Columns_row.size();
		List<WebElement> headerTableRow=ReportTableHeaders.findElements(By.tagName("th"));
		for (int column = 0; column < columns_count & column < tableHeaderData.length; column++) {
    		String headerText = headerTableRow.get(column).getText().trim();
    		System.out.println(headerText);
    		if(headerText.equals(tableHeaderData[column])) {
    			tableCellValues.add(headerText);
    			Assert.assertTrue(true, "Table Header Data is in Order");
    		}
    		else {
    			Assert.assertTrue(false, "Table Header Data is NOT in Order");
    		}
		}
	}
	
	public void getCustActivityRepoTableData() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		//System.out.println("Table Columns : "+ReportTableHeaders.size());
		System.out.println("TableRows : "+noTableRows.size());
		
    	//To locate rows of table. 
    	List < WebElement > rows_table = tableBodyLocate.findElements(By.tagName("tr"));
    	//To calculate no of rows In table.
    	int rows_count = rows_table.size();
    	
    	List<WebElement> ab = starRating.findElements(By.tagName("span"));
		System.out.println(allStarRating.size());
		
    	//Loop will execute till the last row of table.
    	for (int row = 0; row < rows_count; row++) {    		
    		//To locate columns(cells) of that specific row.
    	    List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
    	    //To calculate no of columns (cells). In that specific row.
    	    int columns_count = Columns_row.size();
    	    System.out.println("Number of cells In Row " + row + " are " + columns_count);

    	    //Loop will execute till the last cell of that specific row.
    	    for (int column = 0; column < columns_count; column++) {
    	    	
    	    	List<WebElement> headerTableRow=ReportTableHeaders.findElements(By.tagName("th"));
        		String headerText = headerTableRow.get(column).getText();
        		
        		
        		String celtext ="";
        		if(column==4) {
        			//Thread.sleep(2000);
        			
        			try {
    					String y=allStarRating.get(row).getAttribute("style");
    					System.out.println("star rating attribue value : "+y);
        			
        			if(y.contains("16")){
        				celtext ="1";
        			}
        			else if(y.contains("32")) {
        				celtext ="2";
        			}
        			else if(y.contains("48")) {
        				celtext ="3";
        			}
        			else if(y.contains("64")) {
        				celtext ="4";
        			}
        			else if(y.contains("80")) {
        				celtext ="5";
        			}
        			}
        			catch(Exception e) {
        				e.printStackTrace();
        			}
        		}
        		else {
        			// To retrieve text from that specific cell.
        			celtext = Columns_row.get(column).getText();
        		}
        		//celtext = Columns_row.get(column).getText();
        		tableCellValues.add(celtext);
    	        System.out.println("Cell Value of row number " + row + " and column " + headerText + " Is : " + celtext);
    	    }
    	    System.out.println("-------------------------------------------------- ");
    	}
	}
	
	public void clickPreviousBTN() {
		if(paginationPrev.isEnabled()) {
			paginationPrev.click();
		}
		else {
			System.out.println("Only 1 page is there so Previous button is disabled");
		}
	}
	
	public void clickNextBTN() {
		if(paginationNext.isEnabled()) {
			paginationNext.click();
		}
		else {
			System.out.println("Only 1 page is there so Next button is disabled");
		}
	}
}
