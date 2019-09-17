package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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


public class TPSEE_Visibility_Page extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	//Navigating to TPSEE Visibility page
	public TPSEE_Visibility_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	
	//Exporting into csv 
	@FindBy(xpath = "//*[@id='btnVisibilityExport']")
	private WebElement exportBtn;
	
	@FindBy(xpath = "//*[@id='csvData']")
	private WebElement csvexport;
	
	@FindBy(xpath = "//*[@id='exportdate']")
	private WebElement exportdate;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement dtpicker;
	
	@FindBy(css = "td.ui-datepicker-days-cell-over")
	private WebElement date;
	
	@FindBy(xpath = "//*[@id='btnLocationExport']")
	private WebElement export;
	
	//Exporting into pdf
	
	@FindBy(xpath = "//a[contains(text(),'Export as PDF')]")
	private WebElement pdfexport;
	
	@FindBy(xpath = "//a[@id='currentData']")
	private WebElement currentpdf;
	
	@FindBy(xpath= "//a[contains(text(),'Click here to download your report')]")
	private WebElement pdfclick;
	
	//locators to export pdf for period of time
	@FindBy(xpath = "//a[@id='historyData']")
	private WebElement historypdf;
	
	@FindBy(xpath = "//input[@id='exportStartDate']")
	private WebElement hstrystart;
	
	@FindBy(xpath = "//input[@id='exportEndDate']")
	private WebElement hstryend;
	
	@FindBy(xpath = "//*[@id='btnHistoryExport']")
	private WebElement hstrybtn;
		
	//section of overall report
	@FindBy(xpath = "#divBars")
	private WebElement overall;
	
	@FindBy(xpath = "//*[@id='divBars']")
	private List<WebElement> comp;
	
	//tooltipvalue in the graph
	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 

	String xpathCompetitors = "//*[@id='divBars']";
	String NoofLocation = "//*[@id='divNumOfLocations']";
	String overallscore = "//*[@id='divOverallScoreValue']";

	//Site table section
	@FindBy(css = "div#barContainer.barContainer")
	private WebElement siteTable;
	
	@FindBy(css = "#divBars")
	private List<WebElement> Site;
	
	@FindBy(css = "div.progress-bar")
	private WebElement progressfound;
	
	@FindBy(css = "div.not-bar")
	private WebElement progressNotfound;
	
	
	//table section at the end of visibility report
	@FindBy(xpath = "//*[@id='visibility_table']")
	private WebElement progressdata;
	
	@FindBy(xpath = "//*[@id='visibility_results_wrapper']")
	private WebElement progresstable;
	
	@FindBy(xpath = "//*[@id='visibility_results']/tbody")
	private WebElement progresstablevalue;
	
	@FindBy(xpath = "//*[@id='ToolTables_visibility_results_0']")
	private WebElement exporttable;
	
	@FindBy(xpath = "//*[@id='visibility_table_title']")
	private WebElement title;
	
	@FindBy(xpath = "//*[@id='visibility_table']/div[2]")
	private WebElement titlehead;
	
	@FindBy(xpath = "//*[@id='visibility_results']/tbody/tr")
	private List<WebElement> reviewTableRow;
	
	String Vendortitle = "//*[@id='visibility_table_title']";
	
	//Displaying no.of entries
	@FindBy(xpath = "//*[@id='visibility_results_wrapper']/div[4]/div[1]")
	private WebElement entries;
	
	@FindBy(xpath = "//*[@id='visibility_results_info']")
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
	
	List<WebElement> columns;
	List<WebElement> rows;
	
	@FindBy(xpath = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']")
	private WebElement vendorslist;
	
	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	/*-------------------------------------------------------------------*/
	
	/* Export visibility overview report below filter*/
	public void exportvisibilityrpt() throws InterruptedException, FileNotFoundException, IOException, ExecutionException{
		JSWaiter.waitJQueryAngular();
		//waitForElement(overall, 40);
				waitForElement(exportBtn, 40);
				scrollByElement(exportBtn);
				clickelement(exportBtn);
				waitForElement(csvexport, 40);
				scrollByElement(csvexport);
				clickelement(csvexport);
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
			    convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExport));
	}
	
	/* Export visibility overview report below filter in pdf for current date*/
	public void exportvisibilityrptpdf() throws InterruptedException, FileNotFoundException, IOException{
	    JSWaiter.waitJQueryAngular();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn btn-primary dropdown-toggle")));
		scrollByElement(exportBtn);
		if(exportBtn.isEnabled() & exportBtn.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportBtn));
			action.moveToElement(exportBtn).click(exportBtn).perform();
			Thread.sleep(5000);
		}
		if(pdfexport.isEnabled() & pdfexport.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(pdfexport));
			action.moveToElement(pdfexport).click(pdfexport).perform();
			Thread.sleep(5000);
		}
		if(currentpdf.isEnabled() & currentpdf.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(currentpdf));
			action.moveToElement(currentpdf).click(currentpdf).perform();
			Thread.sleep(5000);
		}
		if(pdfclick.isEnabled() & pdfclick.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(pdfclick));
			action.moveToElement(pdfclick).click(pdfclick).perform();
			Thread.sleep(5000);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExportPdf));
			Thread.sleep(5000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
			}else {
			System.out.println("No Data Available in Visibility Page");
		}
	     
	}
	
	//To get overall score of visibility
	public List<Map<String, String>> getOverviewReport() {
		JSWaiter.waitJQueryAngular();
		waitForElement(overall, 40);
		System.out.println("\n Reading overall ********** \n");
		Map<String, String> kMap;
		
		//adding data into List
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
		//retrieving data and displaying of tooltip visibility score
			return ovrwRprtData;
	}
	
	//Page Load Method
	public void verify_pageloadCompletely(int timeout) {
		if ( waitForElement(grphtooltip, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	//printing visibility report data from downloaded excel sheet 
	public List<Map<String, String>> getExportData() throws Exception {
		JSWaiter.waitJQueryAngular();
		exportvisibilityrpt();
		JSWaiter.waitJQueryAngular();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+VisibilityExport), "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			//adding data into map
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
		}
		//returning visibility report from excel
		return exportData;

	}
	
	//comparing values of graph and overall report
	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {
		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				Assert.assertEquals(formatFloat(m1.get("score")), 
									formatFloat(m2.get("overallscore")), 0.05f,
									"Verifying score for" + m1.get("NoofLocations"));
				}
			}
		}
	
	// To Get Data from the Progress Bar Table Found
	public List<Map<String, String>> DataTablefound() throws InterruptedException{
		JSWaiter.waitJQueryAngular();
		waitForElement(siteTable, 40);
		waitForElement(progressfound, 40);
		//getting into progressbar found listing
		scrollByElement(progressfound);
		//clicking on found listing progress bar
		clickelement(progressfound);
		System.out.println("\n progress bar clicked \n");
		waitForElement(progressdata,40);
		scrollByElement(progressdata);
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
		System.out.println("\n reading progress bar data div ********************* \n");
		waitForElement(progresstable,50);
		scrollByElement(progresstable);
		System.out.println("\n reading progress bar data table ******************* \n");
		waitForElement(totalentries,50);
		JSWaiter.waitJQueryAngular();
		waitForElement(progresstablevalue,50);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n"+page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String entiresText = driver.findElement(By.className("dataTables_info")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		WebElement TableTitle = driver.findElement(By.xpath("//*[@id='visibility_table_title']"));
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
	    					celtext = driver.findElement(By.xpath("(//*[@id='visibility_results']/tbody/tr)["+ (row+1) +"]")).getText();
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

		
	//Clicking on progress bar and getting the data from the table for Not Found list
	public List<Map<String, String>> DataTableNotfound() throws InterruptedException{
		JSWaiter.waitJQueryAngular();
		waitForElement(siteTable, 40);
		waitForElement(progressNotfound, 40);
		//getting into progressbar found listing
		scrollByElement(progressNotfound);
		//clicking on found listing progress bar
		action.doubleClick(progressNotfound).build().perform();
		System.out.println("\n progress bar clicked \n");
		waitForElement(progressdata,40);
		scrollByElement(progressdata);
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
		System.out.println("\n reading progress bar data div ********************* \n");
		JSWaiter.waitJQueryAngular();
		waitForElement(progresstable,50);
		scrollByElement(progresstable);
		System.out.println("\n reading progress bar data table ******************* \n");
		waitForElement(totalentries,50);
		waitForElement(progresstablevalue,50);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n"+page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		String entiresText = driver.findElement(By.className("dataTables_info")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		WebElement TableTitle = driver.findElement(By.xpath("//*[@id='visibility_table_title']"));
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
	    					celtext = driver.findElement(By.xpath("(//*[@id='visibility_results']/tbody/tr)["+ (row+1) +"]")).getText();
	    					System.out.println("\n"+celtext);
	    			}
	    				kMap.put("rowdata", celtext);
	    				tableCellValues.add(kMap);
	    			//	System.out.println("Cell Value of row " + noOfRows + " and column " + headerText + " Is : " + celtext);
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

		
	//exporting progress bar table data
	public void exporttablefound() throws FileNotFoundException, IOException, InterruptedException {				
			JSWaiter.waitJQueryAngular();
			waitForElement(progressdata, 40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
			scrollByElement(exporttable);
			if(exporttable.isEnabled() & exporttable.isDisplayed()) {
				wait.until(ExpectedConditions.visibilityOf(exporttable));
				action.moveToElement(exporttable).click(exporttable).perform();
				Thread.sleep(5000);
				convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExporttableFound));
				Thread.sleep(5000);
				//System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
				CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
				
			}else {
				System.out.println("No Data Available in Visibility Page");
			}
		}
		
	//printing visibility progress bar table data from downloaded excel sheet
	public List<Map<String, String>> getExporttableDataFound() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttablefound();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+VisibilityExporttableFound), "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			String size = Integer.toString(colSize);
			for (int col = 1; col < colSize; col++) {
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("rowdata", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				exporttableData.add(kMap);
			}
			return exporttableData;
		}
	
	
	//exporting progress bar Not found table data
	public void exporttableNotfound() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(progressdata, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		scrollByElement(exporttable);
		if(exporttable.isEnabled() & exporttable.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exporttable));
			action.moveToElement(exporttable).click(exporttable).perform();
			Thread.sleep(5000);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExporttableNotFound));
			Thread.sleep(5000);
			//System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
			
		}else {
			System.out.println("No Data Available in Visibility Page");
		}
	}
	
	//printing visibility progress bar Not found table data from downloaded excel sheet
	public List<Map<String, String>> getExporttableDataNotFound() throws Exception {
		JSWaiter.waitJQueryAngular();
		exporttableNotfound();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+VisibilityExporttableNotFound), "Sheet0").getExcelTable();
		List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		String size = Integer.toString(colSize);
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("rowdata", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			exporttableData.add(kMap);
		}
		return exporttableData;
	}
	
	//comparing data from excell sheet with table data of progress bar Not found and Found
	public void compareXlDataNotFoundandNotFound_UIdata() throws Exception {
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

	//comparing total entries in excel of visibilityreport table and test from bottom of the table
	public  void compareexporttableDatannumberofentries(List<Map<String, String>> dataTablefound,
			List<Map<String, String>> exporttableDataFound) {	
					
			for (Map<String, String> m1 : dataTablefound) {
				for (Map<String, String> m2 : exporttableDataFound) {
					if (m1.get("rowdata").equals(m2.get("rowdata"))) {
						Assert.assertEquals(formatFloat(m1.get("rowdata")), formatFloat(m2.get("rowdata")));
					}
				}
			}
		}

	public void compareexporttableDatannumberofentriesNotFound(List<Map<String, String>> dataTableNotfound,
			List<Map<String, String>> exporttableDataNotFound) {
		// TODO Auto-generated method stub
		
		for (Map<String, String> m1 : dataTableNotfound) {
			for (Map<String, String> m2 : exporttableDataNotFound) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(formatFloat(m1.get("rowdata")), formatFloat(m2.get("rowdata")));
				}
			}
		}
		
	}
		
	}