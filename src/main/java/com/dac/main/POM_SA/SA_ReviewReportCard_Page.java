package com.dac.main.POM_SA;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.dac.main.BasePage;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class SA_ReviewReportCard_Page extends BasePage{

	
	public static ArrayList<String> tableCellValues = new ArrayList<String>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public SA_ReviewReportCard_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="btnExport")
	private WebElement exportBtn;
	
	// overview report
	@FindBy(xpath = "//div[contains(@class,'date-selection')]")
	private WebElement timePeriodBTN;
	
	@FindBy(xpath = "//span[@id='spanYourScore']")
	private WebElement RRC_Score;  // displays in percentage need to convert
	
	@FindBy(xpath = "//span[@id='spanYourGrade']")
	private WebElement RRC_Grade;
	
	@FindBy(xpath = "//*[local-name() = 'svg']/*[name()='g'][7]")
	private WebElement RRC_barGraph;
	
	// site table
	@FindBy(xpath= "//*[@id='locationTable']/tbody")
	private WebElement RRC_Table;
	
	@FindBy(xpath="(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath="//*[@class='pagination']//a")
	private List<WebElement> pagination;
	
	@FindBy(xpath="(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	
	@FindBy(id="locationTable")
	private WebElement ReportTableHeaders;
	
	@FindBy(xpath="//table[@id='locationTable']/thead")
	private WebElement reviewTableHeader;
	
	@FindBy(xpath="//table[@id='locationTable']/tbody")
	private WebElement reviewTableBody;
	
	@FindBy(xpath="//table[@id='locationTable']/tbody/tr")
	private List<WebElement> reviewTableRow;
	
	@FindBy(xpath="//table[@id='locationTable']/tbody/td")
	private List<WebElement> reviewTableData;
	
	// Global Filter locators
	@FindBy(xpath="//*[@id='filter-options']")
	private WebElement filter_Panel;

	@FindBy(id="myGroups")
	private WebElement fiterGroup;
	
	@FindBy(xpath="//*[@class='menu transition visible']")
	private WebElement filterDropDown;
	
	@FindBy(xpath="(//*[contains(@class,'myList')])[1]")
	private WebElement filterCountry;

	@FindBy(xpath="//*[contains(@class,'myList1')]")
	private WebElement filterState;

	@FindBy(xpath="//*[contains(@class,'myList2')]")
	private WebElement filterCity;

	@FindBy(xpath="//*[contains(@class,'myList3')]")
	private WebElement filterlocation;

	@FindBy(css = "button#apply_filter")
	private WebElement Apply_filter;
	
	@FindBy(id="btnExport")
	private WebElement exportBTN;
	
	
	//-----------------------------------------------------------
	
	@FindBy(xpath="//*[@id='filterBySource']/div")
	private WebElement filterSourceOptions;
	
	@FindBy(xpath="//div[contains(@class,'search sites')]//*[@class='search']")
	private WebElement filterSourceTB;
	
	@FindBy(xpath="//*[@class='delete icon']")
	private List<WebElement> filterSourceClose;
	
	@FindBy(xpath="//*[@id='page-content']/h1")
	private WebElement RRCPageTitle;
	
	/**
	 *This method is used to get the score from the RRC Graph
	 * @return toolTipScore			*/
	private String RRCScore() {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		scrollByElement(RRCPageTitle);
		String toolTipScore = "";
		String scoreFromToolTip = "//*[contains(@class,'highcharts-label')]/*[local-name() = 'text']/*[local-name() = 'tspan'][2]";
		if(RRC_barGraph.isDisplayed()) {
			action.moveToElement(RRC_barGraph).perform();
			WebElement tooltip = driver.findElement(By.xpath(scoreFromToolTip));
			wait.until(ExpectedConditions.visibilityOf(tooltip));
			try {
				BaseClass.addEvidence(CurrentState.getDriver(), "graph's tool tip score", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			toolTipScore = tooltip.getText().replace(" %", "");
		}
		return toolTipScore;
	}
	
	/**
	 *This method is used to check whether data is there in table or not based on the applied criteria
	 * @return true : if data is there otherwise return false			*/
	public boolean isDataAvailable() throws Exception {
		JSWaiter.waitJQueryAngular();
		scrollByElement(filterSourceTB);
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
			return true;
		}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			return false;
		}
		waitForElement(filterSourceTB, 20);
		return false;
	}
	
	/**
	 * This method is used to select the sites from filterBySource based on the passed parameters to which sites need to select
	 * @param forOptions : pass the sites need to select as String[]	*/
	public void filterBySource(String[] forOptions) throws Exception {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		scrollByElement(RRCPageTitle);
		WebElement locationsText = driver.findElement(By.xpath("//*[@class='col-lg-7 col-xs-7']/h3"));
		scrollByElement(locationsText);
		filterSourceClose.stream().forEachOrdered(item -> item.click());
		for(String sites : forOptions) {
			clickelement(filterSourceTB);
			waitForElement(filterDropDown, 20);
			Thread.sleep(1000);
			WebElement source = driver.findElement(By.xpath("//*[@id='filterBySource']/div[text()='"+sites+"']"));
			clickelement(source);
			waitUntilLoad(driver);
		}
		BaseClass.addEvidence(driver, "selected the sites in FilterBySource field", "yes");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
	}
	
	/**
	 * This method is used to check whether expected score and grade is displaying in the UI
	 * @param withCalRRCScore : pass the expected RRCScore
	 * @param calRRCGrade : pass the expected RRCGrade 		*/
	public void isRRCScoreCorrect(String withCalRRCScore, String calRRCGrade) {
		JSWaiter.waitJQueryAngular();
		String rrcScore = RRC_Score.getText().replaceAll("%", "");
		scrollByElement(RRCPageTitle);
		Assert.assertEquals(RRCScore(), rrcScore);
		//System.out.println("rrcScore: "+rrcScore);
		Assert.assertEquals(rrcScore, withCalRRCScore);
		verifyText(RRC_Grade, calRRCGrade);
	}
	
	/**
	 * This method is used to select the time period from TimePeriod drop down of RRC based on passed parameter
	 * @param ofMonth : for which month need to check the data (ex: March ) 
	 * @return month value */
	public String selectTimePeriod(String ofMonth) throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		scrollByElement(timePeriodBTN);
		clickelement(timePeriodBTN);
		waitForElement(filterDropDown, 20);
		driver.findElement(By.xpath("//div[contains(@class,'date-selection')]/*[@class='search']")).clear();
		Thread.sleep(1000);
		String LofMonth = ofMonth.toLowerCase();
		LofMonth = LofMonth.replace(LofMonth.charAt(0), ofMonth.toUpperCase().charAt(0));
		WebElement monthLB = driver.findElement(By.xpath("//*[@class='menu transition visible']/div[contains(text(),'"+LofMonth+"')]"));
		String value = monthLB.getAttribute("data-value");
		clickelement(monthLB);
		Thread.sleep(3000);
		//waitUntilLoad(driver);
		return value;
	}	
	
	/**
	 * @param CountryCode : pass the Country code which you want to apply filter (ex: CA or US etc..) or else pass null
	 * @param State : pass the State name of the selected country 
	 * 				  make sure State name exactly matches with State names available in respective drop down (ex: British Columbia). else pass null
	 * @param City : pass the State name of the selected country -> state
	 * 				 make sure City name exactly matches with City names available in respective drop down (ex: Pitt Meadows). else pass null
	 * @param Location : pass the Location name of the selected country -> state -> City
	 * 				 make sure location name exactly matches with Location names available in respective drop down (ex: Fit4Less, 19800 Lougheed Hwy, m9l 9j8, +1 613-528-9635). else pass null
	 *           
	 *            for Global filtering reports			*/
	public void applyGlobalFilter(String Group, String CountryCode, String State, String City, String Location) {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		WebElement country,state,city,location,group;
		if(Group == null || Group.equalsIgnoreCase("none")) Group = "None";
		if (CountryCode == null || CountryCode.equalsIgnoreCase("null")) CountryCode = "All Countries";
		if (CountryCode == null || State == null || State.equalsIgnoreCase("null")) State = "All States";
		if (CountryCode == null || State == null || City == null || City.equalsIgnoreCase("null")) City = "All Cities"; 
		if (CountryCode == null || State == null || City == null | Location == null || Location.equalsIgnoreCase("null")) Location = "All Locations";
		try {
			waitForElement(filter_Panel, 25);
			scrollByElement(RRCPageTitle);
			waitUntilLoad(driver);
			if(!Group.equals("None")) {			
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 20);
				group = filterState.findElement(By.xpath("//div[@data-value='"+Group+"']"));
				waitForElement(group, 10);
				clickelement(group);
				waitUntilLoad(driver);
			}
			if(!CountryCode.equals("All Countries")) {
				clickelement(filterCountry);
				waitForElement(filterDropDown, 20);
				country = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[1]//div[@data-value='"+CountryCode.toUpperCase()+"']"));
				waitForElement(country, 10);
				Thread.sleep(1000);
				clickelement(country);
				waitUntilLoad(driver);
			}
			if(!State.equals("All States")) {			
				clickelement(filterState);
				waitForElement(filterDropDown, 20);
				state = filterState.findElement(By.xpath("//div[@data-value='"+State+"']"));
				waitForElement(state, 10);
				Thread.sleep(1000);
				clickelement(state);
				waitUntilLoad(driver);
			}
			if(!City.equals("All Cities")) {
				clickelement(filterCity);
				waitForElement(filterDropDown, 20);
				city = filterCity.findElement(By.xpath("//div[@data-value='"+City+"']"));
				waitForElement(city, 10);
				Thread.sleep(1000);
				clickelement(city);
				waitUntilLoad(driver);
			}
			if(!Location.equals("All Locations")) {			
				clickelement(filterlocation);
				waitForElement(filterDropDown, 20);
				location = filterlocation.findElement(By.xpath("//div[text()='"+Location+"']"));
				waitForElement(location, 10);
				Thread.sleep(1000);
				clickelement(location);
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
	}
	
	/**
	 * This method used to click on the Apply Filter button		*/
	public void clickApplyFilterBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if(Apply_filter.isDisplayed()) {
			clickelement(Apply_filter);
			Thread.sleep(3000);
		}
	}
	
	/** 
	 * This method is used to click on Export the excel in RRC page		*/
	public void clickExportBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		scrollByElement(exportBTN);
		if(exportBTN.isEnabled() & exportBTN.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportBTN));
			//exportBTN.click();
			action.moveToElement(exportBTN).click(exportBTN).perform();
			Thread.sleep(5000);
			//System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
		}else {
			System.out.println("No Data Available in Review Table");
		}
	}
	
	/**
	 * This method is used to get the RRC table UI data and store in as List based on the applied filter	
	 * And checks whether table data count is matches with the number of entries in the table	 */
	public void getReviewTableData() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String entiresText = driver.findElement(By.className("dataTables_info")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		WebElement locationsText = driver.findElement(By.xpath("//*[@class='col-lg-7 col-xs-7']/h3"));
		scrollByElement(locationsText);
		int count = 0;
	    if(paginationNext.isDisplayed()) {
	    	for(int i=1;i<=pagination.size()-2;i++) {	//Loop will execute till the all the row of table completes.
	    		scrollByElement(locationsText);
	    		List < WebElement > rows_table = reviewTableRow;	//To locate rows of table. 
	    		int rows_count = rows_table.size();		//To calculate no of rows In table.
	    		count = count + rows_count;
	    		for (int row = 0; row < rows_count; row++) { 
	    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
	    			int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
	    			int noOfRows=row+1;
	    			//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
	    			for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
	    				List<WebElement> headerTableRow=reviewTableHeader.findElements(By.tagName("th"));
	    				String headerText = headerTableRow.get(column).getText(), celtext ="";
	    				if(column==1 & row < rows_count) {
	    					celtext = driver.findElement(By.xpath("(//td[contains(@class,'locationIdRedirect')]/a)["+ (row+1) +"]")).getText();
	    				}else if(column == columns_count-2) {
	    					celtext = new String(""+Double.parseDouble(Columns_row.get(column).getText().trim().replace("%", ""))+"%"); // need change the grade fromat (ex: 89% to 89.00%) so that easy to compare with xl
	    				}else {
	    					celtext = Columns_row.get(column).getText().trim();	// To retrieve text from that specific cell.
	    				}
	    				tableCellValues.add(celtext);
	    				//System.out.println("Cell Value of row " + noOfRows + " and column " + headerText + " Is : " + celtext);
	    			}
	    			//System.out.println("-------------------------------------------------- ");
	    		}
	    		if(paginationNext.isEnabled()) {
	    			scrollByElement(paginationNext);
	    			paginationNext.click();
	    			Thread.sleep(4000);
	    		}
	    	}
	    }
	    System.out.println("Total number of entries in table : "+count);
	    Assert.assertTrue(entiresText.contains(""+count+""), "Table Data count matches with total enties count");
	}
	
	/**
	 * This method is used to verify the Exported table data with the stored table UI data using <getReviewTableData> method
	 * and method can use after the immediate implementation if <getReviewTableData> method of this POM class otherwise fails the execution 	*/
	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List < WebElement > Columns_row = ReportTableHeaders.findElements(By.tagName("th"));
		int col_count = Columns_row.size();
		String newfilename = BasePage.getLastModifiedFile("./downloads");
		//String newfilename = new formatConvert("./downloads/"+fileName).convertFile("xlsx");
		ExcelHandler a = new ExcelHandler("./downloads/"+newfilename, "Sheet0"); a.deleteEmptyRows();
		int xlRowCount=new ExcelHandler("./downloads/"+newfilename, "Sheet0").getRowCount();
		int count = 0;
		for(int i=1;i<xlRowCount;i++) {
			col_count = a.getColCount(i);
			for(int j=0;j<=col_count;j++) {
				String cellValue = a.getCellValue(i, j+1).trim();
				if(cellValue.contains("%")) cellValue = new String(""+Double.parseDouble(cellValue.replace("%", ""))+"%");
				if(cellValue.length() != 0 & cellValue != null) {
					String uiTableCellValue = tableCellValues.get(count).trim();
					if(uiTableCellValue.contains(cellValue)) { // | uiTableCellValue.equals(cellValue)
						Assert.assertTrue(uiTableCellValue.contains(cellValue), uiTableCellValue+" is matches with Downloaded Excel value : "+cellValue);
					}else {
						Assert.assertTrue(false, uiTableCellValue+" is NOT matches with Downloaded Excel value : "+cellValue);
					}
					
					if(j <1 | j >5) count++;
				}
			}
		}
		CurrentState.getLogger().info("UI table data matches with Exported Excel Data");
		Assert.assertTrue(true, "UI table data matches with Exported Excel Data");
		tableCellValues.clear();
	}
}
