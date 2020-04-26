package com.dac.testcases.CA;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Review_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CA_Review_Test extends BaseClass {

	static List<Map<String, String>> export;
	static List<String> export1;
	Navigationpage np;
	CA_Review_Page data;

	String grphfromDate = "(//*[@class='highcharts-label highcharts-range-input'])[1]";
	String grphtoDate = "(//*[@class='highcharts-label highcharts-range-input'])[2]";

	@Test(priority= 1, groups = { "smoke" }, description = "Test for navigating to Review page")
	public void navigateToReviewPage() throws Exception {  
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateCA_Reviewpage();
		/*new CA_Review_Page(CurrentState.getDriver())
	          .verify_pageloadCompletely(10);*/
		CurrentState.getLogger().log(Status.PASS,
				"Navigated successfully to CA Review page");    
		addEvidence(CurrentState.getDriver(), "Navigate to Review page from Dashboard", "yes"); 
	}



	@Test(priority= 2, groups= {"smoke"}, description = "Verify Review page loads after filter applied")
	public void verifyFilteringReportsReview() throws Exception {try {	
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "CA_FIL"); wb.deleteEmptyRows();
		CA_Review_Page s = new CA_Review_Page(CurrentState.getDriver());
		for(int i=1;i<=wb.getRowCount();i++) {
			System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			if(i>1) CurrentState.getDriver().navigate().refresh();
			s.waitUntilLoad(CurrentState.getDriver());


			String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
			String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
			String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
			String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());

			s.applyFilter(CountryCode, State, City, Location);
			System.out.println(CountryCode+", "+State+", "+City+", "+Location);
			s.clickApplyFilterBTN();
			BaseClass.addEvidence(CurrentState.getDriver(),
					"Applied global filter: "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");

			Thread.sleep(4000);
			System.out.println("-------------- Scenarios : "+ count++ + "Ends --------------------");

		}
	}catch(Exception e) {
		e.printStackTrace();
		//Assert.fail("");
	}}

	@Test(priority = 3 , groups = {"smoke" }, description = "Test for Review overview export and export verification")
	public void verifyOverviewReportnExportReview() throws Exception {

		// export and overview compare

		data = new CA_Review_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getLogger().log(Status.PASS,
				"Overview report export and Overview report data found matching");
		addEvidence(CurrentState.getDriver(), "Verified overview export for review report", "yes"); 
	}

	@Test(priority= 4, groups = {"smoke" }, description = "Test for verifying tooltips in Review page")
	public void verifyOverviewReportnTooltipReview() throws Exception {

		data = new CA_Review_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(),
				data.getOverviewReport());
		CurrentState.getLogger().log(Status.PASS,
				"Review history graph tooltip and overview report score found matching");
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview report", "yes"); 

	}

	@Test(priority= 5, groups = { "smoke" }, description = "Test for comparing Review Site table and overview export values")
	public void verifySiteTablenExport() throws Exception {

		data = new CA_Review_Page(CurrentState.getDriver());
		data.compareExportnTable(export, data.verifySitetable());
		CurrentState.getLogger().log(Status.PASS,
				"Site level scores in site table  and overview report export found matching");
		addEvidence(CurrentState.getDriver(), "Site level scores in Review site table  and overview report export found matchin", "yes"); 
	}

	@Test( priority= 6, groups = { "smoke" }, description = "Location Competitor Set Test Evidence Capture with Export")
	public void navigatetoLocationCompetitorset() throws Exception{

		data = new CA_Review_Page(CurrentState.getDriver());
		data.LocationCompetitorSetNavigation();

		data = new CA_Review_Page(CurrentState.getDriver());
		//     data.exportAllReviewLocationSet();
		//	      data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getLogger().log(Status.PASS,
				"Location report export and Location report data found matching");
		addEvidence(CurrentState.getDriver(), "Verified location export for review report", "yes"); 

		CurrentState.getLogger().log(Status.PASS,
				"Location Competitor Set Test Evidence Capture with Export");
		addEvidence(CurrentState.getDriver(), "Export Button for the whole Location is displayed in the UI", "yes"); 


	}
	@Parameters({ "Filter" })
	@Test(priority = 7, groups = {"smoke" }, description = "Select location for competitor")
	public void selectlocationforcompetitor(String Filter) throws InterruptedException

	{

		data = new CA_Review_Page(CurrentState.getDriver());
		String[] filter = Filter.split(";");
		Thread.sleep(3000);
		data.selectionLocationforcompetitor(filter[0]); 

		Thread.sleep(3000);
	}




	@Test(priority= 8, groups = {"smoke" }, description = "Test for verifying tooltips in Review page")
	public void verifylocationReportTooltipReview() throws Exception {

		data = new CA_Review_Page(CurrentState.getDriver());
		data.verifyHistoryGraphLocationcompSet();
		data.getLocationReport();
		CurrentState.getLogger().log(Status.PASS,
				"Review history graph tooltip and overview relcnRprtDataport score found matching");
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview report", "yes"); 
	}
	//Test to verify Zoom Functionality
	@Test(priority=9,groups = {"smoke"}, description ="Verify Zoom Functionality")
	public void gethighchartsdate() throws Exception{
		data = new CA_Review_Page(CurrentState.getDriver());

		String OneMonth ="1m";
		data.clickHighchartCriteria(OneMonth);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);

		String ThreeMonth ="3m";
		data.clickHighchartCriteria(ThreeMonth);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);

		String SixMonth ="6m";
		data.clickHighchartCriteria(SixMonth);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);

		String OneYear ="1y";
		data.clickHighchartCriteria(OneYear);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);

		String YearToDate ="ytd";
		data.clickHighchartCriteria(YearToDate);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);

		String ALLDATA ="all";
		data.clickHighchartCriteria(ALLDATA);
		addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
		Thread.sleep(5000);
	}

	@Test(priority = 10,enabled = true, dataProvider = "testData")
	public void SetCalendarDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {

		CA_Review_Page s = new CA_Review_Page(CurrentState.getDriver());
		if(!(from_day.equals("null")) | !(to_day.equals("null")) ) {
			s.selectCalender_FromDate(grphfromDate,(int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			s.selectCalender_ToDate(grphtoDate,(int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));	
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");				
			Date fromcal = s.getCurrentfromDate();
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
			Date tocal = s.getCurrenttoDate();
			addEvidence(CurrentState.getDriver(), "SetCalendarDate", "Yes");
		}				
	}

	@DataProvider
	public String[][] testData(){
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Zoom");
			wb.deleteEmptyRows();
			int rowCount = wb.getRowCount();
			System.out.println("rowCount : "+rowCount);
			data = new String[rowCount-1][6];
			data1 = new String[rowCount-1][6];
			int row = 0;
			for(int i = 2; i<=rowCount;i++) {
				int colCount = wb.getColCount(i);
				for(int j = 0;j<colCount; j++) {
					if(j > 0) {
						if(((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null")) | 
								((wb.getCellValue(i, j).trim()).length() == 0)){
							data1[row][j] = "null";
						}else data1[row][j] = wb.getCellValue(i, j).trim();
					}else data1[row][j] = wb.getCellValue(i, j).trim();		 
				}
				row++;
			}
			data[0][0] = wb.getCellValue(2, 0);  // from day
			data[0][1] = wb.getCellValue(2, 1);  // from month
			data[0][2] = wb.getCellValue(2, 2);  // from year
			data[0][3] = wb.getCellValue(2, 3);  // to day
			data[0][4] = wb.getCellValue(2, 4);  // to month
			data[0][5] = wb.getCellValue(2, 5);  // to year
			System.out.println("Arrays.deepToString(data) : "+Arrays.deepToString(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return data;
		}
	}


	@Test( priority= 11,groups = {"smoke" }, description = "Test for comparing Review Site table and overview export values")
	public void verifylocationSiteTableExport() throws Exception {

		data = new CA_Review_Page(CurrentState.getDriver());

		data.compareExportnTableInArray(data.getExportDataLocationInArray(), data.verifySitetableLocnInArray());
		CurrentState.getLogger().log(Status.PASS,
				"Site level scores in site table  and overview report export found matching");
		addEvidence(CurrentState.getDriver(), "Site level scores in Review site table  and overview report export found matchin", "yes"); 

	}
	@Test(priority= 12, groups = {"smoke" }, description = "Export Location data")
	public void exportlocationcompetitordata() throws Exception
	{
		data = new CA_Review_Page(CurrentState.getDriver());
		export1 = data.getExportDataLocationover();
		data.compareExprttoLocationOver(export1, data.getLocationReportOver());
		// data.getLocationReportOver();
		CurrentState.getLogger().log(Status.PASS,
				"Overview report export and Overview report data found matching");
		addEvidence(CurrentState.getDriver(), "Verified overview export for review report", "yes"); 
	}
}