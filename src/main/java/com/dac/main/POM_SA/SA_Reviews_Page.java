package com.dac.main.POM_SA;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

public class SA_Reviews_Page extends SA_Abstarct_Methods{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	/*-------------------------Locators-------------------------*/
	
	@FindBy(xpath = "//div[@class='message multi-review']//span[@class='average-score']")
	private WebElement averagereviews;
	
	@FindBy(xpath = "//div[@class='message multi-review']//span[@class='count']")
	private WebElement numofreviews;
	
	@FindBy(xpath = "//*[@id='positive-rec-count']")
	private WebElement positive;
	
	@FindBy(xpath = "//*[@id='negative-rec-count']")
	private WebElement negative;
	
	@FindBy(xpath = "//*[@id='npsscore']")
	private WebElement RNPS_Score;
	
	@FindBy(xpath = "//*[@id='btnLocationNPSExport']")
	private WebElement LocationExport;
	
	@FindBy(xpath = "//span[@id='fromDate']")
	private WebElement From_Date_Review;
	
	@FindBy(xpath = "//span[@id='toDate']")
	private WebElement To_Date_Review;
	
	@FindBy(xpath = "//*[@id='highchart-review']")
	private WebElement Highcharts;
	
	@FindBy(xpath = "//div[@id='review_summary']")
	private WebElement ReviewsOverview;
	
	@FindBy(xpath = "//div[@id='recommendation_summary']")
	private WebElement recomendsec;
	
	@FindBy(xpath = "//div[@id='nps_summary']")
	private WebElement RNPSsec;
	
	/*-----------------------------------------Review Tab---------------------------------------------------------*/
	@FindBy(id="btnReportExport")
	private WebElement exportBTN;
	
	@FindBy(xpath = "//*[@class='reviewEnhancement']")
	private WebElement ReviewSection;
	
	@FindBy(xpath="//*[@id='Review']//dl[contains(@class,'source-filter')]")
	private WebElement SourceTab;
	
