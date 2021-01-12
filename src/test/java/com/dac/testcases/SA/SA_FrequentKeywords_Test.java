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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.dac.main.BasePage;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.SA_FrequentKeywords_Page;
import com.dac.main.POM_SA.SA_Reviews;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.formatConvert;

public class SA_FrequentKeywords_Test extends BaseClass {
	String exlScore[];
	@Test(enabled = true)
	public void navigateToFrequentKeywords() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		//np.select_DB_Lang_Link("en", "US");
		np.navigateToSA_FrequentKeywords();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Frequent Keywords Page", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"navigateToFrequentKeywords"})
	public void applyDatefilter() throws Exception {
		SA_FrequentKeywords_Page fk = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		fk.addFromDate("01/01/2020");
		Thread.sleep(5000);
		addEvidence(CurrentState.getDriver(), "Date Filter Applied", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"applyDatefilter"})
	public void ExportFK() throws Exception {
		String exportedData=null;
		SA_FrequentKeywords_Page s = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		s.clickExportBTN();
		Thread.sleep(3000);
		String fileName = (BasePage.getLastModifiedFile("./downloads")).trim();
		System.out.println("fileName : "+fileName);
		List<String> reviewReportData = readDataFromCSV("./downloads/"+fileName);
		System.out.println("########################################");
		for(int i = 0; i<1; i++) {
			exportedData = reviewReportData.get(i + 1);
			System.out.println(exportedData);
		}
		System.out.println("#############################################");
		exlScore=exportedData.split(",");
		for (String element: exlScore) {
            System.out.println(element);
        }
//		System.out.println(Arrays.toString(exlScore));
	}
	@Test(enabled = true, dependsOnMethods= {"ExportFK"})
	public void verifyScore() throws Exception {
		SA_FrequentKeywords_Page fk = new SA_FrequentKeywords_Page(CurrentState.getDriver());
		String kw=exlScore[0];
		String uiScore=fk.getFKScore(kw);
		Thread.sleep(5000);
		System.out.println("uiScore : "+uiScore);
		addEvidence(CurrentState.getDriver(), "Score Verification Completed", "yes");
	}
	/*
	/**
	 * This method is used to read the each line from extracted .csv file as a object of list
	 * and return the <List>	
	 * 
	 * @param fileName : pass the ".csv" file name including path and extension if the file. To get the data*/
	private static List<String> readDataFromCSV(String fileName) { 
		List<String> fkdata = new ArrayList<>();
		Path pathToFile = Paths.get(fileName); // create an instance of BufferedReader // using try with resource, Java 7 feature to close resources 
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) { // read the first line from the text file
			String line = br.readLine().trim(); // loop until all lines are read 
			
			while (line !=null ) { // use string.split to load a string array with the values from 
				System.out.println("line123 : "+line);
				String strNew = line.replace("\"", "");
				String[] attributes =  strNew.split("\n");
				for(int i=0; i< attributes.length; i++) {
					fkdata.add(attributes[i]);
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
		return fkdata;
	}
}
