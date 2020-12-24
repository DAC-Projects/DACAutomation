package com.dac.main.POM_SA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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

import bsh.org.objectweb.asm.Type;
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
	String SenCatSel;
	String sentiment;
	String[] vendorlist;
	String[] Ratinglist;
	String[] ResponseList;
	String[] ContentList;
	String[] taglist;
	String[] SenCatList;
	String[] sentimentlist;
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

	@FindBy(xpath = "(//div[@id='paginationInfo'])[1]")
	private WebElement Entry;

	@FindBy(xpath = "(//select[@id='pageSize'])[1]")
	private WebElement ResultperPage;

	@FindBy(xpath = "(//input[@class='page-input form-control form-control-sm'])[1]")
	private WebElement gotopage;

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

	@FindBy(xpath = "(//dl[@class='dropdown sentiment-category-filter'])[1]")
	private WebElement SentimentCat;

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

	@FindBy(xpath = "//div[@class='tooltips-container']")
	private List<WebElement> SentimentContainer;
	
	@FindBy(xpath = "//button//span[contains(text(),'Cancel')]")
	private WebElement CancelBtn;

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
	
	public void CancelWalkme() {
		if(CancelBtn.isDisplayed()) {
			clickelement(CancelBtn);
		}else {
			System.out.println("No Cancel Btn Displayed");
		}
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
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + LocationDataExport));
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
			clickelement(driver.findElement(By.xpath("(//*[@class='pagination']//a)[2]")));
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
									.findElement(By.xpath("(//*[@id='Review']//div[@class='form-group col-xs-12'])["
											+ row + "]//*[@id='viewListingLink']"))
									.getAttribute("href");
							Thread.sleep(2000);
							if (!text.contains("yelp")) {
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
					XLBname = readCsvColdata(chromepath, 4);
					Thread.sleep(1000);
					XLRefCode = readCsvColdata(chromepath, 1);
					Thread.sleep(1000);
					XLURL = readCsvColdata(chromepath, 3);
					Thread.sleep(1000);
					XLSource = readCsvColdata(chromepath, 2);
					Thread.sleep(1000);
					deletefile();
				}
				if (CurrentState.getBrowser().equals("IE")) {
					XLBname = readCsvColdata(IEpath, 4);
					Thread.sleep(1000);
					XLRefCode = readCsvColdata(IEpath, 1);
					Thread.sleep(1000);
					XLURL = readCsvColdata(IEpath, 3);
					Thread.sleep(1000);
					XLSource = readCsvColdata(IEpath, 2);
					Thread.sleep(1000);
					deletefile();
				}
				if (CurrentState.getBrowser().equals("Firefox")) {
					XLBname = readCsvColdata(FFpath, 4);
					Thread.sleep(1000);
					XLRefCode = readCsvColdata(FFpath, 1);
					Thread.sleep(1000);
					XLURL = readCsvColdata(FFpath, 3);
					Thread.sleep(1000);
					XLSource = readCsvColdata(FFpath, 2);
					Thread.sleep(1000);
					deletefile();
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
							By.xpath("(//*[@id='filter-area']//input[@title='" + Vendor.trim() + "'])[1]"))).click()
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
									.findElement(By
											.xpath("(//*[@id='Review']//div//img[@class = 'source-thumb'])[" + j + "]"))
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
			action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[1]")))
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
							By.xpath("(//*[@id='filter-area']//input[@title='"+Rating+"'])[1]"))).click()
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
							driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[2]"))).click()
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
							.xpath("//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"
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
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
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
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
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
		action.moveToElement(driver.findElement(By.xpath("//div[text()='" + sorttype + "']"))).click().build()
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
					scrollByElement(SentimentCat);
					clickelement(SentimentCat);
					driver.findElement(By.xpath("(//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'"
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
							By.xpath("(//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'All')]")).click();
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
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
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

	/*
	 * public void selectmultiplesentiment() throws Exception {
	 * 
	 * ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx",
	 * "Reviews_AdvancedFilters"); for (int k = 1; k <= wb.getRowCount(); k++) {
	 * String sentiments = wb.getCellValue(k, wb.seacrh_pattern("MultiSentiment",
	 * 0).get(0).intValue()); System.out.println("The vendors listed are :" +
	 * sentiments); sentimentlist = sentiments.split(","); int size =
	 * sentimentlist.length; System.out.println("The size of list is :" + size); for
	 * (int i = 0; i <= size - 1; i++) { sentiment = sentimentlist[i];
	 * System.out.println("The Sentiment is :" + sentiment); if
	 * (!sentiment.equalsIgnoreCase("null")) { scrollByElement(advanceSearch);
	 * clickelement(advanceSearch); scrollByElement(SentimentCat);
	 * clickelement(SentimentCat); driver.findElement(By.xpath(
	 * "(//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'" +
	 * sentiment.trim() + "')]")).click(); JSWaiter.waitJQueryAngular();
	 * clickelement(SentimentCat); BaseClass.addEvidence(driver,
	 * "Test to select multiple sentiment category", "yes");
	 * System.out.println("Sentiment selected" + sentiment); waitUntilLoad(driver);
	 * Thread.sleep(5000); clickelement(SentimentCat); clickelement(advanceSearch);
	 * } else { System.out.println("No Vendor selected"); } } if
	 * (!Vendor.equalsIgnoreCase("null")) { comparesentimentswithreviews(); } else {
	 * System.out.println("No vendors selected"); } }
	 * 
	 * }
	 * 
	 * public void comparesentimentwithreviews() { // Complete code for verifying
	 * data JSWaiter.waitJQueryAngular(); waitForElement(ReviewSection, 10);
	 * scrollByElement(ReviewSection); waitForElement(paginationLast, 10); boolean
	 * dataavailable = DataAvailable(); if (dataavailable == false) { int lastpage =
	 * Integer .parseInt(driver.findElement(By.xpath(
	 * "(//*[@class='pagination']//a)[last()-1]")).getText());
	 * System.out.println("Last Page Number is :" + lastpage);
	 * waitForElement(paginationPrev, 10); clickelement(paginationPrev); WebElement
	 * sentiment; try { if (paginationNext.isDisplayed()) { int size =
	 * SentimentContainer.size(); System.out.println("The size is :" + size); for
	 * (int j = 1; j <= size; j++) { // write code here to compare data ExcelHandler
	 * wb = new ExcelHandler("./data/Reviews.xlsx", "Reviews_AdvancedFilters"); for
	 * (int k = 1; k <= wb.getRowCount(); k++) { String sentiments =
	 * wb.getCellValue(k, wb.seacrh_pattern("MultiSentiment", 0).get(0).intValue());
	 * System.out.println("The vendors listed are :" + sentiments); sentimentlist =
	 * sentiments.split(","); int size1 = sentimentlist.length;
	 * System.out.println("The size of list is :" + size1); for (int i = 0; i <=
	 * size1 - 1; i++) { String selsentiment = sentimentlist[i];
	 * System.out.println("The Sentiment is :" + selsentiment);
	 * if(sentiments.contains("Overall")) { sentiment = driver.findElement(By.xpath(
	 * "(//a[contains(text(),'Overall')])[" + j + "]")); scrollByElement(sentiment);
	 * String color = driver.findElement(
	 * By.xpath("(//a[contains(text(),'Overall')])["+ j +"]/parent::div"))
	 * .getAttribute("class"); System.out.println("The color is :" + color);
	 * soft.assertTrue(!color.contains("grey"), "The color should not be grey");
	 * }else if(selsentiment.equalsIgnoreCase("Atmosphere")) { //atms }else
	 * if(selsentiment.equalsIgnoreCase("")) { //prdct }else
	 * if(selsentiment.equalsIgnoreCase("")) { //cusser }else
	 * if(selsentiment.equalsIgnoreCase("")) { //val } } } }
	 * BaseClass.addEvidence(driver, "Test to verify sentiment category selected",
	 * "yes"); if (paginationNext.isEnabled()) { scrollByElement(paginationNext);
	 * paginationNext.click(); JSWaiter.waitJQueryAngular(); } } } catch (Exception
	 * e) { e.printStackTrace(); } } else { System.out.println("No data available");
	 * } }
	 */

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
						By.xpath("//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"
								+ sentiment + "')]"))
						.click();
				waitUntilLoad(driver);
				for (int j = 0; j <= size - 1; j++) {
					SenCatSel = SenCatList[j];
					System.out.println("The Sentiment Category selected is :" + SenCatSel);
					if (!SenCatSel.equals("null")) {
						scrollByElement(SentimentCat);
						clickelement(SentimentCat);
						driver.findElement(By.xpath("(//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'"
								+ SenCatSel.trim() + "')]")).click();
						JSWaiter.waitJQueryAngular();
						clickelement(SentimentCat);
						BaseClass.addEvidence(driver, "Test to select the sentiment category", "yes");
						VerifySentimentandCategoryverify(SenCatSel, sentiment);
						clickelement(SentimentCat);
						driver.findElement(
								By.xpath("(//div[@class='dropdown-multi'])[3]//li//label[contains(text(),'All')]"))
								.click();
						JSWaiter.waitJQueryAngular();
						clickelement(SentimentCat);
					}
				}
				scrollByElement(SentimentTab);
				clickelement(SentimentTab);
				driver.findElement(By.xpath(
						"//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), 'All')]"))
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
					.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
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
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		ResultsperPage(soft, Entry, ResultperPage);
	}

	public void GoTo() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@class='pagination']//a[contains(text(),'1')])")).click();
		Thread.sleep(3000);
		waitForElement(gotopage, 10);
		scrollByElement(gotopage);
		GoTopage(gotopage);
	}

	public void verifyDateSelected() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat").toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		JSWaiter.waitJQueryAngular();
		List<Date> ReviewDates = new ArrayList<Date>();
		Date FromDate = getFromDate();
		System.out.println("From Date selected is : " + FromDate);
		Date ToDate = getToDate();
		System.out.println("To Date selected is : " + ToDate);
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
					for (int j = 1; j <= lastpage; j++) {
						JSWaiter.waitJQueryAngular();
						int size = ReviewInfo.size();
						System.out.println(size);
						for (int k = 1; k <= size; k++) {

							WebElement date = driver.findElement(By.xpath("(//div[@class='date-text'])[" + k + "]"));
							scrollByElement(date);
							String reviewdate = date.getText();
							System.out.println("Review Date is :" + reviewdate);
							Date UIDate = formats.parse(reviewdate);
							ReviewDates.add(UIDate);
							BaseClass.addEvidence(driver, "Test to get UI Date", "yes");

						}
						if (paginationNext.isEnabled()) {
							scrollByElement(paginationNext);
							paginationNext.click();
							Thread.sleep(4000);
						}
					}
					System.out.println("The list consists of :" + ReviewDates);
					for (int l = 0; l <= ReviewDates.size() - 1; l++) {
						soft.assertTrue(ReviewDates.get(l).compareTo(FromDate) >= 0
								&& ReviewDates.get(l).compareTo(ToDate) <= 0);
					}
				}
				soft.assertAll();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

}
