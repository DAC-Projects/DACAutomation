package com.dac.testcases.TPSEE;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Settings_Mousehover;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Settings_Mousehover_Test extends BaseClass {
	
	Navigationpage np;
	TPSEE_Settings_Mousehover data;
	
	/**
	 * test to verify mouse hover text
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to verify mousehover text")
	public void verifymousehovertext() throws Exception {
		data = new TPSEE_Settings_Mousehover(CurrentState.getDriver());
		data.verifyMousehoverText();
	}

}
