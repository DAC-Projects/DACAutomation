package resources;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class CurrentState {
	
	private static ThreadLocal<String> testcasefile = new ThreadLocal<String>();
    private static ThreadLocal<String> testName = new ThreadLocal<String>();
    private static ThreadLocal<String> browser = new ThreadLocal<String>();
    private static ThreadLocal<ExtentTest> logger = new ThreadLocal<ExtentTest>();
    
    public static ExtentTest getLogger() {
		return logger.get();
	}
	public static void setLogger(ExtentTest log) {
		CurrentState.logger.set(log);
	}
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<ArrayList<String[]>> testSteps = new ThreadLocal<ArrayList<String[]>>();
    public static ThreadLocal<String> currentTestcase = new ThreadLocal<String>();
    private static ThreadLocal<ArrayList<String>> imgnames = new ThreadLocal<ArrayList<String>>();
    
    
    public static String getCurrentTestcase() {
		return currentTestcase.get();
	}
	public static void setCurrentTestcase(String currentTestcase) {
		CurrentState.currentTestcase.set(currentTestcase);
	}
	public static ArrayList<String[]> getTestSteps() {
		return testSteps.get();
	}
	public static void setTestSteps(ArrayList<String[]> testSteps) {
		CurrentState.testSteps.set(testSteps);
	}
	public static ArrayList<String> getImgnames() {
		return imgnames.get();
	}
	public static void setImgnames(ArrayList<String> imgnames) {
		CurrentState.imgnames.set(imgnames);
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver driver) {
		CurrentState.driver.set(driver); 
	}
	public static String getTestcasefile() {
		return testcasefile.get();
	}
	public static void setTestcasefile(String testcasefile) {
		CurrentState.testcasefile.set(testcasefile); 
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
