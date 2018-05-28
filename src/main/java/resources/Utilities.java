package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	
	
	public static String captureScreenshot(WebDriver driver, String resultName, boolean b) throws IOException {
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
	
	
	//For the test evidence creation
	public static void addScreenshot(WebDriver driver, String message) throws IOException {
		System.out.println("reached screenshot block");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("Screenshot/"+message+".png"));

	}
}
