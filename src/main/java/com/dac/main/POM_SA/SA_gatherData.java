package com.dac.main.POM_SA;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import resources.CurrentState;
import resources.ExcelHandler;

public class SA_gatherData extends RRCSites{
	
	/**
	 * This Method is used to calculate the RRC score and grade based on the passed parameter data from the data file
	 * 
	 * @param file : pass the Data file's path in the project.
	 * @param sheetName : pass the Sheet Name, which contains the RAW data in the passed data file.
	 * @param timePeriod : Need to pass the Integer value for which month value need to calculate the scores from data file.
	 * @param sites : pass the List<String> data of the sites for which you want to calculate the scores for.
	 * @param clientRefCriteria : pass the List<Long> clientRefID's for which location's you  want to calculate the scores.
	 * @throws Exception */
	public String[] calculateRRC_Scores(String file, String sheetName, Integer timePeriod, List<String> sites, List<Long> clientRefCriteria) throws Exception {
		SA_gatherData s = new SA_gatherData();
		if(sites == null | sites.contains("All")) sites = Arrays.asList("Bing", "Merchantcircle", "google", "yelp", "yp.com", "facebook",
																		"CitySearch","yellowpages", "foursquare", "Superpages", "YellowpagesCom", 
																		"tripadvisor");
		if(sites != null) sites.stream().forEachOrdered(item -> item.toLowerCase());
		List<String> vendorCriteria1 = new ArrayList<>(), vendorCriteria2 = new ArrayList<>();;
        //skip element with value "foursquare"
		sites.stream().filter(name -> !name.equals("foursquare")).forEachOrdered(vendorCriteria1::add);
		//add element with value "foursquare"
		sites.stream().filter(name -> name.equals("foursquare")).forEachOrdered(vendorCriteria2::add);
		
		List<Integer> revMonthRowNum = s.getReviewMonthCriteriaRowNums(file, sheetName, timePeriod);
		List<Integer> clientIDCriteria = s.getClientRefCriteriaRowNums(file, sheetName, clientRefCriteria), dClientRefRownum = clientIDCriteria;
		List<Integer> c1RowNum = s.getVendorCriteriaRowNums(file, sheetName, vendorCriteria1), dc1RowNum = c1RowNum;
		List<Integer> c2RowNum = s.getVendorCriteriaRowNums(file, sheetName, vendorCriteria2), dc2RowNum = c2RowNum;
		dc1RowNum.retainAll(revMonthRowNum); // Uncommon values will delete from "dc1RowNum" when compare with "revMonthRowNum" values
		dc2RowNum.retainAll(revMonthRowNum); // Uncommon values will delete from "dc2RowNum" when compare with "revMonthRowNum" values
		dc1RowNum.retainAll(dClientRefRownum);
		dc2RowNum.retainAll(dClientRefRownum);
		
		double[] a = evaluateRRC_AvgScore(file, sheetName, dc1RowNum), b = null;
		//System.out.println("Arrays.toString(a) : "+Arrays.toString(a));
		//System.out.println("------------------------");
		if(dc2RowNum.size() != 0) b = evaluateRRC_AvgScore(file, sheetName, dc2RowNum);	
		//System.out.println("---------------------");
		List score_1 = s.calRRCScore(""+vendorCriteria1.toString()+"" , a), score_2 = null;
		if(b != null) score_2 = s.calRRCScore(""+vendorCriteria2.toString()+"",b);			
		Object[] fScore = s.calRRC_fScore(score_1, score_2);
		//System.out.println(score_1.toString());
		if(score_2 != null) System.out.println(score_2.toString());
		
		CurrentState.getLogger().info(""+score_1.toString()+"\n"+score_2.toString()+"");
		
		//System.out.println("--------------------------------");
		CurrentState.getLogger().info("Final Score : "+ Arrays.deepToString(fScore));
		String finalScores[] = Arrays.deepToString(fScore).replace("[", "").replace("]", "").split(",");
		finalScores[0] = finalScores[0].trim(); finalScores[1] = finalScores[1].trim();
		//System.out.println(""+finalScores[0] + ""+finalScores[1]);
		return finalScores;
	}
	
