package resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.dac.main.DashboardpageKPI_Menu;
import com.dac.main.LoginAC_Beta;
import com.selenium.testevidence.SeleniumEvidence;

public abstract class BaseClass {


  // **********************for login to auth and then Dashboard

  /**
   * This method is to navigate to TSEE dashboard page from AC1 Beta	
 * @throws InterruptedException */
  public static void navigateToBasePage() {
    LoginAC_Beta lp = new LoginAC_Beta();
    BaseClass.loginAuth(lp);
    BaseClass.navigateToDashboard(lp);

    WebDriverWait wait = new WebDriverWait(CurrentState.getDriver(), 10);
    if(!CurrentState.getBrowser().contains("ie")) {

    	wait.until(ExpectedConditions.visibilityOf(CurrentState.getDriver().findElement(By.xpath("//div//button[contains(@class,'walkme-custom-balloon-button walkme-custom-balloon-weak-button walkme-custom-balloon-cancel-button walkme-action-cancel walkme-click-and-hover')]"))));
    	CurrentState.getDriver().findElement(By.xpath("//div//button[contains(@class,'walkme-custom-balloon-button walkme-custom-balloon-weak-button walkme-custom-balloon-cancel-button walkme-action-cancel walkme-click-and-hover')]")).click();    	
    }
  }

  /**
   * This method used to login to AC1 for a specific account which using for respective modules		
 * @throws InterruptedException */
  private static void loginAuth(LoginAC_Beta lp) {
    // login to auth centre
    String[] account = getAccount();
    CurrentState.getDriver().get(account[0]);
    lp.setUserName(IAutoconst.email);
    lp.setPassword(IAutoconst.password);
    lp.clickLogin();
    System.out.println("logged to Auth center");  
  }

  private static void navigateToDashboard(LoginAC_Beta lp) {
    System.out.println("navigating to dashboard");
    String firstWindowHandle = CurrentState.getDriver().getWindowHandle();
    String[] account = getAccount();
    lp.findUser(account[1]);
    lp.clickDashboardLink();

    if (CurrentState.getBrowser().equalsIgnoreCase("firefox")) {
      WebDriverWait wait = new WebDriverWait(CurrentState.getDriver(), 5);
      wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    ArrayList<String> handles = new ArrayList<String>(
        CurrentState.getDriver().getWindowHandles());

   
    // change focus to new tab
    for(String windowHandle : CurrentState.getDriver().getWindowHandles()) {
      if (!windowHandle.equals(firstWindowHandle)) {
        CurrentState.getDriver().switchTo().window(windowHandle);
      }
  }
    
    CurrentState.getDriver().manage().window().maximize();
    System.out.println("navigted to Dashboard");

  }

  private static String[] getAccount() {
	  System.out.println(CurrentState.getTestName());
    switch (CurrentState.getTestName()) {

    case "Competitive Analysis":
      return IAutoconst.competitiveAnalysis;
    case "Customer_FeedBack":
      return IAutoconst.deepfieldAccount;

    case "TransparenSEE":
    	return IAutoconst.transparenSEE;

    case "Sentiment Analysis":
    case "SA":
        return IAutoconst.transparenSEE;
        	   //IAutoconst.Fit4LessAccount;
        	   //IAutoconst.neuralTuringTechAccount;
        

    default:
      return IAutoconst.transparenSEE;
    }

  }
  
  private static String takeScreenshot(WebDriver driver) {
    return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
  }
  
  /**
   * This message is used to add the steps and images in test evidence 
   * @throws Exception 
   * @testStep: pass the step description to add in test evidence
   * @isImageNeeded: while execution of test case, to create the test evidence
   * 				 if user wants to take a screen shot then, he/she can give as "yes" 
   * 				 otherwise "no"			*/
  public static void addEvidence(WebDriver driver, String testStep, String isImageNeeded) throws Exception {
	  
	  if(isImageNeeded.equals("yes")) {
		  CurrentState.getEvidenceList().add(new SeleniumEvidence(testStep, takeScreenshot(driver)));
		  CurrentState.getLogger().info(testStep);
	  }
	  else {
		  CurrentState.getEvidenceList().add(new SeleniumEvidence(testStep, null));
		  CurrentState.getLogger().info(testStep);
	  }	  
  }

}
