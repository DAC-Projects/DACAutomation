package com.dac.main.POM_CA;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

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
		download(BaseTest.browser, exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), VisibilityExport);
	}

	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		waitForElement(overviewReport, 10);
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
		if(waitForElement(overviewReport, timeout)&&waitForElement(siteTable, timeout)&&waitForElement(hstryGrph, timeout)&&waitForElement(hstryGrph, timeout)&
				waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	public List<Map<String, String>> verifySitetable() {
		waitForElement(siteTable, 10);
		String[][] table = readTable(siteTable);
		List<Map<String, String>> siteTableData = new ArrayList<Map<String, String>>();

		for (int j = 0; j < table[0].length - 1; j++) {
			Map<String, String> kMap = new HashMap<String, String>();

			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][j + 1]);
				kMap.put(table[i][0], table[i][j + 1]);
			}
			siteTableData.add(kMap);
		}
		System.out.println("google :" + siteTableData.get(0).get("Google"));
		System.out.println(siteTableData.get(0).get("Bing"));
		System.out.println("Superpages :" + siteTableData.get(0).get("Superpages"));

		System.out.println("MAP******************MAP");
		for (String name : siteTableData.get(1).keySet()) {

			String key = name.toString();
			String value = siteTableData.get(1).get(name).toString();
			System.out.println(key + " " + value);
		}

		return siteTableData;

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

	public void compareExprttoOvervw(List<Map<String, String>> exportData, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : exportData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(formatFloat(m1.get("score")),  formatFloat(m2.get("Overall")), 0.05f, "Verifying score for" + m1.get("compName"));
				}
			}
		}
	}

	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(formatFloat(m1.get("score")),  formatFloat(m2.get("Overall")), 0.05f, "Verifying score for" + m1.get("compName"));
				}
			}
		}

	}

	public void compareExportnTable(List<Map<String, String>> exportData, List<Map<String, String>> siteTableData) {

		for (Map<String, String> m1 : exportData) {
			for (Map<String, String> m2 : siteTableData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(m1.size() - 1, m2.size());
					for (String name : m1.keySet()) {
						System.out.println(m1.get(name)+"  "+m2.get(name));
						System.out.println(name);
						if(!name.equalsIgnoreCase("Overall")) {
						Assert.assertEquals(formatFloat(m1.get(name)),  formatFloat(m2.get(name)), 0.05f, "Verifying score for " + name + "for" + m1.get("compName"));
						}
					}
				}
			}

		}

	}

}
