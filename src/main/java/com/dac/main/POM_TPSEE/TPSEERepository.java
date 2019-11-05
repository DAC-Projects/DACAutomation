package com.dac.main.POM_TPSEE;
// To store constant values
public interface TPSEERepository {
	
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
	String VisibilityExportCSV = "VisibilityExportCSV.xlsx";
	String VisibilityExportXLSX = "VisibilityExportXLSX.xlsx";
	String VisibilityExportPdf = "VisibilityExport.pdf";
	String ReviewExport = "ReviewExport.xlsx";
	String CAExport = "CAExport.xlsx";
	String Exportpath ="./downloads/";
	String VisibilityExporttableFoundCSV = "VisibilityExporttableFoundCSV.xlsx";
	String VisibilityExporttableFoundXLSX = "VisibilityExporttableFoundXLSX.xlsx";
	String VisibilityExporttableNotFoundCSV = "VisibilityExporttableNotFound.xlsx";
	String VisibilityExporttableNotFoundXLSX = "VisibilityExporttableNotFound.xlsx";
	String AccuracyExporttable = "AccuracyExporttable.xlsx";
	String AccuracyExporttableInAccuracy = "AccuracyExporttableInAccuracy.xlsx";
	String AccuracyExporttableIgnored = "AccuracyExporttableIgnored.xlsx";
	String ContentAnalysisExport = "ContentAnalysisExport.xlsx";
	String ContentAnalysisSiteExport = "ContentAnalysisSiteExport.xlsx";
	String GoogleRankingExport = "GoogleRankingExport.xlsx";
	String LocationExport = "LocationExport.xlsx";
	String ReviewStreamExport = "ReviewStreamExport.xlsx";
	String ReviewScoreExport = "ReviewScoreExport.xlsx";
	String GMBExport = "GMBExport.xlsx";
	String BingExport = "BingExport.xlsx";
	String BingXLSX = "BingXLSX.xlsx";
	String GMBXLSX = "GMBXLSX.xlsx";
}