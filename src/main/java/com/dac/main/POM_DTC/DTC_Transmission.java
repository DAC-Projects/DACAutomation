package com.dac.main.POM_DTC;
import java.awt.Desktop.Action;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.dac.main.BasePage;

import groovyjarjarasm.asm.ClassReader;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
public class DTC_Transmission extends BaseClass  {
	WebDriver driver;
	ExcelHandler wb1;
	;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[4]")
	private WebElement mnuTransmission;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[5]")
	private WebElement mnuDACCategory;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[6]")
	private WebElement mnuAccountConfig;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[7]")
	private WebElement mnuVendorInfo;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[8]")
	private WebElement mnuReports;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[9]")
	private WebElement mnuFileCenter;
	
	@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[10]")
	private WebElement mnuLaunchpad;

	@FindBy(xpath="//*[@id='duplocationnumber']")
	private WebElement location_number;
	
	@FindBy(xpath="//*[@id='btnfilter5']")
	private WebElement apply;

	@FindBy(xpath="//*[@id='notes']")
	private WebElement Notes;
	
	
	@FindBy(xpath="//*[@id='interalnotes']")
	private WebElement In_Notes;
	@FindBy(xpath="//*[@id=\"duplicatelistTable\"]/tbody/tr[1]/td[6]/span")
	private WebElement status;
	
	@FindBy(xpath="//*[@id='btnDupSubmit']")
	private WebElement Submit;
	
	@FindBy(xpath="//*[@id='btnclrfilter12']")
	private WebElement Clear_button;
	
	@FindBy(xpath="//*[@id='alertModal']/div/div/div[2]")
	private WebElement model;
	
	@FindBy(xpath="(//*[@class='btn btn-danger btnalertclose'])[2]")
	private WebElement close;
	
	
public DTC_Transmission (WebDriver driver) {
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath="//*[@id=\"i0116\"]")
	private WebElement txtLoginName;
	
	@FindBy(xpath="//*[@id='idSIButton9']")
	private WebElement btnSignIn;
	
/*	@FindBy(css="input[value=Sign in]")
	private WebElement btnSignIn;*/
	//*[@id="idSIButton9"]
	@FindBy(css="input[value=Next]")
	private WebElement btnNext;
	
	@FindBy(xpath="//*[@id=\"i0118\"]")
	private WebElement txtPassword;
	
	@FindBy(xpath="/html/body/div/form/div[1]/div/div[1]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div[2]")
	private WebElement btnYes;
	
	@FindBy(xpath="//*[@id=\"idA_PWD_ForgotPassword\"]")
	private WebElement txtFGTPassword;
	
	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[5]")
	private WebElement Manual_Transmission;
	
	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[6]")
	private WebElement API_Transmission;
	
	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[7]")
	private WebElement apple_trans;

	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[8]")
	private WebElement zomato_trans;
	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[9]")
	private WebElement here_trans;
	
	@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[10]")
	private WebElement tomtom_trans;
	
	@FindBy(xpath="//*[@id=\"btnapplerequest\"]")
	private WebElement addrequest_apple;
	@FindBy(xpath="//*[@id=\"btnaddrequest\"]")
	private WebElement addrequest;
	
	@FindBy(xpath="//*[@id='txtlocationno']")
	private WebElement location_num;
	
	@FindBy(xpath="//*[@id='btnvalidatelocation']")
	private WebElement verify_location;
	@FindBy(xpath="//*[@class='btn-group']")
	private WebElement select_Vendor;
	
	@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[2]/div[1]/form/div[1]/div[1]/div")
	private WebElement Se_vendor;
	
	@FindBy(xpath="//*[@id='transmissionModal']/div/div/div[2]/div[2]/div[1]/form/div[1]/div[1]/div/ul/li[1]/a/label/input")
	private WebElement Se_vendor1;
	
	//*[@id="idA_PWD_ForgotPassword"]
	@FindBy(xpath="//*[@id='btnfilter']")
	private WebElement Ap_filter;

	@FindBy(xpath="//input[@id='checkall']")
	private WebElement check_Location;
	
	@FindBy(xpath="//input[@id='chkimmediate']")
	private WebElement run;
	
	@FindBy(xpath="//button[@id='btnexport']")
	private WebElement initiate;
	
	@FindBy(xpath="//button[@class='btnalertclose']")
	private WebElement Close;
	
	@FindBy(xpath="//button[@class='btn btn-danger btnalertclose']")
	private WebElement Close_Trans;
	
