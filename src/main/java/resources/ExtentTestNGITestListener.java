package resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IClassListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.testevidence.EvidenceReport;
import com.selenium.testevidence.EvidenceType;
import com.selenium.testevidence.GenerateEvidenceReport;
import com.selenium.testevidence.SeleniumEvidence;

import io.github.bonigarcia.wdm.Architecture;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ExtentTestNGITestListener
    implements ITestListener, IClassListener, IAutoconst, ISuiteListener {

  private static ExtentReports extent = ExtentManager.getInstance();
  private static ThreadLocal parentTest = new ThreadLocal();
  private static ThreadLocal test = new ThreadLocal();
  private static ThreadLocal<ArrayList<JasperPrint>> printList =new ThreadLocal();
    
  
  /**
   * Setting up folders downloads, Screenshot and testevidence And if exist
   * clear its content
   * 
   * @throws IOException
   */
  @Override
  public void onStart(ISuite suite) {
	  System.out.println("Suite Name : "+ suite.getName());
	  
	  String[] folderCreate = { "./downloads", "./Screenshot", "./testevidence" };
	    System.out.println("folderCreate.toString() : "+folderCreate.toString());

	    for (String folder : folderCreate) {
	      File file = new File(folder);

	      if (!file.exists()) {

	       file.mkdirs();
	      }

	      try {
			FileUtils.cleanDirectory(file);
			 System.out.println("cleaned directory");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("catch block");
		}
	    }
  	
  }
  
  /**
   * Set parent "node" for extent reports store "browser" and "test name" from testng.xml
   * 
   * @see org.testng.ITestListener#onStart(org.testng.ITestContext)		*/
  @Override
  public synchronized void onStart(ITestContext context) {

    CurrentState
        .setBrowser(context.getCurrentXmlTest().getParameter("browser"));
    CurrentState.setTestName(context.getName());
    ExtentTest parent = extent.createTest(CurrentState.getTestName());
    parentTest.set(parent);
    printList.set(new ArrayList<JasperPrint>()); 

  }

  /**
   * open browser, clear cookies and open maximized
   * 
   * @see org.testng.IClassListener#onBeforeClass(org.testng.ITestClass)
   */
  @Override
  public synchronized void onBeforeClass(ITestClass testClass) {
 
      try {
        CurrentState.setDriver(openBrowser(CurrentState.getBrowser()));
      } catch (IOException e) {
        e.printStackTrace();
      }
      CurrentState.getDriver().manage().window().maximize();
      CurrentState.getDriver().manage().deleteAllCookies();
      //Send driver object to JSWaiter Class
      JSWaiter.setDriver(CurrentState.getDriver());
      
      BaseClass.navigateToBasePage();
   
  }

  /**
   * (non-Javadoc)
   * Initialize Child nodes in extent report
   * 
   * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
   */
  @Override
  public synchronized void onTestStart(ITestResult result) {
    ExtentTest child = ((ExtentTest) parentTest.get()).createNode(getTestname(result));
    child.assignCategory(
        "Tests_executed_in_" + CurrentState.getBrowser() + "_browser");
    test.set(child);
    CurrentState.setLogger(child);
    ((ExtentTest) test.get()).log(Status.INFO, "Testlogs");
    
   CurrentState.setEvidenceList(new ArrayList<SeleniumEvidence>());
 
  }
  
  /**
   * This method return the description of @Test method if provided 
   * otherwise will return the test method name 	*/
  private String getTestname(ITestResult result){
    String description = (result.getMethod().getDescription() != null)
        ? result.getMethod().getDescription()
        : result.getMethod().getMethodName();
    return description;
  }

  /**
   * This method is used to execute when the @Test method is successfully executed		
   * 
   * @see org.testng.IClassListener#onTestSuccess(org.testng.ITestResult)		*/
  @SuppressWarnings("unchecked")
  @Override
  public synchronized void onTestSuccess(ITestResult result) {
    ((ExtentTest) test.get()).pass("Test Case Success and Verified");
    List evidence =CurrentState.getEvidenceList();
    try {
    
    if(!evidence.isEmpty()) {
      
    EvidenceReport report = new EvidenceReport(evidence, "MyReportOK", getTestname(result), result.getTestContext().getName(), null);
      printList.get().add(GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.PDF));}else {
      BaseClass.addEvidence(CurrentState.getDriver(), "Test Passed. No Steps were added to this method", "yes");
        EvidenceReport report = new EvidenceReport(CurrentState.getEvidenceList(), "MyReportOK", getTestname(result), result.getTestContext().getName(), null);
        printList.get().add(GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.PDF));}
      
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to execute when the @Test method is failed from execution		
   * 
   * @see org.testng.IClassListener#onTestFailure(org.testng.ITestResult)		*/
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public synchronized void onTestFailure(ITestResult result) {

    generateErrorLog(result);

    try {
    	BaseClass.addEvidence(CurrentState.getDriver(), "Error", "yes");
    String errorMessage = result.getThrowable().getMessage();
    List evidence =CurrentState.getEvidenceList();
      
    EvidenceReport report = new EvidenceReport(evidence, "MyReportNOK", getTestname(result), result.getTestContext().getName(), errorMessage);
      printList.get().add(GenerateEvidenceReport.generareEvidenceReport(report, EvidenceType.PDF));

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * This method is used to execute when the @Test method is skipped from execution 
   * as those method may depends on stats of another test method		
   * 
   * @see org.testng.IClassListener#onTestSkipped(org.testng.ITestResult)		*/
  @Override
  public synchronized void onTestSkipped(ITestResult result) {
    String description = (result.getMethod().getDescription() != null)
        ? result.getMethod().getDescription()
        : result.getMethod().getMethodName();
    ExtentTest child = ((ExtentTest) parentTest.get()).createNode(description);
    child.assignCategory(CurrentState.getBrowser());
    test.set(child);

    ((ExtentTest) test.get()).skip(result.getThrowable());
    
  }

  @Override
  public synchronized void onTestFailedButWithinSuccessPercentage(
      ITestResult result) {

  }

  /**
   * (non-Javadoc)
   * Close the browser after the execution of each class in TestNG.xml 
   * 
   * @see org.testng.IClassListener#onAfterClass(org.testng.ITestClass)	   */
  @Override
  public synchronized void onAfterClass(ITestClass testClass) {
   
    CurrentState.getDriver().quit();
    CurrentState.setDriver(null);
  }

  /**
   * Quit the browser after the execution of each class in TestNG.xml 
   * and generate the test evidence after each execution of a class		
   * 
   * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)	*/
  @Override
  public synchronized void onFinish(ITestContext context) {
    extent.flush();
    if (CurrentState.getDriver() != null) {
      CurrentState.getDriver().quit();
    }
    
    if(!printList.get().isEmpty()) {
      GenerateEvidenceReport.exportReport(printList.get(), "Report for "+context.getName());
      printList.get().clear();}

  }

  /**
   * Code to generate error log when the test script got failed.
   * 
   * @param result
   */
  private void generateErrorLog(ITestResult result) {
    try {
      String methodname = result.getMethod().getMethodName();
      ((ExtentTest) test.get()).log(Status.FAIL, methodname);
      String image;

      image = Utilities.captureScreenshot(CurrentState.getDriver(),
          this.getClass().getSimpleName() + "_" + result.getName(), true);

      ((ExtentTest) test.get()).addScreenCaptureFromPath(image, "Error");

      if (result.getThrowable() instanceof ElementNotVisibleException) {
        ((ExtentTest) test.get()).log(Status.FAIL,
            "function " + methodname + " failed beacuse :");
        ((ExtentTest) test.get()).log(Status.FAIL,
            "Although an element is present on the DOM, it is not visible");

      } else if (result
          .getThrowable() instanceof ElementNotSelectableException) {
        ((ExtentTest) test.get()).log(Status.FAIL,
            "function " + methodname + " failed beacuse :");
        ((ExtentTest) test.get()).log(Status.FAIL,
            "Element cannot be selected. Element could be disabled");

      } else if (result.getThrowable() instanceof TimeoutException) {
        ((ExtentTest) test.get()).log(Status.FAIL,
            "function " + methodname + " failed beacuse :");
        ((ExtentTest) test.get()).log(Status.FAIL,
            "Execution failed because the command did not complete in enough time.");

      } else if (result.getThrowable() instanceof NoSuchElementException) {
        ((ExtentTest) test.get()).log(Status.FAIL,
            "function " + methodname + " failed beacuse :");
        ((ExtentTest) test.get()).log(Status.FAIL,
            "Cannot find Element on the page");

      } else if (result
          .getThrowable() instanceof StaleElementReferenceException) {
        ((ExtentTest) test.get()).log(Status.FAIL,
            "function " + methodname + " failed beacuse :");
        ((ExtentTest) test.get()).log(Status.FAIL,
            "Element is no longer appearing on the DOM page.");

      }

      Throwable th = result.getThrowable();
      if (th != null) {
        System.out.println(th.getMessage());

        String error = th.getMessage().split("Session info")[0];
        ((ExtentTest) test.get()).log(Status.FAIL, error);
        //result.setThrowable(null);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used to open the browser ie. chrome or firefox or ie browser 
   * while execution and setup the configuration before opening the browser.
   * 
   * @param browser
   * @return the driver in which user going to execute among chrome/firefox/ie.
   * @throws IOException	   */
  public WebDriver openBrowser(String browser) throws IOException {
	  WebDriverWait wait;
    WebDriver driver = null;

    File file = new File("./downloads");
    if (!file.exists())
      file.mkdirs();

    String downloadFolder = System.getProperty("user.dir") + "/downloads";

    if (browser.equalsIgnoreCase("Chrome")) {
      WebDriverManager.chromedriver().version("73.0.3683.68").setup();
      //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
      HashMap<String, Object> chromePref = new HashMap<>();
      chromePref.put("download.default_directory", downloadFolder);
      chromePref.put("download.prompt_for_download", "false");
      ChromeOptions options = new ChromeOptions();
      options.addArguments("disable-infobars");
      options.setExperimentalOption("prefs", chromePref);
      //WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver(options);
    //Send driver object to JSWaiter Class
      JSWaiter.setDriver(driver);
      //This is the default wait for Explicit Waits
      wait = new WebDriverWait(driver,15);
    } else if (browser.equalsIgnoreCase("Firefox")) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxProfile profile = new FirefoxProfile();
      profile.setPreference("browser.download.dir", downloadFolder); // folder
      profile.setPreference("pdfjs.disabled", true); // disable the built-in
                                                     // viewer
      profile.setPreference("browser.download.folderList", 2);
      profile.setPreference("browser.download.panel.shown", false);
      profile.setPreference("browser.helperApps.neverAsksaveToDisk",
          "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");

      FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
      firefoxOptions.setCapability(FirefoxDriver.MARIONETTE, true);
      firefoxOptions.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 0);

      driver = new FirefoxDriver(firefoxOptions);
    //Send driver object to JSWaiter Class
      JSWaiter.setDriver(driver);
      //This is the default wait for Explicit Waits
      wait = new WebDriverWait(driver,15);

    } else if (browser.equalsIgnoreCase("IE")) {
      WebDriverManager.iedriver().architecture(Architecture.X32).setup();
      DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
      capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
      capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
      capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"");
      capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
      capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
      capabilities.setCapability("ignoreProtectedModeSettings", 1);
      capabilities.setCapability("IntroduceInstabilityByIgnoringProtectedModeSettings", true);
      //options.requireWindowFocus();
      String path = System.getProperty("user.dir") + "/downloads";
      String cmd1 = "REG ADD \"HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main\" /F /V \"Default Download Directory\" /T REG_SZ /D "
          + path;

      try {
        Runtime.getRuntime().exec(cmd1);
      } catch (Exception e) {
        System.out.println(
            "Coulnd't change the registry for default directory for IE");
      }
      driver = new InternetExplorerDriver(capabilities);
    //Send driver object to JSWaiter Class
      JSWaiter.setDriver(driver);
      //This is the default wait for Explicit Waits
      wait = new WebDriverWait(driver,15);

    }

    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    System.out.println("opened browser");
    return driver;

  }



  @Override
  public void onFinish(ISuite suite) {
	  System.out.println("Suite Name : "+ suite.getName());

  }
}
