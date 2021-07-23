package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.dac.main.POM_TPSEE.TPSEE_Language_List_Verification;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_LanguageList_Verification_Test extends BaseClass {
	
	TPSEE_Language_List_Verification data;
	
	/**
	 * Test to get and verify language list
	 */
	@Test(priority = 1, description = "Test to verify languages listed in dashboard")
	public void verifylanguageslisted() throws Exception {
		data = new TPSEE_Language_List_Verification(CurrentState.getDriver());
		data.VerifyLanguageList();
		addEvidence(CurrentState.getDriver(), "Test to verify languages", "yes");
	}
}
