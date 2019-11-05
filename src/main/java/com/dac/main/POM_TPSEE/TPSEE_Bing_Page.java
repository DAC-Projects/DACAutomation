package com.dac.main.POM_TPSEE;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import resources.CurrentState;
import resources.JSWaiter;

public class TPSEE_Bing_Page extends TPSEE_abstractMethods {

	public TPSEE_Bing_Page(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		return null;
	}
	
	/*----------------------------------Locators----------------------------------------------*/
	
	@FindBy(xpath = "//div[@class='big-stats tooltip-info']//span")
	private WebElement Impressions;
	
	@FindBy(xpath = "//*[contains(@class,'highcharts-label highcharts-tooltip-box highcharts-color-none')]")
	private WebElement grphtooltip;
	
	@FindBy(xpath = "//button[@class='btn btn-primary dropdown-toggle export-dropdown-btn']")
	private WebElement export;
	
	@FindBy(xpath  = "//a[contains(text(), 'Export as XLSX')]")
	private WebElement XLSXExport;
	
	@FindBy(xpath = "//a[contains(text(), 'Export as CSV')]")
	private WebElement CSVExport;

	/*----------------------------------Locators----------------------------------------------*/
	
	
	//Exporting Bing Report	
	public void exportCSVBing() throws InterruptedException, FileNotFoundException, IOException {

		JSWaiter.waitJQueryAngular();
		if(export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
		}if(CSVExport.isDisplayed() && CSVExport.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(XLSXExport));
			CSVExport.click();
			Thread.sleep(5000);
			getLastModifiedFile(Exportpath);
			}else {
			System.out.println("No Data Available in GMB");
		}
	}
	
	public void exportXLSXBing() throws InterruptedException, FileNotFoundException, IOException {

		JSWaiter.waitJQueryAngular();
		if(export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
		}if(XLSXExport.isDisplayed() && XLSXExport.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(XLSXExport));
			XLSXExport.click();
			Thread.sleep(5000);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+BingXLSX));
			Thread.sleep(6000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile(Exportpath));
			}else {
			System.out.println("No Data Available in GMB");
		}
	}
	
	//Getting Impressions from UI
	public int getImpressions(WebElement a) throws Exception {		
		JSWaiter.waitJQueryAngular();
		String Impression = a.getText();
		String u = "";
		int Impressions = 0;
		if(Impression.contains(",")) {
			u = Impression.replace(",", "");
			u.trim();
			Impressions = Integer.parseInt(u);
			}else {
				Impressions = Integer.parseInt(Impression);
			}
			System.out.println("\n Reading Impressions");
			System.out.println("\n Total Impressions is :" +Impressions);
			return Impressions;
		}
	
	/**
	 * @return History graph value read
	 */
	public void verifyBingHistoryGraph() {
		
		//display tool tip
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
	
		//read the tooltip variables
		tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);
		int index = tooltipvalue.indexOf(":");
		String tooltip1 = tooltipvalue.substring(index+1);
		System.out.println("Impressions :" +tooltip1);
		}
	
	//Compare UI and exported Impression
	public void compareUInExportImpressions() throws Exception {
		int x=0;
		if (CurrentState.getBrowser().equals("chrome")) {
		x = GetDataUsingColName("./downloads/chromeBingXLSX.xlsx","Impression");
		}
		if(CurrentState.getBrowser().equals("IE")){
			x = GetDataUsingColName("./downloads/IEBingXLSX.xlsx","Impression");
		}
		if(CurrentState.getBrowser().equals("Firefox")){
			x = GetDataUsingColName("./downloads/FirefoxBingXLSX.xlsx","Impression");
		}
		WebElement Impressions = driver.findElement(By.xpath("//div[@class='big-stats tooltip-info']//span"));
		int TotalImpressions = getImpressions(Impressions); 
		Assert.assertEquals(x, TotalImpressions , "Count is equal");
	}	
}
