package com.dac.main.POM_CA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;

public class FormulaEvaluator {

	private Competitor comps;
	Map<String, Object[]> review = new HashMap<String, Object[]>();
	Map<String, Object[]> Map = new LinkedHashMap<String, Object[]>();
	List<Object[]> compltness = new ArrayList<Object[]>();
	List<Object[]> reviews = new ArrayList<Object[]>();
	Map<String, Object[]> contentAnalysis = new HashMap<String, Object[]>();
	private List<Object[]> accuracy = new ArrayList<Object[]>();
	private List<Object[]> visibility = new ArrayList<Object[]>();

	public FormulaEvaluator(Competitor comps) {
		this.comps = comps;
	}

	public FormulaEvaluator visibilityScore() {
		visibility.add(new Object[] { "-", "-","-","-","-","-","-","-","-","-","-","-" });
		visibility.add(new Object[] { "visibility  score calculation" });
		visibility.add(new Object[] { "SiteName", "visibilityScore" });
		int counter = 0;
		double Sum = 0.0;
		for (Sites site : comps.sites) {
			if (site.name != null) {
				visibility.add(new Object[] { site.name, site.visibilityScore });
				counter++;
				Sum = Sum + site.visibilityScore;
			}

		}
		visibility.add(new Object[] {"Sum",Sum });
		visibility.add(new Object[] { "FINAL visibility SCORE", (Sum / counter) });
		
		return this;

	}

	public FormulaEvaluator accuracyScore() {
		accuracy.add(new Object[] { "-", "-","-","-","-","-","-","-","-","-","-","-" });
		accuracy.add(new Object[] { "accuracy  score calculation" });
		accuracy.add(new Object[] { "SiteName", "accuracyScore", "SiteCount", "accuracyScore*SiteCount" });
		int counter = 2;
		double rCountSum = 0.0;
		double multipliedSum = 0.0;
		for (Sites site : comps.sites) {
			if (site.name != null) {
				accuracy.add(new Object[] { site.name, site.AccuracyScore, site.count,
						site.count * site.AccuracyScore });
				counter++;
				rCountSum = rCountSum + site.count;
				multipliedSum = multipliedSum + (site.count * site.AccuracyScore);
			}

		}
		accuracy.add(new Object[] { "-", rCountSum, "-", multipliedSum });
		accuracy.add(new Object[] { "-", "-", "FINAL REVIEW SCORE", (multipliedSum / rCountSum) });
		return this;
		
	}

	public FormulaEvaluator contentAnalysis() {
		compltness.add(new Object[] { "content analysis score calculation" });
		compltness.add(new Object[] { "SiteName", "CompletenessScore" });
		int counter = 0;
		double sum = 0.0;
		for (Sites site : comps.sites) {
			if (site.name != null) {
				compltness.add(new Object[] { site.name, site.completnessScore });
				counter++;
				sum = +site.completnessScore;
			}
		}
		compltness.add(new Object[] { "Sum", sum });
		compltness.add(new Object[] { "FINAL  SCORE", sum / (counter) });
		return this;
	}

	public FormulaEvaluator reviewScore() {
		reviews.add(new Object[] { "-", "-","-","-","-","-","-","-","-","-","-","-" });
		reviews.add(new Object[] { "review  score calculation" });
		reviews.add(new Object[] { "SiteName", "ReviewScore", "ReviewCount", "ReviewScore*RewviewCount" });
		int counter = 2;
		double rCountSum = 0.0;
		double multipliedSum = 0.0;
		for (Sites site : comps.sites) {
			if (site.name != null&&!site.name.equalsIgnoreCase("Foursquare")&&!site.name.equalsIgnoreCase("CitySearch")) {
				reviews.add(new Object[] { site.name, site.ReviewCount, site.ReviewScore,
						site.ReviewCount * site.ReviewScore });
				counter++;
				rCountSum = rCountSum + site.ReviewCount;
				multipliedSum = multipliedSum + (site.ReviewCount * site.ReviewScore);
			}

		}
		reviews.add(new Object[] { "-", rCountSum, "-", multipliedSum });
		reviews.add(new Object[] { "-", "-", "FINAL REVIEW SCORE", (multipliedSum / rCountSum) });
		return this;
	}

	public void summaryScore() {

	}

	public Map execute() {

		compltness.addAll(reviews);
		compltness.addAll(visibility);
		compltness.addAll(accuracy);
		

		for (int i = 0; i <= compltness.size() - 1; i++) {

			System.out.println(Arrays.toString(compltness.get(i)));
			Map.put(Integer.toString(i), compltness.get(i));

		}
		return Map;

	}
}