	/**
	 * To get the "Review Report score Grade" based on the "Review Report card Score"	*/
	private String calRSC(double reportCardScore) {
		String reportScoreGrade = "";
		DecimalFormat newFormat = new DecimalFormat("#.##");
		reportCardScore = Double.valueOf(newFormat.format(reportCardScore));
		if(reportCardScore >=0 & reportCardScore <64) reportScoreGrade = "F";
		else if(reportCardScore >=64 & reportCardScore <65) reportScoreGrade = "D-";
		else if(reportCardScore >=65 & reportCardScore <69) reportScoreGrade = "D";
		else if(reportCardScore >=69 & reportCardScore <70) reportScoreGrade = "D+";
		else if(reportCardScore >=70 & reportCardScore <72) reportScoreGrade = "C-";
		else if(reportCardScore >=72 & reportCardScore <75) reportScoreGrade = "C";
		else if(reportCardScore >=75 & reportCardScore <79) reportScoreGrade = "C+";
		else if(reportCardScore >=79 & reportCardScore <82) reportScoreGrade = "B-";
		else if(reportCardScore >=82 & reportCardScore <85) reportScoreGrade = "B";
		else if(reportCardScore >=85 & reportCardScore <90) reportScoreGrade = "B+";
		else if(reportCardScore >=90 & reportCardScore <94) reportScoreGrade = "A-";
		else if(reportCardScore >=94 & reportCardScore <95) reportScoreGrade = "A";
		else if(reportCardScore >=95 & reportCardScore <=200) reportScoreGrade = "A+";
		return reportScoreGrade;
	}
	
	/**
	 * Get the "Star Value" based on the "Average Star Rating" of a Criteria */
	private double getStarValue(double avgStarRating) {
		double starValue = 0.0;
		DecimalFormat newFormat = new DecimalFormat("#.##");
		if(avgStarRating == 0) starValue = 0;
		else if(avgStarRating <= 3 & avgStarRating > 0) starValue = (avgStarRating - 1)*12.5;
		else if(avgStarRating <= 4 & avgStarRating > 3) starValue = ((avgStarRating - 3)*17.5) + 25;
		else if(avgStarRating <= 5 & avgStarRating > 4) starValue = ((avgStarRating - 4)*7.5) + 42.5;
		starValue =  Double.valueOf(newFormat.format(starValue));
		return starValue;
	}
	
	/**
	 * This method is used calculate final "Review Report Card Score" and "Review Report Card Grade" 
	 * 	
	 * @param s1 : Final Scores of the Criteria_1 got from "calculateRRCScore" method
	 * @param s2 : Final Scores of the Criteria_2 got from "calculateRRCScore" method 
	 * 
	 * @return the final "Review Report Card Score" and "Review Report Card Grade" */
	public Object[] calRRC_fScore(List s1, List s2) {
		DecimalFormat newFormat = new DecimalFormat("#.##"), newFormat1 = new DecimalFormat("#.#####");
		double avgRev_1 = Double.parseDouble((s1.get(2)).toString()), RRCS_1 = Double.parseDouble((s1.get(13)).toString());
		int fRCS = (int) Math.round(RRCS_1);
		double aVal_1 = Math.round(avgRev_1*RRCS_1);
		String RRCG_1 = (s1.get(14)).toString(), fRCG = RRCG_1;
		
		if(s2 != null) {			
			double avgRev_2 = Double.parseDouble((s2.get(2)).toString()), RRCS_2 = Double.parseDouble((s2.get(13)).toString());
			double aVal_2 = Math.round(avgRev_2*RRCS_2);
			String RRCG_2 = (s2.get(14)).toString();
			double bVal_1 = Double.valueOf(newFormat1.format( aVal_1/(avgRev_1+avgRev_2))),
					bVal_2 = Double.valueOf(newFormat.format( aVal_2/(avgRev_1+avgRev_2)));
			fRCS = (int) Math.round(Double.valueOf(newFormat.format(bVal_1 + bVal_2)));
			fRCG = new SA_gatherData().calRSC(fRCS);
		}
		Object[] o = {fRCS, fRCG};
		return o;
	}
	
