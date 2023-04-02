package edubridge.nopcommerce.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserFactory {
static WebDriver driver;
static Properties config; 
	
	//Open Browser
	public static WebDriver LaunchBrowser() throws IOException {
		
		WebDriverManager.chromedriver().setup();
		if(driver == null)
		{
			driver = new ChromeDriver();
	    	config = new Properties();
	    	FileInputStream fs = new FileInputStream("config.properties");
	    	config.load(fs);
	    	String url = config.getProperty("ApplicationUrl");
	    	driver.get(url);
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    	driver.manage().window().maximize();	
		}
		return driver;
		
	}
	
	//Close Browser
	public static void CloseBrowser() {
		
		driver.quit();
		
	}

}
