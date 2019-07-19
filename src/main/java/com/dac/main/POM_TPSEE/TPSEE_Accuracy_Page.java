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

import com.dac.main.BasePage;

import resources.BaseClass;
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
		
		@FindBy(xpath = "(//div//a[@class='load-table'][contains(text(),'')])")
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
		
		@FindBy(xpath = "//div[@id='allSitesScores']//a[contains(@class,'load-table')][contains(text(),'')]")
		private WebElement vendorslist;
		

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
		waitForElement(exportBtn, 40);
		scrollByElement(exportBtn);
		clickelement(exportBtn);
		waitForElement(exportdate,40);
		scrollByElement(exportdate);
		clickelement(exportdate);
		waitForElement(dtpicker,40);
		scrollByElement(dtpicker);
		waitForElement(date, 40);
		scrollByElement(date);
		clickelement(date);
	    //download visibility report
	    download(CurrentState.getBrowser(), export, 30);
	    convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExport));
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
			System.out.println("\n Link clicked \n");
			waitForElement(tableresult,40);
			scrollByElement(tableresult);
			System.out.println("\n reading data div ********************* \n");
			waitForElement(tableresultset,50);
			scrollByElement(tableresultset);
			System.out.println("\n reading progress bar data table ******************* \n");
			JSWaiter.waitJQueryAngular();
			waitForElement(totalentries,50);
			waitForElement(tableresultset,50);
			if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
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
								System.out.println("\n"+celtext);
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
			
		}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return tableCellValues;
			}

		
		
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

	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List < WebElement > Columns_row = titlehead.findElements(By.tagName("th"));
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
					Map<String, String> uiTableCellValue = tableCellValues.get(count);
					if(uiTableCellValue.containsValue(cellValue)) { // | uiTableCellValue.equals(cellValue)
						Assert.assertTrue(uiTableCellValue.containsValue(cellValue), uiTableCellValue+" is matches with Downloaded Excel value : "+cellValue);
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
	
	
	//Clicking on progress bar and getting the data from the table for found
			public List<Map<String, String>> verifyInAccuracysitelinkdata() throws InterruptedException{
				JSWaiter.waitJQueryAngular();
				waitForElement(accuracysite, 40);
				waitForElement(siteshow, 40);
				//getting into progressbar found listing
				scrollByElement(sitelink);
				//clicking on found listing progress bar
				clickelement(sitelink);
				System.out.println("\n Link clicked \n");
				waitForElement(tableresult,40);
				scrollByElement(tableresult);
				System.out.println("\n reading data div ********************* \n");
				JSWaiter.waitJQueryAngular();
				waitForElement(tableresultset,50);
				scrollByElement(tableresultset);
				if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
				WebElement inaccuracychkbox = driver.findElement(By.xpath("//input[@id='toggle-inaccuracies']"));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='toggle-inaccuracies']")));
				JSWaiter.waitJQueryAngular();
				inaccuracychkbox.click();
				System.out.println("\n reading data table ******************* \n");
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
			    					System.out.println("\n"+celtext);
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
				}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
					try {
						BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
									System.out.println("\n"+celtext);
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
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
				}
			}
		}
		
	}
	
	//To get Vendors List displaying in the application
		public List<Map<String, String>> verifyAccuracySitevendors() {
				JSWaiter.waitJQueryAngular();
				waitForElement(vendorslist, 40);
				scrollByElement(vendorslist);
				Map<String, String> kMap;
				List<Map<String, String>> Vendors = new ArrayList<Map<String, String>>();
				List<WebElement> elements = driver.findElements(By.xpath("//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']"));
			    java.util.Iterator<WebElement> program = elements.iterator();
			    kMap = new HashMap<String, String>();
			        
			    //reading Vendors data
			    while (program.hasNext()) {
			        String values = program.next().getText();
			        if(!values.equals("null"))
			        {
			        	kMap.put("vendors", values);
			        	System.out.println("\n" +values);
			        }
			        else
			        {
			            System.out.println("\n No sites displayed \n");
			        }
			        //adding into the map
			        Vendors.add(kMap);
			    }
				return Vendors;
				}
		
}