	@FindBy(className="ownerResponse")
	private WebElement ownerResponseDD;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='dropdown star-filter']")
	private WebElement RatingTab;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='searchByContent']")
	private WebElement ContentTab;
	
	@FindBy(xpath="//*[@id='Review']//*[@id='filter-search']")
	private WebElement advanceSearch;
	
	@FindBy(xpath = "(//*[@id='filter-hidden']//div//input[@class='form-control keywordSearch'])[1]")
	private WebElement KeywordSearch;
	
	@FindBy(xpath = "//*[@id='filter-hidden']//li//input[@class='ui-widget-content ui-autocomplete-input']")   
	private WebElement SearchTag;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='sentiment-score-filter']")
	private WebElement SentimentTab;
	
	@FindBy(xpath="//*[@id='Review']//*[contains(@class,'sentiment-category-filter')]")
	private WebElement SentimentCategoryTab;
	
	@FindBy(xpath = "//div[@class='business-info']//div//strong")
	private List<WebElement> BusinessName;
	
	@FindBy(xpath = "//div[@class='reference-code']//div[2]")
	private List<WebElement> ReferenceCode;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='review-content']")
	private List<WebElement> Reviews;
	
	@FindBy(xpath="(//*[@id='viewListingLink'])")
	private List<WebElement> Links;
	
	@FindBy(xpath = "//*[@id='Review']//dl[contains(@class,'source-filter')]//a//p")
	private WebElement Source;
	
	@FindBy(xpath = "//*[@id='Review']//div//img[@class = 'source-thumb']")
	private List<WebElement> SourcefromReviews;
	
	@FindBy(xpath = "//*[@id='filter-options']//div[@class= 'ui fluid search selection dropdown myList1']")
	private WebElement locationdata;
	
	/*-----------------------------------------Review Tab---------------------------------------------------------*/
	
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
	
	/*-------------------------Locators------------------------*/
	
	List<String> ReferenceNumber = new ArrayList<String>();
	List<String> URL = new ArrayList<String>();
	List<String> BusinssName = new ArrayList<String>();
	List<String> Location = new ArrayList<String>();
	
	public SA_Reviews_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * To get the average score from overview section
	 * @return
	 */
	public double averagescore(){
		waitForElement(ReviewsOverview, 10);
		scrollByElement(ReviewsOverview);
		double average = getscore(averagereviews);
		System.out.println(average);
		return average;
	}
	
	/**
	 * To get the number of reviews from overview section
	 * @return
	 */
	public int numberofreviews() {
		waitForElement(ReviewsOverview, 10);
		scrollByElement(ReviewsOverview);
		int NoOfReviews = getnumber(numofreviews);
		System.out.println(NoOfReviews);
		return NoOfReviews;
	}
	
	/**
	 * To get Number of positive recommendations
	 * @return
	 */
	public int getpositive() {
		waitForElement(recomendsec, 10);
		scrollByElement(recomendsec);
		int positiverecomends = getnumber(positive);
		System.out.println(positiverecomends);
		return positiverecomends;
	}
	
	/**
	 * To get Number of negative recommendations
	 * @return
	 */
	public int getnegative() {
		waitForElement(recomendsec, 10);
		scrollByElement(recomendsec);
		int negativerecomends = getnumber(negative);
		System.out.println(negativerecomends);
		return negativerecomends;
	}
	
	/**
	 * To get RNPS score
	 * @return
	 */
	public String getRNPS() {
		waitForElement(RNPSsec, 10);
		scrollByElement(RNPSsec);
		String RNPS = getRnpsscore(RNPS_Score);
		System.out.println(RNPS);
		return RNPS;
	}
	
	/**
	 * Download CSV file of Location Export
	 * @throws Exception
	 */
	public void LocationExport() throws Exception {
		waitForElement(LocationExport, 10);
		scrollByElement(LocationExport);
		clickelement(LocationExport);
		convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+LocationDataExport));
		Thread.sleep(5000);
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+LocationDataExport), "Sheet0").getExcelTable();
	}
	
	
	/**
	 * Get from date from highcharts section
	 * @return
	 * @throws ParseException
	 */
	public Date getFromDateHighcharts() throws ParseException {
		waitForElement(Highcharts, 10);
		scrollByElement(Highcharts);
		Date fromdate = GetDatehighcharts(From_Date_Review);
		return fromdate;
	}
	
	
	/**
	 * Get To Date from highcharts section
	 * @return
	 * @throws ParseException
	 */
	public Date getToDateHighcharts() throws ParseException {
		waitForElement(Highcharts, 10);
		scrollByElement(Highcharts);
		Date fromdate = GetDatehighcharts(To_Date_Review);
		return fromdate;
	}
	
	/**
	 * Export Reviews and convert csv to xlsx
	 * 
	 */
	
	public void ReviewsExport() throws Exception {
		waitForElement(exportBTN, 10);
		scrollByElement(exportBTN);
		clickelement(exportBTN);
		convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+ReviewExport));
		Thread.sleep(5000);
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+ReviewExport), "Sheet0").getExcelTable();
	}
	
	/**
	 * To Get the List of reference code , Links , Business name , Source
	 * @throws Exception 
	 * 
	 */
	
	public void CompareReviewTableDatawithExport(String Text) throws Exception {
		
		JSWaiter.waitJQueryAngular();
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {	
	    		List < WebElement > rows_table = BusinessName;
	    		int rows_count = rows_table.size();
	    		for (int row = 1; row <=rows_count; row++) { 
	    			scrollByElement(ReviewSection);
	    			String text = driver.findElement(By.xpath("(//*[@id='Review']//div[@class='form-group col-xs-12'])["+ row +"]//*[@id='viewListingLink']")).getAttribute("href");
	    			Thread.sleep(2000);
	    			if(!text.contains("yelp")) {
	    			String BName = driver.findElement(By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])["+ row +"]//div[@class='business-info']//div//strong")).getText();
	    			System.out.println(BName);
	    			Thread.sleep(2000);
	    			String ReferenceNum = driver.findElement(By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])["+ row +"]//div[@class='reference-code']//div[2]")).getText();
	    			System.out.println(ReferenceNum);
	    			Thread.sleep(2000);
	    			String LinksUrl = driver.findElement(By.xpath("(//div[@class='reviewEnhancement']//div[@class='form-group col-xs-12'])["+ row +"]//*[@id='viewListingLink']")).getAttribute("href");
	    			System.out.println(LinksUrl);
	    			Thread.sleep(2000);
	    			String loc = driver.findElement(By.xpath("//*[contains(@class,'myList1')]")).getText().toLowerCase();
	    			if(!Text.equalsIgnoreCase("Null")) {
	    				String locdetails = driver.findElement(By.xpath("(//*[@class='location-separator'])[" + row + "]")).getText().toLowerCase();
	    				Assert.assertTrue("Found", locdetails.contains(loc));
	    			}else {
	    				System.out.println("No filter applied");
	    			}
	    			BusinssName.add(BName);
	    			ReferenceNumber.add(ReferenceNum);
	    			URL.add(LinksUrl);
	    				//System.out.println(text);
	    			}else {
	    				System.out.println("Yelp is displayed");
	    			}
	    			}if(paginationNext.isEnabled()) {
		    			scrollByElement(paginationNext);
		    			paginationNext.click();
		    			Thread.sleep(4000);
		    			}
	    	   	}
			}
			System.out.println("Business Name in Review : " +BusinssName);
			System.out.println("Reference Code in Review :" +ReferenceNumber);
			System.out.println("Link Listing in Review :" +URL);
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String chromepath = "./downloads/chromeReviewExport.xlsx";
			String IEpath = "./downloads/IEReviewExport.xlsx";
			String FFpath = "./downloads/FFReviewExport.xlsx";
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
			}if(CurrentState.getBrowser().equals("IE")){
				XLBname = GetDataUsingColName(IEpath, "Business Name");
				Thread.sleep(1000);
				XLRefCode = GetDataUsingColName(IEpath, "Reference Code");
				Thread.sleep(1000);
				XLURL = GetDataUsingColName(IEpath, "URL");
				Thread.sleep(1000);
				XLSource = GetDataUsingColName(IEpath, "Source");
				Thread.sleep(1000);
			}if(CurrentState.getBrowser().equals("Firefox")){
				XLBname = GetDataUsingColName(FFpath, "Business Name");
				Thread.sleep(1000);
				XLRefCode = GetDataUsingColName(FFpath, "Reference Code");
				Thread.sleep(1000);
				XLURL = GetDataUsingColName(FFpath, "URL");
				Thread.sleep(1000);
				XLSource = GetDataUsingColName(FFpath, "Source");
				Thread.sleep(1000);
			}				
			Assert.assertEquals(XLBname.size()-1, BusinssName.size());
			Assert.assertEquals(XLBname, BusinssName);
			Assert.assertEquals(XLRefCode.size()-1, ReferenceNumber.size());
			Assert.assertEquals(XLRefCode, ReferenceNumber);
			Assert.assertEquals(XLURL.size()-1, URL.size());
			Assert.assertEquals(XLURL, URL);
			Assert.assertEquals(XLSource.size()-1, URL.size());
			if(XLSource.size() == URL.size()) {
				for(int i =0; i<=XLSource.size()-1;i++) {
					XLSource.get(i).contains(URL.get(i));
				}
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To apply source filter
	 * @param text
	 */
	public void applySourceFilter(String Text) {
		JSWaiter.waitJQueryAngular();
		if(Text == null || Text.equalsIgnoreCase("none")) Text = "All";
		try {
			waitForElement(SourceTab, 25);
			waitUntilLoad(driver);
			if(!Text.equals("None")) {			
				clickelement(SourceTab);
				waitForElement(SourceTab, 20);
				action.moveToElement(driver.findElement(By.xpath("//*[@id='filter-area']//input[@value='"+Text+"']"))).click().build().perform();
				waitUntilLoad(driver);
				driver.findElement(By.xpath("//*[@id='Review']//div[@class='row']")).click();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * To Get the source name from dropdown
	 * @return
	 */
	public String getsource() {
		waitForElement(Source, 10);
		scrollByElement(Source);
		String sourcename = Source.getText();
		System.out.println(sourcename);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return sourcename;
	}
	
	/**
	 * To compare the source with Reviews
	 */
	public void comparesourcewithreviews() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();
		String Sourcename = getsource().toLowerCase();		
		System.out.println(Sourcename);
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {		    	
				int size = Reviews.size();
				System.out.println(size);
				for(int j = 1; j<= size; j++) {
					String sourcenme = driver.findElement(By.xpath("(//*[@id='Review']//div//img[@class = 'source-thumb'])["+ j +"]")).getAttribute("src");
					System.out.println(sourcenme);
					Assert.assertTrue("Source is displayed",sourcenme.contains(Sourcename));
				}if(paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					Thread.sleep(4000);
				}		
			}	
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
		clickelement(SourceTab);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[1]"))).click().build().perform();
	}
	
	
	/**
	 * To Get the Tag name from dropdown
	 * @return
	 */
	public String getTag() {
		WebElement tag = driver.findElement(By.xpath("//span[@class='tagit-label']")); 
		waitForElement(tag, 10);
		scrollByElement(tag);
		String sourcename = tag.getText();
		System.out.println(sourcename);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return sourcename;
	}
	
	
	/**
	 * To apply Search tag filter
	 * @param text
	 */
	public void applySearchTagtext(String Text) {
		JSWaiter.waitJQueryAngular();
		if(Text == null || Text.equalsIgnoreCase("none")) Text = "All";
		try {
			waitForElement(SourceTab, 25);
			waitUntilLoad(driver);
			waitForElement(advanceSearch, 10);
			scrollByElement(advanceSearch);
			clickelement(advanceSearch);
			if(!Text.equals("None")) {			
				clickelement(SearchTag);
				SearchTag.sendKeys(Text);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				SearchTag.sendKeys(Keys.ENTER);
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * To compare the Tags with Reviews
	 */
	public void compareTagswithreviews() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();
		String Tag = getTag();		
		System.out.println(Tag);
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {		    	
				int size = Reviews.size();
				System.out.println(size);
				for(int j = 1; j<= size; j++) {
					String Tagname = driver.findElement(By.xpath("(//*[@class='col-lg-7 col-md-9 col-xs-12 reviews-middle-column']//li[@class='tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable']//span[@class='tagit-label'])["+ j +"]")).getText();
					System.out.println(Tagname);
					Assert.assertTrue("Tag is displayed",Tagname.contains(Tag));
				}if(paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					Thread.sleep(4000);
				}		
			}	
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * To Get the Tag name from dropdown
	 * @return
	 */
	public String getKeywords() {
		WebElement tag = driver.findElement(By.xpath("//span[@class='tagit-label']")); 
		waitForElement(tag, 10);
		scrollByElement(tag);
		String sourcename = tag.getText();
		System.out.println(sourcename);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return sourcename;
	}

	/**
	 * To apply Search tag filter
	 * @param text
	 */
	public void applyKeywordSearchtext(String Text) {
		JSWaiter.waitJQueryAngular();
		if(Text == null || Text.equalsIgnoreCase("none")) Text = "All";
		try {
			waitForElement(SourceTab, 25);
			waitUntilLoad(driver);
			waitForElement(advanceSearch, 10);
			scrollByElement(advanceSearch);
			clickelement(advanceSearch);
			if(!Text.equals("None")) {			
				clickelement(KeywordSearch);
				KeywordSearch.sendKeys(Text);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				KeywordSearch.sendKeys(Keys.ENTER);
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To compare the source with Reviews
	 */
	public void compareKeywordswithreviews(String Text) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();		
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {		    	
				int size = Reviews.size();
				System.out.println(size);
				for(int j = 1; j<= size; j++) {
					String Reviews = driver.findElement(By.xpath("(//*[@class='review-content'])["+ j +"]")).getText();
				
					System.out.println(Reviews);
					Assert.assertTrue("Keyword does'nt Present", Reviews.toLowerCase().contains(Text.toLowerCase()));
				}if(paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					Thread.sleep(4000);
				}		
			}	
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
		KeywordSearch.click();
		KeywordSearch.clear();
	}
	
	
	/**
	 * To apply Search tag filter
	 * @param text
	 */
	public void applySentiment(String Text) {
		JSWaiter.waitJQueryAngular();
		if(Text == null || Text.equalsIgnoreCase("none")) Text = "All";
		try {
			waitForElement(SourceTab, 25);
			waitUntilLoad(driver);
			waitForElement(advanceSearch, 10);
			scrollByElement(advanceSearch);
			clickelement(advanceSearch);
			if(!Text.equals("None")) {			
				scrollByElement(SentimentTab);	
				clickelement(SentimentTab);
				driver.findElement(By.xpath("//div[@class='sentiment-score-filter']//div[@class = 'item' and contains(text(), '"+ Text +"')]")).click();
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * To compare the source with Reviews
	 */
	public void compareSentimentswithreviews(String Text) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();		
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		String ReviewsSentiment = null;
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {		    	
				int size = Reviews.size();
				System.out.println(size);
				for(int j = 1; j<= size; j++) {
					String txt = driver.findElement(By.xpath("(//div[contains(@class, 'overall-sentiment-text overall ')])["+ j +"]")).getAttribute("class");
					if(txt.contains("lime")){
						ReviewsSentiment = "Positive";
					}else if(txt.contains("green")){
						ReviewsSentiment = "Positive";
					}else if(txt.contains("yellow")){
						ReviewsSentiment = "Neutral";
					}else if(txt.contains("orange")){
						ReviewsSentiment = "Negative";
					}else if(txt.contains("red")){
						ReviewsSentiment = "Negative";
					}else if(txt.contains("grey")){
						ReviewsSentiment = "Not Available";
					}else{
						System.out.println("No Data Available");
					}
					System.out.println(ReviewsSentiment);
					Assert.assertTrue("Keyword Present", ReviewsSentiment.toLowerCase().contains(Text.toLowerCase()));
				}if(paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					Thread.sleep(4000);
				}		
			}	
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
		clickelement(SentimentTab);
		driver.findElement(By.xpath("(//div[@class='sentiment-score-filter']//div[contains(text(), 'All')])[1]")).click();
	} 	
	
	
	/**
	 * To apply Search tag filter
	 * @param text
	 */
	public void applySentimentCategory(String Text) {
		JSWaiter.waitJQueryAngular();
		if(Text == null || Text.equalsIgnoreCase("none")) Text = "All";
		try {
			waitForElement(SourceTab, 25);
			waitUntilLoad(driver);
			waitForElement(advanceSearch, 10);
			scrollByElement(advanceSearch);
			clickelement(advanceSearch);
			if(!Text.equals("None")) {			
				scrollByElement(SentimentCategoryTab);	
				clickelement(SentimentCategoryTab);
				action.moveToElement(driver.findElement(By.xpath("//*[@class='dropdown sentiment-category-filter']//input[contains(@title, '"+ Text +"')]"))).click().build().perform();
				clickelement(SentimentCategoryTab);
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * To compare the source with Reviews
	 */
	public void compareSentimentsCategorywithreviews(String Text) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();		
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {		    	
				int size = Reviews.size();
				System.out.println(size);
				for(int j = 1; j<= size; j++) {
					String ReviewsSentimentCategory = driver.findElement(By.xpath("(//div[contains(@class,'custom-tooltip')])["+ j +"]")).getAttribute("class");
					if(ReviewsSentimentCategory.contains(Text)) {
						assert(!ReviewsSentimentCategory.contains("grey"));
						Assert.assertTrue("Sentiment Category Not Found", ReviewsSentimentCategory.toLowerCase().contains(Text.toLowerCase()));
					}
				}if(paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					Thread.sleep(4000);
				}		
			}	
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
		clickelement(SentimentCategoryTab);
		action.moveToElement(driver.findElement(By.xpath("(//*[@class='dropdown sentiment-category-filter']//input[contains(@title, 'All')])"))).click().build().perform();
	} 	
	
}
