package com.dac.main.POM_CA;

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
import org.testng.Assert;

import resources.BaseTest;
import resources.ExcelTestDataHandler;

public class CA_Visibility_Page extends CA_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public CA_Visibility_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button#btnExport>span")
	private WebElement exportBtn;

	@FindBy(css = "div#compIntOverviewContainer")
	private WebElement overviewReport;

	@FindBy(xpath = "//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')]")
	private List<WebElement> competitors;

	String xpathCompetitors = "(//div[@id='compIntOverviewContainer']//div[starts-with(@class,'overviewSubContainer')])";
	String compName = ".//div[starts-with(@class, 'competitorName')]";
	String compScore = ".//div[starts-with(@class, 'competitorScore')]";

	@FindBy(css = "table#compIntVisibilitySitesTable")
	private WebElement siteTable;

	List<WebElement> columns;
	List<WebElement> rows;

	public void exportvisibilityReport() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(overviewReport, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		download(BaseTest.browser, exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), VisibilityExport);
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

	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(overviewReport, timeout) && waitForElement(siteTable, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	public List<Map<String, String>> getExportData() throws Exception {
		exportvisibilityReport();

		String[][] table = new ExcelTestDataHandler(Exportpath + VisibilityExport, "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();

			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			exportData.add(kMap);
		}

		return exportData;

	}

	public void compareExportnTable(List<Map<String, String>> exportData, List<Map<String, String>> siteTableData) {

		for (Map<String, String> m1 : exportData) {
			for (Map<String, String> m2 : siteTableData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(m1.size() - 1, m2.size());
					for (String name : m1.keySet()) {
						System.out.println("for name " + name + " score from export " + m1.get(name)
								+ "and score from site table" + m2.get(name));
						if (!name.equalsIgnoreCase("Overall") && !name.contains("Yellowpages")
								&& !name.equals("compName")) {
							Assert.assertEquals(formatFloat(m1.get(name)), formatFloat(m2.get(name)), 0.05,
									"Verifying score for " + name + "for" + m1.get("compName"));
						} else if (name.equalsIgnoreCase("YellowpagesCom")) {
							Assert.assertEquals(formatFloat(m1.get(name)), formatFloat(m2.get("Yellowpages.com")), 0.05,
									"Verifying score for " + name + "for" + m1.get("compName"));
						} else if (name.equalsIgnoreCase("Yellowpages")) {
							Assert.assertEquals(formatFloat(m1.get(name)), formatFloat(m2.get("Yellowpages.ca")), 0.05,
									"Verifying score for " + name + "for" + m1.get("compName"));

						}
					}
				}
			}

		}

	}

}
