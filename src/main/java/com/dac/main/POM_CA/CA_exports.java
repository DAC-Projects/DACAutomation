package com.dac.main.POM_CA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import resources.BaseTest;
import resources.FileHandler;
import resources.formatConvert;

public class CA_exports  extends CA_Visibility_Page implements CARepository {

	WebDriver driver;
	
	public CA_exports(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}

	public void getallExports() throws InterruptedException, FileNotFoundException, IOException {
		getVisibilityExport();
		getAccuracyExport();
		getSummaryExport();
		getContentAnalysisExport();
		getReviewExport();
		
	}

	public void getReviewExport() {
		// TODO Auto-generated method stub
		
	}

	private void getContentAnalysisExport() {
		// TODO Auto-generated method stub
	
	}

	private void getSummaryExport() {
		// TODO Auto-generated method stub
		
	}

	public void getAccuracyExport() throws InterruptedException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Thread.sleep(5000);
		new CA_Visibility_Page(driver).exportaccuracyReport();
		Thread.sleep(2000);
		convertExports(getLastModifiedFile(Exportpath), AccuracyExport);
		
	}

	public void getVisibilityExport() throws InterruptedException, FileNotFoundException, IOException {
		Thread.sleep(5000);
		new CA_Visibility_Page(driver).exportvisibilityReport();
		Thread.sleep(10000);
//		convertExports(getLastModifiedFile(Exportpath), VisibilityExport);
	}
	
	public static void convertExports(String filename, String export) throws FileNotFoundException, IOException {
		// write code to export visibility reports here
		String visibility_export = new formatConvert(
				Exportpath+filename).convertFile("xlsx");
		FileHandler.renameTo(new File(Exportpath + visibility_export), Exportpath + export);

		
	}
}
