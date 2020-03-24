package com.dac.main.POM_SE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;  


import com.dac.main.BasePage;

import resources.BaseClass;
import resources.CurrentState;
import resources.JSWaiter;

public class SE_Post_Page extends SE_abstractMethods {

	
	private static final String Group = null;
	private static final String CountryCode = null;
	private static final String State = null;
	private static final String City = null;
	private static final String Location = null;
	public static ArrayList<String> tableCellValues = new ArrayList<String>();
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	public SE_Post_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	//*[@class="container-fluid"][2]//div[@class='table-responsive']
	
	
	String fromDate = "(//*[@class='datepicker hasDatepicker'])[1]";
	
	String toDate = "(//*[@class = 'datepicker hasDatepicker'])[2]";
	
    String StartDate = "(//*[@id='dateFrom'])";
	
	String EndDate = "(//*[@id = 'dateTo'])";
	
	@FindBy(xpath = "//*[@data-handler='prev']")
	private WebElement prevMonth;
	
	@FindBy(xpath = "//*[@data-handler='next']")
	private WebElement nextMonth;
	
	@FindBy(className = "ui-datepicker-month")
	private WebElement currentMonth_DatePicker;
	
	@FindBy(className = "ui-datepicker-year")
	private String currentYear_DatePicker1;
	
	@FindBy(className = "ui-datepicker-year")
	private WebElement currentYear_DatePicker;
	
	@FindBy(id = "apply_filter")
	private WebElement applyFilter;
	
	@FindBy(id = "socialMediaType")
	private WebElement socialMediaType;
	
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
	
	//Abinaya 
	
    @FindBy (linkText = "Create a new post")
    private WebElement createNewPostbutton;
    
    @FindBy(xpath="//*[@id=\"wizard-next-button\"]/span")
    private WebElement step1Next;
    
    @FindBy(xpath="//*[@id=\"41407_txt\"]")
    private WebElement pageSelect;
    
    @FindBy(xpath="//*[@id=\"wizard-next-button\"]/span")
    private WebElement step2Next;
    
    @FindBy(xpath="//*[@id=\"facebook-message-field\"]")
    private WebElement enterPost;
    
    @FindBy(xpath="//*[@id=\"wizard-submit-button-text-publish\"]")
    private WebElement publishPost;
    
    @FindBy(xpath = "//*[@class='btn btn-long configuration-button'][contains(text(),'OK')]")
    private WebElement sucessButton;
    
    @FindBy(xpath = "//table[@class='table table-bordered table-striped create-post-page-table']")
    private WebElement loctable;
    
    @FindBy(xpath = "//*[@id='table-pagination-cell']//div[@class='col-xs-6']")
    private WebElement numofentries;
    
    @FindBy(xpath="//div[@id='tblPublishedItems_wrapper']")
    private WebElement publishPostTable;
    
    @FindBy(xpath="//*[@id='txtKeyword']")
    private WebElement keywordText;
    
    @FindBy(xpath="//*[@id='postTable']")
    private WebElement Posttable;
    
    @FindBy(xpath = "(//*[@class='pagination']//a)")
    private List<WebElement> pagination;
   
    @FindBy(xpath = "(//*[@class='pagination']//a)[1]")
    private WebElement paginationPrev;
   
    @FindBy(xpath = "(//*[@class='pagination']//a)[last()]")
    private WebElement paginationLast;
   
    @FindBy(xpath = "(//*[@class='pagination']//a)[last()-1]")
    private WebElement paginationNext;
    
    @FindBy(xpath="(//*[@id='btn-group-gmb-select']")
    private WebElement Gmbbtn;
    
    
    
    
    @FindBy(xpath="//*[@id='0']")
    private WebElement All;
    
   @FindBy(xpath="//*[@id='1']")
   private WebElement fb;
   
   @FindBy(xpath="//*[@id='3']")
   private WebElement twitter;
   
   @FindBy(xpath="//*[@id='4']")
   private WebElement insta;
   
   @FindBy(xpath="//*[@id='5']")
   private WebElement youtube;
   
   @FindBy(xpath="//*[@id=\"postTable\"]/tbody/tr[1]/td[4]/div/div[2]")
   private WebElement date;
   
   @FindBy(xpath="//*[@id='dateFrom']")
    private WebElement fromDate1;
   
   
  
   @FindBy(xpath="//*[@id='dateTo']")
	private WebElement toDate1;
   
   @FindBy(xpath="//*[@class='btn btn-xs btn-default btn-block link-edit'][1]")
    private WebElement edit_btn;
    
