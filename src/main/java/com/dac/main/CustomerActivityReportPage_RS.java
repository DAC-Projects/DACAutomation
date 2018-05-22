package com.dac.main;

import java.util.ArrayList;
import java.util.List;

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

public class CustomerActivityReportPage_RS extends BasePage{

	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public CustomerActivityReportPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
		
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
	
	@FindBy(xpath="//*[@class='stars']")
	private WebElement starRating;
	
	public void clickDownloadReport() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		downloadReportBTN.click();
	}
	
	public void getCustActivityRepoTableData() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		//System.out.println("Table Columns : "+ReportTableHeaders.size());
		System.out.println("TableRows : "+noTableRows.size());
		
    	//To locate rows of table. 
    	List < WebElement > rows_table = tableBodyLocate.findElements(By.tagName("tr"));
    	//To calculate no of rows In table.
    	int rows_count = rows_table.size();
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
        			
        			List<WebElement> ab = starRating.findElements(By.tagName("span"));
        			try {
    					String y=ab.get(row).getAttribute("style");
        			
        			if(y.contains("16")){
        				celtext ="1 Star";
        			}
        			else if(y.contains("32")) {
        				celtext ="2 Stars";
        			}
        			else if(y.contains("48")) {
        				celtext ="3 Stars";
        			}
        			else if(y.contains("64")) {
        				celtext ="4 Stars";
        			}
        			else if(y.contains("80")) {
        				celtext ="5 Stars";
        			}
        			}
        			catch(Exception e) {
        				
        			}
        		}
        		else {
        			// To retrieve text from that specific cell.
        			celtext = Columns_row.get(column).getText();
        		}
        		//celtext = Columns_row.get(column).getText();
    	        System.out.println("Cell Value of row number " + row + " and column " + headerText + " Is : " + celtext);
    	    }
    	    System.out.println("-------------------------------------------------- ");
    	}
	}
	
	
	/**
	 * @eText : Expected camp name Text tend to display in customer activity page 
	 * note : eText should be Case Sensitive*/
	public void verifyCampName(String eText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		verifyText(campName, eText);
	}
	
}
