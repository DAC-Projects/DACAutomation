package com.dac.testcases.SA;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.Quick_Responses;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Quick_Responses_Approver_CopyandTransfer_Test extends BaseClass {

	Navigationpage np;
	Quick_Responses data;
	
	@Test(priority = 1, description = "Test to navigate to Response Management Page")
	public void NavigateToResponseManagement() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSA_ReviewsFeed();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Response Management Page", "yes");
	}
	
	@Test(priority = 2, description = "Test to copy quick response from one group to another")
	public void CopyQuickResponse() throws Exception {
		data = new Quick_Responses(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String GrouptobeCopied = wb.getCellValue(2, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The group responses to be copied : " +GrouptobeCopied);
		String GrouptobeCopiedfrom = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The group response to be copied from : " +GrouptobeCopiedfrom);
		data.CopyQuickResponses(GrouptobeCopied, GrouptobeCopiedfrom);
		Thread.sleep(3000);
		data.verifyCopyOrTransferToGroup(GrouptobeCopied, GrouptobeCopiedfrom);
	}
	
	@Test(priority = 3, description = "Test to transfer quick response from one group to another")
	public void TransferQuickResponse() throws Exception {
		data = new Quick_Responses(CurrentState.getDriver());
		ExcelHandler wb = new ExcelHandler("./data/Reviews.xlsx", "Quick Response");
		wb.deleteEmptyRows();
		String GrouptobeCopied = wb.getCellValue(2, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The group responses to be copied : " +GrouptobeCopied);
		String GrouptobeCopiedfrom = wb.getCellValue(1, wb.seacrh_pattern("Group", 0).get(0).intValue());
		System.out.println("The group response to be copied from : " +GrouptobeCopiedfrom);
		data.TransferQuickResponses(GrouptobeCopied, GrouptobeCopiedfrom);
		Thread.sleep(3000);
		data.verifyCopyOrTransferToGroup(GrouptobeCopied, GrouptobeCopiedfrom);
		Thread.sleep(3000);
		data.verifyTransferFromGroup(GrouptobeCopied, GrouptobeCopiedfrom);
	}
}
