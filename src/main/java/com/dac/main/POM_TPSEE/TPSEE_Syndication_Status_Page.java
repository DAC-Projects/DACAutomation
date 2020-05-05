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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
	 * To get the details of location created in LPAD
	 * 
	 * @param LocationNumber
	 * @return
	 * @throws InterruptedException
	 */
	public List<String> getLocationDetailsLPAD(String LocationNumber) throws InterruptedException {
		ArrayList<String> VendorList = new ArrayList<String>();
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
					List<WebElement> rows_table = SyndicationLocTableRow;
					int rows_count = rows_table.size();
					for (int row = 0; row < rows_count; row++) {
						String celText = driver
								.findElement(By
										.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1) + "]//td[1]"))
								.getText();
						if (celText.contains(LocationNumber)) {
							WebElement s = driver.findElement(
									By.xpath("(//table[@id='syndication-table']//a[@class='anchor-slide'])[" + (row + 1)
											+ "]//span"));
							if (s.isDisplayed()) {
								s.click();
								List<WebElement> vendortablerow = driver
										.findElements(By.xpath("//table[@id='syndication-table']//tbody//tr["
												+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr"));
								int size = vendortablerow.size();
								for (int j = 1; j <= size; j++) {
									String Vendor = driver
											.findElement(By.xpath("//table[@id='syndication-table']//tbody//tr["
													+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr["
													+ (j + 1) + "]//td[1]"))
											.getText();
									System.out.println("The vendor is :" + Vendor);
									VendorList.add(Vendor);
									String Status = driver
											.findElement(By.xpath("//table[@id='syndication-table']//tbody//tr["
													+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr["
													+ (j + 1) + "]//td[2]"))
											.getText();
									System.out.println("The status of the vendor is :" + Status);
									Assert.assertEquals(Status, "In Progress");
									if (Vendor.equals("HERE") || Vendor.equals("Factual")) {
										String Notes = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[4]"))
												.getText();
										System.out.println("The Notes is :" + Notes);
										Assert.assertEquals(Notes, "Monthly Submissions");
									} else if (Vendor.equals("TomTom")) {
										String Notes = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[4]"))
												.getText();
										System.out.println("The Notes is :" + Notes);
										Assert.assertEquals(Notes, "Quarterly Submissions");
									} else if (Vendor.equals("Zomato")) {
										String Notes = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[4]"))
												.getText();
										System.out.println("The Notes is :" + Notes);
										Assert.assertEquals(Notes, "Weekly Submissions");
										break Outer;
									}
								}if (Page_Next.isEnabled()) {
									scrollByElement(Page_Next);
									Page_Next.click();
									Thread.sleep(4000);
								}
								
							}
						}
					}
				}
			}
		}
		return VendorList;
	}

	/**
	 * To verify status of vendors manually/api processed
	 * 
	 * @param LocationNumber
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public void getLocationDetailsDTCManualApi(String LocationNumber) throws InterruptedException, ParseException {
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
					List<WebElement> rows_table = SyndicationLocTableRow;
					int rows_count = rows_table.size();
					for (int row = 0; row < rows_count; row++) {
						String celText = driver
								.findElement(By
										.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1) + "]//td[1]"))
								.getText();
						if (celText.contains(LocationNumber)) {
							WebElement s = driver.findElement(
									By.xpath("(//table[@id='syndication-table']//a[@class='anchor-slide'])[" + (row + 1)
											+ "]//span"));
							if (s.isDisplayed()) {
								s.click();
								List<WebElement> vendortablerow = driver
										.findElements(By.xpath("//table[@id='syndication-table']//tbody//tr["
												+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr"));
								int size = vendortablerow.size();
								for (int j = 1; j <= size; j++) {
									String Vendor = driver
											.findElement(By.xpath("//table[@id='syndication-table']//tbody//tr["
													+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr["
													+ (j + 1) + "]//td[1]"))
											.getText();
									System.out.println("The vendor is :" + Vendor);
									String Status = driver
											.findElement(By.xpath("//table[@id='syndication-table']//tbody//tr["
													+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr["
													+ (j + 1) + "]//td[2]"))
											.getText();
									System.out.println("The status of the vendor is :" + Status);
									if (Vendor.equals("Bing") || Vendor.equals("Facebook") || Vendor.equals("Factual")
											|| Vendor.equals("Foursquare") || Vendor.equals("Google")
											|| Vendor.equals("TripAdvisor")) {
										Assert.assertEquals(Status, "Submitted");
										String var = ((JavascriptExecutor) driver)
												.executeScript("return window.dateFormat.shortTemplate.PlainHtml")
												.toString();
										SimpleDateFormat formats = new SimpleDateFormat(var);
										System.out.println(var);
										String today = ((JavascriptExecutor) driver).executeScript(
												"return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
												.toString();
										System.out.println(today);
										Date todaydate = formats.parse(today);
										System.out.println(todaydate);
										String date = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[3]"))
												.getText();
										System.out.println("Date is :" + date);
										Date UIDate = formats.parse(date);
										System.out.println("UI Date is :" + UIDate);
										Assert.assertEquals(todaydate, UIDate);
									} else if (Vendor.equals("Apple") || Vendor.equals("HERE")
											|| Vendor.equals("Zomato") || Vendor.equals("TomTom")) {
										Assert.assertEquals(Status, "In Progress");
									}
									if (Vendor.equals("HERE") || Vendor.equals("Factual")) {
										String Notes = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[4]"))
												.getText();
										System.out.println("The Notes is :" + Notes);
										Assert.assertEquals(Notes, "Monthly Submissions");
									} else if (Vendor.equals("TomTom")) {
										String Notes = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[4]"))
												.getText();
										System.out.println("The Notes is :" + Notes);
										Assert.assertEquals(Notes, "Quarterly Submissions");
									} else if (Vendor.equals("Zomato")) {
										String Notes = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[4]"))
												.getText();
										System.out.println("The Notes is :" + Notes);
										Assert.assertEquals(Notes, "Weekly Submissions");
										break Outer;
									}
								}if (Page_Next.isEnabled()) {
									scrollByElement(Page_Next);
									Page_Next.click();
									Thread.sleep(4000);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * To verify status of vendors {"Apple,Zomato,HERE,TomTom"}
	 * 
	 * @param LocationNumber
	 * @param vendor
	 * @throws ParseException
	 * @throws InterruptedException
	 */
	public void getLocationDetailsDTCVendors(String LocationNumber, String vendor)
			throws ParseException, InterruptedException {
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
					List<WebElement> rows_table = SyndicationLocTableRow;
					int rows_count = rows_table.size();
					for (int row = 0; row < rows_count; row++) {
						String celText = driver
								.findElement(By
										.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1) + "]//td[1]"))
								.getText();
						if (celText.contains(LocationNumber)) {
							WebElement s = driver.findElement(
									By.xpath("(//table[@id='syndication-table']//a[@class='anchor-slide'])[" + (row + 1)
											+ "]//span"));
							if (s.isDisplayed()) {
								s.click();
								List<WebElement> vendortablerow = driver
										.findElements(By.xpath("//table[@id='syndication-table']//tbody//tr["
												+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr"));
								int size = vendortablerow.size();
								System.out.println("The size of vendor table is :" + size);
								for (int j = 1; j <= size - 1; j++) {
									String Vendor = driver
											.findElement(By.xpath("//table[@id='syndication-table']//tbody//tr["
													+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr["
													+ (j + 1) + "]//td[1]"))
											.getText();
									System.out.println("The vendor is :" + Vendor);
									String Status = driver
											.findElement(By.xpath("//table[@id='syndication-table']//tbody//tr["
													+ (row + 1) + "]//div[@class='table-listing-details']//tbody//tr["
													+ (j + 1) + "]//td[2]"))
											.getText();
									System.out.println("The status of the vendor is :" + Status);
									if (Vendor.equals(vendor)) {
										Assert.assertEquals(Status, "Submitted");
										String var = ((JavascriptExecutor) driver)
												.executeScript("return window.dateFormat.shortTemplate.PlainHtml")
												.toString();
										SimpleDateFormat formats = new SimpleDateFormat(var);
										System.out.println(var);
										String today = ((JavascriptExecutor) driver).executeScript(
												"return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())")
												.toString();
										System.out.println(today);
										Date todaydate = formats.parse(today);
										System.out.println(todaydate);
										String date = driver.findElement(
												By.xpath("//table[@id='syndication-table']//tbody//tr[" + (row + 1)
														+ "]//div[@class='table-listing-details']//tbody//tr[" + (j + 1)
														+ "]//td[3]"))
												.getText();
										System.out.println("Date is :" + date);
										Date UIDate = formats.parse(date);
										System.out.println("UI Date is :" + UIDate);
										Assert.assertEquals(todaydate, UIDate);
										break Outer;
									}
								}if (Page_Next.isEnabled()) {
									scrollByElement(Page_Next);
									Page_Next.click();
									Thread.sleep(4000);
								}
							}
						} else {
							System.out.println("Vendor not displayed");
						}
					}
					
				}
			}
		}
	}
}
