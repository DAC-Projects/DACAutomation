package com.dac.testcases;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.dac.main.Dashboardpage;
import com.dac.main.Navigationpage;
import com.dac.main.VisibilityPage;
import com.relevantcodes.extentreports.LogStatus;


import resources.BaseTest;
import resources.ReadExcel;
import resources.Utilities;


public class VisibilityTest extends BaseTest {

	Navigationpage nav;
	Dashboardpage Dp; 
	VisibilityPage Vp; 
	JavascriptExecutor js;	
	Actions action ;
	String locationNo;
	String visibilityScore;
	
	ReadExcel excel; 
	
	 @Test(priority=1)
	 public void veriy_DashboardKPIScore() throws Exception {
	 try {
		 
		 Dp =new Dashboardpage(driver);
	 Thread.sleep(20000);
	 locationNo= Dp.getLocations().getText();
	 visibilityScore = Dp.getVisibility().getText().replaceAll("%", "");;
	 logger.log(LogStatus.INFO, "Read visibility score and locations from Dashboard");
	 System.out.println("locationNo: "+locationNo+"\n"+"visibility score:"+visibilityScore);

	 
	 } catch (InterruptedException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	
	 }

	@Test(priority=2)
	public void navigateVisibilityPage() throws Exception {
		try {
			
			 nav= new Navigationpage(driver);
			nav.getVisibility().click();
			logger.log(LogStatus.INFO, "Navigated to visibility page");
		} catch(ElementNotVisibleException e)
		{
			logger.log(LogStatus.FAIL, "Couldn't navigate to visibility page");
		}
			
	}
	
	
		
		@Test(priority=3)	
		public void readGraph() throws Exception {	
			try {
			js	 = (JavascriptExecutor)driver;	
			Vp =  new VisibilityPage(driver);
			action =  new Actions(driver);
			Thread.sleep(20000);
			WebElement graph = Vp.getScoreGraph();
			js.executeScript("arguments[0].scrollIntoView();", graph);
			action.moveToElement(graph).moveByOffset((graph.getSize().getWidth() / 2) - 2, 0).click().perform();
			
			// read tooltip
			String graphTooltip = Vp.getScoreGraphTooltipDate().getText();
			String[] tooltip = graphTooltip.split("\\r?\\n");
			String date = tooltip[0];
			String locationsNo = tooltip[1].replaceAll("Number of Locations:", "");
			String ScoreValue =  tooltip[2].replaceAll("Score Value:", "");
			System.out.println("date: "+date+ "graphlocationNo: "+locationsNo+"\n"+"graphvisibility score:"+ScoreValue);
			Utilities.addScreenshot(driver, imgnames.get(0).toString());
			 Assert.assertEquals(locationsNo, locationNo);
			 Assert.assertEquals(ScoreValue, visibilityScore);
			 logger.log(LogStatus.INFO, "Asserted graph values to Dashboard values");
			}catch(ElementNotVisibleException e)
			{
				logger.log(LogStatus.FAIL, "Asseting Scores graph tooltip and Dashboard tooltip failed ");
			}
		}
		
		
		
			// Using filter filter to any location and then repeat from scroll down until
			// screenshot. Verify graph is displayed.
			// log global filters are loading correctly

			
			
			
			//Bars found and not found
			//below code will not execute a s not found locations tooltip not visible in beta. in prod this may not occur
//			int counter=0;
//			for (WebElement bar : Vp.getGlobalBarsFound()) {
//				js.executeScript("arguments[0].scrollIntoView(false);", bar);
//				counter=counter+1;
//				action.moveToElement(bar).build().perform();
//				String foundloactions = Vp.getNoOfLoctnsTooltip().getText();
//				System.out.println("Tooltip from Found bar"+foundloactions);
//				
//				if (foundloactions != "0") {
//					action.moveToElement(bar).click().build().perform();
//					Thread.sleep(2000);
//					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//					String tabledata = Vp.getCountOfLoctns().getText();
//					String count = tabledata.substring(27, 31);
//					System.out.println("from table data count:"+count);
//					Assert.assertEquals(count, foundloactions);
//					logger.log(LogStatus.PASS, "found locations matched in bar tooltip and table for Globals bars bar no:"+ counter);
//				} else {
//					
//					logger.log(LogStatus.INFO, "found locations found blank for Global bar:"+counter);
//				}
//				
//				
//			}
			
			
		@Test(priority=4)	
		public void testBars() throws Exception {	
			js = (JavascriptExecutor)driver;
			action =  new Actions(driver);
			int counter=0;
			for (WebElement bar : Vp.getGlobalBarsNtFound()) {
				js.executeScript("arguments[0].scrollIntoView(false);", bar);
				counter=counter+1;
				action.moveToElement(bar).build().perform();
				String foundloactions = Vp.getNoOfLoctnsTooltip().getText();
				System.out.println("Tooltip from Found bar"+foundloactions);
				
				if (foundloactions != "0") {
					action.moveToElement(bar).click().build().perform();
					Thread.sleep(2000);
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					Thread.sleep(2000);
					String tabledata = Vp.getCountOfLoctns().getText();
					
					String count = StringUtils.substringBetween(tabledata, "(of ", " entries)");
					//removed- String count = tabledata.substring(27, 29);
					System.out.println("from table data count:"+count);
					Assert.assertEquals(count, foundloactions);
					logger.log(LogStatus.PASS, "not found locations matched in bar tooltip and table for Globals bars bar no:"+ counter);
				} else {
					
					logger.log(LogStatus.INFO, "not found locations found blank for Global bar:"+counter);
				}
				
			}
			}
			
			
			
			@Test(priority=5)	
			public void applyGlobalFilter() throws Exception {
			try {
			action =	 new Actions(driver);
			js =   (JavascriptExecutor)driver;	
			js.executeScript("window.scrollTo(0, 0)");

			action.moveToElement(Vp.getFilterCountry()).doubleClick().build().perform();
			WebDriverWait wait = new WebDriverWait(driver, 10);			 
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.menu.transition.visible>div:nth-child(2)")));
			element.click();
			Thread.sleep(1000);
			action.moveToElement(Vp.getFilterState()).clickAndHold().click(Vp.getStateOption1()).build().perform();;
			Thread.sleep(1000);
			action.moveToElement(Vp.getFilterCity()).clickAndHold().click(Vp.getCityOption1()).build().perform();;
			Thread.sleep(2000);
			action.moveToElement(Vp.getFilterlocation()).clickAndHold().click(Vp.getLocationoption1()).build().perform();;
			Thread.sleep(1000);
			Vp.getApply_filter().click();
			js.executeScript("arguments[0].scrollIntoView(false);",  Vp.getScoreGraph());
			Assert.assertTrue( Vp.getScoreGraph().isDisplayed());
			logger.log(LogStatus.INFO, "filtered down visibility report and graph is present");

			} catch (ElementNotVisibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
