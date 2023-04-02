package edubridge.nopcommerce.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edubridge.nopcommerce.pageobject.RegisterPage;
import edubridge.nopcommerce.utils.BrowserFactory;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class RegisterTest{
	
	WebDriver driver;
	
	@BeforeMethod
	public void StartBrowser() throws IOException {
		driver = BrowserFactory.LaunchBrowser();
	}
	
	@Test
    public void Test_Register_Success(ITestContext context) {
        // Create a New User
        RegisterPage obj_register = new RegisterPage(driver);
        obj_register.register();
        
        context.setAttribute("userEmail", obj_register.registedEmail);
        
        // Verify the cart has the product
        assertEquals(obj_register.isConsumerRegistered(), true);
    }
	
}
