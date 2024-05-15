package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import api.test.weatherTests;
import api.utilities.helperMethods;
import base.TestBase;
import util.ExcelUtil;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
        this.driver = driver; // Assign to instance field
        PageFactory.initElements(driver, this);
         wait = new WebDriverWait(driver, Duration.ofSeconds(60));
       
    }
	
	
	@FindBy(xpath="//input[@name='q']")
	WebElement searchbar;
	
	@FindBy(xpath="//input[@placeholder='Search city']")
	WebElement searchbar_midPage;
	
	@FindBy(xpath = "//span[@class='orange-text']/following-sibling::h2")
	WebElement cityName;
	
	@FindBy(xpath = "//div[@class='current-temp']//span[1]")
	WebElement temperature;
	
	//@FindBy(xpath = "//div[@class='weather-alert orange']")
	//WebElement weatherAlertOrange;
	
	@FindBy(xpath = "//li/span[@class='symbol']")
	WebElement humidity;
	
	
	
	@FindBy(xpath="(//ul[contains(@class,'weather-items text-container')]//li)[2]")
	WebElement pressure;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement buttonSearch;
	
	@FindBy(xpath="//ul[@class='search-dropdown-menu']//span[1]")
	WebElement searchDropdown;
	
	public String getWeather() {
		String s  =humidity.getText();
		String g = humidity.toString();
		String m = humidity.getAttribute("innerHTML");
	
		System.out.println(s);
		System.out.println(g);
		System.out.println(m);
		
		return  s;
	}
	
	
	
	public void verifyPageLoaded() {

		Assert.assertEquals(driver.getTitle(), "Current weather and forecast - OpenWeatherMap");
	}
	public  String getCity() {
		String s = cityName.getText();
		return s;
	}

	public void verifyUIELementsDisplayed() {
		List<WebElement> UIElements = new ArrayList();
		//UIElements.add(humidity);
		UIElements.add(buttonSearch);
		UIElements.add(searchbar_midPage);
		//UIElements.add(weatherAlertOrange);
		UIElements.add(temperature);
		UIElements.add(cityName);
		UIElements.add(searchbar);
		wait.until(ExpectedConditions.visibilityOfAllElements(UIElements));
	}

	public void searchAndVerifyCityWeather(String cityName) {
		//searchbar_midPage.sendKeys(cityName);
	//	buttonSearch.click();
		//wait.until(ExpectedConditions.visibilityOf(searchDropdown)).click();
	
		List<String>API_output =helperMethods.currentWeatherSpecificCity(cityName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", searchbar_midPage);

		String temp = temperature.getText().substring(0, temperature.getText().length()-2);
		String press = pressure.getText().substring(0, pressure.getText().length()-3);
		String humi = humidity.getText().substring(0, humidity.getText().length()-1);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(temp, API_output.get(0), "Temperature API "+API_output.get(0)+"'C, UI "+ temperature.getText()+".  " );
		
		sa.assertEquals(press, API_output.get(1)," Pressure API "+ API_output.get(1)+ "hPa, UI "+ pressure.getText()+".   ");
		//sa.assertEquals(humi, API_output.get(2)," Humidity API "+ API_output.get(2)+ "%, UI "+ humidity.getText()+".   ");
		sa.assertAll();
		
	}
	
	
	
	
}
