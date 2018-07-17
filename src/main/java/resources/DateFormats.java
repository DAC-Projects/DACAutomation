package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormats {

	public static String shortDate_en_US(){
		String engDateFormat = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		return engDateFormat;
	}
	
	public static String shortDate_de_DE(){
		String germanDateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("de","DE")).format(new Date());
		return germanDateFormat;
	}
	
	public static String shortDate_es_ES(){
		String spanish_SpainFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return spanish_SpainFormat;
	}
	
	public static String shortDate_es_MX(){
		return shortDate_es_ES();
	}
	
	public static String shortDate_fr_CA(){
		String french_CanadaFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return french_CanadaFormat;
	}
	
	public static String shortDate_fr_FR(){
		String french_FranceFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return french_FranceFormat;
	}
	
	public static String shortDate_it_IT(){
		String italianFormat = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		return italianFormat;
	}
	
	public static String shortDate_sv_SE(){
		String swedishDateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("sv","SE")).format(new Date());
		return swedishDateFormat;
	}	
	
	//----------------------------------- Long Date Formats ---------------------------------------------------
	
	public static String longDate_de_DE(){
		String germanDateFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("de","DE")).format(new Date());
		return germanDateFormat;
	}
	
	public static String longDate_en_US(){
		String englishFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("en","US")).format(new Date());
		return englishFormat;
	}
	
	public static String longDate_es_ES(){
		String engCF_LDatepattern = "EEEE, MMMM d yyyy";
		String spanish_SpainDate =new SimpleDateFormat(engCF_LDatepattern, new Locale("es", "ES")).format(new Date());
		return spanish_SpainDate;
	}
	
	public static String longDate_es_MX(){
		return longDate_es_ES();
	}
	
	public static String longDate_fr_CA(){
		String french_CanadaFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("fr","CA")).format(new Date());
		return french_CanadaFormat;
	}
	
	public static String longDate_fr_FR() {
		return longDate_fr_CA();
	}
	
	public static String longDate_it_IT(){
		String italianFormat = DateFormat.getDateInstance(DateFormat.FULL, new Locale("it","IT")).format(new Date());
		return italianFormat;
	}
	
	public static String longDate_sv_SE(){
		String engCF_LDatepattern = "EEEE d MMMM yyyy";
		String swedishDate =new SimpleDateFormat(engCF_LDatepattern, new Locale("sv", "SE")).format(new Date());
		return swedishDate;
	}	
	
	/*public static void main(String[] args) {
		System.out.println(longDate_en_US());
		
	}*/
}