	@FindBy(xpath="//*[@class='glyphicon glyphicon-refresh sptbrefresh']")
	private WebElement Refresh;
	
	//*[@id="transmissionlistTable"]/tbody/tr[1]/td[5]
	
	@FindBy(xpath="//*[@id='transmissionlistTable']/tbody/tr[1]/td[5]")
	private WebElement Status;
	
	@FindBy(xpath="//*[@id='transmissionlistTable']/tbody/tr[1]")
	private WebElement cli;
	
	@FindBy(xpath="//*[@id='selctedlistTable']//input[@value='complete']")
	private WebElement checkbox;
	@FindBy(xpath="//*[@id='selctedlistTable']/tbody/tr[2]/td[6]/label[1]/input")
	private WebElement checkbox_cancel3;
		
	@FindBy(xpath="//*[@id='selctedlistTable']//input[@value='cancel']")
	private WebElement checkbox_cancel1;
	
	@FindBy(xpath="//*[@id='selctedlistTable']/tbody/tr[2]/td[6]/label[2]/input")
	private WebElement checkbox_cancel2;
	
	@FindBy(xpath="//*[@id='txtrequestId']")
	private WebElement requestID;
	
	@FindBy(xpath="//*[@id='btnfiltertrans']")
	private WebElement applyFilter;
	
	@FindBy(xpath="//*[@class='btn btn-danger btnalertclose']")
	private WebElement close_btn;
	@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[3]/div[2]/input[1]")
	private WebElement All_complete;
	
	@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[3]/div[2]/input[2]")
	private WebElement All_cancel;
	
	@FindBy(xpath="//*[@id=\"txtlocationnumberggle\"]")
	private WebElement API_LocationNumber;
	
	@FindBy(xpath="//*[@id=\"btnfilterggle\"]")
	private WebElement API_applyfilter;
	
	
	@FindBy(xpath="//*[@id='divappleaccount']/div[2]/div/button/span")
	private WebElement Account;
	
	@FindBy(xpath="//*[@id=\"divzomatoaccount\"]/div[2]/div/button/span")
	private WebElement Account_zomato;
	
	@FindBy(xpath="//*[@id=\"divhereaccount\"]/div[2]/div/button/span")
	private WebElement Account_here;
	
	@FindBy(xpath="//*[@id=\"divappleaccount\"]/div[2]/div/ul/li[1]/div/input")
	private WebElement acc_Input;
	@FindBy(xpath="	//*[@id=\"divzomatoaccount\"]/div[2]/div/ul/li[1]/div/input")
	private WebElement acc_Input_zomato;
	
	@FindBy(xpath="	//*[@id=\"divhereaccount\"]/div[2]/div/ul/li[1]/div/input")
	private WebElement acc_Input_here;
	
	@FindBy(xpath="//*[@id=\"divappleaccount\"]/div[2]/div/ul/li[12]/a/label/input")
	private WebElement acc_Name;
	
	@FindBy(xpath="	//*[@id=\"divzomatoaccount\"]/div[2]/div/ul/li[4]/a/label/input")
	private WebElement acc_Name_zomato;
	
	@FindBy(xpath="//*[@id=\"divhereaccount\"]/div[2]/div/ul/li[9]/a/label/input")
	private WebElement acc_Name_here;
	
	@FindBy(xpath="//*[@id=\"txtscheduletransmission\"]")
	private WebElement schedule_time;
	
	@FindBy(xpath="//*[@id=\"transmissionrequestModal\"]/div/div/div[1]")
	private WebElement cli_pop;
	
	@FindBy(xpath="//*[@id=\"btnexporttransmission\"]")
	private WebElement Ini_apple;
	
	@FindBy(xpath="//*[@id=\"alertModal\"]/div/div/div[3]/button[3]")
	private WebElement close_apple;
	
	@FindBy(xpath="//*[@id=\"transmission\"]/div/div/div[2]/div[3]/div[2]/div/span")
	private WebElement Apple_refresh;
	
	@FindBy(xpath="//*[@id=\"appleTransmissionlistTable\"]/tbody/tr[1]/td[3]")
	private WebElement Apple_sta;
	
	@FindBy(xpath="//*[@id=\"appleTransmissionlistTable\"]/tbody/tr[1]/td[8]/a[1]")
	private WebElement Apple_dow;
	
	@FindBy(xpath="//*[@id=\"btnzomatorequest\"]")
	private WebElement addrequest_zomato;
	
