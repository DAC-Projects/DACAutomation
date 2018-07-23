package com.dac.main.POM_CA;

import java.util.ArrayList;
import java.util.List;

public class Competitor extends Sites {
	
	public Competitor(String competitor) {
		this.competitorName =competitor;
	}
	
	public String competitorName;
	public List<Sites> sites = new ArrayList<Sites>(); 
	
}
