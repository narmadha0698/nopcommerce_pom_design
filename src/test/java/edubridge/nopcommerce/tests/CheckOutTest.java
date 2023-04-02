package edubridge.nopcommerce.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import edubridge.nopcommerce.pageobject.CheckoutPage;
import edubridge.nopcommerce.utils.BrowserFactory;

public class CheckOutTest {
	WebDriver driver;
	CheckoutPage obj_checkout;
	
	@DataProvider(name = "country-data-provider")
	public Object[][] SearchData() {
		return new Object[][] { { "United States", "California" } };
	}
	
	@BeforeMethod
	public void StartBrowser() throws IOException {
		driver = BrowserFactory.LaunchBrowser();
		obj_checkout = new CheckoutPage(driver);
	}
	
	@Test(priority = 1)
	public void Start_CheckOut_Success() {

		// Initiate CheckOut
		assertTrue(obj_checkout.StartCheckOut());
	}
	
	@Test(dataProvider = "country-data-provider", priority = 2)
	public void Complete_CheckOut_Success(String Country, String State) {

		// Complete CheckOut
		obj_checkout.CompleteCheckOut(Country, State);
	}
	
}
