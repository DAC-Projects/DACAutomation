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

	 email="adevaraj@dacgroup.com",
	 password="DacQa123";

	//account credentials
	String baseUrl="http://staging.manage.dacgroup.com:82/accounts/";
	//String baseUrl = "https://manage.dacgroup.com/accounts/";
	String[] competitiveAnalysis = {baseUrl+"competitive-analysis-test-account-beta-1", "mobittah98@yahoo.ca"};
	String[] deepfieldAccoount_SE_app = {baseUrl+"social-automation-beta", "rohitmenon8055@gmail.com"};
	String[] deepfieldAccount_SE = {baseUrl+"social-automation-beta","vrohitmenon@gmail.com	"};
	//String[] transparenSEE = {baseUrl+"international-location-test-account","chappel.mann+stc@gmail.com"};
	String[] transparenSEE = {baseUrl+"aurify-brands-test-beta","jmohan@dacgroup.com"};

	//String [] transparenSEE = {baseUrl +"aurify-brands","aarti.mehta@meltshop.com"};
	//String [] transparenSEE = {baseUrl +"aurify-brands","p.pundyk@fieldsgoodchicken.com"}; 
	String[] Fit4LessAccount = {baseUrl+"fit4less-beta", "1test@gmail.com"};
	String[] neuralTuringTechAccount = {baseUrl+"neural-turing-tech-beta", "svarghese@dacgroup.com"};
	String LPADUrlBeta="https://dac-map-beta.azurewebsites.net/";
	String ResellerAdmin="smitha.internal.domain@dacgroup.com";
	String ResellerPassword="111111";
	String Reseller="Domain N";
	String LocationDataExcelPath="./data/LocationSampleData.xlsx";
	String AddressFormatExcelPath="./data/AddressFormats.xlsx";
	String LpadAccountName="Automation Test Account - TSEE";
	String LpadContact="TestAcccountOwner4";
	
}
