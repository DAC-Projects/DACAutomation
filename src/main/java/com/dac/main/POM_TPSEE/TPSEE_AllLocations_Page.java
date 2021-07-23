package com.dac.main.POM_TPSEE;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class TPSEE_AllLocations_Page extends TPSEE_abstractMethods {

	public static List<Map<String, String>> tableCellValues = new ArrayList<Map<String, String>>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	SoftAssert soft = new SoftAssert();
	int sortrow;
	static String time_stamp;

	public TPSEE_AllLocations_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
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

	@FindBy(xpath = "//table[@id='locationTable']//thead")
	private WebElement LocationTableHeader;

	@FindBy(xpath = "//table[@id='locationTable']//tbody//tr")
	private List<WebElement> LocationTableRow;

	@FindBy(xpath = "//*[@id='locationTable_info']")
	private WebElement entiresText;

	@FindBy(xpath = "//*[@id='allLocations']//h4")
	private WebElement loc;

	@FindBy(xpath = "//select[@name='locationTable_length']")
	private WebElement ResultperPage;

	@FindBy(xpath = "//input[contains(@class,'page-input form-control form-control-sm')]")
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

	@FindBy(xpath = "//*[@id='locations']")
	private WebElement LocationSec;

	@FindBy(xpath = "//*[@id='all_locations']")
	private WebElement AllLocationsPage;

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'Location Number')]")
	private WebElement LocationNumberhead;

	private String LocationNumber = "(//table[@id='locationTable']//tbody//td[1])";

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'Name')]")
	private WebElement Namehead;

	private String Name = "(//table[@id='locationTable']//tbody//td[2])";

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'Address')]")
	private WebElement Addresshead;

	private String Address = "(//table[@id='locationTable']//tbody//td[3])";

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'City')]")
	private WebElement Cityhead;

	private String City = "(//table[@id='locationTable']//tbody//td[4])";

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'St/Prov/Region')]")
	private WebElement Statehead;

	private String State = "(//table[@id='locationTable']//tbody//td[5])";

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'Postal Code')]")
	private WebElement PostCodehead;

	private String PostCode = "(//table[@id='locationTable']//tbody//td[6])";

	@FindBy(xpath = "//table[@id='locationTable']//th[contains(text(),'Phone')]")
	private WebElement Phonehead;

	private String Phone = "(//table[@id='locationTable']//tbody//td[7])";
	
	@FindBy(xpath = "//input[@id='searchBox1']")
	private WebElement Searchbox;

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
	List<String> List1 = new ArrayList<String>();

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
		waitForElement(LocationTable, 10);
		return false;
	}

	/**
	 * to get UI table data
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public List<Map<String, String>> LocationDataTable() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		waitForElement(LocationTable, 5);
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
									.findElement(By
											.xpath("(//table[@id='locationTable']//tbody//tr//td)[" + (row + 1) + "]"))
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
			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.setAutoDelay(2000);
			Thread.sleep(1000);
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
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " + time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + LocationExportCSV));
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
			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.setAutoDelay(2000);
			Thread.sleep(1000);
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
		time_stamp = timeStamp();
		System.out.println("The timestamp is : " + time_stamp);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + time_stamp + LocationExportXLSX));
		CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
		BaseClass.addEvidence(CurrentState.getDriver(), "Download XLSX File", "yes");
	}

	/**
	 * to get data form XLSX
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getLocationDataTableExport() throws Exception {
		JSWaiter.waitJQueryAngular();
		LocationDataTableExportXLSX();
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser() + time_stamp + LocationExportXLSX),
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

	/**
	 * to compare UI and XL
	 * 
	 * @param LocationDataTable
	 * @param getLocationDataTableExport
	 */
	public void compareExprttoAnalysisSiteLinkData(List<Map<String, String>> LocationDataTable,
			List<Map<String, String>> getLocationDataTableExport) {

		for (Map<String, String> m1 : LocationDataTable) {
			for (Map<String, String> m2 : getLocationDataTableExport) {
				if (m1.get("Location").equals(m2.get("Location")))
					Assert.assertEquals(m1.get("Location").contains(m2.get("Location")), "Data Matches");

			}
		}
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

	/**
	 * to verify title and title text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyLocationsTitleText(String Tit, String titText) {

		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
	}

	/**
	 * to verify results per page
	 * 
	 * @param soft
	 * @throws InterruptedException
	 */
	public void resultperpage(SoftAssert soft) throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		JSWaiter.waitJQueryAngular();
		ResultsperPage(soft, entiresText, ResultperPage);

	}

	/**
	 * to verify goto page
	 * 
	 * @throws InterruptedException
	 */
	public void GoTo() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		JSWaiter.waitJQueryAngular();
		waitForElement(GoToPage, 10);
		scrollByElement(GoToPage);
		GoTopage(GoToPage);
	}

	/**
	 * to verify table sort - Location Number
	 * 
	 * @throws InterruptedException
	 */
	public void verifyLocationNumber() throws InterruptedException {
		TableSorting(LocationNumberhead, LocationNumber, entiresText, LocationTableRow, LocationTable);
	}

	/**
	 * to verify table sort - Name
	 * 
	 * @throws InterruptedException
	 */
	public void verifyName() throws InterruptedException {
		TableSorting(Namehead, Name, entiresText, LocationTableRow, LocationTable);
	}

	/**
	 * to verify table sort - Address
	 * 
	 * @throws InterruptedException
	 */
	public void verifyAddress() throws InterruptedException {
		TableSorting(Addresshead, Address, entiresText, LocationTableRow, LocationTable);
	}

	/**
	 * to verify table sort - City
	 * 
	 * @throws InterruptedException
	 */
	public void verifyCity() throws InterruptedException {
		TableSorting(Cityhead, City, entiresText, LocationTableRow, LocationTable);
	}

	/**
	 * to verify table sort - State
	 * 
	 * @throws InterruptedException
	 */
	public void verifyState() throws InterruptedException {
		TableSorting(Statehead, State, entiresText, LocationTableRow, LocationTable);
	}

	/**
	 * to verify table sort - Post Code
	 * 
	 * @throws InterruptedException
	 */
	public void verifyPostCode() throws InterruptedException {
		TableSorting(PostCodehead, PostCode, entiresText, LocationTableRow, LocationTable);
	}

	/**
	 * to verify table sort - Phone
	 * 
	 * @throws InterruptedException
	 */
	public void verifyPhone() throws InterruptedException {
		TableSorting(Phonehead, Phone, entiresText, LocationTableRow, LocationTable);
	}
	
	public void SearchwithKeywords(String Keyword) throws InterruptedException {
		scrollByElement(Searchbox);
		clickelement(Searchbox);
		Searchbox.sendKeys(Keyword);
		Searchbox.sendKeys(Keys.ENTER);
		JSWaiter.waitJQueryAngular();
		Thread.sleep(3000);
	}
	
	public void ClearSearchText() throws InterruptedException {
		scrollByElement(Searchbox);
		Searchbox.clear();
		JSWaiter.waitJQueryAngular();
		Thread.sleep(3000);
	}
	
}
