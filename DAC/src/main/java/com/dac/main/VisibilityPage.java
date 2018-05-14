package com.dac.main;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VisibilityPage {
	
	WebDriver driver;
	public VisibilityPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//found bars
	@FindBy(css = "div#barContainerGlobal>div>svg>g>g.highcharts-series.highcharts-series-1.highcharts-tracker>rect")
	private List<WebElement> GlobalBarsFound;
	@FindBy(css = "div#barContainerCA>div>svg>g>g.highcharts-series.highcharts-series-1.highcharts-tracker>rect")
	private List<WebElement> CABarsFound;
	@FindBy(css = "div#barContainerUS>div>svg>g>g.highcharts-series.highcharts-series-1.highcharts-tracker>rect")
	private List<WebElement> USBarsFound;
	
	
	//not found bars
	@FindBy(css = "div#barContainerGlobal>div>svg>g>g.highcharts-series.highcharts-series-0.highcharts-tracker>rect")
	private List<WebElement> GlobalBarsNtFound;
	@FindBy(css = "div#barContainerCA>div>svg>g>g.highcharts-series.highcharts-series-0.highcharts-tracker>rect")
	private List<WebElement> CABarsNtFound;
	@FindBy(css = "div#barContainerUS>div>svg>g>g.highcharts-series.highcharts-series-0.highcharts-tracker>rect")
	private List<WebElement> USBarsNtFound;
	
	
	//locations 
	@FindBy(xpath = "//div[@id='visibility_results_info']")
	private WebElement countOfLoctns;
	@FindBy(css = "div#barContainerGlobal>div>svg>g.highcharts-tooltip>text>tspan:nth-child(4)")
	private WebElement noOfLoctnsTooltip;
	
	//graph properties
		@FindBy(css = "g.highcharts-series.highcharts-series-0>path:nth-child(4)")
		private WebElement scoreGraph;	
		@FindBy(css = "div.highcharts-label.highcharts-tooltip>span>span:nth-child(1)")
		private WebElement scoreGraphTooltipDate;
		@FindBy(css = "div.highcharts-label.highcharts-tooltip>span>span:nth-child(2)")
		private WebElement scoreGraphTooltiploctns;
		@FindBy(css = "div.highcharts-label.highcharts-tooltip>span>span:nth-child(3)")
		private WebElement scoreGraphTooltipScore;
		
		
		
		//globalfilter  ui fluid search selection dropdown myList active visible
		@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList>i.dropdown.icon")
		private WebElement FilterCountry;
		@FindBy(css = "div[value=CA]")
		private WebElement optionCA;
		
				
		@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList1")
		private WebElement FilterState;	
		@FindBy(css = "div.menu.transition.visible>div:nth-child(2)")
		private WebElement stateOption1;
		
		@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList2")
		private WebElement FilterCity;	
		@FindBy(css = "div.menu.transition.visible>div:nth-child(2)")
		private WebElement cityOption1;
		
		
		@FindBy(css = "div.ui.fluid.search.selection.dropdown.myList3")
		private WebElement Filterlocation;
		@FindBy(css = "div.menu.transition.visible>div:nth-child(2)")
		private WebElement locationoption1;
		
		

		@FindBy(css = "button#apply_filter")
		private WebElement Apply_filter;
		
		
		
		
		
		
		
		
		public WebDriver getDriver() {
			return driver;
		}
		public List<WebElement> getGlobalBarsFound() {
			return GlobalBarsFound;
		}
		public List<WebElement> getCABarsFound() {
			return CABarsFound;
		}
		public List<WebElement> getUSBarsFound() {
			return USBarsFound;
		}
		public List<WebElement> getGlobalBarsNtFound() {
			return GlobalBarsNtFound;
		}
		public List<WebElement> getCABarsNtFound() {
			return CABarsNtFound;
		}
		public List<WebElement> getUSBarsNtFound() {
			return USBarsNtFound;
		}
		public WebElement getCountOfLoctns() {
			return countOfLoctns;
		}
		public WebElement getNoOfLoctnsTooltip() {
			return noOfLoctnsTooltip;
		}
		public WebElement getScoreGraph() {
			return scoreGraph;
		}
		public WebElement getScoreGraphTooltipDate() {
			return scoreGraphTooltipDate;
		}
		public WebElement getScoreGraphTooltiploctns() {
			return scoreGraphTooltiploctns;
		}
		public WebElement getScoreGraphTooltipScore() {
			return scoreGraphTooltipScore;
			
		}
		public WebElement getFilterCountry() {
			return FilterCountry;
		}
		public WebElement getFilterState() {
			return FilterState;
		}
		public WebElement getFilterCity() {
			return FilterCity;
		}
		public WebElement getFilterlocation() {
			return Filterlocation;
		}
		public WebElement getApply_filter() {
			return Apply_filter;
		}
		
		public WebElement getOptionCA() {
			return optionCA;
		}
		public WebElement getStateOption1() {
			return stateOption1;
		}
		public WebElement getCityOption1() {
			return cityOption1;
		}
		public WebElement getLocationoption1() {
			return locationoption1;
		}

	
	


	
	
	
	
	

}