	@FindBy(xpath="//*[@id='btnhererequest']")
	private WebElement addrequest_here;
	
	@FindBy(xpath="//*[@id=\"tomtombtnaddparam\"]")
	private WebElement tomtom_create;
	
	@FindBy(xpath="//*[@id=\"txttomtomparamname\"]")
	private WebElement tomtom_para;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/button")
	private WebElement tomtom_ven;
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/ul/li/a/label/input")
	private WebElement tomtom_selectvendor;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[1]/div/button")
	private WebElement tomtom_reseller;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[1]/div/ul/li[1]/div/input")
	private WebElement tomtom_resellername;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[1]/div/ul/li[128]/a/label/input")
	private WebElement tomtom_selectresller;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[2]/div/button")
	private WebElement tomtom_Account;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[2]/div/ul/li[1]/div/input")
	private WebElement tomtom_AccountName;
	
	@FindBy(xpath="//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[2]/div/ul/li[2]/a/label/input")
    private WebElement tomtom_selectAccount;	
	
	@FindBy(xpath="//*[@id=\"txttomtomparamlocationno\"]")
	private WebElement locationnumber;
	
	@FindBy(xpath="//*[@id=\"btnmodaltomtomparamfilter\"]")
	private WebElement apply_Filter;
	
	@FindBy(xpath="//*[@id=\"tomtomparamcheckall\"]")
	private WebElement tomtom_check;
	
	@FindBy(xpath="//*[@id=\"btnmodaltomtomsaveparam\"]")
	private WebElement tomtom_run;
	
	@FindBy(xpath="//*[@id=\"alertModal\"]/div/div/div[3]/button[3]")
	private WebElement tomtom_close;
	
	@FindBy(xpath="//*[@id=\"tomtomnamefilter\"]")
	private WebElement tomtom_paraname;
	
	@FindBy(xpath="//*[@id='tomtombtnfilter']")
	private WebElement tomtom_UIapply;
	
	@FindBy(xpath="//*[@id=\"tomtomreportparamTable\"]")
	private WebElement tomtom_table;
	
	@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[1]/form/div/div[2]/div/button")
	private WebElement vendor;
	
	@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[1]/form/div/div[2]/div/ul/li[1]/div/input")
	private WebElement vendor_search;
	
	@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[1]/form/div/div[2]/div/ul/li/a/label")
	private WebElement vendor_name;
	
