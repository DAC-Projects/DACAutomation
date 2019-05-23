package com.dac.testcases.SA;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.testng.annotations.Test;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SA.SA_Reviews;
import resources.BaseClass;
import resources.CurrentState;

public class SA_ReviewReportSorting_Test extends BaseClass {

	@Test(enabled = true)
	public void navigateToRRC() throws Exception {
		Navigationpage np=new Navigationpage(CurrentState.getDriver());
		//np.select_DB_Lang_Link("en", "US");
		np.navigateToSA_ReviewReport();
		//Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "Navigated to Review Report Card page", "yes");
	}
	
	@Test(enabled = true, dependsOnMethods= {"navigateToRRC"})
	public void sortByNewest_Oldest() {
		SA_Reviews s = new SA_Reviews(CurrentState.getDriver());
		String[] dateSorts = {"Date - Oldest","Date - Newest"};
		for(String dateSort:dateSorts) {
			String selected = s.selectSort(dateSort);
			ArrayList<Date> reviewDateList = s.getAllReviewDatesSort();
			try {
				if(selected.equals("Date - Oldest")) s.checkASCDateOrder(reviewDateList);
				else if(selected.equals("Date - Newest")) s.checkDSCDateOrder(reviewDateList);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test(enabled = true, dependsOnMethods= {"sortByNewest_Oldest"})
	public void sortByHighest_Lowest() {
		SA_Reviews s = new SA_Reviews(CurrentState.getDriver());
		String[] ratingSorts = {"Star Rating - Highest","Star Rating - Lowest"};
		for(String ratingSort:ratingSorts) {
			String selected = s.selectSort(ratingSort);
			ArrayList<Integer> ratingList = s.getSortedRatings();
			try {
				if(selected.equals("Star Rating - Highest")) s.checkASCRatingOrder(ratingList);
				else if(selected.equals("Star Rating - Lowest")) s.checkDSCRatingOrder(ratingList);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test(enabled = true, dependsOnMethods= {"sortByHighest_Lowest"})
	public void sortByLocationName() {
		SA_Reviews s = new SA_Reviews(CurrentState.getDriver());
		String selected = s.selectSort("Location Name");
		ArrayList<String> locationList = s.getLocationNameSort();
		if(selected.equals("Location Name"))
			s.checkASCLocationNameOrder(locationList);
	}
	
}
