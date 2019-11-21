package com.dac.main.POM_TPSEE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import resources.ExcelHandler;
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
	
	@FindBy(xpath="//h1[contains(text(),'')]")
	private WebElement PageTitle;
	
	@FindBy(xpath="//h2[contains(text(),'')]")
	private WebElement GMBPageTitle;

	// History graph
	@FindBy(css = "rect.highcharts-plot-background")
	public WebElement hstryGrph;

	@FindBy(css = "div.highcharts-label.highcharts-tooltip-box.highcharts-color-none")
	private WebElement grphtooltip; 
	
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
	
	
	/**
	 * @param Country
	 * @param State
	 * @param City
	 * @param Location
	 *            for Global filtering reports
	 */
	public void applyGlobalFilter(String Group, String CountryCode, String State, String City, String Location) {
		JSWaiter.waitJQueryAngular();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='visibility-page']"))).getText();
		WebElement country,state,city,location,group;
		if(Group == null || Group.equalsIgnoreCase("none")) Group = "None";
		if (CountryCode == null || CountryCode.equalsIgnoreCase("null")) CountryCode = "All Countries";
		if (CountryCode == null || State == null || State.equalsIgnoreCase("null")) State = "All States";
		if (CountryCode == null || State == null || City == null || City.equalsIgnoreCase("null")) City = "All Cities"; 
		if (CountryCode == null || State == null || City == null || Location == null || Location.equalsIgnoreCase("null")) Location = "All Locations";
		try {
			waitForElement(filter_Panel, 25);
			//waitForElement(PageTitle, 25);
			//scrollByElement(GMBPageTitle);
			waitUntilLoad(driver);
			if(!Group.equals("None")) {			
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 20);
				group = fiterGroup.findElement(By.xpath("//div[@data-value='"+Group+"']"));
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
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dataTables_info")));
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

	//convert exported file from csv to xlsx
	public void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		String report_export = new formatConvert(Exportpath + filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + filename), Exportpath + export);
		
	}
	
	public void renamefile(String filename, String export) throws FileNotFoundException, IOException{
		FileHandler.renameTo(new File(Exportpath + filename), Exportpath + export);
	}
	
	

	/**
	 * @return History graph value read
	 */
	public List<Map<String, String>> verifyHistoryGraph() {
		
		//display tool tip
		waitForElement(hstryGrph, 30);
		scrollByElement(hstryGrph);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth() / 2) - 2, 0).click().perform();
		
		//read the tooltip variables
		tooltipvalue = grphtooltip.getText();
		System.out.println("\n Reading tooltipdata ********** \n");
		System.out.println("\n tooltipvalue is \n" +tooltipvalue);
		
		//read the tooltip variables
		rows = grphtooltip.findElements(By.tagName("span"));
		String[][] table = readTable(grphtooltip);
		
		//adding tooltipdata into List
		List<Map<String, String>> tooltipdata = new ArrayList<Map<String, String>>();
			for (int i = 0; i < table.length; i += 4) {
				Map<String, String> kMap = new HashMap<String, String>();
				kMap.put("Date", table[i][0]);
				kMap.put(table[i + 1][0], table[i + 1][1]);
				kMap.put(table[i + 2][0], table[i + 2][1]);
				tooltipdata.add(kMap);
			}
			//returning tooltipdata from list
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
	
	//Comparing values of overall visibility score and visibility export data
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
	
	//Comparing values of overall visibility score and visibility tooltip data
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
	//To get Vendors List displaying in the application
	public List<Map<String, String>> verifySitevendors() {
			JSWaiter.waitJQueryAngular();
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
		        	kMap.put("vendors", values);
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
	//getting data of accuracy score table
	public List<Map<String, String>> verifyaccuracySitetable() {
		waitForElement(accuracysite, 30);
		scrollByElement(accuracysite);
		System.out.println("\n Reading accuracy site table********** \n");
		List<Map<String, String>> accuracysiteTableData = new ArrayList<Map<String, String>>();
		Map<String, String> kMap = new HashMap<String, String>();
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"accuracyScoresTable\"]"));
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
	
	//Adding Vendors List to Array 
		public List<Map<String, String>> vendorsList() throws Exception {
								
					List<Map<String, String>> Vendorslist = new ArrayList<Map<String, String>>();
					// Creation of HashMap 
			    	  Map<String, String> kMap = new HashMap<String, String>();
			    	   ExcelHandler a = new ExcelHandler("./data/VendorList.xlsx","VendorList");
			    	   a.deleteEmptyRows();
			    	   String[][] vendors = a.getExcelTable();
			    		int colSize = vendors[0].length;
			    		String size = Integer.toString(colSize);
			    		System.out.println("Size is :" +size);
			    		for (int col = 1; col < colSize; col++) {
			    			for (int i = 1; i < vendors.length; i++) {
			    				kMap.put("Vendors", vendors[0][col]);
			    				kMap.put(vendors[i][0], vendors[i][col]);
			    			}
			      	  Vendorslist.add(kMap);	
			    	}
						return Vendorslist;
				}
		
		//Comparing Vendors List from application and Vendors List from Array
		public void comparevendorsListnverifySitevendors(List<Map<String, String>> verifySitevendors,
					List<Map<String, String>> vendorsList) {
				for (Map<String, String> m1 : verifySitevendors) {
					for (Map<String, String> m2 : vendorsList) {
						Assert.assertEquals(m1.size(), m2.size());
						Assert.assertEquals(m1.get("vendors"), m2.get("vendors"), "Data Matches");
					}
				}
			}
		
		//Get data using column name
		public int GetDataUsingColName(String PathofXL, String Col_Name) throws Exception {		  
			
		      FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
		      Workbook wb = new XSSFWorkbook(excelFilePath);
		      Sheet sh = wb.getSheetAt(0);     
		      Row row = sh.getRow(0);
		      int col = row.getLastCellNum();
		      int Last_row = sh.getLastRowNum();
		      //int cellNum = sh.getPhysicalNumberOfRows();
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
		
		
		
		//Export as CSV/XLSX of overall report
		public void exportVA(WebElement a, WebElement b, WebElement c, WebElement d, WebElement e, WebElement f) throws InterruptedException, FileNotFoundException, IOException{
			JSWaiter.waitJQueryAngular();
			//waitForElement(overall, 40);
			if(a.isDisplayed() && a.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(a));
				a.click();
				Thread.sleep(5000);
			}
			if(b.isDisplayed() && b.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(b));
				b.click();
				Thread.sleep(5000);
			}
			if(c.isDisplayed() && c.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(c));
				c.click();
				Thread.sleep(5000);
			}
			if(d.isDisplayed() && d.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(d));
				d.click();
				Thread.sleep(5000);
			}
			if(e.isDisplayed() && e.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(e));
				e.click();
				Thread.sleep(5000);
			}
			if(f.isDisplayed() && f.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(f));
				f.click();
				Thread.sleep(5000);
			}
		}
		
		
		public void exportVATable(WebElement a, WebElement b) throws InterruptedException, FileNotFoundException, IOException{
			JSWaiter.waitJQueryAngular();
			//waitForElement(overall, 40);
			if(a.isDisplayed() && a.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(a));
				a.click();
				Thread.sleep(5000);
			}
			if(b.isDisplayed() && b.isEnabled()) {
				wait.until(ExpectedConditions.visibilityOf(b));
				b.click();
				Thread.sleep(5000);
			}
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
		public String verifyinitialHistoryGraph() throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
			
			waitForElement(hstryGrph, 30);
			scrollByElement(hstryGrph);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth())/2 -980, 0).click().perform();
			
			//read the tooltip variables
			String initialtooltipvalue = grphtooltip.getText();
			System.out.println("\n Reading tooltipdata ********** \n");
			System.out.println("\n tooltipvalue is \n" +initialtooltipvalue);
			String initdate = initialtooltipvalue.substring(0, 10);
			System.out.println(initdate);
			return initdate;
		}
		
		
		//Get the Latest point and date of the graph
		public String verifyfinalHistorygraph() throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException{
			waitForElement(hstryGrph, 30);
			scrollByElement(hstryGrph);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			action.moveToElement(hstryGrph).moveByOffset((hstryGrph.getSize().getWidth())/2 - 2, 0).click().perform();
			
			//read the tooltip variables
			String finaltooltipvalue = grphtooltip.getText();
			System.out.println("\n Reading tooltipdata ********** \n");
			System.out.println("\n tooltipvalue is \n" +finaltooltipvalue);
			String finaldate = finaltooltipvalue.substring(0, 10);
			return finaldate;			
		}		
		
		//To Get Number of days between two dates
		
	//	^((([0]?[1-9]|1[0-2])(:|\.)
		
		public  int getNumberofDays() throws Exception {
	
			Date init = getInitialDate();
			Thread.sleep(5000);
			Date end =  getFinalDate();
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
			Assert.assertEquals(end, latest);
			long difference = Math.abs(init.getTime() - end.getTime());
	        long differenceDates = difference / (24 * 60 * 60 * 1000);
			int diff = (int)(long)differenceDates;
			System.out.println(diff);	
			System.out.println(diff);
			return diff;
		}
		
		
		//Get initial point of graph
		public Date getInitialDate() throws ParseException, bsh.ParseException, FileNotFoundException, IOException, InterruptedException {
			
			String var = null;			
			var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
			System.out.println(var);
			String InitialDate = verifyinitialHistoryGraph();
			SimpleDateFormat formats = new SimpleDateFormat(var);
			Date startDate = formats.parse(InitialDate);
			
			return startDate;
			
		}
		
		//Get final point of the graph
		public Date getFinalDate() throws bsh.ParseException, FileNotFoundException, ParseException, IOException, InterruptedException {
			
			String var = null;			
			var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
			System.out.println(var);
			String FinalDate = verifyfinalHistorygraph();
			SimpleDateFormat formats = new SimpleDateFormat(var);
			Date endtDate = formats.parse(FinalDate);
			
			return endtDate;
		}

		//To check whether element is clicked
		public boolean eleClicked(WebElement element) {
			if(element.isDisplayed()) {
				String classes = element.getAttribute("class");
				boolean isDisabled = classes.contains("highcharts-button highcharts-button-pressed");
				System.out.println(isDisabled);
				return !(isDisabled);			
			}
			return false;
		}
		
		//Click on highchart zoom functionality
		public TPSEE_abstractMethods clickHighchartCriteria(String durationFor) throws Exception {
			durationFor = durationFor.toLowerCase();
			scrollByElement(highChartZoom);
			int days;			
			if((!durationFor.equalsIgnoreCase("null"))) {				
				switch(durationFor) {				
				case "1m"  : 	clickelement(highChart_1M);
								if(eleClicked(highChart_1M)) {
								days = getNumberofDays();			
								if(days >= 28 && days<=31 ) {
									System.out.println("1 Month data is displayed");
								}else {
									System.out.println("Not 1 Month");
										}
									}else {
										System.out.println("Element not clicked");
									}
								break;
							
				case "3m"  : 	clickelement(highChart_3M);
								if(eleClicked(highChart_3M)) {
								days = getNumberofDays();
								if(days>=90 && days<=92) {
									System.out.println("3 Month data is displayed");
									}else {
										System.out.println("Not 3 Month");
										}
									}else {
										System.out.println("Element not clicked");
									}
								break;
							 
				case "6m"  : 	clickelement(highChart_6M);
								if(eleClicked(highChart_6M)) {
									days = getNumberofDays();
									if(days>=180 && days<=184) {
										System.out.println("6 Month data is displayed");
									}else {
										System.out.println("Not 6 Month");
										}
									}else {
										System.out.println("Element not clicked");
									}
								break;
							 
				case "ytd" : 	clickelement(highChart_YTD);
								if(eleClicked(highChart_YTD)) {
							 	days = getNumberofDays();
								}else {
									System.out.println("Element Not clicked");
									}
								break;
						     
				case "1y"  : 	clickelement(highChart_1y);
								if(eleClicked(highChart_1y)) {
									days = getNumberofDays();
									if(days>=364 && days<=366) {
										System.out.println("1 Year data is displayed");
									}else {
										System.out.println("Not 1 Year");
										
										}
									}else {
									System.out.println("Element not clicked");
									}
								break;
							 
				case "all" :
				default    : 	clickelement(highChart_All);
								if(eleClicked(highChart_All)) {
								days = getNumberofDays();
								}else {
									System.out.println("Element not clicked");
								}				
						}
				}
				return this;
		}	
		
		//To check whether given element is visible 
		public boolean IsVisible(WebElement Title) {
			boolean  Visibiltyofele = false;
			if(Title.isDisplayed()) {
				Visibiltyofele =  true;
			}else {
				System.out.println("Element Not Visible");
			}
			return Visibiltyofele;			
		}
		
		//Top button fuctionality
		public void TopButton() throws InterruptedException {			
			waitForElement(Top, 10);
			scrollByElement(Top);
			if(Top.isDisplayed() && Top.isEnabled()) {
				clickelement(Top);
				Thread.sleep(5000);
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
		}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		String regex = "^[- /.]";
		
		private void selectCalender_Date(WebElement calenderField, int day_d, String month_MMM, int year_YYYY) {

			clickelement(calenderField);
			//calenderField.click();
			int diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
			if(diff != 0) {
				while(diff < 0) {
					clickelement(prevMonth);
					//prevMonth.click();
					diff = year_YYYY - Integer.parseInt(currentYear_DatePicker.getText());
				}
				while(diff > 0) {
					clickelement(nextMonth);
					//nextMonth.click();
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
						//prevMonth.click();
						diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
					}
					while(diffMonth > 0) {
						clickelement(nextMonth);
						//nextMonth.click();
						diffMonth = monthCode(month_MMM) - monthCode(currentMonth_DatePicker.getText());
					}
				}
			}
			(driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']//td/a[text()="+day_d+"]"))).click();;
		}
		
		private int monthCode(String month_MMM) {

			int month = 0;
			Date date;
			try {
				
				date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse("February");
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(date);
				 month = cal.get(Calendar.MONTH);
				 //System.out.println(month);
				 //System.out.println(month == Calendar.FEBRUARY);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return month;
		}

		public TPSEE_abstractMethods selectCalender_FromDate(int day_d, String month_MMM, int year_YYYY) {
			if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
				selectCalender_Date(highChart_fromDate, day_d, month_MMM, year_YYYY);
			}
			return this;
		}
		
		public TPSEE_abstractMethods selectCalender_ToDate(int day_d, String month_MMM, int year_YYYY) {
			if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
				selectCalender_Date(highChart_toDate, day_d, month_MMM, year_YYYY);	
			}
			return this;
		}
		
		public String getCurrentFromDate() {
			return highChart_fromDate.getText();
		}
		
		public String getCurrentToDate() {
			return highChart_toDate.getText();
		}

		public void enterFromDate(String date) {
			JSWaiter.waitJQueryAngular();
			String[] dateSplit = date.split("/");
			String Lchars = Month.of(Integer.parseInt(dateSplit[0])).name().toLowerCase();
			String month =Month.of(Integer.parseInt(dateSplit[0])).name().charAt(0)+Lchars.substring(1, Lchars.length());
			selectCalender_FromDate(Integer.parseInt(dateSplit[0]), month, Integer.parseInt(dateSplit[2]));
		}
			
		public void enterToDate(String date) {
			JSWaiter.waitJQueryAngular();
			String[] dateSplit = date.split("/");
			String Lchars = Month.of(Integer.parseInt(dateSplit[0])).name().toLowerCase();
			String month =Month.of(Integer.parseInt(dateSplit[0])).name().charAt(0)+Lchars.substring(1, Lchars.length());
			selectCalender_ToDate(Integer.parseInt(dateSplit[0]), month, Integer.parseInt(dateSplit[2]));
		}
	
		
	}