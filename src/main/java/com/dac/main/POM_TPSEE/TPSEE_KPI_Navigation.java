package com.dac.main.POM_TPSEE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.BaseClass;
import resources.CurrentState;
import resources.JSWaiter;

public class TPSEE_KPI_Navigation extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public TPSEE_KPI_Navigation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='location']")
	private WebElement AllLocations;

	@FindBy(xpath = "//div[@id='visibility']")
	private WebElement VisibilityReport;

	@FindBy(xpath = "//div[@id='accuracy']")
	private WebElement AccuracyReport;

	@FindBy(xpath = "//div[@id='contentAnalysis']")
	private WebElement ContentAnalysis;

	@FindBy(xpath = "//div[@id='googleRanking']")
	private WebElement GoogleRanking;

	@FindBy(xpath = "//div[@id='average']")
	private WebElement ReviewInsights;

	@FindBy(xpath = "//li[@id='dashboard']")
	private WebElement Dashboard;

	@FindBy(xpath = "//h3[@class='page-title']")
	private WebElement PageTitle;

	@FindBy(xpath = "//*[@id='page-content']//h1")
	private WebElement CATitle;
	
	@FindBy(xpath = "//*[@class='page-title']")
	private WebElement GRTitle;
	
	@FindBy(xpath = "//div[contains(@class,'btn btn-app btn-primary ace-settings-btn')]")
	private WebElement HideKPI;
	
	@FindBy(xpath = "//*[@id='lang-flag']")
	private WebElement Lang;
	
	@FindBy(xpath = "//div[@id='lang-popup']//div")
	private List<WebElement> LangList;

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Navigate to All Locations from KPI and verify title and title text
	 * @throws Exception
	 */
	public void navigateToAllLocations() throws Exception {
		KPIMouseHover(AllLocations, "Number of participating locations");
		Thread.sleep(3000);
		navigateKPI(AllLocations, PageTitle, "Locations");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to All Location", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	/**
	 * Navigate to Visibility Report from KPI and verify title and title text
	 * @throws Exception
	 */
	public void navigateToVisibilityRpt() throws Exception {
		KPIMouseHover(VisibilityReport, "Visibility score for all participating locations");
		Thread.sleep(3000);
		navigateKPI(VisibilityReport, PageTitle, "Visibility Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Visibility Report", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	/**
	 * Navigate to Accuracy Report from KPI and verify title and title text
	 * @throws Exception
	 */
	public void navigateToAccuracyReport() throws Exception {
		KPIMouseHover(AccuracyReport, "Accuracy score for all found listings");
		Thread.sleep(3000);
		navigateKPI(AccuracyReport, PageTitle, "Accuracy Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Accuracy Report", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	/**
	 * Navigate to Content Analysis from KPI and verify title and title text
	 * @throws Exception
	 */
	public void navigateToContentAnalysis() throws Exception {
		KPIMouseHover(ContentAnalysis, "Content Analysis score for all found locations");
		Thread.sleep(3000);
		navigateKPI(ContentAnalysis, CATitle, "Content Analysis Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Content Analysis", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	/**
	 * Navigate to Google Ranking from KPI and verify title and title text
	 * @throws Exception
	 */
	public void navigateToGoogleRanking() throws Exception {
		KPIMouseHover(GoogleRanking, "Google Ranking score for all participating locations");
		Thread.sleep(3000);
		navigateKPI(GoogleRanking, GRTitle, "Google Ranking Report");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Google Ranking", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}

	/**
	 * Navigate to Reviews from KPI and verify title and title text
	 * @throws Exception
	 */
	public void navigateToReviews() throws Exception {
		KPIMouseHover(ReviewInsights, "Average review score of participating locations over the last month");
		Thread.sleep(3000);
		navigateKPI(ReviewInsights, PageTitle, "Review Insights");
		Thread.sleep(3000);
		BaseClass.addEvidence(driver, "Test to navigate to Review Insights", "yes");
		clickelement(Dashboard);
		Thread.sleep(3000);
	}
	
	/**
	 * Verify the order of Reports in KPI Dashboard
	 */
	public void VerifyOrder() {
		List<WebElement> Reports = driver.findElements(By.xpath("//span[@class='kpi-url-text']"));
		List<String> Report = new ArrayList<String>();
		int size = Reports.size();
		for (int i = 1; i <= size; i++) {
			String reportname = driver.findElement(By.xpath("(//span[@class='kpi-url-text'])["+ i +"]")).getText();
			System.out.println("Report name is : " +reportname);
			Report.add(reportname);
		}
		System.out.println("The reports are : " +Report);
		List<String> ReportsOrder = new ArrayList<String>();
		ReportsOrder.add("Locations");
		ReportsOrder.add("Visibility");
		ReportsOrder.add("Accuracy");
		ReportsOrder.add("Analysis");
		ReportsOrder.add("Ranking");
		ReportsOrder.add("Reviews");
		System.out.println("The Order of the reports are : " +ReportsOrder);
		int ReportsOrdersize = ReportsOrder.size();
		System.out.println("The ReportsOrder Array size is : " +ReportsOrdersize);
		int Reportsize = Report.size();
		System.out.println("The UI Report size is : " +Reportsize);
		if(ReportsOrdersize == Reportsize) {
			for (int i = 0; i <= Reportsize-1; i++){
				soft.assertTrue(Report.get(i).equals(ReportsOrder.get(i)), "The UI Report " +Report.get(i) + "compared with " +ReportsOrder.get(i));
			}
		}		
		soft.assertAll();
	}

	/**
	 * 
	 * Verify icon size and text of reports in KPI Dashboard
	 */
	public void verifyIconsizeandtextsize() {
		String font;
		List<WebElement> Reportnamesize = driver.findElements(By.xpath("(//div[@class='infobox-data kpi-url'])"));
		int size = Reportnamesize.size();
		System.out.println("The size is : " +size);
		for(int i = 1; i <= size; i++) {
			font = driver.findElement(By.xpath("(//div[@class='infobox-data kpi-url'])["+ i +"]")).getCssValue("font-size");
			System.out.println("The font size is : " +font);
			soft.assertEquals(font, "13px", "The size of : " +i);
		}
		WebElement Locations = driver.findElement(By.xpath("//span[@class='infobox-data-number locations-value kpi-number']"));
		font = Locations.getCssValue("font-size");
		System.out.println("The font size of locations is : " +font);
		soft.assertEquals(font, "30px", "The size of Locations KPI");
		WebElement Visibility = driver.findElement(By.xpath("//span[@id='visibility_kpi_value']"));
		font = Visibility.getCssValue("font-size");
		System.out.println("Visibility font is : " +font);
		soft.assertEquals(font, "18px", "The size of Visibility KPI");
		WebElement Accuracy = driver.findElement(By.xpath("(//span[@id='accuracy_kpi_value'])"));
		font = Accuracy.getCssValue("font-size");
		System.out.println("Accuracy font is : " +font);
		soft.assertEquals(font, "18px", "The size of Accuracy KPI");
		WebElement Analysis = driver.findElement(By.xpath("(//span[@id='contentAnalysis_kpi_value'])"));
		font = Analysis.getCssValue("font-size");
		System.out.println("Analysis font is : " +font);
		soft.assertEquals(font, "18px" , "The size of Analysis KPI");
		WebElement Ranking = driver.findElement(By.xpath("(//span[@id='ranking_count_kpi_value'])"));
		font = Ranking.getCssValue("font-size");
		System.out.println("Ranking size is : " +font);
		soft.assertEquals(font, "18px", "The size of Ranking KPI");
		WebElement Reviews = driver.findElement(By.xpath("(//div[@id='kpi_reviews_count_box'])//span"));
		font = Reviews.getCssValue("font-size");
		System.out.println("Reviews font is : " +font);
		soft.assertEquals(font, "18px", "The size of Reviews KPI");
		List<WebElement> ReportIcon = driver.findElements(By.xpath("(//img[@class='svg_icon'])"));
		int iconsize = ReportIcon.size();
		for(int j = 1; j <= iconsize; j++) {
			WebElement icon = driver.findElement(By.xpath("(//img[@class='svg_icon'])["+ j +"]"));
			String height = icon.getCssValue("height");
			System.out.println("The height is : " +height);
			String width = icon.getCssValue("width");
			System.out.println("The width is : " +width);
			soft.assertEquals(height, "30px" , "The height of : " +j);
			soft.assertEquals(width, "30px", "The width of : " +j);
		}
		soft.assertAll();
	}
	
	/**
	 * To verify hiding the KPI Dashboard
	 * @throws Exception
	 */
	public void HideandVerify() throws Exception {
		JSWaiter.waitJQueryAngular();
		clickelement(HideKPI);
		String classname = HideKPI.getAttribute("class");
		System.out.println("The class name is : " +classname);
		BaseClass.addEvidence(CurrentState.getDriver(), "Test to hide and verify KPI", "yes");
		Assert.assertEquals(classname, "btn btn-app btn-primary ace-settings-btn");
	}
	
	/**
	 * Test to verify languages listed
	 * @throws Exception
	 */
	public void getLangListnVerify() throws Exception {
		JSWaiter.waitJQueryAngular();
		clickelement(Lang);
		BaseClass.addEvidence(CurrentState.getDriver(), "To verify languages", "yes");
		List<String> languagelist = new ArrayList<String>();
		List<String> LanguageList = new ArrayList<String>();
		int size = LangList.size();
		System.out.println("The size of language is : " +size);
		for(int i = 1; i <= size; i++) {
			String Language = driver.findElement(By.xpath("(//div[@id='lang-popup']//div)["+ i +"]")).getText();
			System.out.println("The language is : " +Language);
			LanguageList.add(Language);
		}
		System.out.println("The language list contains : " +LanguageList);
		languagelist.add("Deutsch");
		languagelist.add("English");
		languagelist.add("Español");
		languagelist.add("Español");
		languagelist.add("Français");
		languagelist.add("Français");
		languagelist.add("Italiano");
		System.out.println("The language list should be : " +languagelist);
		CurrentState.getDriver().navigate().refresh();
		JSWaiter.waitForJQueryLoad();
		if(languagelist.size() == LanguageList.size()) {
		for(int j = 0; j < languagelist.size() - 1; j++) {
			soft.assertTrue(languagelist.get(j).equals(LanguageList.get(j)), "The language is not equal and selected from expected is : "+languagelist.get(j) +
			"The UI selected value is : " +LanguageList.get(j));
		}
		}else {
			soft.fail("Language list size are not equal");
		}
		soft.assertAll();
	}
}
