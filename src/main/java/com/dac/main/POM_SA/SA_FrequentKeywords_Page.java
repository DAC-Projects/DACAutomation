package com.dac.main.POM_SA;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;
import com.google.common.collect.Ordering;

import resources.CurrentState;
import resources.DateFormats;
import resources.ExcelHandler;
import resources.IAutoconst;
import resources.JSWaiter;

public class SA_FrequentKeywords_Page extends BasePage{
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	@FindBy(id="dateFrom")
	private WebElement fromDataTB;
	
	@FindBy(id="dateTo")
	private WebElement toDataTB;
	
	@FindBy(id="ulReportTab")
	private WebElement reviewsTab;

	@FindBy(xpath="//*[@id='Review']//dl[contains(@class,'source-filter')]")
	private WebElement filterSourceTB;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='dropdown star-filter']")
	private WebElement filterRatingTB;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='searchByContent']")
	private WebElement filterContentTB;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='sentiment-score-filter']")
	private WebElement filterSentimentTB;
	
	@FindBy(xpath="//*[@id='Review']//*[contains(@class,'sentiment-category-filter')]")
	private WebElement filterSentimentCategoryTB;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='sortingSelect']")
	private WebElement filterSortByTB;
	
	@FindBy(xpath="//*[@id='Review']//*[@id='filter-search']")
	private WebElement advanceSearch;
	
	@FindBy(xpath="//*[@id='Review']//ul[@class='source-filter-items']")
	private WebElement filterSourceDropDown;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='dropdown source-filter']//*[@class='selected']")
	private List<WebElement> selectedSource;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='dropdown star-filter']//*[@class='selected']")
	private List<WebElement> selectedRating;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='dropdown sentiment-category-filter']//*[@class='selected']")
	private List<WebElement> selectedSentimentCategory;
	
	@FindBy(className="ownerResponse")
	private WebElement ownerResponseDD;
	
	@FindBy(id="btnReportExportWordCloud")
	private WebElement exportBTN;
	
	@FindBy(id="btnApply")
	private WebElement btnApply;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='reference-code']/div[2]")
	private List<WebElement> refIDs;
	
	@FindBy(xpath="//*[@id='Review']//*[@id='viewListingLink']")
	private List<WebElement> viewListingLink;
	
	@FindBy(xpath="//*[@class='business-info']")
	private List<WebElement> locInfo;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='review-content']")
	private List<WebElement> reviewContent;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='date-text']")
	private List<WebElement> reviewDate;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='author']")
	private List<WebElement> authorData;
	
	@FindBy(id="lastPage")
	private WebElement paginationLastPageBTN;
	
	@FindBy(id="firstPage")
	private WebElement paginationFirstPageBTN;
	
	@FindBy(id="nextPage")
	private WebElement paginationNextPageBTN;
	
	@FindBy(id="prevPage")
	private WebElement paginationPreviousPageBTN;
	
	@FindBy(xpath="//*[@id='nextPage']/../preceding-sibling::li[1]/a")
	private WebElement LastPageBTN;
	
	@FindBy(css=".message.no-review")
	private WebElement noReviewOverallmsg;
	
	@FindBy(css=".message.single-review")
	private WebElement singleReviewOverallmsg;
	
	@FindBy(css=".message.multi-review")
	private WebElement multiReviewOverallmsg;
	
	@FindBy(id="negative-recommendations")
	private WebElement negativeRecommendations;
	
	@FindBy(id="positive-recommendations")
	private WebElement PositiveRecommendations;
	
	@FindBy(id="popOverRecommendation")
	private WebElement recommendationsPopUp;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='source-filter-items']/li/label")
	private List<WebElement> sourceList;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='dropdown source-filter']")
	private WebElement sourceDropDown;
	
	//-----------------------------------------------------------
	
	@FindBy(xpath = "//*[@class='business-info']//span[@itemprop='name']")
	private List<WebElement> locationName;
	
	/*@FindBy(id="popOverRecommendation")
	private WebElement addressLines;*/
	
	@FindBy(xpath = "//*[@class='business-info']//span[@itemprop='addressLocality']")
	private List<WebElement> city;
	
	@FindBy(xpath = "//*[@class='business-info']//span[@itemprop='addressRegion']")
	private List<WebElement> region;
	
	@FindBy(xpath = "//*[@class='business-info']//span[@itemprop='postalCode']")
	private List<WebElement> zipCode;
	
	@FindBy(xpath = "//*[@class='business-info']//span[@itemprop='telephone']")
	private List<WebElement> phoneNumber;
	
	
	static int numberOfReviews = 0, numberOfStarReviews = 0;
	static int sumOfAllStars = 0,  numberOfRecommends = 0, numberOfNonRecommends = 0;
	
	public SA_FrequentKeywords_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	/** 
	 * This method is used to click on Export the excel in RRC page		*/
	public void clickExportBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
		wait.until(ExpectedConditions.visibilityOf(exportBTN));
		scrollByElement(exportBTN);
		if(exportBTN.isEnabled() & exportBTN.isDisplayed()) {
			action.moveToElement(exportBTN).click(exportBTN).perform();
			Thread.sleep(5000);
			//System.out.println("downloaded file name: "+getLastModifiedFile("./downloads"));
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
		}else {
			System.out.println("No Data Available in Review Table");
		}
	}
	
	public boolean isDataAvailable() {
		JSWaiter.waitJQueryAngular();
		if(driver.findElement(By.xpath("//*[@id='Review']//*[@id='paginationInfo']")).isDisplayed()) {
			return true;
		}
		else if(driver.findElement(By.xpath("//*[@id='Review']//*[@id='noData']")).isDisplayed()) {
			return false;
		}
		return false;
	}
	
	/**
	 * @param dateInFormat : will add in the format of the dashboard language date format 
	 * 						 ex: for en_US --> MM/DD/YYYY	
	 * @throws InterruptedException */
	public void addFromDate(String dateInFormat) throws InterruptedException {
		JSWaiter.waitJQueryAngular();
//		Thread.sleep(3000);
		fromDataTB.click();
		fromDataTB.clear();
//		System.out.println(dateInFormat);
////		fromDataTB.sendKeys(Keys.CLEAR);
//		fromDataTB.sendKeys(dateInFormat);
//		Thread.sleep(3000);
//		fromDataTB.sendKeys(Keys.ENTER);
//		applyFilter();
	}
		
	public void isUrlWorking(String urlString) throws MalformedURLException, IOException {
	    URL u = new URL(urlString); 
	    HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
	    huc.setRequestMethod("GET"); 
	    huc.connect(); 
	     String a = huc.getResponseCode()+"";
	    if(a.charAt(0) == 4 || a.charAt(0) == 5) Assert.fail(urlString+ " not working");
	    
	   // return huc.getResponseCode();
	}
	
	//working
	private int getTotalPagesPagination() {
		clickelement(paginationLastPageBTN);
		JSWaiter.waitJQueryAngular();
		String numOfPages = LastPageBTN.getText();
		clickelement(paginationFirstPageBTN);
		return Integer.parseInt(numOfPages);
	}
	
	public List<String> getSourceList() {
		JSWaiter.waitJQueryAngular();
		List<String> sourceLst = new ArrayList<String>();
		clickelement(sourceDropDown);
		for(int i = 0; i<sourceList.size(); i++) {
			sourceLst.add(sourceList.get(i).getText());
			System.out.println(sourceList.get(i).getText());
		}
		return sourceLst;
	}
	
	public ArrayList<String> allLocationInfo() throws InterruptedException, MalformedURLException, IOException {
		JSWaiter.waitJQueryAngular();
		Thread.sleep(3000);
		HashMap<Integer,ArrayList<String>> a = new HashMap<Integer,ArrayList<String>>();
					 		
		int totPages = getTotalPagesPagination();
		System.out.println("totPages : "+totPages);
		ArrayList<String> links,source, dataExport;
		links = new ArrayList<String>(); source= new ArrayList<String>(); dataExport = new ArrayList<String>();
		ArrayList<String> totData = new ArrayList<String>();
		int count = 1;
		List<String> lst = getSourceList();
		
		for(int page=1; page<=totPages;page++) {
			scrollByElement(exportBTN);
			String refID, review;
			JSWaiter.waitJQueryAngular();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			for(WebElement ListingLink : viewListingLink) {
				String s="";
				String link = ListingLink.getAttribute("href");
				System.out.println("link : "+link);
				links.add(link);
				if (link.contains("foursquare")) s = "foursquare";
				else s = link.substring(link.indexOf(".")+1,link.indexOf(".", link.indexOf(".")+1));
				for(String b:lst) { if(b.equalsIgnoreCase(s)) s = b; }
				System.out.println(s);
				source.add(s);  //this will not work for "yellowPages.com"
			}
			
			for(int i=0; i<viewListingLink.size();i++) {
				
				int reviewNum = i + 1;
				try 
				{ 
					driver.findElement(By.xpath("(//*[@class='business-info'])["+reviewNum+"]/../div[@class='reference-code']/div[2]")).isDisplayed();
					refID = driver.findElement(By.xpath("(//*[@class='business-info'])["+reviewNum+"]/../div[@class='reference-code']/div[2]")).getText();
				} catch(Exception e) { refID = ""; }
				
				review = getReview(reviewNum);
				//if(!(source.get(numberOfReviews)).equalsIgnoreCase("yelp"))
				String rating =	getRatings(source.get(numberOfReviews), reviewNum), dupRating="";
				isUrlWorking(links.get(i));
				String locationData = locationInfo(i);
				String author = (authorData.get(i).getText().split("\n"))[0].trim();
				
				try
				{ 
					String src = source.get(numberOfReviews);
					if(!((src).equals("foursquare"))) {
						Integer.parseInt(rating);
						dupRating = ",\""+rating+"\",\"\",\"";
						numberOfStarReviews++;
					}else if(source.get(numberOfReviews).equals("foursquare")) dupRating = "\",\"\",\"";
				} catch(Exception e) {
					if (rating.equals("Recommends")) numberOfRecommends++;
					else if(rating.equals("Doesn't Recommend")) numberOfNonRecommends++;
					dupRating = ",\"\",\""+rating+"\"";
				} 
				
				if(!source.get(numberOfReviews).equalsIgnoreCase("yelp")) {
					System.out.println("count : "+count);
					String data = "\""+refID+"\",\""+source.get(numberOfReviews)+"\",\""+links.get(i)+"\","+
							locationData+",\" "+reviewDate.get(i).getText()+"\",\""+review+"\",\""+author+"\""+dupRating;
					System.out.println("exportedData : "+ data);
					dataExport.add(data);
					a.put(count++, totData);
				}
				System.out.println("-----------------------------");
				numberOfReviews++;
			}
			clickelement(paginationNextPageBTN);
		}
		
		System.out.println("total Data size after excluding \"yelp\" : "+a.size());
		System.out.println("number of reviews : "+numberOfReviews+" numberOfRecommends : "+numberOfRecommends);
		System.out.println("sumOfAllStars : "+sumOfAllStars+" numberOfNonRecommends : "+numberOfNonRecommends);
		System.out.println("numberOfStarReviews : "+numberOfStarReviews);
		calReviews();	calRecommendationsCount();
		return dataExport;
	}
	
	public ArrayList<Integer> getSortedRatings() {
		int totPages = getTotalPagesPagination();
		ArrayList<Integer> ratingList=new ArrayList<>();
		ArrayList<Integer> exportRatingList=new ArrayList<>();
		List<WebElement> stars = new ArrayList<>();
		String rating = "0";
		
		for(int page=1; page<=totPages;page++) {
			
			for(WebElement ListingLink : viewListingLink) {
				String link = ListingLink.getAttribute("href");
				for(int i=0;i<reviewDate.size();i++) {
					if(link.contains("foursquare")) {
						rating = "0";
						ratingList.add(Integer.parseInt(rating));
						exportRatingList.add(Integer.parseInt(rating));
					}
					else if(!link.contains("yelp")) {
						try{
							rating = driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+(i + 1)+"]/span/div/span")).getText();  //for facebook
							if(rating.equals("Recommends")) rating = "5";
							else if(rating.equals("Doesn't Recommend")) rating = "1";
						} catch(Exception e){
							stars = driver.findElements(By.xpath("(//*[@id='Review']//*[@class='author'])["+(i + 1)+"]/span/div/i[contains(@class,'yellow')]"));
							sumOfAllStars = sumOfAllStars + stars.size();
							rating = ""+stars.size();
						}
						ratingList.add(Integer.parseInt(rating));
						exportRatingList.add(Integer.parseInt(rating));
						System.out.println("Rating : "+rating);
					}else {
						List<WebElement> yelpStars = driver.findElements(By.xpath("(//*[@id='Review']//*[@class='author'])["+(i + 1)+"]/span/div/img[not(contains(@src,'grey'))]"));
						//System.out.println("yelpStars.size() : "+yelpStars.size());
						sumOfAllStars = sumOfAllStars + yelpStars.size();
						rating = ""+stars.size();
						ratingList.add(Integer.parseInt(rating));
					}
				}
			}
			
			clickelement(paginationNextPageBTN);
		}
		return ratingList;
	}
	
	public ArrayList<String> getLocationNameSort() {
		int totPages = getTotalPagesPagination();
		ArrayList<String> locationNameList=new ArrayList<>();
		System.out.println("totPages : "+totPages);
		for(int page=1; page<=totPages;page++) {
			for(int i=0;i<reviewDate.size();i++) {
				locationNameList.add(locationName.get(i).getText().trim());
			}
			clickelement(paginationNextPageBTN);
		}
		return locationNameList;
	}
	
	// ascending order 
	public void checkASCLocationNameOrder(ArrayList<String> locationNameList) {
		JSWaiter.waitJQueryAngular();
		ArrayList<String> locationNames = new ArrayList<>(locationNameList);
		ArrayList<String> duplocationNames = new ArrayList<>(locationNameList);
		Collections.sort(locationNames);
		Assert.assertEquals(locationNames, duplocationNames);
	}
	
	
	public ArrayList<Date> getAllReviewDatesSort() {
		int totPages = getTotalPagesPagination();
		ArrayList<Date> reviewDateList=new ArrayList<>();
		SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("totPages : "+totPages);
		for(int page=1; page<=totPages;page++) {
			for(int i=0;i<reviewDate.size();i++) {
				try {
					reviewDateList.add(format.parse(reviewDate.get(i).getText().trim()));
				} catch (ParseException e) {  e.printStackTrace(); 	}
			}
			clickelement(paginationNextPageBTN);
		}
		return reviewDateList;
	}
	
	// ascending order - Star Rating - Highest to Lowest
	public void checkASCRatingOrder(ArrayList<Integer> ratingList) throws ParseException {
		JSWaiter.waitJQueryAngular();
		ArrayList<Integer> rating = new ArrayList<>(ratingList);
		ArrayList<Integer> dupRating = new ArrayList<>(ratingList);
		Collections.sort(rating);
		Assert.assertEquals(rating, dupRating);
	}
	
	// ascending order - Star Rating - Lowest to Highest 
		public void checkDSCRatingOrder(ArrayList<Integer> ratingList) throws ParseException {
			JSWaiter.waitJQueryAngular();
			ArrayList<Integer> rating = new ArrayList<>(ratingList);
			ArrayList<Integer> dupRating = new ArrayList<>(ratingList);
			Collections.sort(rating, Collections.reverseOrder());
			Assert.assertEquals(rating, dupRating);
		}
		
		
	
	// ascending order - older to new
	public void checkASCDateOrder(ArrayList<Date> reviewDateList) throws ParseException {
		JSWaiter.waitJQueryAngular();
		ArrayList<Date> date=new ArrayList<>(reviewDateList);
		ArrayList<Date> dupDate = new ArrayList<>(reviewDateList);
		Collections.sort(date);
		Assert.assertEquals(date, dupDate);
	}
	
	// descending order - new to older
	public void checkDSCDateOrder(ArrayList<Date> reviewDateList) throws ParseException {
		JSWaiter.waitJQueryAngular();
		ArrayList<Date> date=new ArrayList<>(reviewDateList);
		ArrayList<Date> dupDate = new ArrayList<>(reviewDateList);
		Collections.sort(date, Collections.reverseOrder());
		Assert.assertEquals(date, dupDate);
	}
	
	private String locationInfo(int reviewNum) {
		int a = reviewNum + 1;
		String addL="",locRegion="", locZip = "", locCity = "", locPhone = "";
		List<WebElement> addressLines = driver.findElements(By.xpath("(//*[@class='business-info'])["+a+"]//span[@itemprop='streetAddress']/span"));
		for(WebElement addressLine : addressLines) {
			if ((addL.length()) == 0) addL = addL + addressLine.getText();
			else addL = addL+","+ addressLine.getText();
		}
		try { locRegion = region.get(reviewNum).getText();
		}catch(IndexOutOfBoundsException e) {locRegion="";}
		
		try { locZip = zipCode.get(reviewNum).getText();
		}catch(IndexOutOfBoundsException e) {locZip="";}
		
		try { locCity = city.get(reviewNum).getText();
		}catch(IndexOutOfBoundsException e) {locCity="";}
		
		try { locPhone = phoneNumber.get(reviewNum).getText();
		}catch(IndexOutOfBoundsException e) {locPhone="";}
		
		System.out.println("region.size() : "+ region.size()+" zipCode.size() : "+zipCode.size());
		String location = "\""+locationName.get(reviewNum).getText()+"\",\""+addL+"\",\""+locCity+"\",\""+locRegion+"\",\""+locZip
							+"\",\""+locPhone+"\"";
		return location;
	}
	
	public void checkRecommendationsToolTipTxt(String eText) {
		scrollByElement(recommendationsPopUp);
		action.moveToElement(recommendationsPopUp).perform();
		JSWaiter.waitJQueryAngular();
		verifyText(recommendationsPopUp, eText);
	}
	
	private void calRecommendationsCount() {
		Assert.assertEquals(PositiveRecommendations.getText().trim(), numberOfRecommends+"\n"+"Positive");
		Assert.assertEquals(negativeRecommendations.getText().trim(), numberOfNonRecommends+"\n"+"Negative");
	}
	
	private void calReviews() {
		String avgRev = "";
		DecimalFormat newFormat = new DecimalFormat("#.#");
		if(isDataAvailable()) {
			double oneDecimal =  Double.valueOf(newFormat.format(sumOfAllStars/
					(double)numberOfStarReviews));
			if(sumOfAllStars==0) avgRev = "NA"; else avgRev = ""+oneDecimal;
			if(numberOfReviews==1) {
				Assert.assertEquals(singleReviewOverallmsg.getText().trim(), "Score of "+avgRev+" from "+numberOfReviews+" review.");
				System.out.println("singleReviewOverallmsg.getText().trim() : "+singleReviewOverallmsg.getText().trim());

			}
			else {
				Assert.assertEquals(multiReviewOverallmsg.getText().trim(), oneDecimal+" average over "+numberOfReviews+" reviews");
				System.out.println("multiReviewOverallmsg.getText().trim() : "+multiReviewOverallmsg.getText().trim());
			}
			
			System.out.println("totReviews : "+oneDecimal);
		}
		else {
			Assert.assertEquals(noReviewOverallmsg.getText().trim(), "No reviews found.");
			System.out.println("totReviews : "+noReviewOverallmsg.getText().trim());
		}
	}

	private String getRatings(String source, int reviewNum) {
		List<WebElement> stars = new ArrayList();
		String rating = "";
		if(!source.equalsIgnoreCase("yelp")) {
			System.out.println("source in getRating method : "+source);
			try{
				rating = driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/span/div/span")).getText();
			} catch(Exception e){
				stars = driver.findElements(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/span/div/i[contains(@class,'yellow')]"));
				sumOfAllStars = sumOfAllStars + stars.size();
				rating = ""+stars.size();
				
			}
			System.out.println("Rating : "+rating);
		}else {
			List<WebElement> yelpStars = driver.findElements(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/span/div/img[not(contains(@src,'grey'))]"));
			System.out.println("yelpStars.size() : "+yelpStars.size());
			sumOfAllStars = sumOfAllStars + yelpStars.size();
			rating = ""+stars.size();
		}
		return rating;
	}
	
	public void applyFilter() {
		btnApply.click();
		
	}
	
	public String getFKScore(String keyword) {
		
		WebElement source = driver.findElement(By.xpath("//*[@id='termAnalysis']//span[text()='"+keyword+"']"));
		String score=source.getAttribute("title");
//		System.out.println(score);
		return score;
	}
	
	private String getReview(int reviewNum) {
		String review;
		boolean revExist;
		JSWaiter.waitJQueryAngular();
		try { revExist = driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/../../following-sibling::div[2]")).isDisplayed(); }
		catch(NoSuchElementException e) {revExist = false; }
		
		if(revExist) {
			boolean lRev;
			try {
				clickelement(driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/../../following-sibling::div[2]/a")));						
				//driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/../../following-sibling::div[2]/a"));
				Thread.sleep(1000);
				lRev= true;
			} catch(Exception e) {lRev = false;}
			if(lRev) {
				review = driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/../../following-sibling::div[3]")).getText();
				System.out.println("review.length(): "+review.length()+"review :"+review);
				review = (review.substring(0, (review.length()-10))).trim();
			}
			else review = driver.findElement(By.xpath("(//*[@id='Review']//*[@class='author'])["+reviewNum+"]/../../following-sibling::div[2]")).getText();
		}
		else review = "";
		return review;
	}
	
	//working
	public String selectSort(String sortBy) {
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		clickelement(filterSortByTB);
		String selectedOpt = driver.findElement(By.xpath("//*[@id='Review']//*[@class='sortingSelect']//div[@class='text']")).getText().trim();
		if(!selectedOpt.equals(sortBy)) {
			clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='sortingSelect']//*[@class='item' and text()='"+sortBy+"']")));
			clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@id='sortingText']")));			
		}
		return sortBy.trim();
	}
	
	//working
	public void deselectSentimentCategory() throws InterruptedException { 
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		String[] selectedRatings = new String[selectedSentimentCategory.size()];
		for(int i=0;i<selectedSentimentCategory.size();i++) {
			selectedRatings[i] = selectedSentimentCategory.get(i).getText();
		}
		clickelement(filterSentimentCategoryTB);
		for(String deselectRating: selectedRatings) {
			WebElement source = driver.findElement(By.xpath("//*[@id='Review']//*[@type='checkbox' and @title='"+deselectRating+"']/../label"));
			clickelement(source);
			Thread.sleep(1000);
		}
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Sentiment Category']")));
	}
	
	//working
	public void selectSentimentCategory(String[] Options) throws InterruptedException { 
		JSWaiter.waitJQueryAngular(); 
		scrollByElement(reviewsTab);
		if(!filterSentimentCategoryTB.isDisplayed()) clickelement(advanceSearch);
		clickelement(filterSentimentCategoryTB);
		Thread.sleep(2000);
		for(String selectOption: Options) {
			WebElement source = driver.findElement(By.xpath("//*[@id='Review']//*[@type='checkbox' and @title='"+selectOption+"']/../label"));
			clickelement(source);
			Thread.sleep(1000);
		}
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Sentiment Category']")));
	}
	
	//working
	public void selectSentiment(String sentiment) {
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		if(!filterSentimentCategoryTB.isDisplayed()) clickelement(advanceSearch);
		clickelement(filterSentimentTB);
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='sentiment-score-filter']//*[@class='item' and text()='"+sentiment+"']")));
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Sentiment']")));
	}
	
	//working
	public void selectContent(String content) {
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		clickelement(filterContentTB);
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='searchByContent']//*[contains(@class,'item') and text()='"+content+"']")));
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Content']")));
	}
	
	//working
	public void selectOwnerResponse(String response) {
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		clickelement(ownerResponseDD);
		clickelement(driver.findElement(By.xpath("//*[@class='item' and text()='"+response+"']")));
		clickelement(driver.findElement(By.xpath("//*[@class='text-default' and text()='Owner Response']")));
	}
	
	//working
	public void selectRating(String[] Options) throws InterruptedException { 
		JSWaiter.waitJQueryAngular(); 
		scrollByElement(reviewsTab);
		clickelement(filterRatingTB);
		Thread.sleep(2000);
		for(String selectOption: Options) {
			if(selectOption.equals("No Rating")) selectOption = "0";
			WebElement source = driver.findElement(By.xpath("//*[@id='Review']//*[@type='checkbox' and @title='"+selectOption+"']/../label"));
			clickelement(source);
			Thread.sleep(1000);
		}
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Rating']")));
	}
	
	//working
	public void deselectAllRatings() throws InterruptedException { 
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		String[] selectedRatings = new String[selectedRating.size()];
		for(int i=0;i<selectedRating.size();i++) {
			selectedRatings[i] = selectedRating.get(i).getText();
		}
		clickelement(filterRatingTB);
		for(String deselectRating: selectedRatings) {
			WebElement source = driver.findElement(By.xpath("//*[@id='Review']//*[@type='checkbox' and @title='"+deselectRating+"']/../label"));
			clickelement(source);
			Thread.sleep(1000);
		}
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Rating']")));
	}
	
	//working
	public void deselectAllSource() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		String[] forOptions = new String[selectedSource.size()];
		for(int i=0;i<selectedSource.size();i++) {
			forOptions[i] = selectedSource.get(i).getText();
		}
		clickelement(filterSourceTB);
		for(String deselectOption: forOptions) {
			WebElement source = driver.findElement(By.xpath("//*[@id='Review']//label[text()='"+deselectOption+"']"));
			clickelement(source);
			Thread.sleep(1000);
		}
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Source']")));
	}
	
	
	//working
	public void selectSource(String[] forOptions) throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		scrollByElement(reviewsTab);
		clickelement(filterSourceTB);
		for(String selectOption: forOptions) {
			WebElement source = driver.findElement(By.xpath("//*[@id='Review']//label[text()='"+selectOption+"']"));
			clickelement(source);
			Thread.sleep(1000);
		}
		clickelement(driver.findElement(By.xpath("//*[@id='Review']//*[@class='text-default' and text()='Source']")));
	}
}
