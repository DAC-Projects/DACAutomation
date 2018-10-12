package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import org.json.simple.parser.ParseException;
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
import org.testng.annotations.BeforeMethod;
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

import org.testng.ITestClass;
import org.testng.ITestContext;

public abstract class BaseTest2 implements IAutoconst {


	

	

	private ReadExcel re;
	private CreateEvidence ce;

	public static String className;
	public static String id;

	public static String title;
	//****************************Extent report
	
	@BeforeSuite(alwaysRun = true)
	public void reportSetup() throws IOException {
		
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

	//***************** intialising  browser
		

/*	@BeforeMethod(alwaysRun = true)
	public void setup(Method m) throws Exception {
		System.out.println("entered before method");
		String className =this.getClass().getName();
		String methodName = m.getName();
		 System.out.println(className + "***********\n"+ methodName);
		 System.out.println("jason object creating");
		 System.out.println(testcasefile);
		 id =Ja.getID();
		 title = Ja.getSheet();
		 System.out.println("id :"+id);
		System.out.println(Ja.getSheet());
		System.out.println(Ja.getID());
		
		re = new ReadExcel(Ja.getIterationPath());
		arraySteps.addAll( re.getTestcases(Ja.getSheet(), Ja.getID()));
		imgnames.addAll(re.getScreenshotNames(Ja.getSheet(), Ja.getID()));
		imgnames.forEach(System.out::println);
		
		String class= this.getClass().getSimpleName();
		String Name = ReadExcel.Testcase +"-" +id; 
		System.out.println(Name);
		logger = report.startTest(Name).assignCategory("Regression Testcases for "+ browser);
		parent.appendChild(logger);
		logger.log(LogStatus.INFO, "Log for Each Step in Test Case");
		System.out.println("setup before method");
	}
	*/
	
	

	
	public static WebDriver openBrowser(String browser) throws IOException {
		
		/*prop = new Properties();
		FileInputStream fis = new FileInputStream(CONFIG_PATH);
		prop.load(fis);*/
		WebDriver driver=null;
		
		 File file = new File("./downloads");


		    if (!file.exists()) 		     
		      file.mkdirs();

		    
		   // FileUtils.cleanDirectory(file);
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
			options.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, ""); 
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
			//options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true); 
			//IEDesiredCapabilities.setCapability("requireWindowFocus", true);
			options.setCapability("enablePersistentHover", false);
			
			options.setCapability("ignoreProtectedModeSettings",1);
			options.setCapability("IntroduceInstabilityByIgnoringProtectedModeSettings",true);
			String path = System.getProperty("user.dir")+"/downloads";
			String cmd1 = "REG ADD \"HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main\" /F /V \"Default Download Directory\" /T REG_SZ /D "+ path;

			try {
			    Runtime.getRuntime().exec(cmd1);
			} catch (Exception e) {
			    System.out.println("Coulnd't change the registry for default directory for IE");
			}
			driver = new InternetExplorerDriver(options);

			
		}

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println("opened browser");
		return driver;

	}
	
	
/*	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws Exception {
	
		//ce =new CreateEvidence(ReadExcel.Testcase);
		ce =new CreateEvidence(title);
		System.out.println("test steps");
		arraySteps.forEach(e->System.out.println(Arrays.toString(e)));
		System.out.println("image names");
		System.out.println(imgnames.toString());;
		ce.creatDoc(arraySteps, imgnames);
		
		driver.quit();
		driver = null;

	}
	*/
	
	
	//**********************for login to auth and then Dashboard

	public static void loginAuth( LoginAC_Beta lp) {
		// login to auth centre
		String [] account = getAccount();
		CurrentState.getDriver().get(account[0]);		
		lp.setUserName(email);
		lp.setPassword(password);
		lp.clickLogin();
		System.out.println("logged to Auth center");

	}

	public static void navigateToDashboard(LoginAC_Beta lp) {
		System.out.println("navigating to dashboard");
		String oldTab = CurrentState.getDriver().getWindowHandle();
		String [] account = getAccount();
		lp.findUser(account[1]);
		lp.clickDashboardLink();
			
		if (CurrentState.getBrowser().equalsIgnoreCase("firefox")) {
			WebDriverWait wait = new WebDriverWait(CurrentState.getDriver(),5);
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		}
		
		ArrayList<String> handles = new ArrayList<String>(CurrentState.getDriver().getWindowHandles());
		for (String handle: handles)
		System.out.println(handle+"*****");
		
		handles.remove(oldTab);
		// change focus to new tab
		for (String handle: handles)
			System.out.println(handle+"*****");
		CurrentState.getDriver().switchTo().window(handles.get(0));
		CurrentState.getDriver().manage().window().maximize();
		System.out.println("navigted to Dashboard");

	}
	
	public static String[] getAccount() {
		
		switch(CurrentState.getTestName()) {
		
		case "Competitive Analysis" :
			return competitiveAnalysis;	
		case "Customer_FeedBack" :
			return deepfieldAccount;
		default:
			return deepfieldAccount;
		}
		
		
	}
	
	/*@AfterClass(alwaysRun=true)
	public synchronized void afterClass() {
		// TODO Auto-generated method stub
		
		String sheet = Math.random()+"";
			try {
				long s = Thread.currentThread().getId();
			new CreateEvidence(sheet).creatDoc(CurrentState.getTestSteps(),CurrentState.getImgnames());
		} catch (InvalidFormatException | IOException | XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Thread.currentThread().getId();
		CurrentState.getDriver();
		CurrentState.getDriver().quit();
		CurrentState.setDriver(null);
		
		if(CurrentState.getTestSteps()!=null) {
			CurrentState.getTestSteps().clear();
		}
		
		
		if(CurrentState.getImgnames()!=null) {
			CurrentState.getImgnames().clear();
		}
		
		//System.out.println("@AfterClass executed for "+testClass.getName());
		

	}*/
	
	

}
