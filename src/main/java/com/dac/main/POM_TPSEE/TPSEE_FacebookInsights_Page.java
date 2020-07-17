package com.dac.main.POM_TPSEE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

public class TPSEE_FacebookInsights_Page extends TPSEE_abstractMethods {

	public TPSEE_FacebookInsights_Page(WebDriver driver) {
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

	/*-----------------------------------Locators---------------------------------------------------*/
	@FindBy(xpath = "//*[@id='page-content']//h2")
	private WebElement PageTitle;

	@FindBy(xpath = "//p[@class='lead']")
	private WebElement PageTitletext;

	@FindBy(xpath = "//li[@class='page-impressions']//span[@class='title']")
	private WebElement PageImpressiontxt;

	@FindBy(xpath = "//li[@class='page-impressions']//span[@class='section-total']")
	private WebElement PageImpressionNum;

	@FindBy(xpath = "//li[@class='post-engagements']//span[@class='title']")
	private WebElement PostEngagetxt;

	@FindBy(xpath = "//li[@class='post-engagements']//span[@class='section-total']")
	private WebElement PostEngageNum;

	@FindBy(xpath = "//li[@class='actions-on-page']//span[@class='title']")
	private WebElement PageActionTxt;

	@FindBy(xpath = "//li[@class='actions-on-page']//span[@class='section-total']")
	private WebElement PageActionNum;

	@FindBy(xpath = "//li[@class='check-ins']//span[@class='title']")
	private WebElement CheckInTxt;

	@FindBy(xpath = "//li[@class='check-ins']//span[@class='section-total']")
	private WebElement CheckInNum;

	@FindBy(xpath = "//li[@class='page-fans']//span[@class='title']")
	private WebElement PageFanTxt;

	@FindBy(xpath = "//li[@class='page-fans']//span[@class='section-total']")
	private WebElement PageFanNum;

	@FindBy(xpath = "//*[name()='path' and contains(@class,'highcharts-point highcharts-color-0')]")
	private WebElement FemalePieChart;

	@FindBy(xpath = "//*[name()='path' and contains(@class,'highcharts-point highcharts-color-1')]")
	private WebElement MalePieChart;

	@FindBy(xpath = "//*[contains(@class,'highcharts-label highcharts-tooltip')]//*[name()='text']//*[name()='tspan'][3]")
	private WebElement PieChartTxt;

	@FindBy(xpath = "//*[@class='highcharts-legend-item highcharts-pie-series highcharts-color-0']//*[name()='text']//*[name()='tspan'][2]")
	private WebElement FemalePiepercent;

	@FindBy(xpath = "//*[@class='highcharts-legend-item highcharts-pie-series highcharts-color-1']//*[name()='text']//*[name()='tspan'][2]")
	private WebElement MalePiepercent;

	@FindBy(xpath = "//*[@id='impression-chart']")
	private WebElement PieChart;

	@FindBy(xpath = "//*[@class='highcharts-title']")
	private WebElement DataAvailability;

	@FindBy(xpath = "//div[@id='facebookExportDropdown']//a[contains(text(),'Export as CSV')]")
	private WebElement ExportCSV;

	@FindBy(xpath = "//div[@id='facebookExportDropdown']//a[contains(text(),'Export as XLSX')]")
	private WebElement ExportXLSX;

	@FindBy(xpath = "//div[@id='facebookExportDropdown']/button")
	private WebElement Export;

	@FindBy(xpath = "//span[@id='actionWebsite']")
	private WebElement WebVisits;

	@FindBy(xpath = "//span[@id='actionRequest']")
	private WebElement ReqDir;

	@FindBy(xpath = "//span[@id='actionPhone']")
	private WebElement PhCalls;

	@FindBy(xpath = "(//div[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[1]//div//span[1]")
	private WebElement PageImpressionGrph;

	@FindBy(xpath = "(//div[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[2]//div//span[1]")
	private WebElement PostEngageGrph;

	@FindBy(xpath = "(//div[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[3]//div//span[1]")
	private WebElement PageActionGrph;

	@FindBy(xpath = "(//div[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[4]//div//span[1]")
	private WebElement FansGrph;

	@FindBy(xpath = "(//div[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')])[5]//div//span[1]")
	private WebElement CheckInsGrph;

	@FindBy(xpath = "//*[name()='rect' and @class='highcharts-plot-background']")
	private List<WebElement> GrphSection;

	@FindBy(xpath = "(//*[name()='rect' and @class='highcharts-plot-background'])[2]")
	private WebElement PageImpressionGrphSec;

	@FindBy(xpath = "(//*[name()='rect' and @class='highcharts-plot-background'])[3]")
	private WebElement PostEngageGraphSec;

	@FindBy(xpath = "(//*[name()='rect' and @class='highcharts-plot-background'])[4]")
	private WebElement PageActionGraphSec;

	@FindBy(xpath = "(//*[name()='rect' and @class='highcharts-plot-background'])[5]")
	private WebElement FansGraphSec;

	@FindBy(xpath = "(//*[name()='rect' and @class='highcharts-plot-background'])[6]")
	private WebElement CheckInsGraphSec;
	
	@FindBy(xpath = "//button//span[@class='walkme-custom-balloon-button-text']")
	private WebElement DoneBtn;

	String path = "./downloads/chromeFacebookExportXLSX.xlsx";

	/*-----------------------------------Locators---------------------------------------------------*/

	
	public void clickDone() {
		waitForElement(DoneBtn, 10);
		clickelement(DoneBtn);
	}
	/**
	 * To Verify Title and Title Text
	 * 
	 * @param Tit
	 * @param titText
	 */
	public void VerifyFacebookTitleText(String Tit, String titText) {
		SoftAssert soft = new SoftAssert();
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("Page Title is : " + Title);
		waitForElement(PageTitletext, 10);
		String TitleText = PageTitletext.getText();
		System.out.println("The title text  is :" + TitleText);
		Assert.assertEquals(Tit, Title);
		Assert.assertEquals(titText, TitleText);
		soft.assertAll();
	}

	/**
	 * To Get Overview Scores
	 * 
	 * @param elename
	 * @param eleNum
	 * @return
	 */
	public int getNumber(WebElement elename, WebElement eleNum) {
		waitForElement(elename, 10);
		String Text = elename.getText();
		System.out.println("The Text is :" + Text);
		String TxtNum = eleNum.getText();
		if (TxtNum.equals("-")) {
			TxtNum.replace("-", "0");
		} else if (TxtNum.contains(",")) {
			TxtNum = TxtNum.replace(",", "");
			TxtNum = TxtNum.trim();
		}
		System.out.println("The Text Number is :" + TxtNum);
		int finalNum = Integer.parseInt(TxtNum);
		System.out.println("The total number of" + Text + "is :" + finalNum);
		return finalNum;
	}

	/**
	 * To get tooltip value from PieChart
	 * 
	 * @param ele
	 * @return
	 * @throws Exception
	 */
	public String getPiechart(WebElement ele) throws Exception {
		scrollByElement(PieChart);
		action.moveToElement(ele).build().perform();
		BaseClass.addEvidence(CurrentState.getDriver(), "To get text from Pie Chart", "yes");
		String percentage = PieChartTxt.getText();
		System.out.println("The percentage for selected field is :" + percentage);
		return percentage;
	}

	/**
	 * To get impressions percentage by gender
	 * 
	 * @param ele
	 * @return
	 */
	public String getpercent(WebElement ele) {
		String percent = ele.getText();
		System.out.println("The percentage is :" + percent);
		return percent;
	}

	/**
	 * To Verify Impression Percentage matches
	 */
	public void VerifyPieChart() {
		boolean datavalidatation = IsDataAvailable();
		if (!datavalidatation == true) {
			try {
				String MalePercentage = getPiechart(MalePieChart);
				System.out.println("Male Percentage is :" + MalePercentage);
				String MaleUIPercentage = getpercent(MalePiepercent);
				System.out.println("Male UI Percentage is :" + MaleUIPercentage);
				Assert.assertEquals(MalePercentage, MaleUIPercentage);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String FemalePercentage = getPiechart(FemalePieChart);
				System.out.println("Male Percentage is :" + FemalePercentage);
				String FemaleUIPercentage = getpercent(FemalePiepercent);
				System.out.println("Male UI Percentage is :" + FemaleUIPercentage);
				Assert.assertEquals(FemalePercentage, FemaleUIPercentage);
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To verify if data is available in the page
	 * 
	 * @return
	 */
	public boolean IsDataAvailable() {
		if (DataAvailability.getText().equals("There is currently not enough data.")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To Export as CSV
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void ExportasCSV() throws FileNotFoundException, InterruptedException, IOException {
		boolean datavalidatation = IsDataAvailable();
		if (!datavalidatation == true) {
			Download(Export, ExportCSV);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + FacebookExportCSV));
			CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To Export as XLSX
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void ExportasXLSX() throws FileNotFoundException, IOException, InterruptedException {
		boolean datavalidatation = IsDataAvailable();
		if (!datavalidatation == true) {
			Download(Export, ExportXLSX);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser() + FacebookExportXLSX));
			CurrentState.getLogger().info("downloaded file name: " + getLastModifiedFile("./downloads"));
		} else {
			System.out.println();
		}
	}

	/**
	 * To get and compare Impressions
	 * 
	 * @throws Exception
	 */
	public void getImpressionCount() throws Exception {
		boolean datavalidation = IsDataAvailable();
		if (!datavalidation == true) {
			int UIImpressions = getNumber(PageImpressiontxt, PageImpressionNum);
			System.out.println("Number of Impressions in UI :" + UIImpressions);
			int XLImpressions = GetDataUsingColName(path, "Page Impressions");
			System.out.println("Number of Impressions in XL :" + XLImpressions);
			Assert.assertEquals(UIImpressions, XLImpressions);
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To get and compare Engagement
	 * 
	 * @throws Exception
	 */
	public void PostEngagement() throws Exception {
		boolean datavalidation = IsDataAvailable();
		if (!datavalidation == true) {
			int UIPostEngagement = getNumber(PostEngagetxt, PostEngageNum);
			System.out.println("Number of Posts :" + UIPostEngagement);
			int XLPostEngagement = GetDataUsingColName(path, "Post Engagements");
			System.out.println("Number of Posts in XL :" + XLPostEngagement);
			Assert.assertEquals(UIPostEngagement, XLPostEngagement);
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To compare UI and XL Data
	 * 
	 * @throws Exception
	 */
	public void PageAction() throws Exception {
		boolean datavalidation = IsDataAvailable();
		if (!datavalidation == true) {

			int UIWeb = 0, UIReqDir = 0, UIPhCalls = 0;
			try {
				UIWeb = WebsiteVists();
				System.out.println("Web Visits :" + UIWeb);
				int XLWebvisits = GetDataUsingColName(path, "Website Visits");
				System.out.println("Number of Posts in XL :" + XLWebvisits);
				Assert.assertEquals(UIWeb, XLWebvisits);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				UIReqDir = RequestDir();
				System.out.println("UI Request Directories :" + UIReqDir);
				int XLReqDir = GetDataUsingColName(path, "Request Directions");
				System.out.println("Number of Posts in XL :" + XLReqDir);
				Assert.assertEquals(UIReqDir, XLReqDir);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				UIPhCalls = PhCalls();
				System.out.println("Number of Ph Calls :" + UIPhCalls);
				int XLPhCall = GetDataUsingColName(path, "Phone Calls");
				System.out.println("Number of Posts in XL :" + XLPhCall);
				Assert.assertEquals(UIPhCalls, XLPhCall);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				int PageActions = getNumber(PageActionTxt, PageActionNum);
				System.out.println("Number of actions on Page :" + PageActions);
				int totalAction = UIWeb + UIReqDir + UIPhCalls;
				System.out.println("The total Actions on Page is :" + totalAction);
				Assert.assertEquals(PageActions, totalAction);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To get WebVisit Count
	 * 
	 * @return
	 */
	public int WebsiteVists() {
		boolean datavalidatation = IsDataAvailable();
		if (!datavalidatation == true) {
			int UIWebvisits = Integer.parseInt(WebVisits.getText());
			System.out.println("Websites Visits are :" + UIWebvisits);
			return UIWebvisits;
		} else {
			System.out.println("No data available");
			return 0;
		}
	}

	/**
	 * To get Request Count
	 * 
	 * @return
	 */
	public int RequestDir() {
		boolean datavalidatation = IsDataAvailable();
		if (!datavalidatation == true) {
			int Reqdir = Integer.parseInt(ReqDir.getText());
			System.out.println("Request directories are :" + Reqdir);
			return Reqdir;
		} else {
			System.out.println("No Data Available");
			return 0;
		}
	}

	/**
	 * To get PhCalls Count
	 * 
	 * @return
	 */
	public int PhCalls() {
		boolean datavalidatation = IsDataAvailable();
		if (!datavalidatation == true) {
			int Phcall = Integer.parseInt(PhCalls.getText());
			System.out.println("Number of Ph Calls are :" + Phcall);
			return Phcall;
		} else {
			System.out.println("No Data Available");
			return 0;
		}
	}

	/**
	 * To compare UI and XL CheckIns
	 * 
	 * @throws Exception
	 */
	public void Checkin() throws Exception {
		boolean datavalidation = IsDataAvailable();
		if (!datavalidation == true) {
			int UICheckIns = getNumber(CheckInTxt, CheckInNum);
			System.out.println("Check-Ins are :" + UICheckIns);
			int XLCheckIns = GetDataUsingColName(path, "Check-ins");
			Assert.assertEquals(UICheckIns, XLCheckIns);
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To Compare UI and XL Fans
	 * 
	 * @throws Exception
	 */
	public void Fans() throws Exception {
		boolean datavalidation = IsDataAvailable();
		if (!datavalidation == true) {
			int UIfans = getNumber(PageFanTxt, PageFanNum);
			System.out.println("Page Fans in UI :" + UIfans);
			int XLfans = GetDataUsingColName(path, "Fans");
			System.out.println("Page Fans in XL :" + XLfans);
			Assert.assertEquals(UIfans, XLfans);
		} else {
			System.out.println("No Data Available");
		}
	}

	/**
	 * To get date from each graph
	 * 
	 * @param Graphs
	 * @param GraphLoc
	 * @return
	 * @throws Exception
	 */
	public Date verifyFBHistorygraph(WebElement Graphs, WebElement GraphLoc) throws Exception {
		scrollByElement(GraphLoc);
		String var = null;
		var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml")
				.toString();
		System.out.println(var);
		Date endtDate = null;
		waitForElement(GraphLoc, 30);
		scrollByElement(GraphLoc);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(GraphLoc).moveByOffset((GraphLoc.getSize().getWidth()) / 2 - 2, 0).click().perform();
		String finaltooltipvalue = Graphs.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" + finaltooltipvalue);
		String finaldate = finaltooltipvalue.substring(2, 12);
		System.out.println(finaldate);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		endtDate = formats.parse(finaldate);
		System.out.println("The final date is :" + endtDate);
		return endtDate;
	}

	/**
	 * To verify date in graph and calendar
	 * 
	 * @throws Exception
	 */
	public void getGraphDatenVerify() throws Exception {
		boolean datavalidation = IsDataAvailable();
		if (!datavalidation == true) {
			try {
				Date ImpressionDate = verifyFBHistorygraph(PageImpressionGrph, PageImpressionGrphSec);
				System.out.println("Impression Date is :" + ImpressionDate);
				Date CalDate = getCurrenttoDate();
				System.out.println("Calender Date is :" + CalDate);
				Assert.assertEquals(ImpressionDate, CalDate);
				BaseClass.addEvidence(CurrentState.getDriver(), "To Verify Impression Graph Date", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Date PostEngagementDate = verifyFBHistorygraph(PostEngageGrph, PostEngageGraphSec);
				System.out.println("Post Engagement Date is :" + PostEngagementDate);
				Date CalDate = getCurrenttoDate();
				System.out.println("Calender Date is :" + CalDate);
				Assert.assertEquals(PostEngagementDate, CalDate);
				BaseClass.addEvidence(CurrentState.getDriver(), "To Verify Post Engagement Graph Date", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Date ActionDate = verifyFBHistorygraph(PageActionGrph, PageActionGraphSec);
				System.out.println("Action Page Date is :" + ActionDate);
				Date CalDate = getCurrenttoDate();
				System.out.println("Calender Date is :" + CalDate);
				Assert.assertEquals(ActionDate, CalDate);
				BaseClass.addEvidence(CurrentState.getDriver(), "To Verify Actions on Page Graph Date", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Date FansDate = verifyFBHistorygraph(FansGrph, FansGraphSec);
				System.out.println("Fans Date is :" + FansDate);
				Date CalDate = getCurrenttoDate();
				System.out.println("Calender Date is :" + CalDate);
				Assert.assertEquals(FansDate, CalDate);
				BaseClass.addEvidence(CurrentState.getDriver(), "To Verify Fans Graph Date", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Date CheckInDate = verifyFBHistorygraph(CheckInsGrph, CheckInsGraphSec);
				System.out.println("CheckIn Date is :" + CheckInDate);
				Date CalDate = getCurrenttoDate();
				System.out.println("Calender Date is :" + CalDate);
				Assert.assertEquals(CheckInDate, CalDate);
				BaseClass.addEvidence(CurrentState.getDriver(), "To Verify Check Ins Graph Date", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Data Available");
		}

	}
}
