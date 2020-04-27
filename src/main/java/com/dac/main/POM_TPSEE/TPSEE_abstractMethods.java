package com.dac.main.POM_TPSEE;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import resources.CurrentState;
import resources.FileHandler;
import resources.JSWaiter;
import resources.formatConvert;

public abstract class TPSEE_abstractMethods extends BasePage implements TPSEERepository {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	String tooltipvalue;
	List<WebElement> rows;

	public TPSEE_abstractMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	// Global Filter locators 
	
	/*------------------------ KPI Dashboard ------------------------*/
	
	@FindBy(xpath = "//*[@id='location']/div[2]/span")
	private static WebElement Locations;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/span")
	private static  WebElement Visibilityscore;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/div[2]/span")
	private static WebElement VisibilityLoc;
	
	@FindBy(xpath = "//*[@id='accuracy']/div[2]/span")
	private static WebElement Accuracyscore;
	
	@FindBy(xpath = "//*[@id='accuracy']/div[2]/div[2]/span")
	private static WebElement AccuracyLoc;
	
	@FindBy(xpath = "//*[@id='contentAnalysis']/div[2]/span")
	private static WebElement Contentscore;
	
	@FindBy(xpath = "//*[@id='contentAnalysis']/div[2]/div[2]/span")
	private static WebElement ContentLoc;
	
	@FindBy(xpath = "//*[@id='googleRanking']/div[2]/span")
	private static WebElement GRScore;
	
	@FindBy(xpath = "//*[@id='googleRanking']/div[2]/div[2]/span")
	private static WebElement GRLoc;
	
	/*------------------------ KPI Dashboard ------------------------*/
	
	/*------------------------ Filter Criteria ------------------------*/

	@FindBy(xpath="//*[@class='menu transition visible']")
	private WebElement filterDropDown;
	
	@FindBy(id="myGroups")
	private WebElement fiterGroup;
	
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

	@FindBy(css = "div#filter-options")
	public WebElement filter_Panel;
	
	/*------------------------ Filter Criteria ------------------------*/
	
	@FindBy(xpath="//*[@id='page-content']//h1")
    private WebElement PageTitle;
   
    @FindBy(xpath="//p[@class='lead']")
    private WebElement PageTitletext;

	// History graph
	@FindBy(css = "rect.highcharts-plot-background")
	public WebElement hstryGrph;

	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 
	
	String grph = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	
	String grphGR = ".highcharts-label.highcharts-tooltip-box.highcharts-color-none";
	
	//section of overall report
	@FindBy(xpath = "//*[@id='divOverallScoreHeader']//*[@id='divOverallScoreHeader']/div[1]//*[@id='divOverallScoreValue']")
	private WebElement overall;

	//site tableesTable
	@FindBy(xpath = "//div[@class='barContainer']") 
	public WebElement siteTable;
	
	@FindBy(xpath = "//*[@id='accuracyScoresTable']")
	public WebElement accuracysite;

	@FindBy(xpath = "//a[contains(text(),'Next')]")
	private WebElement pagination;
	
	@FindBy(xpath = "//div[@class='container']/div[@class='row']/div[@class='col-lg-2 bar-chart-column']")
	private WebElement vendorslist;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-group']/*[name()='g'])[1]")
	private WebElement highChartZoom;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[1]")
	private WebElement highChart_1M;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[2]")
	private WebElement highChart_3M;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[3]")
	private WebElement highChart_6M;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[4]")
	private WebElement highChart_YTD;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[5]")
	private WebElement highChart_1y;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-range-selector-buttons']/*[name()='g'])[6]")
	private WebElement highChart_All;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-input-group']/*[name()='g'])[2]/*[name()='text']")
	private WebElement highChart_fromDate;
	
	@FindBy(xpath = "(//*[name()='g' and @class='highcharts-input-group']/*[name()='g'])[4]/*[name()='text']")
	private WebElement highChart_toDate;
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;
	
	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;
	
	@FindBy(xpath = "//*[@class='back-to-top btn btn-primary']")
	private WebElement Top;
	
	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;
	
	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;
	
	@FindBy(xpath= "//*[@id = 'exportStartDate']")
	private WebElement pdffromdate;
	
	@FindBy(xpath = "//*[@id = 'exportEndDate']")
	private WebElement pdftoDate;
	
	@FindBy(xpath = "(//*[@class='highcharts-label highcharts-range-input'])[1]")
	private WebElement fromDate;
	
	@FindBy(xpath = "(//*[@class='highcharts-label highcharts-range-input'])[2]")
	private WebElement toDate;
	
	 /**
     * Get data using column name and sum for Bing and GMB Page
     * @param PathofXL
     * @param Col_Name
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unused", "deprecation" })
	public double GetDRSDataUsingColName(String PathofXL, String Col_Name) throws Exception {         
          FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
          Workbook wb = new XSSFWorkbook(excelFilePath);
          Sheet sh = wb.getSheetAt(0);    
          Row row = sh.getRow(0);
          int col = row.getLastCellNum();
          int Last_row = sh.getLastRowNum();
          int col_num = 0;
          System.out.println(""+col);      
          List<Double> score = new ArrayList<Double>();
          for (int i = 0; i <row.getLastCellNum(); i++) {             
                if ((row.getCell(i).toString()).equals(Col_Name)) {                
                    col_num = i;                   
                    System.out.println(""+col_num);   
                }
          }
                       String s = null;
                    double y = 0;
                    double sum = 0;   
                    double average = 0;
                    double finalaverage=0.00;
                    for(int j =1;j<=Last_row; j++) {
                        row = sh.getRow(j);
                        Cell cell = row.getCell(col_num);
                        if (cell != null) {
                        	if(!(cell.getCellType() == Cell.CELL_TYPE_STRING)) {
                                String cellValue1 = Double.toString(cell.getNumericCellValue());
                                System.out.println("Cell VAlue is :" +cellValue1);
                                if(!cellValue1.contains("N/A")) {
                                /*s = cellValue1.replace("N/A", "0");
                                y = Double.parseDouble(s);
                                System.out.println("\n " +s);
                                sum = sum+y;*/
                                	
