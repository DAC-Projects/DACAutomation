package com.dac.main.POM_SA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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

public class Reviews_Feed_Response_To_Reviews extends SA_Abstarct_Methods{
	
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	SoftAssert soft = new SoftAssert();
	List<String> ReferenceNumber = new ArrayList<String>();
	List<String> URL = new ArrayList<String>();
	List<String> BusinssName = new ArrayList<String>();
	List<String> Location = new ArrayList<String>();
	public static String time_Stamp;
	String Vendor = null;
	String Rating = null;
	String contentsel;
	String SenCatSel;
	String sentiment;
	String[] vendorlist;
	String[] Ratinglist;
	String[] ContentList;
	String[] taglist;
	String[] SenCatList;
	String[] sentimentlist;
	String tag = null;
	Select select;
	List<String> XLReferenceNumber = new ArrayList<String>();
	List<String> XLURL = new ArrayList<String>();
	List<String> XLBusinssName = new ArrayList<String>();
	List<String> XLAddress = new ArrayList<String>();
	List<String> XLCity = new ArrayList<String>();
	List<String> XLState = new ArrayList<String>();
	List<String> XLPostCode = new ArrayList<String>();
	List<String> XLPhone = new ArrayList<String>();
	List<String> XLReviewDate = new ArrayList<String>();
	List<String> XLReview = new ArrayList<String>();
	List<String> XLAuthor = new ArrayList<String>();
	List<String> XLStarRating = new ArrayList<String>();
	List<String> XLTags = new ArrayList<String>();
	List<String> XLSource = new ArrayList<String>();
	List<String> UIReferenceNumber = new ArrayList<String>();
	List<String> UIURL = new ArrayList<String>();
	List<String> UIBusinssName = new ArrayList<String>();
	List<String> UIAddress = new ArrayList<String>();
	List<String> UIReviewDate = new ArrayList<String>();
	List<String> UIReview = new ArrayList<String>();
	List<String> UIAuthor = new ArrayList<String>();
	List<String> UIStarRating = new ArrayList<String>();
	List<String> UITags = new ArrayList<String>();
	List<String> UISource = new ArrayList<String>();

