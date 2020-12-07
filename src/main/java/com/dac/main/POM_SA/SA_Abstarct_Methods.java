package com.dac.main.POM_SA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.FileHandler;
import resources.JSWaiter;
import resources.formatConvert;

public abstract class SA_Abstarct_Methods extends BasePage implements SA_Repository{

	/*------------------------ Filter Criteria ------------------------*/
	
	@FindBy(xpath = "//h3[@class = 'page-title']")
	private WebElement PageTitle;
	
	@FindBy(xpath = "//p[@class = 'lead']")
	private WebElement TitleText;

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

	@FindBy(xpath = "//button[@id='btnApply']")
	private WebElement Apply_filter;

	@FindBy(css = "div#filter-options")
	public WebElement filter_Panel;
	
	@FindBy(id="dateFrom")
	private WebElement fromDate;
	
	@FindBy(id="dateTo")
	private WebElement toDate;
	
	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;
	
	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;
	
	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;
	
	@FindBy(xpath = "//*[@id='kpi_reviews_count_box']/span")
	private WebElement avgreview;
	
	@FindBy(xpath = "//*[@id='nps_score_kpi_value']")
	private WebElement DashRNPS;
	
	@FindBy(xpath="//*[@id='Review']//*[@class='review-content']")
	private List<WebElement> Reviews;
	
	@FindBy(xpath = "//*[@class='row reviewEnhancement']")
	private WebElement ReviewSection;
	
	/*------------------------ Filter Criteria ------------------------*/
	
	@FindBy(xpath = "//*[@class='back-to-top btn btn-primary']")
	private WebElement Top;
	
	/*-------------------------Highcharts-------------------------------*/
	
	@FindBy(xpath = "//div[@id='highchart-review']")
	private WebElement highchartSec;
	
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
	
	@FindBy(xpath = "//span[@id='fromDate']")
	private WebElement From_Date_Review;
	
	@FindBy(xpath = "//span[@id='toDate']")
	private WebElement To_Date_Review;
	
	/*-------------------------Highcharts-------------------------------*/
	
/*-------------------------Pagination-----------------------*/
	
	@FindBy(xpath = "(//*[@class='pagination']//a)")
	private List<WebElement> pagination;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[1]")
	private WebElement paginationPrev;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
	private WebElement paginationLast;
	
	@FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
	private WebElement paginationNext;
	
	/*-------------------------Pagination-----------------------*/
	
	SoftAssert soft = new SoftAssert();
	
