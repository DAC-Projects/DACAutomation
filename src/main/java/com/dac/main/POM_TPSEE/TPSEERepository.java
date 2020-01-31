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
	String AccuracyExportCSV = "AccuracyExportcsv.xlsx";
	String AccuracyExportXLSX = "AccuracyExportxlsx.xlsx";
	String VisibilityExportCSV = "VisibilityExportCSV.xlsx";
	String VisibilityExportXLSX = "VisibilityExportXLSX.xlsx";
	String VisibilityExportPdf = "VisibilityExport.pdf";
	String VisibilityExportHistoryPdf = "VisibilityExportHistoryPdf.pdf";
	String ReviewExport = "ReviewExport.xlsx";
	String CAExport = "CAExport.xlsx";
	String Exportpath ="./downloads/";
	String VisibilityExporttableFoundCSV = "VisibilityExporttableFoundCSV.xlsx";
	String VisibilityExporttableFoundXLSX = "VisibilityExporttableFoundXLSX.xlsx";
	String VisibilityExporttableNotFoundCSV = "VisibilityExporttableNotFoundcsv.xlsx";
	String VisibilityExporttableNotFoundXLSX = "VisibilityExporttableNotFound.xlsx";
	String AccuracyExporttableXLSX = "AccuracyExporttablexlsx.xlsx";
	String AccuracyExporttableCSV = "AccuracyExporttablecsv.xlsx";
	String AccuracyExporttableInAccuracyXLSX = "AccuracyExporttableInAccuracyxlsx.xlsx";
	String AccuracyExporttableInAccuracyCSV = "AccuracyExporttableInAccuracycsv.xlsx";
	String AccuracyExporttableIgnored = "AccuracyExporttableIgnored.xlsx";
	String ContentAnalysisExport = "ContentAnalysisExport.xlsx";
	String ContentAnalysisSiteExport = "ContentAnalysisSiteExport.xlsx";
	String GoogleRankingExportCSV = "GoogleRankingExportCSV.csv";
	String GoogleRankingExportXLSX = "GoogleRankingExportXLSX.xlsx";
	String LocationExportCSV = "LocationExportcsv.csv";
	String LocationExportXLSX = "LocationExportXLSX.xlsx";
	String ReviewStreamExport = "ReviewStreamExport.xlsx";
	String ReviewScoreExportCSV = "ReviewScoreExportCSV.csv";
	String ReviewScoreExportXLSX = "ReviewScoreExportXLSX.xlsx";
	String GMBExport = "GMBExport.xlsx";
	String BingExport = "BingExport.xlsx";
	String BingXLSX = "BingXLSX.xlsx";
	String GMBXLSX = "GMBXLSX.xlsx";
}