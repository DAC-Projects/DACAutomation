package com.dac.main.POM_DTC;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

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

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class DTC_Transmission_Setup extends BaseClass {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;


public DTC_Transmission_Setup (WebDriver driver) {
	
	this.driver = driver;
	wait = new WebDriverWait(driver, 30);
	action = new Actions(driver);
	PageFactory.initElements(driver, this);
	}
private By vendor_name(String name) {
    By vendor_name=By.xpath("//*[@id=\"vendorlistTable\"]/tbody/tr/td[contains(text(),'"+name+"')]");
    return vendor_name;
}

private By setup_name(String setupname) {
    By setup_name=By.xpath("//*[@id=\"transmissiontypeTable\"]/tbody/tr/td[contains(text(), '"+setupname+"')]/../td[6]/span[3]");
    return setup_name;
}

private By fieldnew(String filednew) {
	By fieldnew=By.xpath("//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[2]/div[3]/div[1]/div/ul/li/a/span[contains(text(),'"+filednew+"')]");
	return fieldnew;
}

private By fieldnew1(String filednew) {
	By fieldnew=By.xpath("//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[2]/div[3]/div[2]/div/ul/li/a/span[contains(text(),'"+filednew+"')]");
	return fieldnew;
}

private By file_name(String setupname) {
    By file_name=By.xpath("//*[@id=\"filelistTable\"]/tbody/tr/td[2][contains(text(),'"+setupname+"')]/../td[1]");
    return file_name;
}

private By fomat_select(String formatname) {
	By fname=By.xpath("//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[3]/div[2]/div/div/ul/li/a/span[1][contains(text(),'"+formatname+"')]");
			return fname;
}
@FindBy(xpath="//*[@id=\"vendors\"]/div/div/div[2]/div[1]/div[1]")
private WebElement Vendors;

@FindBy(xpath="//*[@id=\"btnnewfield\"]/span")
private WebElement newfield;

@FindBy(xpath="//*[@id=\"txtnewvendorfieldname\"]")
private WebElement vendor_field_name;

@FindBy(xpath="//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[2]/div[3]/div[1]/button/span[1]")
private WebElement DTC_field;
@FindBy(xpath="//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[2]/div[3]/div[2]/button/span[1]")
private WebElement DTC_field_Multi;

@FindBy(xpath="//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[2]/div[3]/div[1]/div/div/input")
private WebElement DTC_filedinput;

@FindBy(xpath="//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[2]/div[3]/div[2]/div/div/input")
private WebElement DTC_fieldinput_multi;

@FindBy(xpath="//*[@id=\"btnfieldmapsave\"]")
private WebElement save;

@FindBy(xpath="//*[@id=\"vendorFieldMappingTable\"]/tbody/tr")
private String table;

@FindBy(xpath="//*[@id=\"btnsavevendorfieldmap\"]")
private WebElement save_pop;

@FindBy(xpath="//*[@id=\"btnduplicatetype\"]")
private WebElement confirm;

@FindBy(xpath="//*[@id=\"modalvendorfieldmapdialog\"]/div/div[1]/button/span")
private WebElement close;

@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[4]/a")
private WebElement transmission;

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

@FindBy(xpath="//input[@id='checkall']")
private WebElement check_Location;

@FindBy(xpath="//input[@id='chkimmediate']")
private WebElement run;

@FindBy(xpath="//button[@id='btnexport']")
private WebElement initiate;

@FindBy(xpath="(//*[@class='btn btn-danger btnalertclose'])[2]")
private WebElement close_tra;

@FindBy(xpath="//button[@class='btn btn-danger btnalertclose']")
private WebElement Close_Trans;

@FindBy(xpath="//*[@class='glyphicon glyphicon-refresh sptbrefresh']")
private WebElement Refresh;

@FindBy(xpath="//*[@id='btnfilter']")
private WebElement Ap_filter;


@FindBy(xpath="//*[@id=\"sidebar\"]/ul/li[4]")
private WebElement mnuTransmission;

@FindBy(xpath="(//*[@class=\"inner-heading innerheadingsubpanel\"])[5]")
private WebElement Manual_Transmission;

@FindBy(xpath="//*[@id='txtrequestId']")
private WebElement requestID;

@FindBy(xpath="//*[@id='btnfiltertrans']")
private WebElement applyFilter;

@FindBy(xpath="//*[@id='transmissionlistTable']/tbody/tr[1]")
private WebElement cli;

@FindBy(xpath="//*[@id=\"transmissionModal\"]/div/div/div[2]/div[1]/div[1]/form/div/div[2]/div/button")
private WebElement vendor;

@FindBy(xpath="//*[@class='btn btn-danger btnalertclose']")
private WebElement close_btn;

@FindBy(xpath="//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[3]/div[2]/div/button/span[1]")
private WebElement format;

@FindBy(xpath="//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[3]/div[2]/div/div/div/input")
private WebElement format_value;

@FindBy(xpath="//*[@id=\"chkmulti\"]")
private WebElement multi;
public void scrollByElement(WebElement element) {
	wait.until(ExpectedConditions.visibilityOf(element));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	int yLoc = element.getLocation().getY() - 10;
	int xLoc = element.getLocation().getX();
	js.executeScript("window.scrollTo(" + xLoc + ", " + yLoc + ")");
}
public void setup(String name, String setupname) throws InterruptedException {
	Vendors.click();
	Thread.sleep(1000);
	driver.findElement(vendor_name(name)).click();
	driver.findElement(setup_name(setupname)).click();
	Thread.sleep(3000);
}
public void Adding_New_filed(String name, String setupname,String field_name, String DTCfield) throws Exception {
	Thread.sleep(3000);
	newfield.click();
	Thread.sleep(2000);
	vendor_field_name.sendKeys(field_name);
	Thread.sleep(3000);
	multi.click();
	Thread.sleep(1000);
	//DTC_field.click();
	
	DTC_field_Multi.click();

	//DTC_filedinput.sendKeys(DTCfield);
	DTC_fieldinput_multi.sendKeys(DTCfield);
	driver.findElement(fieldnew1(DTCfield)).click();
	Thread.sleep(1000);
	DTC_field_Multi.click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[7]/div[2]/div/button/span[1]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"ModalAvailableField\"]/div/div/div[2]/div/div[7]/div[2]/div/div/ul/li[2]/a/span[1]")).click();
	//format.click();
	//format_value.sendKeys(fname);
	Thread.sleep(1000);
	//driver.findElement(fomat_select(fname)).click();
	driver.findElement(By.xpath("//*[@id=\"spanindex17\"][1]")).click();
	Robot robot = new Robot();
	robot.keyPress(KeyEvent.VK_PAGE_DOWN);
	robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	 addEvidence(CurrentState.getDriver(), "Adding new filed", "yes");
	save.click();
	Thread.sleep(3000);
	List<WebElement> actmenu1 = driver.findElements(By.xpath("//*[@id=\"vendorFieldMappingTable\"]/tbody/tr/td[2]"));
	List<WebElement> actmenu2 = driver.findElements(By.xpath("//*[@id=\"vendorFieldMappingTable\"]/tbody/tr/td[3]"));
	System.out.println(actmenu1.size());
	 
	for (int i1 = 0; i1 < actmenu1.size(); i1++) {
	    	 String a1= actmenu1.get(i1).getText();
	    	 String a2=actmenu2.get(i1).getText();
	    	 System.out.println(a1);
	    	 System.out.println(a2);
	    	 Assert.assertEquals(DTCfield, field_name);
	    	 if(a1.equals(field_name)&&a2.equals(DTCfield)) {
	    		 System.out.println("Working");	
}
}
robot.keyPress(KeyEvent.VK_PAGE_DOWN);
robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
save_pop.click();
Thread.sleep(3000);
confirm.click();
Thread.sleep(6000);
//close.click();
}

