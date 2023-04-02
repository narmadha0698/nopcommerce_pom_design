package edubridge.nopcommerce.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import edubridge.nopcommerce.utils.CommonUtil;
import edubridge.nopcommerce.utils.Constants;

public class LoginPage {

	WebDriver _driver;
	public String userEmail;
	public LoginPage(WebDriver driver,String Email)
	{
		this._driver = driver;
		this.userEmail = Email;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="ico-login")
	WebElement loginLink;
	
	@FindBy(id="Email")
	WebElement Email;
	
	@FindBy(id="Password")
	WebElement Password;
	
	@FindBy(className="login-button")
	WebElement LoginButton;
	
	public void performLogin() {
		if(!IsUserAuthenticated())
		{
			loginLink.click();
			Email.sendKeys(userEmail);
			Password.sendKeys(Constants.Default_Password);
			LoginButton.click();
        	_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		
	}
	public boolean IsUserAuthenticated() {
		return CommonUtil.isTextPresent(_driver, Constants.LogOut_Link);
	}
}
