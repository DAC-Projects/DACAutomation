package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.reporter.*;
import com.beust.jcommander.Parameter;

public abstract class BaseTest implements IAutoconst {

	static {
		System.setProperty(IE_KEY, IE_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		System.setProperty(CHROME_KEY, CHROME_VALUE);
	}

	public static WebDriver driver;
	public Properties prop;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentTest parent;

	//****************************Extent report
	
	@BeforeSuite(alwaysRun = true)
	public void reportSetup() throws IOException {

		report = new ExtentReports("target/surefire-reports/TestReport.html");
		report.loadConfig(new File("Extent-Config.xml"));
	}
	
	@Parameters({"browser"})
	@BeforeTest
	public void generateNode(@Optional("Chrome")String browser) {
		  
			parent = report.startTest("Testcases for browser:"+ browser);	 

	}
	
	
	@AfterTest
	public void closenode() {
		report.endTest(parent);
		report.flush();
	}

	@AfterMethod(alwaysRun = true)
	public void generateReport(ITestResult result) throws IOException {
		System.out.println("@After Method");
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String res = Utilities.captureScreenshot(driver, result.getName(), true);
				String TestCaseName = this.getClass().getSimpleName() + " Failed";
				String methodname = result.getMethod().getMethodName();
				logger.log(LogStatus.FAIL, methodname);
				logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(result.getName() + "screenshot.png"));
				logger.log(LogStatus.FAIL, result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Verified");
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
			}
//			report.endTest(logger);
//			report.flush();
		} catch (Throwable t) {
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}

	}

	
	
	
	//***************** intialising  browser
		
	@BeforeClass
	@Parameters({"browser"})
	public void setup(@Optional("Chrome")String browser) throws IOException {
		driver = openBrowser(browser);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		loginAuth(driver, prop); //logins to DAC
		navigateToDashboard(driver, prop); //navigate to dashboard
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date date = new Date();
		//String time = sdf.format(date);
	}

	
	public WebDriver openBrowser(String browser) throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(CONFIG_PATH);
		prop.load(fis);
		
		String className= this.getClass().getSimpleName();
		logger = report.startTest(className).assignCategory("Regression Testcases for "+ browser);
		parent.appendChild(logger);
		logger.log(LogStatus.INFO, "Log for Each Step in Test Case");
		//String browserName = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Firefox")) {
			
			driver = new FirefoxDriver();
			
		} else if (browser.equalsIgnoreCase("IE")) {
			
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}
	
	/**
	@AfterClass
	public void closeBrowser() throws Exception {
		// Create an object of current class
		driver.quit();
		driver = null;

	}
	
	*/
	
	//**********************for login to auth and then Dashboard

	public void loginAuth(WebDriver driver, Properties prop) {
		// login to auth centre
		driver.get(prop.getProperty("AuthCenterURL"));
		WebElement Login = driver.findElement(By.xpath(prop.getProperty("Authlogin")));

		Login.sendKeys(prop.getProperty("email"));
		WebElement Password = driver.findElement(By.xpath(prop.getProperty("Authpassword")));
		Password.sendKeys(prop.getProperty("password"));
		WebElement Signin = driver.findElement(By.xpath(prop.getProperty("signin")));
		Signin.submit();

	}

	public void navigateToDashboard(WebDriver driver, Properties prop) {
		String oldTab = driver.getWindowHandle();
		WebElement email = driver.findElement(By.id(prop.getProperty("emailsearch")));
		email.clear();
		email.sendKeys(prop.getProperty("emailID"));
		WebElement linkToDashboard = driver.findElement(By.linkText(prop.getProperty("dashboardLink")));
		linkToDashboard.click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));

	}
	


}