	/**
	 * This method is used to calculate the "Review Report Card Score" and "Review Report Card Grade" for each criteria
	 * 
	 * @param vendorCriteria : A "Vendor criteria" to which to calculate the score. For Reference only 
	 * @param avgScores : Average scores of the each criteria's "+ve", "-ve", "star rating's" etc. averages		
	 * @return return the calculated "Available Sentiment", "Review Report Card Score" and "Review Report Card Grade" etc.  as a list	*/
	public List calRRCScore(String vendorCriteria, double[] avgScores) throws Exception {
		SA_gatherData s = new SA_gatherData();
		DecimalFormat newFormat = new DecimalFormat("#.##"), newFormat1 = new DecimalFormat("#.#####");
		double availSenti = 0, totSentival = 0, avgSentiVal = 0, sentiVal  = 0, reportCardScore = 0;
		double[] a = avgScores;
		availSenti = a[0] - a[4]; totSentival = (a[1]*0)+(a[2]*5)+(a[3]*10);
		
		if(availSenti != 0) {
			avgSentiVal = Double.valueOf(newFormat1.format(totSentival/availSenti));
			sentiVal = (avgSentiVal*3.5) + 15;
		}
		System.out.println("newFormat.format(a[12]) : "+newFormat.format(a[12]));
		double avgStarRating = Double.valueOf(newFormat.format(a[12])), starValue =  getStarValue(avgStarRating);
		if(avgStarRating != 0) {
			reportCardScore = Double.valueOf(newFormat.format(starValue + sentiVal));			
		}else reportCardScore = Double.valueOf(newFormat.format(sentiVal*2));
		
		String reportScoreGrade = s.calRSC(reportCardScore); 
		
		CurrentState.getLogger().info("avgStarRating : "+avgStarRating+" availSenti : "+availSenti+"\n"+
								      "totSentival :"+totSentival+" avgSentival : "+avgSentiVal+"\n"+
								      "starValue : "+starValue+" sentiVal : "+sentiVal+"\n"+
								      "reportCardScore : "+reportCardScore+" reportScoreGrade : "+reportScoreGrade);
		
		System.out.println("avgStarRating : "+avgStarRating+" availSenti : "+availSenti);
		System.out.println("totSentival :"+totSentival+" avgSentival : "+avgSentiVal);
		System.out.println("starValue : "+starValue+" sentiVal : "+sentiVal);
		System.out.println("reportCardScore : "+reportCardScore+" reportScoreGrade : "+reportScoreGrade);
		System.out.println("------------------------------------");
		
		List scoreList = new ArrayList();
		Object[] scores_1 = new Object[] {"Overall Score", vendorCriteria, a[0], a[1], a[2], a[3], a[4], availSenti, totSentival, avgSentiVal,
				sentiVal, avgStarRating, starValue, reportCardScore, reportScoreGrade};
		for( Object c : scores_1) scoreList.add(c);	
		
		return scoreList;
	}
	
	/**
	 * To get the matching rows of the extracted data file based on the "Vendor Criteria"	
	 * 
	 * @param file : file name of the "Data file" with complete path of the file in project/local system
	 * @param sheetName : sheet Name in the extracted data file in which sheet data will be.
	 * @param vendorCriteria : Criteria of a vendor to which we want to filter, pass as a string array.	 
	 * 							ex: String[] vendorCriteria = {"google", "facebook"} etc....		*/
	public List<Integer> getVendorCriteriaRowNums(String file, String sheetName, List<String> vendorCriteria) throws Exception {
		ExcelHandler workbook = new ExcelHandler(file, sheetName);
		//Integer a = workbook.seacrh_pattern("VendorId", 0).get(0);
		List<Integer> vcRowNum = workbook.find_Row_no(vendorCriteria, 1, workbook.seacrh_pattern("VendorId", 0).get(0));
		return vcRowNum;
	}
	
	/**
	 * To get the matching rows of the extracted data file based on the "Location name Criteria"	
	 * 
	 * @param file : file name of the "Data file" with complete path of the file in project/local system
	 * @param sheetName : sheet Name in the extracted data file in which sheet data will be.
	 * @param locNameCriteria : Criteria of a Location name to which we want to filter, pass as a string array.	 
	 * 							ex: String[] locationCriteria = {"us,britishcolumbia,,,"} etc....		*/
	public List<Integer> getLocNameCriteriaRowNums(String file, String sheetName, List<String> locNameCriteria) throws Exception {
		ExcelHandler workbook = new ExcelHandler(file, 0);
		//Integer a = workbook.seacrh_pattern("LocationName", 0).get(0);
		List<Integer> lnRowNum = workbook.find_Row_no(locNameCriteria, 1, workbook.seacrh_pattern("LocationName", 0).get(0));
		return lnRowNum;
	}
	
	/**
	 * To get the matching rows of the extracted data file based on the "Client Reference ID Criteria"	
	 * 
	 * @param file : file name of the "Data file" with complete path of the file in project/local system
	 * @param sheetName : sheet Name in the extracted data file in which sheet data will be.
	 * @param clientRefCriteria : Criteria of a client reference ID to which we want to filter, pass as a Long array.	 
	 * 							ex: Long[] clientRefCriteria = {"9876543210L", "8490543210L"} etc....		*/
	public List<Integer> getClientRefCriteriaRowNums(String file, String sheetName, List<Long> clientRefCriteria) throws Exception {
		ExcelHandler workbook = new ExcelHandler(file, 0);
		//Integer a = workbook.seacrh_pattern("ClientRef", 0).get(0);
		List<Integer> crRowNum = workbook.find_Row_no(clientRefCriteria, 1, workbook.seacrh_pattern("ClientRef", 0).get(0));
		return crRowNum;
	}
	
