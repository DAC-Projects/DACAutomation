package com.dac.testcases.SE;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_Visibility_Page;
import com.dac.main.POM_SE.SE_Post_Page;
import com.dac.main.POM_SE.SE_Report_Page;
import com.dac.main.POM_TPSEE.TPSEE_ESRreports_Page;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;

import junit.framework.Assert;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CompareReportsData_PostData extends BaseClass{
	SE_Post_Page data;
	
	ExcelHandler wb ;
	Navigationpage np = null;
	ArrayList<String> excelvalues;
	ArrayList<String> UIValues;
	String Location;
	String word =null;
	@Test(enabled = true)
	public void navigateToSE_PostPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_Post();
        addEvidence(CurrentState.getDriver(), "Navigated to Social Posts Page", "yes");
	}
	
	 @Test(dependsOnMethods = { "navigateToSE_PostPage"}, groups= {"smoke"}, description = "Test for overview export and export verification")
	 public void create_PostPage_FB() throws Exception {
		 excelvalues=new ArrayList<String>();
		 data = new SE_Post_Page(CurrentState.getDriver());
		 			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Message.xlsx", "MSG"); wb.deleteEmptyRows();
				SE_Post_Page s = new SE_Post_Page (CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String site = wb.getCellValue(i, wb.seacrh_pattern("Site Login", 0).get(0).intValue());
				    String location = wb.getCellValue(i, wb.seacrh_pattern("Location/Brand", 0).get(0).intValue());
			        String text = wb.getCellValue(i, wb.seacrh_pattern("Content", 0).get(0).intValue());
			        String creator = wb.getCellValue(i, wb.seacrh_pattern("Creator", 0).get(0).intValue());
			        String Vendor = wb.getCellValue(i, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
			        excelvalues.add(site);
			        excelvalues.add(location);
			        excelvalues.add(text);
			        excelvalues.add(creator);
			        System.out.println("Excel"+ excelvalues);
					s.create_PostforFB(text,location,Vendor);				
					addEvidence(CurrentState.getDriver(), "", "yes");
					count++;
				}			
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
		 addEvidence(CurrentState.getDriver(), "Create post page", "yes"); 
		 }}
	 
	/* @Test(dependsOnMethods =  "create_PostPage_FB", groups= {"smoke"}, description = "Test for overview export and export verification")
	 public void create_PostPage_GMB() throws Exception {
		 excelvalues=new ArrayList<String>();
		 data = new SE_Post_Page(CurrentState.getDriver());
		 			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Message.xlsx", "MSG1"); wb.deleteEmptyRows();
				SE_Post_Page s = new SE_Post_Page (CurrentState.getDriver());
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					 CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String site = wb.getCellValue(1, wb.seacrh_pattern("Site Login", 0).get(0).intValue());
				    String location = wb.getCellValue(1, wb.seacrh_pattern("Location/Brand", 0).get(0).intValue());
			        String text = wb.getCellValue(1, wb.seacrh_pattern("Content", 0).get(0).intValue());
			        String creator = wb.getCellValue(1, wb.seacrh_pattern("Creator", 0).get(0).intValue());
			        String Vendor = wb.getCellValue(1, wb.seacrh_pattern("Vendor", 0).get(0).intValue());
			        String from_day = wb.getCellValue(2, 0);  // from day
			        String from_month = wb.getCellValue(2, 1);  // from month
			        String from_year = wb.getCellValue(2, 2);  // from year
			        String to_day = wb.getCellValue(2, 3);  // to day
			        String to_month = wb.getCellValue(2, 4);  // to month
			        String to_year = wb.getCellValue(2, 5);  // to year 
			        excelvalues.add(site);
			        excelvalues.add(location);
			        excelvalues.add(text);
			        excelvalues.add(creator);
			        System.out.println("Excel"+ excelvalues);
					s.create_PostforGMB(text,location, from_day, from_month, from_year, to_day, to_month, to_year);				
					addEvidence(CurrentState.getDriver(), "", "yes");
					count++;
				
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
		 addEvidence(CurrentState.getDriver(), "Create post page", "yes"); 
	 }
	 
	 @Test(dependsOnMethods = { "create_PostPage_GMB"}, groups= {"smoke"}, description = "Test for overview export and export verification")
	 public void content_Management() throws Exception {
		 np = new Navigationpage(CurrentState.getDriver());
		 np.navigateToSE_ContentManagement();
		 data = new SE_Post_Page(CurrentState.getDriver());
		 SE_Post_Page s = new SE_Post_Page (CurrentState.getDriver());
		 UIValues =s.table();
		 for(int i =0;i<=UIValues.size() - 1;i++) {
			 UIValues.get(i).contains(excelvalues.get(i));
		 }
		
	 }
	 @Test(dependsOnMethods = { "content_Management"}, groups= {"smoke"}, description = "Test for overview export and export verification" )
	 public void keyword_Search() {
		 np = new Navigationpage(CurrentState.getDriver());
		 np.navigateToSE_Post();
		 excelvalues=new ArrayList<String>();
		 data = new SE_Post_Page(CurrentState.getDriver());
		 			try {	
				int count = 1;
				wb = new ExcelHandler("./data/Message.xlsx", "KEYWORD"); wb.deleteEmptyRows();
				SE_Post_Page s = new SE_Post_Page (CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					s.waitUntilLoad(CurrentState.getDriver());
					word = wb.getCellValue(i, wb.seacrh_pattern("Keyword", 0).get(0).intValue());
					System.out.println("Excel"+ word);
					s.applyKey(word);						
					addEvidence(CurrentState.getDriver(), "", "yes");
					count++;
				}			 
			}catch(Exception e) {
				e.printStackTrace();
			}
		 addEvidence(CurrentState.getDriver(), "Keywords", "yes"); 		 
}

	 
	 @Test(dependsOnMethods = "verifyFilteringReportsSocial", dataProvider="testData")
	 public void applyDate(String from_day, String from_month, String from_year, String to_day, String to_month, String to_year) throws Exception {
		 SE_Post_Page sp = new SE_Post_Page(CurrentState.getDriver());
		 sp.selectCalender_FromDate((int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
		 sp.selectCalender_ToDate((int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));		 
		 addEvidence(CurrentState.getDriver(), "Date FIlter", "yes");
		//  sp.getCurrentfromDate();
		 // sp.getCurrenttoDate();
			sp.clickApplyFilterBTN();
			  sp.ForTabsscenario(word,Location);

		  addEvidence(CurrentState.getDriver(), "Different Tabs", "yes");
		}
	 
	 
	
	 
	 @DataProvider
				public String[][] testData(){
	                String[][] data = null, data1 = null;
	                try {
	                ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "Sheet1");
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
	
	//Test for applying filters to Visibilty Page
		@Parameters({ "Filter" })
		@Test(dependsOnMethods = { "keyword_Search" }, groups = {
				"smoke" }, description = "Verify social page loads after filter applied")
		public void verifyFilteringReportsSocial() throws Exception {
			data = new SE_Post_Page(CurrentState.getDriver());
			try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Filter.xlsx", "Sheet1"); wb.deleteEmptyRows();
				SE_Post_Page s = new SE_Post_Page(CurrentState.getDriver());
				for(int i=1;i<=wb.getRowCount();i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					s.waitUntilLoad(CurrentState.getDriver());
					String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
					String CountryCode = wb.getCellValue(i, wb.seacrh_pattern("Country", 0).get(0).intValue());
					String State = wb.getCellValue(i, wb.seacrh_pattern("State", 0).get(0).intValue());
					String City = wb.getCellValue(i, wb.seacrh_pattern("City", 0).get(0).intValue());
					 Location = wb.getCellValue(i, wb.seacrh_pattern("Location", 0).get(0).intValue());
					s.applyGlobalFilter(Group, CountryCode, State, City, Location);
					System.out.println(Group+", "+CountryCode+", "+State+", "+City+", "+Location);
					//s.clickApplyFilterBTN();
					//s.flag();
					//s.flag1();
					BaseClass.addEvidence(CurrentState.getDriver(),
							"Applied global filter: "+Group+", "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
				}
					}catch(Exception e) {
				e.printStackTrace();
			}
		}
	 }
/*	@Test(enabled = true, dataProvider = "testData")
	public void isReports_PostDataCountEqual(String vendor, String graph_criteria, String from_day, String from_month,
									         String from_year, String to_day, String to_month, String to_year) {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_Report();
		addEvidence(CurrentState.getDriver(), "Navigated to Review Report Card page", "yes");
		SE_Report_Page s = new SE_Report_Page(CurrentState.getDriver());
		SE_Post_Page sp = new SE_Post_Page(CurrentState.getDriver());
		

		System.out.println("vendor : "+vendor+" graph_criteria : "+graph_criteria);
		String fromDate = s.forVendor(vendor).clickHighchartCriteria(graph_criteria).getCurrentFromDate();
		
		if(!(from_day.equals("null")) | !(to_day.equals("null")) ) {
			s.selectCalender_FromDate((int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
			s.selectCalender_ToDate((int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));			
		}
		
		String toDate =s.getCurrentToDate();
		System.out.println("fromDate : "+fromDate+" toDate : "+toDate);
		int reportDataCount = s.getGraphsTotalDataCount();
		np.navigateToSE_Post();
		sp.enterFromDate(fromDate);
		sp.enterToDate(toDate);
		sp.clickApplyFilter();
		
		//s.getGraphsTotalDataCount();
		int postDataCount = sp.forVendor(vendor).getTotalTableDataCount();
		addEvidence(CurrentState.getDriver(), "reportDataCount : "+reportDataCount+" postDataCount : "+postDataCount, "no");
		System.out.println("reportDataCount : "+reportDataCount+" postDataCount : "+postDataCount);
		Assert.assertEquals(reportDataCount, postDataCount);
	}
	
	@DataProvider
	public String[][] testData(){
		String[][] data = null, data1 = null;
		try {
		ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "SA_Reports_Post");
		wb.deleteEmptyRows();
		int rowCount = wb.getRowCount();
		//System.out.println("rowCount : "+rowCount);
		data = new String[rowCount-1][8];
		data1 = new String[rowCount-1][8];
		int row = 0;
			 for(int i = 2; i<=rowCount;i++) {
				 int colCount = wb.getColCount(i);
				 for(int j = 0;j<colCount; j++) {
					 //System.out.println("row : "+row+" j : "+j+" i : "+i);
					 //System.out.println("wb.getCellValue(i, j).trim() : "+wb.getCellValue(i, j).trim());
					 if(j > 0) {
						 if(((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null")) | 
								 ((wb.getCellValue(i, j).trim()).length() == 0)){
							 data1[row][j] = "null";
						 }else data1[row][j] = wb.getCellValue(i, j).trim();
					 }else data1[row][j] = wb.getCellValue(i, j).trim();
					 //System.out.println(wb.getCellValue(i, j));					 
				 }
				 row++;
			 }
			 data[0][0] = wb.getCellValue(2, 0);  //vendor
			 data[0][1] = wb.getCellValue(2, 1);  // highchart_Criteria
			 data[0][2] = wb.getCellValue(2, 2);  // from day
			 data[0][3] = wb.getCellValue(2, 3);  // from month
			 data[0][4] = wb.getCellValue(2, 4);  // from year
			 data[0][5] = wb.getCellValue(2, 5);  // to day
			 data[0][6] = wb.getCellValue(2, 6);  // to month
			 data[0][7] = wb.getCellValue(2, 7);  // to year
			 System.out.println("Arrays.deepToString(data1) : "+Arrays.deepToString(data1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			return data1;
		}
	}*/


