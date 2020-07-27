package com.dac.main.POM_TPSEE;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_AllLocations_Page extends TPSEE_abstractMethods {

	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	// Navigating to TPSEE Content_Analysis page
	public TPSEE_AllLocations_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	/*
	 * ------------------------------Locators---------------------------------------
	 */

	@FindBy(xpath = "//div[@id='locationsTableExportDropdown']/button")
	private WebElement Export;

	@FindBy(xpath = "//div[@id='locationsTableExportDropdown']//a[contains(text(),'Export as CSV')]")
	private WebElement Export_csv;

	@FindBy(xpath = "//div[@id='locationsTableExportDropdown']//a[contains(text(),'Export as XLSX')]")
	private WebElement Export_xlsx;

	@FindBy(xpath = "//div[@id='locationTable']/table")
	private WebElement LocationTable;

	@FindBy(xpath = "//table[@id='table_report']//thead")
	private WebElement LocationTableHeader;

	@FindBy(xpath = "//table[@id='table_report']//tbody//tr")
	private List<WebElement> LocationTableRow;

	@FindBy(xpath = "//div[@id='paginationInfo']")
	private WebElement entiresText;

	@FindBy(xpath = "//*[@id='location-table']/h3")
	private WebElement loc;

	@FindBy(xpath = "//select[@id='pageSize']")
	private WebElement ResultperPage;

	@FindBy(xpath = "//input[@id='current-page']")
	private WebElement GoToPage;

	/*-------------------------Pagination-----------------------*/

	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "//ul[@class='pagination']//li[@class='active']")
	private WebElement paginationFirst;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private List<WebElement> paginationLast;

	@FindBy(xpath = "//*[@id='page-content']//h3")
	private WebElement PageTitle;

	@FindBy(xpath = "//p[@class='lead']")
	private WebElement PageTitletext;

	/*-------------------------Pagination-----------------------*/

	/*
	 * ------------------------------Locators---------------------------------------
	 */

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	Select select;

	/**
	 * This method is used to check whether data is there in table or not based on
	 * the applied criteria
	 * 
	 * @return true : if data is there otherwise return false
	 */
	public boolean isDataAvailable() throws Exception {
		JSWaiter.waitJQueryAngular();
		scrollByElement(LocationTable);
		if (driver.findElement(By.id("paginationInfo")).isDisplayed()) {
			return true;
		} else if (driver.findElement(By.id("paginationInfo_empty")).isDisplayed()) {
			BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			return false;
		}
		waitForElement(LocationTable, 20);
		return false;
	}

	// Get UI table
	public List<Map<String, String>> LocationDataTable() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(LocationTable, 40);
		// getting into progressbar found listing
		System.out.println("\n reading table data********************* \n");
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("col-md-4")));
		String entiresText = driver.findElement(By.className("col-md-4")).getText();
		entiresText = entiresText.substring(entiresText.indexOf("("));
		int count = 0;
		if (paginationNext.isDisplayed()) {
			for (int i = 1; i <= page; i++) { // Loop will execute till the all the row of table completes.
				scrollByElement(LocationTableHeader);
				List<WebElement> rows_table = LocationTableRow; // To locate rows of table.
				int rows_count = rows_table.size(); // To calculate no of rows In table.
				count = count + rows_count;
				Map<String, String> kMap = new HashMap<String, String>();
				for (int row = 0; row < rows_count; row++) {
					List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); // To locate
																										// columns(cells)
																										// of that
																										// specific row.
					int columns_count = Columns_row.size(); // To calculate no of columns (cells). In that specific row.
					// System.out.println("Number of cells In Row " + noOfRows + " are " +
					// columns_count);
					for (int column = 0; column < columns_count; column++) { // Loop will execute till the last cell of
																				// that specific row.
						List<WebElement> headerTableRow = LocationTableHeader.findElements(By.tagName("th"));
						String headerText = headerTableRow.get(column).getText(), celtext = "";
						if (column == 1 & row < rows_count) {
							celtext = driver
									.findElement(
											By.xpath("(//table[@id='table_report']//tbody//tr//td)[" + (row + 1) + "]"))
									.getText();
						} else {
							celtext = Columns_row.get(column).getText().trim(); // To retrieve text from that specific
																				// cell.
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
		Assert.assertTrue(entiresText.contains("" + count + ""), "Table Data count matches with total enties count");
		return tableCellValues;
	}

	/**
	 * exporting progress bar table data CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void LocationDataTableExportCSV() throws FileNotFoundException, IOException, InterruptedException {
		JSWaiter.waitJQueryAngular();

		try {
			exportVATable(Export, Export_csv);
			Thread.sleep(4000);
			Robot robot = new Robot();
			robot.setAutoDelay(5000);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_S);
			if ("firefox".equalsIgnoreCase(CurrentState.getBrowser()))
				robot.keyPress(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + LocationExportCSV));
		Thread.sleep(5000);
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
	}

	/**
	 * exporting progress bar table data XSLX
	 * 
	 * @throws Exception
	 */
	public void LocationDataTableExportXLSX() throws Exception {
		JSWaiter.waitJQueryAngular();
		try {
			exportVATable(Export, Export_xlsx);
			Thread.sleep(4000);
			Robot robot = new Robot();
			robot.setAutoDelay(5000);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_S);
			if ("firefox".equalsIgnoreCase(CurrentState.getBrowser()))
				robot.keyPress(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + LocationExportXLSX));
		Thread.sleep(5000);
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
		BaseClass.addEvidence(CurrentState.getDriver(), "Download XLSX File", "yes");
	}

	// Get Excel into Map
	public List<Map<String, String>> getLocationDataTableExport() throws Exception {
		JSWaiter.waitJQueryAngular();
		LocationDataTableExportXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser() + LocationExportXLSX),
				"Location_List").getExcelTable();
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

	// Compare UI and Excel
	public void compareExprttoAnalysisSiteLinkData(List<Map<String, String>> LocationDataTable,
			List<Map<String, String>> getLocationDataTableExport) {

		for (Map<String, String> m1 : LocationDataTable) {
			for (Map<String, String> m2 : getLocationDataTableExport) {
				if (m1.get("Location").equals(m2.get("Location")))
					Assert.assertEquals(m1.get("Location").contains(m2.get("Location")), "Data Matches");

			}
		}
	}

	// Compare UI and Excel
	public void compareXlData_UIdata() throws Exception {
		JSWaiter.waitJQueryAngular();
		List<WebElement> Columns_row = LocationTableHeader.findElements(By.tagName("th"));
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

	/**
	 * To get Overview Location count
	 * 
	 * @return
	 */
	public int numberoflocation() {
		int totloc = numofloc(loc);
		return totloc;
	}

	public void VerifyLocationsTitleText(String Tit, String titText) {

		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
	}
	
	public void resultperpage(SoftAssert soft) throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		ResultsperPage(soft, entiresText, ResultperPage);
	}
	
	public void GoTo() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		waitForElement(GoToPage, 10);
		scrollByElement(GoToPage);
		GoTopage(GoToPage);
	}
}