                                	double cellValue = Double.parseDouble(cellValue1);
                                	System.out.println(cellValue);
                                	score.add(cellValue);
                                }
                        	}else {
                        		System.out.println("String Value");
                        	}
                            
                            System.out.println("ArrayList contains :" +score);
                            
                            
                            } else {
                                System.out.println("Smt wrong");
                            }
                        wb.close();
                    }
                    int sizeOfList = score.size();
                    System.out.println("Size of List is :" +sizeOfList);
                    for(int i = 0; i <= sizeOfList - 1; i++) {
                        sum = sum + score.get(i);
                    }
                    System.out.println("Total sum is :"+sum);
                    average = sum/sizeOfList;
                    BigDecimal bd = BigDecimal.valueOf(average);
                    bd = bd.setScale(1, RoundingMode.HALF_UP);
                    finalaverage = bd.doubleValue();
                    System.out.println("Final Average Score:" +finalaverage);
                    return finalaverage;
        }
    
	/**
	 * @param Group
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 *            for Global filtering reports
	 */
	public void applyGlobalFilter(String Group, String CountryCode, String State, String City, String Location) {
		JSWaiter.waitJQueryAngular();
		WebElement country,state,city,location,group;
		if(Group == null || Group.equalsIgnoreCase("none")) Group = "None";
		if (CountryCode == null || CountryCode.equalsIgnoreCase("null")) CountryCode = "All Countries";
		if (CountryCode == null || State == null || State.equalsIgnoreCase("null")) State = "All States";
		if (CountryCode == null || State == null || City == null || City.equalsIgnoreCase("null")) City = "All Cities"; 
		if (CountryCode == null || State == null || City == null || Location == null || Location.equalsIgnoreCase("null")) Location = "All Locations";
		try {
			waitForElement(filter_Panel, 25);
			waitUntilLoad(driver);
			if(!Group.equals("None")) {			
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 20);
				group = fiterGroup.findElement(By.xpath("//*[@id = 'myGroups']//div[contains(@class,'item') and contains(text(),'"+Group+"')]"));
				waitForElement(group, 10);
				clickelement(group);
				waitUntilLoad(driver);
			}
			if(!CountryCode.equals("All Countries")) {
				clickelement(FilterCountry);
				waitForElement(filterDropDown, 20);
				country = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[1]//div[@data-value='"+CountryCode.toUpperCase()+"']"));
				waitForElement(country, 10);
				Thread.sleep(1000);
				clickelement(country);
				waitUntilLoad(driver);
			}
			if(!State.equals("All States")) {			
				clickelement(FilterState);
				waitForElement(filterDropDown, 20);
				state = FilterState.findElement(By.xpath("//div[@data-value='"+State+"']"));
				waitForElement(state, 10);
				Thread.sleep(1000);
				clickelement(state);
				waitUntilLoad(driver);
			}
			if(!City.equals("All Cities")) {
				clickelement(FilterCity);
				waitForElement(filterDropDown, 20);
				city = FilterCity.findElement(By.xpath("//div[@data-value='"+City+"']"));
				waitForElement(city, 10);
				Thread.sleep(1000);
				clickelement(city);
				waitUntilLoad(driver);
			}
			if(!Location.equals("All Locations")) {			
				clickelement(Filterlocation);
				waitForElement(filterDropDown, 20);
				location = Filterlocation.findElement(By.xpath("//div[text()='"+Location+"']"));
				waitForElement(location, 10);
				Thread.sleep(1000);
				clickelement(location);
				waitUntilLoad(driver);
			}
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("searched Country/State/City/Location may not be there or may be a typo error please check it");
		}
		waitUntilLoad(driver);
	}
	
	/**
	 * This method used to click on the Apply Filter button		*/
	public void clickApplyFilterBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if(Apply_filter.isDisplayed()) {
			clickelement(Apply_filter);
			Thread.sleep(3000);
		}
	}

	/**
	 * @return must implement overview report for all pages
	 */
	public abstract List<Map<String, String>> getOverviewReport();
	
	/**
	 * convert exported file from csv to xlsx
	 * @param filename
	 * @param export
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + report_export), Exportpath + export);
		
	}
	
	/**
	 * Renaming the file downloaded
	 * @param filename
	 * @param export
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void renamefile(String filename, String export) throws FileNotFoundException, IOException{
		FileHandler.renameTo(new File(Exportpath + filename), Exportpath + export);
	}	

	/**
	 * @return History graph value read
	 */
	public List<Map<String, String>> verifyHistoryGraph() {
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
		tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);
		rows = grphtooltip.findElements(By.tagName("span"));
		String[][] table = readTable(grphtooltip);
		List<Map<String, String>> tooltipdata = new ArrayList<Map<String, String>>();
			for (int i = 0; i < table.length; i += 4) {
				Map<String, String> kMap = new HashMap<String, String>();
				kMap.put("Date", table[i][0]);
				kMap.put(table[i + 1][0], table[i + 1][1]);
				kMap.put(table[i + 2][0], table[i + 2][1]);
				tooltipdata.add(kMap);
			}
			return tooltipdata;
	}			

	/**
	 * @param s
	 * @return Finds no within a string and returns as float
	 */
	public float formatFloat(String s) {
		Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
		Matcher matcher = regex.matcher(s);
		if (matcher.find())
			return Float.parseFloat(matcher.group(1));
		else
			return 0f;
	}
	
	/**
	 * @param s
	 * @to read site table values
	 */
	public List<Map<String, String>> verifySitetable() {
		waitForElement(siteTable, 30);
		scrollByElement(siteTable);
		System.out.println("\n Reading site table********** \n");
		List<Map<String, String>> siteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='barContainer']//div//div"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    while (program.hasNext()) {
		    	String values = program.next().getText();		        
		        if(!values.equals("null"))
		        {
		            System.out.println("" +values);
		        }
		        else
		        {
		            System.out.println("\n No sites displayed \n");
		        }
		        	siteTableData.add(kMap);
		    }
		return siteTableData;
	}
	
	/**
	 * Comparing values of overall visibility score and visibility export data
	 * @param exportData
	 * @param ovrwRprtData
	 */
	public void compareExprttoOvervw(List<Map<String, String>> exportData, List<Map<String, String>> ovrwRprtData) {
		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : exportData) {
				if (m1.get("NoofLocation").equals(m2.get("compName"))) {
					Assert.assertEquals(formatFloat(m1.get("score")), formatFloat(m2.get("overallscore")), 0.05f,
							"Verifying score for" + m1.get("compName"));
				}
			}
		}
	}
	
	/**
	 * Comparing values of overall visibility score and visibility tooltip data
	 * @param tooltipdata
	 * @param ovrwRprtData
	 */
	public void compareReportnGraph(List<Map<String, String>> tooltipdata, List<Map<String, String>> ovrwRprtData) {
		for (Map<String, String> m1 : ovrwRprtData) {
			for (Map<String, String> m2 : tooltipdata) {
				if (m1.get("compName").equals(m2.get("overall"))) {
					Assert.assertEquals(formatFloat(m1.get("overallscore")), formatFloat(m2.get("Overallscore")), 0.05f,
							"Verifying score for" + m1.get("NoofLocation"));
				}
			}
		}
	}
	/**
	 * To get Vendors List displaying in the application
	 * @return
	 */
	public ArrayList<String> verifySitevendors() {
			JSWaiter.waitJQueryAngular();
			waitForElement(vendorslist, 40);
			scrollByElement(vendorslist);
			ArrayList<String> Vendors = new ArrayList<String>();
			List<WebElement> elements = driver.findElements(By.xpath("//*[@class='col-lg-2 bar-chart-column']"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    while (program.hasNext()) {
		        String values = program.next().getText();
		        if(!values.equals("null")) {
		        	Vendors.add(values);
		        	System.out.println("\n" +values);
		        }
		    else
		        {
		            System.out.println("\n No sites displayed \n");
		        }
		       
		    }
			return Vendors;
	}
	
	/**
	 * getting data of accuracy score table			
	 * @return
	 */
	public List<Map<String, String>> verifyaccuracySitetable() {
		waitForElement(accuracysite, 30);
		scrollByElement(accuracysite);
		System.out.println("\n Reading accuracy site table********** \n");
		List<Map<String, String>> accuracysiteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='accuracyScoresTable']"));
		    java.util.Iterator<WebElement> program = elements.iterator();
		    while (program.hasNext()) {
		        String values = program.next().getText();
		        if(!values.equals("null"))
		        {
		            System.out.println("" +values);
		            kMap.put("sites", values);
		        }
		        else
		        {
		            System.out.println("No sites displayed");
		        }
		        accuracysiteTableData.add(kMap);
		    }
		return accuracysiteTableData;
	}
		
		/**
		 * Get data using column name and sum for Bing and GMB Page
		 * @param PathofXL
		 * @param Col_Name
		 * @return
		 * @throws Exception
		 */
		@SuppressWarnings("deprecation")
		public int GetDataUsingColName(String PathofXL, String Col_Name) throws Exception {		  
			  FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		      Workbook wb = new XSSFWorkbook(excelFilePath);
		      Sheet sh = wb.getSheetAt(0);     
		      Row row = sh.getRow(0);
		      int col = row.getLastCellNum();
		      int Last_row = sh.getLastRowNum();
		      int col_num = 0;
		      System.out.println(""+col);	      
		      for (int i = 0; i <row.getLastCellNum(); i++) {	    	  
		    	    if ((row.getCell(i).toString()).equals(Col_Name)) {	        	 
		    	    	col_num = i;	    	    	
		    	    	System.out.println(""+col_num);	
		    	    }
		      }
		    	       	String s = null;
		            	int cellValue = 0;
		            	int y = 0;
		            	int sum = 0;	
		            	for(int j =1;j<Last_row; j++) {
		            		row = sh.getRow(j);
		            		Cell cell = row.getCell(col_num);
		            		if (cell != null) {
		            			if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
		            				String cellValue1 = cell.getStringCellValue().toString();
		            			    if(cellValue1.contains("-")) {
		            				s = cellValue1.replace("-", "0");
		            				y = Integer.parseInt(s);
		            				System.out.println("\n " +s);
		            				sum = sum+y;
		            			    }
		            			}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		            				double cellValue2 = cell.getNumericCellValue();
		            				cellValue = (int)cellValue2;
		            				System.out.println("\n " +cellValue);
		            				sum = sum+cellValue;
		            				}
		            			System.out.println(""+sum);
		            			} else {
		            				System.out.println("Smt wrong");
		            			}
		            		System.out.println(":" +sum);
		            		wb.close();
		            	}
						return sum;
		    }
		
		/**
		 * To get site details
		 * @param PathofXL
		 * @param Col_Name
		 * @return
		 * @throws Exception
		 */ 
		@SuppressWarnings("deprecation")
		public ArrayList<String> GetSiteDataUsingColName(String PathofXL, String Col_Name) throws Exception {		  
			  FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		      Workbook wb = new XSSFWorkbook(excelFilePath);
		      Sheet sh = wb.getSheetAt(0);     
		      Row row = sh.getRow(0);
		      int col = row.getLastCellNum();
		      int Last_row = sh.getLastRowNum();
		      int col_num = 0;
		      System.out.println(""+col);	
		      ArrayList<String> sites  = new ArrayList<String>();
		      for (int i = 0; i <row.getLastCellNum(); i++) {	    	  
		    	    if ((row.getCell(i).toString()).equals(Col_Name)) {	        	 
		    	    	col_num = i;	    	    	
		    	    	System.out.println(""+col_num);	
		    	    }
		      }
		    	       	String cellValue = null ;
		            	for(int j =1;j<=Last_row; j++) {
		            		row = sh.getRow(j);
		            		Cell cell = row.getCell(col_num);
		            		if (cell != null) {
		            			if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
		            				 cellValue = cell.getStringCellValue().toString();
		            				 sites.add(cellValue);
		            			    } 
		            		System.out.println(":" +sites);
		            		wb.close();
		            	}
					}
		            	return sites; 
		    }	
		
		/**
		 * Export as CSV/XLSX of overall report
		 * @param ExportDropdown
		 * @param ExportType[CSV/XLSX]
		 * @param DatePicker/Calendar
		 * @param Date to Select
		 * @param Export button
		 * @throws InterruptedException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void exportVA(WebElement ExportDropdown, WebElement ExportType, WebElement DatePicker, WebElement SelectDate, WebElement ExportBtn) throws InterruptedException, FileNotFoundException, IOException{
			JSWaiter.waitJQueryAngular();
			waitForElement(ExportDropdown, 10);
			clickelement(ExportDropdown);
			waitForElement(ExportType, 10);
			clickelement(ExportType);
			waitForElement(DatePicker, 10);
			clickelement(DatePicker);	
			waitForElement(SelectDate, 10);
			clickelement(SelectDate);
			waitForElement(ExportBtn, 10);
			clickelement(ExportBtn);
		}
		
		/**
		 * Export as CSV/XLSX of overall report
		 * @param ExportType
		 * @param ExportBtn
		 * @throws InterruptedException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void exportVATable(WebElement ExportType, WebElement ExportBtn) throws InterruptedException, FileNotFoundException, IOException{
			JSWaiter.waitJQueryAngular();
			if(ExportType.isDisplayed() && ExportType.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(ExportType));
				ExportType.click();
				Thread.sleep(5000);
			}
			if(ExportBtn.isDisplayed() && ExportBtn.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(ExportBtn));
				ExportBtn.click();
				Thread.sleep(5000);
			}
		}
		
		/**
		 * Export visibility overview report below filter in pdf for current date
		 * @param ExportDropdown
		 * @param ExportType
		 * @param SelectPDF
		 * @param LinkClick
		 * @throws InterruptedException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void exportasPDFCurrentDate(WebElement ExportDropdown, WebElement ExportType, WebElement SelectPDF, WebElement LinkClick ) throws InterruptedException, FileNotFoundException, IOException{
		    JSWaiter.waitJQueryAngular();
			if(ExportDropdown.isEnabled() & ExportDropdown.isDisplayed()) {
				wait.until(ExpectedConditions.visibilityOf(ExportDropdown));
				action.moveToElement(ExportDropdown).click().perform();
				Thread.sleep(5000);
			}
			if(ExportType.isEnabled() & ExportType.isDisplayed()) {
				wait.until(ExpectedConditions.visibilityOf(ExportType));
				action.moveToElement(ExportType).moveToElement(SelectPDF).click().perform();
				Thread.sleep(10000);
			}
			if(LinkClick.isEnabled() & LinkClick.isDisplayed()) {
				wait.until(ExpectedConditions.visibilityOf(LinkClick));
				action.moveToElement(LinkClick).click().perform();
				Thread.sleep(5000);
			}
		}
		
		/**
		 * Click on PDF Export Link
		 * @param ExportBtn
		 * @param LinkClick
		 * @throws InterruptedException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void exportasPDFHistory(/*WebElement ExportDropdown, WebElement ExportType, WebElement SelectPDF,*/ WebElement ExportBtn, WebElement LinkClick) throws InterruptedException, FileNotFoundException, IOException{
		    JSWaiter.waitJQueryAngular();
		    if(ExportBtn.isDisplayed() & ExportBtn.isEnabled()) {
		    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    	action.moveToElement(ExportBtn).click().perform();
		    	Thread.sleep(5000);
		    }
			if(LinkClick.isDisplayed() & LinkClick.isEnabled()) {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				action.moveToElement(LinkClick).click().perform();;
			}			
		}
		
		/**
		 * Click on history button Pdf
		 * @param ExportDropdown
		 * @param ExportType
		 * @param SelectPDF
		 * @throws InterruptedException
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public void exporthistrybtn(WebElement ExportDropdown, WebElement ExportType, WebElement SelectPDF) throws InterruptedException, FileNotFoundException, IOException {
			Thread.sleep(5000);
			
			 if(ExportDropdown.isDisplayed() & ExportDropdown.isEnabled()) {
			    	wait.until(ExpectedConditions.visibilityOf(ExportDropdown));
					action.moveToElement(ExportDropdown).click().perform();
					Thread.sleep(5000);
			    }
			    if(ExportType.isDisplayed() & ExportType.isEnabled()) {
					wait.until(ExpectedConditions.visibilityOf(ExportType));
					action.moveToElement(ExportType).moveToElement(SelectPDF).click().perform();
					Thread.sleep(5000);
					
			    }		
			
		}
		/**
		 * To get the file extension and verify the same
		 * @throws InterruptedException
		 */
		public void verifyfileextension() throws InterruptedException {
			String filename = getLastModifiedFile("./downloads");
			System.out.println(filename);
			String extension = (filename.substring(filename.lastIndexOf(".") + 1));
			System.out.println(extension);
			Assert.assertEquals("pdf", extension);
			
		}
		
		
		/**
		 * Get the initial point and date of the graph
		 * @return initial/starting History graph value 
		 * @throws ParseException 
		 * @throws bsh.ParseException 
		 * @throws InterruptedException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 */
		public Date verifyinitialHistoryGraph(int start, int end, String elemnt) throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
			
			String var = null;			
			var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
			System.out.println(var);
			
			waitForElement(hstryGrph, 30);
			scrollByElement(hstryGrph);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth())/2 -start, end).click().perform();
			String initialtooltipvalue = driver.findElement(By.cssSelector(elemnt)).getText();
			System.out.println("\n Reading tooltipdata ********** \n");
			System.out.println("\n tooltipvalue is \n" +initialtooltipvalue);
			String initdate = initialtooltipvalue.substring(0, 10);
			System.out.println(initdate);
			
			SimpleDateFormat formats = new SimpleDateFormat(var);
			Date startDate = formats.parse(initdate);			
			return startDate;
		}
		
		
		/**
		 * Get the Latest point and date of the graph
		 * @return final/ending History graph value
		 * @throws ParseException
		 * @throws bsh.ParseException
		 * @throws FileNotFoundException
		 * @throws IOException
		 * @throws InterruptedException
		 */
		public Date verifyfinalHistorygraph(int start, int end, String elemnt) throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException{
			
			String var = null;			
			var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
			System.out.println(var);
			
			waitForElement(hstryGrph, 30);
			scrollByElement(hstryGrph);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth())/2 - start, end).click().perform();
			String finaltooltipvalue = driver.findElement(By.cssSelector(elemnt)).getText();;
			System.out.println("\n Reading tooltipdata ********** \n");
			System.out.println("\n tooltipvalue is \n" +finaltooltipvalue);
			String finaldate = finaltooltipvalue.substring(0, 10);
			System.out.println(finaldate);
			
			SimpleDateFormat formats = new SimpleDateFormat(var);
			Date endtDate = formats.parse(finaldate);			
			return endtDate;
		}	
		
		/**
		 * To get difference between two dates
		 * @return
		 * @throws Exception
		 */
		public  int getNumberofDays(int start, int end, String elemnt, int start1, int end1) throws Exception {
			Date init = verifyinitialHistoryGraph(start,end,elemnt);
			Thread.sleep(5000);
			Date enddate =  verifyfinalHistorygraph(start1,end1, elemnt);
			Thread.sleep(5000);
			String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
			SimpleDateFormat formats = new SimpleDateFormat(var);
			System.out.println(var);
			String today = ((JavascriptExecutor)driver).executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())").toString();
			System.out.println(today);
			Date todaydate = formats.parse(today);
			System.out.println(todaydate);
			String Yesterday = ((JavascriptExecutor)driver).executeScript("return moment().add({days: -1}).format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())").toString();
			Date latest = formats.parse(Yesterday);
			System.out.println(latest);
			Thread.sleep(5000);			
			Assert.assertEquals(enddate, latest);
			long difference = Math.abs(init.getTime() - enddate.getTime());
	        long differenceDates = difference / (24 * 60 * 60 * 1000);
			int diff = (int)(long)differenceDates;
			System.out.println(diff);	
			System.out.println(diff);
			return diff;
		}
		
		
		/**
		 * To check whether element is clicked
		 * @param element
		 * @return
		 */
		public boolean eleClicked(WebElement element) {
			if(element.isDisplayed()) {
				String classes = element.getAttribute("class");
				boolean isDisabled = classes.contains("highcharts-button highcharts-button-pressed");
				System.out.println(isDisabled);
				return !(isDisabled);			
			}
			return false;
		}
		
		/**
		 * Click on highchart zoom functionality
		 * @param durationFor
		 * @return
		 * @throws Exception
		 */
	
		public TPSEE_abstractMethods clickHighchartCriteria(String durationFor) throws Exception {
			
			durationFor = durationFor.toLowerCase();
			WebElement x = driver.findElement(By.xpath(" //*[name()='rect' and @class='highcharts-background']"));
			scrollByElement(x);
			//scrollByElement(highChartZoom);
			int days;			
			if((!durationFor.equalsIgnoreCase("null"))) {				
				switch(durationFor) {				
				case "1m"  : 	try{
									
									clickelement(highChart_1M);
									if(eleClicked(highChart_1M)) {
										scrollByElement(x);
										days = getNumberofDays();			
										if(days >= 28 && days<=31 ) {
											assert(true);
											System.out.println("1 Month data is displayed");
										}else {
											System.out.println("Not 1 Month");
											}
										}else {
											System.out.println("Element not clicked");
										}
								}catch(Exception e) {
									e.printStackTrace();
								}
								break;
							
				case "3m"  : 	try{
									clickelement(highChart_3M);
									if(eleClicked(highChart_3M)) {
										scrollByElement(x);
										days = getNumberofDays();
										if(days>=90 && days<=92) {
											assert(true);
											System.out.println("3 Month data is displayed");
										}else {
											System.out.println("Not 3 Month");
											}
										}else {
											System.out.println("Element not clicked");
										}
									}catch(Exception e) {
										e.printStackTrace();
									}
									break;
							 
				case "6m"  : 	try{
									clickelement(highChart_6M);
									if(eleClicked(highChart_6M)) {
										scrollByElement(x);
										days = getNumberofDays();
										if(days>=180 && days<=184) {
											assert(true);
											System.out.println("6 Month data is displayed");
										}else {
											System.out.println("Not 6 Month");
											}
										}else {
											System.out.println("Element not clicked");
										}
									}catch(Exception e) {
										e.printStackTrace();
										}
								break;
							 
				case "ytd" : 	try{
									clickelement(highChart_YTD);
									 if(eleClicked(highChart_YTD)) {
	                                        scrollByElement(x);
	                                        days = getNumberofDays();
	                                        System.out.println("Days Abi"+days);
	                                        Date UI_date = getCurrentfromDate();
	                                        System.out.println("UI"+UI_date);
	                                        Date VE_Date=getCurrent();
	                                        System.out.println("VE"+VE_Date);	                                        
	                                        Assert.assertEquals(UI_date, VE_Date);
	                                        System.out.println("1 year data is displayed");
	                                    }else {
	                                        System.out.println("Element Not clicked");
	                                        }
	                                }catch(Exception e) {
	                                    e.printStackTrace();
	                                }
	                                break;
						     
				case "1y"  : 	try{
									clickelement(highChart_1y);
									if(eleClicked(highChart_1y)) {
										scrollByElement(x);
										days = getNumberofDays();
										if(days>=364 && days<=366) {
											assert(true);
											System.out.println("1 Year data is displayed");
										}else {
											System.out.println("Not 1 Year");
											}
										}else {
											System.out.println("Element not clicked");
										}
									}catch(Exception e) {
										e.printStackTrace();
									}
								break;
								
				case "all" :
				default    : 	try{
									clickelement(highChart_All);
									if(eleClicked(highChart_All)) {
										scrollByElement(x);
										days = getNumberofDays();
										assert(true);
									}else {
										System.out.println("Element not clicked");
										}
									}catch(Exception e) {
										e.printStackTrace();
									}
								}
							}
		
					return this;
			}	
		
		
		/**
		 * To get date of 01/01/current year
		 * @return
		 * @throws ParseException
		 */
		public Date getCurrent() throws ParseException {
            String ad1="01/01/";
            String year = Integer.toString(Year.now().getValue());
            String ad2=ad1.concat(year);
            System.out.println(ad2);
            String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
            SimpleDateFormat formats = new SimpleDateFormat(var);
            Date finalcurrentdate = formats.parse(ad2);
            return finalcurrentdate;
        }
		
		/**
		 * To get difference between two dates
		 * @return
		 * @throws Exception
		 */
		public  int getNumberofDays() throws Exception {
			int diff = 0;
			Date init = getCurrentfromDate();
			Thread.sleep(5000);
			Date enddate =  getCurrenttoDate();
			Thread.sleep(5000);
			long difference = Math.abs(init.getTime() - enddate.getTime());
	        long differenceDates = difference / (24 * 60 * 60 * 1000);
			diff = (int)(long)differenceDates;		
			System.out.println(diff);	
			return diff;
		}	
		
		/**
		 * To check whether given element is visible 
		 * @param Title
		 * @return
		 */
		public boolean IsVisible(WebElement Title) {
			boolean  Visibiltyofele = false;
			if(Title.isDisplayed()) {
				Visibiltyofele =  true;
			}else {
				System.out.println("Element Not Visible");
			}
			return Visibiltyofele;			
		}
		
		/**
		 * Top button fuctionality
		 */
		public void TopButton()  {			
			try {
				
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,-250)");
				waitForElement(Top, 10);
			scrollByElement(Top);
			if(Top.isDisplayed() && Top.isEnabled()) {
				clickelement(Top);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				boolean x = IsVisible(Top);
				System.out.println(x);
				if(x = true) {
					System.out.println("Button Clicked");
				}else {
					System.out.println("Button not clicked");
				}				
			}else {
				System.out.println("Title is not displayed");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

		
		/**
		 * To get Number of Locations from Dashboard 
		 * @return
		 */
		public int getLocations() {
			wait.until(ExpectedConditions.visibilityOf(Locations));
			waitUntilLoad(driver);
			String s  = Locations.getText();
			System.out.println("Locations are :" +s);
			int loc = Integer.parseInt(s);
			System.out.println(loc);
			return loc;			
		}
		
		/**
		 * To get Visibility Score from Dashboard
		 * @return
		 * @throws InterruptedException 
		 */
		public  double getVisibilityscore() throws InterruptedException {	
			Thread.sleep(5000);
			String sc = Visibilityscore.getText();
			System.out.println(sc);
			String s = sc.replace("%", "");
			s.trim();
			double visibilityscore = Double.parseDouble(s);
			System.out.println(visibilityscore);
			BigDecimal bd = BigDecimal.valueOf(visibilityscore);
            bd = bd.setScale(1, RoundingMode.HALF_UP);
            double finalvisibilityscore = bd.doubleValue();
			return finalvisibilityscore;		
			
		}
		
		/**
		 * To get Number of Locations of Visibility from Dashboard
		 * @return
		 * @throws InterruptedException 
		 */
		public  int getVisibilityLoc() throws InterruptedException {	
			Thread.sleep(5000);
			int visibilityloc = Integer.parseInt(VisibilityLoc.getText());
			System.out.println(visibilityloc);
			return visibilityloc;		
			
		}
		
		/**
		 * To get Accuracy Score from Dashboard
		 * @return
		 * @throws InterruptedException 
		 */
		public static double getAccuracyscore() throws InterruptedException {	
			Thread.sleep(5000);
			String sc = Accuracyscore.getText();
			System.out.println(sc);
			String s = sc.replace("%", "");
			s.trim();
			double accuracyscore = Double.parseDouble(s);
			System.out.println(accuracyscore);
			BigDecimal bd = BigDecimal.valueOf(accuracyscore);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
            double finalaccuracyscore = bd.doubleValue();
			return finalaccuracyscore;
		}
		
		/**
		 * To get Number of Locations of Accuracy from Dashboard 
		 * @return
		 * @throws InterruptedException 
		 */
		public static int getAccuracyLoc() throws InterruptedException {	
			Thread.sleep(5000);
			int accuracyloc = Integer.parseInt(AccuracyLoc.getText());
			System.out.println(accuracyloc);
			return accuracyloc;
		}
		
		/**
		 * To get Content Analysis Score from Dashboard
		 * @return
		 * @throws InterruptedException 
		 */
		public double getContentscore() throws InterruptedException {	
			Thread.sleep(5000);
			String sc = Contentscore.getText();
			System.out.println(sc);
			String s = sc.replace("%", "");
			s.trim();
			double cascore = Double.parseDouble(s);
			System.out.println(cascore);
			BigDecimal bd = BigDecimal.valueOf(cascore);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
            double finalcascore = bd.doubleValue();
			return finalcascore;	
		}
		
		/**
		 * To get Number of Locations of Content Analysis
		 * @return
		 * @throws InterruptedException 
		 */
		public int getContentLoc() throws InterruptedException {	
			Thread.sleep(5000);
			int contentloc = Integer.parseInt(ContentLoc.getText());
			System.out.println(contentloc);
			return contentloc;
		}
		
		/**
		 * To get Google Ranking Score from Dashboard
		 * @return
		 * @throws InterruptedException 
		 */
		public static double getGRScore() throws InterruptedException {	
			Thread.sleep(5000);
			double GRscore = Double.parseDouble(GRScore.getText());
			System.out.println(GRscore);
			BigDecimal bd = BigDecimal.valueOf(GRscore);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
            double finalGRscore = bd.doubleValue();
			return finalGRscore;
		}
		
		/**
		 * To get Number of locations of Google ranking from Dashboard
		 * @return
		 * @throws InterruptedException 
		 */
		public static int getGRLoc() throws InterruptedException {		
			Thread.sleep(5000);
			int GRloc = Integer.parseInt(GRLoc.getText());
			System.out.println(GRloc);
			return GRloc;
		}	
		
		/**
		 * Overview and Dashboard Comparision
		 * @param layout
		 * @param loc
		 * @return
		 */
		public int overviewLocation(WebElement layout, WebElement loc ) {	
			waitForElement(layout, 10);
			scrollByElement(layout);
			waitForElement(loc, 10);
			scrollByElement(loc);
			int location = Integer.parseInt(loc.getText());					
			return location;			
		}
		
		public int numofloc(WebElement locations) {
			waitForElement(locations, 10);
			scrollByElement(locations);
			String s = locations.getText();
			String x = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
			int location = Integer.parseInt(x);
			return location;
		}
		
		/**
		 * To get overview score from the reports (VA Reports)
		 * @param layout
		 * @param score
		 * @return
		 */
		public double overviewscore(WebElement layout, WebElement score) {
			waitForElement(layout, 10);
			scrollByElement(layout);
			waitForElement(score, 10);
			scrollByElement(score);
			String sc = score.getText();
			String s = sc.replace("%", "");
			double scores = Double.parseDouble(s);
			BigDecimal bd = BigDecimal.valueOf(scores);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
            double finaloverviewscore = bd.doubleValue();
			return finaloverviewscore;

		}		
		
		/**
		 * To get overall score for Content Analysis Report
		 * @param score
		 * @return
		 */
		public double overviewcascore(WebElement score) {
			waitForElement(score, 10);
			scrollByElement(score);
			String sc = score.getAttribute("data-percent");
			String s = sc.replace("%", "");
			double scores = Double.parseDouble(s);
			BigDecimal bd = BigDecimal.valueOf(scores);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
            double finaloverviewcascore = bd.doubleValue();
			return finaloverviewcascore;
		}		
		
		/**
		 * To get current selected from date from UI Calendar
		 * @return
		 * @throws ParseException
		 */
		public Date getCurrentfromDate() throws ParseException {
		String currentfromDate = fromDate.getText();
		String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		System.out.println(var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date finalcurrentdate = formats.parse(currentfromDate);
		return finalcurrentdate;
	}
	
		/**
		 * To get current selected to date from UI Calendar
		 * @return
		 * @throws ParseException
		 */
		public Date getCurrenttoDate() throws ParseException {
		String currenttoDate = toDate.getText();
		String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		System.out.println(var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date finaltodate = formats.parse(currenttoDate);
		return finaltodate;
		}
	
		
		/**
		 * To pass date to the UI Calendar
		 * @param calenderField
		 * @param day_d
		 * @param month_MMM
		 * @param year_YYYY
		 */
		private void selectCalender_Date(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		//clickelement(calenderField);
		driver.findElement(By.xpath(calenderField)).click();
		int diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
		if(diff != 0) {
			while(diff < 0) {
				clickelement(prevMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
			while(diff > 0) {
				clickelement(nextMonth);
				diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			}
		}
		if(diff == 0) {
			if(!(month_MMM.equals(currentMonth_DatePicker.getText()))) {
				int actualMonthCode = monthCode(currentMonth_DatePicker.getText());
				int expMonthCode = monthCode(month_MMM);
				int diffMonth = expMonthCode - actualMonthCode;
				while(diffMonth < 0) {
					clickelement(prevMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
				while(diffMonth > 0) {
					clickelement(nextMonth);
					diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
				}
			}
		}
		(driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']//td/a[text()="+day_d+"]"))).click();
	}
	
		/**
		 * To get month code
		 * @param month_MMM
		 * @return
		 */
		private int monthCode(String month_MMM) {

		int month = 0;
		Date date;
		try {
			 date = new SimpleDateFormat("MMM").parse(month_MMM);
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(date);
			 month = cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return month;
	}
	
		/**
		 * To set from date in a calendar
		 * @param calenderField
		 * @param day_d
		 * @param month_MMM
		 * @param year_YYYY
		 */
		public  void selectCalender_FromDate(String calenderField,int day_d, String month_MMM, int year_YYYY) {
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);
		}
		
	}
	
		/**
		 * To set to date in calendar
		 * @param calenderField
		 * @param day_d
		 * @param month_MMM
		 * @param year_YYYY
		 */
		public void  selectCalender_ToDate(String calenderField,int day_d, String month_MMM, int year_YYYY) {
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);	
		}
		
	}
	
		/**
		 * Method returns list of found vendors
		 * @return
		 */
		public ArrayList<String> verifyfoundSitevendors(){
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			ArrayList<String> foundVendors = new ArrayList<String>();
			List<WebElement> vendorrow = driver.findElements(By.xpath("(//*[@id='barContainer']//div[@class='row'])"));
			vendorrow.size();			
			for(int i = 1 ; i<=vendorrow.size(); i++){
				WebElement foundlisting = driver.findElement(By.xpath("(*//div[@class='col-lg-1 bar-chart-column found-number'])["+ i +"]"));
				String numoflisting = foundlisting.getText();
				WebElement vendorname =driver.findElement(By.xpath("(//*[@class='col-lg-2 bar-chart-column'])[" + i + "]"));
				if(!numoflisting.equals("0") ) {
					scrollByElement(vendorname);
					String vendor = vendorname.getText();
					System.out.println(vendor);
					foundVendors.add(vendor);
					System.out.println(foundVendors);
					}
					else{
						System.out.println("No found Listing");
					}
				}
					return foundVendors;
			}
		
		public void deletefile() {
			File path = new File("./downloads");
		    File[] files = path.listFiles();
		    for (File file : files) {
		        System.out.println("Deleted filename :"+ file.getName());
		        file.delete();
		    }
		}
		
		public void VerifyTitleText(String Tit, String titText) {
	           
            waitForElement(PageTitle, 10);
            String Title = PageTitle.getText();
            System.out.println("Page Title is : "+Title);
            String TitleText = PageTitletext.getText();
            System.out.println("The title text  is :" + TitleText);
            Assert.assertEquals(Tit, Title);
            Assert.assertEquals(titText,TitleText );     
        }
		
		public void Download(WebElement ExportBtn, WebElement ExportType) throws FileNotFoundException, InterruptedException, IOException {
			try {
				exportVATable(ExportBtn, ExportType );
		        Robot robot = new Robot();
		        robot.setAutoDelay(5000);
		        robot.keyPress(KeyEvent.VK_ALT);
		        robot.keyPress(KeyEvent.VK_S);
		        robot.keyRelease(KeyEvent.VK_ALT);
		        robot.keyRelease(KeyEvent.VK_S);
		        if ("firefox".equalsIgnoreCase(CurrentState.getBrowser()))
		          robot.keyPress(KeyEvent.VK_ENTER);

		      } catch (AWTException e) {
		        e.printStackTrace();
		      }
		}
		
		public int NumOfentries(WebElement entry) {
			waitForElement(entry, 10);
			if(entry.isDisplayed()) {
			String entiresText = entry.getText();
			System.out.println("The total entries in a table is :" + entiresText);
			String result = entiresText.substring(entiresText.indexOf("(") + 3, entiresText.indexOf(")") - 7).trim();
			int finalvalue = Integer.parseInt(result);
			System.out.println("The number of entries is : " +finalvalue);
			return finalvalue;
			}else {
				System.out.println("No Data available");
				return 0;
			}		
		}
}