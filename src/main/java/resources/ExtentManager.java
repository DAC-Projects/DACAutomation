package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
    private static ExtentReports extent;
    
    /**
     * This method is used to create only 1 instance per execution of a TestNG.xml file
     * to create an instance of extent report
     * target/surefire-reports/extent.html		*/
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance("./extent.html");
    	
        return extent;
    }

    /**
     * This method is used to set the configuration of extent report 
     * how the theme and chart should display		*/
    private static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);   // DARK and STANDARD themes are available
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);
   
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }

}
