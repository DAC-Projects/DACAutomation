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
	String url ="http://dac-map-beta.azurewebsites.net/#",

	 email="rvmenon@dacgroup.com",
	 password="DacQa123";

	//account credentials
	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
//	String baseUrl="https://manage.dacgroup.com/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
	String[] deepfieldAccount_SE = {baseUrl+"deepfield-software-incorporated-beta", "vijayata_201989@yahoo.com"};
	//String[] transparenSEE = {baseUrl+"international-account-beta-beta", "svarghese@dacgroup.com"}; //AO beta
	//String[] transparenSEE = {baseUrl+"international-account-beta-beta", "newlocation@test.com"}; //LM beta
	//String[] transparenSEE = {baseUrl+"international-location-test-account","chappel.mann+stc@gmail.com"};
	//String[] transparenSEE = {baseUrl+"international-location-test-account","chappel.mann+stl@gmail.com"};
	//String[] transparenSEE = {baseUrl+"international-location-test-account","india@test.com"}; // AO Staging
	String[] transparenSEE = {baseUrl+"international-location-test-account","uae@test.com"}; // LM Staging
	//String[] transparenSEE = {baseUrl+"automation-test-account-tsee-beta","amahadev@dacgroup.com"}; //AO 
	//String[] transparenSEE = {baseUrl+"automation-test-account-tsee-beta","jmann@dacgroup.com"};
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "svarghese@dacgroup.com"};
	//String[] Configuration = {baseUrl+"social-automation-beta", "rohitmenon8055@gmail.com"};
	//String[] Configuration = {baseUrl+"social-beings-beta", "rohit.watson@gmail.com"};
	String[] Configuration = {baseUrl+"auto-assign-gmb-beta", "rohitmenon8055@gmail.com"}; //AO
	//String[] Configuration = {baseUrl+"auto-assign-gmb-beta", "rvmenon@dacgroup.com"}; //LM
}
