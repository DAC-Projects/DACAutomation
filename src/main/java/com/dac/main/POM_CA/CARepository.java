package com.dac.main.POM_CA;

public interface CARepository {
	
	String User = "UserId";
	String UserID = "58f658ece0264678337dc4c1";

	String competitor_column = "Competitors.CompetitorName";
	String site_count = "Competitors.Sites.*.Count";
	String Review_score = "Competitors.Sites.*.ReviewScore";
	String Review_count = "Competitors.Sites.*.ReviewCount";
	String completeness_Score = "Competitors.Sites.*.CompletenessScore";
	String Site_Name = "Competitors.Sites.*.Name";
	String Site_Name_pattern = "Sites(.*)\\.Name$";
	String AccuracyExport = "AccuracyExport.xlsx";
	String VisibilityExport = "VisibilityExport.xlsx";
	String ReviewExport = "ReviewExport.xlsx";
	String Exportpath ="./downloads/";
	
	

	
	
}
