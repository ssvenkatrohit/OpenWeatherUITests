package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NavigateMaps {
	WebDriver driver;
	WebDriverWait wait;
	public NavigateMaps(WebDriver driver) {
        this.driver = driver; // Assign to instance field
        PageFactory.initElements(driver, this);
         wait = new WebDriverWait(driver, Duration.ofSeconds(60));
       
    }
	
	@FindBy(xpath = "//a[@href='/weathermap']")
	WebElement maps_menu;
	
	@FindBy(xpath = "//div[@class='weather-layer-container']")
	List<WebElement> map_filters;
	
	public void verifyElementsVisible() {
		maps_menu.click();
		for(WebElement mapFilters: map_filters) {
			
			Assert.assertTrue(mapFilters.isDisplayed());
			mapFilters.click();
		}
		
	}

}