	public void EnterLogin(String name) {
		txtLoginName.sendKeys(name);
		txtLoginName.sendKeys(Keys.CONTROL, Keys.ENTER);
		
	}
	  private By file(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[8]/a[1]");
	        return apple_file;
	    }
	  private By file_date(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]/../td[9]");
	        return apple_file;
	    }
	  private By file_status(String id) {
	        By apple_file=By.xpath("//td[text()='"+id+"']/../td[3]");
	        return apple_file;
	    }
	public void EnterPassword(String pwd) {
		txtPassword.sendKeys(Keys.CONTROL,Keys.CLEAR);
		txtPassword.sendKeys(pwd);
		txtPassword.sendKeys(Keys.CONTROL, Keys.TAB);
		txtFGTPassword.sendKeys(Keys.CONTROL, Keys.TAB);
		btnSignIn.sendKeys(Keys.RETURN);
		System.out.println("Click on sign in after password enter");

	}
	/*public void Next() {
		btnNext.click();
	}*/
	public void submitLogin(String name, String pwd) {
		EnterLogin(name);
		EnterPassword(pwd);
}
	public void pressYesKey() {
		btnSignIn.sendKeys(Keys.RETURN);
	}
	

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
		
	public void Transmission( ) throws Exception {
		System.out.println("Transmission");
		mnuTransmission.click();
		Manual_Transmission.click();
		Thread.sleep(2000);
		}
	
	public void manual_Transmission(String LO_number, int i, int j) throws Exception{
	    wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		addrequest.click();
		Thread.sleep(1000);
		location_num.sendKeys(LO_number);
		Thread.sleep(1000);
		verify_location.click();
		Thread.sleep(1000);
		Se_vendor.click();
		Thread.sleep(1000);
		Se_vendor1.click();
		Thread.sleep(1000);
		Se_vendor.click();
		Thread.sleep(1000);
		Ap_filter.click();
		Thread.sleep(20000);
		check_Location.click();
		Thread.sleep(1000);
		run.click();
		addEvidence(CurrentState.getDriver(), "Manual", "yes");
		Thread.sleep(1000);
		initiate.click();
		Thread.sleep(1000);
		close.click();
		Thread.sleep(6000);
		Close_Trans.click();
		Refresh.click();
		Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        String re=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[1]")).getText();
        System.out.println(re);      
		wb1.setCellValue(0, 0, "Manual_ID");
	    wb1.setCellValue(i, j, re);
		//String ad=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		//System.out.println(ad);
		//String ad2=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		//System.out.println(ad2);	
		Thread.sleep(1000);
	}
	
	public void API_Transmission() throws Exception {
		wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		System.out.println("Transmission");	
		mnuTransmission.click();
		API_Transmission.click();
		Thread.sleep(2000);
	}
		
	public void API_Transmission_vendor(String LO_number, String vendor) throws Exception {
		wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		System.out.println("Transmission");	
        driver.findElement(By.xpath("//*[@id=\"btnclrfilterggle\"]")).click();		Thread.sleep(2000);
		API_LocationNumber.sendKeys(LO_number);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[2]/div[2]/div[1]/div/form/div[3]/div[1]/div/button/span[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[2]/div[2]/div[1]/div/form/div[3]/div[1]/div/div/ul/li/a/span[contains(text(), '"+vendor+"')]")).click();
		Thread.sleep(2000);
		API_applyfilter.click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		String ad=driver.findElement(By.xpath("//*[@id='ggletransmissionlistTable']/tbody/tr[1]/td[9]")).getText();
		Thread.sleep(1000);
		String da=driver.findElement(By.xpath("//*[@id=\"ggletransmissionlistTable\"]/tbody/tr/td[11]")).getText();
		
		
		System.out.println("Date"+da);
		if(vendor.equals("Google")) {
		driver.findElement(By.xpath("//*[@id=\"ggletransmissionlistTable\"]/tbody/tr/td[9]/span")).click();
		Thread.sleep(1000);
		String g=driver.findElement(By.xpath("//*[@id=\"alertModal\"]/div/div/div[2]")).getText();
		System.out.println("abi"+g);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"alertModal\"]/div/div/div[3]/button[3]")).click();
		}
		System.out.println(ad);	
		int i=1;
		int j=5;
		wb1.setCellValue(0, 5, vendor);
	    wb1.setCellValue(i, j, ad);
		Thread.sleep(1000);
	}

	public void apple_Trans(String account) throws Exception {
	    wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		System.out.println("Transmission");	
		mnuTransmission.click();
		Thread.sleep(2000);
		apple_trans.click();
		Thread.sleep(4000);
		Apple_refresh.click();
		Thread.sleep(2000);
		String sta=driver.findElement(By.xpath("//*[@id=\"appleTransmissionlistTable\"]/tbody/tr[1]/td[3]")).getText();
		System.out.println(sta);
		addrequest_apple.click();
		Thread.sleep(2000);
		Account.click();
		Thread.sleep(2000);
		acc_Input.sendKeys(account);
		Thread.sleep(2000);
		//acc_Name.click();
		driver.findElement(By.xpath("//*[@id=\"divappleaccount\"]/div[2]/div/ul/li/a/label[contains(text(),'"+account+"')]")).click();
		Thread.sleep(2000);
		Account.click();
		Thread.sleep(2000);
		schedule_time.click();
		addEvidence(CurrentState.getDriver(), "Apple", "yes");
		Thread.sleep(2000);
		cli_pop.click();
		Thread.sleep(2000);
		Ini_apple.click();
		Thread.sleep(2000);
		close_apple.click();
		Thread.sleep(1000);
		String na=driver.findElement(By.xpath("//*[@id=\"appleTransmissionlistTable\"]/tbody/tr[1]/td[1]")).getText();
		System.out.println(na);
		int i=1;
		int j=1;
		wb1.setCellValue(0, 1, "Apple_Request_ID");
	    wb1.setCellValue(i, j, na);
		Thread.sleep(1000);
		apple_trans.click();}
	
	public void verify_apple(String LO_number, String id) throws Exception {
		String a="Complete";
		mnuTransmission.click();
		Thread.sleep(2000);
		apple_trans.click();
		Apple_refresh.click();
		Thread.sleep(2000);
		//Apple_dow.click();
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file(id)).click();
		Thread.sleep(5000);
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Apple request", "yes");
		String splitBy = ",";
		String fname="./downloads/" + filname;
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line = br.readLine();
		String myList="null";
		while ((line = br.readLine()) !=null){
          String[] b = line.split(splitBy); 
          myList=b[1];
        }
		System.out.println(myList);
		String ad1=LO_number;
		myList = myList.replaceAll("^\"|\"$", "");
		System.out.println(myList);
		Assert.assertEquals(myList, ad1);
		br.close();
		apple_trans.click();
	}

	public void zoamto_Trans(String account) throws Exception {
	    wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		System.out.println("Transmission");	
		mnuTransmission.click();
		Thread.sleep(2000);
		zomato_trans.click();
		Thread.sleep(4000);
		addrequest_zomato.click();
		Thread.sleep(2000);
		Account_zomato.click();
		Thread.sleep(2000);
		acc_Input_zomato.sendKeys(account);
		Thread.sleep(2000);
		//acc_Name_zomato.click();
		driver.findElement(By.xpath("//*[@id=\"divzomatoaccount\"]/div[2]/div/ul/li/a/label[contains(text(),'"+account+"')]")).click();
		Thread.sleep(2000);
		Account_zomato.click();
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Zomato", "yes");
		schedule_time.click();
		Thread.sleep(2000);
		cli_pop.click();
		Thread.sleep(2000);
		Ini_apple.click();
		Thread.sleep(2000);
		close_apple.click();
		Thread.sleep(2000);
		String na=driver.findElement(By.xpath("//*[@id=\"zomatoTransmissionlistTable\"]/tbody/tr[1]/td[1]")).getText();
		System.out.println(na);
		int i=1;
		int j=2;
		wb1.setCellValue(0, 2, "Zomato_Request_ID");
	    wb1.setCellValue(i, j, na);
		zomato_trans.click();
	  }


	public void verify_zomato(String LO_number, String id) throws Exception {
		String a="Complete";
		System.out.println("Transmission");	
		Thread.sleep(2000);
		zomato_trans.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[4]/div[2]/div/span")).click();
		Thread.sleep(2000);
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		Assert.assertEquals(a, ad1q);
		driver.findElement(file(id)).click();
		Thread.sleep(1000);		
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		addEvidence(CurrentState.getDriver(), "Zomato request", "yes");
		String fname="./downloads/" + filname;
		String splitBy = ",";
		BufferedReader br = new BufferedReader(new FileReader(fname));
		String line = br.readLine();
		//List<String> wordList = new ArrayList<String>(); 
		ArrayList<String> wordList1 = new ArrayList<String>(); 
		while ((line = br.readLine()) !=null){
        String[] b = line.split(splitBy); 
        wordList1.add(b[1]);
		}	
				 System.out.println("Print"+wordList1);  
				 String ad1=LO_number; 
				 for (String number : wordList1) {
					 number=number.replaceAll("^\"|\"$", "");
					 if(number.equals(ad1)) {
						Assert.assertEquals(number, ad1);
						 System.out.println("Both are same");
						 break;
		}
					else {
						System.out.println("Location not found");}
				 		}
				    br.close();
				 zomato_trans.click();
	}
	
	public void Here_Trans( String account) throws Exception {
		String a="Complete";
		System.out.println("Transmission");	
		mnuTransmission.click();
		Thread.sleep(2000);
		here_trans.click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[5]/div[2]/div/span")).click();
		String te=driver.findElement(By.xpath("//*[@id=\"hereTransmissionlistTable\"]/tbody/tr[1]/td[3]")).getText();
		System.out.println(te);
		//Assert.assertEquals(te,a);
		Thread.sleep(1000);
		addrequest_here.click();
		Thread.sleep(2000);
		Account_here.click();
		Thread.sleep(2000);
		acc_Input_here.sendKeys(account);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"divhereaccount\"]/div[2]/div/ul/li/a/label[contains(text(),'"+account+"')]")).click();
