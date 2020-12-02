package com.dac.main.POM_SA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import resources.BaseClass;
import resources.CurrentState;
import resources.JSWaiter;

public class Reviews_Insights extends SA_Abstarct_Methods {

	public WebDriver driver;
	public Actions action;
	WebDriverWait wait;
	SoftAssert soft = new SoftAssert();

	static double averageStar;
	static int TotReviews;
	static int PosReco;
	static int NegReco;
	static int Reviewresponse;
	static int reviewNoresponse;
	static String RNPSscore;
	static double KPIreviewScore;
	static String KPIrnpsScore;
	static int OneStarCount;
	static int TwoStarCount;
	static int ThreeStarCount;
	static int FourStarCount;
	static int FiveStarCount;
	static int totalStarCount;
	static int totThStarCount;
	static double PromotorScore;
	static double DeTractorScore;

	public Reviews_Insights(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='kpi_reviews_count_box']")
	private WebElement KPIReviewScore;

	@FindBy(xpath = "//div[@id='reviewNPS']//span[@id='nps_score_kpi_value']")
	private WebElement KPIRNPSScore;

	@FindBy(xpath = "//li[@id='reviews-new']")
	private WebElement ReviewsSection;

	@FindBy(xpath = "//li[@id='reviews_insight']")
	private WebElement ReviewsInsights;

	@FindBy(xpath = "//*[@id='export-text-notices']")
	private WebElement Notices;

	@FindBy(xpath = "//*[@class='tooltip-container-Notices']//p")
	private WebElement Noticestext;

	@FindBy(xpath = "//*[@id='popOverSummary']")
	private WebElement ReviewsTooltip;

	@FindBy(xpath = "//*[@class='popover fade top in']")
	private WebElement Tootiptext;

	@FindBy(xpath = "//*[@id='popOverRecommendation']")
	private WebElement RecommendationTooltip;

	@FindBy(xpath = "//*[@id='popOverResponses']")
	private WebElement ResponsesTooltip;

	@FindBy(xpath = "//*[@id='nps-learn-more']")
	private WebElement LearnMore;

	@FindBy(xpath = "(//div[@class='modal-content']//div[@class='modal-body'])[1]")
	private WebElement LearnContent;

	@FindBy(xpath = "(//div[@class='modal-content']//button[@class='close'])[1]")
	private WebElement close;

	@FindBy(xpath = "//button[@id='btnLocationNPSExport']")
	private WebElement ExportBtn;

	@FindBy(xpath = "//div[@class='message multi-review']//span[@class='average-score']")
	private WebElement AverageStarRating;

	@FindBy(xpath = "//div[@class='message multi-review']//span[@class='count']")
	private WebElement TotalReviews;

	@FindBy(xpath = "//span[@id='positive-rec-count']")
	private WebElement PositiveRecommendation;

	@FindBy(xpath = "//span[@id='negative-rec-count']")
	private WebElement NegativeRecommendation;

	@FindBy(xpath = "//span[@id='responded-rec-count']")
	private WebElement ReviewsResponed;

	@FindBy(xpath = "//span[@id='notresponded-rec-count']")
	private WebElement ReviewsNotResponded;

	@FindBy(xpath = "//span[@id='npsscore']")
	private WebElement RNPSScore;

	@FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-0')]//*[name()='rect']")
	private List<WebElement> RecommendedChart;

	String Recochart = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-0')]//*[name()='rect']";

	@FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-1')]//*[name()='rect']")
	private List<WebElement> FiveStarChart;

	String Fivestarchart = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-1')]//*[name()='rect']";

	@FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-2')]//*[name()='rect']")
	private List<WebElement> ThreeStarChart;

	String Threestarchart = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-2')]//*[name()='rect']";

	@FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-3')]//*[name()='rect']")
	private List<WebElement> OneStarChart;

	String Onestarchart = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-3')]//*[name()='rect']";

	@FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-5')]//*[name()='rect']")
	private List<WebElement> NotAvailableChart;

	String Notavailablechart = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-5')]//*[name()='rect']";

