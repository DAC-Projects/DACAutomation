package com.dac.main.api;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class ATUReport {


	  //Set Property for ATU Reporter Configuration
  {
    System.setProperty("atu.reporter.config", "config/reports.properties");

  }

	
	public void atuLogAsInfo(String info)
	{
		
		ATUReports.add(info, LogAs.INFO,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		System.out.println(info);
	}

	public void atuLogAsFail(String info)
	{
		
		ATUReports.add(info, LogAs.FAILED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		System.err.println(info);
		

	}

	public void atuLogAsPass(String info)
	{
	
		ATUReports.add(info, LogAs.PASSED,new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		System.out.println(info);
	}

	public void atuLogAsPass(String info,String expected, String actual)
	{	
	ATUReports.add(info, expected, actual, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}

	public void atuLogAsFail(String info,String expected, String actual)
	{	
		
		ATUReports.add(info, expected, actual, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		System.err.println(info+" expected = "+expected+" Actual = "+actual);
		
	}
}
