package com.dac.testcases.SA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.Test;

import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.SA_ReviewReportCard_Page;
import com.dac.main.POM_SA.SA_gatherData;

import junit.framework.Assert;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.IAutoconst;
import resources.formatConvert;

/**
 * Before executing this Test Class make sure that you have added updated Data File as 
 * file Name&Path: ".\\filesToUpload\\NewSample.xlsx"
 * sheetName = "Sheet1"		*/
public class SA_RevewReportCard_Test extends BaseClass {

	static String dataFile = ".\\data\\NewSample.xlsx", sheetName = "Sheet1";
	
	@Test(enabled = true)
	public void navigateToRRC() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		//np.select_DB_Lang_Link("en", "US");
		np.navigateToSA_ReportCard();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Review Report Card page", "yes");
	}
	
	
	@Test(groups= {"smoke"}, description = "With/Without applying filters, verification of calculated RRCscores and Grades with data comparision")
	public void rrcCalScenaraio1() throws Exception {
		try {	
			int count = 1;
			ExcelHandler wb = new ExcelHandler("./data/FilterCriteria.xlsx", "SA_RRC"); wb.deleteEmptyRows();
			SA_ReviewReportCard_Page s = new SA_ReviewReportCard_Page(CurrentState.getDriver());
			SA_gatherData sd = new SA_gatherData();
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
				s.clickApplyFilterBTN();
				BaseClass.addEvidence(CurrentState.getDriver(),
						"Applied global filter: "+Group+", "+CountryCode+", "+State+", "+City+", "+Location+"", "yes");
				
				if(s.isDataAvailable()) {
					
					String excelTimePeriod = wb.getCellValue(i, wb.seacrh_pattern("TimePeriod", 0).get(0).intValue()); //fetch the TimePeriod Criteria from an excel
					String timePeriodValue = (s.selectTimePeriod(excelTimePeriod)).trim();  
					
					String b = wb.getCellValue(i, wb.seacrh_pattern("FilterBySource", 0).get(0).intValue());
					String[] c = {b};
					if(b.contains(",")) {
						c = StringUtils.stripAll(b.split(","));
					}
					s.filterBySource(c);
					
					BaseClass.addEvidence(CurrentState.getDriver(),
							"Applied timePeriod : "+timePeriodValue+ " and filterBySource : "+Arrays.toString(c)+"", "yes");
					
					List<Long> cleintRefId = getClientRefs(downloadExcel());
					String[] calScore = sd.calculateRRC_Scores(dataFile, sheetName, new Integer(timePeriodValue), Arrays.asList(c), cleintRefId);
					s.isRRCScoreCorrect(calScore[0], calScore[1]);	
					dataComparision();
				}
				Thread.sleep(4000);
				System.out.println("-------------- Scenarios : "+ count++ + "Ends --------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
			//Assert.fail("");
		}
	}
	
	/**
	 * This method used compare the Exported excel sheet with UI table data		*/
	public void dataComparision() throws Exception {
		SA_ReviewReportCard_Page s = new SA_ReviewReportCard_Page(CurrentState.getDriver());
		addEvidence(CurrentState.getDriver(), "Exported the RRC table data", "yes");
		s.getReviewTableData();
		s.compareXlData_UIdata();	
	}
	
	/**
	 * This method is used to download the excel and convert into .xlsx from .csv file	
	 * @return : converted file name	*/
	private String downloadExcel() throws Exception {
		SA_ReviewReportCard_Page s = new SA_ReviewReportCard_Page(CurrentState.getDriver());
		s.clickExportBTN();
		String fileName = BasePage.getLastModifiedFile("./downloads");
		String newfilename = new formatConvert("./downloads/"+fileName).convertFile("xlsx");
		ExcelHandler a = new ExcelHandler("./downloads/"+newfilename, "Sheet0"); a.deleteEmptyRows();
		//System.out.println("newfilename : "+newfilename);
		Thread.sleep(10000);
		return newfilename;
	}
	
	/**
	 * This method is used to return the clientID's of the applied filter	
	 * @return : List of ClientID's in form of List<Long>	*/
	public List<Long> getClientRefs(String newfilename) throws Exception {
		ExcelHandler a = new ExcelHandler("./downloads/"+newfilename, "Sheet0");
		List<Long> cRefCriteria = new ArrayList<Long>();
		for(int i=1; i<= a.getRowCount(); i++) {
			cRefCriteria.add(Long.parseLong(a.getCellValue(i, a.seacrh_pattern("ClientRef", 0).get(0))));
		}
		CurrentState.getLogger().info("RefCriteria's based on the Applied Criteria : "+Arrays.toString(cRefCriteria.toArray()));
		//System.out.println("cRefCriteria : "+Arrays.toString(cRefCriteria.toArray()));
		return cRefCriteria;
	}
}