//		acc_Name_here.click();
		Thread.sleep(2000);
		Account_here.click();
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Here", "yes");
		schedule_time.click();
		Thread.sleep(2000);
		cli_pop.click();
		Thread.sleep(2000);
		Ini_apple.click();
		Thread.sleep(2000);
		close_apple.click();
		Thread.sleep(2000);
		String na=driver.findElement(By.xpath("//*[@id=\"hereTransmissionlistTable\"]/tbody/tr[1]/td[1]")).getText();
		System.out.println(na);
		int i=1;
		int j=3;
		wb1.setCellValue(0, 3, "Here_Request_ID");
	    wb1.setCellValue(i, j, na);
		here_trans.click();
	  }
	public void verify_here(String id,String location_name) throws Exception {
		System.out.println("Transmission");	
		mnuTransmission.click();
		Thread.sleep(2000);
		here_trans.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[5]/div[2]/div/span")).click();
		//driver.findElement(By.xpath("//*[@id=\"hereTransmissionlistTable\"]/tbody/tr[2]/td[8]/a")).click();
		String ad1q=driver.findElement(file_status(id)).getText();
		System.out.println(ad1q);
		driver.findElement(file(id)).click();
		Thread.sleep(1000);	
		Thread.sleep(3000);		
		String filname = BasePage.getLastModifiedFile("./downloads");
		System.out.println(filname);
		String name=filname.substring(0,13);
		System.out.println("Abi"+name);
		addEvidence(CurrentState.getDriver(), "HERE request", "yes");
		String fname = "./downloads/"+filname;
		System.out.println(fname);
		ArrayList<String> wordList1 = new ArrayList<String>(); 
		ExcelHandler wb = new ExcelHandler(fname, filname); 
		wb.deleteEmptyRows();
		for(int i=1;i<=wb.getRowCount();i++) {
			String LO_number1 = wb.getCellValue(i, wb.seacrh_pattern("Primary Place Name", 0).get(0).intValue());
			wordList1.add(LO_number1);
		}
		System.out.println(wordList1);
					String ad1=location_name;
					 for (String number : wordList1) {
						 number=number.replaceAll("^\"|\"$", "");
						 if(number.equals(ad1)) {
							 Assert.assertEquals(number, ad1);
							 System.out.println("Both are same");
							 break;
			}
						else {
							System.out.println("Location not found");}
					 }
						here_trans.click();
	}
	
	public  String time_Stamp;	
	public void Tomtom_Trans(String LO_number, String account, String reseller, String parameter) throws Exception {
	    wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
		time_Stamp=timeStamp();
		System.out.println("Getting time"+time_Stamp);
		System.out.println("Transmission");	
		mnuTransmission.click();
		Thread.sleep(2000);
		tomtom_trans.click();
		Thread.sleep(1000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,200)");
		tomtom_create.click();
		Thread.sleep(1000);
		tomtom_para.sendKeys(parameter+time_Stamp);
		tomtom_ven.click();
		Thread.sleep(1000);
		tomtom_selectvendor.click();
		Thread.sleep(1000);
		tomtom_ven.click();
		Thread.sleep(1000);
		tomtom_reseller.click();
		tomtom_resellername.sendKeys(reseller);
		//tomtom_selectresller.click();
		driver.findElement(By.xpath("//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[1]/div/ul/li/a/label[contains(text(),'"+reseller+"')]")).click();
		tomtom_reseller.click();
		tomtom_Account.click();
		tomtom_AccountName.sendKeys(account);
		driver.findElement(By.xpath("//*[@id=\"modaltomtomReportParam\"]/div/div/div[2]/div/div[1]/form/div[2]/div[2]/div/ul/li/a/label[contains(text(),'"+account+"')]")).click();
		//tomtom_selectAccount.click();
		tomtom_Account.click();
		locationnumber.sendKeys(LO_number);
		apply_Filter.click();
		Thread.sleep(2000);
		tomtom_check.click();
		Thread.sleep(1000);
		addEvidence(CurrentState.getDriver(), "Tomtom", "yes");
		tomtom_run.click();
		Thread.sleep(1000);
		tomtom_close.click();
		Thread.sleep(2000);
		tomtom_UIapply.click();
		Thread.sleep(3000);
		String ad=driver.findElement(By.xpath("//*[@id=\"tomtomreportparamTable\"]/tbody/tr[1]/td[1]")).getText();
		System.out.println(ad);
		int i=1;
		int j=4;
		wb1.setCellValue(0, 4, "tomtom_Request_ID");
	    wb1.setCellValue(i, j, ad);
		tomtom_trans.click();
		}	
	
	
	public String timeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		System.out.println("Abi Time"+formattedDate);
		return formattedDate;
		}
	
