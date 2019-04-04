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
import org.testng.Assert;

import resources.CurrentState;
import resources.ExcelHandler;
import resources.JSWaiter;

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
		
		//@FindBy(xpath = "//div[contains(@class,'row scores-row active')]//a[contains(@class,'load-table')][contains(text(),'')]")
		@FindBy(xpath = "//div//a[@class='load-table'][contains(text(),'CitySearch')]")
		private WebElement sitelink;
		
		@FindBy(xpath = "//a[@id='ToolTables_inaccuracy_results_0']")
		private WebElement tablebtn;
		
		@FindBy(xpath = "//*[@id='inaccuracy_table_title']")
		private WebElement tabletitle;
		
		@FindBy(xpath = "//table[@id='inaccuracy_results']")
		private WebElement tableresult;
		
		@FindBy(xpath = "//table[@id='inaccuracy_results']/tbody")
		private WebElement tableresultset;
		
		@FindBy(xpath = "//thead//tr[@role='row']")
		private WebElement tablehead;
		
		@FindBy(xpath = "//div[@id='allSitesScores']//a[contains(@class,'load-table')][contains(text(),'')]")
		private WebElement sitenames;
		

		@FindBy(xpath = "//div[contains(@class,'row scores-row')]")
		private WebElement siteshow;
		
		@FindBy(xpath = "//input[@id='toggle-inaccuracies']")
		private WebElement InAccuracychkBox;
		
		@FindBy(xpath = "//input[@id='toggle_overridden']")
		private WebElement IgnoredchkBox;
	
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
	JSWaiter.waitJQueryAngular();
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
		ovrwRprtData.add(kMap);
	}
	return ovrwRprtData;
	}

	public void compareExportnTable(List<Map<String, String>> verifyHistoryGraph,
			List<Map<String, String>> verifySitetable) {
		
		
	}
	
	/* Export visibility overview report below filter*/
	public void exportvisibilityrpt() throws InterruptedException, FileNotFoundException, IOException{
		JSWaiter.waitJQueryAngular();
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
			JSWaiter.waitJQueryAngular();
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
		
		
		//Clicking on progress bar and getting the data from the table for found
		public List<Map<String, String>> verifysitelinkdata(){
			try {
			JSWaiter.waitJQueryAngular();
			waitForElement(accuracysite, 40);
			waitForElement(siteshow,30);
			clickelement(siteshow);
			waitForElement(sitelink, 40);
			//getting into progressbar found listing
			scrollByElement(sitelink);
			//clicking on found listing progress bar
			clickelement(sitelink);
			System.out.println("\n progress bar clicked \n");
			waitForElement(tableresult,40);
			scrollByElement(tableresult);
			System.out.println("\n reading table data ********************* \n");
			waitForElement(tableresult,50);
			scrollByElement(tableresult);
			waitForElement(tableresultset,50);
			waitForElement(tableresultset,50);
			String[][] table = readTable(tableresultset);
			int n = table.length;
			System.out.println("\n" +n);
			System.out.println(driver.findElement(By.xpath("//div[@id='inaccuracy_table_title']")).getText());
			}catch(Exception e) {
				e.printStackTrace();
			}
			//adding data into list
			List<Map<String, String>> sitelinkdata = new ArrayList<Map<String, String>>();
			List<WebElement> elements = driver.findElements(By.xpath("tableresultset"));
			waitForElement(tableresult, 40);
		    java.util.Iterator<WebElement> program = elements.iterator();
		        //reading site data
		    while (program.hasNext()) {
		        String values = program.next().getText();
		        if(!values.equals("null"))
		        {
		            System.out.println("\n" +values);
		            
		        }
		        else
		        {
		            System.out.println("\n No sites displayed \n");
		        }
		     }
		    return sitelinkdata;

		}
		
		
		/*public  List<Map<String, String>> numberofentries() {
			
			waitForElement(tableresult,50);
			List<Map<String, String>> totalentries = new ArrayList<Map<String, String>>();
			//getting the text at the bottom of the table and split the string by space
			List<WebElement> col = driver.findElements(By.xpath("//table[@id='inaccuracy_results']/tbody"));
			System.out.println(col.size());
			rows = tableresultset.findElements(By.tagName("tr"));
			waitForElement(tableresultset,50);
			int count = rows.size();
			System.out.println("\n Total number of rows : "+count);
			return totalentries;
			}*/
		
		
		public void exporttable() throws FileNotFoundException, IOException, InterruptedException {
			
			waitForElement(tableresult, 40);
			waitForElement(tablebtn, 40);
			//scrollByElement(tablebtn);
			download(CurrentState.getBrowser(), tablebtn, 30);
			JSWaiter.waitUntilJQueryReady();
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttable));
			
		}
		
	//printing visibility progress bar table data from downloaded excel sheet
	public List<Map<String, String>> getExporttableData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttable();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttable), "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table.length;
			for (int col = 1; col < colSize; col++) {
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("appvendor", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				/*String row = Integer.toString(colSize);
				kMap.put("count", colsize);*/
				//System.out.println("\n The total number of rows \n" +colSize );
				exporttableData.add(kMap);
			}
			return exporttableData;
		}

	public void compareexporttableDatantable(List<Map<String, String>> exporttableData,
			List<Map<String, String>> verifysitelinkdata) {
		for (Map<String, String> m1 : exporttableData) {
			for (Map<String, String> m2 : verifysitelinkdata) {
				if (m1.get("Confirmed Info").equals(m2.get("Confirmed Info"))) {
					Assert.assertEquals(m1.get("Displayed Name"), m2.get("Displayed Name"), "verifying list for " +m2.get("Confirmed Info"));
					Assert.assertEquals(m1.get("Displayed Address"), m2.get("Displayed Address"), "verifying list for " +m2.get("Confirmed Info"));
					Assert.assertEquals(m1.get("Displayed Phone Number"), m2.get("Displayed Phone Number"), "verifying list for " +m2.get("Confirmed Info"));
					
					//Assert.assertEquals(m1.values(), m2.values(), "verify site vendors");
					//Assert.assertEquals(m1, m2, "verify for table report");
				}
			}
		}
		
	}
	
	
	//Clicking on progress bar and getting the data from the table for found
			public List<Map<String, String>> verifyInAccuracysitelinkdata(){
				try {
				JSWaiter.waitJQueryAngular();
				waitForElement(accuracysite, 40);
				waitForElement(siteshow,30);
				clickelement(siteshow);
				waitForElement(sitelink, 40);
				//getting into progressbar found listing
				scrollByElement(sitelink);
				//clicking on found listing progress bar
				clickelement(sitelink);
				System.out.println("\n progress bar clicked \n");
				waitForElement(tableresult,40);
				scrollByElement(tableresult);
				System.out.println("\n reading table data ********************* \n");
				waitForElement(tableresult,50);
				scrollByElement(tableresult);
				WebElement inaccuracychkbox = driver.findElement(By.xpath("//input[@id='toggle-inaccuracies']"));
				inaccuracychkbox.click();
				waitForElement(tableresultset,50);
				waitForElement(tableresultset,50);
				String[][] table = readTable(tableresultset);
				int n = table.length;
				System.out.println("\n" +n);
				System.out.println(driver.findElement(By.xpath("//div[@id='inaccuracy_table_title']")).getText());
				}catch(Exception e) {
					e.printStackTrace();
				}
				//adding data into list
				List<Map<String, String>> InAccuracysitelinkdata = new ArrayList<Map<String, String>>();
				List<WebElement> elements = driver.findElements(By.xpath("tableresultset"));
				waitForElement(tableresult, 40);
			    java.util.Iterator<WebElement> program = elements.iterator();
			        //reading site data
			    while (program.hasNext()) {
			        String values = program.next().getText();
			        if(!values.equals("null"))
			        {
			            System.out.println("\n" +values);
			            
			        }
			        else
			        {
			            System.out.println("\n No sites displayed \n");
			        }
			     }
			    return InAccuracysitelinkdata;

			}
			
			
			/*public  List<Map<String, String>> numberofentries() {
				
				waitForElement(tableresult,50);
				List<Map<String, String>> totalentries = new ArrayList<Map<String, String>>();
				//getting the text at the bottom of the table and split the string by space
				List<WebElement> col = driver.findElements(By.xpath("//table[@id='inaccuracy_results']/tbody"));
				System.out.println(col.size());
				rows = tableresultset.findElements(By.tagName("tr"));
				waitForElement(tableresultset,50);
				int count = rows.size();
				System.out.println("\n Total number of rows : "+count);
				return totalentries;
				}*/
			
			
			public void exporttableInAccuracy() throws FileNotFoundException, IOException, InterruptedException {
				
				WebElement inaccuracychkbox = driver.findElement(By.xpath("//input[@id='toggle-inaccuracies']"));
				inaccuracychkbox.click();
				waitForElement(tableresult, 40);
				waitForElement(tablebtn, 40);
				//scrollByElement(tablebtn);
				download(CurrentState.getBrowser(), tablebtn, 30);
				JSWaiter.waitUntilJQueryReady();
				convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableInAccuracy));
				
			}
			
		//printing visibility progress bar table data from downloaded excel sheet
		public List<Map<String, String>> getInAccuracyExporttableData() throws Exception {
				JSWaiter.waitJQueryAngular();
				exporttableInAccuracy();
				String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttableInAccuracy), "Sheet0").getExcelTable();
				List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
				int colSize = table.length;
				for (int col = 1; col < colSize; col++) {
					Map<String, String> kMap = new HashMap<String, String>();
					for (int i = 1; i < table.length; i++) {
						kMap.put("appvendor", table[0][col]);
						kMap.put(table[i][0], table[i][col]);
					}
					/*String row = Integer.toString(colSize);
					kMap.put("count", colsize);*/
					//System.out.println("\n The total number of rows \n" +colSize );
					exporttableData.add(kMap);
				}
				return exporttableData;
			}

		public void compareexporttableDatanInAccuracytable(List<Map<String, String>> getInAccuracyExporttableData,
				List<Map<String, String>> verifyInAccuracysitelinkdata) {
			for (Map<String, String> m1 : getInAccuracyExporttableData) {
				for (Map<String, String> m2 : verifyInAccuracysitelinkdata) {
					if (m1.get("Confirmed Info").equals(m2.get("Confirmed Info"))) {
						Assert.assertEquals(m1.get("Displayed Name"), m2.get("Displayed Name"), "verifying list for " +m2.get("Confirmed Info"));
						Assert.assertEquals(m1.get("Displayed Address"), m2.get("Displayed Address"), "verifying list for " +m2.get("Confirmed Info"));
						Assert.assertEquals(m1.get("Displayed Phone Number"), m2.get("Displayed Phone Number"), "verifying list for " +m2.get("Confirmed Info"));
						
						//Assert.assertEquals(m1.values(), m2.values(), "verify site vendors");
						//Assert.assertEquals(m1, m2, "verify for table report");
					}
				}
			}
			
		}
		
		//Clicking on progress bar and getting the data from the table for found
		public List<Map<String, String>> verifyIgnoredsitelinkdata(){
			try {
			JSWaiter.waitJQueryAngular();
			waitForElement(accuracysite, 40);
			waitForElement(siteshow,30);
			clickelement(siteshow);
			waitForElement(sitelink, 40);
			//getting into progressbar found listing
			scrollByElement(sitelink);
			//clicking on found listing progress bar
			clickelement(sitelink);
			System.out.println("\n progress bar clicked \n");
			waitForElement(tableresult,40);
			scrollByElement(tableresult);
			System.out.println("\n reading table data ********************* \n");
			waitForElement(tableresult,50);
			scrollByElement(tableresult);
			WebElement ignoredchkbox = driver.findElement(By.xpath("//input[@id='toggle_overridden']"));
			ignoredchkbox.click();
			waitForElement(tableresultset,50);
			waitForElement(tableresultset,50);
			String[][] table = readTable(tableresultset);
			int n = table.length;
			System.out.println("\n" +n);
			System.out.println(driver.findElement(By.xpath("//div[@id='inaccuracy_table_title']")).getText());
			}catch(Exception e) {
				e.printStackTrace();
			}
			//adding data into list
			List<Map<String, String>> InAccuracysitelinkdata = new ArrayList<Map<String, String>>();
			List<WebElement> elements = driver.findElements(By.xpath("tableresultset"));
			waitForElement(tableresult, 40);
		    java.util.Iterator<WebElement> program = elements.iterator();
		        //reading site data
		    while (program.hasNext()) {
		        String values = program.next().getText();
		        if(!values.equals("null"))
		        {
		            System.out.println("\n" +values);
		            
		        }
		        else
		        {
		            System.out.println("\n No sites displayed \n");
		        }
		     }
		    return InAccuracysitelinkdata;

		}
		
		
		/*public  List<Map<String, String>> numberofentries() {
			
			waitForElement(tableresult,50);
			List<Map<String, String>> totalentries = new ArrayList<Map<String, String>>();
			//getting the text at the bottom of the table and split the string by space
			List<WebElement> col = driver.findElements(By.xpath("//table[@id='inaccuracy_results']/tbody"));
			System.out.println(col.size());
			rows = tableresultset.findElements(By.tagName("tr"));
			waitForElement(tableresultset,50);
			int count = rows.size();
			System.out.println("\n Total number of rows : "+count);
			return totalentries;
			}*/
		
		
		public void exporttableIgnored() throws FileNotFoundException, IOException, InterruptedException {
			
			WebElement Ignoredchkbox = driver.findElement(By.xpath("//input[@id='toggle_overridden']"));
			Ignoredchkbox.click();
			waitForElement(tableresult, 40);
			waitForElement(tablebtn, 40);
			//scrollByElement(tablebtn);
			download(CurrentState.getBrowser(), tablebtn, 30);
			JSWaiter.waitUntilJQueryReady();
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+AccuracyExporttableIgnored));
			
		}
		
	//printing visibility progress bar table data from downloaded excel sheet
	public List<Map<String, String>> getIgnoredExporttableData() throws Exception {
			JSWaiter.waitJQueryAngular();
			exporttableIgnored();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+AccuracyExporttableIgnored), "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table.length;
			for (int col = 1; col < colSize; col++) {
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("appvendor", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				/*String row = Integer.toString(colSize);
				kMap.put("count", colsize);*/
				//System.out.println("\n The total number of rows \n" +colSize );
				exporttableData.add(kMap);
			}
			return exporttableData;
		}

	public void compareexporttableDatanIgnoredtable(List<Map<String, String>> getIgnoredExporttableData,
			List<Map<String, String>> verifyIgnoredsitelinkdata) {
		for (Map<String, String> m1 : getIgnoredExporttableData) {
			for (Map<String, String> m2 : verifyIgnoredsitelinkdata) {
				if (m1.get("Confirmed Info").equals(m2.get("Confirmed Info"))) {
					Assert.assertEquals(m1.get("Displayed Name"), m2.get("Displayed Name"), "verifying list for " +m2.get("Confirmed Info"));
					Assert.assertEquals(m1.get("Displayed Address"), m2.get("Displayed Address"), "verifying list for " +m2.get("Confirmed Info"));
					Assert.assertEquals(m1.get("Displayed Phone Number"), m2.get("Displayed Phone Number"), "verifying list for " +m2.get("Confirmed Info"));
					
					//Assert.assertEquals(m1.values(), m2.values(), "verify site vendors");
					//Assert.assertEquals(m1, m2, "verify for table report");
				}
			}
		}
		
	}
		
}