	@FindBy(xpath = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-4')]//*[name()='rect']")
	private List<WebElement> NotRecommendedChart;

	String Notrecochart = "//*[name()='g' and contains(@class,'highcharts-series highcharts-series-4')]//*[name()='rect']";

	@FindBy(xpath = "//div[@class='highcharts-label highcharts-tooltip highcharts-color-undefined']//span//span[@class='review-count']")
	private WebElement ToolTip;

	@FindBy(xpath = "//span[@id='fromDate']")
	private WebElement FromDateChart;

	@FindBy(xpath = "//span[@id='toDate']")
	private WebElement ToDateChart;

	@FindBy(xpath = "(//div[@class='serieName' and contains(text(),'Recommended')])[1]")
	private WebElement RecommendHighChart;

	@FindBy(xpath = "//div[@class='serieName' and contains(text(),'4-5 Stars')]")
	private WebElement FourStarsHighChart;

	@FindBy(xpath = "//div[@class='serieName' and contains(text(),'3 Stars')]")
	private WebElement ThreeStarsHighChart;

	@FindBy(xpath = "//div[@class='serieName' and contains(text(),'1-2 Stars')]")
	private WebElement OneStarHighChart;

	@FindBy(xpath = "//div[@class='serieName' and contains(text(),'Not Recommended')]")
	private WebElement NotRecoHighChart;

	@FindBy(xpath = "//div[@class='serieName' and contains(text(),'Not Available')]")
	private WebElement NotAvailHighChart;

	@FindBy(xpath = "//input[@id='dateFrom']")
	private WebElement FromDate;

	@FindBy(xpath = "//input[@id='dateTo']")
	private WebElement ToDate;

	@FindBy(xpath = "(//span[@id='review_totalcounts'])[1]")
	private WebElement ReviewCount;

	@FindBy(xpath = "(//span[@id='unaggregated_totalCounts'])[1]")
	private WebElement ReviewExclude;

	@FindBy(xpath = "//*[@id='Review']//dl[contains(@class,'dropdown star-filter')]")
	private WebElement RatingFilter;

	@FindBy(xpath = "//div[@class='ownerResponse']")
	private WebElement Response;

	String chromepath = "./downloads/chromeLocationDataExportInsights.csv";
	String IEpath = "./downloads/IELocationDataExportInsights.csv";
	String FFpath = "./downloads/FFLocationDataExportInsights.csv";

	/**
	 * Get KPI Dashboard Score - Review Score
	 * 
	 * @return
	 */
	public double getKPIReviewScore() {
		waitForElement(KPIReviewScore, 10);
		scrollByElement(KPIReviewScore);
		String KPIScore = KPIReviewScore.getText();
		System.out.println("The String value of KPI Reviews Score is :" + KPIScore);
		KPIScore = KPIScore.substring(0, KPIScore.lastIndexOf("s") - 5);
		KPIreviewScore = Double.parseDouble(KPIScore);
		System.out.println("The integer value of KPI Review Score is :" + KPIreviewScore);
		return KPIreviewScore;
	}

	/**
	 * Get KPI Dashboard Score - RNPS Score
	 * 
	 * @return
	 */
	public String getKPIRNPS() {
		waitForElement(KPIRNPSScore, 10);
		scrollByElement(KPIRNPSScore);
		KPIrnpsScore = KPIRNPSScore.getText();
		System.out.println("The value of RNPS score before getting substring is :" + KPIrnpsScore);
		return KPIrnpsScore;
	}

	/**
	 * Verify report tab highlight
	 */
	public void Review_Insights_Highlight() {
		reporthighlight(ReviewsInsights, ReviewsSection);
	}

	/**
	 * Verify Notice ToolTip Text
	 */
	public void NoticeText() {
		waitForElement(Notices, 10);
		scrollByElement(Notices);
		action.moveToElement(Notices).click().perform();
		String NoticeText = Noticestext.getAttribute("innerText").trim();
		System.out.println("The Notice Text is :" + NoticeText);
		Assert.assertEquals(NoticeText,
				"Google reviews were disabled for all accounts from March 20th to April 9th 2020 due to Covid19 restrictions.");
	}

	/**
	 * Verify Tootip Text
	 */
	public void tooltiptext() {
		waitForElement(ReviewsTooltip, 10);
		scrollByElement(ReviewsTooltip);
		action.moveToElement(ReviewsTooltip).click().perform();
		String ReviewTooltiptext = Tootiptext.getText().trim();
		System.out.println("The tooltiptext is :" + ReviewTooltiptext);
		soft.assertEquals(ReviewTooltiptext,
				"Average Star Rating sources include Google, YP.ca, Yelp, YP.com, Superpages and Facebook*.\n"
						+ "The total number of reviews includes reviews from all sources (with and without star rating)\n"
						+ "*Facebook reviews containing star ratings exist mostly prior to May 2018");
		scrollByElement(RecommendationTooltip);
		action.moveToElement(RecommendationTooltip).click().perform();
		String RecommendationTootiptext = Tootiptext.getText();
		System.out.println("The tooltiptext is :" + RecommendationTootiptext);
		soft.assertEquals(RecommendationTootiptext, "Source includes Facebook");
		scrollByElement(ResponsesTooltip);
		action.moveToElement(ResponsesTooltip).click().perform();
		String ResponseTooltipText = Tootiptext.getText();
		System.out.println("The tooltiptext is :" + ResponseTooltipText);
		soft.assertEquals(ResponseTooltipText,
				"Responses include those submitted on the source websites and the dashboard for all sources.");
		soft.assertAll();
	}

	/**
	 * Verify Learn Text ToolTip
	 */
	public void verifyLearntext() {
		waitForElement(LearnMore, 10);
		scrollByElement(LearnMore);
		clickelement(LearnMore);
		waitForElement(LearnContent, 10);
		scrollByElement(LearnContent);
		String Text = LearnContent.getText();
		System.out.println("The Learn Text is :" + Text);
		Assert.assertEquals(Text, "What is Review Net Promoter Score?\n"
				+ "Review Net Promoter Score, or rNPS measures customer experience and predicts business growth. It is a calculation of how likely a person is to recommend a brand to a friend or colleague.\n"
				+ "Responses are grouped into 3 main areas Promoters, Passives & Detractors. Subtracting the percentage of Detractors from the percentage of Promoters yields the Review Net Promoter Score, which can range from a low of -100 (if every customer is a Detractor) to a high of 100 (if every customer is a Promoter). Any score over 0% is indicative that a brand has more promoters than detractors. The median average for any industry is a score of 16% and examples of real-life scores are Tesla with 97% and MasterCard with 5%.\n"
				+ "Calculation: rNPS = % Promoters (5 star reviews) - % Detractors (1, 2 and 3 star reviews)\n"
				+ "Note: Facebook Recommendations are not included in rNPS");
		clickelement(close);
	}

	/**
	 * Get Average star rating
	 * 
	 * @return
	 */
	public double AverageStar() {
		waitForElement(AverageStarRating, 10);
		scrollByElement(AverageStarRating);
		String avgscr = AverageStarRating.getText();
		System.out.println("The average score string value is :" + avgscr);
		averageStar = Double.parseDouble(avgscr);
		System.out.println("The double value of average score is :" + averageStar);
		return averageStar;
	}

	/**
	 * Get Total Review Count
	 * 
	 * @return
	 */
	public int TotalReviews() {
		waitForElement(TotalReviews, 10);
		scrollByElement(TotalReviews);
		String totrev = TotalReviews.getText();
		System.out.println("The string value of total reviews is :" + totrev);
		TotReviews = Integer.parseInt(totrev);
		System.out.println("The integer value of total reviews is :" + TotReviews);
		return TotReviews;
	}

	/**
	 * Get Positive Recommendation count
	 * 
	 * @return
	 */
	public int PositiveReco() {
		waitForElement(PositiveRecommendation, 10);
		scrollByElement(PositiveRecommendation);
		String posreco = PositiveRecommendation.getText();
		System.out.println("The string value of positive reco is :" + posreco);
		PosReco = Integer.parseInt(posreco);
		// PosReco = Integer.parseUnsignedInt(posreco);
		System.out.println("The integer value of Positive Reco is :" + PosReco);
		return PosReco;
	}

	/**
	 * Get Negative Recommendation Count
	 * 
	 * @return
	 */
	public int NegativeReco() {
		waitForElement(NegativeRecommendation, 10);
		scrollByElement(NegativeRecommendation);
		String negreco = NegativeRecommendation.getText();
		System.out.println("The String value of negative reco is :" + negreco);
		NegReco = Integer.parseInt(negreco);
		System.out.println("The integer value of negative Reco is :" + NegReco);
		return NegReco;
	}

	/**
	 * Get Response count
	 * 
	 * @return
	 */
	public int reviewResponse() {
		waitForElement(ReviewsResponed, 10);
		scrollByElement(ReviewsResponed);
		String Response = ReviewsResponed.getText();
		System.out.println("The String value of response is :" + Response);
		Reviewresponse = Integer.parseInt(Response);
		System.out.println("The integer value of response is :" + Reviewresponse);
		return Reviewresponse;
	}

	/**
	 * Get NO Response Count
	 * 
	 * @return
	 */
	public int reviewNoResponse() {
		waitForElement(ReviewsNotResponded, 10);
		scrollByElement(ReviewsNotResponded);
		String NoResponse = ReviewsNotResponded.getText();
		System.out.println("The String value of no response is :" + NoResponse);
		reviewNoresponse = Integer.parseInt(NoResponse);
		System.out.println("The integer value of no response is :" + reviewNoresponse);
		return reviewNoresponse;
	}

	/**
	 * Get RNPS Score
	 * 
	 * @return
	 */
	public String RNPSScore() {
		waitForElement(RNPSScore, 10);
		scrollByElement(RNPSScore);
		RNPSscore = RNPSScore.getText();
		System.out.println("The String value of RNPS Score is :" + RNPSscore);
		return RNPSscore;
	}

	/**
	 * Compare KPI and Report Score
	 */
	public void CompareKPIandReportReviewScore() {
		System.out.println("KPI Review Score is :" + KPIreviewScore);
		System.out.println("Report Review Score is :" + averageStar);
		soft.assertEquals(KPIreviewScore, averageStar);
		System.out.println("KPI RNPS Score is :" + KPIrnpsScore);
		System.out.println("Report RNPS Score is :" + RNPSscore);
		soft.assertEquals(KPIrnpsScore, RNPSscore);
		soft.assertAll();
	}

	/**
	 * Compare Applied and Highchart Date
	 * 
	 * @throws ParseException
	 */
	public void CompareAppliedDatenChartDate() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println("The date format is :" + var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date FromDate = getFromDate();
		System.out.println("The from date applied :" + FromDate);
		Date ToDate = getToDate();
		System.out.println("The to date applied :" + ToDate);
		String chartfrom = FromDateChart.getText();
		System.out.println("The string value of chart from date is :" + chartfrom);
		Date ChartFromDate = formats.parse(chartfrom);
		System.out.println("The from date of Chart is :" + ChartFromDate);
		soft.assertTrue(FromDate.equals(ChartFromDate), "The dates are not equal");
		String chartto = ToDateChart.getText();
		System.out.println("The string value of chart to date is :" + chartto);
		Date ChartToDate = formats.parse(chartto);
		System.out.println("The To date of Chart is :" + ChartToDate);
		soft.assertTrue(ToDate.equals(ChartToDate), "The dates are not equal");
		soft.assertAll();
	}

	/**
	 * To get from date selected from UI
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Date getFromDate() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		String fromdate = ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('dateFrom').value").toString();
		System.out.println("The from date selected is :" + fromdate);
		Date finalfromdate = formats.parse(fromdate);
		System.out.println("The Date is :" + finalfromdate);
		return finalfromdate;
	}

	/**
	 * To get to date selected from UI
	 * 
	 * @return
	 * @throws ParseException
	 */
	public Date getToDate() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		String todate = ((JavascriptExecutor) driver).executeScript("return document.getElementById('dateTo').value")
				.toString();
		System.out.println("The to date selected is :" + todate);
		Date finaltodate = formats.parse(todate);
		System.out.println("The Date is :" + finaltodate);
		return finaltodate;
	}