	public SA_Abstarct_Methods(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
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
				group = fiterGroup.findElement(By.xpath("//div[@data-value='"+Group+"']"));
				waitForElement(group, 10);
				clickelement(group);
				waitUntilLoad(driver);
			}
			if(!CountryCode.equals("All Countries")) {
				clickelement(FilterCountry);
				waitForElement(filterDropDown, 20);
				country = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[1]//div[contains(@class,'item') and contains(text(),'"+ CountryCode.toUpperCase() +"')]"));
				waitForElement(country, 10);
				Thread.sleep(1000);
				clickelement(country);
				waitUntilLoad(driver);
			}
			if(!State.equals("All States")) {			
				clickelement(FilterState);
				waitForElement(filterDropDown, 20);
				state = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[2]//div[contains(@class,'item') and contains(text(),'"+State+"')]"));
				waitForElement(state, 10);
				Thread.sleep(1000);
				clickelement(state);
				waitUntilLoad(driver);
			}
			if(!City.equals("All Cities")) {
				clickelement(FilterCity);
				waitForElement(filterDropDown, 20);
				city = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[3]//div[contains(@class,'item') and contains(text(),'"+City+"')]"));
				waitForElement(city, 10);
				Thread.sleep(1000);
				clickelement(city);
				waitUntilLoad(driver);
			}
			if(!Location.equals("All Locations")) {			
				clickelement(Filterlocation);
				waitForElement(filterDropDown, 20);
				location = driver.findElement(By.xpath("(//*[contains(@class,'myList')])[4]//div[contains(@class,'item') and contains(text(),'"+Location+"')]"));
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
	 * To get current selected from date from UI Calendar
	 * @return
	 * @throws ParseException
	 */
	public Date getCurrentfromDate() throws ParseException {
	String currentfromDate = ((JavascriptExecutor)driver).executeScript("return document.getElementsByClassName('datepicker hasDatepicker')[0].value").toString();
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
	String currenttoDate = ((JavascriptExecutor)driver).executeScript("return document.getElementsByClassName('datepicker hasDatepicker')[1].value").toString();
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
	scrollByElement(driver.findElement(By.xpath(calenderField)));
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
	 * To get score in double value
	 * @param ele
	 * @return
	 */
	public double getscore(WebElement ele) {		
		waitForElement(ele, 10);
		scrollByElement(ele);
		String text = ele.getText();
		double score = Double.parseDouble(text);
		return score;
	}
	
	/**
	 * To get number of reviewes 
	 * @param ele
	 * @return
	 */
	public int getnumber(WebElement ele) {
		waitForElement(ele, 10);
		scrollByElement(ele);
		String text = ele.getText();
		int number = Integer.parseInt(text);
		return number;
	}
	
	
	/**
	 * To get the RNPS score
	 * @param ele
	 * @return
	 */
	public String getRnpsscore(WebElement ele) {
		waitForElement(ele, 10);
		scrollByElement(ele);
		String text = ele.getText();
		if(text.contains("%")) {
			text = text.replace("%", "");
		}else {
			System.out.println("No given string found");
		}
		return text;
	}
	
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
	
	public void renamefile(String filename, String export) throws FileNotFoundException, IOException {
		FileHandler.renameTo(new File(Exportpath + filename), Exportpath + export);
	}
	
	
	/**
	 * Get the date from highcharts section 
	 * @param ele
	 * @return
	 * @throws ParseException
	 */
	public Date GetDatehighcharts(WebElement ele) throws ParseException{
		String currenttoDate = ele.getText();
		String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		System.out.println(var);
		SimpleDateFormat formats = new SimpleDateFormat(var);
		Date finaltodate = formats.parse(currenttoDate);
		return finaltodate;
		
	}
	
	/**
	 * To get the average review from Dashboard
	 * @return
	 */
	public double getavgreview() {
		waitForElement(avgreview, 10);
		scrollByElement(avgreview);
		String review = avgreview.getText();
		String rev = review.substring(0, 3);
		double averagereview = Double.parseDouble(rev);
		System.out.println(averagereview);
		return averagereview;	
		}
	
	/**
	 * To get RNPNS Score from Dashboard
	 * @return
	 */
	public String getDashRnpsscore() {
		waitForElement(DashRNPS, 10);
		scrollByElement(DashRNPS);
		String text = DashRNPS.getText();
		if(text.contains("%")) {
			text = text.replace("%", "");
		}else {
			System.out.println("No given string found");
		}
		return text;
	}
	
	/**
	 * To get difference between two dates
	 * @return
	 * @throws Exception
	 */
	public  int getNumberofDays() throws Exception {
		Date init = GetDatehighcharts(From_Date_Review);
		Thread.sleep(5000);
		Date enddate =  GetDatehighcharts(To_Date_Review);
		Thread.sleep(5000);
		String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		System.out.println(var);
		String today = ((JavascriptExecutor)driver).executeScript("return moment().format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())").toString();
		System.out.println(today);
		Date todaydate = formats.parse(today);
		System.out.println(todaydate);
		//String Yesterday = ((JavascriptExecutor)driver).executeScript("return moment().add({days: -1}).format(window.dateFormat.shortTemplate.PlainHtml.toUpperCase())").toString();
		//Date latest = formats.parse(today);
		//System.out.println(latest);
		Thread.sleep(5000);			
		//Assert.assertEquals(enddate, todaydate);
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
	public SA_Abstarct_Methods clickHighchartCriteria(String durationFor) throws Exception {
		durationFor = durationFor.toLowerCase();
		scrollByElement(highchartSec);
	//	scrollByElement(highChartZoom);
		int days;			
		if((!durationFor.equalsIgnoreCase("null"))) {				
			switch(durationFor) {				
			case "1m"  : 	try{
								clickelement(highChart_1M);
								if(eleClicked(highChart_1M)) {
									scrollByElement(highchartSec);
									BaseClass.addEvidence(driver, "Test to verify 1m zoom applied", "yes");
									days = getNumberofDays();			
									if(days >= 28 && days<=31 ) {
										System.out.println("1 Month data is displayed");
									}else {
										System.out.println("Not 1 Month");
										assert(false);
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
									scrollByElement(highchartSec);
									BaseClass.addEvidence(driver, "Test to verify 3m zoom applied", "yes");
									days = getNumberofDays();
									if(days>=90 && days<=92) {
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
									scrollByElement(highchartSec);
									BaseClass.addEvidence(driver, "Test to verify 6m zoom applied", "yes");
									days = getNumberofDays();
									if(days>=180 && days<=184) {
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
									scrollByElement(highchartSec);
									BaseClass.addEvidence(driver, "Test to verify ytd zoom applied", "yes");
									days = getNumberofDays();
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
									scrollByElement(highchartSec);
									BaseClass.addEvidence(driver, "Test to verify 1y zoom applied", "yes");
									days = getNumberofDays();
									if(days>=364 && days<=366) {
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
									scrollByElement(highchartSec);
									BaseClass.addEvidence(driver, "Test to verify all zoom applied", "yes");
									days = getNumberofDays();
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
	 * Get Review export data using column name
	 * @param PathofXL
	 * @param Col_Name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<String> GetDataUsingColName(String PathofXL, String Col_Name) throws Exception {		  
		  FileInputStream excelFilePath = new FileInputStream(new File(PathofXL)); // or specify the path directly
	      Workbook wb = new XSSFWorkbook(excelFilePath);
	      Sheet sh = wb.getSheetAt(0);     
	      Row row = sh.getRow(0);
	      int col = row.getLastCellNum();
	      int Last_row = sh.getLastRowNum();
	      int col_num = 0;
	      System.out.println(""+col);	
	      ArrayList<String> ExportData  = new ArrayList<String>();
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
	            				 cellValue = cell.getStringCellValue();
	            				 ExportData.add(cellValue);
	            			    } 
	            		
	            	}
				}
	            	System.out.println("From Excel:" +ExportData);
            		wb.close();
	            	return ExportData; 
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
	 * To compare the source with Reviews
	 *//*
	public void comparesourcewithreviews(String ele) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		JSWaiter.waitJQueryAngular();
		String Sourcename = getsource().toLowerCase();		
		System.out.println(Sourcename);
		waitForElement(ReviewSection, 10);
		scrollByElement(ReviewSection);
		waitForElement(paginationLast, 10);
		clickelement(paginationLast);
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-2]")).getText());
		System.out.println("Last Page Number is :" +lastpage);
		waitForElement(paginationPrev, 10);
		clickelement(paginationPrev);
		try {
		if(paginationNext.isDisplayed()) {
			for(int i=1;i<=lastpage;i++) {		    	
				int size = Reviews.size();
				System.out.println(size);
				for(int j = 1; j<= size; j++) {
					String sourcenme = driver.findElement(By.xpath((ele[j])).getAttribute("src");
					System.out.println(sourcenme);
					Assert.assertTrue(sourcenme.contains(Sourcename),"Found");
				}if(paginationNext.isEnabled()) {
					scrollByElement(paginationNext);
					paginationNext.click();
					Thread.sleep(4000);
				}		
			}	
		}
	}catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public void verifyTitle(String title, String titletext) {
		waitForElement(PageTitle, 10);
		String Title = PageTitle.getText();
		System.out.println("The title is :" +Title);
		String Titletext = TitleText.getText();
		System.out.println("The title text is :" +Titletext);
		soft.assertEquals(Title, title);
		soft.assertEquals(Titletext, titletext);
		soft.assertAll();
	}
	
	public void reporthighlight(WebElement ele, WebElement ele1) {
		waitForElement(ele1, 10);
		String mainmenu = ele1.getAttribute("class");
		System.out.println("The mainmenu is :" +mainmenu);
		soft.assertEquals(mainmenu, "on_off_root active");
		waitForElement(ele, 10);
		String text = ele.getAttribute("class");
		System.out.println("The class name is :" +text);
		soft.assertEquals(text, "active");
		soft.assertAll();
	}
	
	public int SANumOfentriesinPage(WebElement entry) {
		waitForElement(entry, 10);
		if(entry.isDisplayed()) {
			String entiresText = entry.getText();
			System.out.println("The total entries in a table is :" + entiresText);
			String result1 = null;
			String result = entiresText.substring(entiresText.indexOf("(") + 3, entiresText.indexOf(")") - 7).trim();
			System.out.println("The result is :" +result);
			if(result.contains(",")) {
				result1 = result.replace(",", "").trim();
				int finalvalue = Integer.parseInt(result1);
				System.out.println("The number of entries is : " +finalvalue);
				return finalvalue;
			}else {
				int finalvalue1 = Integer.parseInt(result);
				System.out.println("The number of entries is :" +finalvalue1);
				return finalvalue1;
			}
		}
		return 0;
	}
	
	public void ResultsperPage(SoftAssert soft, WebElement entry, WebElement results) throws InterruptedException {
		int totalentries = NumOfentries(entry);
		System.out.println("The total number of entries are :" + totalentries);
		int entryperPage;
		if (totalentries >= 10) {
			scrollByElement(entry);
			select = new Select(results);
			select.selectByVisibleText("10");
			Thread.sleep(5000);
			entryperPage = NumOfentriesinPage(entry);
			System.out.println("The entries per page is :" + entryperPage);
			soft.assertEquals(10 , entryperPage);
		} else {
			System.out.println("No enough data to perform");
		}
		if (totalentries >= 25) {
			scrollByElement(entry);
			select = new Select(results);
			select.selectByVisibleText("25");
			Thread.sleep(5000);
			entryperPage = NumOfentriesinPage(entry);
			System.out.println("The entries per page is :" + entryperPage);
			soft.assertEquals(25 , entryperPage);
		} else {
			System.out.println("No enough data to perform");
		}
		if (totalentries >= 50) {
			scrollByElement(entry);
			select = new Select(results);
			select.selectByVisibleText("50");
			Thread.sleep(5000);
			entryperPage = NumOfentriesinPage(entry);
			System.out.println("The entries per page is :" + entryperPage);
			soft.assertEquals(50 , entryperPage);
		} else {
			System.out.println("No enough data to perform");
		}
		if (totalentries >= 75) {
			scrollByElement(entry);
			select = new Select(results);
			select.selectByVisibleText("75");
			Thread.sleep(5000);
			entryperPage = NumOfentriesinPage(entry);
			System.out.println("The entries per page is :" + entryperPage);
			soft.assertEquals(75 , entryperPage);
		} else {
			System.out.println("No enough data to perform");
		}
		if (totalentries >= 100) {
			scrollByElement(entry);
			select = new Select(results);
			select.selectByVisibleText("100");
			Thread.sleep(5000);
			entryperPage = NumOfentriesinPage(entry);
			System.out.println("The entries per page is :" + entryperPage);
			soft.assertEquals(100 , entryperPage);
		} else {
			System.out.println("No enough data to perform");
		}
	}
	
	public void GoTopage(WebElement GoTo) throws InterruptedException {
		scrollByElement(GoTo);
		String n = driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
		int totalpage = Integer.parseInt(n);
		clickelement(GoTo);
		GoTo.clear();
		if(totalpage>1) {
		GoTo.sendKeys("2");
		GoTo.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		String classname = driver.findElement(By.xpath("(//*[@class='pagination'])//li[3]")).getAttribute("class");
		System.out.println("The class name is :" +classname);
		Assert.assertEquals(classname, "paginate_button active");
		}else {
			System.out.println("No more pages found");
		}
	}
	
	/**
	 * To get from date selected from UI
	 * @return
	 * @throws ParseException
	 */
	public Date getFromDate() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		String fromdate = ((JavascriptExecutor) driver).executeScript("return document.getElementById('dateFrom').value")
				.toString();
		System.out.println("The from date selected is :" +fromdate);
		Date finalfromdate = formats.parse(fromdate);
		System.out.println("The Date is :" +finalfromdate);
		return finalfromdate;		
	}
	
	/**
	 * To get to date selected from UI
	 * @return
	 * @throws ParseException
	 */
	public Date getToDate() throws ParseException {
		String var = ((JavascriptExecutor) driver).executeScript("return window.dateFormat")
				.toString();
		SimpleDateFormat formats = new SimpleDateFormat(var);
		String todate = ((JavascriptExecutor) driver).executeScript("return document.getElementById('dateTo').value")
				.toString();
		System.out.println("The to date selected is :" +todate);
		Date finaltodate = formats.parse(todate);
		System.out.println("The Date is :" +finaltodate);
		return finaltodate;
	}
	
	public void deletefile() {
		File path = new File("./downloads");
		File[] files = path.listFiles();
		for (File file : files) {
			System.out.println("Deleted filename :" + file.getName());
			file.delete();
		}
	}
}
