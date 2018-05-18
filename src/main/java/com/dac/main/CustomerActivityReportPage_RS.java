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
	
	@FindBy(id="LocationName")
	private WebElement locationNameHeader;
	
	@FindBy(id="LocationNumber")
	private WebElement locationNumberHeader;
	
	@FindBy(id="Email")
	private WebElement emailHeader;
	
	@FindBy(id="Name")
	private WebElement nameHeader;
	
	@FindBy(id="Rating")
	private WebElement starRatingHeader;
	
	@FindBy(id="Comment")
	private WebElement CommentsHeader;
	
	@FindBy(id="Unsubscribed")
	private WebElement unSubscribedHeader;
	
	@FindBy(id="customerActivityTable_info")
	private WebElement noOfEntriesReviewTable;
	
	@FindBy(xpath="//*[@id=\"customerActivityTable\"]/tbody")
	private WebElement tableBodyLocate;
	
	@FindBy(xpath="//*[@id='customerActivityTable']/tbody/tr")
	private List<WebElement> tableRows;
	
	@FindBy(xpath="//*[@id='customerActivityTable']//th")
	private List<WebElement> ReportTableHeaders;
	
	@FindBy(xpath="//*[@id='customerActivityTable']/tbody/tr/td[1]")
	private List<WebElement> noTableRows;
	
	
	public void clickDownloadReport() {
		downloadReportBTN.click();
	}
	
	public void custAvtivityRepoTableData() {
		
		System.out.println("Table Columns : "+ReportTableHeaders.size());
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
    	        // To retrieve text from that specific cell.
    	        String celtext = Columns_row.get(column).getText();
    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
    	    }
    	    System.out.println("-------------------------------------------------- ");
    	}
	}
	
	/**
	 * @eText : Expected camp name Text tend to display in customer activity page 
	 * note : eText should be Case Sensitive*/
	public void verifyCampName(String eText) {
		verifyText(campName, eText);
	}
	
}
