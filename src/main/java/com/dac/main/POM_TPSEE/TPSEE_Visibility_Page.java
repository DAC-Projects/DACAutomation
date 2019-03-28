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


public class TPSEE_Visibility_Page extends TPSEE_abstractMethods {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	
	//Navigating to TPSEE Visibility page
	public TPSEE_Visibility_Page(WebDriver driver) {

		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	
	//Exporting into csv 
	@FindBy(xpath = "//*[@id='btnVisibilityExport']")
	private WebElement exportBtn;
	
	@FindBy(xpath = "//*[@id='csvData']")
	private WebElement csvexport;
	
	@FindBy(xpath = "//*[@id='exportdate']")
	private WebElement exportdate;
	
	@FindBy(id = "ui-datepicker-div")
	private WebElement dtpicker;
	
	@FindBy(css = "td.ui-datepicker-days-cell-over")
	private WebElement date;
	
	@FindBy(xpath = "//*[@id='btnLocationExport']")
	private WebElement export;
	
	//Exporting into pdf
	
	@FindBy(xpath = "//a[contains(text(),'Export as PDF')]")
	private WebElement pdfexport;
	
	@FindBy(xpath = "//a[@id='currentData']")
	private WebElement currentpdf;
	
	@FindBy(xpath= "//a[contains(text(),'Click here to download your report')]")
	private WebElement pdfclick;
	
	//locators to export pdf of some period of time
	@FindBy(xpath = "//a[@id='historyData']")
	private WebElement historypdf;
	
	@FindBy(xpath = "//input[@id='exportStartDate']")
	private WebElement hstrystart;
	
	@FindBy(xpath = "//input[@id='exportEndDate']")
	private WebElement hstryend;
	
	@FindBy(xpath = "//*[@id='btnHistoryExport']")
	private WebElement hstrybtn;
		
	//section of overall report
	@FindBy(xpath = "#divBars")
	private WebElement overall;
	
	@FindBy(xpath = "//*[@id='divBars']")
	private List<WebElement> comp;
	
	//tooltipvalue in the graph
	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 

	String xpathCompetitors = "//*[@id='divBars']";
	String NoofLocation = "//*[@id='divNumOfLocations']";
	String overallscore = "//*[@id='divOverallScoreValue']";

	//Site table section
	@FindBy(css = "div#barContainer.barContainer")
	private WebElement siteTable;
	
	@FindBy(css = "#divBars")
	private List<WebElement> Site;
	
	@FindBy(css = "div.progress-bar")
	private WebElement progressfound;
	
	@FindBy(css = "div.not-bar")
	private WebElement progressNotfound;
	
	
	//table section at the end of visibility report
	@FindBy(xpath = "//*[@id='visibility_table']")
	private WebElement progressdata;
	
	@FindBy(xpath = "//*[@id='visibility_results_wrapper']")
	private WebElement progresstable;
	
	@FindBy(xpath = "//*[@id='visibility_results']/tbody")
	private WebElement progresstablevalue;
	
	@FindBy(xpath = "//*[@id='ToolTables_visibility_results_0']")
	private WebElement exporttable;
	
	@FindBy(xpath = "//*[@id='visibility_table_title']")
	private WebElement title;
	
	@FindBy(xpath = "//*[@id='visibility_table']/div[2]")
	private WebElement titlehead;
	
	String Vendortitle = "//*[@id='visibility_table_title']";
	
	//Displaying no.of entries
	@FindBy(xpath = "//*[@id='visibility_results_wrapper']/div[4]/div[1]")
	private WebElement entries;
	
	@FindBy(xpath = "//*[@id='visibility_results_info']")
	private WebElement totalentries;
	
	List<WebElement> columns;
	List<WebElement> rows;
	
	@FindBy(xpath = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']")
	private List<WebElement> vendors;
	
	@FindBy(xpath = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']")
	private WebElement vendorslist;
	
	String vend = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']";
	
	/* Export visibility overview report below filter*/
	public void exportvisibilityrpt() throws InterruptedException, FileNotFoundException, IOException{
		//waitForElement(overall, 40);
		waitForElement(exportBtn, 40);
		scrollByElement(exportBtn);
		clickelement(exportBtn);
		waitForElement(csvexport, 40);
		scrollByElement(csvexport);
		clickelement(csvexport);
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
	    convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExport));
	     
	}
	
	
	/* Export visibility overview report below filter in pdf for current date*/
	public void exportvisibilityrptpdf() throws InterruptedException, FileNotFoundException, IOException{
		//waitForElement(overall, 40);
		waitForElement(exportBtn, 40);
		scrollByElement(exportBtn);
		clickelement(exportBtn);
		waitForElement(pdfexport, 40);
		scrollByElement(pdfexport);
		clickelement(pdfexport);
		waitForElement(currentpdf,40);
		scrollByElement(currentpdf);
		clickelement(currentpdf);
		waitForElement(pdfclick,60);
		scrollByElement(pdfclick);
		clickelement(pdfclick);
	    //download visibility report
	    download(CurrentState.getBrowser(), pdfclick, 50);
	    convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExportPdf));
	     
	}
	
	//To get overall score of visibility
	public List<Map<String, String>> getOverviewReport() {
		
		waitForElement(overall, 40);
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
		String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+VisibilityExport), "Sheet0").getExcelTable();
		List<Map<String, String>> exportData = new ArrayList<Map<String, String>>();
		int colSize = table[0].length;
		for (int col = 1; col < colSize; col++) {
			//adding data into map
			Map<String, String> kMap = new HashMap<String, String>();
			for (int i = 1; i < table.length; i++) {
				kMap.put("compName", table[0][col]);
				kMap.put(table[i][0], table[i][col]);
			}
		}
		//returning visibility report from excel
		return exportData;

	}
	
	//Compare Export file and graph values
	/*public void compareExportnTable(List<Map<String, String>> exportData, List<Map<String, String>> siteTableData) {
		
		for (Map<String, String> m1 : exportData) {
			for (Map<String, String> m2 : siteTableData) {
			}
		}
	}*/
		
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

	//Clicking on progress bar and getting the data from the table for found
	public List<Map<String, String>> verifyprogresstablefound(){
		
		waitForElement(siteTable, 40);
		waitForElement(progressfound, 40);
		//getting into progressbar found listing
		scrollByElement(progressfound);
		//clicking on found listing progress bar
		clickelement(progressfound);
		System.out.println("\n progress bar clicked \n");
		waitForElement(progressdata,40);
		scrollByElement(progressdata);
		System.out.println("\n reading progress bar data div ********************* \n");
		waitForElement(progresstable,50);
		scrollByElement(progresstable);
		System.out.println("\n reading progress bar data table ******************* \n");
		waitForElement(totalentries,50);
		waitForElement(progresstablevalue,50);
		String[][] table = readTable(progresstablevalue);
		int n = table.length;
		System.out.println("\n" +n);
		System.out.println(driver.findElement(By.xpath("//*[@id='visibility_table_title']")).getText());
		//adding data into list
		List<Map<String, String>> ProgressTableDatafound = new ArrayList<Map<String, String>>();
		List<WebElement> elements = driver.findElements(By.xpath("progresstable"));
		waitForElement(progresstable, 40);
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
	    return ProgressTableDatafound;

	}
		
	//Clicking on progress bar and getting the data from the table for Not Found list
	public List<Map<String, String>> verifyprogresstableNotfound(){
		
		waitForElement(siteTable, 40);
		waitForElement(progressNotfound, 40);
		//getting into progressbar found listing
		scrollByElement(progressNotfound);
		//clicking on found listing progress bar
		clickelement(progressNotfound);
		System.out.println("\n progress bar clicked \n");
		waitForElement(progressdata,40);
		scrollByElement(progressdata);
		//System.out.println("\n reading progress bar data div ********************* \n");
		waitForElement(progresstable,40);
		scrollByElement(progresstable);
		System.out.println("\n reading progress bar data table ******************* \n");
		rows = progresstablevalue.findElements(By.tagName("tr"));
		String[][] table = readTable(progresstable);
		int len = table[0].length;
		System.out.println("\n Length of table is :" +len);
		waitForElement(progresstable,30);
		System.out.println(driver.findElement(By.xpath("//*[@id=\"visibility_table_title\"]")).getText());
		//adding data into list
		List<Map<String, String>> ProgressTableDatafound = new ArrayList<Map<String, String>>();
		List<WebElement> elements = driver.findElements(By.xpath("progresstable"));
		waitForElement(progresstable,30);
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
	    return ProgressTableDatafound;
	}
		
	//exporting progress bar table data
	public void exporttable() throws FileNotFoundException, IOException, InterruptedException {
						
			waitForElement(progressdata, 40);
			waitForElement(exporttable, 40);
			scrollByElement(exporttable);
			download(CurrentState.getBrowser(), exporttable, 30);
			convertExports(getLastModifiedFile(Exportpath), (CurrentState.getBrowser()+VisibilityExporttable));
			
		}
		
	//printing visibility progress bar table data from downloaded excel sheet
	public List<Map<String, String>> getExporttableData() throws Exception {
		
			exporttable();
			String[][] table = new ExcelHandler(Exportpath + (CurrentState.getBrowser()+VisibilityExporttable), "Sheet0").getExcelTable();
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
		
	//To get total number  of entries at the bottom of the page
	public  List<Map<String, String>> numberofentries() {
		
		waitForElement(progresstable,50);
		List<Map<String, String>> totalentries = new ArrayList<Map<String, String>>();
		//getting the text at the bottom of the table and split the string by space
		List<WebElement> col = driver.findElements(By.xpath("//*[@id='visibility_results']/tbody"));
		System.out.println(col.size());
		rows = progresstablevalue.findElements(By.tagName("tr"));
		waitForElement(progresstablevalue,50);
		int count = rows.size();
		System.out.println("\n Total number of rows : "+count);
		return totalentries;
		}
	
	//comparing total entries in excel of visibilityreport table and test from bottom of the table
	public void compareexporttableDatannumberofentries(List<Map<String, String>> verifyprogresstablefound,
			List<Map<String, String>> numberofentries) {
		
		for (Map<String, String> m1 : verifyprogresstablefound) {
			for (Map<String, String> m2 : numberofentries) {
				if (m1.get("verifyprogresstablefound").equals(m2.get("numberofentries"))) {
					Assert.assertEquals(formatFloat(m1.get("row")), formatFloat(m2.get("entry")), 0.05f,
							"Verifying score for" + m1.get("row"));
				}
			}
		}
	}
		
	//To get Vendors List displaying in the application
	public List<Map<String, String>> verifySitevendors() {
			
			waitForElement(vendorslist, 40);
			scrollByElement(vendorslist);
			Map<String, String> kMap;
			List<Map<String, String>> Vendors = new ArrayList<Map<String, String>>();
			List<WebElement> elements = driver.findElements(By.xpath("//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    kMap = new HashMap<String, String>();
		        
		    //reading Vendors data
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
		        //adding into the map
		        Vendors.add(kMap);
		    }
			return Vendors;
			}
			
	//Adding Vendors List to Array of(Country = US) 
	public List<Map<String, String>> vendorsList() {
							
				List<Map<String, String>> Vendorslist = new ArrayList<Map<String, String>>();
				// Creation of HashMap 
		    	  Map<String, String> kMap = new HashMap<String, String>();
		       // Adding values to HashMap as ("keys", "values") 
			        kMap.put("1", "Bing"); 
			        kMap.put("2", "Citysearch"); 
			        kMap.put("3", "Facebook"); 
			        kMap.put("4", "Foursquare"); 
			        kMap.put("5", "Google"); 
			        kMap.put("6", "MerchantCircle"); 
			        kMap.put("7", "Superpages"); 
			        kMap.put("8", "Yahoo"); 
			        kMap.put("9", "TripAdvisor"); 
			        kMap.put("10", "Yelp"); 
			        kMap.put("11", "YP.com"); 
			        kMap.put("12", "Zomato"); 
			        kMap.put("13", "OpenTable"); 
		  
			        System.out.println("\n Testing .isEmpty() method"); 
		        
		      		        // Checks whether the HashMap is empty or not 
		        // Not empty so printing the values 
		        if (!kMap.isEmpty())
		        { 
		            System.out.println("\n HashMap Geeks is notempty"); 
		            
		            // Accessing the contents of HashMap through Keys 
		            System.out.println("\n" + kMap.get("1")); 
		            System.out.println("\n" + kMap.get("2")); 
		            System.out.println("\n" + kMap.get("3")); 
		            System.out.println("\n" + kMap.get("4"));
		            System.out.println("\n" + kMap.get("5"));
		            System.out.println("\n" + kMap.get("6"));
		            System.out.println("\n" + kMap.get("7"));
		            System.out.println("\n" + kMap.get("8"));
		            System.out.println("\n" + kMap.get("9"));
		            System.out.println("\n" + kMap.get("10"));
		            System.out.println("\n" + kMap.get("11"));
		            System.out.println("\n" + kMap.get("12"));
		            System.out.println("\n" + kMap.get("13"));
		  
		            // size() method prints the size of HashMap. 
		            System.out.println("\n Size Of HashMap : " + kMap.size()); 
		          // Vendorslist.add(kMap);
		        } 			
			return Vendorslist;	
			}

	//Comparing Vendors List from application and Vendors List from Array
	public void comparevendorsListnverifySitevendors(List<Map<String, String>> verifySitevendors,
				List<Map<String, String>> vendorsList) {
			for (Map<String, String> m1 : verifySitevendors) {
				for (Map<String, String> m2 : vendorsList) {
					if (m1.get("verifySitevendors").equals(m2.get("vendorsList"))) {
						
						//Assert.assertArrayEquals(verifySitevendors(), vendorsList());
						//Assert.assertEquals(m1.get(verifySitevendors), m2.get(vendorsList), "verifying list for " +m2.get(verifySitevendors));
						Assert.assertEquals(m1.values(), m2.values(), "verify site vendors");
						//assertEquals(verifySitevendors, vendorsList);
						
					}
				}
			}
			
		}

	//Getting the table data of the progress bar
	public List<Map<String, String>> tabledata() {
			
			waitForElement(progresstablevalue, 20);
			scrollByElement(progresstablevalue);
			Map<String, String> kMap;
			List<Map<String, String>> foundtable = new ArrayList<Map<String, String>>();
			List<WebElement> elements = driver.findElements(By.xpath("progresstablevalue"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    kMap = new HashMap<String, String>();
		        
		    //reading found table data
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
		        //adding into the map
		        foundtable.add(kMap);
		    }
			return foundtable;
		}

	//comparing data from excell sheet with table data of progress bar found
	public void compareexporttableDatantable(List<Map<String, String>> getExporttableData,
				List<Map<String, String>> tabledata) {
			for (Map<String, String> m1 : getExporttableData) {
				for (Map<String, String> m2 : tabledata) {
					if (m1.get("Name").equals(m2.get("Name"))) {
						Assert.assertEquals(m1.get("City"), m2.get("City"), "verifying list for " +m2.get("Name"));
						//Assert.assertEquals(m1.values(), m2.values(), "verify site vendors");
						//Assert.assertEquals(m1, m2, "verify for table report");
					}
				}
			}
			
		}

	//comparing number of entries for not found locations
	public void compareexporttableDatannumberofentriesNotFound(List<Map<String, String>> verifyprogresstableNotfound,
			List<Map<String, String>> numberofentries) {
		
		for (Map<String, String> m1 : verifyprogresstableNotfound) {
			for (Map<String, String> m2 : numberofentries) {
				if (m1.get("verifyprogresstablefound").equals(m2.get("numberofentries"))) {
					Assert.assertEquals(formatFloat(m1.get("row")), formatFloat(m2.get("entry")), 0.05f,
							"Verifying score for" + m1.get("row"));
				}
			}
		}
		
	}

}	