package edubridge.nopcommerce.tests;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edubridge.nopcommerce.pageobject.LoginPage;
import edubridge.nopcommerce.utils.BrowserFactory;

public class LoginTest {

	WebDriver driver;
	
	@BeforeMethod
	public void StartBrowser() throws IOException {
		driver = BrowserFactory.LaunchBrowser();
	}
    @Test
	 public void Test_Login_Success(ITestContext context) {
		 
	        String userEmail = (String) context.getAttribute("userEmail");
	        
	        // Login with User
	        LoginPage obj_login = new LoginPage(driver,userEmail);
	        obj_login.performLogin();
	        
	        // Verify the cart has the product
	        assertEquals(obj_login.IsUserAuthenticated(), true);
	    }
}
