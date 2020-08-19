package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_ContentAnalysis_Page extends TPSEE_abstractMethods {

	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	// Navigating to TPSEE Content_Analysis page
	public TPSEE_ContentAnalysis_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	/*
	 * ------------------------------Locators---------------------------------------
	 */

	@FindBy(css = "div.progress.progress-striped.pos-rel")
	private WebElement Progress;

	@FindBy(xpath = "//*[@id='table_review']")
	private WebElement contentsiteTable;

	String Progressbar = "//div[contains(@class,'progress progress-striped pos-rel')]";

	@FindBy(xpath = "//div[@id='completenessTableExportDropdown']//button")
	private WebElement export1;

	@FindBy(xpath = "//div[@id='completenessTableExportDropdown']//a[contains(text(),'Export as CSV')]")
	private WebElement export1_csv;

	@FindBy(xpath = " //div[@id='completenessTableExportDropdown']//a[contains(text(),'Export as XLSX')]")
	private WebElement export1_xlsx;

	@FindBy(xpath = "//table[@id='table_review']/tbody/tr")
	private List<WebElement> SiteTableRow;

	@FindBy(xpath = "//table[@id='table_review']/thead")
	private WebElement SiteTableHeader;

	@FindBy(css = "img.logo-img.img-responsive.sourceImg")
	private List<WebElement> SiteLink;

	@FindBy(xpath = "//img[@class='logo-img img-responsive sourceImg']")
	private WebElement vendorslist;
	/*-------------------------SiteTableData-----------------------*/

	@FindBy(xpath = "//div[@id='incomplete_table']")
	private WebElement SiteLinkTable;

	@FindBy(xpath = "//div[@id='incomplete_table_title']")
	private WebElement SiteLinkTabletitle;

	@FindBy(xpath = "//*[@id='incomplete_results']/thead")
	private WebElement SiteLinkTableHeader;

	@FindBy(xpath = "//div[@id='jump_to_table']//tbody//tr")
	private List<WebElement> SiteLinkTableRow;

	@FindBy(xpath = "//table[@id='incomplete_results']")
	private WebElement Tableresults;

	@FindBy(xpath = "//div[@id='contentAnalysisIncompleteExportDropdown']/button")
	private WebElement TableExport;

	@FindBy(xpath = "//div[@id='contentAnalysisIncompleteExportDropdown']//a[contains(text(),'Export as CSV')]")
	private WebElement TableExport_csv;

	@FindBy(xpath = " //div[@id='contentAnalysisIncompleteExportDropdown']//a[contains(text(),'Export as XLSX')]")
	private WebElement TableExport_xlsx;

	@FindBy(xpath = "//div[@id='incomplete_results_info']")
	private WebElement entiresText;

	@FindBy(xpath = "//div[@class='highcharts-label highcharts-tooltip-box highcharts-color-none']//span")
	private WebElement NumOfLocations;

	/*-------------------------SiteTableData-----------------------*/

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

	// tooltipvalue in the graph
	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip;

	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none>span>span.bold")
	private WebElement scorenloc;
	
	@FindBy(xpath = "//*[@id='completeness_report']")
	private WebElement ContentAnalysisPage;
	
	@FindBy(xpath = "//*[@id='reports']")
	private WebElement ContentAnalysisSec;
	
	@FindBy(xpath = "//select[@name='incomplete_results_length']")
	private WebElement Resultperpage;
	
	@FindBy(xpath = "//input[contains(@class , 'form-control')]")
	private WebElement gotopage;

	/*
	 * ------------------------------Locators
	 * Ends---------------------------------------
	 */

	/*
	 * //Download Excel public void exportvisibilityrpt() throws
	 * InterruptedException, FileNotFoundException, IOException{
	 * JSWaiter.waitJQueryAngular(); if(export.isEnabled() & export.isDisplayed()) {
	 * wait.until(ExpectedConditions.visibilityOf(export));
	 * action.moveToElement(export).click(export).perform();
	 * convertExports(getLastModifiedFile(Exportpath),
	 * (CurrentState.getBrowser()+ContentAnalysisExportXLSX)); Thread.sleep(5000);
	 * CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile(
	 * "./downloads")); }else {
	 * System.out.println("No Data Available in ContentAnalysis Page"); } }
	 */

	// Get Overview Score
	@Override
	public List<Map<String, String>> getOverviewReport() {
		JSWaiter.waitJQueryAngular();
		waitForElement(Progress, 40);
		System.out.println("\n Reading overall ********** \n");
		Map<String, String> kMap;

		// adding data into List
		List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
		WebElement s = driver.findElement(By.xpath(Progressbar));

		kMap = new HashMap<String, String>();
		kMap.put("overallscore", s.findElement(By.xpath(Progressbar)).getAttribute("data-percent"));
		System.out.format("%10s", s.findElement(By.xpath(Progressbar)).getAttribute("data-percent"));
		ovrwRprtData.add(kMap);
		return ovrwRprtData;
	}

	/**
	 * exporting progress bar table data CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void ContentAnalysisExportCSV() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		exportVATable(export1, export1_csv);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + ContentAnalysisExportCSV));
		Thread.sleep(5000);
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	/**
	 * exporting progress bar table data XSLX
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void ContentAnalysisExportXLSX() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		exportVATable(export1, export1_xlsx);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + ContentAnalysisExportXLSX));
		Thread.sleep(5000);
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	/*
	 * //Download Export public void exportcontentanalysisrpt() throws
	 * InterruptedException, FileNotFoundException, IOException { // TODO
	 * Auto-generated method stub JSWaiter.waitJQueryAngular();
	 * if(export.isDisplayed() && export.isEnabled()) {
	 * wait.until(ExpectedConditions.visibilityOf(export));
	 * action.moveToElement(export).click(export).perform(); Thread.sleep(5000);
	 * convertExports(getLastModifiedFile(Exportpath),
	 * (CurrentState.getBrowser()+ContentAnalysisExportXLSX)); Thread.sleep(5000);
	 * CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile(
	 * "./downloads")); }else {
	 * System.out.println("No Data Available in Content Analysis Page"); } }
	 */

	// Store Excel into Map
	public List<Map<String, String>> getExportData() throws Exception {
		JSWaiter.waitJQueryAngular();
		ContentAnalysisExportXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser() + ContentAnalysisExportXLSX),
				"ContentAnalysis_Report").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			// adding data into map
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("Vendor", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
		}
		// returning content analysis report from excel
		return exportData;
	}

	@SuppressWarnings("unused")
	public List<Map<String, String>> AnalysisSiteData() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if (contentsiteTable.isDisplayed()) {
			// WebElement vendorname= driver.findElement(By.xpath("//*[@class='logo-img
			// img-responsive sourceImg']"));
			WebElement completeAnalysis = driver
					.findElement(By.xpath("//*[@class='easy-pie-chart percentage easyPieChart']"));
			scrollByElement(completeAnalysis);
			int count = 0;
			// Loop will execute till the all the row of table completes.
			scrollByElement(completeAnalysis);
			List<WebElement> rows_table = SiteTableRow; // To locate rows of table.
			int rows_count = rows_table.size(); // To calculate no of rows In table.
			count = count + rows_count;
			Map<String, String> kMap = new HashMap<String, String>();
			for (int row = 0; row < rows_count; row++) {
				List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));// To locate
																									// columns(cells) of
																									// that specific
																									// row.
				int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that specific row.

				// System.out.println("Number of cells In Row " + noOfRows + " are " +
				// columns_count);
				for (int column = 0; column < columns_count; column++) { // Loop will execute till the last cell of that
																			// specific row.
					List<WebElement> headerTableRow = SiteTableHeader.findElements(By.tagName("th"));
					String headerText = headerTableRow.get(column).getText(), vendorname = "", celtext = "";
					if (column == 1 & row < rows_count) {
						vendorname = driver
								.findElement(By
										.xpath("(//*[@class='logo-img img-responsive sourceImg'])[" + (row + 1) + "]"))
								.getAttribute("id");
						celtext = driver.findElement(By.xpath(
								"(//*[contains(@class,'easy-pie-chart percentage easyPieChart')])[" + (row + 1) + "]"))
								.getAttribute("data-percent");
					} else {
						celtext = Columns_row.get(column).getText().trim(); // To retrieve text from that specific cell.
					}
					kMap.put("rowdata", celtext);
					tableCellValues.add(kMap);
					// System.out.println("Cell Value of row " + noOfRows + vendorname +" and column
					// " + headerText + " Is : " + celtext);
				}
				// System.out.println("-------------------------------------------------- ");
			}

			System.out.println("Total number of entries in table : " + rows_count);
		}
		return tableCellValues;
	}

	public void compareExprttoAnalysisSiteData(List<Map<String, String>> exportData,
			List<Map<String, String>> analysisSiteData) {

		for (Map<String, String> m1 : exportData) {
			for (Map<String, String> m2 : analysisSiteData) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(m1.size(), m2.size() - 1);
					Assert.assertEquals(m1.get("rowdata"), m2.get("rowdata"));
				}
			}
		}
	}

	/**
	 * To get Overview Score
	 * 
	 * @return
	 */
	public double overviewscore() {
		double score = overviewcascore(Progress);
		return score;
	}

	public void SitelLinkData(SoftAssert soft) throws Exception { 
		JSWaiter.waitJQueryAngular();
		waitForElement(contentsiteTable, 40);
		scrollByElement(contentsiteTable);
		int size = SiteLink.size();
		int newsize;
		if (size > 3) {
			newsize = 3;
		} else {
			newsize = size;
		}
		System.out.println("The size is :" + size);
		for (int k = 1; k <= newsize; k++) {
			driver.findElement(By.xpath("(//*[@class='logo-img img-responsive sourceImg'])[" + k + "]")).click();

			System.out.println("\n Link clicked \n");
			waitForElement(SiteLinkTable, 40);
			scrollByElement(SiteLinkTable);
			System.out.println("\n reading table data********************* \n");
			JSWaiter.waitJQueryAngular();
			waitForElement(Tableresults, 50);
			scrollByElement(Tableresults);
			System.out.println("\n reading data table ******************* \n");
			if (driver.findElement(By.className("dataTables_info")).isDisplayed()) {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='incomplete_results']")));
				JSWaiter.waitJQueryAngular();
				waitForElement(Tableresults, 50);
				String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
				int page = Integer.parseInt(n);
				System.out.println("\n" + page);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
				String entiresText = driver.findElement(By.className("dataTables_info")).getText();
				entiresText = entiresText.substring(entiresText.indexOf("("));
				WebElement TableTitle = driver.findElement(By.xpath("//div[@id='incomplete_table_title']"));
				scrollByElement(TableTitle);
				String s = TableTitle.getText();
				System.out.println("The vendor is :" + s);
				if (!s.contains("Yelp")) {
					WebElement locationsText = driver.findElement(By.xpath("//*[@id='incomplete_results']//div"));
					scrollByElement(locationsText);
					int count = 0;
					if (paginationNext.isDisplayed()) {
						for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
							scrollByElement(SiteLinkTableHeader);
							List<WebElement> rows_table = SiteLinkTableRow; // To locate rows of table.
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

								// System.out.println("Number of cells In Row " + noOfRows + " are " +
								// columns_count);
								for (int column = 1; column < columns_count; column++) { // Loop will execute till the
																							// last cell of that
																							// specific row.
									List<WebElement> headerTableRow = SiteLinkTableHeader
											.findElements(By.tagName("th"));
									String headerText = headerTableRow.get(column).getText(), celtext = "";
									if (column == 1 & row < rows_count) {
										celtext = driver
												.findElement(By.xpath(
														"(//*[@id='incomplete_results']//div)[" + (row + 1) + "]"))
												.getText();
									} else {
										celtext = Columns_row.get(column).getText().trim(); // To retrieve text from
																							// that specific cell.
									}
									kMap.put("Location", celtext);
									tableCellValues.add(kMap);
									System.out.println("Cell Value of column " + headerText + " Is : " + celtext);
								}
								// System.out.println("-------------------------------------------------- ");
							}
							if (paginationNext.isEnabled()) {
								JSWaiter.waitJQueryAngular();
								scrollByElement(paginationNext);
								paginationNext.click();
								Thread.sleep(4000);
							}
						}
					}
					System.out.println("Total number of entries in table : " + count);
					Assert.assertTrue(entiresText.contains("" + count + ""),
							"Table Data count matches with total enties count");
					scrollByElement(Tableresults);
					System.out.println("UI Table Values :" + tableCellValues);
					List<Map<String, String>> TableExport = getSiteLinkExporttableData();
					System.out.println("Excel File Values :" + TableExport);
					int UISize = tableCellValues.size();
					System.out.println("UI Table size is :" + UISize);
					int XLSize = TableExport.size();
					System.out.println("Excel File size is :" + XLSize);
					if (UISize == XLSize) {
						for (int i = 0; i <= UISize; i++) {
							// assertTrue(tableCellValues.get(i).equals(TableExport.get(i)));
						}
					}
					deletefile();
					tableCellValues.clear();
					scrollByElement(contentsiteTable);
				} else {
					try {
						BaseClass.addEvidence(driver, "Data is not available for Yelp", "yes");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
				try {
					BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			GoTo();
			Thread.sleep(3000);
			resultperpage(soft);
		}
	}

	/*
	 * public void SiteLinkexporttable() throws FileNotFoundException, IOException,
	 * InterruptedException { waitForElement(SiteLinkTable, 40);
	 * waitForElement(TableExport, 40); //scrollByElement(TableExport);
	 * JSWaiter.waitUntilJQueryReady(); download(CurrentState.getBrowser(),
	 * TableExport, 30); convertExports(getLastModifiedFile(Exportpath),
	 * (CurrentState.getBrowser()+ContentAnalysisSiteExportXLSX)); }
	 */

	/**
	 * exporting progress bar table data CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void ContentAnalysisSiteExportCSV() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		exportVATable(TableExport, TableExport_csv);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + ContentAnalysisSiteExportCSV));
		Thread.sleep(5000);
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	/**
	 * exporting progress bar table data XSLX
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void ContentAnalysisSiteExportXLSX() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();
		exportVATable(TableExport, TableExport_xlsx);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + ContentAnalysisSiteExportXLSX));
		Thread.sleep(5000);
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	public List<Map<String, String>> getSiteLinkExporttableData() throws Exception {
		JSWaiter.waitJQueryAngular();
		ContentAnalysisSiteExportXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser() + ContentAnalysisSiteExportXLSX),
				"Content_Analysis").getExcelTable();
		List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			// adding data into map
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("Location", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
		}
		return exporttableData;
	}

	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List<WebElement> Columns_row = SiteLinkTableHeader.findElements(By.tagName("th"));
		int col_count = Columns_row.size();
		String newfilename = BasePage.getLastModifiedFile("./downloads");
		// String newfilename = new
		// formatConvert("./downloads/"+fileName).convertFile("xlsx");
		ExcelHandler a = new ExcelHandler("./downloads/" + newfilename, "Sheet0");
		a.deleteEmptyRows();
		int xlRowCount = new ExcelHandler("./downloads/" + newfilename, "Sheet0").getRowCount();
		int count = 0;
		for (int i = 1; i < xlRowCount; i++) {
			col_count = a.getColCount(i);
			for (int j = 0; j <= col_count; j++) {
				String cellValue = a.getCellValue(i, j + 1).trim();
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

	// To get Vendors List displaying in the application
	public List<Map<String, String>> verifyAnalysisSitevendors() {
		JSWaiter.waitJQueryAngular();
		waitForElement(vendorslist, 40);
		scrollByElement(vendorslist);
		Map<String, String> kMap;
		List<Map<String, String>> Vendors = new ArrayList<Map<String, String>>();
		List<WebElement> elements = driver.findElements(By.xpath("(//*[@class='logo-img img-responsive sourceImg'])"));
		java.util.Iterator<WebElement> program = elements.iterator();
		kMap = new HashMap<String, String>();

		// reading Vendors data
		while (program.hasNext()) {
			String values = program.next().getAttribute("id");
			if (!values.equals("null")) {
				kMap.put("vendors", values);
				System.out.println("\n" + values);
			} else {
				System.out.println("\n No sites displayed \n");
			}
			// adding into the map
			Vendors.add(kMap);
		}
		return Vendors;
	}

	public void compareexporttableDatannumberofentries(List<Map<String, String>> sitelLinkData,
			List<Map<String, String>> siteLinkExporttableData) {

		for (Map<String, String> m1 : sitelLinkData) {
			for (Map<String, String> m2 : siteLinkExporttableData) {
				if (m1.get("rowdata").equals(m2.get("rowdata"))) {
					Assert.assertEquals(m1.size(), m2.size());
					Assert.assertEquals(m1.get("rowdata").contains(m2.get("rowdata")), true);
				}
			}
		}

	}

	public double CAScore()
			throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth()) / 2 - 2, 0).click().perform();
		String tooltipvalue = NumOfLocations.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + tooltipvalue);
		String score1 = tooltipvalue.substring(tooltipvalue.lastIndexOf(":") + 1);
		// double score = Double.parseDouble(tooltipvalue.substring(46, 51));
		double score = Double.parseDouble(score1);
		System.out.println(score);
		return score;
	}

	public int CALoc()
			throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth()) / 2 - 2, 0).click().perform();
		String tooltipvalue = NumOfLocations.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + tooltipvalue);
		// int numberoflocations = Integer.parseInt(tooltipvalue.substring(31, 33));
		String NumOfLocations = tooltipvalue.substring(tooltipvalue.indexOf(":") + 1, tooltipvalue.indexOf("S") - 1);
		int numberoflocations = Integer.parseInt(NumOfLocations);
		System.out.println(numberoflocations);
		return numberoflocations;
	}
	
	public void ContentAnalysishighlight() {
		reporthighlight(ContentAnalysisPage, ContentAnalysisSec);
	}
	
	public void resultperpage(SoftAssert soft) throws InterruptedException {
		if (driver.findElement(By.className("dataTables_info")).isDisplayed()) {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		ResultsperPage(soft, entiresText, Resultperpage);
		}else {
			System.out.println("No Data Available");
		}
	}
	
	public void GoTo() throws InterruptedException {
		if (driver.findElement(By.className("dataTables_info")).isDisplayed()) {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		waitForElement(gotopage, 10);
		scrollByElement(gotopage);
		GoTopage(gotopage);
		}else {
			System.out.println("No Data available");
		}
	}
}
