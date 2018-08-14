package com.dac.main.POM_CF;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.DecimalFormat;
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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;
import resources.DateFormats;
import resources.ExcelTestDataHandler;
import resources.formatConvert;

public class ResponsesPage_RS extends BasePage{

	public ArrayList<String> tableCellValues = new ArrayList<String>();
	
	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait wait;
	JavascriptExecutor js;
	SoftAssert assertValue;
	BaseTest_CF bt = new BaseTest_CF();
	
	static int totalStarCount = 0, star1_Count=0, star2_Count=0, star3_Count=0, star4_Count=0, star5_Count=0;
	static int sumOfAllStars = 0, star1_Sum = 0, star2_Sum = 0, star3_Sum = 0, star4_Sum = 0, star5_Sum = 0;
	
	
	public ResponsesPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
		assertValue= new SoftAssert();
	}
	
	@FindBy(tagName="h1")
	private WebElement ResponsesPageName;
	
	@FindBy(tagName="p")
	private WebElement ResponsesPageDesc;
	
	@FindBy(id="ddlCampaign")
	private WebElement selectCampaign;
	
	@FindBy(id="dateFrom")
	private WebElement fromDate;
	
	@FindBy(id="dateTo")
	private WebElement toDate;
	
	@FindBy(id="btnApply")
	private WebElement applyFilter;
	
	@FindBy(id="btnReviewsExport")
	private WebElement exportBTN;
	
	@FindBy(xpath="//*[@id='reviewTable']/tbody")
	private WebElement tableBodyLocate;
	
	@FindBy(id="reviewTable")
	private WebElement ReportTableHeaders;
	
	@FindBy(xpath="//table[@id='reviewTable']/thead")
	private WebElement reviewTableHeader;
	
	@FindBy(xpath="//table[@id='reviewTable']/tbody")
	private WebElement reviewTableRow;
	
	@FindBy(xpath="//td//*[@class='stars']//span")
	private List<WebElement> allStarRating;
	
	@FindBy(xpath="//td//*[@class='stars']")
	private WebElement starRating;
	
	@FindBy(className="feedbackId")
	private WebElement checkboxMarkInclusion;
	
	@FindBy(xpath="//div[@class='col-sm-11']/h3")
	private WebElement overviewSection;
	
	@FindBy(xpath="(//h5)[1]")
	private WebElement avgRatingWidgetTitle;
	
	@FindBy(xpath="//*[@id='divOverallStarRatings']//h5")
	private WebElement overallRatingWidgetTitle;
	
	@FindBy(xpath="//*[@id='dt-list-1']/dt/span/span")
	private List<WebElement> overallRatingStars;
	
	@FindBy(xpath="//*[@id='dt-list-1']//div[contains(@class,'progress-percent')]")
	private List<WebElement> overallStarsPercents;
	
	@FindBy(xpath="//div[contains(@class,'progress progress')]")
	private List<WebElement> overallSR_BarGraphs;
	
	@FindBy(className="popover-content")
	private WebElement overallSR_GraphsToolTip;
	
	@FindBy(xpath="//*[@id='stardiv']/div[2]/span[1]")
	private WebElement AvgRatingScore;
	
	@FindBy(xpath="//*[@id='stardiv']/div[3]/span")
	private WebElement AvgRatingReviewsCount;
	
	@FindBy(xpath="//*[@id='stardiv']/div")
	private List<WebElement> AverageStarRatingText;
	
	@FindBy(xpath="(//*[@id='stardiv']/div/span)[last()]")
	private WebElement numReviewSubmitted_AverageSR;
	
	@FindBy(xpath="(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath="//*[@class='pagination']//a")
	private List<WebElement> pagination;
	
	@FindBy(xpath="(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	static private int numOfReviews = 0;	
	
	public void verifyToDate(String langCode, String contryCode) throws UnsupportedFlavorException, IOException  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String toDateUI = getClipboardContents(toDate);
		String todayDate = DateFormats.shortDate(langCode, contryCode);
		if (toDateUI.equals(todayDate)) {
			Assert.assertEquals(toDateUI, todayDate);
		}
		else if(toDateUI.equals(bt.campEndDate)){
			Assert.assertEquals(toDateUI, bt.campEndDate);
		}
		else {
			Assert.fail();
		}
	}
	
	public void verifyUnarchivedToDate(String langCode, String contryCode) throws UnsupportedFlavorException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String toDateUI = getClipboardContents(toDate);
		String todayDate = DateFormats.shortDate(langCode, contryCode);
		if (toDateUI.equals(todayDate)) {
			Assert.assertEquals(toDateUI, todayDate);
		}
		else {
			Assert.fail();
		}
	}
	
	public void verifyUnarchivedFromDate(String langCode, String countryCode) throws UnsupportedFlavorException, IOException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String fromDateUI = getClipboardContents(fromDate);
		String minus30Date = BasePage.minusDays(langCode, countryCode, 30);
		if (fromDateUI.equals(minus30Date)) {
			Assert.assertEquals(fromDateUI, minus30Date);
		}
		else {
			Assert.fail();
		}
	}
	
	public void verifyFromDate(String langCode, String contryCode) throws UnsupportedFlavorException, IOException  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String fromDateUI = getClipboardContents(fromDate);
		if (fromDateUI.equals("07/16/2018")) { //BaseTest_CF.campStartDate
			Assert.assertEquals(fromDateUI, "07/16/2018");
		}
		else {
			Assert.fail();
		}
	}
	
	/**
	 * In this method we are verifying number of reviews in Average Star Rating with Number of Reveiws of Table data available
	 * @throws InterruptedException */
	public void VerifyNumOfReviews() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		List < WebElement > rows_table = reviewTableRow.findElements(By.tagName("tr"));
		String numReviews_String=numReviewSubmitted_AverageSR.getText();
		numOfReviews = Integer.parseInt(numReviews_String);
		int count = rows_table.size(); 
		if(paginationNext.isEnabled() & paginationNext.isDisplayed()) {
			for(int i=1;i<=pagination.size()-2;i++) {
				if(paginationNext.isEnabled()) {
					paginationNext.click();
					Thread.sleep(4000);
					count = count + reviewTableRow.findElements(By.tagName("tr")).size();
					break;
				}
			}
		}
		System.out.println((numOfReviews==count));
		System.out.println(count+" Reviews");
		pagination.get(1).click();
		Thread.sleep(1000);
	}
	
	/**
	 * This method is depends on methods : "VerifyNumOfReviews", "getReviewTableData"
	 * 
	 * @overallSR_WidgetTitle in English :  "Overall Star Ratings"	, @overallSR_WidgetTitle in Deutsch : "Gesamt-Sternebewertungen"
	 * @overallSR_WidgetTitle in es_ES   :	*/
	public void verifyOverallRatingWidget(String overallSR_WidgetTitle) {
		scrollByElement(overviewSection, driver);
		verifyText(overallRatingWidgetTitle, overallSR_WidgetTitle);
		for(int i=0; i<5; i++) {
			String y = overallRatingStars.get(i).getAttribute("style");
			if(y.contains("16")){
				String star1_Per = Long.toString(Math.round((star1_Count/(double)numOfReviews)*100))+"%";
				Assert.assertEquals(star1_Per, overallStarsPercents.get(0).getText());
				System.out.println("1 star % is : "+overallStarsPercents.get(0).getText());				
			}
			else if(y.contains("32")) {
				String star1_Per = Long.toString(Math.round((star2_Count/(double)numOfReviews)*100))+"%";
				Assert.assertEquals(star1_Per, overallStarsPercents.get(1).getText());
				System.out.println("2 star % is : "+overallStarsPercents.get(1).getText());
			}
			else if(y.contains("48")) {
				String star1_Per = Long.toString(Math.round((star3_Count/(double)numOfReviews)*100))+"%";
				Assert.assertEquals(star1_Per, overallStarsPercents.get(2).getText());
				System.out.println("3 star % is : "+overallStarsPercents.get(2).getText());
			}
			else if(y.contains("64")) {
				String star1_Per = Long.toString(Math.round((star4_Count/(double)numOfReviews)*100))+"%";
				Assert.assertEquals(star1_Per, overallStarsPercents.get(3).getText());
				System.out.println("4 star % is : "+overallStarsPercents.get(3).getText());
			}
			else if(y.contains("80")) {
				String star1_Per = Long.toString(Math.round((star5_Count/(double)numOfReviews)*100))+"%";
				Assert.assertEquals(star1_Per, overallStarsPercents.get(4).getText());
				System.out.println("5 star % is : "+overallStarsPercents.get(4).getText());
			}
			int starcounts[] = {star1_Count, star2_Count, star3_Count, star4_Count, star5_Count};
			action.moveToElement(overallSR_BarGraphs.get(i)).perform();
			String[] graphToolTip = overallSR_GraphsToolTip.getText().split(" ");
			System.out.println(Integer.parseInt(graphToolTip[0])+"  "+starcounts[i]);
			System.out.print(Integer.parseInt(graphToolTip[0])==starcounts[i]);
			System.out.print("   ");
			System.out.print(Integer.parseInt(graphToolTip[graphToolTip.length-1])==totalStarCount);
			System.out.println();
		}
	}
	
	public void selectCamp(String campaignName) {
		if(selectCampaign.isDisplayed()) {
			select = new Select(selectCampaign);
			select.selectByVisibleText(campaignName);
		}	
	}
	
	public void clickApplyFilterBTN() throws InterruptedException {
		if(applyFilter.isDisplayed()) {
			applyFilter.click();
			Thread.sleep(3000);
		}
	}
	
	/**
	 * This method is depends on methods : "VerifyNumOfReviews", "getReviewTableData"
	 * 
	 * @avgSR_WidgetTitle in English :  "Average Star Rating"	, @avgSR_WidgetTitle in Deutsch : "Durchschnittliche Sternebewertung"
	 * @avgSR_WidgetTitle in es_ES   :	*/
	public String avgStarRatingData(String avgSR_WidgetTitle) throws InterruptedException {
		//Thread.sleep(6000);
		String avgRating = "";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		scrollByElement(overviewSection, driver);
		verifyText(avgRatingWidgetTitle, avgSR_WidgetTitle);
		System.out.println("AverageStarRatingText.size() : "+AverageStarRatingText.size());
		for(int i=0;i<AverageStarRatingText.size();i++) {
			avgRating = avgRating +" "+ AverageStarRatingText.get(i).getText();
			System.out.println(avgRating);
			if(i==2) {
				System.out.println(AvgRatingReviewsCount.getText()+"   "+totalStarCount);
				Assert.assertEquals(AvgRatingReviewsCount.getText(), Integer.toString(totalStarCount));
			}
			if(i==1) {
				double d = sumOfAllStars/(double)totalStarCount;
				DecimalFormat newFormat = new DecimalFormat("#.#");
				double oneDecimal =  Double.valueOf(newFormat.format(d));
				
				System.out.println(AvgRatingScore.getText()+"   "+oneDecimal);
				Assert.assertEquals(AvgRatingScore.getText(), Double.toString(oneDecimal));
			}
		}
		System.out.println("After for loop : "+avgRating);
		
		return avgRating;
	}
	
	public void clickExportBTN() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		if(exportBTN.isEnabled() & exportBTN.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportBTN));
			//exportBTN.click();
			action.moveToElement(exportBTN).click(exportBTN).perform();
			Thread.sleep(5000);
			//String filename = getLastModifiedFile("./downloads");
			System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
		}
		else {
			System.out.println("No Data Available in Review Table");
		}
	}
	
	public void verifyOrderOfTableHeader(String[] tableHeaderData, String... xlHeaderValues) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		List < WebElement > Columns_row = tableBodyLocate.findElements(By.tagName("td"));
		int columns_count = Columns_row.size();
		List<WebElement> headerTableRow=ReportTableHeaders.findElements(By.tagName("th"));
		for (int column = 0; column < columns_count & column < tableHeaderData.length; column++) {
    		String headerText = headerTableRow.get(column).getText().trim();
    		if(headerText.length()!=0) {
    			System.out.println(headerText);
    			if(headerText.equals(tableHeaderData[column])) {
    				tableCellValues.add(headerText);    				
    				Assert.assertTrue(true, headerText+" Table Header Data is in Order "+tableHeaderData[column]);
    			}
    			else {
    				Assert.assertTrue(false, headerText+" Table Header Data is NOT in Expected Order "+tableHeaderData[column]);
    			}
    		}
		}
		if(tableCellValues.size()<8 & xlHeaderValues.length>0) {
			tableCellValues.add(2, xlHeaderValues[0]);
			tableCellValues.add(3, xlHeaderValues[1]);
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
				String cellValue = new ExcelTestDataHandler("./downloads/"+newfilename, "Sheet0").getCellValue(i, j).trim();
				String uiTableCellValue = tableCellValues.get(count).trim();
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
	
	public void getReviewTableData() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		WebElement reviewText = driver.findElement(By.xpath("//*[@class='col-sm-12']/h3"));
		scrollByElement(reviewText, driver);
		
		//To locate rows of table. 
    	List < WebElement > rows_table = reviewTableRow.findElements(By.tagName("tr"));
    	//To calculate no of rows In table.
    	int rows_count = rows_table.size();
    	//Loop will execute till the last row of table.
    	System.out.println("pagination.size() : "+pagination.size());
	    if(paginationNext.isDisplayed()) {
		for(int i=1;i<=pagination.size()-2;i++) {
    	
    	for (int row = 0; row < rows_count; row++) {    		
    		//To locate columns(cells) of that specific row.
    	    List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
    	    //To calculate no of columns (cells). In that specific row.
    	    int columns_count = Columns_row.size();
    	    System.out.println("columns_count : "+columns_count);
    	    int noOfRows=row+1;
    	    System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
    	    //Loop will execute till the last cell of that specific row.
    	    for (int column = 0; column < columns_count; column++) {
    	    	
    	    	List<WebElement> headerTableRow=reviewTableHeader.findElements(By.tagName("th"));
        		String headerText = headerTableRow.get(column).getText();
        		
        		String celtext ="";
        		
        		if(column==6) {
        			
        			//List<WebElement> ab = starRating.findElements(By.tagName("span"));
        			try {
    					String y=allStarRating.get(row).getAttribute("style");
        			
        			if(y.contains("16")){
        				celtext ="1";
        				star1_Sum = star1_Sum + 1;
        				star1_Count++;
        			}
        			else if(y.contains("32")) {
        				celtext ="2";
        				star2_Sum = star2_Sum + 2;
        				star2_Count++;
        			}
        			else if(y.contains("48")) {
        				celtext ="3";
        				star3_Sum = star3_Sum + 3;
        				star3_Count++;
        			}
        			else if(y.contains("64")) {
        				celtext ="4";
        				star4_Sum = star4_Sum + 4;
        				star4_Count++;
        			}
        			else if(y.contains("80")) {
        				celtext ="5";
        				star5_Sum = star5_Sum + 5;
        				star5_Count++;
        			}
        			}
        			catch(Exception e) {
        				
        			}
        		}
        		else {
        			// To retrieve text from that specific cell.
        			celtext = Columns_row.get(column).getText().trim();
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
        		tableCellValues.add(celtext);
    	        System.out.println("Cell Value of row " + noOfRows + " and column " + headerText + " Is : " + celtext);
    	    }
    	    System.out.println("-------------------------------------------------- ");
    	}
    		if(paginationNext.isEnabled()) {
    			paginationNext.click();
    			Thread.sleep(4000);
    			break;
    		}
		}
	    }
	    System.out.println("star1_Count : "+star1_Count +"   star2_Count : "+star2_Count+"   star3_Count : "+star3_Count+
	    		"   star4_Count : "+star4_Count+"   star5_Count : "+star5_Count);
	    System.out.println("star1_Sum : "+star1_Sum +"   star2_Sum : "+star2_Sum+"   star3_Sum : "+star3_Sum+
	    		"   star4_Sum : "+star4_Sum+"   star5_Sum : "+star5_Sum);
	    sumOfAllStars = star1_Sum + star2_Sum + star3_Sum + star4_Sum + star5_Sum;
	    totalStarCount = star1_Count + star2_Count + star3_Count + star4_Count + star5_Count;
	    System.out.println("sumOfAllStars : "+ sumOfAllStars);
	    System.out.println("totalStarCount : "+totalStarCount);
	}
	
}
