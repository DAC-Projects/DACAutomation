package resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.LoginAC_Beta;
import com.selenium.testevidence.SeleniumEvidence;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public abstract class BaseClass {

	/*--------------------------Walkme Snippet-----------------------------------*/

	@FindBy(xpath = "//button[@class='//span[@class='walkme-custom-balloon-button-text' and contains(text(),'Cancel')]")
	private static WebElement WalkMeCancel;

	@FindBy(xpath = "//span[@class= 'walkme-action-destroy-0 wm-close-link' and contains(text(),'Okay')]")
	private static WebElement NotificationPopUp;

	/*--------------------------Walkme Snippet-----------------------------------*/

	// **********************for login to auth and then Dashboard

	/**
	 * This method is to navigate to TSEE dashboard page from AC1 Beta
	 * 
	 * @throws InterruptedException
	 */
	public static void navigateToBasePage() {
		LoginAC_Beta lp = new LoginAC_Beta();
		BaseClass.loginAuth(lp);
		BaseClass.navigateToDashboard(lp);
	}

	/**
	 * This method used to login to AC1 for a specific account which using for
	 * respective modules
	 * 
	 * @throws InterruptedException
	 */
	private static void loginAuth(LoginAC_Beta lp) {
		// login to auth centre
		String[] account = getAccount();
		CurrentState.getDriver().get(account[0]);
		lp.setUserName(IAutoconst.email);
		lp.setPassword(IAutoconst.password);
		lp.clickLogin();
		System.out.println("logged to Auth center");
	}

	@SuppressWarnings("unused")
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

		ArrayList<String> handles = new ArrayList<String>(CurrentState.getDriver().getWindowHandles());

		// change focus to new tab
		for (String windowHandle : CurrentState.getDriver().getWindowHandles()) {
			if (!windowHandle.equals(firstWindowHandle)) {
				CurrentState.getDriver().switchTo().window(windowHandle);
			}
		}

		CurrentState.getDriver().manage().window().maximize();
		System.out.println("navigted to Dashboard");

		try {
			
			if(CurrentState.getDriver().findElement(By.xpath("//button[@class='//span[@class='walkme-custom-balloon-button-text' and contains(text(),'Cancel')]")).isDisplayed()) {
				CurrentState.getDriver().findElement(By.xpath("//button[@class='//span[@class='walkme-custom-balloon-button-text' and contains(text(),'Cancel')]")).click();
			}
			}catch(Exception e) {
				System.out.println("Walkme Not displayed");
			}
			
			try {
				if(CurrentState.getDriver().findElement(By.xpath("//span[@class= 'walkme-action-destroy-0 wm-close-link' and contains(text(),'Okay')]")).isDisplayed()) {
					CurrentState.getDriver().findElement(By.xpath("//span[@class= 'walkme-action-destroy-0 wm-close-link' and contains(text(),'Okay')]")).click();
				}
			}catch(Exception e) {
				System.out.println("Notification PopUp Not displayed");
			}
	}

	private static String[] getAccount() {
		System.out.println(CurrentState.getTestName());
		switch (CurrentState.getTestName()) {

		case "Competitive Analysis":
			return IAutoconst.competitiveAnalysis;
		case "Customer_FeedBack":
			return IAutoconst.deepfieldAccount;
			//return IAutoconst.NandithaAccount;

		case "TransparenSEEBetaClient":
			return IAutoconst.transparenSEEClientBeta;
			
		case "TransparenSeeBetaLocation":
			return IAutoconst.transparenSEELocationBeta;
			
		case "TransparenSeeBetaDup" :
			return IAutoconst.transparenSEEDupData;
			
		case "TransparenSeeBetaBingLocation" :
			return IAutoconst.transparenSEEBingLocation;
			
		case "TransparenSEEStagingClient" :
			return IAutoconst.transparenSEEStagingClient;
			
		case "TransparenSeeStagingLocation" :
			return IAutoconst.transparenSEEStagingLocation;
			
		case "TransparenSeeClientBetaFace" :
			return IAutoconst.transparenSEEFaceBetaClient;
			
		case "TransparenSeeClientBetaBing" :
			return IAutoconst.transparenSEEDupData;
			
		case "ReviewsBetaGlobalFilter":
			return IAutoconst.neuralTuringTechAccount;
			
		case "ReviewsBetaGroupFilter":
			return IAutoconst.neuralTuringTechAccount;
			
		case "ReviewsBetaCountryFilter":
			return IAutoconst.neuralTuringTechAccount;
			
		case "ReviewsBetaStateFilter":
			return IAutoconst.neuralTuringTechAccount;
			
		case "ReviewsBetaCityFilter":
			return IAutoconst.neuralTuringTechAccount;
			
		case "ReviewsBetaLocationFilter":
			return IAutoconst.neuralTuringTechAccount;
			
		/*case "ReviewsStaging":
			return IAutoconst.neuralturingtechStaging;*/
		// IAutoconst.Fit4LessAccount;
		// IAutoconst.neuralTuringTechAccount;
			
		case "ResponseManagementCreator" :
			return IAutoconst.neuralResponseBeta;
			
			case "DRS" :
				return IAutoconst.DRSClient;
			case "DRS1" :
				return IAutoconst.DRSLocation;
				
		case "Social Engagement":
			return IAutoconst.SocialdeepFieldAccount;

		case "Social Automation":
			return IAutoconst.SocialAutomationAccount;
		case "Social Automation Creator":
			return IAutoconst.SocialAutomationAccountCreator;
			
		case "CFBeta" :
			return IAutoconst.CFAccountBeta;
			
		/*case "Reviews Staging" :
			return IAutoconst.neuralturingtechStaging;*/

		default:
			return IAutoconst.transparenSEEClientBeta;
		}

	}

	private static String takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	/**
	 * This message is used to add the steps and images in test evidence
	 * 
	 * @throws Exception
	 * @testStep: pass the step description to add in test evidence
	 * @isImageNeeded: while execution of test case, to create the test evidence if
	 *                 user wants to take a screen shot then, he/she can give as
	 *                 "yes" otherwise "no"
	 */
	@SuppressWarnings("unchecked")
	public static void addEvidence(WebDriver driver, String testStep, String isImageNeeded) throws Exception {

		if (isImageNeeded.equals("yes")) {
			CurrentState.getEvidenceList().add(new SeleniumEvidence(testStep, takeScreenshot(driver)));
			CurrentState.getLogger().info(testStep);
		} else {
			CurrentState.getEvidenceList().add(new SeleniumEvidence(testStep, null));
			CurrentState.getLogger().info(testStep);
		}
	}


	/**
	 * To Click on Cancel Walkme Snippet
	 */
	/*public static void clickwalkme() {
		JSWaiter.waitJQueryAngular();
		if (WalkMeCancel.isDisplayed()) {
			WalkMeCancel.click();
		} else {
			System.out.println("No Walkme Displayed");
		}
	}*/

	/**
	 * To Click on Okay Notification PopUp
	 */
	/*public static void clickNotificationPopUp() {
		JSWaiter.waitJQueryAngular();
		if (NotificationPopUp.isDisplayed()) {
			NotificationPopUp.click();
		} else {
			System.out.println("No Notification Displayed");
		}
	}*/
}
