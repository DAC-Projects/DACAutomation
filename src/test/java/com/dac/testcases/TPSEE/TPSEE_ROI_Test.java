package com.dac.testcases.TPSEE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_ROI;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_ROI_Test  extends BaseClass{
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_ROI data;
	double sum1, sum2;
	@Test(groups = { "smoke" }, description = "Test for navigating to ROI")
	public void navigateToROI() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		//np.navigateToROI();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE ROI");
		addEvidence(CurrentState.getDriver(), "Navigate to ROI from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	@Test(dependsOnMethods = "navigateToROI",/* dataProvider="testData",*/ description = "Getting the ROI values")
	public void VerifyROIvalues() throws Exception {
		data = new TPSEE_ROI(CurrentState.getDriver());
		np = new Navigationpage(CurrentState.getDriver());
	data.Nav_GMB();
		data.GMB();
		 np.navigateToROI();
		 data.ROIvalues();
	}
		 /* data.val_pass();
		 sum1=data.to();
		 data.avg();
		 data.Nav_GMB();
		 data.selectCalender_FromDate1((int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
		 data.selectCalender_ToDate1((int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));	
		 data.GMB();
		 np.navigateToROI();
		 data.selectCalender_FromDate((int)(Double.parseDouble(from_day)), from_month, (int)(Double.parseDouble(from_year)));
		 data.selectCalender_ToDate((int)(Double.parseDouble(to_day)), to_month, (int)(Double.parseDouble(to_year)));
		 data.ROIvalues();
		 data.val_pass();
		  sum2=data.to();
		 data.avg();
		 data.sel_options();
		 data.Sym_veri();
	
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

	@Test(dependsOnMethods = { "VerifyROIvalues" }, groups = {
	"smoke" }, description = "Getting the ROI values")
	public void navigateToROI_Verify() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		//np.navigateToROI();
		data = new TPSEE_ROI(CurrentState.getDriver());
		data.ROIvalues();
		data.tot(sum2);
		data.avg();
		data.sel_options();
		data.Sym_veri();
	}
	*/
}
