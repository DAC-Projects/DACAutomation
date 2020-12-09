package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

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
	@FindBy(xpath = "//*[@id='accuracyCurrentExportDropdown']//button")
	private WebElement exportBtn;

	@FindBy(xpath = "//*[@id='exportdate']")
	private WebElement exportdate;

	@FindBy(id = "ui-datepicker-div")
	private WebElement dtpicker;

	@FindBy(css = "td.ui-datepicker-days-cell-over")
	private WebElement date;

	@FindBy(xpath = "//a[contains(text(),'Export as CSV')]")
	private WebElement csvexport;

	@FindBy(xpath = "//a[contains(text(),'Export as XLSX')]")
	private WebElement XLSXExport;

	@FindBy(xpath = "//*[@id='btnLocationExport']")
	private WebElement export;

	@FindBy(xpath = "(//div//a[@class='load-table'][contains(text(),'')])")
	private List<WebElement> sitelink;

	@FindBy(xpath = "//*[@id='vendorAccuracyReportExport']//button")
	private WebElement tablebtn;

	@FindBy(xpath = "//*[@id='vendorAccuracyReportExport']//a[contains(text(),'Export as XLSX')]")
	private WebElement ExporttableXLSX;

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

	@FindBy(xpath = "//*[@id='inaccuracy_results']/tbody/tr[@role='row']")
	private List<WebElement> reviewTableRow;

	@FindBy(xpath = "//*[@id='inaccuracy_table']")
	private WebElement titlehead;

	@FindBy(xpath = "//label[contains(text(),'Show Inaccuracies Only')]")
	private WebElement InAccuracychkBox;

	@FindBy(xpath = "//label[contains(text(),'Show Ignored Only')]")
	private WebElement IgnoredchkBox;

	@FindBy(xpath = "//*[@id='inaccuracy_results_info']")
	private WebElement totalentries;

	@FindBy(xpath = "//div[@id='allSitesScores']//a[contains(@class,'load-table')][contains(text(),'')]")
	private WebElement vendorslist;

	@FindBy(xpath = "//div[@id='divNumOfLocations']")
	private WebElement overviewloc;

	@FindBy(xpath = "//div[@id='divOverallScoreValue']")
	private WebElement overviewscore;

	@FindBy(xpath = "//div[@id='divBars']")
	private WebElement overviewlayout;

	@FindBy(xpath = "//*[@id='all']")
	private WebElement Alltab;

	@FindBy(xpath = "//*[@id='name']")
	private WebElement Nametab;

	@FindBy(xpath = "//*[@id='address']")
	private WebElement Addresstab;

	@FindBy(xpath = "//*[@id='phone']")
	private WebElement PHNumtab;
	
	@FindBy(xpath = "//*[@id='accuracy_report']")
	private WebElement AccuracyPage;
	
	@FindBy(xpath = "//*[@id='reports']")
	private WebElement AccuracySec;
	
	@FindBy(xpath = "//select[@name='inaccuracy_results_length']")
	private WebElement Resultperpage;
	
	@FindBy(xpath = "//input[@class='page-input form-control form-control-sm']")
	private WebElement gotopage;
	
	@FindBy(xpath = "//div[@id='inaccuracy_results_info']")
	private WebElement Entry;

	@FindBy(xpath = "//label[@for='checkboxName']")
	private WebElement IgnoreName;
	
	@FindBy(xpath = "//label[@for='checkboxAddress']")
	private WebElement IgnoreAddress;
	
	@FindBy(xpath = "//label[@for='checkboxPhone']")
	private WebElement IgnorePhone;
	
	@FindBy(xpath = "//button[@id='updateInaccuracies']")
	private WebElement UpdateInaccuracyBtn;
	
	@FindBy(xpath = "//button[@class='btn btn-width-sm btn-success']")
	private WebElement UpdateSuccessBtn;
	
	@FindBy(xpath = "(//table//td[@class='display-name incorrect-style']//button[contains(@class,'btn-xs btn-info ignore')])")
	private List<WebElement> nameignore;
	
	String NameIgnore = "(//table//td[@class='display-name incorrect-style']//button[contains(@class,'btn-xs btn-info ignore')])";
	
	@FindBy(xpath = "(//table//td[@class='display-address incorrect-style']//button[contains(@class,'btn-xs btn-info ignore')])")
	private List<WebElement> addressignore; 
	
	String AddressIgnore = "(//table//td[@class='display-address incorrect-style']//button[contains(@class,'btn-xs btn-info ignore')])";
	
	@FindBy(xpath = "(//table//td[@class='display-phone incorrect-style']//button[contains(@class,'btn-xs btn-info ignore')])")
	private List<WebElement> phoneignore;
	
	String PhoneIgnore = "(//table//td[@class='display-phone incorrect-style']//button[contains(@class,'btn-xs btn-info ignore')])";
	
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
	/**
	 * Initializing webdriver
	 * @param driver
	 */
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

	/**
	 * Page load method
	 * @param timeout
	 */
	public void verify_pageloadCompletely(int timeout) {
		if ( waitForElement(grphtooltip, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	/**
	 * To get Overview Location count
	 * @return
	 */
	public int overviewlocation() {
		int location = overviewLocation(overviewlayout,overviewloc);
		return location;
	}

	/**
	 * To get Overview Score 
	 * @return
	 */
	public double overviewscore() {
		double score = overviewscore(overviewlayout,overviewscore);
		return score;
	}

	/**
	 * To get overall accuracy score
	 */
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

	/**
	 * Export as CSV overall accuracy
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void exportaccuracyrptCSV() throws InterruptedException, FileNotFoundException, IOException, ExecutionException{
		JSWaiter.waitJQueryAngular();
		exportVA(exportBtn, csvexport, exportdate,  date, export);
		Thread.sleep(10000);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExportCSV));
	}

	/**
	 * Export as XLSX overall accuracy
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void exportaccuracyrptXLSX() throws InterruptedException, FileNotFoundException, IOException, ExecutionException{
		JSWaiter.waitJQueryAngular();
		exportVA(exportBtn, XLSXExport, exportdate,  date, export);
		Thread.sleep(10000);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExportXLSX));
	}	

	/**
	 * To get list of data from excel
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getExportData() throws Exception {
		JSWaiter.waitJQueryAngular();
		exportaccuracyrptXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExportXLSX), "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		Workbook wb = new XSSFWorkbook(Exportpath + (CurrentState.getBrowser()+AccuracyExportXLSX));
		Sheet sh = wb.getSheetAt(0);
		Row row = sh.getRow(0);
		int Last_row = sh.getLastRowNum();
		System.out.println("The Last row number is : " +Last_row);
		int totLocations = overviewlocation();
		System.out.println("The total locations is : " +totLocations);
		soft.assertEquals(totLocations, Last_row - 1);
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("NoofLocation", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			exportData.add(kMap);
		}
		return exportData;
	}		

	/**
	 * To Get UI table Data
	 * @return
	 * @throws Exception 
	 */

	@SuppressWarnings("unused")
	public void verifysitelinkdata(SoftAssert soft) throws Exception{
		String data = null;
		JSWaiter.waitJQueryAngular();
		waitForElement(accuracysite, 40);
		waitForElement(siteshow, 40);
		scrollByElement(siteshow);
		int size = (sitelink.size())/2;
		int newsize;
		if(size>3) {
			newsize=3;
		}
		else {
			newsize =size;
		}
		System.out.println("The size is :" +newsize);
		for(int k = 1; k<=newsize; k++) {
			WebElement ele = driver.findElement(By.xpath("(//div//a[@class='load-table'][contains(text(),'')])["+ k +"]"));
			scrollByElement(ele);
			ele.click();
			System.out.println("Vendor Clicked");
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
				WebElement TableTitle = driver.findElement(By.xpath("(//*[@id='inaccuracy_table_title']//div//span)[1]"));
				String s = TableTitle.getText();
				System.out.println("The Vendor is :" +s);
				scrollByElement(TableTitle);
				if(!s.equalsIgnoreCase("Yelp")) {
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
								
								//System.out.println("Number of cells In Row " + noOfRows + " are " + columns_count);
								for (int column = 0; column < columns_count; column++) {	//Loop will execute till the last cell of that specific row.
									List<WebElement> headerTableRow=titlehead.findElements(By.tagName("th"));
									String headerText = headerTableRow.get(column).getText(), celtext ="";
									if(column==1 & row < rows_count) {
										celtext = driver.findElement(By.xpath("(//*[@id='inaccuracy_results']/tbody/tr[@role='row'])["+ (row+1) +"]")).getText();
										System.out.println("\n"+celtext);
										data = celtext.replace("Ignore inaccuracy", "");
										data = data.trim();
									}
									kMap.put("rowdata", data);
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
					scrollByElement(tableresult);
					System.out.println("UI Table Values :" +tableCellValues);
					List<Map<String, String>> TableExport = getExporttableData();
					System.out.println("Excel File Values :" +TableExport);
					int UISize = tableCellValues.size();
					System.out.println("UI Table size is :" +UISize);
					int XLSize = TableExport.size();
					System.out.println("Excel File size is :" +XLSize);
					if(UISize == XLSize) {
						for(int i = 0; i<=UISize; i++) {							
							//assertTrue(tableCellValues.get(i).equals(TableExport.get(i)));
						}
					}
					deletefile();
					tableCellValues.clear();
					scrollByElement(siteshow);
				}else {
					try {
						BaseClass.addEvidence(driver, "Data is not available for Yelp", "yes");
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
					try {
						BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		GoTo();
		Thread.sleep(3000);
		resultperpage(soft);
	}
	
	
	public boolean IsElementPresent(WebElement ele) {
		if(ele.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	public void VerifyUpdateInaccuracies(WebElement ele, String ele2, List<WebElement> ele3) throws InterruptedException {
		boolean elepresent = IsElementPresent(ele);
		if(elepresent == true) {
		scrollByElement(ele);
		clickelement(ele);
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
			String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
			int page = Integer.parseInt(n);
			System.out.println("\n"+page);
			if(paginationNext.isDisplayed()) {
				for(int i=1;i<=page;i++) {
					List < WebElement > rows_table = ele3;	//To locate rows of table. 
					int rows_count = rows_table.size();	
					for (int row = 1; row < rows_count; row++) { 
						WebElement ele1 = driver.findElement(By.xpath(ele2 + "["+ row +"]"));
						if(ele1.isDisplayed()) {
						scrollByElement(ele1);
						String verifycheck = ele1.getAttribute("class");
						System.out.println("The class name is : "+verifycheck);
						soft.assertTrue(verifycheck.contains("checked"));
						}else {
							System.out.println("No checkbox displayed");
						}
					}if(paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
					scrollByElement(UpdateInaccuracyBtn);
					clickelement(UpdateInaccuracyBtn);
					Thread.sleep(5000);
					wait.until(ExpectedConditions.visibilityOf(UpdateSuccessBtn));
					clickelement(UpdateSuccessBtn);
				}
			}
		}else {
			System.out.println("No Data Available");
		}
		}else {
			System.out.println("Only single data is available");
		}
	}
	
	public void verifyupdateinaccuracyname() throws InterruptedException {
		VerifyUpdateInaccuracies(IgnoreName, NameIgnore, nameignore);
		Thread.sleep(3000);
		WebElement ele =driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])"));
		scrollByElement(ele);
		ele.click();
	}
	
	public void verifyupdateinaccuracyaddress() throws InterruptedException {
		VerifyUpdateInaccuracies(IgnoreAddress, AddressIgnore, addressignore);
		Thread.sleep(3000);
		WebElement ele =driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])"));
		scrollByElement(ele);
		ele.click();
	}
	
	public void verifyupdateinaccuracyphone() throws InterruptedException {
		VerifyUpdateInaccuracies(IgnorePhone, PhoneIgnore, phoneignore);
		Thread.sleep(3000);
		WebElement ele =driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])"));
		scrollByElement(ele);
		ele.click();
	}

	/**
	 * To export table data as XLSX
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void exporttableAccuracyXLSX() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(tableresult, 40);
		WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
		String s = TableTitle.toString();
		scrollByElement(TableTitle);
		if(!s.equalsIgnoreCase("Yelp")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
			exportVATable(tablebtn, ExporttableXLSX);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableXLSX));
			Thread.sleep(5000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));

		}else {
			System.out.println("Data Not available for :" +s);
		}
	}


	/**
	 * 	To get data from export XLSX file
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getExporttableData() throws Exception {
		JSWaiter.waitJQueryAngular();
		exporttableAccuracyXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttableXLSX), "Accuracy_Report").getExcelTable();
		List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
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

	/**
	 * Method to verify inaccuracy checkbox
	 */
	public void showinaccuracy() {
		waitForElement(InAccuracychkBox, 15);
		scrollByElement(InAccuracychkBox);
		clickelement(InAccuracychkBox);
		String var = ((JavascriptExecutor)driver).executeScript("return document.getElementById('toggle-inaccuracies').checked").toString();
		System.out.println(var);
		boolean app = Boolean.parseBoolean(var);
		boolean out = true;
		Assert.assertEquals(app, out , "Matches");
	}

	/**
	 * Method to verify ignored checkbox
	 */
	public void showignored() {
		waitForElement(IgnoredchkBox, 10);
		scrollByElement(IgnoredchkBox);
		clickelement(IgnoredchkBox);
		String var = ((JavascriptExecutor)driver).executeScript("return document.getElementById('toggle_overridden').checked").toString();
		System.out.println(var);
		boolean app = Boolean.parseBoolean(var);
		boolean out = true;
		Assert.assertEquals(app, out , "Matches");
	}


	/**
	 * Compare XL and UI data
	 * @param getExporttableData
	 * @param verifysitelinkdata
	 */
	public void compareexporttableDatannumberofentries(List<Map<String, String>> getExporttableData,
			List<Map<String, String>> verifysitelinkdata) {
		for (Map<String, String> m1 : getExporttableData) {
			for (Map<String, String> m2 : verifysitelinkdata) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
				}
			}
		}
	}

	/**
	 * To get the Inaccuracy table headers
	 * @return
	 */
	public ArrayList<String> verifyHeader() {
		JSWaiter.waitJQueryAngular();
		waitForElement(tableresult, 40);
		scrollByElement(tableresult);
		ArrayList<String> TableHeader = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='inaccuracy_results']//th"));
		java.util.Iterator<WebElement> program = elements.iterator();
		while (program.hasNext()) {
			String values = program.next().getText();
			if(!values.equals("null")) {
				TableHeader.add(values);
				System.out.println("\n" +values);
			}
			else
			{
				System.out.println("\n No sites displayed \n");
			}

		}
		return TableHeader;
	}

	/**
	 * To verify ALL Tab headers with test data
	 * @throws Exception
	 */
	public void verifyAllTab() throws Exception{	
		waitForElement(Alltab, 10);
		scrollByElement(Alltab);
		ArrayList<String> XLData = GetSiteDataUsingColName("./data/InaccuracyTabs.xlsx", "ALL");
		ArrayList<String> UIsite = verifyHeader();
		Assert.assertEquals(XLData, UIsite, "Matches");			
	}

	/**
	 * To verify Name Tab headers with test data
	 * @throws Exception
	 */
	public void verifyNameTab() throws Exception{		
		waitForElement(Nametab, 10);
		scrollByElement(Nametab);
		clickelement(Nametab);
		ArrayList<String> XLData = GetSiteDataUsingColName("./data/InaccuracyTabs.xlsx", "NAME");
		ArrayList<String> UIData = verifyHeader();
		Assert.assertEquals(XLData, UIData, "Matches");			
	}

	/**
	 * To verify Address Tab headers with test data
	 * @throws Exception
	 */
	public void verifyAddressTab() throws Exception{
		waitForElement(Addresstab, 10);
		scrollByElement(Addresstab);
		clickelement(Addresstab);
		ArrayList<String> XLData = GetSiteDataUsingColName("./data/InaccuracyTabs.xlsx", "ADDRESS");
		ArrayList<String> UIData = verifyHeader();
		Assert.assertEquals(XLData, UIData, "Matches");			
	}

	/**
	 * To verify PhNo Tab headers with test data
	 * @throws Exception
	 */
	public void verifyPHNOTab() throws Exception{
		waitForElement(PHNumtab, 10);
		scrollByElement(PHNumtab);
		clickelement(PHNumtab);
		ArrayList<String> XLData = GetSiteDataUsingColName("./data/InaccuracyTabs.xlsx", "PH NUM");
		ArrayList<String> UIData = verifyHeader();
		Assert.assertEquals(XLData, UIData, "Matches");			
	}

	/*//Comparision of UI and Excel
	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
		String s = TableTitle.toString();
		scrollByElement(TableTitle);
		if(!s.equalsIgnoreCase("Yelp")) {
		List < WebElement > Columns_row = titlehead.findElements(By.tagName("th"));
		int col_count = Columns_row.size();
		String newfilename = BasePage.getLastModifiedFile("./downloads");
		//String newfilename = new formatConvert("./downloads/"+fileName).convertFile("xlsx");
		ExcelHandler a = new ExcelHandler("./downloads/"+newfilename, "Sheet0"); a.deleteEmptyRows();
		//a.find_column_no("", 0);
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
	}else {
		System.out.println("Data not available for : " +s);
	}
		}
	 */

	/*//Clicking on progress bar and getting the data from the table for found
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
				String s = TableTitle.toString();
				scrollByElement(TableTitle);
				if(!s.equalsIgnoreCase("Yelp")) {				
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
				}else {
					try {
						BaseClass.addEvidence(driver, "Data is not available for Yelp", "yes");
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
					return tableCellValues;
			}


			public void exporttableInAccuracy() throws FileNotFoundException, IOException, InterruptedException {
				JSWaiter.waitJQueryAngular();
				waitForElement(tableresult, 40);
				WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
				String s = TableTitle.toString();
				scrollByElement(TableTitle);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
				if(!s.equalsIgnoreCase("Yelp")) {				
					exportVATable(tablebtn, ExporttableXLSX);
					renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableInAccuracyXLSX));
					Thread.sleep(5000);
					CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));

				}else {
					System.out.println("No Data Available for :" +s);
				}
					}

		//printing visibility progress bar table data from downloaded excel sheet
		public List<Map<String, String>> getInAccuracyExporttableData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttableInAccuracy();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttableInAccuracyXLSX), "Sheet0").getExcelTable();
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
			String s = TableTitle.toString();
			scrollByElement(TableTitle);
			if(!s.equalsIgnoreCase("Yelp")) {
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
			else {
				System.out.println("No Data Available for : "+s);
			}
		}
			return tableCellValues;
		}


		public void exporttableIgnored() throws FileNotFoundException, IOException, InterruptedException {
			WebElement Ignoredchkbox = driver.findElement(By.xpath("//input[@id='toggle_overridden']"));
			Ignoredchkbox.click();
			waitForElement(tableresult, 40);
			WebElement TableTitle = driver.findElement(By.xpath("//*[@id='inaccuracy_table_title']"));
			String s = TableTitle.toString();
			scrollByElement(TableTitle);
			if(!s.equalsIgnoreCase("Yelp")) {
			waitForElement(tablebtn, 40);
			//scrollByElement(tablebtn);
			JSWaiter.waitUntilJQueryReady();
			download(CurrentState.getBrowser(), tablebtn, 30);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableIgnored));
			}else {
				System.out.println("No Data Available for :"+s);
			}
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

	//Compare Ignored UI Table and Excel
	public void compareexporttableDatanIgnoredtable(List<Map<String, String>> getIgnoredExporttableData,
			List<Map<String, String>> verifyIgnoredsitelinkdata) {
		for (Map<String, String> m1 : getIgnoredExporttableData) {
			for (Map<String, String> m2 : verifyIgnoredsitelinkdata) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
				}
			}
		}

	}*/


	/*//Compare Inaccuracy UI and Export
		public void compareexporttableDatanInaccuracytable(List<Map<String, String>> getIgnoredExporttableData,
		List<Map<String, String>> verifyIgnoredsitelinkdata) {
	for (Map<String, String> m1 : getIgnoredExporttableData) {
		for (Map<String, String> m2 : verifyIgnoredsitelinkdata) {
			if (m1.get("rowdata").equals(m2.get("rowdata"))) {
				Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
			}
		}
	}

}*/

	/**
	 * Method returns list of vendors
	 * @return
	 */
	public ArrayList<String> verifyAccuracySitevendors() {
		JSWaiter.waitJQueryAngular();
		waitForElement(vendorslist, 40);
		scrollByElement(vendorslist);
		ArrayList<String> vendor = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@id='allSitesScores']//a[contains(@class,'load-table')]"));
		System.out.println(elements.size());
		//*[@id='allSitesScores']/div/div/a
		for(int i = 1; i<=elements.size(); i++) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement vendorlisting = driver.findElement(By.xpath("(//div[@id='allSitesScores']//a[contains(@class,'load-table')][contains(text(),'')])["+i+"]"));
			scrollByElement(vendorlisting);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String vendorname = vendorlisting.getText();
			System.out.println(vendorname);
			vendor.add(vendorname);
			System.out.println(vendor);
		}
		return vendor;
	}

	public void Accuracyhighlight() {
		reporthighlight(AccuracyPage, AccuracySec);
	}
	
	public void resultperpage(SoftAssert soft) throws InterruptedException {
		WebElement ele =driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])"));
		scrollByElement(ele);
		ele.click();
		Thread.sleep(3000);
		ResultsperPage(soft, Entry, Resultperpage);
	}
	
	public void GoTo() throws InterruptedException {
		WebElement ele =driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])"));
		scrollByElement(ele);
		ele.click();
		waitForElement(gotopage, 10);
		scrollByElement(gotopage);
		GoTopage(gotopage);
	}
	
	public void comparegraphoverviewscore() {
		double ovrviewscore = overviewscore(overviewlayout,overviewscore);
		System.out.println("The overview panel score is : " +ovrviewscore);
		double grphscore = verifygrphscore();
		System.out.println("The graphscore is : " +grphscore);
		Assert.assertEquals(ovrviewscore, grphscore);
	}
}