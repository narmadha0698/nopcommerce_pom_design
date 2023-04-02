package edubridge.nopcommerce.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.github.javafaker.Faker;

import edubridge.nopcommerce.utils.CommonUtil;
import edubridge.nopcommerce.utils.Constants;

public class RegisterPage {

	WebDriver _driver;
	public String registedEmail = "";
	Faker faker;
	public RegisterPage(WebDriver driver)
	{
		this._driver = driver;
		this.faker = new Faker();
	}
	public String register() {
		goToRegister();
		_driver.findElement(By.id("FirstName")).sendKeys(faker.name().firstName());
		_driver.findElement(By.id("LastName")).sendKeys(faker.name().firstName());
		registedEmail = CommonUtil.generteEmail();
		_driver.findElement(By.id("Email")).sendKeys(registedEmail);
		_driver.findElement(By.id("Password")).sendKeys(Constants.Default_Password);
		_driver.findElement(By.id("ConfirmPassword")).sendKeys(Constants.Default_Password);
		_driver.findElement(By.id("register-button")).click();
		return registedEmail;
	}
	
	public boolean isConsumerRegistered() {
		return CommonUtil.isTextPresent(_driver, "Your registration completed");
	}
	
	public void goToRegister()
	{
		_driver.findElement(By.className("ico-register")).click();
		
	}
}
