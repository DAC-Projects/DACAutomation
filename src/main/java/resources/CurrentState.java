package resources;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

/**
 * This class provides thread-local variables.
 * To make the global usage variables are local to each thread so that each variable data would not 
 * be merged with another browser's variables data.
 * 
 * Where we can set the data values to the identifiers before getting the values of the identifiers 
 * for each thread local variables		*/
public class CurrentState {

  private static ThreadLocal<String> testName = new ThreadLocal<String>();
  private static ThreadLocal<String> browser = new ThreadLocal<String>();
  private static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();
  private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
  @SuppressWarnings("rawtypes")
private static ThreadLocal<List> evidenceList =new ThreadLocal<List>();

  /**
   * This method used to get the logger for setted logger, execute in specific driver
   * for each browser as a local identifier		*/
  public static ExtentTest getLogger() {
    return logger.get();
  }

  /**
   * This method used to get the setted evidence list, execute in specific driver
   * for each browser as a local identifier		*/
  @SuppressWarnings("rawtypes")
public static List getEvidenceList() {
    return evidenceList.get();
  }

  /**
   * This method used to "set the list of evidence" of execution steps to execute in specific driver
   * for each browser as a local identifier	to add the steps in creating test evidences for each test class	*/
  @SuppressWarnings("rawtypes")
public static void setEvidenceList(List evidencelist) {
    CurrentState.evidenceList.set(evidencelist) ;
    
  }

  /**
   * This method used to set the logger to add the logger info, pass/fail status of execution steps in Extent reports*/
  public static void setLogger(ExtentTest log) {
    CurrentState.logger.set(log);
  }

  /**
   * This method used to get the setted driver, execute in specific driver
   * for each browser as a local identifier		*/
  public static WebDriver getDriver() {
    return driver.get();
  }

  /**
   * This method used to "set the driver" of execution steps to execute in specific driver
   * for each browser as a local identifier		*/
  public static void setDriver(WebDriver driver) {
    CurrentState.driver.set(driver);
  }

  /**
   * This method used to get the setted test case name, execute in specific driver
   * for each browser as a local identifier		*/
  public static String getTestName() {
    return testName.get();
  }

  /**
   * This method used to "set the test case name" to execute in specific driver
   * for each browser as a local identifier		*/
  public static void setTestName(String testName) {
    CurrentState.testName.set(testName);
  }

  /**
   * This method used to get the setted browser, execute in specific driver
   * for each browser as a local identifier		*/
  public static String getBrowser() {
    return browser.get();
  }

  /**
   * This method used to set on which browser to execute the steps of test cases
   * 
   * @param browser : on which browser to execute ie. chrome or ie or firefox		*/
  public static void setBrowser(String browser) {
    CurrentState.browser.set(browser);
  }

}
