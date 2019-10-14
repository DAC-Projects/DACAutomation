package com.dac.main.POM_SA;

import resources.ExcelHandler;

public class RRCSites {

	public double totalReviewsCount, negativeCount, neutralCount, positiveCount, nACount;
	public double star0Count, star1Count, star2Count, star3Count, star4Count, star5Count;
	public double avgStarRating, reviewsMonth, reportCardScore;
	public String site, locationName;
	Integer TotalReviewsCol, NegativeCountCol, NeutralCountCol, PositiveCountCol, NACountCol,
	        s0CountCol, s1CountCol, s2CountCol, s3CountCol, s4CountCol, s5CountCol,
	        AvgStarRatingCol, ReviewsMonthCol;
	double totalReviewSum = 0.0, NegativeCountSum = 0.0, NeutralCountSum = 0.0, PositiveCountSum = 0.0,
			NACountSum = 0.0, s0CountSum = 0.0, s1CountSum = 0.0, s2CountSum = 0.0,
			s3CountSum = 0.0, s4CountSum = 0.0, s5CountSum = 0.0, AvgStarRatingSum = 0.0,
			ReviewsMonthSum = 0.0;


	void setRRCScoreVars(ExcelHandler workbook) {
		TotalReviewsCol = workbook.seacrh_pattern("TotalReviews", 0).get(0);
		NegativeCountCol = workbook.seacrh_pattern("NegativeCount", 0).get(0);
		NeutralCountCol = workbook.seacrh_pattern("NeutralCount", 0).get(0);
		PositiveCountCol = workbook.seacrh_pattern("PositiveCount", 0).get(0);
		NACountCol = workbook.seacrh_pattern("NACount", 0).get(0);
		s0CountCol = workbook.seacrh_pattern("Star0Count", 0).get(0);
		s1CountCol = workbook.seacrh_pattern("Star1Count", 0).get(0);
		s2CountCol = workbook.seacrh_pattern("Star2Count", 0).get(0);
		s3CountCol = workbook.seacrh_pattern("Star3Count", 0).get(0);
		s4CountCol = workbook.seacrh_pattern("Star4Count", 0).get(0);
		s5CountCol = workbook.seacrh_pattern("Star5Count", 0).get(0);
		AvgStarRatingCol = workbook.seacrh_pattern("AvgStarRating", 0).get(0);
		ReviewsMonthCol = workbook.seacrh_pattern("ReviewsMonth", 0).get(0);
	}



}
