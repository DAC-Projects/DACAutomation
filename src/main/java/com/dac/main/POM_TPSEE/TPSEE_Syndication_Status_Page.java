package com.dac.main.POM_TPSEE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class TPSEE_Syndication_Status_Page extends TPSEE_abstractMethods {

	public TPSEE_Syndication_Status_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		return null;
	}

	@FindBy(xpath = "//*[@id='page-content']//h3")
	private WebElement PageTitle;

	@FindBy(xpath = "//p[@class='lead']")
	private WebElement PageTitletext;

	@FindBy(xpath = "//div//span[@class = 'locations-count text-primary']")
	private WebElement NumOfLoc;

	@FindBy(xpath = "(//*[@id='overviewTables']//h4)[1]")
	private WebElement Tier1VendorTableHeader;

	@FindBy(xpath = "(//table[@id='tier1-table'])")
	private List<WebElement> VendorTable;

	@FindBy(xpath = "//table[@id='syndication-table']")
	private WebElement SyndicationLocTable;

	@FindBy(xpath = "//table[@id='syndication-table']//tbody//tr")
	private List<WebElement> SyndicationLocTableRow;

	@FindBy(xpath = "//a[@class='anchor-slide']")
	private WebElement ListingLink;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private WebElement Last_Page;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement Page_Next;

	@FindBy(xpath = "(//*[@class='pagination']//a)[2]")
	private WebElement Page_First;

	@FindBy(xpath = "//div[@id='syndication-table_info']")
	private WebElement SyndTableInfo;

	@FindBy(xpath = "//*[@id='syndication-table_info']")
	private WebElement TotalEntries;
	
	@FindBy(xpath = "//li[@id='reports']")
	private WebElement SyndicationSec;
	
	@FindBy(xpath = "//li[@id='syndication_status_report']")
	private WebElement SyndicationPage;
	
	@FindBy(xpath = "//select[@name='syndication-table_length']")
	private WebElement Resultperpage;
	
	@FindBy(xpath = "//input[contains(@class,'page-input form-control form-control-sm')]")
	private WebElement gotopage;
	
	@FindBy(xpath = "//div[@id='syndication-table_info']")
	private WebElement Entry;

	/**
	 * To verify title and title text
	 */
	public void VerifyTitlenText() {
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		String PgeTitle = "Syndication Status";
		String TitleTxt = "Below are locations participating in data syndication and the current status of each vendor supported in this report.";
		Assert.assertEquals(Title, PgeTitle);
		Assert.assertEquals(TitleText, TitleTxt);
	}

	/**
	 * To get Number of Locations in Overview Section
	 */
	public void getNumofLoc() {
		String Locations = NumOfLoc.getText();
		int TotalLocations = Integer.parseInt(Locations);
		System.out.println("The total Locations are:" + TotalLocations);
		int NumOflocations = NumOfentries(TotalEntries);
		System.out.println("The Number of Locations is:" + NumOflocations);
		Assert.assertEquals(TotalLocations, NumOflocations);
	}

	/**
	 * To get List of vendors from UI
	 */
	public void getvendorList() {
		scrollByElement(Tier1VendorTableHeader);
		ArrayList<String> VendorList = new ArrayList<String>();
		int size = VendorTable.size();
		System.out.println("The size of table is :" + size);
		for (int i = 1; i <= size; i++) {
			List<WebElement> TableRow = driver
					.findElements(By.xpath("(//table[@id='tier1-table'])[" + i + "]//tbody//tr"));
			int rows_table = TableRow.size();
			System.out.println("Number of rows is :" + rows_table);
			for (int row = 1; row <= rows_table; row++) {
				String vendorname = driver
						.findElement(By.xpath("(//table[@id='tier1-table'])[" + i + "]//tbody//tr[" + row + "]//td[1]"))
						.getText();
				VendorList.add(vendorname);
			}
		}
		System.out.println("The VendorList contains" + VendorList);
	}



	/**
	 * Verify status of each vendor
	 * @param Vendor
	 * @param XLStatus
	 * @param row
	 * @param soft
	 * @throws Exception 
	 */
	public void verifyStatus(String Vendor, String XLStatus, int row, SoftAssert soft, String LocationNumber) throws Exception {
		String UIstatus = "";
		List<WebElement> vendortablerow = driver.findElements(
				By.xpath("(//tbody//tr[@role='row'])[" + row + "]//div[@class='table-listing-details']//tbody//tr"));
		int size = vendortablerow.size();
		System.out.println("The size of vendor table is :" + size);
			WebElement x = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + row
					+ "]//div[@class='table-listing-details']//tbody//td[contains(text(),'" + Vendor + "')]"));
			scrollByElement(x);
			if (x.isDisplayed()) {
				UIstatus = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + row
						+ "]//div[@class='table-listing-details']//tbody//td[contains(text(),'" + Vendor
						+ "')]//following-sibling::td[1]")).getAttribute("innerText");
				System.out.println("Status of Vendor is :" + UIstatus);
				BaseClass.addEvidence(CurrentState.getDriver(), "Verify vendors", "yes");
				soft.assertEquals(UIstatus, XLStatus,"The Location Number is "+LocationNumber+" The Vendor is "+Vendor+" UI Status is "+UIstatus+" and XL Status is "+XLStatus+"");
			} else {
				System.out.println("Vendor is not displayed");
			}
	}

	/**
	 * Get row number for the provided location Number
	 * @param LocationNumber
	 * @return
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("null")
	public int getLocationNumberRowNum(String LocationNumber) throws ParseException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(SyndicationLocTable));
		int row = 0;
		String celText = null;
		Outer: if (SyndicationLocTable.isDisplayed()) {
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			String entriesText = SyndTableInfo.getText();
			entriesText = entriesText.substring(entriesText.indexOf("("));
			clickelement(Page_First);
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(SyndicationLocTable);
					int size = driver.findElements(By.xpath("//tbody//tr[@role='row']")).size();
					System.out.println("The size is :" + size);
					for (row = 1; row <= size; row++) {
						celText = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + (row) + "]//td[1]"))
								.getAttribute("innerText");
						if (celText.contains(LocationNumber)) {
							WebElement s = driver.findElement(
									By.xpath("(//table[@id='syndication-table']//a[@class='anchor-slide'])[" + (row)
											+ "]//span"));
							s.click();
							System.out.println("The row number is :" + row);
							break Outer;
						}
					}
					if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			}
		} else {
			Assert.assertFalse(false, "Location Number is not available");
		}
		System.out.println("Row Number for : " + row);
		return row;
	}

	/**
	 * Verify Date Submitted for each vendors
	 * @param row
	 * @param Vendor
	 * @param soft
	 * @throws ParseException
	 */
	public void verifydatesumbitted(int row, String Vendor, SoftAssert soft) throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		System.out.println(var);
		String today = ((JavascriptExecutor) driver)
				.executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
				.toString();
		System.out.println(today);
		Date todaydate = formats.parse(today);
		System.out.println(todaydate);
		String date = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + row
				+ "]//div[@class='table-listing-details']//tbody//td[contains(text(),'" + Vendor
				+ "')]//following-sibling::td[2]")).getAttribute("innerText");
		System.out.println("Date is :" + date);
		if(!date.equals("-")) {
		Date UIDate = formats.parse(date);
		System.out.println("UI Date is :" + UIDate);
		soft.assertEquals(todaydate, UIDate);
		}		
	}

	/**
	 * Verify Notes of vendors provided
	 * @param Vendor
	 * @param row
	 * @param soft
	 */
	public void verifyNotes(String Vendor, int row, SoftAssert soft) {
		String Notes;
		if (Vendor.equals("HERE") || Vendor.equals("Factual")) {
			Notes = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + row
					+ "]//div[@class='table-listing-details']//tbody//td[contains(text(),'" + Vendor
					+ "')]//following-sibling::td[3]")).getAttribute("innerText");
			System.out.println("The Notes is :" + Notes);
			soft.assertEquals(Notes, "Monthly Submissions");
		} else if (Vendor.equals("TomTom")) {
			Notes = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + row
					+ "]//div[@class='table-listing-details']//tbody//td[contains(text(),'" + Vendor
					+ "')]//following-sibling::td[3]")).getAttribute("innerText");
			System.out.println("The Notes is :" + Notes);
			soft.assertEquals(Notes, "Quarterly Submissions");
		} else if (Vendor.equals("Zomato")) {
			Notes = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + row
					+ "]//div[@class='table-listing-details']//tbody//td[contains(text(),'" + Vendor
					+ "')]//following-sibling::td[3])")).getAttribute("innerText");
			System.out.println("The Notes is :" + Notes);
			soft.assertEquals(Notes, "Weekly Submissions");
		}
	}
	
	/**
	 * Get row number for the provided location Number
	 * @param LocationNumber
	 * @return
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("null")
	public void VerifyLocationNumber(String LocationNumber) throws ParseException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(SyndicationLocTable));
		String celText = null;
		Outer: if (SyndicationLocTable.isDisplayed()) {
			scrollByElement(Last_Page);
			String n = Last_Page.getText();
			System.out.println("Last PageNumber in String is :" + n);
			int page = Integer.parseInt(n);
			System.out.println("Last Page Number is :" + page);
			String entriesText = SyndTableInfo.getText();
			entriesText = entriesText.substring(entriesText.indexOf("("));
			clickelement(Page_First);
			if (Page_Next.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					scrollByElement(SyndicationLocTable);
					int size = driver.findElements(By.xpath("//tbody//tr[@role='row']")).size();
					System.out.println("The size is :" + size);
					for (int row = 1; row <= size; row++) {
						celText = driver.findElement(By.xpath("(//tbody//tr[@role='row'])[" + (row) + "]//td[1]"))
								.getAttribute("innerText");
						if (celText.contains(LocationNumber)) {
							Assert.fail("Location Number exists but expected is Location Number should not exist");
							break Outer;
						}
					}
					if (Page_Next.isEnabled()) {
						scrollByElement(Page_Next);
						Page_Next.click();
						Thread.sleep(4000);
					}
				}
			}
		}
	}
	
	public void Syndicationhighlight() {
		reporthighlight(SyndicationPage, SyndicationSec);
	}
	
	public void resultperpage(SoftAssert soft) throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		ResultsperPage(soft, Entry, Resultperpage);
	}
	
	public void GoTo() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		waitForElement(gotopage, 10);
		scrollByElement(gotopage);
		GoTopage(gotopage);
	}
}