package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormats {
	
	/**
	 * This method is used to return the "short date" in string format according to the country code and language code.
	 * if we pass any wrong country code or/and language code then it will print the message to check the country code and language code 
	 * 
	 * 
     * @param langCode : Language code, date format to be in specific format for
     * 				   English : en , 	 german  : de , 	spanish : es
     * 				   french  : fr , 	 italian : it ,		swedish : sv
     * 
     * @param countryCode : Country code could be case sensitive, date format to be in specific format for
     * 				      unitedStates    : US ,   german          : DE , 	spanish(Spain) : ES
     * 				      spanish(Mexico) : MX ,   french(France)  : FR ,   french(Canada) : CA
     * 				      italian         : IT ,   swedish : SE
     * 
     * @return the date in specific date format based on passed country code and language code	*/
	public static String shortDate(String langCode, String contryCode) {
		String shortDateFromat = "";
		
		String langConCode = langCode+"_"+contryCode;
		
		switch(langConCode) {
		
		case "de_DE" : shortDateFromat = DateFormats.shortDate_de_DE();
					   break;
					   
		case "en_US" : shortDateFromat = DateFormats.shortDate_en_US();
		   			   break;
		   			   
		case "es_ES" : shortDateFromat = DateFormats.shortDate_es_ES();
		   			   break;
		   			   
		case "es_MX" : shortDateFromat = DateFormats.shortDate_es_MX();
		   			   break;
		   			   
		case "fr_CA" : shortDateFromat = DateFormats.shortDate_fr_CA();
		   			   break;
		   			   
		case "fr_FR" : shortDateFromat = DateFormats.shortDate_fr_FR();
		   			   break;
		   			   
		case "it_IT" : shortDateFromat = DateFormats.shortDate_it_IT();
		   			   break;
		   			   
		case "sv_SE" : shortDateFromat = DateFormats.shortDate_sv_SE();
					   break;
					 
		default		 : System.out.println("Selected wrong Language Code or Contry Code please check and Execute once again");
		}
		
		System.out.println(shortDateFromat);
		
		return shortDateFromat;
	}
	
	/**
	 * This method is used to return the "long date" in string format according to the country code and language code.
	 * if we pass any wrong country code or/and language code then it will print the message to check the country code and language code 
	 * 
	 * 
     * @param langCode : Language code, date format to be in specific format for
     * 				   English : en , 	 german  : de , 	spanish : es
     * 				   french  : fr , 	 italian : it ,		swedish : sv
     * 
     * @param countryCode : Country code could be case sensitive, date format to be in specific format for
     * 				      unitedStates    : US ,   german          : DE , 	spanish(Spain) : ES
     * 				      spanish(Mexico) : MX ,   french(France)  : FR ,   french(Canada) : CA
     * 				      italian         : IT ,   swedish : SE
     * 
     * @return the date in specific date format based on passed country code and language code	*/
	public static String longDate(String langCode, String contryCode) {
		String longDateFromat = "";
		
		String langConCode = langCode+"_"+contryCode;
		
		switch(langConCode) {
		
		case "de_DE" : longDateFromat = DateFormats.longDate_de_DE();
					   break;
					   
		case "en_US" : longDateFromat = DateFormats.longDate_en_US();
		   			   break;
		   			   
		case "es_ES" : longDateFromat = DateFormats.longDate_es_ES();
		   			   break;
		   			   
		case "es_MX" : longDateFromat = DateFormats.longDate_es_MX();
		   			   break;
		   			   
		case "fr_CA" : longDateFromat = DateFormats.longDate_fr_CA();
		   			   break;
		   			   
		case "fr_FR" : longDateFromat = DateFormats.longDate_fr_FR();
		   			   break;
		   			   
		case "it_IT" : longDateFromat = DateFormats.longDate_it_IT();
		   			   break;
		   			   
		case "sv_SE" : longDateFromat = DateFormats.longDate_sv_SE();
					   break;
					 
		default		 : System.out.println("Selected wrong Language Code or Contry Code please check and Execute once again");
		}
		
		System.out.println(longDateFromat);
		
		return longDateFromat;
	}

	private static String shortDate_en_US(){
		String engDateFormat = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		return engDateFormat;
	}
	
	private static String shortDate_de_DE(){
		String germanDateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("de","DE")).format(new Date());
		return germanDateFormat;
	}
	
	private static String shortDate_es_ES(){
		String spanish_SpainFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return spanish_SpainFormat;
	}
	
	private static String shortDate_es_MX(){
		return shortDate_es_ES();
	}
	
	private static String shortDate_fr_CA(){
		String french_CanadaFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return french_CanadaFormat;
	}
	
	private static String shortDate_fr_FR(){
		String french_FranceFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return french_FranceFormat;
	}
	
	private static String shortDate_it_IT(){
		String italianFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return italianFormat;
	}
	
	private static String shortDate_sv_SE(){
		String swedishDateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("sv","SE")).format(new Date());
		return swedishDateFormat;
	}	
	
	//----------------------------------- Long Date Formats ---------------------------------------------------
	
	private static String longDate_de_DE(){
		String germanDateFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("de","DE")).format(new Date());
		return germanDateFormat;
	}
	
	private static String longDate_en_US(){
		String englishFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("en","US")).format(new Date());
		return englishFormat;
	}
	
	private static String longDate_es_ES(){
		String engCF_LDatepattern = "EEEE, MMMM d yyyy";
		String spanish_SpainDate =new SimpleDateFormat(engCF_LDatepattern, new Locale("es", "ES")).format(new Date());
		return spanish_SpainDate;
	}
	
	private static String longDate_es_MX(){
		return longDate_es_ES();
	}
	
	private static String longDate_fr_CA(){
		String french_CanadaFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("fr","CA")).format(new Date());
		return french_CanadaFormat;
	}
	
	private static String longDate_fr_FR() {
		return longDate_fr_CA();
	}
	
	private static String longDate_it_IT(){
		String italianFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("it","IT")).format(new Date());
		return italianFormat;
	}
	
	private static String longDate_sv_SE(){
		String engCF_LDatepattern = "EEEE d MMMM yyyy";
		String swedishDate =new SimpleDateFormat(engCF_LDatepattern, new Locale("sv", "SE")).format(new Date());
		return swedishDate;
	}	
	
	/**
	 * This method is used to return the date format according to the country code and language code.
	 * if we pass any wrong country code or/and language code then by default it will return en_US date format
	 * 
	 * 
     * @param langCode : Language code, date format to be in specific format for
     * 				   English : en , 	 german  : de , 	spanish : es
     * 				   french  : fr , 	 italian : it ,		swedish : sv
     * 
     * @param countryCode : Country code could be case sensitive, date format to be in specific format for
     * 				      unitedStates    : US ,   german          : DE , 	spanish(Spain) : ES
     * 				      spanish(Mexico) : MX ,   french(France)  : FR ,   french(Canada) : CA
     * 				      italian         : IT ,   swedish : SE
     * 
     * @return the date in specific date format based on passed country code and language code	*/
	public static DateFormat dateFormat(String langCode, String contryCode) {
		
		DateFormat dateFormat;
		
		String langConCode = langCode+"_"+contryCode;
		
		switch(langConCode) {
		
		case "de_DE" : dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("de","DE"));
					   break;
					   
		case "es_MX" : 
		case "fr_FR" : 
		case "it_IT" : 
		case "es_ES" : dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		   			   break;
		   			   
		case "fr_CA" : dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   			   break;
		   			   
		case "sv_SE" : dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("sv","SE"));
					   break;
					 
		default		 : //System.out.println("Selected wrong Language Code or Contry Code please check and Execute once again");
		case "en_US" : dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		   			   break;  
		}
		
		return dateFormat;
	}
	
}
