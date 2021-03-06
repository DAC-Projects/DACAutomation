package com.dac.main.POM_CA;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Accuracy_Page extends CA_abstractMethods{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public CA_Accuracy_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="myGroups")
	private WebElement filterGroup;

	@FindBy(xpath="//*[@class='menu transition hidden']")
	private WebElement filterDropDown;

	@FindBy(xpath="(//*[@id='divCIAccuracy']/h1)")
	private WebElement CATitleContent;

	// Global Filter locators

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList")
	private WebElement FilterCountry;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList1")
	private WebElement FilterState;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList2")
	private WebElement FilterCity;

	@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList3")
	private WebElement Filterlocation;

	@FindBy(css = "button#apply_filter")
	private WebElement Apply_filter;

	@FindBy(xpath = "//table[@class='table-responsive table-hover']/tbody/tr")
	private List<WebElement> reviewTableRow;

	// export button
	@FindBy(css = "button#btnExport>span")
	private WebElement exportBtn;

	// overview report
	@FindBy(css = "div#compIntOverviewContainer")
	private WebElement overviewReport;

	// section of overview report
	@FindBy(xpath = "//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')]")
	private List<WebElement> competitors;



	String xpathCompetitors = "(//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')])";
	String compName = ".//div[starts-with(@class, 'competitorName')]";
	String compScore = ".//div[starts-with(@class, 'competitorScore')]";
	
	@FindBy(xpath="//table[@class='table-responsive table-hover']/tbody/tr")
	private List<WebElement> tableRow;

	// site table
	@FindBy(css = "table#compIntAccuracySitesTable")
	private WebElement siteTable;

	List<WebElement> columns;
	List<WebElement> rows;


	public void exportaccuracyReport() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(overviewReport, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		download(CurrentState.getBrowser(), exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), AccuracyExport);
	}



	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		waitForElement(overviewReport, 10);
		scrollByElement(overviewReport);
		Map<String, String> kMap;
		List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= competitors.size(); i++) {
			WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
			// ovrwRprtData.put("Score", s.findElement(By.xpath(compScore)).getText());
			kMap = new HashMap<String, String>();
			kMap.put("compName", s.findElement(By.xpath(compName)).getText());
			kMap.put("score", s.findElement(By.xpath(compScore)).getText());
			System.out.format("%10s%10s", s.findElement(By.xpath(compName)).getText(),
					s.findElement(By.xpath(compScore)).getText());
			System.out.println("");
			ovrwRprtData.add(kMap);
		}
		return ovrwRprtData;
	}

	//	@Override
	//	public List<Map<String, String>> getOverviewReport() {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}
	public List<Map<String, String>> DataTable() throws InterruptedException{


		WebElement TableTitle = driver.findElement(By.xpath("//*[@class = 'title-divider']"));
		List < WebElement > rows_table = tableRow;
		return null;

	}


	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(overviewReport, timeout) && waitForElement(siteTable, timeout)
				& waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	public List<Map<String, String>> getExportData() throws Exception {
		exportaccuracyReport();

		String[][] table = new ExcelHandler(Exportpath + AccuracyExport, "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();

			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			exportData.add(kMap);//to be reviewed
		}


		return exportData;

	}

	public void compareExportnTable(List<Map<String, String>> exportData, List<Map<String, String>> siteTableData) {

		for (Map<String, String> m1 : exportData) {
			for (Map<String, String> m2 : siteTableData) {

				if (m1.get("compName").equals(m2.get("compName"))) {

					Assert.assertEquals(m1.size() - 1, m2.size());
					m1.forEach((k,v)->{
						System.out.println("for name " + k + " score from export " + v
								+ "and score from site table" + m2.get(k));
						if (!k.equalsIgnoreCase("Overall") && !k.contains("Yellowpages")
								&& !k.equals("compName")) {
							Assert.assertEquals(formatFloat(v), formatFloat(m2.get(k)), 0.05,
									"Verifying score for " + k + "for" + m1.get("compName"));
						} else if (k.equalsIgnoreCase("YellowpagesCom")) {
							Assert.assertEquals(formatFloat(v), formatFloat(m2.get("Yellowpages.com")), 0.05,
									"Verifying score for " + k + "for" + m1.get("compName"));
						} else if (k.equalsIgnoreCase("Yellowpages")) {
							Assert.assertEquals(formatFloat(v), formatFloat(m2.get("Yellowpages.ca")), 0.05,
									"Verifying score for " + k + "for" + m1.get("compName"));

						}

					});

				}
			}

		}

	}

	public void calculateAccuracyScore() throws Exception {
		String[][] table = new ExcelHandler(Exportpath + AccuracyExport, "Sheet0").getExcelTable();

		//	double FinScore;
		double[] FinScore = new double[table[0].length-1];

		int[][] visScore = CA_Visibility_Page.finalArray;
		double[] score = new double[table.length-2];
		//		double [][] fullScore = new double[table.length-2][table[0].length-1];
		for (int j=0;j<table[0].length-1;j++) {
			double totScore = 0;
			int sum = 0;
			for(int i=0;i<table.length-2;i++) {			 

				if(table[i+2][j+1].isEmpty()) {
					score[i] = 0;
				}
				else {
				score[i] = Double.parseDouble(table[i+2][j+1].replace("%", ""));
				}
				System.out.println("double array value"+i+":" + Arrays.toString(score) );

				try {
				totScore = totScore + visScore[j][i]*score[i];
				}
				catch (Exception e) {
					System.err.println("Please check Visibility report is executed as a precondition for Claculation");
				}
				
				sum = sum + visScore[j][i];
				
				System.err.println("Total score" +j+totScore+" Sum ="+sum);
			}
			if(sum!=0) {
				FinScore[j] = Math.round((totScore/sum)*100.0)/100.0;
			}
			else {
				FinScore[j] = 0.00;
			}
			
		}
		System.out.println("FinScore :"+Arrays.toString(FinScore));
		for (int k=0;k<table[0].length-1;k++) {
			
			assertEquals(table[1][k+1].replace("%", ""), String.format("%.2f", FinScore[k]));
			System.out.println("Compare "+table[1][k+1].replace("%", "")+" * "+ String.format("%.2f", FinScore[k]) );
		}
		
	}
}
