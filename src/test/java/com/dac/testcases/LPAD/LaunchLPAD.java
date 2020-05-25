package com.dac.testcases.LPAD;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


import io.github.bonigarcia.wdm.WebDriverManager;
import resources.IAutoconst;




/**
 * Unit test for simple App.
 */
public abstract class LaunchLPAD
{
	public static WebDriver driver;
	public static String UserName, Password, url, LocationDataExcelPath, Browser, AddressFormatExcelPath,locationNumber;
	@BeforeSuite
	public void ReadProperties() {
		 System.out.println("Before Test....Reading LPAD Credentials file");
		 
	Browser= "Chrome";
	url= IAutoconst.LPADUrlBeta;
	UserName=IAutoconst.ResellerAdmin;
	Password=IAutoconst.ResellerPassword;
	LocationDataExcelPath=IAutoconst.LocationDataExcelPath;
	
	}
	@BeforeTest
    public void LaunchBroswer()
    {
    switch (Browser) {
     
		case "Chrome":
			 WebDriverManager.chromedriver().version("80.0.3987.16").setup();
			 driver=new ChromeDriver();
//	    	 System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
	    	 break;
		case "Firefox":
			 System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			 driver=new FirefoxDriver();
			 break;
		case "IE":
			System.setProperty("webdriver.ie.driver","./Driver/IEDriverServer.exe");
			InternetExplorerDriver driver;
			driver=new InternetExplorerDriver();
			break;

	default:
		break;
	}
		        
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("LPAD Portal Launched....");
     }
	
	/*@BeforeClass
	public void test() {
		System.out.println("Before Class");
	}
	*/
@AfterTest
	public void CloseBrowser() {
	System.out.println("After Test- Test Completed");
//	driver.close();
 }

	
}
