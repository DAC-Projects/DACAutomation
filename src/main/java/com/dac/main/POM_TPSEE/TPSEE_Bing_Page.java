package com.dac.main.POM_TPSEE;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-group']/*[name()='g'])[1]")
	private WebElement highChartZoom;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[1]")
	private WebElement highChart_1M;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[2]")
	private WebElement highChart_3M;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[3]")
	private WebElement highChart_6M;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[4]")
	private WebElement highChart_YTD;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[5]")
	private WebElement highChart_1y;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[6]")
	private WebElement highChart_All;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-input-group']/*[name()='g'])[2]/*[name()='text']")
	private WebElement highChart_fromDate;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-input-group']/*[name()='g'])[4]/*[name()='text']")
	private WebElement highChart_toDate;
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;
	
	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;

	/*----------------------------------Locators----------------------------------------------*/
	
	String datavalidatation;
	//Exporting Bing Report	
	public void exportCSVBing() throws InterruptedException, FileNotFoundException, IOException {
		JSWaiter.waitJQueryAngular();
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
		if(export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
		}if(CSVExport.isDisplayed() && CSVExport.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(XLSXExport));
			CSVExport.click();
			Thread.sleep(5000);
			getLastModifiedFile(Exportpath);
			}else {
			System.out.println("No Data Available in Bing");
		}
		}else {
			System.out.println("No Data for Bing");
		}
	}
	
	public void exportXLSXBing(String file) throws InterruptedException, FileNotFoundException, IOException {

		JSWaiter.waitJQueryAngular();
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
		if(export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
		}if(XLSXExport.isDisplayed() && XLSXExport.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(XLSXExport));
			XLSXExport.click();
			Thread.sleep(5000);
			renamefile(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+file));
			Thread.sleep(6000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile(Exportpath));
			}else {
			System.out.println("No Data Available in GMB");
		}
		}else {
			System.out.println("No Data for Bing");
		}
	}
	
	//Getting Impressions from UI
	public int getImpressions(WebElement a) throws Exception {		
		JSWaiter.waitJQueryAngular();
		int Impressions = 0;
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
		String Impression = a.getText();
		String u = "";		
		if(Impression.contains(",")) {
			u = Impression.replace(",", "");
			u.trim();
			Impressions = Integer.parseInt(u);
			}else {
				Impressions = Integer.parseInt(Impression);
			}
			System.out.println("\n Reading Impressions");
			System.out.println("\n Total Impressions is :" +Impressions);
	}else {
		System.out.println("No Data for Bing");
	}
			return Impressions;
		}
	
	/**
	 * @return History graph value read
	 */
	public void verifyBingHistoryGraph() {
		
		//display tool tip
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
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
		}else {
			System.out.println("No Data for Bing");
		}
	}
	
	//Compare UI and exported Impression
	public void compareUInExportImpressions(String chromepath, String IEpath, String FFpath) throws Exception {
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
		int x=0;
		if (CurrentState.getBrowser().equals("chrome")) {
		x = GetDataUsingColName(chromepath,"Impression");
		}
		if(CurrentState.getBrowser().equals("IE")){
			x = GetDataUsingColName(IEpath,"Impression");
		}
		if(CurrentState.getBrowser().equals("Firefox")){
			x = GetDataUsingColName(FFpath,"Impression");
		}
		WebElement Impressions = driver.findElement(By.xpath("//div[@class='big-stats tooltip-info']//span"));
		int TotalImpressions = getImpressions(Impressions); 
		Assert.assertEquals(x, TotalImpressions , "Count is equal");
		}else {
			System.out.println("No Data for Bing");
		}
	}	
	
	
	/**
	 * Click on highchart zoom functionality
	 * @param durationFor
	 * @return
	 * @throws Exception
	 */
	public TPSEE_abstractMethods clickBingHighchartCriteria(String durationFor) throws Exception {
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
		durationFor = durationFor.toLowerCase();
		scrollByElement(highChartZoom);
		int days;			
		if((!durationFor.equalsIgnoreCase("null"))) {				
			switch(durationFor) {				
			case "1m"  : 	try{
								clickelement(highChart_1M);
								if(eleClicked(highChart_1M)) {
									days = BinggetNumberofDays();			
									if(days >= 28 && days<=31 ) {
										System.out.println("1 Month data is displayed");
									}else {
										System.out.println("Not 1 Month");
										}
									}else {
										System.out.println("Element not clicked");
									}
							}catch(Exception e) {
								e.printStackTrace();
							}
							break;
						
			case "3m"  : 	try{
								clickelement(highChart_3M);
								if(eleClicked(highChart_3M)) {
									days = BinggetNumberofDays();
									if(days>=90 && days<=92) {
										System.out.println("3 Month data is displayed");
									}else {
										System.out.println("Not 3 Month");
										}
									}else {
										System.out.println("Element not clicked");
									}
								}catch(Exception e) {
									e.printStackTrace();
								}
								break;
						 
			case "6m"  : 	try{
								clickelement(highChart_6M);
								if(eleClicked(highChart_6M)) {
									days = BinggetNumberofDays();
									if(days>=180 && days<=184) {
										System.out.println("6 Month data is displayed");
									}else {
										System.out.println("Not 6 Month");
										}
									}else {
										System.out.println("Element not clicked");
									}
								}catch(Exception e) {
									e.printStackTrace();
									}
							break;
						 
			case "ytd" : 	try{
								clickelement(highChart_YTD);
								if(eleClicked(highChart_YTD)) {
									days = BinggetNumberofDays();
								}else {
									System.out.println("Element Not clicked");
									}
							}catch(Exception e) {
								e.printStackTrace();
							}
							break;
					     
			case "1y"  : 	try{
								clickelement(highChart_1y);
								if(eleClicked(highChart_1y)) {
									days = BinggetNumberofDays();
									if(days>=364 && days<=366) {
										System.out.println("1 Year data is displayed");
									}else {
										System.out.println("Not 1 Year");
										}
									}else {
										System.out.println("Element not clicked");
									}
								}catch(Exception e) {
									e.printStackTrace();
								}
							break;
						 
			case "all" :
			default    : 	try{
								clickelement(highChart_All);
								if(eleClicked(highChart_All)) {
									days = BinggetNumberofDays();
								}else {
									System.out.println("Element not clicked");
									}
								}catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
		}else {
			System.out.println("No Data for Bing");
		}
				return this;
		}	
	
	/**
	 * To get difference between two dates
	 * @return
	 * @throws Exception
	 */
	public  int BinggetNumberofDays() throws Exception {
		int diff = 0;
		datavalidatation = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		if(!datavalidatation.equals("There is currently not enough data from Bing to display this report")) {
		Date init = getCurrentfromDate();
		Thread.sleep(5000);
		Date enddate =  getCurrenttoDate();
		Thread.sleep(5000);
		long difference = Math.abs(init.getTime() - enddate.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000);
		diff = (int)(long)differenceDates;		
		System.out.println(diff);	
		
		}else {
			System.out.println("No Data for Bing");
		}
		return diff;
	}	
	
	
	public String validdata() {
		
		String Isdataavailable = driver.findElement(By.xpath("//*[@class='highcharts-title']")).getText();
		
		return Isdataavailable;
		
		
	}
}
