package com.dac.testcases.LPAD;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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
public abstract class LaunchLPAD1
{
	public static WebDriver driver;
	public static String UserName, Password, url, LocationDataExcelPath, Browser, AddressFormatExcelPath,NewlocationNumber,Reseller;
	@BeforeSuite
	public void ReadProperties() {
		 System.out.println("Before Test....Reading LPAD Credentials file");
		 
	Browser= "Chrome";
//	url= IAutoconst.LPADUrlBeta;
	url= IAutoconst.TSEE_url;
	UserName=IAutoconst.ResellerAdmin;
	Password=IAutoconst.ResellerPassword;
	LocationDataExcelPath=IAutoconst.LocationDataExcelPath;
	Reseller=IAutoconst.Reseller;
	
	}
	@BeforeTest
    public void LaunchBroswer()
    {
    switch (Browser) {
     
		case "Chrome":
			 WebDriverManager.chromedriver().version("98.0.4758.80").setup();
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
//        ((JavascriptExecutor)driver).executeScript("document.body.style.zoom='80%';");
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
