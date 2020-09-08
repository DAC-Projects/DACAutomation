package com.dac.main.POM_SA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class Reviews_Feed extends SA_Abstarct_Methods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	SoftAssert soft = new SoftAssert();
	List<String> ReferenceNumber = new ArrayList<String>();
	List<String> URL = new ArrayList<String>();
	List<String> BusinssName = new ArrayList<String>();
	List<String> Location = new ArrayList<String>();
	String Vendor = null;
	String Rating = null;
	String ONResponse;
	String contentsel;
	String[] vendorlist;
	String[] Ratinglist;
	String[] ResponseList;
	String[] ContentList;
	String[] taglist;
	String tag = null;
	Select select;

	public Reviews_Feed(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='btnReportExport']")
	private WebElement LocationExport;

	@FindBy(xpath = "(//*[@class='reviewEnhancement'])[1]")
	private WebElement ReviewSection;

	@FindBy(xpath = "//div[@class='business-info']//div//strong")
	private List<WebElement> BusinessName;

	@FindBy(xpath = "//div[@id='paginationInfo']")
	private WebElement Entry;

	@FindBy(xpath = "(//span[@id='review_totalcounts'])[1]")
	private WebElement ReviewCount;

	@FindBy(xpath = "//*[@id='Review']//dl[contains(@class,'source-filter')]")
	private WebElement SourceFilter;

	@FindBy(xpath = "(//input[@class='form-control keywordSearch'])[1]")
	private WebElement SearchKeyword;

	@FindBy(xpath = "//*[@id='Review']//dl[contains(@class,'dropdown star-filter')]")
	private WebElement RatingFilter;

	@FindBy(xpath = "(//div[@class='searchByContent'])[1]")
	private WebElement ContentSel;

	@FindBy(xpath = "//div[@class='ownerResponse']")
	private WebElement Response;

	@FindBy(xpath = "//*[@id='Review']//*[@id='filter-search']")
	private WebElement advanceSearch;

	@FindBy(xpath = "//div[@class='input-icon input-icon-right']//input[contains(@class,'ui-widget-content ui-autocomplete-input')]")
	private WebElement TagInput;

	@FindBy(xpath = "//ul[contains(@id,'review-tags')]")
	private WebElement TagEntry;

	@FindBy(xpath = "//*[@id='Review']//*[@class='review-content']")
	private List<WebElement> Reviews;

	@FindBy(xpath = "//a[@id='viewListingLink']")
	private List<WebElement> ListingLink;

	@FindBy(xpath = "//*[@id='Review']//*[@class='business-info']")
	private List<WebElement> ReviewInfo;

	@FindBy(xpath = "//*[@id='Review']//*[@class='sentiment-score-filter']")
	private WebElement SentimentTab;

	@FindBy(xpath = "//select[@id='sorting']")
	private WebElement Sort;

	@FindBy(xpath = "//div[@class='ui fluid dropdown selection sort-dropdown']")
	private WebElement clicksort;

	@FindBy(xpath = "//div[@class='date-text']")
	private WebElement dates;

	@FindBy(xpath = "//div[@class='reference-code']//div[2]")
	private WebElement refcode;

	@FindBy(xpath = "//li[@id='reviews-new']")
	private WebElement ReviewsSection;

	@FindBy(xpath = "//li[@id='reviews_feed']")
	private WebElement Review_FeedPage;

	/*-------------------------Pagination-----------------------*/

	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationLast;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	/*-------------------------Pagination-----------------------*/

	/**
	 * Export Reviews
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public void LocationExport() throws FileNotFoundException, IOException, InterruptedException {
		scrollByElement(LocationExport);
		clickelement(LocationExport);
		Thread.sleep(3000);
		convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + LocationDataExport));
	}

	/**
	 * To Get the List of reference code , Links , Business name , Source and
	 * compare with XL
	 * 
	 * @throws Exception
	 * 
	 */

	public void CompareReviewTableDatawithExport(String Text) throws Exception {

		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int numberofentries = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + numberofentries);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					List<WebElement> rows_table = BusinessName;
					int rows_count = rows_table.size();
					for (int row = 1; row <= rows_count; row++) {
						scrollByElement(ReviewSection);
						String text = driver
								.findElement(By.xpath("(//*[@id='Review']//div[@class='form-group col-xs-12'])[" + row
										+ "]//*[@id='viewListingLink']"))
								.getAttribute("href");
						Thread.sleep(2000);
						if (!text.contains("yelp")) {
							String BName = driver.findElement(
									By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])["
											+ row + "]//div[@class='business-info']//div//strong"))
									.getText();
							System.out.println(BName);
							Thread.sleep(2000);
							String ReferenceNum = driver.findElement(
									By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])["
											+ row + "]//div[@class='reference-code']//div[2]"))
									.getText();
							System.out.println(ReferenceNum);
							Thread.sleep(2000);
							String LinksUrl = driver.findElement(
									By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])["
											+ row + "]//*[@id='viewListingLink']"))
									.getAttribute("href");
							System.out.println(LinksUrl);
							Thread.sleep(2000);
							String loc = driver.findElement(By.xpath("//*[contains(@class,'myList1')]")).getText()
									.toLowerCase();
							if (!Text.equalsIgnoreCase("Null") || !Text.equalsIgnoreCase("All States")) {
								String locdetails = driver
										.findElement(By.xpath("(//*[@class='location-separator'])[" + row + "]"))
										.getText().toLowerCase();
								soft.assertTrue(locdetails.contains(loc), "Found");
							} else {
								System.out.println("No filter applied");
							}
							BusinssName.add(BName);
							ReferenceNumber.add(ReferenceNum);
							URL.add(LinksUrl);
							// System.out.println(text);
						} else {
							System.out.println("Yelp is displayed");
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
			System.out.println("Business Name in Review : " + BusinssName);
			System.out.println("Reference Code in Review :" + ReferenceNumber);
			System.out.println("Link Listing in Review :" + URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String chromepath = "./downloads/chromeLocationDataExport.xlsx";
			String IEpath = "./downloads/IELocationDataExport.xlsx";
			String FFpath = "./downloads/FFLocationDataExport.xlsx";
			List<String> XLBname = new ArrayList<String>();
			List<String> XLRefCode = new ArrayList<String>();
			List<String> XLURL = new ArrayList<String>();
			List<String> XLSource = new ArrayList<String>();

			if (CurrentState.getBrowser().equals("chrome")) {
				XLBname = GetDataUsingColName(chromepath, "Business Name");
				Thread.sleep(1000);
				XLRefCode = GetDataUsingColName(chromepath, "Reference Code");
				Thread.sleep(1000);
				XLURL = GetDataUsingColName(chromepath, "URL");
				Thread.sleep(1000);
				XLSource = GetDataUsingColName(chromepath, "Source");
				Thread.sleep(1000);
			}
			if (CurrentState.getBrowser().equals("IE")) {
				XLBname = GetDataUsingColName(IEpath, "Business Name");
				Thread.sleep(1000);
				XLRefCode = GetDataUsingColName(IEpath, "Reference Code");
				Thread.sleep(1000);
				XLURL = GetDataUsingColName(IEpath, "URL");
				Thread.sleep(1000);
				XLSource = GetDataUsingColName(IEpath, "Source");
				Thread.sleep(1000);
			}
			if (CurrentState.getBrowser().equals("Firefox")) {
				XLBname = GetDataUsingColName(FFpath, "Business Name");
				Thread.sleep(1000);
				XLRefCode = GetDataUsingColName(FFpath, "Reference Code");
				Thread.sleep(1000);
				XLURL = GetDataUsingColName(FFpath, "URL");
				Thread.sleep(1000);
				XLSource = GetDataUsingColName(FFpath, "Source");
				Thread.sleep(1000);
			}
			soft.assertEquals(XLBname.size(), BusinssName.size());
			// Assert.assertEquals(expected, actual);
			soft.assertEquals(XLBname, BusinssName);
			soft.assertEquals(XLRefCode.size(), ReferenceNumber.size());
			soft.assertEquals(XLRefCode, ReferenceNumber);
			soft.assertEquals(XLURL.size(), URL.size());
			soft.assertEquals(XLURL, URL);
			soft.assertEquals(XLSource.size(), URL.size());
			if (XLSource.size() == URL.size()) {
				for (int i = 0; i <= XLSource.size() - 1; i++) {
					XLSource.get(i).contains(URL.get(i));
				}
			}
			soft.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verify Review count and Number of entries in a table
	 */
	public void verifyReviewCount() {
		waitForElement(ReviewCount, 10);
		int RCount = Integer.parseInt(ReviewCount.getText());
		System.out.println("The Review Count is :" + RCount);
		int count = SANumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + count);
		Assert.assertEquals(count, RCount);
	}

	/**
	 * To get the source name
	 * 
	 * @param source
	 * @return
	 */
	public String getSource(String source) {
		if (source.contains("google")) {
			return "google";
		} else if (source.contains("facebook")) {
			return "facebook";
		} else if (source.contains("foursquare")) {
			return "foursquare";
		} else if (source.contains("superpages")) {
			return "superpages";
		} else if (source.contains("yp.ca")) {
			return "yp.ca";
		} else if (source.contains("yp.com")) {
			return "yp.com";
		} else if (source.contains("yelp")) {
			return "yelp";
		} else {
			return "null";
		}
	}

	/**
	 * To select source from dropdown
	 * 
	 * @throws Exception
	 */
	public void SelectSource() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
		for (int k = 1; k <= wb.getRowCount(); k++) {
			String vendors = wb.getCellValue(k, wb.seacrh_pattern("Source", 0).get(0).intValue());
			System.out.println("The vendors listed are :" + vendors);
			vendorlist = vendors.split(",");
			int size = vendorlist.length;
			System.out.println("The size of list is :" + size);
			for (int i = 0; i <= size - 1; i++) {
				Vendor = vendorlist[i];
				System.out.println("The vendor is :" + Vendor);
				if (!Vendor.equalsIgnoreCase("null")) {
					scrollByElement(SourceFilter);
					clickelement(SourceFilter);
					action.moveToElement(driver.findElement(
							By.xpath("(//*[@id='filter-area']//input[@title='" + Vendor.trim() + "'])[1]"))).click()
							.build().perform();
					System.out.println("Vendor selected" + Vendor);
					waitUntilLoad(driver);
					Thread.sleep(5000);
					clickelement(SourceFilter);
				} else {
					System.out.println("No Vendor selected");
				}
			}
			if (!Vendor.equalsIgnoreCase("null")) {
				comparesourcewithreviews(vendors);
			} else {
				System.out.println("No vendors selected");
			}
		}
	}

	/**
	 * To compare the source with Reviews
	 */
	public void comparesourcewithreviews(String Vendor) {
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					int size = Reviews.size();
					System.out.println(size);
					for (int j = 1; j <= size; j++) {
						String sourcenme = driver
								.findElement(
										By.xpath("(//*[@id='Review']//div//img[@class = 'source-thumb'])[" + j + "]"))
								.getAttribute("src").toLowerCase();
						System.out.println(sourcenme);
						String vendorname = getSource(sourcenme);
						System.out.println("The Vendor name is :" + vendorname);
						ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
						for (int k = 1; k <= wb.getRowCount(); k++) {
							String vendors = wb.getCellValue(k, wb.seacrh_pattern("Source", 0).get(0).intValue())
									.toLowerCase();
							System.out.println("The vendors listed are :" + vendors);
							soft.assertTrue(vendors.contains(vendorname), "vendor is :" + vendorname);
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollByElement(SourceFilter);
		clickelement(SourceFilter);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[1]"))).click()
				.build().perform();
		clickelement(SourceFilter);
		waitUntilLoad(driver);
		soft.assertAll();
	}

	/**
	 * Select Star rating from dropdown and compare
	 * 
	 * @throws Exception
	 */
	public void SelectRating() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
		for (int k = 1; k <= wb.getRowCount(); k++) {
			String rating = wb.getCellValue(k, wb.seacrh_pattern("Rating", 0).get(0).intValue());
			System.out.println("The vendors listed are :" + rating);
			Ratinglist = rating.split(",");
			int size = Ratinglist.length;
			System.out.println("The size of list is :" + size);
			for (int i = 0; i <= size - 1; i++) {
				Rating = Ratinglist[i];
				System.out.println("The vendor is :" + Rating);
				if (!Rating.equalsIgnoreCase("null")) {
					scrollByElement(RatingFilter);
					clickelement(RatingFilter);
					action.moveToElement(driver.findElement(
							By.xpath("(//*[@id='filter-area']//input[@title='" + Rating.trim() + "'])[1]"))).click()
							.build().perform();
					System.out.println("Rating selected" + Rating);
					BaseClass.addEvidence(driver, "Select Rating", "yes");
					waitUntilLoad(driver);
					Thread.sleep(5000);
					clickelement(RatingFilter);
				} else {
					System.out.println("No Rating selected");
				}
			}
			if (!Rating.equalsIgnoreCase("null")) {
				compareratingwithreviews();
			} else {
				System.out.println("No rating selected");
			}
		}
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[2]"))).click()
				.build().perform();
		soft.assertAll();
	}

	/**
	 * Compare Rating with Reviews in the table
	 * 
	 * @throws Exception
	 */
	public void compareratingwithreviews() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			waitForElement(paginationLast, 10);
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						int size = Reviews.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							scrollByElement(driver
									.findElement(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]")));
							if (!Rating.equals("All") || !Rating.equals("Recommended")
									|| !Rating.equals("Not Recommended") || !Rating.equals("No Rating")) {
								List<WebElement> starrate = driver
										.findElements(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j
												+ "]//i[contains(@class,'yellow')]"));
								BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
								int starratesize = starrate.size();
								String sratesize = Integer.toString(starratesize);
								System.out.println("The starrate size is :" + sratesize);
								for (int k = 1; k <= wb.getRowCount(); k++) {
									String XLrating = wb.getCellValue(k,
											wb.seacrh_pattern("Rating", 0).get(0).intValue());
									System.out.println("The vendors listed are :" + XLrating);
									soft.assertTrue(XLrating.contains(sratesize),
											"The rating from UI is " + sratesize + " is not present in " + XLrating);
								}
							} else if (Rating.equals("Recommended")) {
								scrollByElement(driver.findElement(
										By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]")));
								String Recom = driver
										.findElement(By
												.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]//span"))
										.getText();
								BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
								System.out.println("The text is :" + Recom);
								if (Recom.equals("Recommends")) {
									for (int k = 1; k <= wb.getRowCount(); k++) {
										String XLrating = wb.getCellValue(k,
												wb.seacrh_pattern("Rating", 0).get(0).intValue());
										System.out.println("The vendors listed are :" + XLrating);
										soft.assertTrue(XLrating.contains("Recommended"),
												"The rating from UI is " + Recom + " is not present in " + XLrating);
									}
								} else {
									soft.fail("Selected Rating is Recommended but not found");
								}
							} else if (Rating.equals("Not Recommended")) {
								scrollByElement(driver.findElement(
										By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]")));
								String NotRecom = driver
										.findElement(By
												.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]//span"))
										.getText();
								BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
								System.out.println("The text is :" + NotRecom);
								if (NotRecom.equals("Doesn't Recommend")) {
									for (int k = 1; k <= wb.getRowCount(); k++) {
										String XLrating = wb.getCellValue(k,
												wb.seacrh_pattern("Rating", 0).get(0).intValue());
										System.out.println("The vendors listed are :" + XLrating);
										soft.assertTrue(XLrating.contains("Not Recommended"),
												"The rating from UI is " + NotRecom + " is not present in " + XLrating);
									}
								} else {
									soft.fail("Selected Rating is Not Recommended but not found");
								}
							} else if (Rating.equals("No Rating")) {
								WebElement UIstarrate = driver.findElement(
										By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]"));
								BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
								soft.assertTrue(!UIstarrate.isDisplayed());
							}
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data available");
		}
	}

	/**
	 * To select the response type
	 * 
	 * @throws Exception
	 */
	public void SelectResponse() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String response = wb.getCellValue(i, wb.seacrh_pattern("OResponse", 0).get(0).intValue());
			System.out.println("The vendors listed are :" + response);
			ResponseList = response.split(",");
			int size = ResponseList.length;
			System.out.println("The response list size is :" + size);
			for (int j = 0; j <= size - 1; j++) {
				ONResponse = ResponseList[j];
				System.out.println("The response selected is :" + ONResponse);
				if (!ONResponse.equals("null")) {
					scrollByElement(Response);
					clickelement(Response);
					driver.findElement(
							By.xpath("//div[@class='ownerResponse']//div[contains(text(),'" + ONResponse + "')]"))
							.click();
					JSWaiter.waitJQueryAngular();
					BaseClass.addEvidence(driver, "Test to select the response type", "yes");
					verifyselectedResponse(ONResponse);
				} else {
					System.out.println("No response selected");
				}
			}
		}
		scrollByElement(Response);
		clickelement(Response);
		driver.findElement(By.xpath("//div[@class='ownerResponse']//div[contains(text(),'All')]")).click();
		soft.assertAll();
	}

	/**
	 * Verify response selected in the reviews table
	 * 
	 * @param response
	 */
	public void verifyselectedResponse(String response) {
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						int size = ListingLink.size();
						System.out.println("The size is :" + size);
						for (int j = 1; j <= size; j++) {
							if (response.equalsIgnoreCase("Response")) {
								WebElement reponsebox = driver
										.findElement(By.xpath("(//div[@class='owner-responses rrm'])[" + j + "]"));
								scrollByElement(reponsebox);
								soft.assertTrue(reponsebox.isDisplayed(), "WebElement is not visible");
							} else if (response.equalsIgnoreCase("Draft Response")) {
								WebElement draftresponse = driver
										.findElement(By.xpath("(//div[@class='card-header']//span)[" + j + "]"));
								scrollByElement(draftresponse);
								String restext = draftresponse.getText();
								System.out.println("The text is :" + restext);
								soft.assertEquals(restext, "Draft");
							} else if (response.equalsIgnoreCase("Assisted Response")) {
								WebElement assistresponse = driver
										.findElement(By.xpath("(//div[@class='owner-name'])[" + j + "]"));
								scrollByElement(assistresponse);
								String assisresponse = assistresponse.getText();
								System.out.println("The response type is :" + assisresponse);
								soft.assertEquals(assisresponse, "Response Assistant");
							} else if (response.equalsIgnoreCase("No Response")) {
								WebElement reponsebox = driver
										.findElement(By.xpath("(//div[@class='owner-responses rrm'])[" + j + "]"));
								WebElement draftresponse = driver
										.findElement(By.xpath("(//div[@class='card-header']//span)[" + j + "]"));
								WebElement assistresponse = driver
										.findElement(By.xpath("(//div[@class='owner-name'])[" + j + "]"));
								soft.assertTrue(
										!(reponsebox.isDisplayed())
												&& !(draftresponse.isDisplayed() && !(assistresponse.isDisplayed())),
										"One of the elements is displayed");
							}
						}
						BaseClass.addEvidence(driver, "Test to verify response type selected", "yes");
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data available");
		}

	}

	/**
	 * \
	 *
	 * Test to enter keyword
	 * 
	 * @throws Exception
	 */
	public void KeywordSearch() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Other_Filters");
		waitForElement(advanceSearch, 10);
		clickelement(advanceSearch);
		waitForElement(SearchKeyword, 10);
		Thread.sleep(5000);
		scrollByElement(SearchKeyword);
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String Keyword = wb.getCellValue(i, wb.seacrh_pattern("Keywords", 0).get(0).intValue());
			clickelement(SearchKeyword);
			SearchKeyword.sendKeys(Keyword);
			SearchKeyword.sendKeys(Keys.ENTER);
			JSWaiter.waitJQueryAngular();
			BaseClass.addEvidence(driver, "Enter keyword to search", "yes");
			verifyKeyword(Keyword);
		}
		clickelement(SearchKeyword);
		SearchKeyword.clear();
		JSWaiter.waitJQueryAngular();
		clickelement(advanceSearch);
		soft.assertAll();
	}

	/**
	 * Test to verify entered keyword
	 * 
	 * @param Key
	 */
	public void verifyKeyword(String Key) {
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						int size = Reviews.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							WebElement UITextele = driver
									.findElement(By.xpath("(//div[@class='review-content'])[" + j + "]"));
							scrollByElement(UITextele);
							String UIText = UITextele.getText();
							System.out.println("The Review is :" + UIText);
							soft.assertTrue(UIText.contains(Key),
									"Text of page " + i + " and row " + j + " doesnot contain" + Key);
						}
						BaseClass.addEvidence(driver, "Test to verify keyword", "yes");
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data available for selected Keyword" + Key);
		}
	}

	/**
	 * To search with tag
	 * 
	 * @throws Exception
	 */
	public void VerifyTag() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
		waitForElement(advanceSearch, 10);
		clickelement(advanceSearch);
		waitForElement(TagInput, 10);
		for (int k = 1; k <= wb.getRowCount(); k++) {
			String tags = wb.getCellValue(k, wb.seacrh_pattern("Tag", 0).get(0).intValue());
			taglist = tags.split(",");
			int size = taglist.length;
			System.out.println("The tag list size is :" + size);
			for (int i = 0; i <= size - 1; i++) {
				tag = taglist[i];
				System.out.println("The tag is :" + tag);
				if (!tag.equalsIgnoreCase("null")) {
					clickelement(TagInput);
					TagInput.sendKeys(tag);
					TagInput.sendKeys(Keys.ENTER);
					scrollByElement(TagEntry);
				} else {
					System.out.println("No tags selected");
				}
			}
			if (!tags.equalsIgnoreCase("null")) {
				compareTagswithreviews(tag);
			} else {
				System.out.println("No tags selected");
			}
		}
		soft.assertAll();
		clickelement(advanceSearch);
	}

	/**
	 * To Compare tag with reviews
	 * 
	 * @param tag
	 */
	public void compareTagswithreviews(String tag) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer
				.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" + lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= lastpage; i++) {
					int size = Reviews.size();
					System.out.println(size);
					for (int j = 1; j <= size; j++) {
						String Tagname = driver.findElement(By.xpath("(//ul[contains(@id,'review-tags')])[" + j + "]"))
								.getText();
						System.out.println(Tagname);
						ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
						for (int k = 1; k <= wb.getRowCount(); k++) {
							String tags = wb.getCellValue(k, wb.seacrh_pattern("Tag", 0).get(0).intValue());
							taglist = tags.split(",");
							int tsize = taglist.length;
							System.out.println("The tag list size is :" + size);
							for (int l = 0; l <= tsize - 1; l++) {
								tag = taglist[l];
								System.out.println("The tag is :" + tag);
								if (Tagname.contains(tag)) {
									soft.assertTrue(true);
								}
							}
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To apply Search tag filter
	 * 
	 * @param text
	 * @throws Exception
	 */
	public void applySentiment() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Sentiment_Filters");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String sentiment = wb.getCellValue(i, wb.seacrh_pattern("Sentiment", 0).get(0).intValue());
			System.out.println("The sentiment is :" + sentiment);
			if (sentiment == null || sentiment.equalsIgnoreCase("none"))
				sentiment = "All";
			try {
				waitUntilLoad(driver);
				waitForElement(advanceSearch, 10);
				scrollByElement(advanceSearch);
				clickelement(advanceSearch);
				if (!sentiment.equals("None")) {
					scrollByElement(SentimentTab);
					clickelement(SentimentTab);
					driver.findElement(By
							.xpath("//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"
									+ sentiment + "')]"))
							.click();
					waitUntilLoad(driver);
					clickelement(advanceSearch);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			compareSentimentswithreviews(sentiment, soft);
		}
		clickelement(advanceSearch);
		scrollByElement(SentimentTab);
		clickelement(SentimentTab);
		driver.findElement(
				By.xpath("//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), 'All')]"))
				.click();
		soft.assertAll();
	}

	/**
	 * To compare the source with Reviews
	 */
	public void compareSentimentswithreviews(String Text, SoftAssert soft) {
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		String ReviewsSentiment = null;
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			waitForElement(paginationLast, 10);
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						int size = Reviews.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							if (driver
									.findElement(By.xpath(
											"(//div[contains(@class, 'overall-sentiment-text overall ')])[" + j + "]"))
									.isDisplayed()) {
								String txt = driver.findElement(By.xpath(
										"(//div[contains(@class, 'overall-sentiment-text overall ')])[" + j + "]"))
										.getAttribute("class");
								if (txt.contains("lime")) {
									ReviewsSentiment = "Positive";
								} else if (txt.contains("green")) {
									ReviewsSentiment = "Positive";
								} else if (txt.contains("yellow")) {
									ReviewsSentiment = "Neutral";
								} else if (txt.contains("orange")) {
									ReviewsSentiment = "Negative";
								} else if (txt.contains("red")) {
									ReviewsSentiment = "Negative";
								} else if (txt.contains("grey")) {
									ReviewsSentiment = "Not Available";
								} else {
									System.out.println("No Data Available");
								}
								System.out.println(ReviewsSentiment);
								soft.assertTrue(ReviewsSentiment.toLowerCase().contains(Text.toLowerCase()));
							} else {
								System.out.println("WebElement is not displayed");
							}
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Method that returns data is available or not in the table
	 * 
	 * @return
	 */
	public boolean DataAvailable() {
		String text = driver.findElement(By.xpath("(//div[@class='form-group'])[2]")).getText();
		System.out.println("The text is :" + text);
		if (text.equals("No data available")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To verify Sort Function of Date
	 * 
	 * @param l
	 * @throws Exception
	 */
	public void verifyDateSort(int l) throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(l, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" + sorttype);
		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat").toString();
		System.out.println("The date format is :" + var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		List<Date> datelist = new ArrayList<>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							WebElement Udate = driver.findElement(By.xpath("(//div[@class='date-text'])[" + j + "]"));
							scrollByElement(Udate);
							String UIdate = Udate.getText();
							System.out.println("The UI Date in String is :" + UIdate);
							Date FinalUIDate = formats.parse(UIdate);
							System.out.println("Converted Date is :" + FinalUIDate);
							datelist.add(FinalUIDate);
							System.out.println("The Date List is :" + datelist);
							// Thread.sleep(3000);
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
				System.out.println("The final date List are:" + datelist);
				int datelistsize = datelist.size();
				System.out.println("The size of the list is :" + datelistsize);
				for (int k = 0; k <= datelistsize - 1; k++) {
					for (int m = k + 1; m <= datelistsize - 1; m++) {
						if (sorttype.equals("Date - Newest")) {
							System.out.println(
									"List start with :" + datelist.get(k) + "the next date is :" + datelist.get(k + 1));
							if ((datelist.get(k).compareTo(datelist.get(m)) >= 0)) {
								soft.assertTrue(true, "Date is in ascending order");
							} else {
								Assert.fail("Date is not ascending order" + "First Date is :" + datelist.get(k)
										+ "Second Date is :" + datelist.get(k++));
							}
						} else if (sorttype.equals("Date - Oldest")) {
							System.out.println(
									"List start with :" + datelist.get(k) + "the next date is :" + datelist.get(k + 1));
							if ((datelist.get(k).compareTo(datelist.get(m)) <= 0)) {
								soft.assertTrue(true, "Date is in descending order");
							} else {
								soft.fail("Date is not descending order" + "First Date is :" + datelist.get(k)
										+ "Second Date is :" + datelist.get(k++));
							}
						} else {
							System.out.println("Specified data is not selected");
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To verify Sort Function of Date by latest
	 * 
	 * @throws Exception
	 */
	public void VerifySortByNewest() throws Exception {
		verifyDateSort(1);
		soft.assertAll();
	}

	/**
	 * To verify Sort Function of Date by oldest
	 * 
	 * @throws Exception
	 */
	public void VerifySortByOldest() throws Exception {
		verifyDateSort(2);
		soft.assertAll();
	}

	/**
	 * To verify sort function of reference code
	 * 
	 * @throws Exception
	 */
	public void verifySortReferenceCode() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(5, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" + sorttype);
		List<String> RCode = new ArrayList<String>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							WebElement Refcode = driver
									.findElement(By.xpath("(//div[@class='reference-code']//div[2])[" + j + "]"));
							scrollByElement(Refcode);
							String referencecode = Refcode.getText();
							System.out.println("The reference code is :" + referencecode);
							RCode.add(referencecode);
							System.out.println("The ref code list is :" + RCode);
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
				System.out.println("The final list of ref code is :" + RCode);
				List<String> Rcodedup = new ArrayList<String>();
				Rcodedup.addAll(RCode);
				Collections.sort(Rcodedup);
				System.out.println("Duplicated list of ref code :" + Rcodedup);
				if (RCode.size() == Rcodedup.size()) {
					if (RCode.equals(Rcodedup)) {
						System.out.println("Lists are equal");
						soft.assertTrue(true);
					} else {
						System.out.println("Lists are not equal");
						Assert.fail("List is not sorted");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To verify sort function by source name
	 * 
	 * @throws Exception
	 */
	public void VerifySortSource() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(7, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" + sorttype);
		ArrayList<String> Source = new ArrayList<String>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							WebElement src = driver.findElement(
									By.xpath("(//*[@id='Review']//div//img[@class = 'source-thumb'])[" + j + "]"));
							scrollByElement(src);
							String sourcenme = src.getAttribute("src").toLowerCase();
							System.out.println(sourcenme);
							String vendorname = getSource(sourcenme);
							System.out.println("The Vendor name is :" + vendorname);
							Source.add(vendorname);
							System.out.println("The list contains :" + Source);
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
				System.out.println("The final list is :" + Source);
				List<String> Source2 = new ArrayList<String>();
				Source2.addAll(Source);
				Collections.sort(Source2);
				System.out.println("Sorted Lists :" + Source2);
				if (Source.size() == Source2.size()) {
					if (Source.equals(Source2)) {
						System.out.println("Lists are equal");
						soft.assertTrue(true);
					} else {
						System.out.println("Lists are not equal");
						Assert.fail("List is not sorted");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data available for selected value");

		}
		soft.assertAll();
	}

	/**
	 * Verify Sort Function by star rating
	 * 
	 * @param i
	 * @throws Exception
	 */
	public void verifyStarRattingSort(int i) throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(i, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" + sorttype);
		List<Integer> starvalue = new ArrayList<Integer>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int j = 1; j <= lastpage; j++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int k = 1; k <= size; k++) {
							scrollByElement(driver
									.findElement(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + k + "]")));
							List<WebElement> starrate = driver
									.findElements(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + k
											+ "]//i[contains(@class,'yellow')]"));
							int starratesize = starrate.size();
							System.out.println("The starrate size is :" + starratesize);
							starvalue.add(starratesize);
							System.out.println("The star value is :" + starvalue);
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
				System.out.println("The final list is :" + starvalue);
				if (sorttype.equalsIgnoreCase("Star Rating - Highest")) {
					System.out.println("Star Rating - Highest");
					for (int m = 1; m <= starvalue.size() - 1; m++) {
						System.out.println("entered for loop");
						if (((starvalue.get(m - 1)) >= (starvalue.get(m)))) {
							System.out.println("inside if" + starvalue.get(m - 1));
							soft.assertTrue(true);
						} else {
							System.out.println("inside else " + starvalue.get(m - 1));
							soft.fail("Compared first value" + starvalue.get(m - 1) + "with second value"
									+ starvalue.get(m));
						}
					}
				} else if (sorttype.equalsIgnoreCase("Star Rating - Lowest")) {
					for (int n = 1; n <= starvalue.size() - 1; n++) {
						if ((starvalue.get(n - 1) <= starvalue.get(n))) {
							System.out.println("inside if" + starvalue.get(n - 1));
							soft.assertTrue(true);
						} else {
							System.out.println("inside else " + starvalue.get(n - 1));
							soft.fail("Compared first value" + starvalue.get(n - 1) + "with second value"
									+ starvalue.get(n));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Verify Sort Function by star rating by high star
	 * 
	 * @throws Exception
	 */
	public void VerifyHighStar() throws Exception {
		verifyStarRattingSort(3);
		soft.assertAll();
	}

	/**
	 * Verify Sort Function by star rating by low star
	 * 
	 * @throws Exception
	 */
	public void VerifyLowStar() throws Exception {
		verifyStarRattingSort(4);
		soft.assertAll();
	}

	/**
	 * Verify report tab highlight
	 */
	public void Review_Feed_Highlight() {
		reporthighlight(Review_FeedPage, ReviewsSection);
	}

	/**
	 * Verify Sort Function using location name
	 * 
	 * @throws Exception
	 */
	public void verifySortLocationName() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(6, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" + sorttype);
		List<String> Location = new ArrayList<String>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							WebElement LocationName = driver
									.findElement(By.xpath("(//div[@class='business-info']//div//strong)[" + j + "]"));
							scrollByElement(LocationName);
							String location = LocationName.getText();
							System.out.println("The reference code is :" + location);
							Location.add(location);
							System.out.println("The ref code list is :" + Location);
							BaseClass.addEvidence(driver, "get location detail", "yes");
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
				System.out.println("The final list of ref code is :" + Location);
				List<String> LocNameup = new ArrayList<String>();
				LocNameup.addAll(Location);
				Collections.sort(LocNameup);
				System.out.println("Duplicated list of ref code :" + LocNameup);
				if (Location.size() == LocNameup.size()) {
					if (Location.equals(LocNameup)) {
						System.out.println("Lists are equal");
						soft.assertTrue(true);
					} else {
						System.out.println("Lists are not equal");
						Assert.fail("List is not sorted");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * Test to select filter from content filter
	 * 
	 * @throws Exception
	 */
	public void SelectContent() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String con = wb.getCellValue(i, wb.seacrh_pattern("Content", 0).get(0).intValue());
			System.out.println("The content listed are :" + con);
			ContentList = con.split(",");
			int size = ContentList.length;
			System.out.println("The content list size is :" + size);
			for (int j = 0; j <= size - 1; j++) {
				contentsel = ContentList[j];
				System.out.println("The response selected is :" + contentsel);
				if (!contentsel.equals("null")) {
					scrollByElement(ContentSel);
					clickelement(ContentSel);
					driver.findElement(By.xpath(
							"//div[@class='searchByContent']//div[contains(text(),'" + contentsel.trim() + "')]"))
							.click();
					JSWaiter.waitJQueryAngular();
					BaseClass.addEvidence(driver, "Test to select the response type", "yes");
					verifycontentfilter(contentsel);
				} else {
					System.out.println("No response selected");
				}
			}
		}
		scrollByElement(ContentSel);
		clickelement(ContentSel);
		driver.findElement(By.xpath("//div[@class='searchByContent']//div[contains(text(),'All')]")).click();
		soft.assertAll();

	}

	/**
	 * Method to verify content filter options in the table
	 * 
	 * @param content
	 */
	public void verifycontentfilter(String content) {
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			WebElement reponsebox;
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						int size = ListingLink.size();
						System.out.println("The size is :" + size);
						for (int j = 1; j <= size; j++) {
							if (content.equalsIgnoreCase("Content")) {
								reponsebox = driver
										.findElement(By.xpath("(//div[@class='review-content'])[" + j + "]"));
								scrollByElement(reponsebox);
								soft.assertTrue(reponsebox.isDisplayed(), "WebElement is not visible");
							} else if (content.equalsIgnoreCase("No Content")) {
								reponsebox = driver
										.findElement(By.xpath("(//div[@class='review-content'])[" + j + "]"));
								scrollByElement(reponsebox);
								soft.assertTrue(!(reponsebox.isDisplayed()), "WebElement is visible");
							}
						}
						BaseClass.addEvidence(driver, "Test to verify response type selected", "yes");
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							JSWaiter.waitJQueryAngular();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data available");
		}
	}
}
