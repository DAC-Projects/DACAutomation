package resources;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.testng.IClassListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestNGITestListener implements ITestListener, IClassListener, IAutoconst {

	private static ExtentReports extent = ExtentManager.createInstance("target/surefire-reports/extent.html");
	private static ThreadLocal parentTest = new ThreadLocal();
	private static ThreadLocal test = new ThreadLocal();
	private static ThreadLocal<String> sheet = new ThreadLocal<String>();
	JsonParse jsonread;

	@Override
	public synchronized void onStart(ITestContext context) {

		CurrentState.setTestcasefile(context.getCurrentXmlTest().getParameter("testcasesfile"));
		CurrentState.setBrowser(context.getCurrentXmlTest().getParameter("browser"));
		CurrentState.setTestName(context.getName());
		Thread.currentThread().getId();

		ExtentTest parent = extent.createTest(CurrentState.getTestName());
		parentTest.set(parent);

		System.setProperty(IE_KEY, IE_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		System.setProperty(CHROME_KEY, CHROME_VALUE);

	}

	@Override
	public synchronized void onBeforeClass(ITestClass testClass) {
		// TODO Auto-generated method stub
		try {
			CurrentState.setDriver(BaseTest2.openBrowser(CurrentState.getBrowser()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		CurrentState.getDriver().manage().window().maximize();
		CurrentState.getDriver().manage().deleteAllCookies();
		/*
		 * LoginAC_Beta lp= new LoginAC_Beta(); BaseTest2.loginAuth(lp);
		 * BaseTest2.navigateToDashboard(lp);
		 */

	}

	@Override
	public synchronized void onTestStart(ITestResult result) {


		try {

			jsonread = new JsonParse(CurrentState.getTestcasefile(), result.getInstance().getClass().getSimpleName(),
					result.getName());
			/*
			 * re = new ReadExcel(jsonread.getIterationPath()); String Name =
			 * CurrentState.getCurrentTestcase() +"-" + jsonread.getID();
			 * 
			 * if(CurrentState.getTestSteps()!=null) {
			 * CurrentState.getTestSteps().addAll(re.getTestcases(jsonread.getSheet(),
			 * jsonread.getID())); }else
			 * CurrentState.setTestSteps(re.getTestcases(jsonread.getSheet(),
			 * jsonread.getID()));
			 * 
			 * 
			 * if(CurrentState.getImgnames()!=null) {
			 * CurrentState.getImgnames().addAll(re.getScreenshotNames(jsonread.getSheet(),
			 * jsonread.getID())); }else
			 * CurrentState.setImgnames(re.getScreenshotNames(jsonread.getSheet(),
			 * jsonread.getID()));
			 */

			sheet.set(jsonread.getSheet());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertFalse(true, "json object creation failed");
		}

		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getDescription());
		child.assignCategory("Tests_executed_in_" + CurrentState.getBrowser() + "_browser");
		test.set(child);
		CurrentState.setLogger(child);
		((ExtentTest) test.get()).log(Status.INFO, "Testlogs");
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		((ExtentTest) test.get()).pass("Test Case Success and Verified");
		ReadExcel re;
		try {
			re = new ReadExcel(jsonread.getIterationPath());

			String Name = CurrentState.getCurrentTestcase() + "-" + jsonread.getID();

			if (CurrentState.getTestSteps() != null) {
				CurrentState.getTestSteps().addAll(re.getTestcases(jsonread.getSheet(), jsonread.getID()));
			} else
				CurrentState.setTestSteps(re.getTestcases(jsonread.getSheet(), jsonread.getID()));

			if (CurrentState.getImgnames() != null) {
				CurrentState.getImgnames().addAll(re.getScreenshotNames(jsonread.getSheet(), jsonread.getID()));
			} else
				CurrentState.setImgnames(re.getScreenshotNames(jsonread.getSheet(), jsonread.getID()));
		} catch (Exception e) {
			assertFalse(true, "json reading failed");
			e.printStackTrace();
		}

	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		// ((ExtentTest) test.get()).fail(result.getThrowable());
		try {
			generateErrorLog(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		ExtentTest child = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
		child.assignCategory(CurrentState.getBrowser());
		test.set(child);

		((ExtentTest) test.get()).skip(result.getThrowable());
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public synchronized void onAfterClass(ITestClass testClass) {
		double roundOff = Math.round(Math.random() * 100.0) / 100.0;
		;

		try {
			if (CurrentState.getTestSteps() != null && CurrentState.getImgnames() != null)
				new CreateEvidence(sheet.get() + roundOff).creatDoc(CurrentState.getTestSteps(),
						CurrentState.getImgnames());
		} catch (InvalidFormatException | IOException | XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CurrentState.getDriver().quit();
		CurrentState.setDriver(null);

		if (CurrentState.getTestSteps() != null) {
			CurrentState.getTestSteps().clear();
		}

		if (CurrentState.getImgnames() != null) {
			CurrentState.getImgnames().clear();
		}

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
		if (CurrentState.getDriver() != null) {
			CurrentState.getDriver().quit();
		}

		System.out.println("******************************");
		System.out.println("Thread -" + Thread.currentThread().getId() + " " + "imagearray below \n");
		CurrentState.getImgnames().stream().forEach(System.out::println);
		System.out.println("******************************");
	}

	public void generateErrorLog(ITestResult result) throws IOException {
		String methodname = result.getMethod().getMethodName();
		((ExtentTest) test.get()).log(Status.FAIL, methodname);
		String image = Utilities.captureScreenshot(CurrentState.getDriver(),
				this.getClass().getSimpleName() + "_" + result.getName(), true);
		((ExtentTest) test.get()).addScreenCaptureFromPath(image, "Error");

		if (result.getThrowable() instanceof ElementNotVisibleException) {
			((ExtentTest) test.get()).log(Status.FAIL, "function " + methodname + " failed beacuse :");
			((ExtentTest) test.get()).log(Status.FAIL, "Although an element is present on the DOM, it is not visible");

		} else if (result.getThrowable() instanceof ElementNotSelectableException) {
			((ExtentTest) test.get()).log(Status.FAIL, "function " + methodname + " failed beacuse :");
			((ExtentTest) test.get()).log(Status.FAIL, "Element cannot be selected. Element could be disabled");

		} else if (result.getThrowable() instanceof TimeoutException) {
			((ExtentTest) test.get()).log(Status.FAIL, "function " + methodname + " failed beacuse :");
			((ExtentTest) test.get()).log(Status.FAIL,
					"Execution failed because the command did not complete in enough time.");

		} else if (result.getThrowable() instanceof NoSuchElementException) {
			((ExtentTest) test.get()).log(Status.FAIL, "function " + methodname + " failed beacuse :");
			((ExtentTest) test.get()).log(Status.FAIL, "Cannot find Element on the page");

		} else if (result.getThrowable() instanceof StaleElementReferenceException) {
			((ExtentTest) test.get()).log(Status.FAIL, "function " + methodname + " failed beacuse :");
			((ExtentTest) test.get()).log(Status.FAIL, "Element is no longer appearing on the DOM page.");

		}

		Throwable th = result.getThrowable();
		if (th != null) {
			System.out.println(th.getMessage());

			String error = th.getMessage().split("Session info")[0];
			((ExtentTest) test.get()).log(Status.FAIL, error);
			result.setThrowable(null);
		}
	}

}
