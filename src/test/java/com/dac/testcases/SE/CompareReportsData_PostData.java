package com.dac.testcases.SE;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.SE_Post_Page;
import com.dac.main.POM_SE.SE_Report_Page;


import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CompareReportsData_PostData extends BaseClass{

	Navigationpage np = null;
	
	/*@Test(enabled = true)
	public void navigateToSE_ReportsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		//np.select_DB_Lang_Link("en", "US");
		np.navigateToSE_Report();
		//np.navigateToSE_Post();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Review Report Card page", "yes");
	}*/
	
	@Test(enabled = true, dataProvider = "testData")
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
	}
	
}
