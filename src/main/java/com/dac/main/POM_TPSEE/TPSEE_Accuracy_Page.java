package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_Accuracy_Page extends TPSEE_abstractMethods{
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	
	String xpathCompetitors = "//*[@id='divBars']";
	String NoofLocation = "//*[@id='divNumOfLocations']";
	String overallscore = "//*[@id='divOverallScoreValue']";
	
	@FindBy(xpath = "//*[@id='allSitesScores']")
	private List<WebElement> Site;
	
	//Exporting into csv 
		@FindBy(xpath = "//button[@id='btnLocationExportPopUp']")
		private WebElement exportBtn;
		
		@FindBy(xpath = "//*[@id='exportdate']")
		private WebElement exportdate;
		
		@FindBy(id = "ui-datepicker-div")
		private WebElement dtpicker;
		
		@FindBy(css = "td.ui-datepicker-days-cell-over")
		private WebElement date;
		
		@FindBy(xpath = "//*[@id='btnLocationExport']")
		private WebElement export;
		
		//@FindBy(xpath = "//div[contains(@class,'row scores-row active')]//a[contains(@class,'load-table')][contains(text(),'')]")
		@FindBy(xpath = "(//div//a[@class='load-table'][contains(text(),'')])[2]")
		private WebElement sitelink;
		
		@FindBy(xpath = "//a[@id='ToolTables_inaccuracy_results_0']")
		private WebElement tablebtn;
		
		@FindBy(xpath = "//*[@id='inaccuracy_table_title']")
		private WebElement tabletitle;
		
		@FindBy(xpath = "//table[@id='inaccuracy_results']")
		private WebElement tableresult;
		
		@FindBy(xpath = "//table[@id='inaccuracy_results']/tbody")
		private WebElement tableresultset;
		
		@FindBy(xpath = "//thead//tr[@role='row']")
		private WebElement tablehead;
		
		@FindBy(xpath = "//div[@id='allSitesScores']//a[contains(@class,'load-table')][contains(text(),'')]")
		private WebElement sitenames;
		
		@FindBy(xpath = "//div[contains(@class,'row scores-row')]")
		private WebElement siteshow;
		
		@FindBy(xpath = "//*[@id='inaccuracy_results']/tbody/tr")
		private List<WebElement> reviewTableRow;
		
		@FindBy(xpath = "//*[@id='inaccuracy_table']/div[2]")
		private WebElement titlehead;
		
		@FindBy(xpath = "//input[@id='toggle-inaccuracies']")
		private WebElement InAccuracychkBox;
		
		@FindBy(xpath = "//input[@id='toggle_overridden']")
		private WebElement IgnoredchkBox;
		
		@FindBy(xpath = "//*[@id='inaccuracy_results_info']")
		private WebElement totalentries;
		

		/*-------------------------Pagination-----------------------*/
		@FindBy(xpath = "(//*[@class='pagination']//a)")
		private List<WebElement> pagination;
		
		@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
		private WebElement paginationPrev;
		
		@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
		private WebElement paginationNext;
		
		@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
		private List<WebElement> paginationLast;
		/*-------------------------Pagination-----------------------*/
		
		public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	
	public TPSEE_Accuracy_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);

	}
	
	//tooltipvalue in the graph
	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip;
	
	//section of overall report
		@FindBy(xpath = "//*[@id='divBars']")
		private WebElement overall;
		
		@FindBy(xpath = "//*[@id='divBars']")
		private List<WebElement> comp;

	public void verify_pageloadCompletely(int timeout) {
		if ( waitForElement(grphtooltip, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}
	
	//get overall accuracy score
	@Override
	public List<Map<String, String>> getOverviewReport() {
	JSWaiter.waitJQueryAngular();
	waitForElement(overall, 40);
	scrollByElement(overall);
	Map<String, String> kMap;
	List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
	for (int i = 1; i <= Site.size(); i++) {
		WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
		kMap = new HashMap<String, String>();
		kMap.put("NoofLocation", s.findElement(By.xpath(NoofLocation)).getText());
		kMap.put("score", s.findElement(By.xpath(overallscore)).getText());
		System.out.format("%10s%10s", s.findElement(By.xpath(NoofLocation)).getText(),
				s.findElement(By.xpath(overallscore)).getText());
		ovrwRprtData.add(kMap);
	}
	return ovrwRprtData;
	}

	public void compareExportnTable(List<Map<String, String>> verifyHistoryGraph,
			List<Map<String, String>> verifySitetable) {
		
		
	}
	
	/* Export visibility overview report below filter*/
	public void exportvisibilityrpt() throws InterruptedException, FileNotFoundException, IOException{
		JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-group")));
		scrollByElement(exportBtn);
		if(exportBtn.isEnabled() & exportBtn.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportBtn));
			//exportBTN.click();
			action.moveToElement(exportBtn).click(exportBtn).perform();
			Thread.sleep(5000);
		}
		if(exportdate.isEnabled() & exportdate.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportdate));
			action.moveToElement(exportdate).click(exportdate).perform();
			Thread.sleep(5000);
		}
		if(dtpicker.isEnabled() & dtpicker.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(dtpicker));
			action.moveToElement(dtpicker).click(dtpicker).perform();
			Thread.sleep(5000);
		} 
		if(date.isEnabled() & date.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(date));
			action.moveToElement(date).click(date).perform();
			Thread.sleep(5000);
		}
		if(export.isEnabled() & export.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExport));
			Thread.sleep(5000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
			
		}else {
			System.out.println("No Data Available in Accuracy Page");
		}
	}
	
	//printing visibility report data from downloaded excel sheet 
		public List<Map<String, String>> getExportData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exportvisibilityrpt();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExport), "Sheet0").getExcelTable();
			List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			for (int col = 1; col < colSize; col++) {
				//adding data into map
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("NoofLocation", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				exportData.add(kMap);
			}
			//returning visibility report from excel
			return exportData;

		}
		
		
		//Clicking on progress bar and getting the data from the table for found

		public List<Map<String, String>> verifysitelinkdata() throws InterruptedException{
			JSWaiter.waitJQueryAngular();
			waitForElement(accuracysite, 40);
			waitForElement(siteshow, 40);
			//getting into progressbar found listing
			scrollByElement(sitelink);
			//clicking on found listing progress bar
			clickelement(sitelink);
			System.out.println("\n progress bar clicked \n");
			waitForElement(tableresult,40);
			scrollByElement(tableresult);
			System.out.println("\n reading progress bar data div ********************* \n");
			waitForElement(tableresultset,50);
			scrollByElement(tableresultset);
			System.out.println("\n reading progress bar data table ******************* \n");
			JSWaiter.waitJQueryAngular();
			waitForElement(totalentries,50);
			waitForElement(tableresultset,50);
			String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			int page = Integer.parseInt(n);
			System.out.println("\n"+page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
			String entiresText = driver.findElement(By.className("dataTables_info")).getText();
			entiresText = entiresText.substring(entiresText.indexOf("("));
			WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
			scrollByElement(TableTitle);
			int count = 0;
			if(paginationNext.isDisplayed()) {
				for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
					scrollByElement(TableTitle);
					List < WebElement > rows_table = reviewTableRow;	//To locate rows of table. 
					int rows_count = rows_table.size();		//To calculate no of rows In table.
					count = count + rows_count;
					Map<String, String> kMap = new HashMap<String, String>();
					for (int row = 0; row < rows_count; row++) { 
						List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
						int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
						int noOfRows=row+1;
						//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
						for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
							List<WebElement> headerTableRow=titlehead.findElements(By.tagName("th"));
							String headerText = headerTableRow.get(column).getText(), celtext ="";
							if(column==1 & row < rows_count) {
								celtext = driver.findElement(By.xpath("(//*[@id='inaccuracy_results']/tbody/tr)["+ (row+1) +"]")).getText();
								System.out.println("/n"+celtext);
							}
							kMap.put("rowdata", celtext);
							tableCellValues.add(kMap);
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
			return tableCellValues;
		}

		
		
		/*public  List<Map<String, String>> numberofentries() {
			
			waitForElement(tableresult,50);
			List<Map<String, String>> totalentries = new ArrayList<Map<String, String>>();
			//getting the text at the bottom of the table and split the string by space
			List<WebElement> col = driver.findElements(By.xpath("//table[@id='inaccuracy_results']/tbody"));
			System.out.println(col.size());
			rows = tableresultset.findElements(By.tagName("tr"));
			waitForElement(tableresultset,50);
			int count = rows.size();
			System.out.println("\n Total number of rows : "+count);
			return totalentries;
			}*/
		
		
		public void exporttable() throws FileNotFoundException, IOException, InterruptedException {
			
			waitForElement(tableresult, 40);
			waitForElement(tablebtn, 40);
			//scrollByElement(tablebtn);
			download(CurrentState.getBrowser(), tablebtn, 30);
			JSWaiter.waitUntilJQueryReady();
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttable));
			
		}
		
	//printing visibility progress bar table data from downloaded excel sheet
	public List<Map<String, String>> getExporttableData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttable();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttable), "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			for (int col = 1; col < colSize; col++) {
				//adding data into map
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("rowdata", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				exporttableData.add(kMap);
			}
			return exporttableData;
		}

	public void compareexporttableDatantable(List<Map<String, String>> exporttableData,
			List<Map<String, String>> verifysitelinkdata) {
		for (Map<String, String> m1 : exporttableData) {
			for (Map<String, String> m2 : verifysitelinkdata) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
				}
			}
		}
		
	}
	
	
	//Clicking on progress bar and getting the data from the table for found
			public List<Map<String, String>> verifyInAccuracysitelinkdata() throws InterruptedException{
				JSWaiter.waitJQueryAngular();
				waitForElement(accuracysite, 40);
				waitForElement(siteshow, 40);
				//getting into progressbar found listing
				scrollByElement(sitelink);
				//clicking on found listing progress bar
				clickelement(sitelink);
				System.out.println("\n progress bar clicked \n");
				waitForElement(tableresult,40);
				scrollByElement(tableresult);
				System.out.println("\n reading progress bar data div ********************* \n");
				JSWaiter.waitJQueryAngular();
				waitForElement(tableresultset,50);
				scrollByElement(tableresultset);
				WebElement inaccuracychkbox = driver.findElement(By.xpath("//input[@id='toggle-inaccuracies']"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='toggle-inaccuracies']")));
				JSWaiter.waitJQueryAngular();
				inaccuracychkbox.click();
				System.out.println("\n reading progress bar data table ******************* \n");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='inaccuracy_results_info']")));
				JSWaiter.waitJQueryAngular();
				waitForElement(tableresultset,50);
				String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			//	driver.findElement(By.xpath("(//*[@class='pagination']//a)[2]")).click();
				int page = Integer.parseInt(n);
				System.out.println("\n"+page);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
				String entiresText = driver.findElement(By.className("dataTables_info")).getText();
				entiresText = entiresText.substring(entiresText.indexOf("("));
				WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
				scrollByElement(TableTitle);
				int count = 0;
			    if(paginationNext.isDisplayed()) {
			    	for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
			    		scrollByElement(TableTitle);
			    		List < WebElement > rows_table = reviewTableRow;	//To locate rows of table. 
			    		int rows_count = rows_table.size();		//To calculate no of rows In table.
			    		count = count + rows_count;
			    		Map<String, String> kMap = new HashMap<String, String>();
			    		for (int row = 0; row < rows_count; row++) { 
			    			List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
			    			int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
			    			int noOfRows=row+1;
			    			//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
			    			for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
			    				List<WebElement> headerTableRow=titlehead.findElements(By.tagName("th"));
			    				String headerText = headerTableRow.get(column).getText(), celtext ="";
			    				if(column==1 & row < rows_count) {
			    					celtext = driver.findElement(By.xpath("(//*[@id='inaccuracy_results']/tbody/tr)["+ (row+1) +"]")).getText();
			    					System.out.println("/n"+celtext);
			    			}
			    				kMap.put("rowdata", celtext);
			    				tableCellValues.add(kMap);
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
			    return tableCellValues;
				
			}
			
			
		public void exporttableInAccuracy() throws FileNotFoundException, IOException, InterruptedException {
				
				waitForElement(tableresult, 40);
				waitForElement(tablebtn, 40);
				//scrollByElement(tablebtn);
				download(CurrentState.getBrowser(), tablebtn, 30);
				JSWaiter.waitUntilJQueryReady();
				convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableInAccuracy));
				
			}
			
		//printing visibility progress bar table data from downloaded excel sheet
		public List<Map<String, String>> getInAccuracyExporttableData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttableInAccuracy();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttableInAccuracy), "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			for (int col = 1; col < colSize; col++) {
				//adding data into map
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("compName", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				exporttableData.add(kMap);
			}
			return exporttableData;
		}

		public void compareexporttableDatanInAccuracytable(List<Map<String, String>> getInAccuracyExporttableData,
				List<Map<String, String>> verifyInAccuracysitelinkdata) {
			for (Map<String, String> m1 : getInAccuracyExporttableData) {
				for (Map<String, String> m2 : verifyInAccuracysitelinkdata) {
					if (m1.get("rowdata").equals(m2.get("rowdata"))) {
						/*Assert.assertEquals(m1.get("Displayed Name"), m2.get("Displayed Name"), "verifying list for " +m2.get("Confirmed Info"));
						Assert.assertEquals(m1.get("Displayed Address"), m2.get("Displayed Address"), "verifying list for " +m2.get("Confirmed Info"));
						Assert.assertEquals(m1.get("Displayed Phone Number"), m2.get("Displayed Phone Number"), "verifying list for " +m2.get("Confirmed Info"));*/
						Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
					}
				}
			}
			
		}
		
		//Clicking on progress bar and getting the data from the table for found

		public List<Map<String, String>> verifyIgnoredsitelinkdata() throws InterruptedException{
			JSWaiter.waitJQueryAngular();
			waitForElement(accuracysite, 40);
			waitForElement(siteshow, 40);
			//getting into progressbar found listing
			scrollByElement(sitelink);
			//clicking on found listing progress bar
			clickelement(sitelink);
			System.out.println("\n progress bar clicked \n");
			waitForElement(tableresult,40);
			scrollByElement(tableresult);
			System.out.println("\n reading progress bar data div ********************* \n");
			waitForElement(tableresultset,50);
			scrollByElement(tableresultset);
			WebElement ignoredchkbox = driver.findElement(By.xpath("//input[@id='toggle_overridden']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='toggle_overridden']")));
			JSWaiter.waitJQueryAngular();
			ignoredchkbox.click();
			System.out.println("\n reading progress bar data table ******************* \n");
			waitForElement(totalentries,50);
			JSWaiter.waitJQueryAngular();
			waitForElement(tableresultset,50);
			String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			if( n instanceof String ) {
				System.out.println("\n No data avilable for the selection \n");
			}else {
			int page = Integer.parseInt(n);
			System.out.println("\n"+page);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
			String entiresText = driver.findElement(By.className("dataTables_info")).getText();
			entiresText = entiresText.substring(entiresText.indexOf("("));
			WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
			scrollByElement(TableTitle);
			int count = 0;
				if(paginationNext.isDisplayed()) {
					for(int i=1;i<=page;i++) {	//Loop will execute till the all the row of table completes.
						scrollByElement(TableTitle);
						List < WebElement > rows_table = reviewTableRow;	//To locate rows of table. 
						int rows_count = rows_table.size();		//To calculate no of rows In table.
						count = count + rows_count;
						Map<String, String> kMap = new HashMap<String, String>();
						for (int row = 0; row < rows_count; row++) { 
							List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));	//To locate columns(cells) of that specific row.
							int columns_count = Columns_row.size();		//To calculate no of columns (cells). In that specific row.
							int noOfRows=row+1;
							//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
							for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
								List<WebElement> headerTableRow=titlehead.findElements(By.tagName("th"));
								String headerText = headerTableRow.get(column).getText(), celtext ="";
								if(column==1 & row < rows_count) {
									celtext = driver.findElement(By.xpath("(//*[@id='inaccuracy_results']/tbody/tr)["+ (row+1) +"]")).getText();
									System.out.println("/n"+celtext);
								}
							
								kMap.put("rowdata", celtext);
								tableCellValues.add(kMap);
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
			return tableCellValues;
		}
			

		public void exporttableIgnored() throws FileNotFoundException, IOException, InterruptedException {
			WebElement Ignoredchkbox = driver.findElement(By.xpath("//input[@id='toggle_overridden']"));
			Ignoredchkbox.click();
			waitForElement(tableresult, 40);
			waitForElement(tablebtn, 40);
			//scrollByElement(tablebtn);
			JSWaiter.waitUntilJQueryReady();
			download(CurrentState.getBrowser(), tablebtn, 30);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableIgnored));
			}
		
		//printing visibility progress bar table data from downloaded excel sheet
		public List<Map<String, String>> getIgnoredExporttableData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttableIgnored();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttableIgnored), "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			for (int col = 1; col < colSize; col++) {
				//adding data into map
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("rowdata", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
			}
			return exporttableData;
		}

	public void compareexporttableDatanIgnoredtable(List<Map<String, String>> getIgnoredExporttableData,
			List<Map<String, String>> verifyIgnoredsitelinkdata) {
		for (Map<String, String> m1 : getIgnoredExporttableData) {
			for (Map<String, String> m2 : verifyIgnoredsitelinkdata) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					/*Assert.assertEquals(m1.get("Displayed Name"), m2.get("Displayed Name"), "verifying list for " +m2.get("Confirmed Info"));
					Assert.assertEquals(m1.get("Displayed Address"), m2.get("Displayed Address"), "verifying list for " +m2.get("Confirmed Info"));
					Assert.assertEquals(m1.get("Displayed Phone Number"), m2.get("Displayed Phone Number"), "verifying list for " +m2.get("Confirmed Info"));*/
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
				}
			}
		}
		
	}
		
}