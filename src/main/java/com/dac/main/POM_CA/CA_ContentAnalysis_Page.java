package com.dac.main.POM_CA;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.CurrentState;
import resources.ExcelHandler;

//
public class CA_ContentAnalysis_Page extends CA_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public CA_ContentAnalysis_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button#btnExport>span")
	private WebElement exportBtn;

	@FindBy(css = "table.table-responsive.table-hover>thead")
	private WebElement caOverviewReport;

	@FindBy(css = "table.table-responsive.table-hover>thead>th")
	private List<WebElement> competitors;

	String xpathCompetitors = "(//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')])";
	String compName = ".//div[starts-with(@class, 'competitorName')]";
	String compScore = ".//div[starts-with(@class, 'competitorScore')]";

	List<WebElement> columns;
	List<WebElement> rows;

	public void exportReviewReport() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(caOverviewReport, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		download(CurrentState.getBrowser(), exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), CAExport);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		waitForElement(caOverviewReport, 10);
		scrollByElement(caOverviewReport);
		Map<String, String> kMap;
		List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
		
	
		for(WebElement row: competitors) {
		  kMap = new HashMap<String, String>();
	    kMap.put("compName", row.findElement(By.xpath(compName)).getText().trim());
	    kMap.put("score",  row.findElement(By.xpath(compScore)).getText().trim());
      System.out.println("");
      ovrwRprtData.add(kMap);
	    
		}

		return ovrwRprtData;

	}

	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(caOverviewReport, timeout) 
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}



	public List<Map<String, String>> getExportData() throws Exception {
		exportReviewReport();

		String[][] table = new ExcelHandler(Exportpath + CAExport, "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();

			for (int i = 1; i < table.length; i++) {
				System.out.println("i value" + i);
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			exportData.add(kMap);
		}

		return exportData;

	}

}
