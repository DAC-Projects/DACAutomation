package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	
	/**
	 * This method is used to take a screenshot when we get bug/error while executing the test scripts
	 * 
	 * @param driver : pass the driver in which test scripts are executing.
	 * @param resultName : result name configured to get class name and method name where the bug we get while executing the scripts	
	 * 
	 * @return this method will return the image name of a bug which have taken at the time of execution in .png format		*/
	public static synchronized String captureScreenshot(WebDriver driver, String resultName, boolean b) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Imglocation = "./Screenshot/" + resultName + ".png";
		File screenshot= new File(Imglocation);
		FileUtils.copyFile(src, screenshot);			
		if (b=true)
		{
			File dest= new File("target/surefire-reports/" + resultName + ".png");
			FileUtils.copyFile(screenshot , dest);
		}
		String image = resultName + ".png";
		return image;
	}
	
}
