package com.dac.testcases.TPSEE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Groups;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class TPSEE_Groups_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Groups data;
	ExcelHandler wb;
	String Group=null;
	
	//Navigation to All Groups Page
	@Test(priority= 1, groups = { "smoke" }, description = "Test for navigating to Groups page")
	public void navigateToAllGroupsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllGroups();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE All Groups page");
		addEvidence(CurrentState.getDriver(), "Navigate to All Groups page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	//Get data from the table
	/*@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToAllGroupsPage"}, groups = {
					"smoke" }, description = "Test to read data from the table")
		public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		data.getTableData();
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
	}*/
	
	@SuppressWarnings("unchecked")
	@Test(priority= 2, groups = {"smoke" }, description = "Test to create group")
		public void CreateTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		 wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1"); wb.deleteEmptyRows();
		  Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
			String Description = wb.getCellValue(1, wb.seacrh_pattern("Description", 0).get(0).intValue());
			TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
			for(int i=1;i<=wb.getRowCount();i++) {
				if(i>1) CurrentState.getDriver().navigate().refresh();
				s.waitUntilLoad(CurrentState.getDriver());				
				String CountryField = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
				String FilterCondition = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
				String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
				
				System.out.println(Group+"is created");
				System.out.println(Group+", "+Description+", "+Search_Term);
		data.create_Group(Group , Description , CountryField , FilterCondition, Search_Term);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
		break;
	}
			
	}
	
	@Test(priority= 3, groups = {"smoke" }, description = "Test to view group")
	public void ViewTable() throws Exception {
	data = new TPSEE_Groups(CurrentState.getDriver());
	ArrayList<String> UIRules = new ArrayList();
	ArrayList<String> Rules = new ArrayList();
	wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1"); wb.deleteEmptyRows();
	TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
	
		//if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		//String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("Group"+Group);
		String field = wb.getCellValue(1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules.add(field);
		String condn = wb.getCellValue(1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules.add(condn);
		String srch = wb.getCellValue(1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules.add(srch);
		System.out.println(Rules);
		Thread.sleep(5000);
		UIRules = data.view_Group(Group);
		
		System.out.println(UIRules);
		Assert.assertEquals(Rules, UIRules);
	addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
			
}	

		@Test(priority= 4, groups = {"smoke" }, description = "Test to edit group")
		public void EditTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1"); wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		
			//if(i>1) CurrentState.getDriver().navigate().refresh();
			s.waitUntilLoad(CurrentState.getDriver());
			//String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
			System.out.println(Group);
			Thread.sleep(5000);
			data.edit_Group(Group);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
			
	}
		
		@Test(priority= 6, groups = {"smoke" }, description = "Test to create multiple criteria")
		public void AddFilterTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1"); wb.deleteEmptyRows();
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
        String Description = wb.getCellValue(1, wb.seacrh_pattern("Description", 0).get(0).intValue());
        TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
        int i =1;
        //for(int i=1;i<=wb.getRowCount();i++) {
            //if(i>1) CurrentState.getDriver().navigate().refresh();
            s.waitUntilLoad(CurrentState.getDriver());               
			String CountryField = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
            String FilterCondition = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
            String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());   
			String CountryField1 = wb.getCellValue(i+1, wb.seacrh_pattern("Field", 0).get(0).intValue());
			String FilterCondition1 = wb.getCellValue(i+1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
			String Search_Term1 = wb.getCellValue(i+1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
			String CountryField2 = wb.getCellValue(i+2, wb.seacrh_pattern("Field", 0).get(0).intValue());
			String FilterCondition2 = wb.getCellValue(i+2, wb.seacrh_pattern("Condition", 0).get(0).intValue());
			String Search_Term2 = wb.getCellValue(i+2, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
			Thread.sleep(5000);
			data.addFilter_Group(Group , Description , CountryField , FilterCondition, Search_Term, CountryField1 , FilterCondition1, Search_Term1, CountryField2 , FilterCondition2, Search_Term2);
			
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
		}		
	

	
	@SuppressWarnings("unchecked")
	@Test(priority= 5, groups = {"smoke" }, description = "Test to delete group")
		public void DeleteTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1"); wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		//for(int i=1;i<=wb.getRowCount();i++) {
			//if(i>1) CurrentState.getDriver().navigate().refresh();
			s.waitUntilLoad(CurrentState.getDriver());
			//String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
			System.out.println(Group);
			data.delete_Group(Group);
		//addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
		//}
	}
	
	@Test(priority= 7, groups = {"smoke" }, description = "Test to verify multiple criteria")
	public void VerificationTable() throws Exception {
	data = new TPSEE_Groups(CurrentState.getDriver());
	ArrayList<String> UIRules1 = new ArrayList();
	ArrayList<String> Rules1 = new ArrayList();
	wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1"); wb.deleteEmptyRows();
	TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
	for(int i=1;i<=wb.getRowCount();i++) {
		if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		//String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		String field1 = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field1);
		String condn1 = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn1);
		String srch1 = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch1);
		
		/*String field2 = wb.getCellValue(i+1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field2);
		String condn2 = wb.getCellValue(i+1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn2);
		String srch2 = wb.getCellValue(i+1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch2);
		
		String field3 = wb.getCellValue(i+2, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field3);
		String condn3 = wb.getCellValue(i+2, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn3);
		String srch3 = wb.getCellValue(i+2, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch3);*/
	}
		System.out.println(Rules1);
		Thread.sleep(5000);
		UIRules1 = data.verification(Group);
		System.out.println(UIRules1);
		Assert.assertEquals(Rules1, UIRules1);
		//Collections.sort(UIRules1);
        //Collections.sort(Rules1);
        
       // System.out.println(UIRules1);
      //  UIRules1.equals(Rules1);
	addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
			
}	
	
	/*@Test(priority= 5, groups = {"smoke" }, description = "Add Account Level and Group Level Keyword")
public void verifyApplyKeywords() throws Exception {
data = new TPSEE_Groups(CurrentState.getDriver());
try {
	 wb = new ExcelHandler("D:\\Excel\\Fetching_Values.xlsx", "Sheet1"); wb.deleteEmptyRows();
	TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
	for(int i=1;i<=wb.getRowCount();i++) {
		if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String Description = wb.getCellValue(i, wb.seacrh_pattern("Description", 0).get(0).intValue());
		String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		System.out.println(Group+", "+Description+", "+Search_Term);
		s.create_Group(Group , Description , Search_Term);

		
		System.out.println();
		//s.clickApplyKeyword();
		BaseClass.addEvidence(CurrentState.getDriver(),
				"Applied Account and Group Level Keywords: "+Group+", "+Description+", "+Search_Term+"", "yes");
	}
}catch(Exception e) {
	e.printStackTrace();
}
}*/
	
	
}