public void verify_tomtom(String LO_number, String tomtom_RqID) throws Exception {
	System.out.println("Transmission");	
	mnuTransmission.click();
	Thread.sleep(2000);
	tomtom_trans.click();
	Thread.sleep(1000);
	 JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,200)");
	tomtom_paraname.sendKeys(tomtom_RqID);
	Thread.sleep(1000);
	tomtom_UIapply.click();
	Thread.sleep(3000);
	List<WebElement> listOfRows = tomtom_table.findElements(By.tagName("tr"));
	System.out.println("Rows: "+listOfRows.size());
	for(int i=1;i<listOfRows.size();i++) {
	String ad=driver.findElement(By.xpath("//*[@id=\"tomtomreportparamTable\"]/tbody/tr[" + i + "]/td[1]")).getText();
	System.out.println(ad);
	if(ad.equals(tomtom_RqID)) {
		String st=driver.findElement(By.xpath("//*[@id=\"tomtomreportparamTable\"]/tbody/tr["+i+"]/td[5]")).getText();
		System.out.println(st);
		String status="Complete";
		Assert.assertEquals(st, status);
		driver.findElement(By.xpath("//*[@id=\"tomtomreportparamTable\"]/tbody/tr[" + i + "]/td[8]/span[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"reportfilelistTable\"]/tbody/tr/td[1]/a")).click();
		Thread.sleep(2000);
		}}
	addEvidence(CurrentState.getDriver(), "tomtom_request", "yes");

	String filname = BasePage.getLastModifiedFile("./downloads");
	System.out.println(filname);
    String fname="./downloads/"+filname;
    System.out.println(fname);
    String ak=filname.replace(".xlsx", "");
	System.out.println(ak);
	ExcelHandler a = new ExcelHandler(fname, ak); 
	System.out.println(a.getRowCount());
	for(int j=1;j<=a.getRowCount();j++) {
		String LO_number1  = a.getCellValue(j, a.seacrh_pattern("Location No", 0).get(0).intValue());
		LO_number1=LO_number1.replaceAll("^\"|\"$", "");
		System.out.println(LO_number1);
		String ad1=LO_number;
		 Assert.assertEquals(LO_number, ad1);
		 }	
	driver.findElement(By.xpath("//*[@id=\"modalClientReportDownload\"]/div/div/div[3]/button")).click();
	Thread.sleep(1000);
}


