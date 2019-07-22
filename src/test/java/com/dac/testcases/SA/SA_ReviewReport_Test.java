package com.dac.testcases.SA;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
//import com.dac.main.POM_SA.SA_ReviewReport_Page;
//import com.dac.main.POM_SA.SA_ReviewReport_Page.reviews;
import com.dac.main.POM_SA.SA_Reviews;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.formatConvert;

public class SA_ReviewReport_Test extends BaseClass {

	@Test(enabled = true)
	public void navigateToRRC() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		//np.select_DB_Lang_Link("en", "US");
		np.navigateToSA_ReviewReport();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Review Report Card page", "yes");
	}
		
	@Test(enabled = true, dependsOnMethods= {"navigateToRRC"})
	public void dataComparision() throws Exception {
		SA_Reviews s = new SA_Reviews(CurrentState.getDriver());
		ArrayList<String> dataRead = s.allLocationInfo();
		s.clickExportBTN();
		Thread.sleep(3000);
		String fileName = (BasePage.getLastModifiedFile("./downloads")).trim();
		System.out.println("fileName : "+fileName);
		List<String> reviewReportData = readDataFromCSV("./downloads/"+fileName);
		System.out.println("########################################");
		for(int i = 0; i<dataRead.size(); i++) {
			String exportedData = reviewReportData.get(i + 1);
			String data = dataRead.get(i);
			if(exportedData.contains(data)) System.out.println("pass");
			else System.out.println("fail");
		}
		System.out.println("#############################################");
		
	}
	
	/**
	 * This method is used to read the each line from extracted .csv file as a object of list
	 * and return the <List>	
	 * 
	 * @param fileName : pass the ".csv" file name including path and extension if the file. To get the data*/
	private static List<String> readDataFromCSV(String fileName) { 
		List<String> reviewReportData = new ArrayList<>();
		Path pathToFile = Paths.get(fileName); // create an instance of BufferedReader // using try with resource, Java 7 feature to close resources 
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) { // read the first line from the text file
			String line = br.readLine().trim(); // loop until all lines are read 
			
			while (line !=null ) { // use string.split to load a string array with the values from 
				System.out.println("line : "+line);
				String[] attributes =  line.split("\n");
				for(int i=0; i< attributes.length; i++) {
					reviewReportData.add(attributes[i]);
				}
				if((line = br.readLine()) !=null) {
					line = line.trim();
					if(line.equals("")) line = null; 
				}
			}
		} 
		catch (IOException ioe) { 
			ioe.printStackTrace(); 
		}
		return reviewReportData;
	}
}
