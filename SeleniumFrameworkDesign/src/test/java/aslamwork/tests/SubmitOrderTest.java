package aslamwork.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import aslamwork.TestComponents.BaseTest;
import aslamwork.pageobjects.CartPage;
import aslamwork.pageobjects.CheckoutPage;
import aslamwork.pageobjects.ConfirmationPage;
import aslamwork.pageobjects.LandingPage;
import aslamwork.pageobjects.OrderPage;
import aslamwork.pageobjects.ProductCatalogue;
import aslamwork.data.DataReader;

 
public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{	
	String country= "india";
	ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
	productCatalogue.getProductList();
	productCatalogue.getProductByName(input.get("product"));
	productCatalogue.addProductToCart(input.get("product"));
	CartPage cartpage = productCatalogue.goToCartPage();
	boolean match = cartpage.VerifyProductDisplay(input.get("product"));
	Assert.assertTrue(match);
	CheckoutPage checkout = cartpage.goToCheckout();
	checkout.setCountry(country);
	ConfirmationPage confirm = checkout.submitOrder();
	String SuccessMessage = confirm.getSuccessMessage();
	Assert.assertTrue(SuccessMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()  
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("aslam81@gmail.com", "Khushi@143");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay("productName"));
		}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//aslamwork//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}


//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "aslam.dhanbad@gmail.com");
//		map.put("password", "Khushi@143");
//		map.put("productName", "ZARA COAT 3");
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "aslam81@gmail.com");
//		map1.put("password", "Khushi@143");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		return new Object[][]  {{map},{map1}};
//	}
//	
}
