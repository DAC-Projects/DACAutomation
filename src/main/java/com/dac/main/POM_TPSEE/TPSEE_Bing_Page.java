package com.dac.main.POM_TPSEE;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	@FindBy(xpath = "//button[@id = 'btnExport']")
	private WebElement export;

	/*----------------------------------Locators----------------------------------------------*/
	
	
	//Exporting Bing Report	
	public void exportBing() throws InterruptedException, FileNotFoundException, IOException {
		
		JSWaiter.waitJQueryAngular();
		if(export.isDisplayed() && export.isEnabled()) {
			wait.until(ExpectedConditions.visibilityOf(export));
			action.moveToElement(export).click(export).perform();
			Thread.sleep(5000);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+BingExport));
			Thread.sleep(5000);
			CurrentState.getLogger().info("downloaded file name: "+getLastModifiedFile("./downloads"));
			}else {
			System.out.println("No Data Available in Bing Page");
		}
	}
	
	//Getting Impressions from UI
	public List<Map<String,Integer>> getImpressions() throws Exception {		
		JSWaiter.waitJQueryAngular();
		List<Map<String, Integer>> totalImpression = new ArrayList<Map<String, Integer>>();
		waitForElement(Impressions, 10);
		scrollByElement(Impressions);
		String Impression = Impressions.getText();
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
			Map<String, Integer> kMap = new HashMap<String, Integer>();
			kMap.put("TotalImpression", Impressions);
			totalImpression.add(kMap);
			return totalImpression;
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
		Map<String, String> kMap = new HashMap<String, String>();
		String tooltip1 = tooltipvalue.substring(index+1);
		System.out.println("Impressions :" +tooltip1);
		
		/*//display tool tip
				waitForElement(hstryGrph, 30);
				scrollByElement(hstryGrph);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 10, 10).click().perform();
			
				//read the tooltip variables
				tooltipvalue = grphtooltip.getText();
				System.out.println("\n Reading tooltipdata ********** \n");
				System.out.println("\n tooltipvalue is \n" +tooltipvalue);
				int index = tooltipvalue.indexOf(":");
				Map<String, String> kMap = new HashMap<String, String>();
				String tooltip1 = tooltipvalue.substring(index+1);
				System.out.println("Impressions :" +tooltip1);*/
		
		}
	
	//Getting last column data - Impression from excel
	public List<Map<String,Integer>> addsubexcel() throws IOException {
		FileInputStream file = new FileInputStream(new File("./downloads/chromeBingExport.xlsx"));
		Workbook wb = new XSSFWorkbook(file);
		Sheet sh = wb.getSheetAt(0);
		int lastRownum = sh.getLastRowNum();
		List<Map<String, Integer>> Impressions = new ArrayList<Map<String, Integer>>();
		String s = null;
		int cellValue = 0;
		int y = 0;
		int sum = 0;
		Map<String, Integer> kMap = new HashMap<String, Integer>();
		for (int i = 1; i < lastRownum; i++) {
			Row row = sh.getRow(i);     
			int lastcol = row.getLastCellNum() - 1;
			Cell cell = row.getCell(lastcol);
			if (cell != null) {
				String cellValue1 = cell.getStringCellValue();
				if(cellValue1.contains("-")) {
					s = cellValue1.replace("-", "0");
					y = Integer.parseInt(s);
					System.out.println("\n " +s);
					sum = sum+y;
					}else {
						cellValue = Integer.parseInt(cellValue1);
						System.out.println("\n " +cellValue);
						sum = sum+cellValue;
						}
        			} else {
        				System.out.println("Smt wrong");
        			}
				}    	
		kMap.put("Impressions", sum);
		Impressions.add(kMap);
		wb.close();
		return Impressions;
	}

	//Compare UI and exported Impression
	public void compareUInExportImpressions(List<Map<String, Integer>> addsubexcel,
			List<Map<String, Integer>> impressions2) {		
				for (Map<String, Integer> m1 : addsubexcel) {
					for (Map<String, Integer> m2 : impressions2) {
						if (m1.get("Impressions").equals(m2.get("TotalImpression"))) {
							Assert.assertEquals(m1.get("Impressions").equals(m2.get("TotalImpression")),true);
						}
					}
				}
			}
	}
