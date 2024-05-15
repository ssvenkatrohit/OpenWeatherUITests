package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;




public class TestBase {
	
	protected static WebDriver driver;
	public static Properties envConfig;
	WebDriverWait wait;
	Properties property = new Properties();

	
	@BeforeSuite

    public void suiteSetup() throws Exception {
		InputStream configFile = new FileInputStream(System.getProperty("user.dir") + 
				"\\src\\test\\java\\config\\envConfig.properties");		 
		
		property.load(configFile);
		String browser=property.getProperty("browser").toLowerCase();
		
		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup(); 
			driver = new FirefoxDriver();
         }
		else if (browser.equals("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);             
         }
		 else {
             throw new RuntimeException("Browser type unsupported");
         }
		driver.manage().window().maximize();
		wait= new WebDriverWait(driver, Duration.ofSeconds(40));
		driver.get(property.getProperty("baseUrl"));  
		
		  
	    driver.findElement(By.xpath("//div//button[contains(text(),'Accept')]")).click();
	        
	}

	@BeforeMethod()
    public void loadBaseUrl(Method method) {
		
        driver.get(property.getProperty("baseUrl"));   
        
      
    }
 
	@AfterMethod
	public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
		//Taking screenshot in case of failure
		if(testResult.getStatus() == ITestResult.FAILURE){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-" 
					+ Arrays.toString(testResult.getParameters()) +  ".jpg"));	
			}
	}
 
    @AfterSuite
    public void suiteTearDown() {
    	driver.quit();
    }

}
