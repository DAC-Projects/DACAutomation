package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.BaseClass;

public class Maximizer_Page extends TPSEE_abstractMethods{
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public static int Completedcount;
	public static int Totalcount;
	public static int ToDocount;
	public static int InProgresscount;
	public static int Ignoredcount;
	
	@FindBy(xpath = "//h3[@class = 'page-title']")
	private WebElement PageTitle;
	
	@FindBy(xpath = "//p[@class = 'lead']")
	private WebElement PageTitletext;
	
	@FindBy(xpath = "//div[@class = 'card-header']//i")
	private WebElement tooltiptext;
	
	@FindBy(xpath = "//div[@class = 'row card-summary-container']")
	private WebElement RecommendationOverview;
	
	@FindBy(xpath = "//span[@id = 'totalCount']")
	private WebElement TotalCount;
	
	@FindBy(xpath = "//h3[@id = 'toDoCount']")
	private WebElement ToDoCount;
	
	@FindBy(xpath = "//h3[@id = 'completedCount']")
	private WebElement CompletedCount;
	
	@FindBy(xpath = "//h3[@id = 'inProgressCount']")
	private WebElement InProgressCount;
	
	@FindBy(xpath = "//h3[@id = 'ignoredCount']")
	private WebElement IgnoredCount;
	
	@FindBy(xpath = "//div[@class = 'card-header']")
	private WebElement RecommendationPercentage;
	
	@FindBy(xpath = "//h3[@id = 'completedPercentage']")
	private WebElement PercentageComplete;
	
	

	public Maximizer_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * to verify title and title text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyTitleText() {
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		soft.assertEquals("Maximizer", Title);
		soft.assertEquals("The Maximizer report is a checklist of optimizations to boost your online presence. Each recommendation will help improve customer experience and your overall ranking. Read Manual", 
						TitleText);
		soft.assertAll();
	}
	
	/**
	 * Tooltip text verification
	 * @throws Exception
	 */
	public void TooltipTextVerification() throws Exception {
		waitForElement(tooltiptext, 5);
		action.moveToElement(tooltiptext).build().perform();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String Tooltiptext = tooltiptext.getAttribute("data-content");
		System.out.println("The tooltiptext is : " +Tooltiptext);
		Assert.assertEquals(Tooltiptext, "Only recommendations that have been completed will count towards this score. Any tactic in progress will not be included. To refresh score, log out and back into the TransparenSEE dashboard.");
		BaseClass.addEvidence(driver, "Test to verify tooltip text", "yes");
	}
	
	/**
	 * Verify Recommendation count
	 */
	public void VerifyRecommendationCount() {
		waitForElement(RecommendationOverview, 3);
		String totcount = TotalCount.getText();
		Totalcount = Integer.parseInt(totcount);
		System.out.println("The total recommendation count is : " +Totalcount);
		
		String todo = ToDoCount.getText();
		ToDocount = Integer.parseInt(todo);
		System.out.println("The to do recommendation count is : " +ToDocount);
		
		String inprogress = InProgressCount.getText();
		InProgresscount = Integer.parseInt(inprogress);
		System.out.println("The In Progress Recommendation count is : " +InProgresscount);
		
		String completed = CompletedCount.getText();
		Completedcount = Integer.parseInt(completed);
		System.out.println("The completed recommendation count is : " +Completedcount);
		
		String ignored = IgnoredCount.getText();
		Ignoredcount = Integer.parseInt(ignored);
		System.out.println("The ignored recommendation count is : " +Ignoredcount);
		
		int totalcount = (ToDocount + InProgresscount + Completedcount + Ignoredcount);
		System.out.println("The total count is : " +totalcount);
		
		Assert.assertEquals(totalcount, Totalcount);
	}
	
	/**
	 * Verify Percentage
	 */
	public void verifyPercentageComplete() {
		waitForElement(RecommendationPercentage, 4);
		
		String Percent = PercentageComplete.getText();
		System.out.println("The percentage completed is : " +Percent);
		
		
		int percentcal = (ToDocount + Completedcount);
		
		double Percentage = (Completedcount/percentcal) * 100 ;
		long percentage = Math.round(Percentage);
		int percent = (int) percentage;
		
		String finalpercentage = String.valueOf(percent);
		finalpercentage = finalpercentage+"%";
		System.out.println("Percentage is : " +finalpercentage);
		
		Assert.assertEquals(Percent, finalpercentage);
	}
	
	
	
}
