package com.dac.main.POM_TPSEE;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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


public class TPSEE_Visibility_Page extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	
	//Navigating to TPSEE Visibility page
	public TPSEE_Visibility_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	
	//Exporting into csv 
	@FindBy(xpath = "//*[@id=\"btnVisibilityExport\"]")
	private WebElement exportBtn;
	
	@FindBy(xpath = "//*[@id=\"csvData\"]")
	private WebElement csvexport;
	
	@FindBy(xpath = "//*[@id=\"exportdate\"]")
	private WebElement exportdate;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement dtpicker;
	
	@FindBy(css = "td.ui-datepicker-days-cell-over")
	private WebElement date;
	
	@FindBy(xpath = "//*[@id=\"btnLocationExport\"]")
	private WebElement export;

	//section of overall report
	@FindBy(xpath = "#divBars")
	private WebElement overall;
	
	@FindBy(xpath = "//*[@id=\"divBars\"]")
	private List<WebElement> comp;
	
	//tooltipvalue in the graph
	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 

	String xpathCompetitors = "//*[@id=\"divBars\"]";
	String NoofLocation = "//*[@id=\"divNumOfLocations\"]";
	String overallscore = "//*[@id=\"divOverallScoreValue\"]";

	//Site table section
	@FindBy(css = "div#barContainer.barContainer")
	private WebElement siteTable;
	
	@FindBy(css = "#divBars")
	private List<WebElement> Site;
	
	@FindBy(css = "div.progress-bar")
	private WebElement progress;
	
	
	//table section at the end of visibility report
	@FindBy(xpath = "//*[@id=\"visibility_table\"]")
	private WebElement progressdata;
	
	@FindBy(xpath = "//*[@id=\"visibility_results_wrapper\"]/div[3]")
	private WebElement progresstable;
	
	@FindBy(xpath = "//*[@id=\"visibility_results\"]/tbody")
	private WebElement progresstablevalue;
	
	@FindBy(xpath = "//*[@id=\"ToolTables_visibility_results_0\"]")
	private WebElement exporttable;
	
	//Displaying no.of entries
	@FindBy(xpath = "//*[@id=\"visibility_results_wrapper\"]/div[4]/div[1]")
	private WebElement entries;
	
	String totalentries = "//*[@id=\"visibility_results_info\"]";
	
	List<WebElement> columns;
	List<WebElement> rows;
	
	
	
	/*
	 * Export visibility overview report
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	public void exportvisibilityrpt() throws InterruptedException, FileNotFoundException, IOException{
		waitForElement(overall, 10);
		waitForElement(exportBtn, 10);
		scrollByElement(exportBtn);
		clickelement(exportBtn);
		waitForElement(csvexport, 10);
		scrollByElement(csvexport);
		clickelement(csvexport);
		waitForElement(exportdate,10);
		scrollByElement(exportdate);
		clickelement(exportdate);
		waitForElement(dtpicker,10);
		scrollByElement(dtpicker);
		waitForElement(date, 10);
		scrollByElement(date);
		clickelement(date);
		DateFormat dateFormat2 = new SimpleDateFormat("dd"); 
	     Date date2 = new Date();

	     String Today = dateFormat2.format(date2); 

	     //find the calendar
	     WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));  
	     List<WebElement> columns=dateWidget.findElements(By.tagName("td"));  

	     //comparing the text of cell with today's date and clicking it.
	     for (WebElement cell : columns)
	     {
	        if (cell.getText().equals(Today))
	        {
	           cell.click();
	           break;
	        }
	     } 
	     
	     download(CurrentState.getBrowser(), export, 20);
	     convertExports(getLastModifiedFile(Exportpath), VisibilityExport);
	     
	}
	
	//To get overall score of visibility
	public List<Map<String, String>> getOverviewReport() {
		
		waitForElement(overall, 10);
		//scrollByElement(overall);
		System.out.println("\n Reading overall ********** \n");
		Map<String, String> kMap;
		
		//adding data into List
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
		//retrieving data and displaying of tooltip visibility score
			return ovrwRprtData;
	}
	
	
	//To get site score of visibility
	public List<Map<String, String>> sitereport() {
		
		waitForElement(overall, 0);
		//scrollByElement(overall);
		System.out.println("\n Reading overall ********** \n");
		Map<String, String> kMap;
		
		//adding data into List
		List<Map<String, String>> siteData = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= comp.size(); i++) {
			WebElement s = driver.findElement(By.xpath(xpathCompetitors + "[" + i + "]"));
			kMap = new HashMap<String, String>();
			kMap.put("NoofLocation", s.findElement(By.xpath(NoofLocation)).getText());
			kMap.put("score", s.findElement(By.xpath(overallscore)).getText());
			System.out.format("%10s%10s", s.findElement(By.xpath(NoofLocation)).getText(),
					s.findElement(By.xpath(overallscore)).getText());
			siteData.add(kMap);
		}
		//retrieving data and displaying of site visibility score
			return siteData;
	}

	public void verify_pageloadCompletely(int timeout) {
		if ( waitForElement(grphtooltip, timeout)
				&& waitForElement(hstryGrph, timeout)
				&& waitForElement(hstryGrph, timeout) & waitForElement(filter_Panel, timeout))
			assertTrue(true, "All sections filter, overview report, site table and graph is loaded");
		else
			assertTrue(false, "Page not loaded completely");
	}

	//printing visibility report data from downloaded excel sheet 
	public List<Map<String, String>> getExportData() throws Exception {
		
		exportvisibilityrpt();
		String[][] table = new ExcelHandler(Exportpath + VisibilityExport, "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			Map<String, String> kMap = new HashMap<String, String>();

			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
			
		}
		return exportData;

	}
	
	//Compare Export file and graph values
	public void compareExportnTable(List<Map<String, String>> exportData, List<Map<String, String>> siteTableData) {
	
		for (Map<String, String> m1 : exportData) {
			for (Map<String, String> m2 : siteTableData) {
				if (m1.get("compName").equals(m2.get("compName"))) {
					Assert.assertEquals(m1.size() - 1, m2.size());
					m1.forEach((k,v)->{
						System.out.println("for name " + k + " score from export " + v
						+ "and score from site table" + m2.get(k));
						if (!k.equalsIgnoreCase("Overall") && !k.contains("Google")
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
		
	//comparing values of graph and overall report
	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {

		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				Assert.assertEquals(formatFloat(m1.get("score")), 
									formatFloat(m2.get("overallscore")), 0.05f,
									"Verifying score for" + m1.get("NoofLocations"));
				}
			}
		}

	
	//Clicking on progress bar and getting the data from the table
	public List<Map<String, String>> verifyprogresstable(){
		
		waitForElement(siteTable, 10);
		waitForElement(progress, 10);
		scrollByElement(progress);
		clickelement(progress);
		System.out.println("\n progress bar clicked \n");
		waitForElement(progressdata,10);
		scrollByElement(progressdata);
		System.out.println("\n reading progress bar data div ********************* \n");
		waitForElement(progresstable,10);
		scrollByElement(progresstable);
		System.out.println("\n reading progress bar data table ******************* \n");
		rows = progresstablevalue.findElements(By.tagName("tr"));
		String[][] table = readTable(progresstable);
		
		//adding data into list
		List<Map<String, String>> ProgressTableData = new ArrayList<Map<String, String>>();
		List<WebElement> elements = driver.findElements(By.xpath("progresstable"));
	    java.util.Iterator<WebElement> program = elements.iterator();
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
	        String s = driver.findElement(By.xpath("//*[@id=\'visibility_results_info\']")).getText().substring(26, 28);
			System.out.println("\n The value is: \n" +s );
	    }
	    return ProgressTableData;

	}
		//exporting progress bar table data
		public void exporttable() throws FileNotFoundException, IOException, InterruptedException {
						
			waitForElement(progressdata, 10);
			waitForElement(exporttable, 30);
			scrollByElement(exporttable);
			download(CurrentState.getBrowser(), exporttable, 20);
			convertExports(getLastModifiedFile(Exportpath), VisibilityExporttable);
			
		}
		
		//printing visibility progress bar table data from downloaded excel sheet
		public List<Map<String, String>> getExporttableData() throws Exception {
		
			exporttable();
			String[][] table = new ExcelHandler(Exportpath + VisibilityExporttable, "Sheet0").getExcelTable();
			List<Map<String, String>> exporttableData = new ArrayList<Map<String, String>>();
			int colSize = table[0].length;
			for (int col = 1; col < colSize; col++) {
				Map<String, String> kMap = new HashMap<String, String>();
				for (int i = 1; i < table.length; i++) {
					kMap.put("google", table[0][col]);
					kMap.put(table[i][0], table[i][col]);
				}
				int row = colSize;
				System.out.println("\n The total number of rows \n" +row );
				
			}
			return exporttableData;
		}
		
	public  int numberofentries() {
		
		/*String s = driver.findElement(By.xpath("//*[@id=\'visibility_results_info\']")).getText().substring(26, 28);
		System.out.println("\n The Number of entries are: \n" +s );*/
		String[] string = driver.findElement(By.xpath("//*[@id=\'visibility_results_info\']")).getText().split(" ");
		String part1 = string[0]; 
		String part2 = string[1]; 
		String part3 = string[2];
		String part4 = string[3];
		String part5 = string[4];
		String part6 = string[5];
		String part7 = string[6];
		String part8 = string[7];
		//System.out.println("\n The number of entries are : \n"  +part7);
		int total = Integer.parseInt(part7);
		return total;
		}

}
