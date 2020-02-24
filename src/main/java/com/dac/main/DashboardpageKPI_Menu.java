package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardpageKPI_Menu {

	public DashboardpageKPI_Menu(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='location']/div[2]/span")
	private static WebElement Locations;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/span")
	private  WebElement Visibilityscore;

	@FindBy(xpath = "//*[@id='visibility']/div[2]/div[2]/span")
	private  WebElement VisibilityLoc;
	
	@FindBy(xpath = "//*[@id='accuracy']/div[2]/span")
	private static WebElement Accuracyscore;
	
	@FindBy(xpath = "//*[@id='accuarcy']/div[2]/div[2]/span")
	private static WebElement AccuracyLoc;
	
	@FindBy(xpath = "//*[@id='contentAnalysis']/div[2]/span")
	private static WebElement Contentscore;
	
	@FindBy(xpath = "//*[@id='contentAnalysis']/div[2]/div[2]/span")
	private static WebElement ContentLoc;
	
	@FindBy(xpath = "//*[@id='googleRanking']/div[2]/span")
	private static WebElement GRScore;
	
	@FindBy(xpath = "//*[@id='googleRanking']/div[2]/div[2]/span")
	private static WebElement GRLoc;
	
	
	
	public static int getLocations() {
		int loc = Integer.parseInt(Locations.getText());
		System.out.println(loc);
		return loc;
		
	}

	public  double getVisibilityscore() throws InterruptedException {
		Thread.sleep(6000);
		String sc = Visibilityscore.getText().replace("%", "");
		double visibilityscore = Double.parseDouble(sc);
		System.out.println(visibilityscore);
		return visibilityscore;		
		
	}
	
	public int getVisibilityLoc() {		
		int visibilityloc = Integer.parseInt(VisibilityLoc.getText());
		System.out.println(visibilityloc);
		return visibilityloc;		
		
	}

	public static double getAccuracy() {
		
		double accuracyscore = Double.parseDouble(Accuracyscore.getText());
		System.out.println(accuracyscore);
		return accuracyscore;
	}
	
	public static int getAccuracyLoc() {
		
		int accuracyloc = Integer.parseInt(AccuracyLoc.getText());
		System.out.println(accuracyloc);
		return accuracyloc;
	}
	
	public static double getContentscore() {
		
		double contentscore = Double.parseDouble(Contentscore.getText());
		System.out.println(contentscore);
		return contentscore;
	}
	
	public static int getContentLoc() {
		
		int contentloc = Integer.parseInt(ContentLoc.getText());
		System.out.println(contentloc);
		return contentloc;
	}
	
	public static double getGRScore() {
		
		double GRscore = Double.parseDouble(GRScore.getText());
		System.out.println(GRscore);
		return GRscore;
	}
	
	public static int getGRLoc() {
		
		int GRloc = Integer.parseInt(GRLoc.getText());
		System.out.println(GRloc);
		return GRloc;
	}
}