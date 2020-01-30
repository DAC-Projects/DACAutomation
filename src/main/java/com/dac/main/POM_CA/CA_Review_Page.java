package com.dac.main.POM_CA;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import org.testng.asserts.SoftAssert;

import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Review_Page extends CA_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public CA_Review_Page(WebDriver driver) {

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

	@FindBy(xpath="(//*[@id='divCIReview']/h1)")
	private WebElement CATitleContent;

	@FindBy(xpath="(//h4[contains(text(),'Location Competitor Sets')])")
	private WebElement CALocationCompetitorSet;

	@FindBy(xpath="(//*[@id='btnExportAll']")
	private WebElement exportlocationcomp;



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

	@FindBy(css = "button#btnExport>span")
	private WebElement exportBtn;

	@FindBy(css="button#btnExportAll>span")
	private WebElement exportBtnAll;

	@FindBy(xpath="(//button[@id='btnExport'])[2]")
	private WebElement exportlocation;

	@FindBy(css = "div#compIntOverviewContainer")
	private WebElement overviewReport;

	@FindBy(xpath="(//div[@id='compIntOverviewContainer'])[2]")
	private WebElement LocationReport;

	@FindBy(xpath = "//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')]")
	private List<WebElement> competitors;

	String xpathCompetitors = "(//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')])";
	String compName = ".//div[starts-with(@class, 'competitorName')]";
	String compScore = ".//div[starts-with(@class, 'competitorScore')]";

	List<WebElement> columns;
	List<WebElement> rows;

	public void exportReviewReport() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(overviewReport, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		download(CurrentState.getBrowser(), exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), ReviewExport);
		//renamefile(getLastModifiedFile(Exportpath), ReviewExport); 
	}

	public void exportAllReviewLocationSet() throws InterruptedException, FileNotFoundException, IOException
	{
		waitForElement(CALocationCompetitorSet, 10);
		waitForElement(exportBtnAll,30);
		scrollByElement(exportBtnAll);
		download(CurrentState.getBrowser(), exportBtnAll, 20);
		//convertExports(getLastModifiedFile(Exportpath), ReviewLocationCompetitorExportAll);
		renamefile(getLastModifiedFile(Exportpath), ReviewLocationCompetitorExportAll); 



	}

	public void exportlocationset() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(CALocationCompetitorSet, 10);
		waitForElement(exportlocation, 10);
		scrollByElement(exportlocation);
		download(CurrentState.getBrowser(), exportlocation, 20);
		//	convertExports(getLastModifiedFile(Exportpath), ReviewLocationCompetitorExport);
		renamefile(getLastModifiedFile(Exportpath), ReviewLocationCompetitorExport); 
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

	public List<Map<String, String>> getLocationReport() {
		// TODO Auto-generated method stub
		waitForElement(LocationReport, 10);
		scrollByElement(LocationReport);
		Map<String, String> kMap;
		List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= competitors.size(); i++) {
			WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
			//ovrwRprtData.put("Score", s.findElement(By.xpath(compScore)).getText());
			kMap = new HashMap<String, String>();
			kMap.put("compName", s.findElement(By.xpath(compName)).getText());
			kMap.put("score", s.findElement(By.xpath(compScore)).getText());
			System.out.format("%10s%10s", s.findElement(By.xpath(compName)).getText(),
					s.findElement(By.xpath(compScore)).getText());
			System.out.println("");
			ovrwRprtData.add(kMap);
		}
		System.out.println("Over view report"+ovrwRprtData);
		return ovrwRprtData;

	}

	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(overviewReport, timeout) && waitForElement(siteTable, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	@Override
	public String[][] readTable(WebElement table) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		action.moveToElement(table);
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		String[][] rowResults = new String[allRows.size()][];
		int i = 0;
		String[] cellValues;
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			if (cells.size() == 0)
				cells = row.findElements(By.tagName("th"));

			cellValues = new String[cells.size()];
			int j = 0;

			for (WebElement cell : cells) {
				if (cell.getText() != null) {

					if (cell.findElements(By.cssSelector("span.fivestars")).size() > 0) {
						cellValues[j] = cell.findElement(By.cssSelector("span.fivestars>span")).getAttribute("style")
								.toString();
					} else
						cellValues[j] = cell.getText().replaceAll("[^\\p{Print}]", "").toString();
				} else
					cellValues[j] = null;

				j++;
			}
			rowResults[i] = cellValues;

			System.out.println(Arrays.toString(rowResults[i]));

			i++;
		}
		System.out.println("\n");
		return rowResults;
	}

	public List<Map<String, String>> getExportData() throws Exception {
		exportReviewReport();

		String[][] table = new ExcelHandler(Exportpath + ReviewExport, "Sheet0").getExcelTable();
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

	public List<Map<String, String>> getExportDataLocation() throws Exception {
		exportlocationset();

		String[][] table = new ExcelHandler(Exportpath + ReviewLocationCompetitorExport, "CA_LocalReview").getExcelTable();
		List<Map<String, String>> exportData1 = new ArrayList<Map<String, String>>();
		//int colSize = table[0].length;
		for (int col = 1; col < 2; col++) {
			Map<String, String> kMap = new HashMap<String, String>();

			for (int i = 1; i < table.length; i++) {
				System.out.println("i value" + i);
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			exportData1.add(kMap);

		}
		System.out.println("Export data 1"+ exportData1);

		return exportData1;

	}

	public String[][] getExportDataLocationInArray() throws Exception {
		exportlocationset();

		String[][] table = new ExcelHandler(Exportpath + ReviewLocationCompetitorExport, "CA_LocalReview").getExcelTable();

		return table;

	}


	public void compareExportnTable(List<Map<String, String>> exportData1, List<Map<String, String>> siteTableData) {

		for (Map<String, String> m1 : exportData1) {
			for (Map<String, String> m2 : siteTableData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(m1.size() - 1, m2.size());
					m1.forEach((k,v)->{
						System.out.println("for name " + k + " score from export " + v
								+ "and score from site table" + m2.get(k));
						if (!k.equalsIgnoreCase("Overall") && !k.contains("Yellowpages")
								&& !k.equals("compName")) {
							Assert.assertEquals(formatFloat(v), formatFloat(m2.get(k)) / 16, 0.05,
									"Verifying score for " + k + "for" + m1.get("compName"));
						} else if (k.equalsIgnoreCase("YellowpagesCom")) {
							Assert.assertEquals(formatFloat(v), formatFloat(m2.get("Yellowpages.com")) / 16,
									0.05, "Verifying score for " + k + "for" + m1.get("compName"));
						} else if (k.equalsIgnoreCase("Yellowpages")) {
							Assert.assertEquals(formatFloat(v), formatFloat(m2.get("Yellowpages.ca")) / 16,
									0.05, "Verifying score for " + k + "for" + m1.get("compName"));
						}
					});
				}
			}

		}

	}


	public void compareExportnTableInArray(String [][] exportData1, String [][] siteTableData) {
		SoftAssert softAssertion= new SoftAssert();

		for(int i=1;i<siteTableData.length;i++) {

			for(int j=1;j<siteTableData[1].length;j++) {

				softAssertion.assertEquals(siteTableData[i][j], exportData1[i+2][j]);

System.out.println("i,j:"+i+","+j+" Actual , expected "+siteTableData[i][j]+","+exportData1[i+2][j]);
				//	exportData1[i+2][j] siteTableData[i][j]
			}
		}
		
		softAssertion.assertAll();

	}

	public void LocationCompetitorSetNavigation() throws InterruptedException
	{
		if(CALocationCompetitorSet.isDisplayed()) {
			clickelement(CALocationCompetitorSet);
			Thread.sleep(3000);
		}
	}



}
