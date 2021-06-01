package resources;

import org.testng.annotations.Parameters;

// Auth centre login properties
@Parameters({"User"})
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

	 email="amahadev@dacgroup.com",
	 password="DacQa123";
	
	//String data;
	//account credentials
	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
	String baseUrlStaging = "https://manage.dacgroup.com/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] CFAccountBeta = {baseUrl + "deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
	String[] NandithaAccount = {baseUrl+"nanditha-test-business-account-cf","wasimakramb325@gmail.com"};
	String[] transparenSEEDupData = {baseUrl+"aurify-brands-test-beta", "jmohan@dacgroup.com"}; // Duplicate Management and data syndication - Beta
	String[] transparenSEEBingLocation = {baseUrl+"aurify-brands-test-beta", "chappel.mann+loctest@gmail.com"}; // Location level - Bing
	String[] transparenSEEClientBeta = {baseUrl+"automation-test-account-tsee-beta","amahadev@dacgroup.com"}; // Client Level User - Beta
	//String[] transparenSEEStagingClient = {baseUrlStaging + "international-location-test-account", "chappel.mann+stc@gmail.com"}; // Client Level - International Account
	String[] transparenSEELocationBeta = {baseUrl+"automation-test-account-tsee-beta","jmann@dacgroup.com"}; // Location Level User - Beta
	//String [] transparenSEEStagingLocation = {baseUrlStaging +"international-location-test-account","chappel.mann+stl@gmail.com"};
	String [] transparenSEEStagingClient = {baseUrlStaging + "aurify-brands", "abritton@dacgroup.com"}; //Client Level{baseUrl +"aurify-brands","abritton@dacgroup.com"};  // Client Level User - Staging
	String [] transparenSEEStagingLocation = {baseUrlStaging +"aurify-brands","p.pundyk@fieldsgoodchicken.com"}; // Location Level User - Staging
	//String [] transparenSEEStagingClient = {baseUrlStaging + "tsee-staging-automation", "jmann@dacgroup.com"};
	//String [] transparenSEEStagingLocation = {baseUrlStaging +"tsee-staging-automation","amahadev@dacgroup.com"};
	String[] transparenSEEFaceBetaClient = {baseUrl + "international-account-beta-beta", "amahadev@dacgroup.com"};
	String[] DRSClient = {baseUrl + "international-location-test-account", "chappel.mann+stc@gmail.com"}; //Client Level
	String[] DRSLocation = {baseUrl + "international-location-test-account","chappel.mann+stl@gmail.com"}; // Location Level
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "skanna@dacgroup.com"};
	String[] neuralTuringTechAccountStaging = {baseUrlStaging + "neural-turing-tech" , "lzimerman@dacgroup.com"}; // Reviews Staging Approver
	String[] neuralTuringTechAccountResponseStaging = {baseUrlStaging + "neural-turing-tech" , "rpillai@dacgroup.com"}; // Reviews Staging creator
	String[] neuralResponseBeta = {baseUrl+"neural-turing-tech-beta","amahadev@dacgroup.com"}; // Response Management
	String[] OssingtonBeta = {baseUrl + "ossington-terminus-beta", "lzimerman@dacgroup.com"}; // Ossington Beta
	String[] OssingtonBetaCreator = {baseUrl + "ossington-terminus-beta", "rpillai@dacgroup.com"}; // Ossington Beta Creator
	String[] SocialdeepFieldAccount = {baseUrl+"deepfield-software-incorporated-beta","vijayata_201989@yahoo.com"};
	String[] SocialAutomationAccount = {baseUrl+"social-beings-beta","rohit.watson@gmail.com"};
	String[] SocialAutomationAccountCreator = {baseUrl+"social-beings-beta","vrohitmenon@gmail.com"};
	String userLocale = "IN";
	/*----------------LPAD Variables----------------*/
    String LPADUrlBeta="https://dac-map-beta.azurewebsites.net/";
    String ResellerAdmin="AutomationResellerAdmin";
    String ResellerPassword="111111";
    String LocationDataExcelPath="./data/LocationSampleData.xlsx";
    String AddressFormatExcelPath="./data/AddressFormats.xlsx";
    String LpadAccountName="SA Test DRS";
    String LpadContact="TestAcccountOwner4";
	
}