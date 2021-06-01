package com.dac.testcases.TPSEE;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Site_Order;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Site_Order_Test extends BaseClass {
	
	Navigationpage np;
	TPSEE_Site_Order data;
	List<String> SiteList = new ArrayList<>();
	List<String> VendorList = new ArrayList<>();
	SoftAssert soft = new SoftAssert();
	
	/**
	 * test to navigate to site order page
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Test to navigate to Site Order")
	public void NavigateToSiteOrder() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSiteOrder();
		addEvidence(CurrentState.getDriver(), "Test to navigate to Site Order", "yes");
	}
	
	/**
	 * test to get site list
	 * @throws Exception
	 */
	@Test(priority = 2, description = "Test to get list of sites")
	public void getSiteList() throws Exception {
		data = new TPSEE_Site_Order(CurrentState.getDriver());
		SiteList = data.getSiteList();
		addEvidence(CurrentState.getDriver(), "Test to get site list", "yes");
	}
	
	/**
	 * test to navigate to visibility report
	 * @throws Exception
	 */
	@Test(priority = 3, description = "Test to navigate to visibility report")
	public void NavigateToVisibilityReport() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Visibility();
		addEvidence(CurrentState.getDriver(), "Test to navigate to visibility report", "yes");
	}
	
	/**
	 * test to get vendors in visibility and compare 
	 * @throws Exception
	 */
	@Test(priority = 4, description = "Test to get visibility vendor")
	public void getVisibilityVendors() throws Exception {
		data = new TPSEE_Site_Order(CurrentState.getDriver());
		VendorList = data.getVisibilitySite();
		addEvidence(CurrentState.getDriver(), "Test to get visibility vendors", "yes");
		if(SiteList.size() == VendorList.size()) {
			for(int i = 0; i <= SiteList.size() - 1; i++) {
				soft.assertEquals(SiteList.get(i), VendorList.get(i));
			}
		}
		soft.assertAll();
	}

}
