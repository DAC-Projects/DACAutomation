package resources;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.selenium.testevidence.SeleniumEvidence;

public class CurrentState {

  private static ThreadLocal<String> testName = new ThreadLocal<String>();
  private static ThreadLocal<String> browser = new ThreadLocal<String>();
  private static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();
  private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
  private static ThreadLocal<List> evidenceList =new ThreadLocal<List>();

  public static ExtentTest getLogger() {
    return logger.get();
  }

  public static List getEvidenceList() {
    return evidenceList.get();
  }

  public static void setEvidenceList(List evidencelist) {
    CurrentState.evidenceList.set(evidencelist) ;
    
  }

  public static void setLogger(ExtentTest log) {
    CurrentState.logger.set(log);
  }

  public static WebDriver getDriver() {
    return driver.get();
  }

  public static void setDriver(WebDriver driver) {
    CurrentState.driver.set(driver);
  }

  public static String getTestName() {
    return testName.get();
  }

  public static void setTestName(String testName) {
    CurrentState.testName.set(testName);
  }

  public static String getBrowser() {
    return browser.get();
  }

  public static void setBrowser(String browser) {
    CurrentState.browser.set(browser);
  }

}
