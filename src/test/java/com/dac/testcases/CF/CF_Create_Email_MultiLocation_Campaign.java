package com.dac.testcases.CF;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_CF.CF_Campaigns_Page;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class CF_Create_Email_MultiLocation_Campaign extends BaseClass{

	Navigationpage np;
	CF_Campaigns_Page data;
	ExcelHandler wb;
	String CampStart = "//input[@id='campStartDt']";
	String CampEnd = "//input[@id='campEndDt']";
	
	/**
	 * Navigate to Campaign Page
	 * 
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Navigate to Campaigns Page")
	public void navigateToCampaignPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToCampaignsPage();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Campaigns page");
		addEvidence(CurrentState.getDriver(), "Navigate to Content Management page from Dashboard", "yes");
	}
	
	@Test(priority = 2, description = "Test to create email Multi-Location campaign", dependsOnMethods = "navigateToCampaignPage", dataProvider = "testData")
	public void CreateBrandEmailCampaign(String from_day, String from_month, String from_year, String to_day,
			String to_month, String to_year) throws Exception {
		wb = new ExcelHandler("./data/CF.xlsx", "EmailMultiLocation");
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		for (int i = 1; i <= wb.getRowCount(); i++) {
			String CampaignType = wb.getCellValue(i, wb.seacrh_pattern("CamType", 0).get(0).intValue());
			System.out.println("The Campaign Type is:" + CampaignType);
			String CampaignOption = wb.getCellValue(i, wb.seacrh_pattern("CamOption", 0).get(0).intValue());
			System.out.println("The Campaign Option is :" + CampaignOption);
			String CampLanguage = wb.getCellValue(i, wb.seacrh_pattern("CamLang", 0).get(0).intValue());
			System.out.println("The Campaign Language is :" + CampLanguage);
			String CampaignName = wb.getCellValue(i, wb.seacrh_pattern("CamName", 0).get(0).intValue());
			System.out.println("The Campaign Name is :" + CampaignName);
			String CampaignDescription = wb.getCellValue(i, wb.seacrh_pattern("CamDes", 0).get(0).intValue());
			System.out.println("The Campaign Description is :" + CampaignDescription);
			data.CampaignInfo(CampaignType, CampaignOption, CampLanguage, CampaignName, CampaignDescription);
			data.Reschedulecampname(i,"EmailMultiLocation");
			String sender = wb.getCellValue(i, wb.seacrh_pattern("Sender", 0).get(0).intValue()); 
			System.out.println("The sender is :" +sender);
			String Subject = wb.getCellValue(i, wb.seacrh_pattern("Email Subject", 0).get(0).intValue()); 
			System.out.println("The subject is :" +Subject);
			String Banner = wb.getCellValue(i, wb.seacrh_pattern("Email Banner", 0).get(0).intValue());
			System.out.println("The Banner is :" +Banner);
			String Body = wb.getCellValue(i, wb.seacrh_pattern("Email Body", 0).get(0).intValue()); 
			System.out.println("The Body is :" +Body);
			String Sign = wb.getCellValue(i, wb.seacrh_pattern("Email Signature", 0).get(0).intValue());
			System.out.println("The sign is :" +Sign);
			data.EmailMultiLocSetUp(sender, Subject, Banner, Body, Sign);
			addEvidence(CurrentState.getDriver(), "Test to add details in scheduleing tab", "yes");
			ECampaignSchedule(from_day, from_month, from_year, to_day, to_month, to_year);
			data.CampaignScheduling("Date");
			addEvidence(CurrentState.getDriver(), "Test to add time", "yes");
			data.ThankYouPage("EmailMultiLocation");
			data.SummaryPage();
		}
	}
	
	/**
	 * Test to preview campaign
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4, dependsOnMethods = "CreateBrandEmailCampaign", description = "Test to search the created campaign in scheduled table")
	public void PreviewCampaign() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		data.PreviewSchedule("EmailMultiLocation");
		addEvidence(CurrentState.getDriver(), "Test to verify Campaign Preview", "Yes");
		CurrentState.getDriver().navigate().refresh();
	}

	/**
	 * Test to delete existing campaign
	 * 
	 * @throws Exception
	 */
	@Test(priority = 5, dependsOnMethods = "PreviewCampaign", description = "Test to Delete Campaign")
	public void DeleteCreatedCampaign() throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		data.DeleteCampaign("EmailMultiLocation");
	}
	
	/**
	 * Test to enter Select date and time
	 * 
	 * @param from_day
	 * @param from_month
	 * @param from_year
	 * @param to_day
	 * @param to_month
	 * @param to_year
	 * @throws Exception
	 */
	public void ECampaignSchedule(String from_day, String from_month, String from_year, String to_day, String to_month,
			String to_year) throws Exception {
		data = new CF_Campaigns_Page(CurrentState.getDriver());
		data.selectCalender_FromDate(CampStart, (int) (Double.parseDouble(from_day)), from_month,
				(int) (Double.parseDouble(from_year)));
		data.selectCalender_ToDate(CampEnd, (int) (Double.parseDouble(to_day)), to_month,
				(int) (Double.parseDouble(to_year)));
		addEvidence(CurrentState.getDriver(), "Test to Schedule Campaign", "yes");
	}
	
	@SuppressWarnings("finally")
	@DataProvider
	public String[][] testData() {
		String[][] data = null, data1 = null;
		try {
			ExcelHandler wb = new ExcelHandler("./data/CF.xlsx", "Date");
			wb.deleteEmptyRows();
			int rowCount = wb.getRowCount();
			System.out.println("rowCount : " + rowCount);
			data = new String[rowCount - 1][6];
			data1 = new String[rowCount - 1][6];
			int row = 0;
			for (int i = 2; i <= rowCount; i++) {
				int colCount = wb.getColCount(i);
				for (int j = 0; j < colCount; j++) {
					if (j > 0) {
						if (((wb.getCellValue(i, j).trim()).equalsIgnoreCase("null"))
								| ((wb.getCellValue(i, j).trim()).length() == 0)) {
							data1[row][j] = "null";
						} else
							data1[row][j] = wb.getCellValue(i, j).trim();
					} else
						data1[row][j] = wb.getCellValue(i, j).trim();
				}
				row++;
			}
			data[0][0] = wb.getCellValue(2, 0); // from day
			data[0][1] = wb.getCellValue(2, 1); // from month
			data[0][2] = wb.getCellValue(2, 2); // from year
			data[0][3] = wb.getCellValue(2, 3); // to day
			data[0][4] = wb.getCellValue(2, 4); // to month
			data[0][5] = wb.getCellValue(2, 5); // to year
			System.out.println("Arrays.deepToString(data) : " + Arrays.deepToString(data));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return data;
		}
	}
}