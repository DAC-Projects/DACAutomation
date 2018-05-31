package com.dac.main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResponsesPage_RS extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public ResponsesPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="btnReviewsExport")
	private WebElement exportBTN;
	
	@FindBy(xpath="//table[@id='reviewTable']/thead")
	private WebElement reviewTableHeader;
	
	@FindBy(xpath="//table[@id='reviewTable']/tbody")
	private WebElement reviewTableRow;
	
	@FindBy(xpath="//td//*[@class='stars']")
	private WebElement starRating;
	
	@FindBy(className="feedbackId")
	private WebElement checkboxMarkInclusion;
	
	@FindBy(xpath="//*[@id='stardiv']/div")
	private List<WebElement> AverageStarRatingText;
	
	public String avgStarRatingData() throws InterruptedException {
		//Thread.sleep(6000);
		String avgRating = "";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		for(int i=0;i<AverageStarRatingText.size();i++) {
			avgRating = avgRating +" "+ AverageStarRatingText.get(i).getText();
		}
		System.out.println(avgRating);
		return avgRating;
	}
	
	public void clickExportBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		if(exportBTN.isEnabled() & exportBTN.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportBTN));
			exportBTN.click();
		}
		else {
			System.out.println("No Data Available in Review Table");
		}
	}
	
	public void getReviewTableData() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		WebElement reviewText = driver.findElement(By.xpath("//*[@class='col-sm-12']/h3"));
		scrollByElement(reviewText, driver);
		
		//To locate rows of table. 
    	List < WebElement > rows_table = reviewTableRow.findElements(By.tagName("tr"));
    	//To calculate no of rows In table.
    	int rows_count = rows_table.size();
    	//Loop will execute till the last row of table.
    	for (int row = 0; row < rows_count; row++) {    		
    		//To locate columns(cells) of that specific row.
    	    List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
    	    //To calculate no of columns (cells). In that specific row.
    	    int columns_count = Columns_row.size();
    	    int noOfRows=row+1;
    	    System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
    	    //Loop will execute till the last cell of that specific row.
    	    for (int column = 0; column < columns_count; column++) {
    	    	
    	    	List<WebElement> headerTableRow=reviewTableHeader.findElements(By.tagName("th"));
        		String headerText = headerTableRow.get(column).getText();
        		
        		String celtext ="";
        		
        		if(column==6) {
        			
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
        		
        		if(column==0) {
        			if(checkboxMarkInclusion.isSelected()) {
        				celtext="Y";
        			}
        			else {
        				celtext="N";
        				checkboxMarkInclusion.click();
        				//System.out.println("click checkbox of mark of Inclusion");
        				celtext="Y";
        			}
        		}
        		//celtext = Columns_row.get(column).getText();
    	        System.out.println("Cell Value of row " + noOfRows + " and column " + headerText + " Is : " + celtext);
    	    }
    	    System.out.println("-------------------------------------------------- ");
    	}
	}
}
