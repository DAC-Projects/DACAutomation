package com.dac.main.POM_CA;

import java.beans.Visibility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.CellReference;

import resources.ExcelTestDataHandler;
import resources.ExcelWrite;

public class CA_gatherData implements CARepository {

	private ExcelTestDataHandler company_level;
	private String level;
	
	

	public CA_gatherData(String excel_file, String level) throws IOException {
		super();
		this.company_level = new ExcelTestDataHandler(excel_file,
				0);
		this.level = level;
	}

	@SuppressWarnings("unchecked")
	public void evaluteScores() throws Exception {
		//ExcelTestDataHandler company_level = new ExcelTestDataHandler("./CA_aggregation_files/company_documents.xlsx","company_documents");
		int row_count = company_level.getrowno();
		List<Integer> competitor_col_no = company_level.find_column_no(competitor_column, 0);
		List<Integer> usrID_col_no = company_level.find_column_no(User, 0);
		
		int competitorCount = company_level.find_Row_no(UserID, usrID_col_no.get(0)).size();
		Competitor[] competitors = new Competitor[competitorCount];
		List<Integer> Sites_col = company_level.seacrh_pattern(Site_Name_pattern, 0);
		int sitesCount = Sites_col.size() - 1;
		Sites[] site = new Sites[sitesCount + 1];

		for (int col : Sites_col)
			System.out.println(CellReference.convertNumToColString(col));
		

		
			
		
	
				
			

		

		for (int row = 1, a = 0; row <= row_count; row++, a++) {
			
			if (company_level.getValue(usrID_col_no.get(0), row).equals(UserID)){
			try {
				competitors[a] = new Competitor(company_level.getValue(competitor_col_no.get(0), row));
				System.out.println("\n" + competitors[a].competitorName);
				System.out.format("%20s%15s%15s%15s%15s", "site", "count", "ReviewScore", "ReviewCount",
						"CompletenessScore" + "\n");
				System.out.format("%20s%15s%15s%15s", "---", "---", "---", "---", "---");
				for (int x = 0; x <= sitesCount; x++) {
					System.out.println("");
					site[x] = getSiteData(x, row, company_level, competitors[a]);
					System.out.format("%20s%15f%15f%15f%15f", site[x].name, site[x].count, site[x].ReviewScore,
							site[x].ReviewCount, site[x].completnessScore);
					competitors[a].sites.add(site[x]);
				}
				Arrays.fill(site, null);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		}

		new ExcelWrite().createExcel("./Results_"+level+"_"+UserID+".xlsx");
		Thread.sleep(1000);
		int i=0;
		
		for (Competitor comps : competitors)
			System.out.println(comps.competitorName);
		
		
		for (Competitor comps : competitors ) {
			System.out.println(i);
			new ExcelWrite().wirte(createMap(comps), comps.competitorName, "./Results_"+level+"_"+UserID+".xlsx");
			new ExcelWrite().wirte(new FormulaEvaluator(comps).reviewScore().contentAnalysis().accuracyScore().execute(),
					"Results-"+comps.competitorName.substring(0,4)+"..."+i, "./Results_"+level+"_"+UserID+".xlsx");
		i= i+1;
		}
		
	
	}

	public  Sites getSiteData(int x, int row, ExcelTestDataHandler company_level, Competitor competitors)
			throws Exception {

		Sites site = new Sites();
		String name = getvalue(x, Site_Name, row, company_level);
		site.name = ((name != null) ? name : null);

		if (site.name != null) {
			String count = getvalue(x, site_count, row, company_level);
			String rCount = getvalue(x, Review_count, row, company_level);
			String rScore = getvalue(x, Review_score, row, company_level);
			String complScore = getvalue(x, completeness_Score, row, company_level);

//			site.count = ((count != null) ? Float.parseFloat(count) : 0.0);
//			site.ReviewCount = ((rCount != null) ? Float.parseFloat(rCount) : 0.0);
//			site.ReviewScore = ((rScore != null) ? Float.parseFloat(rScore) : 0.0);
//			site.completnessScore = ((complScore != null) ? Float.parseFloat(complScore) : 0.0);
//
//			String visibilityScore = getSiteDataFromExport(site.name, Exportpath + VisibilityExport, competitors);
//			site.visibilityScore = ((visibilityScore != null) ? Float.parseFloat(visibilityScore) : 0.0);
//			
//			String AccuracyScore = getSiteDataFromExport(site.name, Exportpath + AccuracyExport, competitors);
//			site.AccuracyScore = ((AccuracyScore != null) ? Float.parseFloat(AccuracyScore) : 0.0);
			
			site.count = ((count != null) ? convertPercentFloat(count) : 0.0);
			site.ReviewCount = ((rCount != null) ? convertPercentFloat(rCount) : 0.0);
			site.ReviewScore = ((rScore != null) ? convertPercentFloat(rScore) : 0.0);
			site.completnessScore = ((complScore != null) ? convertPercentFloat(complScore) : 0.0);

			String visibilityScore = getSiteDataFromExport(site.name, Exportpath + VisibilityExport, competitors);
			site.visibilityScore = ((visibilityScore != null) ?convertPercentFloat(visibilityScore) : 0.0);
			
			String AccuracyScore = getSiteDataFromExport(site.name, Exportpath + AccuracyExport, competitors);
			site.AccuracyScore = ((AccuracyScore != null) ? convertPercentFloat(AccuracyScore) : 0.0);
		}

		return site;
	}
	
	public double convertPercentFloat(String score) {
		if(score.endsWith("%")) {
			return Float.parseFloat(score.replace("%", ""));
		}
		else if(score.equals("-")){
			return 0.0;
		}
		else if(score.equals("")) {
			return 0.0;
		}
		else
			return Float.parseFloat(score);
	}
	
	private static String getSiteDataFromExport(String name, String path, Competitor competitors) throws Exception {
		ExcelTestDataHandler read = new ExcelTestDataHandler(path, "Sheet0");
		int column = read.find_column_no(competitors.competitorName, 0).get(0);
		System.out.println(name);
		int row = read.find_Row_no(name, 0).get(0);
		System.out.println("row and column no" + row + "***" + column);
		return read.getValue(column, row);
	}

	public static String getvalue(int x, String value, int row, ExcelTestDataHandler company_level) throws Exception {
		String col = StringUtils.replace(value, "*", Integer.toString(x));
		String name = company_level.getValue(company_level.find_column_no(col, 0).get(0), row);
		return name;

	}

	public static Map<String, Object[]> createMap(Competitor comps) {
		int counter = 2;
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("1", new Object[] { "SiteName", "SiteCount", "ReviewScore", "ReviewCount", "CompletenessScore",
				"Visibility Score" });
		for (Sites site : comps.sites) {
			if (site.name != null) {
				data.put(Integer.toString(counter), new Object[] { site.name, site.count, site.ReviewScore,
						site.ReviewCount, site.completnessScore, site.visibilityScore });
				counter = counter + 1;
			}
		}
		return data;
	}
}
