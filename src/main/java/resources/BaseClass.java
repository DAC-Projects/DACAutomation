package resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import com.dac.main.LoginAC_Beta;

public abstract class BaseClass {

  /**
   * Setting up folders downloads, Screenshot and testevidence And if exist
   * clear its content
   * 
   * @throws IOException
   */
  
   List test = new ArrayList();
   
   static int i = 10;
   
   static BaseClass b = new BaseClass() {
  };
  
  public static List getValue() {
    return b.test;
  }
  @BeforeSuite(alwaysRun = true)
  public void reportSetup() throws IOException {

    String[] folderCreate = { "./downloads", "./Screenshot", "./testevidence" };

    for (String folder : folderCreate) {
      File file = new File(folder);

      if (!file.exists()) {

       file.mkdirs();
      }

      FileUtils.cleanDirectory(file);
    }
  }

  // **********************for login to auth and then Dashboard

  public static void navigateToBasePage() {
    LoginAC_Beta lp = new LoginAC_Beta();
    BaseClass.loginAuth(lp);
    BaseClass.navigateToDashboard(lp);
  }

  public static void loginAuth(LoginAC_Beta lp) {
    // login to auth centre
    String[] account = getAccount();
    CurrentState.getDriver().get(account[0]);
    lp.setUserName(IAutoconst.email);
    lp.setPassword(IAutoconst.password);
    lp.clickLogin();
    System.out.println("logged to Auth center");

  }

  public static void navigateToDashboard(LoginAC_Beta lp) {
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
    for (String handle : handles)
      System.out.println(handle + "*****");

   
    // change focus to new tab
    for(String windowHandle : CurrentState.getDriver().getWindowHandles()) {
      if (!windowHandle.equals(firstWindowHandle)) {
        CurrentState.getDriver().switchTo().window(windowHandle);
      }
  }
    
    CurrentState.getDriver().manage().window().maximize();
    System.out.println("navigted to Dashboard");

  }

  public static String[] getAccount() {
    switch (CurrentState.getTestName()) {

    case "Competitive Analysis":
      return IAutoconst.competitiveAnalysis;
    case "Customer_FeedBack":
      return IAutoconst.deepfieldAccount;
    default:
      return IAutoconst.deepfieldAccount;
    }

  }
  public static String takeScreenshot(WebDriver driver) {
    return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
  }

}
