package com.dac.testcases.SA;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.dac.main.BasePage;
import com.dac.main.Navigationpage;

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
	public void selectSource() throws Exception {
		SA_Reviews s = new SA_Reviews(CurrentState.getDriver());
		//s.locationInfo();
		s.allLocationInfo();
		/*s.clickExportBTN();
		Thread.sleep(3000);
		String fileName = (BasePage.getLastModifiedFile("./downloads")).trim();
		System.out.println("fileName : "+fileName);
		List<String> reviewReportData = readBooksFromCSV("./downloads/"+fileName);*/
		
	}
	
	private static List<String> readBooksFromCSV(String fileName) { 
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
