package resources;

import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	 
    private static ExtentReports extent;
    private static ExtentTest test;
    
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance("target/surefire-reports/extent.html");
    	
        return extent;
    }
    
    public static ExtentTest getTest(ITestContext context) {
        return extent.createTest(context.getName());
    }
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
   
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }

}
