package com.dac.main.POM_TPSEE;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.dac.main.POM_SE.SE_Report_Page;

public class TPSEE_ROI extends TPSEE_abstractMethods {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public TPSEE_ROI(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//*[@id='website-clicks']")
	private String ScoresTable;
	
	@FindBy(xpath="//*[@id='website-clicks-value']")
	private WebElement website_Click_Value;
	
	@FindBy(xpath="//*[@id='direction-clicks-value']")
	private WebElement direction_Click_Value;
	
	@FindBy(xpath="//*[@id='phonecalls-clicks-value']")
	private WebElement phone_Click_Value;
	
	@FindBy(xpath="//*[@id='annual-cost-per-location']")
	private WebElement annual_cost;
	@FindBy(xpath="(//span[@class='rev'])[1]")
	private WebElement total_Web;
	
	@FindBy(xpath="(//span[@class='rev'])[2]")
	private WebElement total_dir;
	
	@FindBy(xpath="(//span[@class='rev'])[3]")
	private WebElement total_pho;
	
	@FindBy(xpath="(//span[@class='rev'])[4]")
	private WebElement total_click;
	
	@FindBy(xpath="(//span[@class='rev'])[5]")
	private WebElement avg;
	@FindBy(xpath="(//span[@class='rev'])[6]")
	private WebElement avg_ROI;
	
	@FindBy(xpath="//*[@id='number-of-locations']")
	private WebElement locations;
			
	@FindBy(xpath="//*[@id='daily-cost-per-location']")
	private WebElement daily;
	@FindBy(xpath="//label[@class='reg-label']")
	private WebElement lab;
	
	@FindBy(xpath="//span[@class='dollar-sign']")
	private WebElement symbol;
	
	@FindBy(xpath="//*[@id='save-btn']")
	private WebElement save_btn;
	
	@FindBy(xpath="//*[@id='roi-note']")
	private WebElement roi_notes;
	@FindBy(xpath="//*[@id='save-notes']")
	private WebElement save_notes;
	
	@FindBy(xpath="//*[@class='btn btn-primary']")
	private WebElement close;
	@FindBy(xpath="//*[@id='page-content']/h1")
	private WebElement PageTitle;
	@FindBy(xpath="//*[@id='page-content']/p")
	private WebElement PageTitletext;
	 String fromDate = "(//*[@class=\"highcharts-label highcharts-range-input\"][1])";
	 String toDate = "(//*[@class=\"highcharts-label highcharts-range-input\"][2])";
	 String StartDate = "(//*[@id='startdatepicker'])";
	 String EndDate = "(//*[@id = 'enddatepicker'])";

		 @FindBy(xpath = "//*[@data-handler='prev']")
		 private WebElement prevMonth;
		 
		 
		    @FindBy(className = "ui-datepicker-year")
		    private WebElement currentYear_DatePicker;
		    
		    @FindBy(className = "ui-datepicker-month")
		    private WebElement currentMonth_DatePicker;
		    
		   @FindBy(className="ui-datepicker-current-day")
		   private WebElement date;
		    @FindBy(xpath = "//*[@data-handler='next']")
			private WebElement nextMonth;
	
		    @FindBy(xpath="//*[@id='google_places_report']/a")
			private WebElement GMB_page;
		    @FindBy(xpath="//*[@id='customerWebsite']")
		    private WebElement cus_web;
		    @FindBy(xpath="//*[@id='customerDirections']")
		    private WebElement cus_Dir;
		    @FindBy(xpath="//*[@id='customerPhonecalls']")
		    private WebElement cus_pho;
		    @FindBy(xpath= "(//*[@class='walkme-icon-image-div'])[1]")
		    private WebElement tool_1;
		    @FindBy(xpath= "(//*[@class='walkme-icon-image-div'])[2]")
		    private WebElement tool_2;
		    @FindBy(xpath= "(//*[@class='walkme-icon-image-div'])[3]")
		    private WebElement tool_3;
		    @FindBy(xpath= "(//*[@class='walkme-icon-image-div'])[4]")
		    private WebElement tool_4;
		    @FindBy(xpath="//div[@class='walkme-tooltip-content']")
		    private WebElement tool_value;
		    @FindBy(xpath="//*[@id='page-content']/div[1]/div[2]/ul/li[1]/div/span")
		    private WebElement dat_count;
	ArrayList<String> actualResult = new ArrayList<String>();

	ArrayList<String> expectedResult = new ArrayList<String>();
	ArrayList<Double> totalResult = new ArrayList<Double>();
	double annual_per=2.0/365;
public void tool1(String t1) throws InterruptedException {
	Thread.sleep(3000);
    waitForElement(tool_1, 30);
    scrollByElement(tool_1);
    tool_1.click();
    Thread.sleep(3000);
    String tool_val=tool_value.getText();
    System.out.println(tool_val);
    Assert.assertEquals(t1,tool_val);

}
@FindBy(xpath="//div[@title='Stop Walk-thru']")
private WebElement notificationClose1;

@FindBy(xpath="//*[text()='x']")
private WebElement notificationClose2;




public void notificationHandle() throws InterruptedException{
    waitForElement(notificationClose1, 15);
  //  notificationClose1.click();
   // notificationClose2.click();
	Thread.sleep(3000);

}


public void tool2(String t2) throws InterruptedException {
	Thread.sleep(3000);
    waitForElement(tool_2, 30);
	scrollByElement(tool_2);
    tool_2.click();
    Thread.sleep(3000);
    String tool_val2=tool_value.getText();
    System.out.println(tool_val2);  
    Assert.assertEquals(t2,tool_val2);		
}
public void tool3(String t3) throws InterruptedException {
	Thread.sleep(3000);
    scrollByElement(tool_3);
	waitForElement(tool_3, 30);
    tool_3.click(); 
    Thread.sleep(3000);

    String tool_val3=tool_value.getText();
    System.out.println(tool_val3);
    //Assert.assertEquals(t3,tool_val3);

}
public void tool4(String t4) throws InterruptedException {
	Thread.sleep(3000);

      scrollByElement(tool_4);
        waitForElement(tool_4, 30);
        tool_4.click(); 
        Thread.sleep(3000);
        String tool_val4=tool_value.getText();
        System.out.println(tool_val4);
        //Assert.assertEquals(t4,tool_val4);

}
	public void VerifyTitleText(String Tit, String titText) {       
	           waitForElement(PageTitle, 10);
	            String Title = PageTitle.getText();
	            System.out.println("Page Title is : "+Title);
	            waitForElement(PageTitletext, 10);
	            String TitleText = PageTitletext.getText();
	            System.out.println("The title text  is :" + TitleText);
	            Assert.assertEquals(Tit, Title);
	            Assert.assertEquals(titText,TitleText );
	            

	                   
	                   
	        }


	public void ROIvalues() throws InterruptedException {
		Thread.sleep(10000);
		WebElement Website =driver.findElement(By.xpath("//*[@id='website-clicks']"));
		String Website_Clicks =Website.getText();
		WebElement Directions=driver.findElement(By.xpath("//*[@id=\"total-direction-clicks\"]"));
		String Rquestfor_Directions =Directions.getText();
		WebElement phone=driver.findElement(By.xpath("//*[@id=\"total-phonecalls-clicks\"]"));
		String phone_Calls=phone.getText();
		System.out.println();
		expectedResult.add(Website_Clicks);
		expectedResult.add(Rquestfor_Directions);
		expectedResult.add(phone_Calls);
		
		for(int i=0;i<actualResult.size();i++){
			for(int j=i;j<expectedResult.size();j++){
            if(actualResult.get(j).equals(expectedResult.get(j))){
                System.out.println("Exist : "+expectedResult.get(j));
            }else{
                System.out.println("Not Exist : "+expectedResult.get(j));
            }
            break;
        }
        }
	}
		private Collection<? extends String> Arrays(String string) {
		// TODO Auto-generated method stub
		return null;
	}
		public void val_pass() throws InterruptedException {
		website_Click_Value.clear();
		website_Click_Value.sendKeys("6.0");
		Thread.sleep(2000);
		direction_Click_Value.clear();
		direction_Click_Value.sendKeys("4.0");
		Thread.sleep(2000);
		phone_Click_Value.clear();
		phone_Click_Value.sendKeys("8.0");
		annual_cost.clear();
		annual_cost.sendKeys("2.0");
		Thread.sleep(2000);
		values(website_Click_Value,total_Web, 0);
		Thread.sleep(2000);
		values(direction_Click_Value,total_dir,1);
		Thread.sleep(2000);
		values(phone_Click_Value,total_pho, 2);
		Thread.sleep(2000);
		}
		


	public void values(WebElement Click_val, WebElement total, int i) {
		
		String Website_Clicks_value1=Click_val.getAttribute("value");
		String str_Web=Website_Clicks_value1.replaceAll(",","");

		System.out.println(str_Web);
		double d = Double.parseDouble(str_Web);
		String at=actualResult.get(i).replaceAll(",", "");
		double total_Web=  Double.parseDouble(at);
		String table_Website_value=total.getText();
		String str=table_Website_value.replaceAll(",","");
		System.out.println(str);
		double d1 = Double.parseDouble(str);
		System.out.println(d1);
		calculation(d, total_Web,d1);
		
	}
private void calculation(double d, double total, double d1) {
	System.out.println("executing");
	double ad=d*total;
	Assert.assertEquals(ad, d1);	
	totalResult.add(ad);
	System.out.println("Arr"+totalResult);
	
}

public double to() {
	double sum = 0;

	for(int i = 0; i < totalResult.size(); i++) {
	    sum += totalResult.get(i);
	}
	double s=sum;
	
	System.out.println("Abi total"+s);
	String table_Click_value=total_click.getText();
	String str=table_Click_value.replaceAll(",","");
	System.out.println(str);
	double d1 = Double.parseDouble(str);
	Assert.assertEquals(d1, sum);	
	Return_per_day(sum);
	return sum;
}
public void tot(double sum2) {
	String table_Click_value=total_click.getText();
	String str=table_Click_value.replaceAll(",","");
	System.out.println(str);
	double d1 = Double.parseDouble(str);
	//Assert.assertEquals(d1, sum2);
}
public void Return_per_day(double sum) {
    DecimalFormat df = new DecimalFormat("0.00");

	String lo_count=locations.getText();
	double d1 = Double.parseDouble(lo_count);
	String ad=driver.findElement(By.xpath("//*[@id=\"page-content\"]/div[1]/div[2]/ul/li[1]/div/span")).getText();
	double ad1=Double.parseDouble(ad);
	System.out.println(lo_count+ad);
	double a1=sum/ad1;
	double a2=a1/d1;
	System.out.println(df.format(a2));
	String fin=df.format(a2);
	double ad2=Double.parseDouble(fin);
	String avg_co=avg.getText();
	double d4 = Double.parseDouble(avg_co);
	Assert.assertEquals(d4, ad2);	
}
public void avg() throws InterruptedException {
    DecimalFormat df = new DecimalFormat("0.00");

	String daily1=daily.getText();
	double d3=Double.parseDouble(daily1);
	System.out.println(d3);
	String avg_co=avg.getText();
	String str1=avg_co.replaceAll(",","");
	double d4 = Double.parseDouble(str1);
	double a=d4/d3;
	//double a1=a*100;
	
	double a1 = Math.round(a*100);

    System.out.println("add"+a1);
	String fin=df.format(a1);
	System.out.println("Final"+a1);
	System.out.println(df.format(a1));
	String a11=avg_ROI.getText();
	String a12=a11.replaceAll(",","");

	double d5=Double.parseDouble(a12);
	System.out.println("dou"+d5);
	
	Assert.assertEquals(a1, d5);
	Thread.sleep(1000);
	save_btn.click();
	close.click();
	Thread.sleep(10000);
	
actualResult.removeAll(actualResult);
expectedResult.removeAll(expectedResult);
totalResult.removeAll(totalResult);
Thread.sleep(1000);

	
}

public void sel_options() throws InterruptedException {
	
	scrollByElement(lab);
	Thread.sleep(1000);
	Select listbox = new Select(driver.findElement(By.id("currency-select")));
	listbox.selectByIndex(2);
}

public void Sym_veri() {
	roi_notes.clear();
	roi_notes.sendKeys("Updated");
	save_notes.click();
close.click();
	 List<WebElement> elements = driver.findElements(By.xpath("//span[@class='dollar-sign']"));
	    System.out.println("Number of elements:" +elements.size());

	    for (int i=0; i<elements.size();i++){
	      System.out.println("text:" + elements.get(i).getText().equals("£"));
	     
	    }
	
}
public void selectCalender_FromDate(int day_d, String month_MMM, int year_YYYY) throws ParseException {
	
	if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
		selectCalender_Date(StartDate, day_d, month_MMM, year_YYYY);
	}
}

public void selectCalender_ToDate(int day_d, String month_MMM, int year_YYYY) throws ParseException {
	if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
		selectCalender_Date(EndDate, day_d, month_MMM, year_YYYY);	
	}
}	
public void selectCalender_FromDate1(int day_d, String month_MMM, int year_YYYY) throws ParseException {
	
	if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
		selectCalender_Date(fromDate, day_d, month_MMM, year_YYYY);
	}
}

