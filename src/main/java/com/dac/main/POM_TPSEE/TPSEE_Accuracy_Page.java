package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Accuracy_Page extends TPSEE_abstractMethods{
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	
	String xpathCompetitors = "//*[@id='divBars']";
	String NoofLocation = "//*[@id='divNumOfLocations']";
	String overallscore = "//*[@id='divOverallScoreValue']";
	
	@FindBy(xpath = "//*[@id='allSitesScores']")
	private List<WebElement> Site;
	
	//Exporting into csv 
		@FindBy(xpath = "//button[@id='btnLocationExportPopUp']")
		private WebElement exportBtn;
		
		@FindBy(xpath = "//*[@id='exportdate']")
		private WebElement exportdate;
		
		@FindBy(id = "ui-datepicker-div")
		private WebElement dtpicker;
		
		@FindBy(css = "td.ui-datepicker-days-cell-over")
		private WebElement date;
		
		@FindBy(xpath = "//*[@id='btnLocationExport']")
		private WebElement export;
	
	public TPSEE_Accuracy_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);

	}
	
	//tooltipvalue in the graph
	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip;
	
	//section of overall report
		@FindBy(xpath = "//*[@id='divBars']")
		private WebElement overall;
		
		@FindBy(xpath = "//*[@id='divBars']")
		private List<WebElement> comp;

	public void verify_pageloadCompletely(int timeout) {
		if ( waitForElement(grphtooltip, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}
	
	//overall accuracy score
	@Override
	public List<Map<String, String>> getOverviewReport() {

	waitForElement(overall, 40);
	scrollByElement(overall);
	Map<String, String> kMap;
	List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
	for (int i = 1; i <= Site.size(); i++) {
		WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
		kMap = new HashMap<String, String>();
		kMap.put("NoofLocation", s.findElement(By.xpath(NoofLocation)).getText());
		kMap.put("score", s.findElement(By.xpath(overallscore)).getText());
		System.out.format("%10s%10s", s.findElement(By.xpath(NoofLocation)).getText(),
				s.findElement(By.xpath(overallscore)).getText());
		System.out.println("");
		ovrwRprtData.add(kMap);
	}
	return ovrwRprtData;
	}

	public void compareExportnTable(List<Map<String, String>> verifyHistoryGraph,
			List<Map<String, String>> verifySitetable) {
		
		
	}
	
	/* Export visibility overview report below filter*/
	public void exportvisibilityrpt() throws InterruptedException, FileNotFoundException, IOException{
		//waitForElement(overall, 40);
		waitForElement(exportBtn, 40);
		scrollByElement(exportBtn);
		clickelement(exportBtn);
		waitForElement(exportdate,40);
		scrollByElement(exportdate);
		clickelement(exportdate);
		waitForElement(dtpicker,40);
		scrollByElement(dtpicker);
		waitForElement(date, 40);
		scrollByElement(date);
		clickelement(date);
	    //download visibility report
	    download(CurrentState.getBrowser(), export, 30);
	    convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExport));
	     
	}
	
	
	//printing visibility report data from downloaded excel sheet 
		public List<Map<String, String>> getExportData() throws Exception {
			
			exportvisibilityrpt();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExport), "Sheet0").getExcelTable();
			List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			for (int col = 1; col < colSize; col++) {
				//adding data into map
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("compName", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				exportData.add(kMap);
			}
			//returning visibility report from excel
			return exportData;

		}
}