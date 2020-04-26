package com.dac.main.POM_CA;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

	@FindBy(xpath = "//div[@class='site-score pull-right ng-binding']")
	private List<WebElement> vendorScore;

	@FindBy(xpath = "//div[@class='category-information']")
	private WebElement categoryInformation;

	@FindBy(xpath = "(//div[@class='category-information'])[2]")
	private WebElement categoryInformation2;

	@FindBy(xpath = "//div[@class=\"score pull-right ng-binding\"]")
	private List<WebElement> avgScore;


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

	public void calculateContentAnalysisScore() throws Exception {
		String[][] table = new ExcelHandler(Exportpath + CAExport, "Sheet0").getExcelTable();

		double[] FinScore = new double[table[0].length-1];		
		double[] score = new double[table.length-2];
		int counter;
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.FLOOR);
		for (int j=0;j<table[0].length-1;j++) {
			double totScore = 0;
			counter = 0;
			for(int i=0;i<table.length-2;i++) {
				score[i] = Double.parseDouble(table[i+2][j+1]);
				if(score[i]!=0.0) {
					counter = counter+1;
				}
				totScore = totScore + score[i];				
				System.out.println("Total score ="+totScore);
			}
			totScore=Double.parseDouble(df.format(totScore));
			System.out.println(" Rounded totscore "+totScore);
			if(j==0) {
				FinScore[j] = Math.round((totScore/score.length)*100.0)/100.0;
				System.out.println("double array value"+j+":" + Arrays.toString(score) );
			}
			else {
				FinScore[j] = Math.round((totScore/counter)*100.0)/100.0;
			}
		}
		System.out.println("Finalscore "+Arrays.toString(FinScore));
		for(int i=0;i<table.length-2;i++) {		
			Assert.assertEquals(FinScore[i], Double.parseDouble(table[1][i+1]));
		}
	}

	public void UiCalculation() {

		System.out.println("UI calculation");

		double score;
		String UIvalue = null;
		double Finscore;
		int size;
		int counter;
		//	System.out.println("size "+size);		


		for (int j =1;j<=8;j++) {
			Finscore=0;
			score=0;
			counter=0;
			scrollByElement(driver.findElement(By.xpath("(//div[@class='category-information'])["+j+"]")));
			clickelement(driver.findElement(By.xpath("(//div[@class='category-information'])["+j+"]")));
			size = vendorScore.size()/2;		
			for(int i =0; i<size;i++) {
				UIvalue =  vendorScore.get(i).getText().replace("%", "");
				if( !(UIvalue.contains("N/A") ||(UIvalue.contains("-")))) {
					score =Double.parseDouble(UIvalue)+score;
					counter++;
				}		
				Finscore =  Math.round((score/counter)*100.0)/100.0;		 	
			}
		
			if(counter == 0)
			{
				assertEquals(avgScore.get(j-1).getText(), "N/A");
			}
			else {
				String Value = avgScore.get(j-1).getText().replace("%", "");
				assertEquals(Double.parseDouble(Value), Finscore);
			}
			clickelement(driver.findElement(By.xpath("(//div[@class='category-information'])["+j+"]")));
		}
	}
}