	/**
	 * Get score from Highchart
	 * 
	 * @param ele
	 * @param ele1
	 * @return
	 * @throws Exception
	 */
	public int getChartData(List<WebElement> ele, String ele1) throws Exception {
		JSWaiter.waitJQueryAngular();
		int ReviewCount = 0;
		List<Integer> ChartReviewCount = new ArrayList<Integer>();
		String charttext;
		int size = ele.size();
		System.out.println("The total size of the element is : " + size);
		if (!(size == 0)) {
			for (int i = 1; i <= size; i++) {
				WebElement chart = driver.findElement(By.xpath(ele1 + "[" + i + "]"));
				System.err.println("The xpath is : " + chart);
				Thread.sleep(4000);
				scrollByElement(chart);
				action.moveToElement(chart).click().build().perform();
				Thread.sleep(3000);
				System.err.println("The xpath is : " + ToolTip);
				charttext = ToolTip.getText();
				System.out.println("The String value of review count is :" + charttext);
				int count = Integer.parseInt(charttext);
				System.out.println("The Integer value of review count :" + count);
				ChartReviewCount.add(count);
				System.out.println("The List contains :" + ChartReviewCount);
			}
			System.out.println("The final list is :" + ChartReviewCount);
			int listsize = ChartReviewCount.size();
			System.out.println("The size of the List is :" + listsize);
			for (int j : ChartReviewCount) {
				ReviewCount = ReviewCount + j;
				System.out.println("The sum is :" + ReviewCount);
			}
			System.out.println("The final sum of the reviews is :" + ReviewCount);
			return ReviewCount;
		} else {
			ReviewCount = 0;
			return ReviewCount;
		}
	}

	/**
	 * To compare total review count with Highchart count
	 * 
	 * @throws Exception
	 */
	public void compareChartnReviewCount() throws Exception {
		int Onestarcount = getChartData(OneStarChart, Onestarchart);
		System.out.println("The one star total count is : " + Onestarcount);
		int Threestarcount = getChartData(ThreeStarChart, Threestarchart);
		System.out.println("The three star total count is : " + Threestarcount);
		int Fivestarcount = getChartData(FiveStarChart, Fivestarchart);
		System.out.println("The five star count is :" + Fivestarcount);
		int Recocount = getChartData(RecommendedChart, Recochart);
		System.out.println("The total recommended count is :" + Recocount);
		int Notrecocount = getChartData(NotRecommendedChart, Notrecochart);
		System.out.println("The total not recommended count :" + Notrecocount);
		int Notavailcount = getChartData(NotAvailableChart, Notavailablechart);
		System.out.println("The total Not Available count is :" + Notavailcount);
		int TotalChartReviewCount = (Onestarcount + Threestarcount + Fivestarcount + Recocount + Notrecocount
				+ Notavailcount);
		System.out.println("The total chart review count is :" + TotalChartReviewCount);
		soft.assertEquals(TotalChartReviewCount, TotReviews);
		soft.assertAll();
	}

