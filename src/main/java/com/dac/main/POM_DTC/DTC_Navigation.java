package com.dac.main.POM_DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import resources.ExcelHandler;

public class DTC_Navigation {
	WebDriver driver;
	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[1]")
	private WebElement mnuVendors;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[2]")
	private WebElement mnuFeatureOrder;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[3]")
	private WebElement mnuTransformation;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[4]")
	private WebElement mnuTransmission;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[5]")
	private WebElement mnuDACCategory;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[6]")
	private WebElement mnuAccountConfig;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[7]")
	private WebElement mnuVendorInfo;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[8]")
	private WebElement mnuReports;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[9]")
	private WebElement mnuFileCenter;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/ul/li[10]")
	private WebElement mnuLaunchpad;

	@FindBy(xpath = "//*[@id='duplocationnumber']")
	private WebElement location_number;

	@FindBy(xpath = "//*[@id='btnfilter5']")
	private WebElement apply;

	@FindBy(xpath = "//*[@id='notes']")
	private WebElement Notes;

	@FindBy(xpath = "//*[@id='interalnotes']")
	private WebElement In_Notes;
	@FindBy(xpath = "//*[@id=\"duplicatelistTable\"]/tbody/tr[1]/td[6]/span")
	private WebElement status;

	@FindBy(xpath = "//*[@id='btnDupSubmit']")
	private WebElement Submit;

	@FindBy(xpath = "//*[@id='btnclrfilter12']")
	private WebElement Clear_button;

	@FindBy(xpath = "//*[@id='alertModal']/div/div/div[2]")
	private WebElement model;

	@FindBy(xpath = "(//*[@class='btn btn-danger btnalertclose'])[2]")
	private WebElement close;

	WebDriverWait wait;
	
	public DTC_Navigation(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);

	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public void navigateVendors() {
		mnuVendors.click();
	}

	public void navigateFeatureOrder() {
		mnuFeatureOrder.click();
	}

	public void navigateTransformation() {
		mnuTransformation.click();
	}

	public void navigateTransmission() {
		mnuTransmission.click();
	}

	public void navigateDACCategory() {
		mnuDACCategory.click();
	}

	public void navigateAccountConfig() {
		mnuAccountConfig.click();
	}

	public void navigateVendorInfo() {
		mnuVendorInfo.click();
	}

	public void navigateReports() {
		mnuReports.click();
	}

	public void navigateFileCenter() {
		mnuFileCenter.click();
	}

	public void navigateLaunchpad(String lo_Number, String va, String EX_Notes, String Internal_Notes)
			throws Exception {
		String text = "This duplicate listing has been submitted already, you cannot change status to [New] or [In Progress].";
		String text1 = "Internal Server Error";
		String lo = String.valueOf(lo_Number);
		location_number.sendKeys(lo);
		apply.click();
		Thread.sleep(5000);
		status.click();
		Thread.sleep(5000);
		Select dropdown1 = new Select(driver.findElement(By.id("Dupddl")));
		System.out.println("test" + va);
		dropdown1.selectByVisibleText(va);
		Thread.sleep(5000);
		In_Notes.sendKeys(Internal_Notes);
		Thread.sleep(5000);

		Notes.sendKeys(EX_Notes);
		Submit.click();
		Thread.sleep(5000);
		if (va.equals("New") || va.equals("In Progress")) {
			System.out.println("executing");
			if (model.isDisplayed()) {
				String[] model_message = model.getText().split("\n");
				System.out.println(model_message[0]);
				System.out.println(model_message[1]);

				Assert.assertEquals(model_message[1], text);
				Assert.assertEquals(model_message[0], text1);
				close.click();
			} else {
				System.out.println("No Model Displayed");
			}
		} else {
			String St = status.getText();
			System.out.println("Abi" + St);
			System.out.println(va.equalsIgnoreCase(St));
		}
		Clear_button.click();
	}

	public void Dup() {
		System.out.println("Duplicate dashboard");
		mnuLaunchpad.click();
	}

	public void excel(int i,String time_Stamp) throws Exception {
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/Message.xlsx", "LONO");
		wb.deleteEmptyRows();
		try {
			System.out.println(wb.getRowCount());
			System.out.println(
					"*******************  Scenarios for Content " + count + "Starts ****************************");
			String lo_Number = wb.getCellValue(i, wb.seacrh_pattern("location_Number", 0).get(0).intValue());
			String Se_Status = wb.getCellValue(i, wb.seacrh_pattern("Status", 0).get(0).intValue());
			//String EX_Notes = wb.getCellValue(i, wb.seacrh_pattern("External Notes", 0).get(0).intValue());
			String Internal_Notes = wb.getCellValue(i, wb.seacrh_pattern("Internal Notes", 0).get(0).intValue());
			System.out.println(lo_Number);
			System.out.println(Se_Status);
			//System.out.println(EX_Notes);
			System.out.println(Internal_Notes);
			navigateLaunchpad(lo_Number, Se_Status, time_Stamp, Internal_Notes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	int before_Ignore;
    public void Count_check(String lo_Number) throws InterruptedException {
        String lo=String.valueOf(lo_Number);
        location_number.sendKeys(lo);
        apply.click();
        Thread.sleep(5000);
        WebElement UICount = driver.findElement(By.xpath("//*[@id='duplicatelistTable_info']"));
        before_Ignore =  NumOfentries(UICount);
        System.out.println("Count before ignore is :" +before_Ignore);
       /* String ad=driver.findElement(By.xpath("//*[@id=\"duplicatelistTable_info\"]")).getText();
        String ad1=ad.substring(19, 21);
        i=Integer.parseInt(ad1);  
        System.out.println(i);
        System.out.println("abi"+ad1);*/
        Thread.sleep(1000);
        Clear_button.click();
    }
    
    public void Count_check_ignore(String lo_Number) throws InterruptedException {
        String lo=String.valueOf(lo_Number);
        location_number.sendKeys(lo);
        apply.click();
        Thread.sleep(5000);
        WebElement UICount = driver.findElement(By.xpath("//*[@id='duplicatelistTable_info']"));
        int after_ignore =  NumOfentries(UICount);
        System.out.println("Count After ignoring :" +after_ignore);
        /*String ad=driver.findElement(By.xpath("//*[@id=\"duplicatelistTable_info\"]")).getText();
        String ad1=ad.substring(19, 21);
        int j=Integer.parseInt(ad1)-1;  
        System.out.println(j);*/
        Assert.assertEquals(before_Ignore - 1, after_ignore);   
    }
    
    public int NumOfentries(WebElement entry) {
    	wait.until(ExpectedConditions.visibilityOf(entry));
		if(entry.isDisplayed()) {
		String entiresText = entry.getText();
		System.out.println("The total entries in a table is :" + entiresText);
		String result = entiresText.substring(entiresText.indexOf("f") + 1, entiresText.indexOf("s") - 6).trim();
		int finalvalue = Integer.parseInt(result);
		System.out.println("The number of entries is : " +finalvalue);
		return finalvalue;
		}else {
			System.out.println("No Data available");
			return 0;
		}		
	}
}
