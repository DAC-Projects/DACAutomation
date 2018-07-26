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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
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
import org.testng.ITestContext;

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
	protected ArrayList<String> imgnames = new ArrayList<String>();
	protected ArrayList<String[]> arraySteps = new ArrayList<>();
	private ReadExcel re;
	private CreateEvidence ce;
	private static String testName;
	public static String testcasefile;
	public static String className;
	public static String id;
	//****************************Extent report
	
	@BeforeSuite(alwaysRun = true)
	public void reportSetup() throws IOException {

		report = new ExtentReports("target/surefire-reports/TestReport.html");
		report.loadConfig(new File("Extent-Config.xml"));
		
		//creating new folders
		
		String[] folderCreate= {"./downloads", "./Screenshot", "./testevidence"};
		
		for (String folder: folderCreate)
		{
		 File file = new File(folder);

		    boolean b = false;

		    if (!file.exists()) {
		     
		      b = file.mkdirs();
		    }
		    
		    FileUtils.cleanDirectory(file);
		}
	}
	
	
	
	@Parameters({"testcasesfile"})
	@BeforeTest
	public void generateNode(final ITestContext testContext, @Optional("Customer_FeedBack.json")String testcasesfile) {
		  	
			parent = report.startTest("Testcases for :"+ testContext.getName());	
			this.testcasefile= testcasesfile;
			this.testName = testContext.getName();
	}
	
	
	@AfterTest
	public void closenode() {
		report.endTest(parent);
		report.flush();
		
	}

	@AfterMethod(alwaysRun = true)
	public void generateReport(ITestResult result) throws IOException, InvalidFormatException, XmlException {
		System.out.println("@After Method");
		int status = result.getStatus();
		
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String image = Utilities.captureScreenshot(driver, this.getClass().getSimpleName()+"_"+result.getName(), true);
				String TestCaseName = this.getClass().getSimpleName() + " Failed";
				String methodname = result.getMethod().getMethodName();
				logger.log(LogStatus.FAIL, methodname);
				logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(image));
				
				if (result.getThrowable() instanceof ElementNotVisibleException) {
					logger.log(LogStatus.FAIL, "function "+methodname+ " failed beacuse :");
					logger.log(LogStatus.FAIL, "Although an element is present on the DOM, it is not visible");
					//logger.log(LogStatus.FAIL, result.getThrowable().getCause());
				}else if(result.getThrowable() instanceof ElementNotSelectableException) {
					logger.log(LogStatus.FAIL, "function "+methodname+ " failed beacuse :");
					logger.log(LogStatus.FAIL, "Element cannot be selected. Element could be disabled");
					//logger.log(LogStatus.FAIL, result.getThrowable().getCause());
				}else if(result.getThrowable() instanceof TimeoutException) {
					logger.log(LogStatus.FAIL, "function "+methodname+ " failed beacuse :");
					logger.log(LogStatus.FAIL, "Execution failed because the command did not complete in enough time.");
					//logger.log(LogStatus.FAIL, result.getThrowable().getCause());
				}else if(result.getThrowable() instanceof NoSuchElementException) {
					logger.log(LogStatus.FAIL, "function "+methodname+ " failed beacuse :");
					logger.log(LogStatus.FAIL, "Cannot find Element on the page");	
					
				}else if(result.getThrowable() instanceof StaleElementReferenceException) {
					logger.log(LogStatus.FAIL, "function "+methodname+ " failed beacuse :");
					logger.log(LogStatus.FAIL, "Element is no longer appearing on the DOM page.");
					
					//logger.log(LogStatus.FAIL, result.getThrowable().getCause());
				}
				
				Throwable th = result.getThrowable();
		        if (th != null) {
		            System.out.println(th.getMessage());
		            
		            String error = th.getMessage().split("Session info")[0];
		            logger.log(LogStatus.FAIL, error);
		            result.setThrowable(null);
		        }
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Verified");
			} /*else if (status!=ITestResult.SUCCESS||status!=ITestResult.FAILURE) {
				logger.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
			}*/
//			report.endTest(logger);
//			report.flush();
		} catch (Throwable t) {
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}
		
	}


	//***************** intialising  browser
		
	@BeforeClass
	@Parameters({"browser"})
	public void setup(@Optional("Chrome")String browser) throws Exception {
		
		 String className =this.getClass().getName();
		 System.out.println(className + "***********");
		 JsonParse Ja = new JsonParse(testcasefile, className);
		 id =Ja.getID();
		System.out.println(Ja.getSheet());
		System.out.println(Ja.getID());
		
		re = new ReadExcel(Ja.getIterationPath());
		arraySteps = re.getTestcases(Ja.getSheet(), Ja.getID());
		imgnames = re.getScreenshotNames(Ja.getSheet(), Ja.getID());
		
		
		driver = openBrowser(browser);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		LoginAC_Beta lp= new LoginAC_Beta(driver);
		loginAuth(driver, lp); //logins to DAC
		navigateToDashboard(driver, lp, browser); //navigate to dashboard
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date date = new Date();
		//String time = sdf.format(date);
		
	}

	
	public WebDriver openBrowser(String browser) throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(CONFIG_PATH);
		prop.load(fis);
		
		String className= this.getClass().getSimpleName();
		String Name = ReadExcel.Testcase +"-" +id; 
		System.out.println(Name);
		logger = report.startTest(Name).assignCategory("Regression Testcases for "+ browser);
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
	
	
	@AfterClass
	public void closeBrowser() throws Exception {
	
		ce =new CreateEvidence(ReadExcel.Testcase);
		ce.creatDoc(arraySteps);
		
		driver.quit();
		driver = null;

	}
	
	
	
	//**********************for login to auth and then Dashboard

	public void loginAuth(WebDriver driver, LoginAC_Beta lp) {
		// login to auth centre
		String [] account = getAccount();
		driver.get(account[0]);	
		
		lp.setUserName(email);
		lp.setPassword(password);
		lp.clickLogin();


	}

	public void navigateToDashboard(WebDriver driver, LoginAC_Beta lp, String browser) {
		String oldTab = driver.getWindowHandle();
		String [] account = getAccount();
		lp.findUser(account[1]);
		lp.clickDashboardLink();
			
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
	
	public String[] getAccount() {
		
		switch(testName) {
		
		case "Competitive Analysis" :
			return competitiveAnalysis;	
		case "Customer_FeedBack" :
			return deepfieldAccount;
		default:
			return deepfieldAccount;
		}
		
		
	}

}
