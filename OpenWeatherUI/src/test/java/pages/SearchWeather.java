package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import api.utilities.helperMethods;

public class SearchWeather {
	WebDriver driver;
	WebDriverWait wait;
	public SearchWeather(WebDriver driver) {
        this.driver = driver; // Assign to instance field
        PageFactory.initElements(driver, this);
         wait = new WebDriverWait(driver, Duration.ofSeconds(60));
       
    }

	
	@FindBy(xpath = "//input[@name='q']")
	WebElement searchWeather;
	
	@FindBy(xpath = "//label[text()='Search']/following::input")
	WebElement searchBox;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement button_search;
	
	@FindBy(xpath = "(//table[@class='table']//td)[2]//b")
	WebElement searchResults;
	
	@FindBy(xpath = "//span[@class='orange-text']/following-sibling::h2")
	WebElement cityName;
	
	@FindBy(xpath = "//div[@class='current-temp']//span[1]")
	WebElement temperature;
	
	@FindBy(xpath = "//div[@class='alert alert-warning']")
	WebElement alert;
	
	
	public void searchWeather(String weatherCity) {
		searchWeather.sendKeys(weatherCity);
		searchWeather.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		try {
			wait.until(ExpectedConditions.visibilityOf(searchResults));
				String text = searchResults.getText().split(",")[0].trim();
				Assert.assertEquals(text, weatherCity);
				
			}
		
			catch(Exception e) {
				if(alert.isDisplayed()) {
					Assert.assertEquals(alert.getText(), "Not found");
				
				
			}
		}
	}
	
	public void searchWeatherInvalidCity(String weatherCity) {
		searchWeather.sendKeys(weatherCity);
		searchWeather.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		try {
			
			if(alert.isDisplayed()) {
				Assert.assertEquals(alert.getText(), "Not found");
			
				
			}
		}
		
			catch(Exception e) {
				System.out.println("invalid city");	
				
			}
		}
	}

	