public void transmission(String LO_number) throws Exception {
	transmission.click();
	Robot robot = new Robot();
	robot.keyPress(KeyEvent.VK_PAGE_UP);
	robot.keyRelease(KeyEvent.VK_PAGE_UP);
	 ExcelHandler wb1 = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
	 driver.findElement(By.xpath("//*[@id=\"transmission\"]/div/div/div[2]/div[1]/div[1]")).click();
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
		Thread.sleep(1000);
		initiate.click();
		Thread.sleep(1000);
		close_tra.click();
		Thread.sleep(6000);
		Close_Trans.click();
		Refresh.click();
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		String re=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[1]")).getText();
		System.out.println(re);   
		int i=0,j=1;
		wb1.setCellValue(0, 0, "Manual_ID");
	    wb1.setCellValue(i, j, re);
		//String ad=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
		//System.out.println(ad);
		//String ad2=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
		//System.out.println(ad2);	
		Thread.sleep(1000);
}

public void verif_Status_complete(String RID, String ven, String filname) throws InterruptedException {
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
	driver.findElement(file_name(filname)).click();

	close_btn.click();
	Thread.sleep(4000);
	String ad12=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[5]")).getText();
	System.out.println(ad12);
	Thread.sleep(4000);
	String ad22=driver.findElement(By.xpath("//*[@id=\"transmissionlistTable\"]/tbody/tr[1]/td[9]")).getText();
	System.out.println(ad22);	
}

}
