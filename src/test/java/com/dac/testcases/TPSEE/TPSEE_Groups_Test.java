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

public class TPSEE_Groups_Test extends BaseClass {

	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Groups data;
	ExcelHandler wb;
	String Group = null;

	/**
	 * navigating
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, groups = { "smoke" }, description = "Test for navigating to Groups page")
	public void navigateToAllGroupsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllGroups();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE All Groups page");
		addEvidence(CurrentState.getDriver(), "Navigate to All Groups page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}

	@Test(priority = 2)
	public void verifyText() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		data.VerifyTitleText("Groups",
				"Create a group to view information and reports based on criteria that is important to you.");
		addEvidence(CurrentState.getDriver(), "Verify Text", "yes");
	}

	@SuppressWarnings("unchecked")
	@Test(priority = 3, groups = { "smoke" }, description = "Test to delete group")
	public void InitialDeleteTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		// for(int i=1;i<=wb.getRowCount();i++) {
		// if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		data.delete_Group(Group);
		// addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
		// }
	}

	// Creating a Group with 1 rule
	@SuppressWarnings("unchecked")
	@Test(priority = 4, groups = { "smoke" }, description = "Test to create group")
	public void CreateTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String Description = wb.getCellValue(1, wb.seacrh_pattern("Description", 0).get(0).intValue());
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		for (int i = 1; i <= wb.getRowCount(); i++) {
			if (i > 1)
				CurrentState.getDriver().navigate().refresh();
			s.waitUntilLoad(CurrentState.getDriver());
			String CountryField = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
			String FilterCondition = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
			String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());

			System.out.println(Group + "is created");
			System.out.println(Group + ", " + Description + ", " + Search_Term);
			data.create_Group(Group, Description, CountryField, FilterCondition, Search_Term);
			addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
			break;
		}

	}

	// Viewing and Verification of Group with 1 Rule
	@Test(priority = 5, groups = { "smoke" }, description = "Test to view group")
	public void ViewTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		ArrayList<String> UIRules = new ArrayList();
		ArrayList<String> Rules = new ArrayList();
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());

		// if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		// String Group = wb.getCellValue(i, wb.seacrh_pattern("Group",
		// 0).get(0).intValue());
		System.out.println("Group" + Group);
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

	// Deleting a Group with 1 Rule
	@SuppressWarnings("unchecked")
	@Test(priority = 6, groups = { "smoke" }, description = "Test to delete group")
	public void DeleteTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());

		s.waitUntilLoad(CurrentState.getDriver());
		// String Group = wb.getCellValue(i, wb.seacrh_pattern("Group",
		// 0).get(0).intValue());
		System.out.println(Group);
		data.delete_Group(Group);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

	}

	// Creating a Group with 2 Rules
	@Test(priority = 7, groups = { "smoke" }, description = "Test to create multiple criteria with 2 Rules")
	public void Add2Rules() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String Description = wb.getCellValue(1, wb.seacrh_pattern("Description", 0).get(0).intValue());
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		int i = 1;

		s.waitUntilLoad(CurrentState.getDriver());
		String CountryField = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		String connector = wb.getCellValue(i, wb.seacrh_pattern("Connector", 0).get(0).intValue());
		String CountryField1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());

		Thread.sleep(5000);
		data.two_Rules_Group(Group, Description, CountryField, FilterCondition, Search_Term, CountryField1,
				FilterCondition1, Search_Term1, connector);

		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
	}

	// Verification of group with 2 rules
	@Test(priority = 8, groups = { "smoke" }, description = "Test to verify two criteria")
	public void Verification_two_Rule() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		ArrayList<String> UIRules2 = new ArrayList();
		ArrayList<String> Rules2 = new ArrayList();
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		int i = 1;

		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		String field1 = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules2.add(field1);
		String condn1 = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules2.add(condn1);
		String srch1 = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules2.add(srch1);

		String field2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules2.add(field2);
		String condn2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules2.add(condn2);
		String srch2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules2.add(srch2);

		String connectorname = wb.getCellValue(i, wb.seacrh_pattern("Connector", 0).get(0).intValue());
		System.out.println(connectorname);
		System.out.println(Rules2);
		Thread.sleep(5000);
		UIRules2 = data.verification_two_Rule(Group);
		System.out.println(UIRules2);

		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

		Assert.assertEquals(Rules2, UIRules2);
		String Connector = data.rulename();
		Assert.assertEquals(Connector, connectorname);
	}

	// Deletion of Group with 2 Rules
	@SuppressWarnings("unchecked")
	@Test(priority = 9, groups = { "smoke" }, description = "Test to delete group")
	public void Delete_two_Rules() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());

		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		data.delete_Group(Group);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

	}

	// Creating a Group with 3 Rules
	@Test(priority = 10, groups = { "smoke" }, description = "Test to create multiple criteria with 3 Rules")
	public void AddFilterTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String Description = wb.getCellValue(1, wb.seacrh_pattern("Description", 0).get(0).intValue());
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		int i = 1;

		s.waitUntilLoad(CurrentState.getDriver());
		String CountryField = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		String CountryField1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		String CountryField2 = wb.getCellValue(i + 2, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition2 = wb.getCellValue(i + 2, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term2 = wb.getCellValue(i + 2, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		String connector = wb.getCellValue(i + 1, wb.seacrh_pattern("Connector", 0).get(0).intValue());
		Thread.sleep(5000);
		data.addFilter_Group(Group, Description, CountryField, FilterCondition, Search_Term, CountryField1,
				FilterCondition1, Search_Term1, CountryField2, FilterCondition2, Search_Term2, connector);

		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
	}

	// Verification of Group with 3 Rules
	@Test(priority = 11, groups = { "smoke" }, description = "Test to verify multiple criteria with 3 rules")
	public void VerificationTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		ArrayList<String> UIRules1 = new ArrayList();
		ArrayList<String> Rules1 = new ArrayList();
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		int i = 1;

		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		String field1 = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field1);
		String condn1 = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn1);
		String srch1 = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch1);

		String field2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field2);
		String condn2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn2);
		String srch2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch2);

		String field3 = wb.getCellValue(i + 2, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field3);
		String condn3 = wb.getCellValue(i + 2, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn3);
		String srch3 = wb.getCellValue(i + 2, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch3);

		System.out.println(Rules1);
		Thread.sleep(5000);
		UIRules1 = data.BeforeEditverification(Group);
		System.out.println(UIRules1);
		Assert.assertEquals(Rules1, UIRules1);

		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

	}

	// Editing a Group with 3 Rules
	@Test(priority = 12, groups = { "smoke" }, description = "Test to edit group")
	public void EditTable() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet1");
		wb.deleteEmptyRows();
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet2");
		wb.deleteEmptyRows();
		String Group1 = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		String Description = wb.getCellValue(1, wb.seacrh_pattern("Description", 0).get(0).intValue());
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		int i = 1;
		// if(i>1) CurrentState.getDriver().navigate().refresh();
		s.waitUntilLoad(CurrentState.getDriver());
		// String Group = wb.getCellValue(1, wb.seacrh_pattern("Group",
		// 0).get(0).intValue());
		String CountryField = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		String CountryField1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term1 = wb.getCellValue(i + 1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		String CountryField2 = wb.getCellValue(i + 2, wb.seacrh_pattern("Field", 0).get(0).intValue());
		String FilterCondition2 = wb.getCellValue(i + 2, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		String Search_Term2 = wb.getCellValue(i + 2, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Thread.sleep(5000);
		System.out.println(Group);
		Thread.sleep(5000);
		System.out.println(Group1);
		// System.out.println(Group, Group1, Description, CountryField, FilterCondition,
		// Search_Term, CountryField1, FilterCondition1, Search_Term1, CountryField2,
		// FilterCondition2, Search_Term2);
		data.edit_Group(Group, Group1, Description, CountryField, FilterCondition, Search_Term, CountryField1,
				FilterCondition1, Search_Term1, CountryField2, FilterCondition2, Search_Term2);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

	}

	// Verification of Group with 3 Rules after editing
	@Test(priority = 13, groups = { "smoke" }, description = "Test to verify multiple criteria with 3 rules")
	public void VerificationTableAfterEdit() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		ArrayList<String> UIRules1 = new ArrayList();
		ArrayList<String> Rules1 = new ArrayList();
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet2");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		int i = 1;

		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(i, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		String field1 = wb.getCellValue(i, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field1);
		String condn1 = wb.getCellValue(i, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn1);
		String srch1 = wb.getCellValue(i, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch1);

		String field2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field2);
		String condn2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn2);
		String srch2 = wb.getCellValue(i + 1, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch2);

		String field3 = wb.getCellValue(i + 2, wb.seacrh_pattern("Field", 0).get(0).intValue());
		Rules1.add(field3);
		String condn3 = wb.getCellValue(i + 2, wb.seacrh_pattern("Condition", 0).get(0).intValue());
		Rules1.add(condn3);
		String srch3 = wb.getCellValue(i + 2, wb.seacrh_pattern("Search_Term", 0).get(0).intValue());
		Rules1.add(srch3);

		String connectorname = wb.getCellValue(i + 1, wb.seacrh_pattern("Connector", 0).get(0).intValue());
		System.out.println(connectorname);

		System.out.println(Rules1);
		Thread.sleep(5000);
		UIRules1 = data.verification(Group);
		System.out.println(UIRules1);
		Assert.assertEquals(Rules1, UIRules1);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
		String Connector = data.rulename();
		Assert.assertEquals(Connector, connectorname);

		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

	}

	// Deleting Group with 3 Rules
	@SuppressWarnings("unchecked")
	@Test(priority = 14, groups = { "smoke" }, description = "Test to delete group")
	public void DeleteTableThreeRules() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		wb = new ExcelHandler("./data/Groups.xlsx", "Sheet2");
		wb.deleteEmptyRows();
		TPSEE_Groups s = new TPSEE_Groups(CurrentState.getDriver());
		s.waitUntilLoad(CurrentState.getDriver());
		String Group = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println(Group);
		data.delete_Group(Group);
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");

	}
}
