package aslamwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import aslamwork.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	public  CheckoutPage(WebDriver driver)
	{
		super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	@FindBy(css=".ta-item:last-child")
	WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By country = By.cssSelector("[placeholder='Select Country']");
	By result=By.cssSelector(".ta-results");
	
	public void setCountry(String countryName)
	{
		setAutoCompleteUsingSendkeys(country, countryName);
		waitForElementForAppear(result);
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);

	}

}