public static String getLastModifiedFile(String dirPath)
		throws InterruptedException {
	Thread.sleep(4000);
	File dir = new File(dirPath);
	File[] files = dir.listFiles();
	if (files == null || files.length == 0) {
		return null;
	}

	File lastModifiedFile = files[0];
	for (int i = 1; i < files.length; i++) {
		if (lastModifiedFile.lastModified() < files[i].lastModified()) {
			lastModifiedFile = files[i];
		}
	}
	return lastModifiedFile.getName();
}
	
	public void verif_Status_complete(String RID, String ven) throws InterruptedException {
		String a="Complete";
		Thread.sleep(3000);
		System.out.println("Transmission");
		mnuTransmission.click();
		Manual_Transmission.click();
		Thread.sleep(2000);
		requestID.sendKeys(RID);
		Thread.sleep(1000);
		applyFilter.click();
		Thread.sleep(2000);
		String ad1=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		System.out.println(ad1);
		//Assert.assertEquals(ad1, a);
		String ad2=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		System.out.println(ad2);
		cli.click();
		Thread.sleep(4000);
		vendor.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[1]/form/div/div[2]/div/ul/li/a/label[contains(text(),'"+ven+"')]")).click();
		Thread.sleep(1000);
		//vendor_name.click();
		Thread.sleep(1000);
		vendor.click();
		Thread.sleep(10000);
		List<WebElement> els = driver.findElements(By.xpath("//*[@id='selctedlistTable']//label[text()='Complete']"));
		for ( WebElement el : els ) {
		    if (!el.isSelected()) {
		        el.click(); }}
		Thread.sleep(10000);
		close_btn.click();
		Thread.sleep(4000);
		String ad12=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		System.out.println(ad12);
		Thread.sleep(4000);
		String ad22=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		System.out.println(ad22);	
	}
	public void verif_Status_cancel(String RID) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Transmission");
		driver.findElement(By.xpath("//*[@id=\"btnclrfiltertrans\"]")).click();
		Thread.sleep(2000);
		requestID.sendKeys(RID);
		Thread.sleep(1000);
		applyFilter.click();
		Thread.sleep(6000);
		String ad1=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		System.out.println(ad1);
		String ad2=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		System.out.println(ad2);
		Thread.sleep(10000);
		cli.click();
		Thread.sleep(3000);
		List<WebElement> els = driver.findElements(By.xpath("//*[@id='selctedlistTable']//label[text()='Cancel']"));
		for ( WebElement el : els ) {
		    if ( !el.isSelected() ) {
		        el.click();
		    }}
		Thread.sleep(10000);
		close_btn.click();
		Thread.sleep(4000);
		String ad12=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		System.out.println(ad12);
		Thread.sleep(4000);
		String ad22=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		System.out.println(ad22);	
		}
	public void verif_Status_allcomplete(String RID) throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Transmission");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"btnclrfiltertrans\"]")).click();
		Thread.sleep(4000);
		requestID.sendKeys(RID);
		Thread.sleep(2000);
		applyFilter.click();
		Thread.sleep(4000);
		String ad1=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		System.out.println(ad1);
		String ad2=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		System.out.println(ad2);
		cli.click();
		Thread.sleep(10000); JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,200)");
	    Thread.sleep(1000);
		All_complete.click();
		Thread.sleep(10000);
		close_btn.click();
		Thread.sleep(4000);
		String ad12=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		System.out.println(ad12);
		String ad22=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		System.out.println(ad22);	
	}
	public void verif_Status_allcancel(String RID) throws InterruptedException {
	Thread.sleep(3000);
	System.out.println("Transmission");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"btnclrfiltertrans\"]")).click();
	Thread.sleep(4000);
	requestID.sendKeys(RID);
	Thread.sleep(4000);
	applyFilter.click();
	Thread.sleep(4000);
	String ad1=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
	System.out.println(ad1);
	String ad2=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
	System.out.println(ad2);
	cli.click();
	Thread.sleep(10000);
	All_cancel.click();
	Thread.sleep(10000);
	close_btn.click();
	Thread.sleep(4000);
	String ad12=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
	System.out.println(ad12);
	String ad22=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
	System.out.println(ad22);	
  }
 public void apple_date(String id) throws Exception {
	mnuTransmission.click();
	Thread.sleep(2000);
	apple_trans.click();
	Apple_refresh.click();
	addEvidence(CurrentState.getDriver(), "Apple", "yes");
	Thread.sleep(2000);
	driver.findElement(file_date(id)).click();
	driver.findElement(By.xpath("//*[@id=\"btnSubmitConfirmSubmit\"]")).click();
	Thread.sleep(1000);
}
 public void zomato_date(String id) throws Exception {
	mnuTransmission.click();
	Thread.sleep(2000);
	zomato_trans.click();
	Thread.sleep(2000);
	addEvidence(CurrentState.getDriver(), "Zomato", "yes");
	driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[4]/div[2]/div/span")).click();
	Thread.sleep(2000);
	driver.findElement(file_date(id)).click();
	driver.findElement(By.xpath("//*[@id=\"btnSubmitConfirmSubmit\"]")).click();
}
public void here_date(String id) throws Exception {
	mnuTransmission.click();
	Thread.sleep(2000);
	here_trans.click();
	Thread.sleep(1000);
	addEvidence(CurrentState.getDriver(), "Here", "yes");
	driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[5]/div[2]/div/span")).click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,200)");
	Thread.sleep(2000);
	driver.findElement(file_date(id)).click();
}
public void tomtom_date(String id) throws Exception {
	System.out.println("Transmission");	
	mnuTransmission.click();
	Thread.sleep(2000);
	tomtom_trans.click();
	Thread.sleep(1000);
	 JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,200)");
	addEvidence(CurrentState.getDriver(), "tomtom", "yes");
    ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
	String tomtom_RqID = wb.getCellValue(1, wb.seacrh_pattern("tomtom_Request_ID", 0).get(0).intValue());
	tomtom_paraname.sendKeys(tomtom_RqID);
	Thread.sleep(1000);
	tomtom_UIapply.click();
	Thread.sleep(1000);
	List<WebElement> listOfRows = tomtom_table.findElements(By.tagName("tr"));
	System.out.println("Rows: "+listOfRows.size());
	for(int i=1;i<listOfRows.size();i++) {
	String ad=driver.findElement(By.xpath("//*[@id=\"tomtomreportparamTable\"]/tbody/tr[" + i + "]/td[1]")).getText();
	System.out.println(ad);
	if(ad.equals(tomtom_RqID)) {
	driver.findElement(By.xpath("//*[@id=\"tomtomreportparamTable\"]/tbody/tr[" + i + "]/td[9]")).click();
		Thread.sleep(2000);
		}}
}}
	

