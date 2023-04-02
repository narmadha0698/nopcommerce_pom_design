package edubridge.nopcommerce.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Address;

import com.github.javafaker.Faker;

import edubridge.nopcommerce.utils.CommonUtil;

public class CheckoutPage {

	WebDriver _driver;
	Faker faker;
	Address address;
	public CheckoutPage(WebDriver driver)
	{
		this._driver = driver;
		this.faker = new Faker();
		this.address = faker.address();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".action__submit")
	 private WebElement submit;

	@FindBy(id = "BillingNewAddress_CountryId")
	private WebElement Country;
	
	@FindBy(id = "BillingNewAddress_StateProvinceId")
	private WebElement State;
	
	@FindBy(id = "BillingNewAddress_City")
	private WebElement City;
	
	@FindBy(id = "BillingNewAddress_Address1")
	private WebElement AddressFirstLine;
	
	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	private WebElement ZipCode;
	
	@FindBy(id = "BillingNewAddress_PhoneNumber")
	private WebElement PhoneNumber;
	
	@FindBy (className = "new-address-next-step-button")
	private WebElement saveAddress;
	
	//button-1 shipping-method-next-step-button
	@FindBy (className = "shipping-method-next-step-button")
	private WebElement shippingMethodNextButton;
	
	@FindBy (className = "payment-method-next-step-button")
	private WebElement paymentMethodNextButton;
	
	@FindBy (className = "payment-info-next-step-button")
	private WebElement paymentInfoNextButton;
	
	@FindBy (className = "confirm-order-next-step-button")
	private WebElement confirmOrderButton;
	
	private void selectFakerAddress()
	{
		this.address = faker.address();
		if(!CommonUtil.isTextPresent(_driver,address.country()))
		{
			selectFakerAddress();
		}
	}
	
	public boolean StartCheckOut()
	{
		 WebElement acceptTermsCheckBox = _driver.findElement(By.id("termsofservice"));
	   	 if(acceptTermsCheckBox.isDisplayed() && !acceptTermsCheckBox.isSelected())
	   	 {
	   		 acceptTermsCheckBox.click(); 
	   	   	 WebElement proceedCheckout = _driver.findElement(By.id("checkout"));
	   	   	 if(proceedCheckout.isDisplayed())
	   	   	 {
	   	   		 proceedCheckout.click();  
	   	   		 return true;
	   	   	 }
	   	   		 
	   	 }
	   	 return false;
   	 
	}
	
	public void CompleteCheckOut(String CountryName, String StateName)
	{
		selectFakerAddress();
		Select selectCountry = new Select(Country);
        selectCountry.selectByVisibleText(CountryName);
        _driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Select selectState = new Select(State);
		selectState.selectByVisibleText(StateName);
		City.sendKeys(address.cityName());
		ZipCode.sendKeys(address.zipCode());
		PhoneNumber.sendKeys(faker.phoneNumber().phoneNumber());
		AddressFirstLine.sendKeys(address.streetAddress());
		saveAddress.click();
		_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		shippingMethodNextButton.click();
		_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		paymentMethodNextButton.click();
		_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		paymentInfoNextButton.click();
		_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		confirmOrderButton.click();
	}
	
}
