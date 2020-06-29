package resources;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Random;

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
	
public static String getCurrentTime() {
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	String time=String.valueOf(timestamp.getTime());
	
		return time;
		
	}
public static int randomNumber() {
	Random rand = new Random(); //instance of random class
    int upperbound = 100,int_random;
      //generate random values from 0-24
//    System.out.println("Random integer value from 0 to" + (upperbound-1) + " : "+ int_random);
    return int_random = rand.nextInt(upperbound); 
//    double double_random=rand.nextDouble();
//    float float_random=rand.nextFloat();
    
//    System.out.println("Random float value between 0.0 and 1.0 : "+float_random);
//    System.out.println("Random double value between 0.0 and 1.0 : "+double_random);
}

}
