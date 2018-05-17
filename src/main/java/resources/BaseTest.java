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
import java.util.HashMap;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import com.dac.main.LoginAC_Beta;

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
		navigateToDashboard(driver, prop, browser); //navigate to dashboard
		
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
		 File file = new File("./downloads");

		    boolean b = false;

		    if (!file.exists()) {
		     
		      b = file.mkdirs();
		    }
		    
		    FileUtils.cleanDirectory(file);
		    String downloadFolder = System.getProperty("user.dir")+"/downloads";
		    
		if (browser.equalsIgnoreCase("Chrome")) {
			
			HashMap<String, Object> chromePref = new HashMap<>();
			chromePref.put("download.default_directory", downloadFolder);
			chromePref.put("download.prompt_for_download", "false");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePref);
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("Firefox")) {
			
		    FirefoxProfile profile = new FirefoxProfile();
		    profile.setPreference("browser.download.dir",downloadFolder );  // folder
		    profile.setPreference("pdfjs.disabled", true);  // disable the built-in viewer
		    profile.setPreference("browser.download.folderList", 2);
		    profile.setPreference("browser.download.panel.shown", false);
		    profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");

		    FirefoxOptions firefoxOptions = new FirefoxOptions();
		    firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
		    firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
		    firefoxOptions.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 0);
	
			driver = new FirefoxDriver(firefoxOptions);
			
		} else if (browser.equalsIgnoreCase("IE")) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			driver = new InternetExplorerDriver(options);
			
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}
	
	
	/*@AfterClass
	public void closeBrowser() throws Exception {
		// Create an object of current class
		driver.quit();
		driver = null;

	}*/
	
	
	
	//**********************for login to auth and then Dashboard

	public void loginAuth(WebDriver driver, Properties prop) {
		// login to auth centre
		driver.get(prop.getProperty("AuthCenterURL"));
		/*WebElement Login = driver.findElement(By.xpath(prop.getProperty("Authlogin")));

		Login.sendKeys(prop.getProperty("email"));
		WebElement Password = driver.findElement(By.xpath(prop.getProperty("Authpassword")));
		Password.sendKeys(prop.getProperty("password"));
		WebElement Signin = driver.findElement(By.xpath(prop.getProperty("signin")));
		Signin.submit();*/
		
		LoginAC_Beta lp=new LoginAC_Beta(driver);
		lp.setUserName("rnair@dacgroup.com");
		lp.setPassword("DACQA123");
		lp.clickLogin();

	}

	public void navigateToDashboard(WebDriver driver, Properties prop, String browser) {
		String oldTab = driver.getWindowHandle();
		WebElement email = driver.findElement(By.id(prop.getProperty("emailsearch")));
		email.clear();
		email.sendKeys(prop.getProperty("emailID"));
		//driver.findElement(By.xpath("//input[@value='Submit']")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("dashboardLink")))).click();
		
		
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		}
		
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		for (String handle: handles)
		System.out.println(handle+"*****");
		
		handles.remove(oldTab);
		// change focus to new tab
		for (String handle: handles)
			System.out.println(handle+"*****");
		driver.switchTo().window(handles.get(0));

	}
	


}
