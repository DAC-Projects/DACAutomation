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
	
//	// Auth centre variables beta
	String url ="http://dac-map-beta.azurewebsites.net/#",

	 email="spillai@dacgroup.com",
	 password="DAC@12345";

	//account credentials
	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
//	String[] transparenSEE = {baseUrl+"international-account-beta-beta", "svarghese@dacgroup.com"};
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "svarghese@dacgroup.com"};
	String[] transparenSEE = {baseUrl+"international-account-beta-beta", "spillai@dacgroup.com","IN"};
	
	
	String userLocale="IN";
	
	/*----------------LPAD Variables----------------*/
	String LPADUrlBeta="https://dac-map-beta.azurewebsites.net/";
	String ResellerAdmin="CSEE_External_reseller";
	String ResellerPassword="111111";
	String Reseller="Domain N";
	String LocationDataExcelPath="./data/LocationSampleData.xlsx";
	String AddressFormatExcelPath="./data/AddressFormats.xlsx";
	String LpadAccountName="SA Test DRS";
	String LpadContact="TestAcccountOwner4";
			
}
