package resources;
// Auth centre login properties
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
	String url ="String url =\"https://manage.dacgroup.com/auth/sign_in",

	 email="wakram@dacgroup.com",
	 password="DACgroup123";

	//account credentials
	String baseUrl="https://manage.dacgroup.com/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
	String[] transparenSEE = {baseUrl+"international-account-beta-beta", "svarghese@dacgroup.com"};
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "svarghese@dacgroup.com"};
	String[] transparenSEE_Prod_Asheley = {baseUrl+"ashley-furniture-3", "ginnyhampton@icloud.com"};
	String[] transparenSEE_Prod_Casella = {baseUrl+"casella-waste-systems-inc", "tconderman@dacgroup.com"};
}
