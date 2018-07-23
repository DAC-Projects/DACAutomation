package com.dac.testcases.CA;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_exports;
import com.dac.main.POM_CA.CA_gatherData;
import com.dac.main.POM_CA.FormulaEvaluator;

import resources.BaseTest;
import resources.formatConvert;

public class CA_test extends BaseTest{
	
	CA_exports exports;

	@Test
	public void verifyCACalculation() throws Exception {
		 Navigationpage np = new Navigationpage(driver);
		np.navigateCA_Visibility();
		new CA_exports(driver).getVisibilityExport();
		np.navigateCA_Accuracy();
		new CA_exports(driver).getAccuracyExport();
		new formatConvert(
				"./CA_aggregation_files/company-documents.csv").convertFile("xlsx");
		new CA_gatherData("./CA_aggregation_files/company-documents.xlsx","company_documents").evaluteScores();
	}

	
}
