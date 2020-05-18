package com.dac.testcases.SE;

import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.Manage_Brand_Page;

import resources.BaseClass;
import resources.CurrentState;

public class Manage_Brand_Test extends BaseClass{

	Navigationpage np;
	Manage_Brand_Page bp;
	
	
	@Test(priority=1,groups= {"smoke"},description = "Test for navigating to Brand page")
	public void navigateToBrandPage() {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_Brand();
	}
	
	@Test(priority=2,groups= {"smoke"},description = "Test for delete exixting Brand")
	public void deleteBrandIfExist() throws InterruptedException {
		 bp = new Manage_Brand_Page(CurrentState.getDriver());
		 bp.deleteBrand("Auto", "des");
		
	}
	
	
	@Test(priority=3,groups= {"smoke"},description = "Test for Add new Brand")
	public void addBrand() throws InterruptedException {
		
	bp.addbrand("Auto", "des");
	}
	
	@Test(priority=4,groups= {"smoke"},description = "Test for Add new Brand")
	public void editBrand() throws InterruptedException {
		
	bp.addbrand("Auto", "des");
	}
	
}


