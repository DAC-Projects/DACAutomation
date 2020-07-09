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

	 email="amahadev@dacgroup.com",
	 password="DacQa123";

	//account credentials
	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
	//String baseUrl = "https://manage.dacgroup.com/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccount = {baseUrl+"deepfield-software-incorporated-beta", "bmobit@dacgroup.com"};
	String[] NandithaAccount = {baseUrl+"nanditha-test-business-account-cf","wasimakramb325@gmail.com"};
	//String[] transparenSEE = {baseUrl+"aurify-brands-test-beta", "jmohan@dacgroup.com"}; // for GMB
	//String[] transparenSEE = {baseUrl+"international-account-beta-beta", "amahadev@dacgroup.com"};
	String[] transparenSEE = {baseUrl+"international-location-test-account","chappel.mann+test1@gmail.com"};
	//String [] transparenSEE = {baseUrl +"aurify-brands","abritton@dacgroup.com"};  
	//String [] transparenSEE = {baseUrl +"aurify-brands","aarti.mehta@meltshop.com"};
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "john.cena@dacgroup.com"};
	String[] SocialdeepFieldAccount = {baseUrl+"deepfield-software-incorporated-beta","vijayata_201989@yahoo.com"};
	String[] SocialAutomationAccount = {baseUrl+"social-automation-beta","rohitmenon8055@gmail.com"};
	String[] SocialAutomationAccountCreator = {baseUrl+"social-automation-beta","vrohitmenon@gmail.com"};
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