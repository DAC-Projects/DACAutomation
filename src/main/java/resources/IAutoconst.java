package resources;
// auth centre login properties
public interface IAutoconst {
	
	String CHROME_KEY="webdriver.chrome.driver";
	String CHROME_VALUE="./driver/chromedriver.exe";
	
	String IE_KEY="webdriver.ie.driver";
	String IE_VALUE="./driver/IEDriverServer.exe";
	
	String GECKO_KEY="webdriver.gecko.driver";
	String GECKO_VALUE="./driver/geckodriver.exe";
	
	String RS_XL_PATH="./data/RS_CampaignE2E.xlsx";
	String PHOTO_PATH="./photo/";
	
	String CONFIG_PATH="./data.properties";
	
	// Auth centre variables
	String url ="http://dac-map-beta.azurewebsites.net/#",
	 email="tthomas@dacgroup.com",
	 password="DACQA123";

	//account credentials
	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
	String[] transparenSEE = {baseUrl+"international-account-beta-beta", "stebinvarghese@gmail.com"};


	
	
	

}