	public Reviews_Feed_Response_To_Reviews(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	
	/**********************Locators Start******************************/
	
	@FindBy(xpath = "//a[@href='#RespondToReviews']")
	private WebElement ResToReviews;
	
	@FindBy(xpath = "(//button[@id='btnReportExport'])[2]")
	private WebElement LocationExport;
	
	@FindBy(xpath = "(//a[contains(text(),'Export as CSV')])[2]")
	private WebElement csvexport;

	@FindBy(xpath = "(//a[contains(text(),'Export as XLSX')])[2]")
	private WebElement XLSXExport;
	
	@FindBy(xpath = "(//*[@class='reviewEnhancement'])[2]")
	private WebElement ReviewSection;
	
	@FindBy(xpath = "//div[@class='business-info']//div//strong")
	private List<WebElement> BusinessName;
	
	@FindBy(xpath = "(//div[@id='paginationInfo'])[2]")
	private WebElement Entry;
	
	@FindBy(xpath = "(//span[@id='review_totalcounts'])[2]")
	private WebElement ReviewCount;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//dl[contains(@class,'source-filter')]")
	private WebElement SourceFilter;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//*[@class='review-content']")
	private List<WebElement> Reviews;
	
	@FindBy(xpath = "(//*[@id='RespondToReviews']//dl[contains(@class,'dropdown star-filter')])")
	private WebElement RatingFilter;
	
	@FindBy(xpath = "(//*[@id='RespondToReviews']//div[@class='searchByContent'])")
	private WebElement ContentSel;
	
	@FindBy(xpath = "//a[@id='viewListingLink']")
	private List<WebElement> ListingLink;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//*[@id='filter-search']")
	private WebElement advanceSearch;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//*[@class='sentiment-score-filter']")
	private WebElement SentimentTab;
	
	@FindBy(xpath = "(//dl[@class='dropdown sentiment-category-filter'])[2]")
	private WebElement SentimentCat;
	
	@FindBy(xpath = "//div[@class='tooltips-container']")
	private List<WebElement> SentimentContainer;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//input[@class='form-control keywordSearch']")
	private WebElement SearchKeyword;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//div[@class='input-icon input-icon-right']//input[contains(@class,'ui-widget-content ui-autocomplete-input')]")
	private WebElement TagInput;
	
	@FindBy(xpath = "//ul[contains(@id,'review-tags')]")
	private WebElement TagEntry;
	
	@FindBy(xpath = "(//div[@class='ui fluid dropdown selection sort-dropdown'])[2]")
	private WebElement clicksort;
	
	@FindBy(xpath = "//div[@id='RespondToReviews']//*[@class='business-info']")
	private List<WebElement> ReviewInfo;
	
	@FindBy(xpath = "//div[@id='RespondToReviews']//select[@id='pageSize']")
	private WebElement ResultperPage;

	@FindBy(xpath = "//div[@id='RespondToReviews']//input[contains(@class,'page-input form-control form-control-sm')]")
	private WebElement gotopage;
	
	@FindBy(xpath = "//*[@id='RespondToReviews']//*[@class='tooltip-container-Notices']//p")
	private WebElement NoticeText;
	
	/*-------------------------Pagination-----------------------*/

	@FindBy(xpath = "(//div[@id='RespondToReviews']//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//div[@id='RespondToReviews']//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "(//div[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationLast;

	@FindBy(xpath = "(//div[@id='RespondToReviews']//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;
	
	
	@FindBy(xpath = "(//div[@id='Review']//*[@class='pagination']//a)")
	private List<WebElement> pagination1;

	@FindBy(xpath = "(//div[@id='Review']//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev1;

	@FindBy(xpath = "(//div[@id='Review']//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationLast1;

	@FindBy(xpath = "(//div[@id='Review']//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext1;

	/*-------------------------Pagination-----------------------*/
	/**********************Locators End******************************/
	
	
	
	public void verifyNotice() throws Exception {
		WebElement Notice = driver.findElement(By.xpath("//*[@id='RespondToReviews']//*[@id='export-text-notices']"));
		scrollByElement(Notice);
		action.moveToElement(Notice).click().build().perform();
		BaseClass.addEvidence(driver, "Test to verify notice text", "yes");
		String Noticetext = NoticeText.getAttribute("innerText").trim();
		System.out.println("The Notice Text is : " +Noticetext);
		String[] txt = Noticetext.split("\n");
		String txt1 = txt[0].trim();
		System.out.println("txt1 is : " +txt1);
		String txt2 = txt[1].trim();
		System.out.println("txt2 is : " +txt2);
		soft.assertEquals(txt1, "1. Yelp data is not eligible to be exported from our Dashboard.");
		soft.assertEquals(txt2, "2. Google reviews were disabled for all accounts from March 20th to April 9th 2020 due to Covid19 restrictions.");
		soft.assertAll();
	//	Assert.assertEquals(Noticetext, "1. Yelp data is not eligible to be exported from our Dashboard. \r\n" + 
		//		"        2. Google reviews were disabled for all accounts from March 20th to April 9th 2020 due to Covid19 restrictions.");
		//Assert.assertEquals(actual, expected);
	}
	
	
	/**
	 * To Click on Respond to Reviews Tab
	 * 
	 */
	
	public void ClickResToReviews() {
		waitForElement(ResToReviews, 10);
		scrollByElement(ResToReviews);
		clickelement(ResToReviews);
	}
	
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
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + ResLocationDataExport));
	}
	
	/**
	 * Export as CSV
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void LocationCSVExport() throws InterruptedException, FileNotFoundException, IOException {
		LocationExport(LocationExport, csvexport);
		Thread.sleep(4000);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + LocationDataExportCSV));
	}
	
	/**
	 * Export as XLSX
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void LocationXLSXExport() throws FileNotFoundException, IOException, InterruptedException {
		LocationExport(LocationExport, XLSXExport);
		Thread.sleep(4000);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + LocationDataExportXLSX));
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
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			clickelement(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[2]")));
			int numberofentries = NumOfentriesinPage(Entry);
			System.out.println("The number of entries :" + numberofentries);
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
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
									.findElement(By.xpath("(//*[@id='RespondToReviews']//div[@class='form-group col-xs-12'])["
											+ row + "]//*[@id='viewListingLink']"))
									.getAttribute("href");
							Thread.sleep(2000);
							if (!text.contains("yelp")) {
								String Address = driver.findElement(By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])[" + row
																					  +"]//div[@class='business-info']//div")).getText();
								System.out.println(Address);
								Thread.sleep(2000);
								String BName = driver.findElement(By.xpath(
										"(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])[" + row
												+ "]//div[@class='business-info']//div//strong"))
										.getText();
								System.out.println(BName);
								Thread.sleep(2000);
								String ReferenceNum = driver.findElement(By.xpath(
										"(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])[" + row
												+ "]//div[@class='reference-code']//div[2]"))
										.getText();
								System.out.println(ReferenceNum);
								Thread.sleep(2000);
								WebElement src = driver.findElement(
										By.xpath("(//*[@id='RespondToReviews']//div//img[@class = 'source-thumb'])[" + row + "]"));
								scrollByElement(src);
								String sourcenme = src.getAttribute("src").toLowerCase();
								System.out.println(sourcenme);
								String vendorname = getSource(sourcenme);
								System.out.println("The Vendor name is :" + vendorname);
								WebElement Author = driver.findElement(By.xpath("(//div[@class='author'])["+ row +"]"));
								scrollByElement(Author);
								String AuthorName = Author.getText();
								System.out.println("The Author name is : " +AuthorName);
								WebElement ReviewDate = driver.findElement(By.xpath("(//div[@class='date-text'])["+ row +"]"));
								scrollByElement(ReviewDate);
								String reviewDate = ReviewDate.getText();
								System.out.println("The Date is : " +reviewDate);
								WebElement Review = driver.findElement(By.xpath("(//div[@class='review-content'])["+ row +"]"));
								scrollByElement(Review);
								String review = Review.getText();
								String LinksUrl = driver.findElement(By.xpath(
										"(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])[" + row
												+ "]//*[@id='viewListingLink']"))
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
								
								UIReferenceNumber.add(ReferenceNum);
								UIAddress.add(Address);
								UIAuthor.add(AuthorName);
								UIBusinssName.add(BName);
								UIReviewDate.add(reviewDate);
								UIReview.add(review);
								UISource.add(vendorname);
								UIURL.add(LinksUrl);
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
				System.out.println("Business Name in Review : " + UIBusinssName);
				System.out.println("Reference Code in Review :" + UIReferenceNumber);
				System.out.println("Link Listing in Review :" + UIURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String chromepath = "./downloads/chromeLocationDataExportxlsx.xlsx";
				String IEpath = "./downloads/IELocationDataExportxlsx.xlsx";
				String FFpath = "./downloads/FFLocationDataExportxlsx.xlsx";
				/*List<String> XLBname = new ArrayList<String>();
				List<String> XLRefCode = new ArrayList<String>();
				List<String> XLURL = new ArrayList<String>();
				List<String> XLSource = new ArrayList<String>();*/

				if (CurrentState.getBrowser().equals("chrome")) {
					XLBusinssName = GetDataUsingColName(chromepath, "Business Name");
					Thread.sleep(1000);
					System.out.println("The Business Name from Xl is : " +XLBusinssName);
					XLReferenceNumber = GetDataUsingColName(chromepath, "Reference Code");
					Thread.sleep(1000);
					XLURL = GetDataUsingColName(chromepath, "URL");
					Thread.sleep(1000);
					XLSource = GetDataUsingColName(chromepath, "Source");
					Thread.sleep(1000);
					XLAddress = GetDataUsingColName(chromepath, "Address");
					Thread.sleep(1000);
					XLCity = GetDataUsingColName(chromepath, "City");
					Thread.sleep(1000);
					XLState = GetDataUsingColName(chromepath, "St/Prov/Region");
					Thread.sleep(1000);
					XLPostCode = GetDataUsingColName(chromepath, "Postal Code");
					Thread.sleep(1000);
					XLPhone = GetDataUsingColName(chromepath, "Phone");
					Thread.sleep(1000);
					XLReviewDate = GetDataUsingColName(chromepath, "Date");
					Thread.sleep(1000);
					XLReview = GetDataUsingColName(chromepath, "Review");
					Thread.sleep(1000);
					XLAuthor = GetDataUsingColName(chromepath, "Author");
					Thread.sleep(1000);
					deletefile();
				}
				if (CurrentState.getBrowser().equals("IE")) {
					XLBusinssName = GetDataUsingColName(IEpath, "Business Name");
					Thread.sleep(1000);
					XLReferenceNumber = GetDataUsingColName(IEpath, "Reference Code");
					Thread.sleep(1000);
					XLURL = GetDataUsingColName(IEpath, "URL");
					Thread.sleep(1000);
					XLSource = GetDataUsingColName(IEpath, "Source");
					Thread.sleep(1000);
					XLAddress = GetDataUsingColName(IEpath, "Address");
					Thread.sleep(1000);
					XLCity = GetDataUsingColName(IEpath, "City");
					Thread.sleep(1000);
					XLState = GetDataUsingColName(IEpath, "St/Prov/Region");
					Thread.sleep(1000);
					XLPostCode = GetDataUsingColName(IEpath, "Postal Code");
					Thread.sleep(1000);
					XLPhone = GetDataUsingColName(IEpath, "Phone");
					Thread.sleep(1000);
					XLReviewDate = GetDataUsingColName(IEpath, "Date");
					Thread.sleep(1000);
					XLReview = GetDataUsingColName(IEpath, "Review");
					Thread.sleep(1000);
					XLAuthor = GetDataUsingColName(IEpath, "Author");
					Thread.sleep(1000);
					deletefile();
				}
				if (CurrentState.getBrowser().equals("Firefox")) {
					XLBusinssName = GetDataUsingColName(FFpath, "Business Name");
					Thread.sleep(1000);
					XLReferenceNumber = GetDataUsingColName(FFpath, "Reference Code");
					Thread.sleep(1000);
					XLURL = GetDataUsingColName(FFpath, "URL");
					Thread.sleep(1000);
					XLSource = GetDataUsingColName(FFpath, "Source");
					Thread.sleep(1000);
					XLAddress = GetDataUsingColName(FFpath, "Address");
					Thread.sleep(1000);
					XLCity = GetDataUsingColName(FFpath, "City");
					Thread.sleep(1000);
					XLState = GetDataUsingColName(FFpath, "St/Prov/Region");
					Thread.sleep(1000);
					XLPostCode = GetDataUsingColName(FFpath, "Postal Code");
					Thread.sleep(1000);
					XLPhone = GetDataUsingColName(FFpath, "Phone");
					Thread.sleep(1000);
					XLReviewDate = GetDataUsingColName(FFpath, "Date");
					Thread.sleep(1000);
					XLReview = GetDataUsingColName(FFpath, "Review");
					Thread.sleep(1000);
					XLAuthor = GetDataUsingColName(FFpath, "Author");
					Thread.sleep(1000);
					deletefile();
				}
				int size;
				if(XLBusinssName.size() == UIBusinssName.size()) {
					size = XLBusinssName.size();
					for(int i = 0 ; i < size ; i++) {
						soft.assertTrue(XLBusinssName.get(i).equals(UIBusinssName.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(XLAddress.size() == UIAddress.size()) {
					size = XLAddress.size();
					for(int i = 0 ; i < size ; i++) {
						soft.assertTrue(UIAddress.get(i).contains(XLAddress.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIAddress.size() == XLCity.size()) {
					size = UIAddress.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIAddress.get(i).contains(XLCity.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIAddress.size() == XLState.size()) {
					size = UIAddress.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIAddress.get(i).contains(XLState.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIAddress.size() == XLPostCode.size()) {
					size = UIAddress.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIAddress.get(i).contains(XLPostCode.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIAddress.size() == XLPhone.size()) {
					size = UIAddress.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIAddress.get(i).contains(XLPhone.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIReviewDate.size() == XLReviewDate.size()) {
					size = UIReviewDate.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIReviewDate.get(i).equals(XLReviewDate.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIReview.size() == XLReview.size()) {
					size = UIReview.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIReview.get(i).equals(XLReview.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIAuthor.size() == XLAuthor.size()) {
					size = UIAuthor.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIAuthor.get(i).equals(XLAuthor.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				if(UIURL.size() == XLURL.size()) {
					size = UIURL.size();
					for(int i = 0; i < size; i++) {
						soft.assertTrue(UIURL.get(i).equals(XLURL.get(i)));
					}
				}else {
					soft.fail("Lists are not equal");
				}
				
				/*soft.assertEquals(XLBname.size(), BusinssName.size());
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
				}*/
				resultperpage(soft);
				Thread.sleep(5000);
				GoTo();
				soft.assertAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	public List<String> readCsvColdata(String path, int colnum) throws IOException {
		String splitBy = ",";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		List<String> csvcollist = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] b = line.split(splitBy);
			csvcollist.add(b[colnum].toString());
		}
		System.out.println("Data in Type column : " + csvcollist);
		return csvcollist;
	}

	/**
	 * Verify Review count and Number of entries in a table
	 * 
	 * @throws Exception
	 */
	public void verifyReviewCount() throws Exception {
		waitForElement(ReviewCount, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			scrollByElement(ReviewCount);
			int RCount = Integer.parseInt(ReviewCount.getText());
			System.out.println("The Review Count is :" + RCount);
			BaseClass.addEvidence(driver, "Test to verify review count", "yes");
			int count = SANumOfentriesinPage(Entry);
			System.out.println("The number of entries :" + count);
			Assert.assertEquals(count, RCount);
		} else {
			System.out.println("No Data Available");
		}
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
							By.xpath("(//*[@id='RespondToReviews']//*[@id='filter-area']//input[@title='" + Vendor.trim() + "'])[1]"))).click()
							.build().perform(); 
					System.out.println("Vendor selected" + Vendor);
					waitUntilLoad(driver);
					Thread.sleep(5000);
					clickelement(SourceFilter);
					BaseClass.addEvidence(driver, "Test to select source", "yes");
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
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
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
									.findElement(By
											.xpath("(//*[@id='RespondToReviews']//div//img[@class = 'source-thumb'])[" + j + "]"))
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
						BaseClass.addEvidence(driver, "Test to compare source with reviews", "yes");
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
			action.moveToElement(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@id='filter-area']//input[@title='All'])[1]")))
					.click().build().perform();
			clickelement(SourceFilter);
			waitUntilLoad(driver);
			soft.assertAll();
		} else {
			System.out.println("No Data Available");
		}
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
					Thread.sleep(3000);
					action.moveToElement(driver.findElement(
							By.xpath("(//*[@id='RespondToReviews']//*[@id='filter-area']//input[@title='"+Rating+"'])[1]"))).click()
							.build().perform();
					System.out.println("Rating selected" + Rating);
					BaseClass.addEvidence(driver, "Select Rating", "yes");
					waitUntilLoad(driver);
					clickelement(RatingFilter);
					Thread.sleep(3000);
				} else {
					System.out.println("No Rating selected");
				}

				if (!Rating.equalsIgnoreCase("null")) {
					compareratingwithreviews();
					scrollByElement(RatingFilter);
					clickelement(RatingFilter);
					action.moveToElement(
							driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@id='filter-area']//input[@title='All'])[2]"))).click()
							.build().perform();
					clickelement(RatingFilter);
				} else {
					System.out.println("No rating selected");
				}
			}
		}
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
							 if (Rating.equals("Recommended")) {
								scrollByElement(driver.findElement(
										By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]")));
								if (!(driver.findElement(By.xpath("(//span[contains(@class,'fivestars')])[" + j + "]"))
										.getAttribute("class").contains("Yelp"))) {
									String Recom = driver
											.findElement(By.xpath(
													"(//span[contains(@class,'fivestars')]//div)[" + j + "]//span"))
											.getText();
									BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
									System.out.println("The text is :" + Recom);
									if (Recom.equals("Recommends")) {
										for (int k = 1; k <= wb.getRowCount(); k++) {
											String XLrating = wb.getCellValue(k,
													wb.seacrh_pattern("Rating", 0).get(0).intValue());
											System.out.println("The vendors listed are :" + XLrating);
											soft.assertTrue(XLrating.contains("Recommended"), "The rating from UI is "
													+ Recom + " is not present in " + XLrating);
										}
									} else {
										soft.fail("Selected Rating is Recommended but not found");
									}
								} else {
									System.out.println("The vendor is Yelp");
								}
							} else if (Rating.equals("Not Recommended")) {
								scrollByElement(driver.findElement(
										By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]")));
								if (!(driver.findElement(By.xpath("(//span[contains(@class,'fivestars')])[" + j + "]"))
										.getAttribute("class").contains("Yelp"))) {

									String NotRecom = driver
											.findElement(By.xpath(
													"(//span[contains(@class,'fivestars')]//div)[" + j + "]//span"))
											.getText();
									BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
									System.out.println("The text is :" + NotRecom);
									if (NotRecom.equals("Doesn't Recommend")) {
										for (int k = 1; k <= wb.getRowCount(); k++) {
											String XLrating = wb.getCellValue(k,
													wb.seacrh_pattern("Rating", 0).get(0).intValue());
											System.out.println("The vendors listed are :" + XLrating);
											soft.assertTrue(XLrating.contains("Not Recommended"),
													"The rating from UI is " + NotRecom + " is not present in "
															+ XLrating);
										}
									} else {
										soft.fail("Selected Rating is Not Recommended but not found");
									}
								} else {
									System.out.println("The vendor is Yelp");
								}
							} else if (Rating.equals("No Rating")) {
								WebElement UIstarrate = driver.findElement(
										By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]"));
								if (!(driver.findElement(By.xpath("(//span[contains(@class,'fivestars')])[" + j + "]"))
										.getAttribute("class").contains("Yelp"))) {
									BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
									soft.assertTrue(!UIstarrate.isDisplayed());
								} else {
									System.out.println("Vendor is Yelp");
								}
							}else {
								scrollByElement(driver
										.findElement(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j + "]")));									
										if (!(driver.findElement(By.xpath("(//span[contains(@class,'fivestars')])[" + j + "]"))
												.getAttribute("class").contains("Yelp"))) {
										List<WebElement> starrate = driver
												.findElements(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + j
														+ "]//i[contains(@class,'yellow')]"));
										BaseClass.addEvidence(driver, "Test to verify rating with filter", "yes");
										int starratesize = starrate.size();
										// String sratesize = Integer.toString(starratesize);
										String sratesize = String.valueOf(starratesize);
										System.out.println("The starrate size is :" + sratesize);
										for (int k = 1; k <= wb.getRowCount(); k++) {
											String XLrating = wb.getCellValue(k,
													wb.seacrh_pattern("Rating", 0).get(0).intValue());
											System.out.println("The vendors listed are :" + XLrating);
											soft.assertTrue(XLrating.contains(sratesize), "The rating from UI is "
													+ sratesize + " is not present in " + XLrating);
										}
									} else {
										System.out.println("The Vendor is Yelp");
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
		Thread.sleep(3000);
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String Keyword = wb.getCellValue(i, wb.seacrh_pattern("Keywords", 0).get(0).intValue());
			waitForElement(advanceSearch, 10);
			clickelement(advanceSearch);
			waitForElement(SearchKeyword, 20);
			/*Thread.sleep(2000);
			scrollByElement(SearchKeyword);
			Thread.sleep(2000);*/
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
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
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
			BaseClass.addEvidence(driver, "Test to enter tag", "yes");
			if (!tags.equalsIgnoreCase("null")) {
				compareTagswithreviews(tag);
			} else {
				System.out.println("No tags selected");
			}
		}
		soft.assertAll();
		clickelement(TagInput);
		JSWaiter.waitJQueryAngular();
		TagInput.sendKeys(Keys.BACK_SPACE);
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
							String Tagname = driver
									.findElement(By.xpath("(//ul[contains(@id,'review-tags')])[" + j + "]")).getText();
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
						BaseClass.addEvidence(driver, "Test to verify tag with reviews", "yes");
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
							.xpath("//*[@id='RespondToReviews']//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"
									+ sentiment + "')]"))
							.click();
					waitUntilLoad(driver);
					BaseClass.addEvidence(driver, "Test to apply sentiment", "yes");
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
				By.xpath("//*[@id='RespondToReviews']//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), 'All')]"))
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
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
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
								BaseClass.addEvidence(driver, "Test to compare sentiment with reviews", "yes");
								System.out.println(ReviewsSentiment);
								soft.assertTrue(ReviewsSentiment.toLowerCase().contains(Text.toLowerCase()),
										"Sentiment selected " + ReviewsSentiment + " and UI sentiment " + Text + "");
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
		String text = driver.findElement(By.xpath("//*[@id='RespondToReviews']/div[@class='form-group']")).getText();
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
		/*WebElement Sort = driver.findElement(By.xpath("(//div[contains(@class,'item') and contains(text(),'" +sorttype+ "')])[2]"));
		scrollByElement(Sort);
		action.moveToElement(Sort).click().build();*/
		//clickelement(Sort);
		action.moveToElement(driver.findElement(By.xpath("(//div[contains(@class,'item') and contains(text(),'" +sorttype+ "')])[2]"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		BaseClass.addEvidence(driver, "Test to select Date Type", "yes");
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
						BaseClass.addEvidence(driver, "Test to compare reviews by date sorted", "yes");
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
		action.moveToElement(driver.findElement(By.xpath("(//div[contains(@class,'item') and contains(text(),'" +sorttype+ "')])[2]"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		BaseClass.addEvidence(driver, "Test to apply sort by ref - code", "yes");
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
						BaseClass.addEvidence(driver, "Test to compare reviews with - sort type - Ref Code", "yes");
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
		action.moveToElement(driver.findElement(By.xpath("(//div[contains(@class,'item') and contains(text(),'" +sorttype+ "')])[2]"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		BaseClass.addEvidence(driver, "Test to select sort type by source", "yes");
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
						BaseClass.addEvidence(driver, "Test to verify reviews by source sorted type", "yes");
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
		action.moveToElement(driver.findElement(By.xpath("(//div[contains(@class,'item') and contains(text(),'" +sorttype+ "')])[2]"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		BaseClass.addEvidence(driver, "Test to apply sort by rating", "yes");
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
							if (!(driver.findElement(By.xpath("(//span[contains(@class,'fivestars')])[" + k + "]"))
									.getAttribute("class").contains("Yelp"))) {
								List<WebElement> starrate = driver
										.findElements(By.xpath("(//span[contains(@class,'fivestars')]//div)[" + k
												+ "]//i[contains(@class,'yellow')]"));
								int starratesize = starrate.size();
								System.out.println("The starrate size is :" + starratesize);
								starvalue.add(starratesize);
								System.out.println("The star value is :" + starvalue);
							} else {
								System.out.println("The vendor is Yelp");
							}
						}
						BaseClass.addEvidence(driver, "Test to compare reviews - sort by rating", "yes");
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
	 *//*
	public void Review_Feed_Highlight() {
		reporthighlight(Review_FeedPage, ReviewsSection);
	}*/

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
		action.moveToElement(driver.findElement(By.xpath("(//div[contains(@class,'item') and contains(text(),'" +sorttype+ "')])[2]"))).click().build()
				.perform();
		JSWaiter.waitJQueryAngular();
		BaseClass.addEvidence(driver, "Test to apply sort filter - Location Name", "yes");
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
							"//div[@id='RespondToReviews']//div[@class='searchByContent']//div[contains(text(),'" + contentsel.trim() + "')]"))
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
		driver.findElement(By.xpath("//div[@id='RespondToReviews']//div[@class='searchByContent']//div[contains(text(),'All')]")).click(); 
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

	/**
	 * To select sentiment category filter
	 * 
	 * @throws Exception
	 */
	public void SelectSentimentcategory() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Sentiment_Filters");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String con = wb.getCellValue(i, wb.seacrh_pattern("Sentiment Category", 0).get(0).intValue());
			System.out.println("The Sentiment Category listed are :" + con);
			SenCatList = con.split(",");
			int size = SenCatList.length;
			System.out.println("The Sentiment Category list size is :" + size);
			for (int j = 0; j <= size - 1; j++) {
				SenCatSel = SenCatList[j];
				System.out.println("The Sentiment Category selected is :" + SenCatSel);
				if (!SenCatSel.equals("null")) {
					scrollByElement(advanceSearch);
					clickelement(advanceSearch);
					Thread.sleep(3000);
				//	scrollByElement(SentimentCat);
					clickelement(SentimentCat);
					driver.findElement(By.xpath("(//*[@id='RespondToReviews']//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'"
							+ SenCatSel.trim() + "')]")).click();
					JSWaiter.waitJQueryAngular();
					clickelement(SentimentCat);
					BaseClass.addEvidence(driver, "Test to select the sentiment category", "yes");
					clickelement(advanceSearch);
					VerifySentimentCategory(SenCatSel);
					scrollByElement(advanceSearch);
					clickelement(advanceSearch);
					scrollByElement(SentimentCat);
					clickelement(SentimentCat);
					driver.findElement(
							By.xpath("(//*[@id='RespondToReviews']//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'All')]")).click();
					JSWaiter.waitJQueryAngular();
					clickelement(advanceSearch);
				} else {
					System.out.println("No sentiment category selected");
				}
			}
		}
		soft.assertAll();
	}

	/**
	 * To verify sentiment category selected in reviews table
	 * 
	 * @param SenCatSelect
	 */
	public void VerifySentimentCategory(String SenCatSelect) {

		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			WebElement sentiment;
			try {
				if (paginationNext.isDisplayed()) {
					int size = SentimentContainer.size();
					System.out.println("The size is :" + size);
					for (int j = 1; j <= size; j++) {
						if (SenCatSelect.equalsIgnoreCase("Overall")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Overall')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By.xpath("(//a[contains(text(),'Overall')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							soft.assertTrue(!color.contains("grey"), "The color should not be grey");
						} else if (SenCatSelect.equalsIgnoreCase("Atmosphere")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Atmosphere')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(
											By.xpath("(//a[contains(text(),'Atmosphere')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							soft.assertTrue(!color.contains("grey"), "The color should not be grey");
						} else if (SenCatSelect.equalsIgnoreCase("Product")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Product')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By.xpath("(//a[contains(text(),'Product')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							soft.assertTrue(!color.contains("grey"), "The color should not be grey");
						} else if (SenCatSelect.equalsIgnoreCase("Customer Service")) {
							sentiment = driver
									.findElement(By.xpath("(//a[contains(text(),'Customer Service')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By
											.xpath("(//a[contains(text(),'Customer Service')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							soft.assertTrue(!color.contains("grey"), "The color should not be grey");
						} else if (SenCatSelect.equalsIgnoreCase("Value")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Value')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By.xpath("(//a[contains(text(),'Value')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							soft.assertTrue(!color.contains("grey"), "The color should not be grey");
						}
					}
					BaseClass.addEvidence(driver, "Test to verify sentiment category selected", "yes");
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No data available");
		}
	}
	
	/**
	 * Get color of sentiment category
	 * 
	 * @param txt
	 * @return
	 */
	public String verifysenticolor(String txt) {
		String ReviewsSentiment = null;
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
		}
		System.out.println(" The review sentiment is :" + ReviewsSentiment);
		return ReviewsSentiment;
	}

	/**
	 * To select sentiment and sentiment category
	 * 
	 * @throws Exception
	 */
	public void selectsentandcatandverifyreview() throws Exception {
		JSWaiter.waitJQueryAngular();
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Sentiment_Combination");
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String sentiment = wb.getCellValue(i, wb.seacrh_pattern("Sentiment", 0).get(0).intValue());
			System.out.println("The sentiment is :" + sentiment);
			String con = wb.getCellValue(i, wb.seacrh_pattern("Category", 0).get(0).intValue());
			System.out.println("The Sentiment Category listed are :" + con);
			SenCatList = con.split(",");
			int size = SenCatList.length;
			System.out.println("The Sentiment Category list size is :" + size);
			waitForElement(advanceSearch, 10);
			scrollByElement(advanceSearch);
			clickelement(advanceSearch);
			if (!sentiment.equals("null")) {
				scrollByElement(SentimentTab);
				clickelement(SentimentTab);
				driver.findElement(
						By.xpath("//*[@id='RespondToReviews']//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"
								+ sentiment + "')]"))
						.click();
				waitUntilLoad(driver);
				for (int j = 0; j <= size - 1; j++) {
					SenCatSel = SenCatList[j];
					System.out.println("The Sentiment Category selected is :" + SenCatSel);
					if (!SenCatSel.equals("null")) {
						scrollByElement(SentimentCat);
						clickelement(SentimentCat);
						driver.findElement(By.xpath("(//*[@id='RespondToReviews']//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'"
								+ SenCatSel.trim() + "')]")).click();
						JSWaiter.waitJQueryAngular();
						clickelement(SentimentCat);
						BaseClass.addEvidence(driver, "Test to select the sentiment category", "yes");
						VerifySentimentandCategoryverify(SenCatSel, sentiment);
						clickelement(SentimentCat);
						driver.findElement(
								By.xpath("(//*[@id='RespondToReviews']//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'All')]"))
								.click();
						JSWaiter.waitJQueryAngular();
						clickelement(SentimentCat);
					}
				}
				scrollByElement(SentimentTab);
				clickelement(SentimentTab);
				driver.findElement(By.xpath(
						"//*[@id='RespondToReviews']//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), 'All')]"))
						.click();
				waitUntilLoad(driver);
				clickelement(advanceSearch);
			}
		}
		soft.assertAll();
	}

	/**
	 * To verify sentiments and category in reviews table
	 * 
	 * @param SenCatSelect
	 * @param sent
	 */
	public void VerifySentimentandCategoryverify(String SenCatSelect, String sent) {
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		boolean dataavailable = DataAvailable();
		String finalcolor = null;
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			WebElement sentiment;
			try { 
				if (paginationNext.isDisplayed()) {
					int size = SentimentContainer.size();
					System.out.println("The size is :" + size);
					for (int j = 1; j <= size; j++) {
						if (SenCatSelect.equalsIgnoreCase("Overall")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Overall')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By.xpath("(//a[contains(text(),'Overall')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							finalcolor = verifysenticolor(color);
							verifysentiment(sent, finalcolor, SenCatSelect);

						} else if (SenCatSelect.equalsIgnoreCase("Atmosphere")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Atmosphere')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(
											By.xpath("(//a[contains(text(),'Atmosphere')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							finalcolor = verifysenticolor(color);
							verifysentiment(sent, finalcolor, SenCatSelect);

						} else if (SenCatSelect.equalsIgnoreCase("Product")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Product')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By.xpath("(//a[contains(text(),'Product')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							finalcolor = verifysenticolor(color);
							verifysentiment(sent, finalcolor, SenCatSelect);

						} else if (SenCatSelect.equalsIgnoreCase("Customer Service")) {
							sentiment = driver
									.findElement(By.xpath("(//a[contains(text(),'Customer Service')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By
											.xpath("(//a[contains(text(),'Customer Service')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							finalcolor = verifysenticolor(color);
							verifysentiment(sent, finalcolor, SenCatSelect);

						} else if (SenCatSelect.equalsIgnoreCase("Value")) {
							sentiment = driver.findElement(By.xpath("(//a[contains(text(),'Value')])[" + j + "]"));
							scrollByElement(sentiment);
							String color = driver
									.findElement(By.xpath("(//a[contains(text(),'Value')])[" + j + "]/parent::div"))
									.getAttribute("class");
							System.out.println("The color is :" + color);
							finalcolor = verifysenticolor(color);
							verifysentiment(sent, finalcolor, SenCatSelect);

						}
					}
					BaseClass.addEvidence(driver, "Test to verify sentiment category selected", "yes");
					if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No data available");
		}
	}

	/**
	 * Verify the sentiments for combination of sentiment and category
	 * 
	 * @param Sentiselected
	 * @param color
	 * @param Senticategory
	 */
	public void verifysentiment(String Sentiselected, String color, String Senticategory) {
		if (Sentiselected.equals("Positive")) {
			soft.assertTrue(color.equals("Positive"),
					"The sentiment selected is :" + Sentiselected + " and sentiment category is :" + Senticategory);
		} else if (Sentiselected.equals("Negative")) {
			soft.assertTrue(color.equals("Negative"),
					"The sentiment selected is :" + Sentiselected + " and sentiment category is :" + Senticategory);
		} else if (Sentiselected.equals("Neutral")) {
			soft.assertTrue(color.equals("Neutral"),
					"The sentiment selected is :" + Sentiselected + " and sentiment category is :" + Senticategory);
		}
	}

	public void resultperpage(SoftAssert soft) throws InterruptedException {
		driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a[contains(text(),'1')])")).click(); 
		Thread.sleep(3000);
		ResultsperPage(soft, Entry, ResultperPage);
	}

	public void GoTo() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		waitForElement(gotopage, 10);
		scrollByElement(gotopage);
		GoTopage(gotopage);
	}
	
	public void verifyReviewsCount() throws Exception {
		waitForElement(ReviewCount, 10);
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			scrollByElement(ReviewCount);
			int RCount = Integer.parseInt(ReviewCount.getText());
			System.out.println("The Review Count is :" + RCount);
			BaseClass.addEvidence(driver, "Test to verify review count", "yes");
			int count = SANumOfentriesinPage(Entry);
			System.out.println("The number of entries :" + count);
			soft.assertEquals(count, RCount);
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			int linkcount = 0;
			try {
				if (paginationNext.isDisplayed()) {
					List<WebElement> Link = driver.findElements(By.xpath("//div[@id='RespondToReviews']//a[contains(text(),'Add Owner Response')]"));
					int size = Link.size();
					for (int j = 1; j <= size; j++) {
						WebElement ele = driver.findElement(By.xpath("(//div[@id='RespondToReviews']//a[contains(text(),'Add Owner Response')])["+ j +"]"));
						scrollByElement(ele);
						linkcount = linkcount+1;
						System.out.println("The link count is : " +linkcount);
					} if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
					System.out.println("The total count is : " +linkcount);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			soft.assertEquals(linkcount, RCount);
			soft.assertAll();
		} else {
			System.out.println("No Data Available");
		}
	}
	
	
	public int getReviewCount() throws Exception {
		waitForElement(ReviewCount, 10);
		int RCount = 0;
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			scrollByElement(ReviewCount);
			RCount = Integer.parseInt(ReviewCount.getText());
			BaseClass.addEvidence(driver, "Test to get Review count", "yes");
			System.out.println("The Review Count is :" + RCount);
		}
		return RCount;
	}	
	
	
	public boolean BadReview() {
		boolean b =false;
		try {
		WebElement ele = driver.findElement(By.xpath("(//div[@class='modal-dialog']//button[@data-dismiss='modal']/following::div//h3[@class='modal-title'])/../..//button[@data-dismiss='modal']"));
		if(ele.isDisplayed()) {
			clickelement(ele);
			b = true;
		}else {
			b = false;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void AddResponse(String Response) {
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='RespondToReviews']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev, 10);
			clickelement(paginationPrev);
			
			try {
			Outer :	if (paginationNext.isDisplayed()) {
					List<WebElement> AddLinks = driver.findElements(By.xpath("//div[@id='RespondToReviews']//a[contains(text(),'Add Owner Response')]"));
					int size = AddLinks.size();
					for(int i = 1; i <= size; i++ ) {
						WebElement AddLink = driver.findElement(By.xpath("(//div[@id='RespondToReviews']//a[contains(text(),'Add Owner Response')])["+ i +"]"));
						clickelement(AddLink);
						boolean b = BadReview();
						if(b==false) {
						/*WebElement ele = driver.findElement(By.xpath("(//div[@class='modal-dialog']//button[@data-dismiss='modal']/following::div//h3[@class='modal-title'])/../..//button[@data-dismiss='modal']"));
						if(!(ele.isDisplayed())) {*/
							time_Stamp = timeStamp();
							System.out.println("The time stamp is : " +time_Stamp);
							WebElement ele1 = driver.findElement(By.xpath("(//textarea[@placeholder='Enter response here'])["+ i +"]"));
							scrollByElement(ele1);
							clickelement(ele1);
							ele1.sendKeys(Response + time_Stamp);
							WebElement ele2 = driver.findElement(By.xpath("(//button[contains(text(),'Submit')])["+ i +"]"));
							clickelement(ele2);
							WebElement ele3 = driver.findElement(By.xpath("(//button[contains(text(), 'Ok')])"));
							clickelement(ele3);
							JSWaiter.waitJQueryAngular();
							break Outer;
						}else {
							System.out.println("Cannot be added because it's a bad review");
						}
					}if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No Data Available");
		}
	}
	
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Timestamp is :"+formattedDate);
		return formattedDate;		
	}
	
	public void clickonReviews() {
		WebElement RevTab = driver.findElement(By.xpath("//li[@id='liReview']"));
		scrollByElement(RevTab);
		clickelement(RevTab);
		JSWaiter.waitJQueryAngular();
	}

	public void VerifyResponse(String Response) {
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int lastpage = Integer
					.parseInt(driver.findElement(By.xpath("(//*[@id='Review']//*[@class='pagination']//a)[last()-1]")).getText());
			System.out.println("Last Page Number is :" + lastpage);
			waitForElement(paginationPrev1, 10);
			clickelement(paginationPrev1);
			try {
			Outer :	if (paginationNext1.isDisplayed()) {
					List<WebElement> AddLinks = driver.findElements(By.xpath("//div[@class='owner-responses rrm']"));
					int size = AddLinks.size();
					for(int i = 1; i <= size; i++ ) {
						WebElement ResponseText = driver.findElement(By.xpath("(//div[@class='owner-responses rrm'])["+ i +"]"));
						scrollByElement(ResponseText);
						String txt = ResponseText.getText();
						System.out.println("The Response text is : " +txt);
						BaseClass.addEvidence(driver, "Test to verify response added", "yes");
						if(txt.contains(Response + time_Stamp)) {
							break Outer;
						}
					}if (paginationNext1.isEnabled()) {
						scrollByElement(paginationNext1);
						paginationNext1.click();
						JSWaiter.waitJQueryAngular();
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("No Data Available");
		}
	}
	
}