	/**
	 * Verify Facebook Count with Highchart
	 * 
	 * @throws Exception
	 */
	public void FacebookVerify() throws Exception {
		int Recocount = getChartData(RecommendedChart, Recochart);
		System.out.println("The total recommended count is :" + Recocount);
		int Notrecocount = getChartData(NotRecommendedChart, Notrecochart);
		System.out.println("The total not recommended count :" + Notrecocount);
		soft.assertEquals(Recocount, PosReco);
		soft.assertEquals(Notrecocount, NegReco);
		soft.assertAll();
	}

	/**
	 * Verification of Highchart
	 * 
	 * @param ele
	 * @param ele1
	 * @param ele2
	 * @param ele3
	 * @param ele4
	 * @param ele5
	 * @param ele8
	 * @param ele9
	 * @param ele10
	 * @param ele11
	 * @param ele12
	 * @param ele13
	 * @param ele14
	 * @param ele15
	 * @param ele16
	 * @throws Exception
	 */
	public void VerifyHighChartType(WebElement ele, String ele1, String ele2, String ele3, String ele4, String ele5,
			WebElement ele8, WebElement ele9, WebElement ele10, WebElement ele11, List<WebElement> ele12,
			List<WebElement> ele13, List<WebElement> ele14, List<WebElement> ele15, List<WebElement> ele16)
			throws Exception {
		scrollByElement(ele);
		clickelement(ele);
		clickelement(ele8);
		clickelement(ele9);
		clickelement(ele10);
		clickelement(ele11);
		if ((ele12.size()) > 0) {
			soft.assertTrue(!(driver.findElement(By.xpath(ele1)).isDisplayed()), "The element is displayed");
		} else {
			System.out.println("No elements to verify");
		}
		if ((ele13.size()) > 0) {
			soft.assertTrue(!(driver.findElement(By.xpath(ele2)).isDisplayed()), "The element is displayed");
		} else {
			System.out.println("No elements to verify");
		}
		if ((ele14.size()) > 0) {
			soft.assertTrue(!(driver.findElement(By.xpath(ele3)).isDisplayed()), "The element is displayed");
		} else {
			System.out.println("No elements to verify");
		}
		if ((ele15.size()) > 0) {
			soft.assertTrue(!(driver.findElement(By.xpath(ele4)).isDisplayed()), "The element is displayed");
		} else {
			System.out.println("No elements to verify");
		}
		if ((ele16.size()) > 0) {
			soft.assertTrue(!(driver.findElement(By.xpath(ele5)).isDisplayed()), "The element is displayed");
		} else {
			System.out.println("No elements to verify");
		}
		BaseClass.addEvidence(driver, "Test to get verify count", "yes");
		clickelement(ele);
		clickelement(ele8);
		clickelement(ele9);
		clickelement(ele10);
		clickelement(ele11);
	}

	/**
	 * Verification of Recommendation Highchart
	 * 
	 * @throws Exception
	 */
	public void verifyRecoHighchart() throws Exception {
		VerifyHighChartType(FourStarsHighChart, Fivestarchart, Threestarchart, Onestarchart, Notrecochart,
				Notavailablechart, ThreeStarsHighChart, OneStarHighChart, NotRecoHighChart, NotAvailHighChart,
				FiveStarChart, ThreeStarChart, OneStarChart, NotRecommendedChart, NotAvailableChart);
	}

	/**
	 * Verification of Five Star Highchart
	 * 
	 * @throws Exception
	 */
	public void verifyFiveStarHighChart() throws Exception {
		VerifyHighChartType(RecommendHighChart, Recochart, Threestarchart, Onestarchart, Notrecochart,
				Notavailablechart, ThreeStarsHighChart, OneStarHighChart, NotRecoHighChart, NotAvailHighChart,
				RecommendedChart, ThreeStarChart, OneStarChart, NotRecommendedChart, NotAvailableChart);
	}

	/**
	 * Verification of Three/Four Star Highchart
	 * 
	 * @throws Exception
	 */
	public void verifyThreeStarHighChart() throws Exception {
		VerifyHighChartType(RecommendHighChart, Recochart, Fivestarchart, Onestarchart, Notrecochart, Notavailablechart,
				FourStarsHighChart, OneStarHighChart, NotRecoHighChart, NotAvailHighChart, RecommendedChart,
				FiveStarChart, OneStarChart, NotRecommendedChart, NotAvailableChart);
	}

	/**
	 * Verification of One/Two Star Highchart
	 * 
	 * @throws Exception
	 */
	public void verifyOneStarHighChart() throws Exception {
		VerifyHighChartType(RecommendHighChart, Recochart, Fivestarchart, Threestarchart, Notrecochart,
				Notavailablechart, FourStarsHighChart, ThreeStarsHighChart, NotRecoHighChart, NotAvailHighChart,
				RecommendedChart, FiveStarChart, ThreeStarChart, NotRecommendedChart, NotAvailableChart);
	}

	/**
	 * Verification of Not Recommended Highchart
	 * 
	 * @throws Exception
	 */
	public void verifyNotRecommendHighChart() throws Exception {
		VerifyHighChartType(RecommendHighChart, Recochart, Fivestarchart, Threestarchart, Onestarchart,
				Notavailablechart, FourStarsHighChart, ThreeStarsHighChart, OneStarHighChart, NotAvailHighChart,
				RecommendedChart, FiveStarChart, ThreeStarChart, OneStarChart, NotAvailableChart);
	}

	/**
	 * Verification of Not Available Highchart
	 * 
	 * @throws Exception
	 */
	public void verifyNoAvailableHighChart() throws Exception {
		VerifyHighChartType(RecommendHighChart, Recochart, Fivestarchart, Threestarchart, Onestarchart, Notrecochart,
				FourStarsHighChart, ThreeStarsHighChart, OneStarHighChart, NotRecoHighChart, RecommendedChart,
				FiveStarChart, ThreeStarChart, OneStarChart, NotRecommendedChart);
	}

