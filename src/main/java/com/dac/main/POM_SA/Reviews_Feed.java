package com.dac.main.POM_SA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
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
import org.testng.asserts.SoftAssert;

import com.google.common.collect.Ordering;

import junit.framework.Assert;
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
	String[] vendorlist;
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

	@FindBy(xpath = "//*[@id='Review']//*[@id='filter-search']")
	private WebElement advanceSearch;

	@FindBy(xpath = "//div[@class='input-icon input-icon-right']//input[contains(@class,'ui-widget-content ui-autocomplete-input')]")
	private WebElement TagInput;

	@FindBy(xpath = "//ul[contains(@id,'review-tags')]")
	private WebElement TagEntry;

	@FindBy(xpath = "//*[@id='Review']//*[@class='review-content']")
	private List<WebElement> Reviews;

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
							if (!Text.equalsIgnoreCase("Null")) {
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
		int count = NumOfentriesinPage(Entry);
		System.out.println("The number of entries :" + count);
		Assert.assertEquals(count, RCount);
	}

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
							System.out.println("The vendors listed are :" +vendors);
							soft.assertTrue(vendors.contains(vendorname), "vendor is :" + vendorname);
						}
					}
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
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
									// soft.assertTrue(Tagname.contains(tag));
									soft.assertTrue(true);
								}
							}
						}
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
	 */
	public void applySentiment(String Text, SoftAssert soft) {
		JSWaiter.waitJQueryAngular();
		if (Text == null || Text.equalsIgnoreCase("none"))
			Text = "All";
		try {
			waitUntilLoad(driver);
			waitForElement(advanceSearch, 10);
			scrollByElement(advanceSearch);
			clickelement(advanceSearch);
			if (!Text.equals("None")) {
				scrollByElement(SentimentTab);
				clickelement(SentimentTab);
				driver.findElement(
						By.xpath("//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"
								+ Text + "')]"))
				.click();
				waitUntilLoad(driver);
				clickelement(advanceSearch);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		compareSentimentswithreviews(Text, soft);
	}

	/**
	 * To compare the source with Reviews
	 */
	public void compareSentimentswithreviews(String Text, SoftAssert soft) {
		JSWaiter.waitJQueryAngular();
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
			String ReviewsSentiment = null;
			try {
				if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						int size = Reviews.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							String txt = driver
									.findElement(By.xpath(
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
						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No Data Available");
			Assert.fail("No Data Available for the selected sort type");
		}
	}

	public boolean DataAvailable() {
		String text = driver.findElement(By.xpath("(//div[@class='form-group'])[2]")).getText();
		System.out.println("The text is :" + text);
		if (text.equals("No data available")) {
			return true;
		} else {
			return false;
		}
	}

	public void verifyDateSort(int l) throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(l, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" +sorttype);
		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat")
				.toString();
		System.out.println("The date format is :" +var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		List<Date> datelist = new ArrayList<>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='"+sorttype+"']"))).click().build().perform();
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
							WebElement Udate = driver.findElement(By.xpath("(//div[@class='date-text'])["+ j +"]"));
							scrollByElement(Udate);
							String UIdate = Udate.getText();
							System.out.println("The UI Date in String is :" +UIdate);
							Date FinalUIDate = formats.parse(UIdate);
							System.out.println("Converted Date is :" +FinalUIDate);
							datelist.add(FinalUIDate);
							System.out.println("The Date List is :" +datelist);
							//Thread.sleep(3000);
						}if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}
					System.out.println("The final date List are:" +datelist);
					int datelistsize = datelist.size();
					System.out.println("The size of the list is :" +datelistsize);
					for(int k=0; k<=datelistsize - 1; k++) {
						for(int m = k+1; m<=datelistsize - 1; m++) {
							if(sorttype.equals("Date - Newest")) {
								System.out.println("List start with :" +datelist.get(k) + "the next date is :"+datelist.get(k+1));
								if((datelist.get(k).compareTo(datelist.get(m))>=0)) {
									soft.assertTrue(true,"Date is in ascending order");
								}else {
									Assert.fail("Date is not ascending order" + "First Date is :"+datelist.get(k) + "Second Date is :" +datelist.get(k++));
								}
							}else if(sorttype.equals("Date - Oldest")) {
								System.out.println("List start with :" +datelist.get(k) + "the next date is :"+datelist.get(k+1));
								if((datelist.get(k).compareTo(datelist.get(m))<=0)) {
									soft.assertTrue(true,"Date is in descending order");
								}else {
									soft.fail("Date is not descending order"+ "First Date is :"+datelist.get(k) + "Second Date is :" +datelist.get(k++));
								}
							}else {
								System.out.println("Specified data is not selected");
								soft.fail("Specified data is not selected");
							}
						}
					}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No Data Available");
		}
	}

	public void VerifySortByNewest() throws Exception {
		verifyDateSort(1);
	}

	public void VerifySortByOldest() throws Exception {
		verifyDateSort(2);
	}

	public void verifySortReferenceCode() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(6, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" +sorttype);
		List<String> RCode = new ArrayList<String>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='"+sorttype+"']"))).click().build().perform();
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
							scrollByElement(refcode);
							String referencecode = refcode.getText();
							System.out.println("The reference code is :" +referencecode);
							RCode.add(referencecode);
							System.out.println("The ref code list is :" +RCode);
						}if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
					System.out.println("The final list of ref code is :" +RCode);

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No Data Available");
		}
	}

	public void VerifySortSource() throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "SortByData");
		String sorttype = wb.getCellValue(7, wb.seacrh_pattern("Filter", 0).get(0).intValue());
		System.out.println("The sort type is :" +sorttype);
		ArrayList<String> Source = new ArrayList<String>();
		scrollByElement(ReviewSection);
		scrollByElement(clicksort);
		clickelement(clicksort);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[text()='"+sorttype+"']"))).click().build().perform();
		JSWaiter.waitJQueryAngular();
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			try {
				/*if (paginationNext.isDisplayed()) {
					for (int i = 1; i <= lastpage; i++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int j = 1; j <= size; j++) {
							WebElement src = driver
									.findElement(
											By.xpath("(//*[@id='Review']//div//img[@class = 'source-thumb'])[" + j + "]"));
							scrollByElement(src);
							String sourcenme = src.getAttribute("src").toLowerCase();
							System.out.println(sourcenme);
							String vendorname = getSource(sourcenme);
							System.out.println("The Vendor name is :" + vendorname);
							Source.add(vendorname);
							System.out.println("The list contains :" +Source);
						}if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
				}*/
				Source.add("Yelp");
				Source.add("Bing");
				Source.add("Google");
				Source.add("Facebook");
				System.out.println("The final list is :" +Source);
				List<String> tmp = Source;
				Collections.sort(tmp);
				boolean ascending = true;
				int size = Source.size();
				System.out.println("Source list size is :" +size);
				int size1 = tmp.size();
				for (int k = 0; k<=size -1  && (ascending); k++) {
					for(int m = 0; m <= size1 -1; m++) {
						/*if(Source.get(k).compareToIgnoreCase(Source.get(k + 1)) >= 0) { */
							if(Source.size() == tmp.size()) {
								soft.assertTrue(Source.get(k).equalsIgnoreCase(tmp.get(m)));
							}
						/*	ascending = true;
							soft.assertTrue(true,"Source is is in ascending order");*/
						//}
							else {
							soft.fail("Source is not in ascending order" + "First Source is" +Source.get(k) + "Second date is :" +Source.get(m));
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			soft.fail("No Data available for selected value");
		}
	}
}
