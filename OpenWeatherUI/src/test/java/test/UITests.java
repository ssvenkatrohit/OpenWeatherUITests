package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.NavigateMaps;
import pages.SearchWeather;
import util.ExcelUtil;

public class UITests extends TestBase{
	
	
	
	private static final Logger log = LogManager.getLogger(UITests.class);

	

	
	
	
	@Test
	 void UI_HomePage() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		log.info("validating UI Elements in HomePage");
		hp.verifyPageLoaded();
		
		hp.verifyUIELementsDisplayed();
		hp.searchAndVerifyCityWeather(hp.getCity().replaceAll("\\s+", ""));
		
	}
	

	@Test
	void searchWeatherInYourCity() {
		log.info("validating searchCity functionality ");
		SearchWeather weatherSearch = new SearchWeather(driver);
		String city = "Mumbai";
		String inValidCity = "adsdhasjd";
		weatherSearch.searchWeather(city);
		log.info("validating invalidCity Search functionality");
		weatherSearch.searchWeatherInvalidCity(inValidCity);
	}
	
	@Test
	void MapsView() {
		log.info("validating UI Elements and options on Maps page");
		NavigateMaps maps = new NavigateMaps(driver);
		
		maps.verifyElementsVisible();
	}
	
	
}
