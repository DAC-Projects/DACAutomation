package com.dac.testcases.CA;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dac.main.Navigationpage;
import com.dac.main.POM_CA.CA_exports;
import com.dac.main.POM_CA.CA_gatherData;
import com.dac.main.POM_CA.FormulaEvaluator;
import com.relevantcodes.extentreports.LogStatus;

import resources.BaseTest;
import resources.Utilities;
import resources.formatConvert;

public class CA_test extends BaseTest{
	
	CA_exports exports;

	@Test
	public void verifyCACalculation() throws Exception {
		 Navigationpage np = new Navigationpage(driver);
		np.navigateCA_Visibility();
		logger.log(LogStatus.INFO, "Navigate to visibility page");
		Utilities.addScreenshot(driver, imgnames.get(0).toString());
		new CA_exports(driver).getVisibilityExport();
		logger.log(LogStatus.PASS, "Succesfully exported Visibility report");
		np.navigateCA_Accuracy();
		Utilities.addScreenshot(driver, imgnames.get(1).toString());
		logger.log(LogStatus.INFO, "Navigate to accuracy page");
		new CA_exports(driver).getAccuracyExport();
		logger.log(LogStatus.PASS, "Succesfully exported accuracy report");
		new formatConvert(
				"./CA_aggregation_files/company-documents.csv").convertFile("xlsx");
		logger.log(LogStatus.INFO, "Calculating scores from aggregation file and report exports ... ");
		new CA_gatherData("./CA_aggregation_files/company-documents.xlsx","company_documents").evaluteScores();
		logger.log(LogStatus.PASS, "Succesfully calculated visibility, accuracy, content analysis and review report");
	}

	
}
