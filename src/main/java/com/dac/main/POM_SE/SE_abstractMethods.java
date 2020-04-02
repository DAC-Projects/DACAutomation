package com.dac.main.POM_SE;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.BasePage;

import bsh.ParseException;
import resources.JSWaiter;

public class SE_abstractMethods extends BasePage {
	
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	public SE_abstractMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 45);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}


	// Global Filter locators 

	@FindBy(xpath="//*[@id='dateFrom']")
	private WebElement fromDate;

	@FindBy(xpath="//*[@id='dateTo']")
	private WebElement toDate;
	
	 @FindBy(xpath = "//*[@data-handler='prev']")
	 private WebElement prevMonth;
	   
	    @FindBy(xpath = "//*[@data-handler='next']")
	    private WebElement nextMonth;
	   
	    @FindBy(className = "ui-datepicker-month")
	    private WebElement currentMonth_DatePicker;
	   
	    @FindBy(className = "ui-datepicker-year")
	    private WebElement currentYear_DatePicker;
	    

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
	

    
    /**
     * To pass date to the UI Calendar
     * @param calenderField
     * @param day_d
     * @param month_MMM
     * @param year_YYYY
     * @throws java.text.ParseException 
     */
    private void selectCalender_Date(String calenderField, int day_d, String month_MMM, int year_YYYY) throws java.text.ParseException {
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
     * @throws java.text.ParseException 
     */
    private int monthCode(String month_MMM) throws java.text.ParseException {



    int month = 0;
    Date date;
    date = new SimpleDateFormat("MMM").parse(month_MMM);
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(date);
	 month = cal.get(Calendar.MONTH);
    return month;
}

    /**
     * To set from date in a calendar
     * @param calenderField
     * @param day_d
     * @param month_MMM
     * @param year_YYYY
     * @throws java.text.ParseException 
     */
    public  void selectCalender_FromDate(String calenderField,int day_d, String month_MMM, int year_YYYY) throws java.text.ParseException {
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
     * @throws java.text.ParseException 
     */
    public void  selectCalender_ToDate(String calenderField,int day_d, String month_MMM, int year_YYYY) throws java.text.ParseException {
    if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
        selectCalender_Date(calenderField, day_d, month_MMM, year_YYYY);    
    }
    
}
    
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
			scrollByElement(PageTitle);
			waitUntilLoad(driver);
			if(!Group.equals("None")) {	
				scrollByElement(fiterGroup);
				clickelement(fiterGroup);
				waitForElement(filterDropDown, 20);
				group = fiterGroup.findElement(By.xpath("//div[@data-value='"+Group+"']"));
				waitForElement(group, 10);
				clickelement(group);
				waitUntilLoad(driver);
			}
			if(!CountryCode.equals("All Countries")) {
				scrollByElement(FilterCountry);

				clickelement(FilterCountry);
				waitForElement(filterDropDown, 20);
				country = driver.findElement(By.xpath("//*[contains(@class,'myList')][1]//div[contains(text(),'"+CountryCode.toUpperCase()+"')]"));
				waitForElement(country, 30);
				clickelement(country);
				waitUntilLoad(driver);
			}
			if(!State.equals("All States")) {	
				scrollByElement(FilterState);

				clickelement(FilterState);
				waitForElement(filterDropDown, 20);
				state = FilterState.findElement(By.xpath("//div[contains(text(),'"+State+"')]"));
				waitForElement(state, 10);
				Thread.sleep(1000);
				clickelement(state);
				waitUntilLoad(driver);
			}
			if(!City.equals("All Cities")) {
				scrollByElement(FilterCity);

				clickelement(FilterCity);
				waitForElement(filterDropDown, 20);
				city = FilterCity.findElement(By.xpath("//div[contains(text(),'"+City+"')]"));
				waitForElement(city, 10);
				Thread.sleep(1000);
				clickelement(city);
				waitUntilLoad(driver);
			}
			if(!Location.equals("All Locations")) {		
				scrollByElement(Filterlocation);

				clickelement(Filterlocation);
				waitForElement(filterDropDown, 20);
				location = Filterlocation.findElement(By.xpath("//div[contains(text(),'"+Location+"')]"));
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
	public void clickApplyFilterBTN() throws InterruptedException {
		JSWaiter.waitJQueryAngular();
		if(Apply_filter.isDisplayed()) {
			clickelement(Apply_filter);
			Thread.sleep(3000);
		}
	}
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Abi Time"+formattedDate);
		return formattedDate;
		
	}

}
