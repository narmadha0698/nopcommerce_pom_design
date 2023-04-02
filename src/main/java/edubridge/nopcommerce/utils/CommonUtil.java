package edubridge.nopcommerce.utils;

import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

public class CommonUtil {

	public static String generteEmail()
    {
		Faker faker = new Faker();
	    return faker.internet().emailAddress();
    }
	
	public static boolean isTextPresent(WebDriver driver, String Text )
    {
		return driver.getPageSource().contains(Text);
    
    }
	
}