	/**
	 * Exporting Location Data
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void ExportLoc() throws FileNotFoundException, IOException, InterruptedException {
		waitForElement(ExportBtn, 10);
		scrollByElement(ExportBtn);
		clickelement(ExportBtn);
		Thread.sleep(4000);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + InsightsExport));
		Thread.sleep(3000);
	}

	/**
	 * To read data from CSV file
	 * 
	 * @throws IOException
	 */
	public void ReadDataCSV() throws IOException {
		List<String> csvlist = new ArrayList<String>();
		if (CurrentState.getBrowser().equals("chrome")) {
			csvlist = readCsvColdata(chromepath);
			System.out.println("csv list contains : " + csvlist);
		} else if (CurrentState.getBrowser().equals("IE")) {
			csvlist = readCsvColdata(IEpath);
			System.out.println("csv list contains : " + csvlist);
		} else if (CurrentState.getBrowser().equals("Firefox")) {
			csvlist = readCsvColdata(FFpath);
			System.out.println("csv list contains : " + csvlist);
		}
		int size = csvlist.size();
		System.out.println("The size of the list is : " + size);
		Assert.assertTrue((size > 0), "The list is empty");
		deletefile();
	}

	/**
	 * Read Data from CSV File
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public List<String> readCsvColdata(String path) throws IOException {
		String splitBy = ",";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		List<String> csvlist = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] b = line.split(splitBy);
			csvlist.add(b[0].toString());
		}
		System.out.println("Data in the list : " + csvlist);
		return csvlist;
	}

	/**
	 * To get Review Count from Feed Page
	 * 
	 * @return
	 */
	public int getreviewcount() {
		boolean dataavailable = DataAvailable();
		if (dataavailable == false) {
			int RCount = Integer.parseInt(ReviewCount.getText());
			System.out.println("The Review Count is :" + RCount);
			int rcnt = Integer.parseInt(ReviewExclude.getText());
			System.out.println("Reviews not included in Insights : " + rcnt);
			int FinalCount = (RCount - rcnt);
			System.out.println("The review count included in Insights is : " + FinalCount);
			return FinalCount;
		} else {
			System.out.println("No Data Available");
			return 0;
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
	 * Verification of review Count between Review Feed and Review Insights
	 * 
	 * @throws Exception
	 */
	public void compareReviewCountbetReports() throws Exception {

		int ReviewFeedCount = getreviewcount();
		System.out.println("The review feed review count is : " + ReviewFeedCount);
		BaseClass.addEvidence(driver, "Test to get review count in review feed", "yes");
		Assert.assertEquals(TotReviews, ReviewFeedCount);
	}

	/**
	 * To Apply recommended in rating filter and verify data
	 * 
	 * @throws Exception
	 */
	public void ApplyPositiveRatingnVerifyCount() throws Exception {
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='Recommended'])[1]")))
				.click().build().perform();
		JSWaiter.waitJQueryAngular();
		clickelement(RatingFilter);
		Thread.sleep(5000);
		int PositiveReviewCount = getreviewcount();
		System.out.println("The Positive Review Count is : " + PositiveReviewCount);
		BaseClass.addEvidence(driver, "Test to verify positive count", "yes");
		System.out.println("The positive review count from Insights is : " + PosReco);
		soft.assertEquals(PosReco, PositiveReviewCount);
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[2]"))).click()
				.build().perform();
		clickelement(RatingFilter);
		soft.assertAll();
	}

