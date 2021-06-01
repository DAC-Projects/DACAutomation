package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_Visibility_Page extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	static String time_stamp;

	/**
	 * Navigation to Visibility Page
	 * 
	 * @param driver
	 */
	public TPSEE_Visibility_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//li[@id='visibility_report']")
	private WebElement Report;
	
	@FindBy(xpath = "//*[@id='reports']")
	private WebElement ReportSection;
	/*----------------------Exporting into csv-------------------------*/
	@FindBy(xpath = "//*[@id='visibilityCurrentExportDropdown']//button")
	private WebElement exportBtn;

	@FindBy(xpath = "//a[contains(text(),'Export as CSV')]")
	private WebElement csvexport;

	@FindBy(xpath = "//a[contains(text(),'Export as XLSX')]")
	private WebElement XLSXExport;

	@FindBy(xpath = "//*[@id='exportdate']")
	private WebElement exportdate;

	@FindBy(id = "ui-datepicker-div")
	private WebElement dtpicker;

	@FindBy(xpath = "//td[contains(@class,'ui-datepicker-days-cell-over')]//a[contains(@class,'ui-state-default')]")
	private WebElement date;

	@FindBy(xpath = "//*[@id='btnLocationExport']")
	private WebElement export;

	/*----------------------Exporting into csv-------------------------*/

	/*----------------------Exporting into PDF-------------------------*/

	@FindBy(xpath = "//a[contains(text(),'Export as PDF')]")
	private WebElement pdfexport;

	@FindBy(xpath = "//a[@id='currentData']")
	private WebElement currentpdf;

	@FindBy(xpath = "//a[contains(text(),'Click here to download your report')]")
	private WebElement pdfclick;

	/*----------------------locators to export pdf for period of time-------------------------*/
	@FindBy(xpath = "//a[@id='historyData']")
	private WebElement historypdf;

	@FindBy(xpath = "//input[@id='exportStartDate']")
	private WebElement hstrystart;

	@FindBy(xpath = "//input[@id='exportEndDate']")
	private WebElement hstryend;

	@FindBy(xpath = "//*[@id='btnHistoryExport']")
	private WebElement hstrybtn;

	@FindBy(xpath = "//div[@id='exportAsyncMessage']//a[@class='close icon-remove icon-color-info']")
	private WebElement close;

	/*----------------------locators to export pdf for period of time-------------------------*/

	/*--------------------section of overall report------------------------*/
	@FindBy(xpath = "#divBars")
	private WebElement overall;

	@FindBy(xpath = "//*[@id='divBars']")
	private List<WebElement> comp;

	/*--------------------section of overall report------------------------*/

	/*--------------------tooltipvalue in the graph------------------------*/

	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip;

	String xpathCompetitors = "//*[@id='divBars']";
	String NoofLocation = "//*[@id='divNumOfLocations']";
	String overallscore = "//*[@id='divOverallScoreValue']";

	@FindBy(xpath = "//div[@id='divNumOfLocations']")
	private WebElement overviewloc;

	@FindBy(xpath = "//div[@id='divOverallScoreValue']")
	private WebElement overviewscore;

	@FindBy(xpath = "//div[@id='divBars']")
	private WebElement overviewlayout;

	/*--------------------tooltipvalue in the graph------------------------*/

	/*--------------------Site table section------------------------*/

	@FindBy(css = "div#barContainer.barContainer")
	private WebElement siteTable;

	@FindBy(css = "#divBars")
	private List<WebElement> Site;

	@FindBy(css = "div.progress-bar")
	private List<WebElement> progressfound;

	@FindBy(css = "div.not-bar")
	private List<WebElement> progressNotfound;

	/*--------------------Site table section------------------------*/

	/*--------------------table section at the end of visibility report------------------------*/

	@FindBy(xpath = "//*[@id='visibility_table']")
	private WebElement progressdata;

	@FindBy(xpath = "//*[@id='visibility_results_wrapper']")
	private WebElement progresstable;

	@FindBy(xpath = "//*[@id='visibility_results']/tbody")
	private WebElement progresstablevalue;

	@FindBy(xpath = "//*[@id='listingsForVendorExport']//button")
	private WebElement exporttable;

	@FindBy(xpath = "//div[@id='listingsForVendorExport']//a[contains(text(),'Export as CSV')]")
	private WebElement ExporttableCSV;

	@FindBy(xpath = "//div[@id='listingsForVendorExport']//a[contains(text(),'Export as XLSX')]")
	private WebElement ExporttableXLSX;

	@FindBy(xpath = "//*[@id='visibility_table_title']")
	private WebElement title;

	@FindBy(xpath = "//*[@id='visibility_table']/div[2]")
	private WebElement titlehead;

	@FindBy(xpath = "//*[@id='visibility_results']/tbody/tr")
	private List<WebElement> VisibityTableRow;
	
	@FindBy(xpath = "//select[@name='visibility_results_length']")
	private WebElement Resultperpage;
	
	@FindBy(xpath = "//input[contains(@class,'page-input form-control form-control-sm')]")
	private WebElement GotoPage;
	
	@FindBy(xpath = "//div[@id='visibility_results_info']")
	private WebElement EntryText;

	String Vendortitle = "//*[@id='visibility_table_title']";

	/*--------------------table section at the end of visibility report------------------------*/

	/*-----------------------------Displaying no.of entries-------------------------------*/

	@FindBy(xpath = "//*[@id='visibility_results_wrapper']/div[4]/div[1]")
	private WebElement entries;

	@FindBy(xpath = "//*[@id='visibility_results_info']")
	private WebElement totalentries;

	/*-----------------------------Displaying no.of entries-------------------------------*/

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

	/*--------------------------- Sites Tabs------------------------*/

	@FindBy(xpath = "//li[@id='liAll']")
	private WebElement AllSites;

	@FindBy(xpath = "//li[@id='liSES']")
	private WebElement SearchEngineSites;

	@FindBy(xpath = "//li[@id='liDS']")
	private WebElement DirectorySites;

	@FindBy(xpath = "//li[@id='liSS']")
	public WebElement SocialSites;
	
	@FindBy(xpath = "//table[@id='visibility_results']//th[contains(text(),'Name')]")
	private WebElement Namehead;
	
	private String Name = "(//table[@id='visibility_results']//tbody//td[2])";
	
	@FindBy(xpath = "//table[@id='visibility_results']//th[contains(text(),'Address')]")
	private WebElement Addresshead;
	
	private String Address = "(//table[@id='visibility_results']//tbody//td[3])";
	
	@FindBy(xpath = "//table[@id='visibility_results']//th[contains(text(),'City')]")
	private WebElement Cityhead;
	
	private String City = "(//table[@id='visibility_results']//tbody//td[4])";
	
	@FindBy(xpath = "//table[@id='visibility_results']//th[contains(text(),'St/Prov/Region')]")
	private WebElement Statehead;
	
	private String State = "(//table[@id='visibility_results']//tbody//td[5])";
	
	@FindBy(xpath = "//table[@id='visibility_results']//th[contains(text(),'Postal Code')]")
	private WebElement PostCodehead;
	
	private String PostCode = "(//table[@id='visibility_results']//tbody//td[6])";
	
	@FindBy(xpath = "//table[@id='visibility_results']//th[contains(text(),'Phone')]")
	private WebElement Phonehead;
	
	private String Phone = "(//table[@id='visibility_results']//tbody//td[7])";

	/*--------------------------- Sites Tabs------------------------*/

	List<WebElement> columns;
	List<WebElement> rows;

	@FindBy(xpath = "//*[@class='col-lg-2 bar-chart-column']")
	private WebElement vendorslist;

	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	public static List<Map<String, String>> tableCellValues1 = new ArrayList<Map<String, String>>();
	/*-------------------------------------------------------------------*/

	/**
	 * Export visibility overview as CSV report
	 * 
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void exportvisibilityrptCSV()
			throws InterruptedException, FileNotFoundException, IOException, ExecutionException {
		JSWaiter.waitJQueryAngular();
		exportVA(exportBtn, csvexport, exportdate, date, export);
		Thread.sleep(15000);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExportCSV));
	}

	/**
	 * Export visibility overview as XLSX report
	 * 
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void exportvisibilityrptXLSX()
			throws InterruptedException, FileNotFoundException, IOException, ExecutionException {
		JSWaiter.waitJQueryAngular();
		exportVA(exportBtn, XLSXExport, exportdate, date, export);
		Thread.sleep(10000);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExportXLSX));
		Workbook wb = new XSSFWorkbook(Exportpath + (CurrentState.getBrowser()+ time_stamp + VisibilityExportXLSX));
		Sheet sh = wb.getSheetAt(0);
		Row row = sh.getRow(0);
		int Last_row = sh.getLastRowNum();
		System.out.println("The Last row number is : " +Last_row);
		int totLocations = overviewlocation();
		System.out.println("The total locations is : " +totLocations);
		soft.assertEquals(totLocations, Last_row - 1);
	}

	/**
	 * Export visibility pdf report of current date
	 * 
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void exportcurrentvisibilityrptPDF()
			throws InterruptedException, FileNotFoundException, IOException, ExecutionException {
		JSWaiter.waitJQueryAngular();
		exportasPDFCurrentDate(exportBtn, pdfexport, currentpdf, pdfclick);
		Thread.sleep(10000);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExportPdf));
		verifyfileextension();
		clickelement(close);
	}

	/**
	 * Export visibility pdf report of selected date
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void exporthistoryvisibilityrptPDF() throws FileNotFoundException, InterruptedException, IOException {
		JSWaiter.waitJQueryAngular();
		exporthistrybtn(exportBtn, pdfexport, historypdf);

	}

	public void hstrypdfexport() throws FileNotFoundException, InterruptedException, IOException {
		JSWaiter.waitJQueryAngular();
		exportasPDFHistory(hstrybtn, pdfclick); 
		Thread.sleep(15000);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExportHistoryPdf));
		verifyfileextension();
		clickelement(close);
		}

	/**
	 * 
	 * To get overall score of the visibilty
	 * 
	 */
	public List<Map<String, String>> getOverviewReport() {
		JSWaiter.waitJQueryAngular();
		waitForElement(overall, 5);
		System.out.println("\n Reading overall ********** \n");
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
	 * 
	 * Page load method
	 * 
	 * @param timeout
	 */
	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(grphtooltip, timeout) && waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	/**
	 * Add exported data to ArrayList
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getExportData() throws Exception {
		JSWaiter.waitJQueryAngular();
		exportvisibilityrptXLSX();
		Thread.sleep(5000);
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser() + time_stamp + VisibilityExportXLSX), "Sheet0")
				.getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			// adding data into map
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
				exportData.add(kMap);
			}
		}
		return exportData;
	}

	/**
	 * 
	 * comparing values of graph and overall report
	 * 
	 */
	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {
		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				Assert.assertEquals(formatFloat(m1.get("score")), formatFloat(m2.get("overallscore")), 0.05f,
						"Verifying score for" + m1.get("NoofLocations"));
			}
		}
	}

	/**
	 * To Get Data from the Progress Bar Table Found
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void DataTablefound(SoftAssert soft) throws Exception {
		JSWaiter.waitJQueryAngular();
		waitForElement(siteTable, 5);
		int size = progressfound.size();
		int newsize;
		if (size > 3) {
			newsize = 3;
		} else {
			newsize = size;
		}
		System.out.println("The size is :" + size);
		for (int k = 1; k <= newsize; k++) {
			WebElement ele = driver.findElement(By.xpath("(//*[contains(@class, 'progress-bar') ])[" + k + "]"));
			scrollByElement(ele);
			ele.click();
			System.out.println("Progress Bar clicked");
			WebElement progressentry = driver.findElement(By.xpath("((//*[contains(@class, 'progress-bar')])/../../../div[contains(@class,'col-lg-1 bar-chart-column found-number')])["+ k +"]"));
			String pentry = progressentry.getText();
			System.out.println("The string entry is : " +pentry);
			int proentry = Integer.parseInt(pentry);
			System.out.println("The progress bar total number is : " +proentry);
			waitForElement(progressdata, 10);
			scrollByElement(progressdata);
			if (driver.findElement(By.className("dataTables_info")).isDisplayed()) {
				System.out.println("\n reading progress bar data div ********************* \n");
				waitForElement(progresstable, 5);
				scrollByElement(progresstable);
				System.out.println("\n reading progress bar data table ******************* \n");
				waitForElement(totalentries, 10);
				JSWaiter.waitJQueryAngular();
				waitForElement(progresstablevalue, 5);
				String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
				int page = Integer.parseInt(n);
				System.out.println("\n" + page);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
				WebElement entry = driver.findElement(By.xpath("//div[@id='visibility_results_info']"));
				int entrytext = NumOfentries(entry);
				System.out.println("UI table entry is : " +entrytext);
				soft.assertTrue(entrytext==proentry, "Table entry " +entrytext+ " not equals to found entry " +proentry);
				WebElement TableTitle = driver.findElement(By.xpath("//*[@id='visibilityTableHeader']/h3"));
				scrollByElement(TableTitle);
				int count = 0;
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
						scrollByElement(TableTitle);
						List<WebElement> rows_table = VisibityTableRow; // To locate rows of table.
						int rows_count = rows_table.size(); // To calculate no of rows In table.
						count = count + rows_count;
						Map<String, String> kMap = new HashMap<String, String>();
						for (int row = 0; row < rows_count; row++) {
							List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // To
																												// locate
																												// columns(cells)
																												// of
																												// that
																												// specific
																												// row.
							int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that
																	// specific row.

							for (int column = 0; column < columns_count; column++) { // Loop will execute till the last
																						// cell of that specific row.
								List<WebElement> headerTableRow = titlehead.findElements(By.tagName("th"));
								String headerText = headerTableRow.get(column).getText(), celtext = "";
								if (column == 1 & row < rows_count) {
									celtext = driver
											.findElement(By.xpath(
													"(//*[@id='visibility_results']/tbody/tr)[" + (row + 1) + "]"))
											.getText();
									System.out.println("\n" + celtext);
								}
								kMap.put("rowdata", celtext);
								tableCellValues.add(kMap);
							}
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
				System.out.println("Total number of entries in table : " + count);
				scrollByElement(progresstable);
				System.out.println("UI Table Values :" + tableCellValues);
				List<Map<String, String>> TableExport = getExporttableDataFound();
				System.out.println("Excel File Values :" + TableExport);
				int UISize = tableCellValues.size();
				System.out.println("UI Table size is :" + UISize);
				int XLSize = TableExport.size();
				System.out.println("Excel File size is :" + XLSize);
				if (UISize == XLSize) {
					for (int i = 0; i <= UISize - 1; i++) {
						assertTrue(tableCellValues.contains(TableExport));
					}
				}
				//deletefile();
				tableCellValues.clear();
			} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
				try {
					BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		GoTo();
		Thread.sleep(2000);
		resultperpage(soft);
	}
	
	public void verifyNameFound() throws InterruptedException {
		TableSorting(Namehead, Name, EntryText, VisibityTableRow, progresstable);
	}
	
	public void verifyAddressFound() throws InterruptedException {
		TableSorting(Addresshead, Address, EntryText, VisibityTableRow, progresstable);
	}
	
	public void verifyCityFound() throws InterruptedException {
		TableSorting(Cityhead, City, EntryText, VisibityTableRow, progresstable);
	}
	
	public void verifyStateFound() throws InterruptedException {
		TableSorting(Statehead, State, EntryText, VisibityTableRow, progresstable);
	}
	
	public void verifyPostCodeFound() throws InterruptedException {
		TableSorting(PostCodehead, PostCode, EntryText, VisibityTableRow, progresstable);
	}
	
	public void verifyPhoneFound() throws InterruptedException {
		TableSorting(Phonehead, Phone, EntryText, VisibityTableRow, progresstable);
	}

	/**
	 * Clicking on progress bar and getting the data from the table for Not Found
	 * list
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void DataTableNotfound(SoftAssert soft) throws Exception {
		JSWaiter.waitJQueryAngular();
		waitForElement(siteTable, 5);
		int size = progressNotfound.size();
		int newsize;
		if (size > 3) {
			newsize = 3;
			System.out.println("The final size is :" +newsize);
		} else {
			newsize = size;
			System.out.println("The final size is :" +newsize);
		}
		System.out.println("The final size is :" +newsize);
		for (int k = 1; k <= newsize; k++) {
			try {
				WebElement ele = driver.findElement(By.xpath("(//*[contains(@class , 'not-bar' )])[" + k + "]"));
				scrollByElement(ele);
				action.doubleClick(ele).build().perform();
				System.out.println("Progress Bar clicked");
				WebElement progressentry = driver.findElement(By.xpath("(//div[contains(@class,'not-bar')]/../../../div[contains(@class,'col-lg-2 bar-chart-column not-number')])[" + k + "]"));
				String pentry = progressentry.getText();
				System.out.println("The string entry is : " +pentry);
				int proentry = Integer.parseInt(pentry);
				System.out.println("The progress bar entry is :" +proentry);
				waitForElement(progressdata, 5);
				scrollByElement(progressdata);
				if (driver.findElement(By.className("dataTables_info")).isDisplayed()) {
					System.out.println("\n reading progress bar data div ********************* \n");
					JSWaiter.waitJQueryAngular();
					waitForElement(progresstable, 5);
					scrollByElement(progresstable);
					System.out.println("\n reading progress bar data table ******************* \n");
					Thread.sleep(5000);
					waitForElement(totalentries, 5);
					JSWaiter.waitJQueryAngular();
					waitForElement(progresstablevalue, 5);
					String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
					int page = Integer.parseInt(n);
					System.out.println("\n" + page);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
					WebElement entry = driver.findElement(By.xpath("//div[@id='visibility_results_info']"));
					int entrytext = NumOfentries(entry);
					System.out.println("UI table entry is : " +entrytext);
					soft.assertTrue(entrytext==proentry, "Table entry " +entrytext+ " not equals to not found " +proentry);
					WebElement TableTitle = driver.findElement(By.xpath("//*[@id='visibilityTableHeader']/h3"));
					scrollByElement(TableTitle);
					int count = 0;
					if (paginationNext.isDisplayed()) {
						for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
							scrollByElement(TableTitle);
							List<WebElement> rows_table = VisibityTableRow; // To locate rows of table.
							int rows_count = rows_table.size(); // To calculate no of rows In table.
							count = count + rows_count;
							Map<String, String> kMap = new HashMap<String, String>();
							for (int row = 0; row < rows_count; row++) {
								List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // To
																													// locate
																													// columns(cells)
																													// of
																													// that
																													// specific
																													// row.
								int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that
																		// specific row.

								for (int column = 0; column < columns_count; column++) { // Loop will execute till the
																							// last cell of that
																							// specific row.
									List<WebElement> headerTableRow = titlehead.findElements(By.tagName("th"));
									String headerText = headerTableRow.get(column).getText(), celtext = "";
									if (column == 1 & row < rows_count) {
										celtext = driver
												.findElement(By.xpath(
														"(//*[@id='visibility_results']/tbody/tr)[" + (row + 1) + "]"))
												.getText();
										System.out.println("\n" + celtext);
									}
									kMap.put("rowdata", celtext);
									tableCellValues.add(kMap);
								}
							}
							if (paginationNext.isEnabled()) {
								scrollByElement(paginationNext);
								paginationNext.click();
								Thread.sleep(4000);
							}
						}

					}
					System.out.println("Total number of entries in table : " + count);
					System.out.println("UI Table Values :" + tableCellValues);
					List<Map<String, String>> TableExport = getExporttableDataNotFound();
					System.out.println("Excel File Values :" + TableExport);
					int UISize = tableCellValues.size();
					System.out.println("UI Table size is :" + UISize);
					int XLSize = TableExport.size();
					System.out.println("Excel File size is :" + XLSize);
					if (UISize == XLSize) {
						for (int i = 0; i <= UISize - 1; i++) {
							assertTrue(tableCellValues.contains(TableExport));
						}
					}
					//deletefile();
					tableCellValues.clear();
				} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
					try {
						BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		GoTo();
		Thread.sleep(2000);
		resultperpage(soft);
	}

	/**
	 * exporting progress bar table data CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void exporttablefoundCSV() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(progressdata, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		exportVATable(exporttable, ExporttableCSV);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExporttableFoundCSV));
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	/**
	 * exporting progress bar table data XSLX
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void exporttablefoundXLSX() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(progressdata, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		exportVATable(exporttable, ExporttableXLSX);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExporttableFoundXLSX));
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	/**
	 * printing visibility progress bar table data from downloaded excel sheet
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public List<Map<String, String>> getExporttableDataFound() throws Exception {
		JSWaiter.waitJQueryAngular();
		exporttablefoundXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser() + time_stamp + VisibilityExporttableFoundXLSX),
				"Visibility_Report").getExcelTable();
		List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		String size = Integer.toString(colSize);
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("ExcelData", table[0][col]);
				kMap.put(table[i][col], table[i][col]);
				exporttableData.add(kMap);
			}
		}
		return exporttableData;
	}

	/**
	 * exporting progress bar Not found table data in CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void exporttableNotfoundCSV() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(progressdata, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		exportVATable(exporttable, ExporttableCSV);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExporttableNotFoundCSV));
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));

	}

	/**
	 * exporting progress bar Not found table data in CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void exporttableNotfoundXLSX() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(progressdata, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		exportVATable(exporttable, ExporttableXLSX);
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " +time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + VisibilityExporttableNotFoundXLSX));
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));

	}

	/**
	 * printing visibility progress bar Not found table data from downloaded excel
	 * sheet
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public List<Map<String, String>> getExporttableDataNotFound() throws Exception {
		JSWaiter.waitJQueryAngular();
		exporttableNotfoundXLSX();
		String[][] table = new ExcelHandler(
				Exportpath + (CurrentState.getBrowser() + time_stamp + VisibilityExporttableNotFoundXLSX), "Visibility_Report")
						.getExcelTable();
		List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		String size = Integer.toString(colSize);
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("rowdata", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
				exporttableData.add(kMap);
			}
		}
		return exporttableData;
	}

	/**
	 * comparing data from excel sheet with table data of progress bar Not found and
	 * Found
	 * 
	 * @throws Exception
	 */
	public void compareXlDataNotFoundandNotFound_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List<WebElement> Columns_row = titlehead.findElements(By.tagName("th"));
		int col_count = Columns_row.size();
		ExcelHandler a = new ExcelHandler("./downloads/chromeVisibilityExporttableFoundXLSX.xlsx", "Visibility_Report");
		a.deleteEmptyRows();
		int xlRowCount = new ExcelHandler("./downloads/chromeVisibilityExporttableFoundXLSX.xlsx", "Visibility_Report")
				.getRowCount();
		int count = 0;
		for (int i = 0; i < xlRowCount; i++) {
			col_count = a.getColCount(i);
			for (int j = 1; j <= col_count; j++) {
				String cellValue = a.getCellValue(i, j).trim();
				if (cellValue.contains("%"))
					cellValue = new String("" + Double.parseDouble(cellValue.replace("%", "")) + "%");
				if (cellValue.length() != 0 & cellValue != null) {
					Map<String, String> uiTableCellValue = tableCellValues.get(count);
					if (uiTableCellValue.containsValue(cellValue)) { // | uiTableCellValue.equals(cellValue)
						Assert.assertTrue(uiTableCellValue.containsValue(cellValue),
								uiTableCellValue + " is matches with Downloaded Excel value : " + cellValue);
					} else {
						Assert.assertTrue(false,
								uiTableCellValue + " is NOT matches with Downloaded Excel value : " + cellValue);
					}

					if (j < 1 | j > 5)
						count++;
				}
			}
		}
		CurrentState.getLogger().info("UI table data matches with Exported Excel Data");
		Assert.assertTrue(true, "UI table data matches with Exported Excel Data");
		tableCellValues.clear();
	}

	/**
	 * To get Overview Location count
	 * 
	 * @return
	 */
	public int overviewlocation() {
		int location = overviewLocation(overviewlayout, overviewloc);
		return location;
	}

	/**
	 * To get Overview Score
	 * 
	 * @return
	 */
	public double overviewscore() {
		double score = overviewscore(overviewlayout, overviewscore);
		return score;
	}
	
	public void compareovrscorengrphscore() {
		double ovrscore = overviewscore();
		System.out.println("The overall score is : " +ovrscore);
		double graphscore = verifygrphscore();
		System.out.println("The graph score is : " +graphscore);
		Assert.assertEquals(ovrscore, graphscore);
	}

	/**
	 * To Verify Sites in ALL SITES Section
	 * 
	 * @throws Exception
	 */
	public void verifyAllsites() throws Exception {

		ArrayList<String> XLsite = GetSiteDataUsingColName("./data/VendorList.xlsx", "All Sites");
		ArrayList<String> UIsite = verifySitevendors();
		int UIsize = UIsite.size();
		int XLsize = XLsite.size();
		if(UIsize == XLsize) {
			for(int i = 0; i <=UIsize - 1; i++) {
				String UIsitename = UIsite.get(i);
				System.out.println("The UI site name is :" +UIsitename);
				soft.assertTrue(XLsite.contains(UIsitename), "XLSite doesn't contains" +UIsitename);
			}
		}else {
			soft.fail("The size of array is not equal");
		}

	}

	/**
	 * To Verify Search engine sites
	 * 
	 * @throws Exception
	 */
	public void verifySearchEngineSites() throws Exception {
		waitForElement(SearchEngineSites, 10);
		scrollByElement(SearchEngineSites);
		Thread.sleep(5000);
		clickelement(SearchEngineSites);
		Thread.sleep(5000);
		ArrayList<String> XLsite = GetSiteDataUsingColName("./data/VendorList.xlsx", "Search Engine Sites");
		ArrayList<String> UIsite = verifySitevendors();
		int UIsize = UIsite.size();
		int XLsize = XLsite.size();
		if(UIsize == XLsize) {
			for(int i = 0; i <=UIsize - 1; i++) {
				String UIsitename = UIsite.get(i);
				System.out.println("The UI site name is :" +UIsitename);
				soft.assertTrue(XLsite.contains(UIsitename), "XLSite doesn't contains" +UIsitename);
			}
		}else {
			soft.fail("The size of array is not equal");
		}
	}

	/**
	 * To verify Directory Sites
	 * 
	 * @throws Exception
	 */
	public void verifyDirectorySites() throws Exception {
		waitForElement(DirectorySites, 5);
		scrollByElement(DirectorySites);
		clickelement(DirectorySites);
		JSWaiter.waitJQueryAngular();
		ArrayList<String> XLsite = GetSiteDataUsingColName("./data/VendorList.xlsx", "Directory Sites");
		ArrayList<String> UIsite = verifySitevendors();
		int UIsize = UIsite.size();
		int XLsize = XLsite.size();
		if(UIsize == XLsize) {
			for(int i = 0; i <=UIsize - 1; i++) {
				String UIsitename = UIsite.get(i);
				System.out.println("The UI site name is :" +UIsitename);
				soft.assertTrue(XLsite.contains(UIsitename), "XLSite doesn't contains" +UIsitename);
			}
		}else {
			soft.fail("The size of array is not equal");
		}
	}

	/**
	 * To verify Social Sites
	 * 
	 * @throws Exception
	 */
	public void verifySocialSites() throws Exception {
		waitForElement(SocialSites, 5);
		scrollByElement(SocialSites);
		clickelement(SocialSites);
		JSWaiter.waitJQueryAngular();
		ArrayList<String> XLsite = GetSiteDataUsingColName("./data/VendorList.xlsx", "Social Sites");
		ArrayList<String> UIsite = verifySitevendors();
		int UIsize = UIsite.size();
		int XLsize = XLsite.size();
		if(UIsize == XLsize) {
			for(int i = 0; i <=UIsize - 1; i++) {
				String UIsitename = UIsite.get(i);
				System.out.println("The UI site name is :" +UIsitename);
				soft.assertTrue(XLsite.contains(UIsitename), "XLSite doesn't contains" +UIsitename);
			}
		}else {
			soft.fail("The size of array is not equal");
		}
		clickelement(AllSites);
	}

	/**
	 * comparing total entries in excel of visibilityreport table and test from
	 * found table
	 * 
	 * @param dataTablefound
	 * @param exporttableDataFound
	 */
	public void compareexporttableDatannumberofentries(List<Map<String, String>> dataTablefound,
			List<Map<String, String>> exporttableDataFound) {
		for (Map<String, String> m1 : dataTablefound) {
			for (Map<String, String> m2 : exporttableDataFound) {
				if (m1.get("rowdata").equals(m2.get("Location Number"))) {
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("Location Number")), "Data Matches");
				}
			}
		}
	}

	/**
	 * comparing total entries in excel of visibilityreport table and test from not
	 * found table
	 * 
	 * @param dataTableNotfound
	 * @param exporttableDataNotFound
	 */
	public void compareexporttableDatannumberofentriesNotFound(List<Map<String, String>> dataTableNotfound,
			List<Map<String, String>> exporttableDataNotFound) {
		for (Map<String, String> m1 : dataTableNotfound) {
			for (Map<String, String> m2 : exporttableDataNotFound) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), "Data Matches");
				}
			}
		}
	}

	public void resultperpage(SoftAssert soft) throws InterruptedException {
		WebElement ele =driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])"));
		scrollByElement(ele);
		ele.click();
		JSWaiter.waitJQueryAngular();
		ResultsperPage(soft, EntryText, Resultperpage);
	}
	
	public void GoTo() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		JSWaiter.waitJQueryAngular();
		waitForElement(GotoPage, 5);
		scrollByElement(GotoPage);
		GoTopage(GotoPage);
	}
}