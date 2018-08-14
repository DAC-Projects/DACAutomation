package resources;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class IntegrationTestListener extends BaseTest implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		/*String image;
		try {
			image = Utilities.captureScreenshot(driver, this.getClass().getSimpleName()+"_"+result.getName(), true);
		
		String TestCaseName = this.getClass().getSimpleName() + " Failed";
		String methodname = result.getMethod().getMethodName();
		logger.log(LogStatus.FAIL, methodname);
		logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(image));
		logger.log(LogStatus.FAIL, result.getThrowable());
		} catch (Throwable t) {
			// TODO Auto-generated catch block
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}*/
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		logger.log(LogStatus.SKIP,  " Test Case Skipped");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Verified");
	}

}
