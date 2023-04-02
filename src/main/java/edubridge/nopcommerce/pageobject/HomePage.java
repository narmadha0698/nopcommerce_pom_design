package edubridge.nopcommerce.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import edubridge.nopcommerce.utils.CommonUtil;
import edubridge.nopcommerce.utils.Constants;

public class HomePage {

	WebDriver _driver;
	
	public HomePage(WebDriver driver)
	{
		_driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="small-searchterms")
	WebElement searchBox;
	
	@FindBy(className = "ui-menu-item-wrapper")
	WebElement searchMenuItems;
	
	@FindBy(className = "add-to-cart-button")
	WebElement addToCartButton;
	
	@FindBy(xpath = "//*[@id=\"bar-notification\"]/div/p/a")
	WebElement checkoutLink;
	
	public boolean SearchProduct(String productName)
	{
		if(productName != null)
		{
			searchBox.sendKeys(productName);
			if(searchMenuItems.isDisplayed())
				searchMenuItems.click();
			return IsValiProduct();
		}
		return false;
	}
	
	public boolean AddProductToCartAndCheckOut()
	{
		addToCartButton.click();
    	
    	_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        	
    	if(checkoutLink.isDisplayed() && checkoutLink.isEnabled())
    	{
    		checkoutLink.click();
    		return true;
    	}
    	return false;
	}
	
	public boolean IsValiProduct() {
		return !CommonUtil.isTextPresent(_driver, Constants.Invalid_ProductSearch_Message);
	}
}