	/**
	 * To get the matching rows of the extracted data file based on the "Month Review Criteria"	
	 * 
	 * @param file : file name of the "Data file" with complete path of the file in project/local system
	 * @param sheetName : sheet Name in the extracted data file in which sheet data will be.
	 * @param reviewMonths : Criteria of a review month to which we want to filter, pass as a Integer array.	 
	 * 							ex: Integer[] reviewMonths = {0, 201903} etc....		*/
	public List<Integer> getReviewMonthCriteriaRowNums(String file, String sheetName, Integer reviewMonth) throws Exception {
		ExcelHandler workbook = new ExcelHandler(file, 0);
		Integer a = workbook.seacrh_pattern("ReviewsMonth", 0).get(0);
		List<Integer> reviewMonths = Arrays.asList(reviewMonth);
		List<Integer> rmRowNum = workbook.find_Row_no(reviewMonths, 1, a);
		return rmRowNum;
	}
	
	/**
	 * To calculate the averages of the reviews on the criteria matching rows in the data excel sheet.
	 * 
	 * @param file : file name of the "Data file" with complete path of the file in project/local system
	 * @param sheetName : sheet Name in the extracted data file in which sheet data will be.
	 * @param c_MatchRows : pass the row numbers of the excel sheet data to which calculate the scores 	
	 * @throws Exception */
	public static double[] evaluateRRC_AvgScore(String file, String sheetName, List<Integer> c_MatchRows) throws Exception {
		
		ExcelHandler workbook = new ExcelHandler(file, sheetName);
		RRCSites r = new RRCSites();
		r.setRRCScoreVars(workbook);
				
		for(int i=0; i<c_MatchRows.size(); i++) {
			r.totalReviewSum = r.totalReviewSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.TotalReviewsCol));
			r.NegativeCountSum = r.NegativeCountSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.NegativeCountCol));
			r.NeutralCountSum = r.NeutralCountSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.NeutralCountCol));
			r.PositiveCountSum = r.PositiveCountSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.PositiveCountCol));
			r.NACountSum = r.NACountSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.NACountCol));
			r.s0CountSum = r.s0CountSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.s0CountCol));
			r.s1CountSum = r.s1CountSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.s1CountCol));
			r.s2CountSum = r.s2CountSum + (Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.s2CountCol)))*2;
			r.s3CountSum = r.s3CountSum + (Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.s3CountCol)))*3;
			r.s4CountSum = r.s4CountSum + (Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.s4CountCol)))*4;
			r.s5CountSum = r.s5CountSum + (Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.s5CountCol)))*5;
			r.AvgStarRatingSum = r.AvgStarRatingSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.AvgStarRatingCol));
			r.ReviewsMonthSum = r.ReviewsMonthSum + Double.parseDouble(workbook.getCellValue(c_MatchRows.get(i), r.ReviewsMonthCol));	
		}
		
		r.ReviewsMonthSum = (r.s0CountSum+r.s1CountSum+r.s2CountSum+r.s3CountSum+r.s4CountSum+r.s5CountSum) / r.AvgStarRatingSum;
		if((new Double(r.ReviewsMonthSum)).toString() == "Infinity" || Double.isNaN(r.ReviewsMonthSum)) r.ReviewsMonthSum = 0;
		/*System.out.println("totalReviewSum : "+r.totalReviewSum+ " NegativeCountSum : "+r.NegativeCountSum+"\n"+"NeutralCountSum : "+r.NeutralCountSum+" PositiveCountSum : "+r.PositiveCountSum);
		System.out.println("NACountSum : "+r.NACountSum+" Star0CountSum : "+r.s0CountSum+"\n"+"Star1CountSum : "+r.s1CountSum+" Star2CountSum : "+r.s2CountSum+"\n"+"Star3CountSum : "+r.s3CountSum);
		System.out.println("Star4CountSum : "+r.s4CountSum+" Star5CountSum : "+r.s5CountSum+"\n"+"AvgStarRatingSum : "+r.AvgStarRatingSum+" ReviewsMonthSum : "+r.ReviewsMonthSum);
		*/
		double[] avgRRCcriteriaScore = new double[] {r.totalReviewSum, r.NegativeCountSum, r.NeutralCountSum, r.PositiveCountSum,
									 r.NACountSum, r.s0CountSum, r.s1CountSum, r.s2CountSum, r.s3CountSum, r.s4CountSum, r.s5CountSum,
									 r.AvgStarRatingSum, r.ReviewsMonthSum }; 
		return avgRRCcriteriaScore;
	}
}
