package com.dac.main.POM_SE;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

import resources.JSWaiter;

public class SE_Report_Page extends BasePage {

	
	public static ArrayList<String> tableCellValues = new ArrayList<String>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public SE_Report_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;
	
	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;
	
	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;
	
	@FindBy(xpath = "(//*[contains(@id,'posts')]//*[name()='svg']/*[name()='g' and @class='highcharts-series-group']/*[name()='g'])[1]/*[name()='rect']")
	private List<WebElement> TotalPost_barGraph;
	
	@FindBy(xpath = "//div[contains(@class,'highcharts-label highcharts-tooltip')]/span/span")
	private WebElement BarGraphToolTip;
	
	@FindBy(xpath = "(//*[contains(@id,'comments')]//*[name()='svg']/*[name()='g' and @class=\"highcharts-series-group\"]/*[name()='g'])[1]/*[name()='rect']")
	private WebElement TotalComments_barGraph;
	
	@FindBy(id = "socialMediaType")
	private WebElement socialMediaType;
	
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
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'all')]")
	private WebElement allTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'facebook')]")
	private WebElement facebookTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'twitter')]")
	private WebElement twitterTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'instagram')]")
	private WebElement instagramTab;
	
	@FindBy(xpath = "//*[@id='socialMediaType']//a[contains(@href,'youtube')]")
	private WebElement youtubeTab;
	

	private void selectCalender_Date(WebElement calenderField, int day_d, String month_MMM, int year_YYYY) {
		clickelement(calenderField);
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
		clickelement(driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar']//td/a[text()="+day_d+"]")));
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

	public SE_Report_Page selectCalender_FromDate(int day_d, String month_MMM, int year_YYYY) {
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date(highChart_fromDate, day_d, month_MMM, year_YYYY);
		}
		return this;
	}
	
	public SE_Report_Page selectCalender_ToDate(int day_d, String month_MMM, int year_YYYY) {
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
	
	private boolean eleDisabled(WebElement element) {
		if(element.isDisplayed()) {
			String classes = element.getAttribute("class");
			boolean isDisabled = classes.contains("highcharts-button highcharts-button-disabled");
			return !(isDisabled);			
		}
		return false;
	}
	
	public SE_Report_Page clickHighchartCriteria(String durationFor) {
		durationFor = durationFor.toLowerCase();
		scrollByElement(socialMediaType);
		
		if((!durationFor.equalsIgnoreCase("null"))) {
			
			switch(durationFor) {
			
			case "1m"  : if(eleDisabled(highChart_1M)) clickelement(highChart_1M);
						 else if(eleDisabled(highChart_YTD)) clickelement(highChart_All);
						 else clickelement(highChart_All);
						 break;
			case "3m"  : if(eleDisabled(highChart_3M)) clickelement(highChart_3M);
						 else if(eleDisabled(highChart_YTD)) clickelement(highChart_All);
						 else clickelement(highChart_All);
						 break;
			case "6m"  : if(eleDisabled(highChart_6M)) clickelement(highChart_6M);
						 else if(eleDisabled(highChart_YTD)) clickelement(highChart_All);
						 else clickelement(highChart_All);
						 break;
			case "ytd" : if(eleDisabled(highChart_YTD)) clickelement(highChart_YTD);
						 else clickelement(highChart_All);
					     break;
			case "1y"  : if(eleDisabled(highChart_1y)) clickelement(highChart_1y);
						 else if(eleDisabled(highChart_YTD)) clickelement(highChart_All);
						 else clickelement(highChart_All);
						 break;
			case "all" :
			default    : if(eleDisabled(highChart_All)) clickelement(highChart_All);
			
			}
		}
		
		return this;
	}
	
	public SE_Report_Page forVendor(String vendorName) {
		vendorName = vendorName.toLowerCase();
		scrollByElement(socialMediaType);
		
		switch(vendorName) {
		
		case "facebook"  : clickelement(facebookTab);
				           break;
		case "twitter"   : clickelement(twitterTab);
		                   break;
		case "instagram" : clickelement(instagramTab);
		                   break;
		case "youtube"   : clickelement(youtubeTab);
		                   break;
		case "all"       :
		default          : clickelement(allTab);
		
		}
		return this;
	}
	
	
	public int getGraphsTotalDataCount() {
		JSWaiter.waitJQueryAngular();
		String eleXPath = "//*[contains(@id,'posts')]//*[name()='g' and @class='highcharts-series-group']//*[name()='rect' and @height > '0' and @x > '0' and @y > '0']";
		int totalCount = 0;
		scrollByElement(socialMediaType);
		List<WebElement> foundBarGraphs = driver.findElements(By.xpath(eleXPath));		
		if(!(foundBarGraphs.isEmpty())) {
			for(WebElement Post_barGraph : foundBarGraphs) {
				action.moveToElement(Post_barGraph);
				//String h1 = Color.fromString(Post_barGraph.getCssValue("fill")).asHex();
				//System.out.println("Color : "+h1);
				try {
					Thread.sleep(2000);
					clickelement(Post_barGraph);
					totalCount = totalCount + Integer.parseInt((BarGraphToolTip.getText().split(" "))[2]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("totalCount : "+totalCount);
		return totalCount;
	}
	
	
	/*
	 * Working for the all vendor
	 * 
	 * 
	public void getToolTipData() {
		String eleXPath = "//*[contains(@id,'posts')]//*[name()='g' and @class='highcharts-series-group']//*[name()='rect' and @height > '0' and @x > '0' and @y > '0']";
		List<WebElement> actBarGraph = new ArrayList<WebElement>();
		int count = 1, totalCount = 0;
		scrollByElement(socialMediaType);
		clickelement(twitterTab);
		//clickelement(highChart_1y);
		
		System.out.println("TotalPost_barGraph size : "+TotalPost_barGraph.size());
		for(WebElement Post_barGraph : TotalPost_barGraph) {
			int xVal = (int)(Double.parseDouble(Post_barGraph.getAttribute("x")));
			int yVal = (int)(Double.parseDouble(Post_barGraph.getAttribute("y")));
			System.out.println("For count: "+count+" xVal : "+xVal+" yVal : "+yVal);
			if(xVal > 0 & yVal >0) {
				actBarGraph.add(driver.findElement(By.xpath("("+eleXPath+")["+count+"]")));
				System.out.println("("+eleXPath+")["+count+"]");
				count++;
			}
		}
		System.out.println("actBarGraph size : "+actBarGraph.size());
		if(!(actBarGraph.isEmpty())) {
			for(WebElement Post_barGraph : actBarGraph) {
				action.moveToElement(Post_barGraph);
			
				String c1 = Post_barGraph.getCssValue("fill");
				String h1 = Color.fromString(c1).asHex();
				System.out.println("Color : "+h1);
				
				try {
					Thread.sleep(3000);
					clickelement(Post_barGraph);
					Thread.sleep(1000);
					String[] a = BarGraphToolTip.getText().split(" ");
					totalCount = totalCount + Integer.parseInt(a[2]);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("totalCount : "+totalCount);
	}*/
	
}