	/**
	 * To Apply not recommended in rating filter and verify data
	 * 
	 * @throws Exception
	 */
	public void ApplyNegativeRatingVerifyCount() throws Exception {
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		Thread.sleep(5000);
		action.moveToElement(
				driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='Not Recommended'])[1]"))).click()
				.build().perform();
		JSWaiter.waitJQueryAngular();
		clickelement(RatingFilter);
		Thread.sleep(5000);
		int NegativeReviewCount = getreviewcount();
		System.out.println("The Negative Review Count is : " + NegativeReviewCount);
		BaseClass.addEvidence(driver, "Test to verify negative count", "yes");
		System.out.println("The negative review count from Insights is : " + NegReco);
		soft.assertEquals(NegReco, NegativeReviewCount);
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[2]"))).click()
				.build().perform();
		clickelement(RatingFilter);
		soft.assertAll();
	}

	/**
	 * To Apply response in Response filter and verify
	 * 
	 * @throws Exception
	 */
	public void verifyResponseCount() throws Exception {
		scrollByElement(Response);
		clickelement(Response);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='ownerResponse']//div[contains(text(),'Response')]")).click();
		JSWaiter.waitJQueryAngular();
		Thread.sleep(5000);
		int totalresponsereview = getreviewcount();
		System.out.println("The total number of review response is : " + totalresponsereview);
		System.out.println("The total number of review response from Insights is : " + Reviewresponse);
		soft.assertEquals(Reviewresponse, totalresponsereview);
		BaseClass.addEvidence(driver, "Test to verify response count for review", "yes");
		scrollByElement(Response);
		clickelement(Response);
		driver.findElement(By.xpath("//div[@class='ownerResponse']//div[contains(text(),'All')]")).click();
		JSWaiter.waitJQueryAngular();
		Thread.sleep(5000);
		soft.assertAll();
	}

	/**
	 * To Apply No response in Response filter and verify
	 * 
	 * @throws Exception
	 */
	public void verifyNotResponseCount() throws Exception {
		scrollByElement(Response);
		clickelement(Response);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='ownerResponse']//div[contains(text(),'No Response')]")).click();
		JSWaiter.waitJQueryAngular();
		Thread.sleep(5000);
		int totalresponsereview = getreviewcount();
		System.out.println("The total number of review no response is : " + totalresponsereview);
		System.out.println("The total number of review no response from Insights is : " + reviewNoresponse);
		soft.assertEquals(reviewNoresponse, totalresponsereview);
		BaseClass.addEvidence(driver, "Test to verify No response count for review", "yes");
		scrollByElement(Response);
		clickelement(Response);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='ownerResponse']//div[contains(text(),'All')]")).click();
		JSWaiter.waitJQueryAngular();
		Thread.sleep(5000);
		soft.assertAll();
	}

	/**
	 * To apply rating filter and getting the review count
	 * 
	 * @param Rating
	 * @return
	 */
	public int getStarReviewCount(String Rating) {
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		action.moveToElement(
				driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='" + Rating.trim() + "'])[1]")))
				.click().build().perform();
		clickelement(RatingFilter);
		int StarCount = getreviewcount();
		System.out.println("The total star count is : " + StarCount);
		scrollByElement(RatingFilter);
		clickelement(RatingFilter);
		action.moveToElement(driver.findElement(By.xpath("(//*[@id='filter-area']//input[@title='All'])[2]"))).click()
				.build().perform();
		clickelement(RatingFilter);
		return StarCount;
	}

	/**
	 * To get review Count of 1 star
	 */
	public void getOneStar() {
		OneStarCount = getStarReviewCount("1");
		System.out.println("The one star count is : " + OneStarCount);
	}

	/**
	 * To get review Count of 2 star
	 */
	public void getTwoStar() {
		TwoStarCount = getStarReviewCount("2");
		System.out.println("The two star count is : " + TwoStarCount);
	}

	/**
	 * To get review Count of 3 star
	 */
	public void getThreeStar() {
		ThreeStarCount = getStarReviewCount("3");
		System.out.println("The three star count is : " + ThreeStarCount);
	}

	/**
	 * To get review Count of 4 star
	 */
	public void getFourStar() {
		FourStarCount = getStarReviewCount("4");
		System.out.println("The four star count is : " + FourStarCount);
	}

	/**
	 * To get review Count of 5 star
	 */
	public void getFiveStar() {
		FiveStarCount = getStarReviewCount("5");
		System.out.println("The five star count is : " + FiveStarCount);
	}

	/**
	 * To calculate total star count
	 */
	public void getTotalStarCount() {
		System.out.println("The one star count is : " + OneStarCount);
		System.out.println("The two star count is : " + TwoStarCount);
		System.out.println("The three star count is : " + ThreeStarCount);
		System.out.println("The four star count is : " + FourStarCount);
		System.out.println("The five star count is : " + FiveStarCount);
		totalStarCount = (OneStarCount + TwoStarCount + ThreeStarCount + FourStarCount + FiveStarCount);
		System.out.println("The total Star Count is : " + totalStarCount);
	}

	/**
	 * Calculating Promoter Score
	 */
	public void getPromoterScore() {
		System.out.println("The five star count is : " + FiveStarCount);
		System.out.println("The total Star Count is : " + totalStarCount);
		PromotorScore = (Double.valueOf(FiveStarCount)) / (Double.valueOf(totalStarCount));
		System.out.println("The promoter score is : " + PromotorScore);
	}

	/**
	 * Calculate total star count except 4 and 5 star
	 */
	public void getTotThreeScore() {
		System.out.println("The one star count is : " + OneStarCount);
		System.out.println("The two star count is : " + TwoStarCount);
		System.out.println("The three star count is : " + ThreeStarCount);
		totThStarCount = (OneStarCount + TwoStarCount + ThreeStarCount);
		System.out.println("The total count excluding 4 and 5 star count is : " + totThStarCount);
	}

	/**
	 * Calculating Detractor Score
	 */
	public void getDetractorScore() {
		System.out.println("The total Star Count is : " + totalStarCount);
		System.out.println("The total count excluding 4 and 5 star count is : " + totThStarCount);
		DeTractorScore = (Double.valueOf(totThStarCount)) / (Double.valueOf(totalStarCount));
		System.out.println("The Detractor score is : " + DeTractorScore);
	}

	/**
	 * To Compare RNPS Score
	 */
	public void CompareRNPSScore() {
		System.out.println("The Detractor score is : " + DeTractorScore);
		System.out.println("The promoter score is : " + PromotorScore);
		double RNPSScoreCalculation = (PromotorScore - DeTractorScore) * 100;
		BigDecimal bd = BigDecimal.valueOf(RNPSScoreCalculation);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		RNPSScoreCalculation = bd.doubleValue();
		System.out.println("The RNPS Score is : " + RNPSScoreCalculation);
		String RNPSCalc = Double.toString(RNPSScoreCalculation);
		System.out.println("The String value is : " + RNPSCalc);
		Assert.assertEquals(RNPSscore, RNPSCalc + "%");
	}

	/**
	 * To Export File
	 * 
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void ExportInsightsFilter() throws InterruptedException, FileNotFoundException, IOException {
		deletefile();
		Thread.sleep(3000);
		waitForElement(ExportBtn, 10);
		scrollByElement(ExportBtn);
		clickelement(ExportBtn);
		Thread.sleep(4000);
		renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + InsightsExport));
		Thread.sleep(3000);
	}

	/**
	 * To Read data from CSV using column number
	 * 
	 * @param path
	 * @param colnum
	 * @return
	 * @throws IOException
	 */
	public List<String> readCsvDatausingCol(String path, int colnum) throws IOException {
		String splitBy = ",";
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		List<String> csvcollist = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			String[] b = line.split(splitBy);
			csvcollist.add(b[colnum]);
		}
		System.out.println("Data in Type column : " + csvcollist);
		return csvcollist;
	}

	/**
	 * Coparison between UI and XL/CSV
	 * 
	 * @throws IOException
	 */
	public void ReadnverifyData() throws IOException {
		int Totrev = 0;
		List<String> reviewTot = new ArrayList<String>();
		double average;
		List<String> avgrev = new ArrayList<String>();
		int posrec;
		List<String> Pos = new ArrayList<String>();
		int negrec;
		List<String> Neg = new ArrayList<String>();
		int Responses = 0;
		List<String> Res = new ArrayList<String>();
		String RNPS;
		List<String> rnps = new ArrayList<String>();

		TotReviews = TotalReviews();
		System.out.println("The total Reviews from UI is : " + TotReviews);

		averageStar = AverageStar();
		System.out.println("The average star rating from UI is : " + averageStar);

		PosReco = PositiveReco();
		System.out.println("The positive recommendation from UI is : " + PosReco);

		NegReco = NegativeReco();
		System.out.println("The negative recommendation from UI is : " + NegReco);

		Reviewresponse = reviewResponse();
		System.out.println("The review Response from UI is :" + Reviewresponse);

		RNPSscore = RNPSScore();
		System.out.println("The RNPS Score from UI is : " + RNPSscore);

		if (CurrentState.getBrowser().equals("chrome")) {

			// To verify tot reviews from XL and UI
			reviewTot = readCsvDatausingCol(chromepath, 10);
			System.out.println("csv list contains : " + reviewTot.get(0));
			if (reviewTot.get(0).contains("\"")) {
				String revtot = reviewTot.get(0).replace("\"", "").trim();
				System.out.println("The string val is : " + revtot);
				Totrev = Integer.parseInt(revtot);
			} else {
				Totrev = Integer.parseInt(reviewTot.get(0));
			}
			System.out.println("The total reviews from XL is : " + Totrev);
			soft.assertEquals(TotReviews, Totrev);

			// To verify average from XL and UI
			avgrev = readCsvDatausingCol(chromepath, 12);
			System.out.println("csv list contains : " + avgrev);
			if (avgrev.get(0).contains("\"")) {
				String avrev = avgrev.get(0).replace("\"", "").trim();
				average = Double.parseDouble(avrev);
			} else {
				average = Double.parseDouble(avgrev.get(0));
			}
			BigDecimal bd = BigDecimal.valueOf(average);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
			average = bd.doubleValue();
			System.out.println("The average score from XL is : " + average);
			soft.assertEquals(averageStar, average);

			// To verify positive reviews from XL and UI
			Pos = readCsvDatausingCol(chromepath, 14);
			System.out.println("csv list contains : " + Pos);
			if (Pos.get(0).contains("\"")) {
				String Prev = Pos.get(0).replace("\"", "").trim();
				posrec = Integer.parseInt(Prev);
			} else {
				posrec = Integer.parseInt(Pos.get(0));
			}
			System.out.println("The positive score from XL is : " + posrec);
			soft.assertEquals(PosReco, posrec);

			// To verify negative reviews from XL and UI
			Neg = readCsvDatausingCol(chromepath, 15);
			System.out.println("csv list contains : " + Neg);
			if (Neg.get(0).contains("\"")) {
				String Nrev = Neg.get(0).replace("\"", "").trim();
				negrec = Integer.parseInt(Nrev);
			} else {
				negrec = Integer.parseInt(Neg.get(0));
			}
			System.out.println("The negative score from XL is : " + negrec);
			soft.assertEquals(NegReco, negrec);

			// To verify Response from XL and UI
			Res = readCsvDatausingCol(chromepath, 16);
			System.out.println("csv list contains : " + Res);
			if (Res.get(0).contains("\"")) {
				String Resp = Res.get(0).replace("\"", "").trim();
				Responses = Integer.parseInt(Resp);
			} else {
				Responses = Integer.parseInt(Res.get(0));
			}
			System.out.println("The response score from XL is : " + Responses);
			soft.assertEquals(Reviewresponse, Responses);

			// To verify No Response from XL and UI
			rnps = readCsvDatausingCol(chromepath, 18);
			System.out.println("csv list contains : " + rnps);
			if (rnps.get(0).contains("\"")) {
				RNPS = rnps.get(0).replace("\"", "").trim();
			} else {
				RNPS = rnps.get(0);
			}
			System.out.println("The rnps score from XL is : " + RNPS);
			soft.assertEquals(RNPSscore, RNPS);

			// To verify Response Rate from
			List<String> resrate = new ArrayList<String>();
			double ResponseRate;
			double resprate;

			resrate = readCsvDatausingCol(chromepath, 17);
			if (resrate.get(0).contains("\"")) {
				String Resprate = resrate.get(0).replace("\"", "").trim();
				if (resrate.get(0).contains("%")) {
					Resprate = Resprate.replace("%", "").trim();
					ResponseRate = Double.parseDouble(Resprate);
				} else {
					ResponseRate = Double.parseDouble(Resprate);
				}
			} else {
				String Resprate = null;
				if (resrate.get(0).contains("%")) {
					Resprate = resrate.get(0).replace("%", "").replace("\"", "").trim();
					ResponseRate = Double.parseDouble(Resprate);
				} else {
					ResponseRate = Double.parseDouble(Resprate);
				}
			}
			System.out.println("The Response Rate from XL is : " + ResponseRate);
			resprate = (Responses / Double.valueOf(Totrev)) * 100;
			BigDecimal bd1 = BigDecimal.valueOf(resprate);
			bd1 = bd1.setScale(2, RoundingMode.HALF_UP);
			resprate = bd1.doubleValue();
			System.out.println("The resprate calculated from XL is : " + resprate);
			soft.assertEquals(ResponseRate, resprate);
		} else if (CurrentState.getBrowser().equals("IE")) {
			// To verify tot reviews from XL and UI
			reviewTot = readCsvDatausingCol(IEpath, 10);
			System.out.println("csv list contains : " + reviewTot.get(0));
			if (reviewTot.get(0).contains("\"")) {
				String revtot = reviewTot.get(0).replace("\"", "").trim();
				System.out.println("The string val is : " + revtot);
				Totrev = Integer.parseInt(revtot);
			} else {
				Totrev = Integer.parseInt(reviewTot.get(0));
			}
			System.out.println("The total reviews from XL is : " + Totrev);
			soft.assertEquals(TotReviews, Totrev);

			// To verify average from XL and UI
			avgrev = readCsvDatausingCol(IEpath, 12);
			System.out.println("csv list contains : " + avgrev);
			if (avgrev.get(0).contains("\"")) {
				String avrev = avgrev.get(0).replace("\"", "").trim();
				average = Double.parseDouble(avrev);
			} else {
				average = Double.parseDouble(avgrev.get(0));
			}
			System.out.println("The average score from XL is : " + average);
			soft.assertEquals(averageStar, average);

			// To verify positive reviews from XL and UI
			Pos = readCsvDatausingCol(IEpath, 14);
			System.out.println("csv list contains : " + Pos);
			if (Pos.get(0).contains("\"")) {
				String Prev = Pos.get(0).replace("\"", "").trim();
				posrec = Integer.parseInt(Prev);
			} else {
				posrec = Integer.parseInt(Pos.get(0));
			}
			System.out.println("The positive score from XL is : " + posrec);
			soft.assertEquals(PosReco, posrec);

			// To verify negative reviews from XL and UI
			Neg = readCsvDatausingCol(IEpath, 15);
			System.out.println("csv list contains : " + Neg);
			if (Neg.get(0).contains("\"")) {
				String Nrev = Neg.get(0).replace("\"", "").trim();
				negrec = Integer.parseInt(Nrev);
			} else {
				negrec = Integer.parseInt(Neg.get(0));
			}
			System.out.println("The negative score from XL is : " + negrec);
			soft.assertEquals(NegReco, negrec);

			// To verify Response from XL and UI
			Res = readCsvDatausingCol(IEpath, 16);
			System.out.println("csv list contains : " + Res);
			if (Res.get(0).contains("\"")) {
				String Resp = Res.get(0).replace("\"", "").trim();
				Responses = Integer.parseInt(Resp);
			} else {
				Responses = Integer.parseInt(Res.get(0));
			}
			System.out.println("The response score from XL is : " + Responses);
			soft.assertEquals(Reviewresponse, Responses);

			// To verify No Response from XL and UI
			rnps = readCsvDatausingCol(IEpath, 18);
			System.out.println("csv list contains : " + rnps);
			if (rnps.get(0).contains("\"")) {
				RNPS = rnps.get(0).replace("\"", "").trim();
			} else {
				RNPS = rnps.get(0);
			}
			System.out.println("The rnps score from XL is : " + RNPS);
			soft.assertEquals(RNPSscore, RNPS);

			// To verify Response Rate from
			List<String> resrate = new ArrayList<String>();
			double ResponseRate;
			double resprate;
			resrate = readCsvDatausingCol(IEpath, 17);
			if (resrate.get(0).contains("\"")) {
				String Resprate = resrate.get(0).replace("\"", "").trim();
				if (resrate.get(0).contains("%")) {
					Resprate = resrate.get(0).replace("%", "").trim();
					ResponseRate = Double.parseDouble(Resprate);
				} else {
					ResponseRate = Double.parseDouble(Resprate);
				}
			} else {
				String Resprate = null;
				if (resrate.get(0).contains("%")) {
					Resprate = resrate.get(0).replace("%", "").trim();
					ResponseRate = Double.parseDouble(Resprate);
				} else {
					ResponseRate = Double.parseDouble(Resprate);
				}
			}
			System.out.println("The Response Rate from XL is : " + ResponseRate);
			resprate = (ResponseRate / Double.valueOf(Totrev)) * 100;
			resprate = (Responses / Double.valueOf(Totrev)) * 100;
			BigDecimal bd = BigDecimal.valueOf(resprate);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			resprate = bd.doubleValue();
			System.out.println("The resprate calculated from XL is : " + resprate);
			soft.assertEquals(ResponseRate, resprate);

		} else if (CurrentState.getBrowser().equals("Firefox")) {
			// To verify tot reviews from XL and UI
			reviewTot = readCsvDatausingCol(FFpath, 10);
			System.out.println("csv list contains : " + reviewTot.get(0));
			if (reviewTot.get(0).contains("\"")) {
				String revtot = reviewTot.get(0).replace("\"", "").trim();
				System.out.println("The string val is : " + revtot);
				Totrev = Integer.parseInt(revtot);
			} else {
				Totrev = Integer.parseInt(reviewTot.get(0));
			}
			System.out.println("The total reviews from XL is : " + Totrev);
			soft.assertEquals(TotReviews, Totrev);

			// To verify average from XL and UI
			avgrev = readCsvDatausingCol(FFpath, 12);
			System.out.println("csv list contains : " + avgrev);
			if (avgrev.get(0).contains("\"")) {
				String avrev = avgrev.get(0).replace("\"", "").trim();
				average = Double.parseDouble(avrev);
			} else {
				average = Double.parseDouble(avgrev.get(0));
			}
			System.out.println("The average score from XL is : " + average);
			soft.assertEquals(averageStar, average);

			// To verify positive reviews from XL and UI
			Pos = readCsvDatausingCol(FFpath, 14);
			System.out.println("csv list contains : " + Pos);
			if (Pos.get(0).contains("\"")) {
				String Prev = Pos.get(0).replace("\"", "").trim();
				posrec = Integer.parseInt(Prev);
			} else {
				posrec = Integer.parseInt(Pos.get(0));
			}
			System.out.println("The positive score from XL is : " + posrec);
			soft.assertEquals(PosReco, posrec);

			// To verify negative reviews from XL and UI
			Neg = readCsvDatausingCol(FFpath, 15);
			System.out.println("csv list contains : " + Neg);
			if (Neg.get(0).contains("\"")) {
				String Nrev = Neg.get(0).replace("\"", "").trim();
				negrec = Integer.parseInt(Nrev);
			} else {
				negrec = Integer.parseInt(Neg.get(0));
			}
			System.out.println("The negative score from XL is : " + negrec);
			soft.assertEquals(NegReco, negrec);

			// To verify No Response from XL and UI
			rnps = readCsvDatausingCol(FFpath, 18);
			System.out.println("csv list contains : " + rnps);
			if (rnps.get(0).contains("\"")) {
				RNPS = rnps.get(0).replace("\"", "").trim();
			} else {
				RNPS = rnps.get(0);
			}
			System.out.println("The rnps score from XL is : " + RNPS);
			soft.assertEquals(RNPSscore, RNPS);

			// To verify Response Rate from
			List<String> resrate = new ArrayList<String>();
			double ResponseRate;
			double resprate;

			resrate = readCsvDatausingCol(FFpath, 17);
			if (resrate.get(0).contains("\"")) {
				String Resprate = resrate.get(0).replace("\"", "").trim();
				if (resrate.get(0).contains("%")) {
					Resprate = resrate.get(0).replace("%", "").trim();
					ResponseRate = Double.parseDouble(Resprate);
				} else {
					ResponseRate = Double.parseDouble(Resprate);
				}
			} else {
				String Resprate = null;
				if (resrate.get(0).contains("%")) {
					Resprate = resrate.get(0).replace("%", "").trim();
					ResponseRate = Double.parseDouble(Resprate);
				} else {
					ResponseRate = Double.parseDouble(Resprate);
				}
			}
			System.out.println("The Response Rate from XL is : " + ResponseRate);
			resprate = (Responses / Double.valueOf(Totrev)) * 100;
			BigDecimal bd = BigDecimal.valueOf(resprate);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			resprate = bd.doubleValue();
			System.out.println("The resprate calculated from XL is : " + resprate);
			soft.assertEquals(ResponseRate, resprate);
		}
		soft.assertAll();
	}
}