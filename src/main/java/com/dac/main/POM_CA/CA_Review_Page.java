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
import resources.ExcelTestDataHandler;

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

	@FindBy(css = "button#btnExport>span")
	private WebElement exportBtn;

	@FindBy(css = "div#compIntOverviewContainer")
	private WebElement overviewReport;

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

		String[][] table = new ExcelTestDataHandler(Exportpath + ReviewExport, "Sheet0").getExcelTable();
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

}