public void selectCalender_ToDate1(int day_d, String month_MMM, int year_YYYY) throws ParseException {
	if(day_d != 0 | !(month_MMM.equalsIgnoreCase("null")) | year_YYYY != 0 ) {
		selectCalender_Date(toDate, day_d, month_MMM, year_YYYY);	
	}
}	
    private void selectCalender_Date(String calenderField, int day_d, String month_MMM, int year_YYYY) throws java.text.ParseException {
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
        public void Nav_GMB() {
        	clickelement(GMB_page);
}
        public void GMB() {
        	//driver.findElement(By.xpath("//*[@id=\"wm-shoutout-219267\"]/div[3]/div[2]/span")).click();
scrollByElement(cus_web);

String web=cus_web.getText();
System.out.println(web);
actualResult.add(web);
String dir=cus_Dir.getText();
System.out.println(dir);
actualResult.add(dir);
String pho=cus_pho.getText();
System.out.println(pho);
actualResult.add(pho);
System.out.println(actualResult);

}


	@Override
	public List<Map<String, String>> getOverviewReport() {
		// TODO Auto-generated method stub
		return null;
	}
	 public  int getNumberofDays_ROI() throws Exception {
         int diff = 0;
         Date init=new SimpleDateFormat("dd.MM.yyyy").parse(getCurrentfromDate_ROI()); 
	     System.out.println(init);
         Thread.sleep(5000);
         Date enddate=new SimpleDateFormat("dd.MM.yyyy").parse(getCurrenttoDate_ROI());
         System.out.println(enddate);
         Thread.sleep(5000);
         long difference = Math.abs(init.getTime() - enddate.getTime());
         System.out.println("ad"+difference);
         long differenceDates = difference / (24 * 60 * 60 * 1000);
         System.out.println("ad"+differenceDates);

         diff = (int)(long)differenceDates+1;       
         System.out.println("diff"+diff); 
         Com_date(diff);
         return diff;
     }   
    
	 
	 public String getCurrentfromDate_ROI() throws ParseException, java.text.ParseException {
	        String currentfromDate = ((JavascriptExecutor)driver).executeScript("return document.getElementById('startdatepicker').value").toString();
	        System.out.println("Abi"+currentfromDate);
	        return currentfromDate;
	    }
	    public String getCurrenttoDate_ROI() throws ParseException, java.text.ParseException {
	        String currenttoDate = ((JavascriptExecutor)driver).executeScript("return document.getElementById('enddatepicker').value").toString();
	        System.out.println("Abi"+currenttoDate);
	        return currenttoDate;
	        }
	    
	    public void Com_date(int diff) {
	    	String da=dat_count.getText();
	    	System.out.println(da);
	    	int da1=Integer.parseInt(da);
	    	System.out.println("UI"+da1);
	        Assert.assertEquals(diff,da1);

	    	
	    }
}
