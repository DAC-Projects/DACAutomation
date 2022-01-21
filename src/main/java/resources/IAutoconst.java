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
	String baseUrl="https://manage.dacgroup.com/accounts/";//Staging URL
//	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
	
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
//	String[] transparenSEE = {baseUrl+"international-account-beta-beta", "svarghese@dacgroup.com"};
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "svarghese@dacgroup.com"};
//	String[] transparenSEE = {baseUrl+"international-account-beta-beta", "spillai@dacgroup.com","IN"};
	
	//beta
//	String environment="BETA";//Enable this while executing BETA
//	String[] transparenSEE = {baseUrl+"aurify-brands-test-beta", "jmohan@dacgroup.com"}; 
//	String[] transparenSEE = {baseUrl+"fit4less-beta", "skanna@dacgroup.com"}; 
//	String[] transparenSEE = {baseUrl+"social-beings-beta", "rohit.watson@gmail.com"}; //SC Approver
//	String[] transparenSEE = {baseUrl+"social-beings-beta", "vrohitmenon@gmail.com"}; //SC Creator
	
//	String[] transparenSEE = {baseUrl+"automation-test-account-tsee-beta","amahadev@dacgroup.com"};//TSEE Account Owner
//	String[] transparenSEE = {baseUrl+"automation-test-account-tsee-beta","jmann@dacgroup.com"}; //TSEE Location Manager
	
	//Staging
	String environment="STAGING";//Enable this while executing STAGING
	String[] transparenSEE = {baseUrl+"international-location-test-account","chappel.mann+stc@gmail.com"};//Account Owner
//	String [] transparenSEE = {baseUrl +"international-location-test-account","chappel.mann+stl2@gmail.com"}; //- Location Manager
	
	String userLocale="IN";
	String TSEE_url="https://transparensee-dashboard-beta.azurewebsites.net/";
	/*----------------LPAD Variables----------------*/
	String LPADUrlBeta="https://dac-map-beta.azurewebsites.net/";
	String ResellerAdmin="cseetestreseller@gmail.com";
	String ResellerPassword="111111";
	String Reseller="DAC Group 2";
	String LocationDataExcelPath="./data/LocationSampleData.xlsx";
	String AddressFormatExcelPath="./data/AddressFormats.xlsx";
	String LpadAccountName="SA Test DRS";
	String LpadContact="TestAcccountOwner4";
			//smitha.internal.domain@dacgroup.com - Smitha's Internal Domain
			//	cseetestreseller@gmail.com - DAC Group,CSEE Test Internal Domain
	//csee_external_reseller - Domain N
}
