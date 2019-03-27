package com.dac.main.POM_CA;

import static org.testng.Assert.assertTrue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

	// locators
	
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
	
	// site table
	@FindBy(css = "table#compIntVisibilitySitesTable")
	private WebElement siteTable;

	List<WebElement> columns;
	List<WebElement> rows;

	/**
	 * Export visibility overview report
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void exportvisibilityReport() throws InterruptedException, FileNotFoundException, IOException {
		waitForElement(overviewReport, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		download(CurrentState.getBrowser(), exportBtn, 20);
		convertExports(getLastModifiedFile(Exportpath), VisibilityExport);
	}

	/* (non-Javadoc)
	 * Reads overview report and return values as list
	 * @see com.dac.main.POM_CA.CA_abstractMethods#getOverviewReport()
	 */
	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		waitForElement(overviewReport, 10);
		scrollByElement(overviewReport);
		Map<String, String> kMap;
		
		List<Map<String, String>> ovrwRprtData = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= competitors.size(); i++) {
			WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
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

	/**
	 * @param timeout
	 * Verifies all elements are present till timeout in seconds
	 */
	public void verify_pageloadCompletely(int timeout) {
		if (waitForElement(overviewReport, timeout) && waitForElement(siteTable, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	
	/**
	 * Reads export data and return as a list
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getExportData() throws Exception {
		exportvisibilityReport();

		String[][] table = new ExcelHandler(Exportpath + VisibilityExport, "Sheet0").getExcelTable();
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

	/**
	 * Compare export and table
	 * @param exportData
	 * @param siteTableData
	 */
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

}
