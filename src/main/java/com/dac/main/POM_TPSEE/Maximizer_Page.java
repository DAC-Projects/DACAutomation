package com.dac.main.POM_TPSEE;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.BaseClass;
import resources.ExcelHandler;

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
	
	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;

	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationNext;

	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private List<WebElement> paginationLast;
	
	

	public Maximizer_Page(WebDriver driver) {
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
	
	
	/**
	 * Get data from XL and compare UI data
	 * @param UIRecoType
	 * @param UIDetails
	 * @param UIImpact
	 * @param UIBenefits
	 * @throws Exception 
	 */
	public void getDatafromXLandVerify(String UIRecoType, String UIDetails, String UIImpact, String UIBenefits) throws Exception {
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Maximizer");
		for(int i = 1; i <= wb.getRowCount(); i++) {
			String XLRecoType = wb.getCellValue(i, wb.seacrh_pattern("Reco Type", 0).get(0).intValue()).trim();
			System.out.println("The recommendation type in XL is : " +XLRecoType);
			if(XLRecoType.equals(UIRecoType)) {
				String XLDetails = wb.getCellValue(i, wb.seacrh_pattern("Details", 0).get(0).intValue()).trim();
				System.out.println("The Reco details is : " +XLDetails);
				soft.assertTrue(UIDetails.contains(XLDetails), "Details of "+ UIDetails +" not matching with " +XLDetails);
				String XLImpact = wb.getCellValue(i, wb.seacrh_pattern("Impact", 0).get(0).intValue()).trim();
				System.out.println("The impact of the reco is : " +XLImpact);
				soft.assertEquals(UIImpact, XLImpact, "Impact of "+ UIImpact + " not matching with " +XLImpact);
				String XLBenefits = wb.getCellValue(i, wb.seacrh_pattern("Benefits", 0).get(0).intValue()).trim();
				System.out.println("The benifit of the reco is : " +XLBenefits);
				soft.assertEquals(UIBenefits, XLBenefits, "Benefits of "+ UIBenefits +" not matching with " +XLBenefits);
			}
		}
	}
	
	
	/**
	 * Get Data from UI and Verify with XL
	 * @throws Exception
	 */
	public void getDatafromUIandverifywithXLdata() throws Exception {
		WebElement RecoTable = driver.findElement(By.xpath("//table[@id = 'recommendationTable']"));
		scrollByElement(RecoTable);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int page = Integer.parseInt(n);
		System.out.println("\n" + page);
		WebElement entriestext = driver.findElement(By.xpath("//div[@id = 'recommendationTable_info']"));
		if(entriestext.isDisplayed()) {
			int totalentries = NumOfentries(entriestext);
			System.out.println("The total entries in a table is : " +totalentries);
			List<WebElement> RecoTableRow = driver.findElements(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])"));
			if (paginationNext.isDisplayed()) {
				for (int i = 1; i <= page; i++) {
					List<WebElement> rows_table = RecoTableRow; // To locate rows of table. 
					int rows_count = rows_table.size();
					for (int row = 1; row < rows_count; row++) {
						
						WebElement RecoType = driver.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])["+ row +"]//td[2]"));
						String UIRecoType = RecoType.getText().trim();
						System.out.println("The UI Reco Type is : " +UIRecoType);
						
						WebElement Details = driver.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])["+ row +"]//td[3]"));
						String UIDetails = Details.getText().trim();
						System.out.println("The UI Details is : " +UIDetails);
						
						WebElement Impact = driver.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])["+ row +"]//td[4]"));
						String UIImpact = Impact.getText().trim();
						System.out.println("The Impact Type is : " +UIImpact);
						
						WebElement Benefits = driver.findElement(By.xpath("(//table[@id = 'recommendationTable']//tbody//tr[@role = 'row'])["+ row +"]//td[5]"));
						String UIBenefits = Benefits.getText().trim();
						System.out.println("The Benefits Type is : " +UIBenefits);
						
						getDatafromXLandVerify(UIRecoType, UIDetails, UIImpact, UIBenefits);
						
						
						
					} if (paginationNext.isEnabled()) {
						scrollByElement(paginationNext);
						paginationNext.click();
						Thread.sleep(4000);
					}
				}
			}
		} else if (driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		soft.assertAll();
	}
	
}