   @FindBy(xpath="//img[@src='/Assets/img/envelope-close.png']")
   private WebElement envelopeClose;
   @FindBy(xpath="//img[@src='/Assets/img/envelope-open.png']")
   private WebElement envelopeOpen;
   @FindBy(xpath="//img[@src='/Assets/img/yellow-flag.png']")
   private WebElement yellowFlag;
   @FindBy(xpath="//img[@src='/Assets/img/grey-flag.png']")
   private WebElement greyFlag;
   
   @FindBy(xpath="//*[@id='event-tab']/a")
   private WebElement event;
   @FindBy(xpath="//*[@id='gmb-event-message-field']")
   private WebElement eventdetails;
   @FindBy(xpath="//*[@id=\"ui-datepicker-div\"]")
   private WebElement datePicker;
 //*[@id="wizard-submit-button-text-submit"]
    
   @FindBy(xpath="//*[@id=\"wizard-submit-button\"]")
   private WebElement approve;
   
   @FindBy(xpath="//span[@id='wizard-submit-button-text-submit']")
   private WebElement approve1;
   
   @FindBy(xpath="//*[@id='btnUnpublished']")
   private WebElement state_rej;

    
    String alertText = "";
	public void create_PostforFB(String textValue, String text, String Vendor) throws InterruptedException, Exception {
		waitForElement(createNewPostbutton, 10);
		clickelement(createNewPostbutton);
		
		waitForElement(step1Next, 10);
		clickelement(step1Next);
		Thread.sleep(5000);
		selectlocation(text);
		Thread.sleep(5000);
		clickelement(step2Next);
		WebElement text1=driver.findElement(By.xpath("//*[@id=\"facebook-message-field\"]"));
		text1.sendKeys(textValue);
		waitForElement(approve, 100);
		clickelement(approve);

		//List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
		//Assert.assertTrue(list.size() > 0);
		clickelement(sucessButton);
		//driver.findElement(By.xpath("//*[@id=\"wizard-submit-button\"]")).click();
		//scrollByElement(approve);
		//clickelement(approve);
		
	}
	public void create_PostforFB_Cre(String textValue, String text, String Vendor) throws InterruptedException, Exception {
		waitForElement(createNewPostbutton, 10);
		clickelement(createNewPostbutton);
		
		waitForElement(step1Next, 10);
		clickelement(step1Next);
		Thread.sleep(5000);
		selectlocation(text);
		Thread.sleep(5000);
		clickelement(step2Next);
		WebElement text1=driver.findElement(By.xpath("//*[@id=\"facebook-message-field\"]"));
		text1.sendKeys(textValue);
		waitForElement(approve, 100);
		clickelement(approve);

		//List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
		//Assert.assertTrue(list.size() > 0);
		clickelement(sucessButton);
		//driver.findElement(By.xpath("//*[@id=\"wizard-submit-button\"]")).click();
		//scrollByElement(approve);
		//clickelement(approve);
		
	}
	public void create_PostforGMB(String textValue, String location,String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws InterruptedException, Exception {
		waitForElement(createNewPostbutton, 10);
		clickelement(createNewPostbutton);
			WebElement icon = driver.findElement(By.xpath("//*[@id=\'btn-group-gmb-select\']"));
			Actions ob = new Actions(driver);
			ob.click(icon);
			Action action  = ob.build();
			action.perform();
		waitForElement(step1Next, 10);
		clickelement(step1Next);
		Thread.sleep(5000);
		selectlocation(location);
		Thread.sleep(5000);
		clickelement(step2Next);
		WebElement text2=driver.findElement(By.xpath("//*[@id=\"gmb-whatsnew-message-field\"]"));
		text2.sendKeys(textValue);
		Select dropdown = new Select(driver.findElement(By.id("gmb-button-select-list"))); 
		dropdown.selectByValue("SHOP");
		WebElement text3=driver.findElement(By.xpath("//*[@id=\"gmb-button-url-field\"]"));
		text3.sendKeys("http://www.abi.com");
		waitForElement(event, 10);
		clickelement(event);
		
		WebElement text33=driver.findElement(By.xpath("//*[@id=\"gmb-event-title-field\"]"));
		text33.sendKeys("Social Meeting");
		eventdetails.sendKeys(textValue);
		Select dropdown1 = new Select(driver.findElement(By.id("gmb-button-select-list"))); 
		dropdown1.selectByValue("SHOP");
		WebElement text31=driver.findElement(By.xpath("//*[@id=\"gmb-button-url-field\"]"));
		text31.sendKeys("http://www.abi.com");
		System.out.println("ABI"+from_day + from_month + from_year +to_day + to_month + to_year);
		 selectCalender_FromDate1((int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
		 selectCalender_ToDate1((int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));		 
		 BaseClass.addEvidence(CurrentState.getDriver(), "Date Picker", "yes");
		 getCurrentfromDate1();
		 getCurrenttoDate1();
		waitForElement(publishPost, 100);
		clickelement(publishPost);
		clickelement(sucessButton); 		
	}
	
	
	public void selectlocation(String text) {
		int rowsize = numofrows();
		waitForElement(loctable, 10);
		scrollByElement(loctable);
		for(int i=1;i<=rowsize; i++) {				
			String celtext = driver.findElement(By.xpath("//table/tbody/tr["+ i +"]/td[contains(text(),'"+text+"')]")).getText();
			System.out.println("String"+ celtext);
			if(celtext.equals(text)) {
				System.out.println("Testing");
			driver.findElement(By.xpath("//table/tbody/tr["+ i +"]//input["+ i +"]")).click();
			}
			break;
		}
		
	}
	
	public int numofrows() {
		waitForElement(numofentries, 10);
		scrollByElement(numofentries);
		String x = numofentries.getText();
		String y=x.substring(27,28);
		//String y = x.substring(x.indexOf('f')+1, x.indexOf('e'));
		System.out.println(y);
		int totrows = Integer.parseInt(y);
		return totrows;
	}
	
	public ArrayList<String> table() {
		ArrayList<String> tableValues=new ArrayList<String>();
		WebElement Table = driver.findElement(By.xpath("//div[@id='tblPublishedItems_wrapper']"));
		
			String text1=driver.findElement(By.xpath("//*[@id='tblPublishedItems']/tbody/tr[1]/td[2]")).getText();
			tableValues.add(text1);
			String text2=driver.findElement(By.xpath("//*[@id='tblPublishedItems']/tbody/tr[1]/td[3]")).getText();
			tableValues.add(text2);
			String text3=driver.findElement(By.xpath("//*[@id='tblPublishedItems']/tbody/tr[1]/td[6]")).getText();
			tableValues.add(text3);
			String text4=driver.findElement(By.xpath("//*[@id='tblPublishedItems']/tbody/tr[1]/td[7]")).getText();
			tableValues.add(text4);
             System.out.println("name"+ text1);
		
		System.out.println(tableValues);
		return tableValues;
		
	}
	public ArrayList<String> table1() {
		ArrayList<String> tableValues=new ArrayList<String>();
		WebElement Table = driver.findElement(By.xpath("//div[@id='tblUnpublishedItems_wrapper']"));
		
			String text1=driver.findElement(By.xpath("//*[@id='tblUnpublishedItems']/tbody/tr[1]/td[2]")).getText();
			tableValues.add(text1);
			String text2=driver.findElement(By.xpath("//*[@id='tblUnpublishedItems']/tbody/tr[1]/td[3]")).getText();
			tableValues.add(text2);
			String text3=driver.findElement(By.xpath("//*[@id='tblUnpublishedItems']/tbody/tr[1]/td[6]")).getText();
			tableValues.add(text3);
			String text4=driver.findElement(By.xpath("//*[@id='tblUnpublishedItems']/tbody/tr[1]/td[7]")).getText();
			tableValues.add(text4);
             System.out.println("name"+ text1);
		
		System.out.println(tableValues);
		return tableValues;
		
	}
	public void applyKey(String Kword) throws InterruptedException {
		waitForElement(keywordText, 10);
	//	scrollByElement(keywordText);
		WebElement word=driver.findElement(By.xpath("//*[@id='txtKeyword']"));
		word.sendKeys(Kword);
		//word.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}
	public void flag() {
		clickelement(envelopeClose);
		clickelement(greyFlag);
	}
	public void flag1() {
		clickelement(envelopeOpen);
		clickelement(yellowFlag);
	}
	
	public ArrayList<String> keywords(String Kword, String location) throws InterruptedException {

		String[] dateValue = null;
		String te = null;
		ArrayList<String> posttableValues=new ArrayList<String>();
		ArrayList<String> locationValues=new ArrayList<String>();
		ArrayList<String> enve=new ArrayList<String>();
		System.out.println("Printing Location"+location);
		ArrayList<String> date1 =new ArrayList<>();
		List<WebElement> Table = driver.findElements(By.xpath("//*[@id='postTable']/tbody/tr"));
		waitForElement(paginationLast, 10);
        clickelement(paginationLast);
        String page=driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText();
        System.out.println("page"+ page);
        if(page.equals("Previous")) {
        	System.out.println("Data is not available for the vendor");
        }
        else {
		int lastpage = Integer.parseInt(driver.findElement(By.xpath("(//*[@class='pagination']//a)[last()-1]")).getText());
		 System.out.println("Last Page Number is :" +lastpage);
		 waitForElement(paginationPrev, 10);
	        clickelement(paginationPrev);
	        try{ 
	        	if(paginationNext.isDisplayed()) {
	            for(int i=1;i<=lastpage;i++) {   
	            	  int rows_count =Table.size() ;
	            	  System.out.println("ROW COUNT"+rows_count );
		for(int j=1;j<=rows_count;j++) {
			String text1=driver.findElement(By.xpath("//*[@id=\"postTable\"]/tbody/tr[" + j + "]/td[3]/div/div[1]")).getText();
			String text2=driver.findElement(By.xpath("//*[@id=\"postTable\"]/tbody/tr[" + j + "]/td[4]/div/div[2]")).getText();
			String text3=driver.findElement(By.xpath("//*[@id=\"postTable\"]/tbody/tr[" + j + "]/td[2]/div/div[3]")).getText();
			String text4=driver.findElement(By.xpath("//*[@id=\"postTable\"]/tbody/tr[" + j + "]/td[2]/div/div[4]")).getText();
			String text5=driver.findElement(By.xpath("//*[@id=\"postTable\"]/tbody/tr[" + j + "]/td[2]/div/div[5]")).getText();
			 te=driver.findElement(By.xpath("//img[@src='/Assets/img/envelope-close.png']")).getAttribute("src");
			
			String te1=driver.findElement(By.xpath("//*[@id=\"postTable\"]/tbody/tr["+ j +"]/td[1]/div/button[1]/img[2]")).getAttribute("src");

			System.out.println("***********"+te1);
			System.out.println("close"+te);
			dateValue = text2.split(" ");
			date1.add(dateValue[4]);
			
        	System.out.println("Arraylist"+date1);
			posttableValues.add(text1);
		
			locationValues.add(text3);
			enve.add(te1);
						
	            	}if(paginationNext.isEnabled()) {
	                    scrollByElement(paginationNext);
	                    paginationNext.click();
	                    Thread.sleep(4000);
	                    }
	            }
	            	try {
	                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	                    sdf.setLenient(false);
	                    Date d1 = sdf.parse(getCurrentfromDate1());
	                    Date d3 = sdf.parse(getCurrenttoDate1());
	                    
	                  for(int i=0;i<date1.size();i++) {
	                	  String d=date1.get(i);
	                	  System.out.println("Date"+d);
	                	  Date d2 = sdf.parse(d);

	                    if (d2.compareTo(d1) >= 0) {
	                          if (d2.compareTo(d3) <= 0) {
	                                 System.out.println("d2 is in between d1 and d3");
	                          } else {
	                                 System.out.println("d2 is NOT in between d1 and d3");
	                          }
	                    } else {
	                          System.out.println("d2 is NOT in between d1 and d3");
	                    }
	                    }
	             } catch (ParseException pe) {
	                    pe.printStackTrace();
	             }
	        	}
	        	
		for(int k =0;k<= posttableValues.size()- 1;k++) {
			System.out.println("Test"+posttableValues.get(k).contains(Kword));
		 }	
		

		for(int l =0;l<= locationValues.size()- 1;l++) {
			System.out.println(location.contains(locationValues.get(l)));
			System.out.println("loc"+locationValues.get(l)+"orginal"+location);
		 }	
		for(int m =0;m<= enve.size()- 1;m++) {
			System.out.println(te.contains(enve.get(m)));
			System.out.println("ac"+enve.get(m)+"dc"+te);
		 }	
	        }
	        
		catch(Exception e) {
            e.printStackTrace();
		
		}
        }  
    	return date1;
	}
	        
public void ForTabsscenario(String Kword, String Location) throws InterruptedException {
	waitForElement(All, 10);
	clickelement(All);
	keywords(Kword,Location);
	Thread.sleep(5000);
	
	waitForElement(fb, 10);
	clickelement(fb);
	keywords(Kword, Location);
	Thread.sleep(5000);
	
	waitForElement(twitter, 10);
	clickelement(twitter);
	keywords(Kword, Location);
	Thread.sleep(5000);
	
	waitForElement(insta, 10);
	clickelement(insta);
	keywords(Kword, Location);
	Thread.sleep(5000);
	
	waitForElement(youtube, 10);
	clickelement(youtube);
	keywords(Kword, Location);
	Thread.sleep(5000);
	
	
	
}
	        
		
	
	/**
	 *This method is used to check whether data is there in table or not based on the applied criteria
	 * @return true : if data is there otherwise return false			*/
	public boolean isDataAvailable() {
		JSWaiter.waitJQueryAngular();
		if(driver.findElement(By.className("dataTables_info")).isDisplayed()) {
			return true;
		}else if(driver.findElement(By.className("dataTables_empty")).isDisplayed()) {
			try {
				BaseClass.addEvidence(driver, "Data is not available for selected Filter", "yes");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}
	
	private void selectCalender_Date(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		//clickelement(calenderField);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//*[@class="datepicker  hasDatepicker"][1]
//		driver.findElement(By.xpath("(//*[@class='datepicker hasDatepicker'])[1]")).click();
		//driver.findElement(By.xpath("//input[@id='EndDate']")).click();
		
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
	
	private void selectCalender_Date1(String calenderField, int day_d, String month_MMM, int year_YYYY) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	
	
	public void selectCalender_FromDate(int day_d, String month_MMM, int year_YYYY) {
		
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date(fromDate, day_d, month_MMM, year_YYYY);
		}
	}
	
	public void selectCalender_ToDate(int day_d, String month_MMM, int year_YYYY) {
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date(toDate, day_d, month_MMM, year_YYYY);	
		}
	}	
	
public void selectCalender_FromDate1(int day_d, String month_MMM, int year_YYYY) {
		
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date1(StartDate, day_d, month_MMM, year_YYYY);
		}
	}
	
	public void selectCalender_ToDate1(int day_d, String month_MMM, int year_YYYY) {
		if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
			selectCalender_Date1(EndDate, day_d, month_MMM, year_YYYY);	
		}
	}	
	
	public SE_Post_Page forVendor(String vendorName) {
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
	
	public int getTotalTableDataCount() {
		int dataCount = 0;
		//clickelement(facebookTab);
		if(isDataAvailable()) {
			String tableDataCount = driver.findElement(By.className("dataTables_info")).getText();
			dataCount = Integer.parseInt((tableDataCount.substring(tableDataCount.indexOf("("), tableDataCount.length()).split(" "))[1]);
		}
		return dataCount;
	}
	
	public void clickApplyFilter() {
		JSWaiter.waitJQueryAngular();
		try {
			Thread.sleep(2000);
			clickelement(applyFilter);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 public String getCurrentfromDate() throws ParseException, java.text.ParseException {
		    String currentfromDate = ((JavascriptExecutor)driver).executeScript("return document.getElementById('fromDate').value").toString();
		    System.out.println(currentfromDate);
		    String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		    System.out.println("text1 :"+var);
		    return currentfromDate;
		}
	 public String getCurrenttoDate() throws ParseException, java.text.ParseException {
		    String currenttoDate = ((JavascriptExecutor)driver).executeScript("return document.getElementById('toDate').value").toString();
		    System.out.println(currenttoDate);
		    String var1 = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		    System.out.println("text2"+var1);
		    return currenttoDate;
		    }
	 
	 public String getCurrentfromDate1() throws ParseException, java.text.ParseException {
		    String currentStartDate = ((JavascriptExecutor)driver).executeScript("return document.getElementById('StartDate').value").toString();
		    System.out.println(currentStartDate);
		    String var = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		    System.out.println("text1 :"+var);
		    return currentStartDate;
		}
	 public String getCurrenttoDate1() throws ParseException, java.text.ParseException {
		    String currentEndDate = ((JavascriptExecutor)driver).executeScript("return document.getElementById('EndDate').value").toString();
		    System.out.println(currentEndDate);
		    String var1 = ((JavascriptExecutor)driver).executeScript("return window.dateFormat.shortTemplate.PlainHtml").toString();
		    System.out.println("text2"+var1);
		    return currentEndDate;
		    }
	 

public void edit_1() throws InterruptedException {
	Select dropdown = new Select(driver.findElement(By.id("ddlStatusUnpublished"))); 
	dropdown.selectByValue("1");
	edit_op();
	
}
public void edit_2() throws InterruptedException {
	Select dropdown = new Select(driver.findElement(By.id("ddlStatusUnpublished"))); 
	dropdown.selectByValue("3");
	edit_op();
	
}
public void edit_op() {
	clickelement(state_rej);
	clickelement(edit_btn);
	clickelement(step2Next);
	WebElement text1=driver.findElement(By.xpath("//*[@id=\"facebook-message-field\"]"));
	text1.sendKeys("Updated");
	scrollByElement(approve1);
	waitForElement(approve1, 100);
	clickelement(approve1);
	clickelement(sucessButton);

}
			
}
