package com.dac.testcases.SA;


import java.util.Arrays;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.SA_Reviews_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class SA_Reviews_Test extends BaseClass{

	Navigationpage np;
	SA_Reviews_Page data;
	String From_Date = "//*[@id='dateFrom']";
	String To_Date = "//*[@id='dateTo']";
	double dashreview;
	String DashRnPs;
	String PageRNPS;
	double pageavgreview;
	
	
	/**
	 * Test to get Dashboard Values
	 * @throws InterruptedException
	 *//*
	@Test(enabled = true, priority =1)
	public void getDashboardreview() throws InterruptedException {
		data = new SA_Reviews_Page(CurrentState.getDriver());
		dashreview = data.getavgreview();		
		DashRnPs = data.getDashRnpsscore();
	}*/
	
	
	/**
	 * Test to navigate to Review Report Page
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void navigateToRRC() throws Exception {
		np=new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewReport();
		addEvidence(CurrentState.getDriver(), "Navigated to Review Report Card page", "yes");
	}
	
	/**
	 * Test to compare Dashboard value with page value
	 * @throws Exception
	 */
	/*@Test(priority = 3)
	public void Getoverallscore() throws Exception {
		data = new SA_Reviews_Page(CurrentState.getDriver());
		pageavgreview = data.averagescore();
		
		Assert.assertEquals(pageavgreview, dashreview);
		PageRNPS = data.getRNPS();
		
		Assert.assertEquals(PageRNPS, DashRnPs);
		addEvidence(CurrentState.getDriver(), "Get Overall Scores", "yes");		
	}*/
	
	/**
	 * Location filter parameters
	 * @throws Exception
	 */
	public void verifyFilteringReportsVisibility() throws Exception {
		data = new SA_Reviews_Page(CurrentState.getDriver());
			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Filters"); wb.deleteEmptyRows();
				SA_Reviews_Page s = new SA_Reviews_Page(CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
					String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
					String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
					String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
					String Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
					s.applyGlobalFilter(Group, CountryCode, State, City, Location);
					System.out.println(Group+", "+CountryCode+", "+State+", "+City+", "+Location);					
					BaseClass.addEvidence(CurrentState.getDriver(),
							"Applied global filter: "+Group+", "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	/**
	 * Apply Global Filters
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception
	 */
	@Test(priority = 4, dataProvider = "testData")
	public void DateFilter(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {		
		data = new SA_Reviews_Page(CurrentState.getDriver());	
		addEvidence(CurrentState.getDriver(), "Verified navigation to Review report", "yes");
		verifyFilteringReportsVisibility();
		
		data.selectCalender_FromDate(From_Date,(int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
		Thread.sleep(5000);
		data.selectCalender_ToDate(To_Date,(int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));
		
		data.clickApplyFilterBTN();
		addEvidence(CurrentState.getDriver(), "Applied Global Filters", "yes");
	}
	
	/**
	 * To get scores in review report page
	 * @throws Exception
	 *//*
	@Test(priority = 5)
	public void Getallscore() throws Exception {
		data = new SA_Reviews_Page(CurrentState.getDriver());
		data.averagescore();
		
		data.numberofreviews();
		
		data.getpositive();
		
		data.getnegative();
		
		data.getRNPS();
		addEvidence(CurrentState.getDriver(), "Get Overall Scores", "yes");		
	}*/
	
	/**
	 * Test to export Location Data
	 * @throws Exception
	 */
	/*@Test(priority = 6)
	public void GetLocationExportData() throws Exception {
		data = new SA_Reviews_Page(CurrentState.getDriver());
		data.LocationExport();
		addEvidence(CurrentState.getDriver(), "Download CSV file", "yes");
	}*/
	
	/**
	 * Date comparision with Global filter calender and highcharts date
	 * @throws Exception
	 */
	/*@Test(priority = 7)
	public void CompareHighchartsDatewithOverallDate() throws Exception {
		data = new SA_Reviews_Page(CurrentState.getDriver());
		Date overallfromDate = data.getCurrentfromDate();
		addEvidence(CurrentState.getDriver(), "Get Overall from Date", "yes");
		
		Date overallToDate = data.getCurrenttoDate();
		addEvidence(CurrentState.getDriver(), "Get Overall to Date", "yes");
		
		Date HighchartsfromDate = data.getFromDateHighcharts();
		addEvidence(CurrentState.getDriver(), "Get highcharts from Date", "yes");
		
		Date HighchartsToDate = data.getToDateHighcharts();
		addEvidence(CurrentState.getDriver(), "Get highcharts to Date", "yes");
		Assert.assertEquals(HighchartsfromDate, overallfromDate);
		Assert.assertEquals(HighchartsToDate, overallToDate);
	}*/
	

	/**
	 * Test to verify zoom functionality 
	 * @throws Exception
	 */
		
		/*@Test(priority = 8,groups = {"smoke"},
			description ="Verify Zoom Functionality")
		public void gethighchartsdate() throws Exception{
			data = new SA_Reviews_Page(CurrentState.getDriver());
			try {
			String OneMonth ="1m";
			data.clickHighchartCriteria(OneMonth);
			addEvidence(CurrentState.getDriver(), "one Month Zoom functionality", "yes");
			Getallscore();
			
			CompareHighchartsDatewithOverallDate();
			
			String ThreeMonths = "3m";
			data.clickHighchartCriteria(ThreeMonths);
			addEvidence(CurrentState.getDriver(), "Three Month Zoom functionality", "yes");
			Getallscore();
			
			CompareHighchartsDatewithOverallDate();
			
			String SixMonths = "6m";
			data.clickHighchartCriteria(SixMonths);
			addEvidence(CurrentState.getDriver(), "Six Month Zoom functionality", "yes");
			Getallscore();
			
			CompareHighchartsDatewithOverallDate();
			
			String OneYear = "1y";
			data.clickHighchartCriteria(OneYear);
			addEvidence(CurrentState.getDriver(), "One Year Zoom functionality", "yes");
			Getallscore();
			
			CompareHighchartsDatewithOverallDate();
			
			String YearToDate ="ytd";
			data.clickHighchartCriteria(YearToDate);
			addEvidence(CurrentState.getDriver(), "Year to Date Zoom functionality", "yes");
			Getallscore();
			
			CompareHighchartsDatewithOverallDate();
			
			String ALLDATA = "all";
			data.clickHighchartCriteria(ALLDATA);
			addEvidence(CurrentState.getDriver(), "All Data Zoom functionality", "yes");
			Getallscore();
			
			CompareHighchartsDatewithOverallDate();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}*/
		
		/**
		 * To Compare Reviews Export and UI
		 * @throws Exception 
		 */
		@Test(priority = 9)
			public void compareUIandXLData() throws Exception {
				data = new SA_Reviews_Page(CurrentState.getDriver());
				data.ReviewsExport();
				addEvidence(CurrentState.getDriver(),"Export Reviews", "Yes");
				
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Filters"); wb.deleteEmptyRows();
				String Text = null;
				for(int i=1;i<=wb.getRowCount();i++) {
					if(i>1) CurrentState.getDriver().navigate().refresh();
					data.waitUntilLoad(CurrentState.getDriver());
					Text = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
				}
					data.CompareReviewTableDatawithExport(Text);
				addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
		}
		
	/**
	 * Test to apply source filter
	 * @throws Exception
	 */
		@Test(priority = 10)
		public void compareUISourceData() throws Exception {
			data = new SA_Reviews_Page(CurrentState.getDriver());
			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Advanced_Filters"); wb.deleteEmptyRows();
				SA_Reviews_Page s = new SA_Reviews_Page(CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String text = wb.getCellValue(i, wb.seacrh_pattern("Source", 0).get(0).intValue());
					s.applySourceFilter(text);
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
					s.comparesourcewithreviews();
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
	}
		
		@Test(priority = 11)
		public void compareUITagsearchData() throws Exception {
			data = new SA_Reviews_Page(CurrentState.getDriver());
			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Advanced_Filters"); wb.deleteEmptyRows();
				SA_Reviews_Page s = new SA_Reviews_Page(CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String text = wb.getCellValue(i, wb.seacrh_pattern("Tags", 0).get(0).intValue());
					s.applySearchTagtext(text);
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
					s.compareTagswithreviews();
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
	}
		
		@Test(priority = 12)
		public void compareUIKeywordsearchData() throws Exception {
			data = new SA_Reviews_Page(CurrentState.getDriver());
			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Advanced_Filters"); wb.deleteEmptyRows();
				SA_Reviews_Page s = new SA_Reviews_Page(CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String text = wb.getCellValue(i, wb.seacrh_pattern("Keyword", 0).get(0).intValue());
				
					s.applyKeywordSearchtext(text);
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
					s.compareKeywordswithreviews(text);
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
	}
		
		
		@Test(priority = 13)
		public void compareUISentimentData() throws Exception {
			data = new SA_Reviews_Page(CurrentState.getDriver());
			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Advanced_Filters"); wb.deleteEmptyRows();
				SA_Reviews_Page s = new SA_Reviews_Page(CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String text = wb.getCellValue(i, wb.seacrh_pattern("Sentiment", 0).get(0).intValue());
					s.applySentiment(text);
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
					s.compareSentimentswithreviews(text);
					addEvidence(CurrentState.getDriver(),"Get UI Reviews, Ref-Code, Business Name and Links", "Yes");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}			
	}
		
		
		
		
		/**
		 * Test to verify Top button functionality
		 * @throws Exception
		 */
			@Test(priority = 10)
			public void GetTopBtn() throws Exception {
				data = new SA_Reviews_Page(CurrentState.getDriver());
				data.TopButton();
				addEvidence(CurrentState.getDriver(), "Top Button click verification", "yes");
			}	
		
	
	@Test(priority = 14)
	public void compareUISentimentCategoryData() throws Exception {
		data = new SA_Reviews_Page(CurrentState.getDriver());
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Advanced_Filters"); wb.deleteEmptyRows();
			SA_Reviews_Page s = new SA_Reviews_Page(CurrentState.getDriver());
			for(int i=1;i<=wb.getRowCount();i++) {
				System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
				if(i>1) CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());
				String text = wb.getCellValue(i, wb.seacrh_pattern("Sentiment Category", 0).get(0).intValue());
				s.applySentimentCategory(text);
				addEvidence(CurrentState.getDriver(),"Applied Sentiment Category Filter", "Yes");
				s.compareSentimentsCategorywithreviews(text);
				addEvidence(CurrentState.getDriver(),"Search for Sentiment Category", "Yes");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}			
	} 
	
	@DataProvider
	public String[][] testData(){
		String[][] data = null, data1 = null;
		try {
		ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "SA_Zoom");
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
}
