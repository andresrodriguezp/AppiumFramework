package endtoEndMobile;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.android.CartPage;
import pageObjects.android.FormPage;
import pageObjects.android.ProductCataloge;
import testUtils.BaseTest;

public class HibrydTest extends BaseTest {
	
	//indicating the testcase that will receive data from dataprovider
	//addtionally we can group the test for then just run the cases by group
	@Test(dataProvider="getData", groups= {"Smoke"})
	//indicating this method testcase will receive data from dataprovider
	public void Hybirdmode(HashMap<String,String> input) throws InterruptedException {
		
        //here i can call formpage directly because it comes from the base class which i extended
		//there was created the object
		//calling the method from formpage for set the namefield
		//as an input we set the data retreived from dataprovider hashmap which is a keyvalue pair
		formpage.setNameField(input.get("name"));
		//calling the method for select the gender
		//as an input we set the data retreived from dataprovider hashmap which is a keyvalue pair
		formpage.setGender(input.get("gender"));
		//calling the method for select the country
		//as an input we set the data retreived from dataprovider hashmap which is a keyvalue pair
		formpage.setCountrySelection(input.get("country"));
		
		//this is the method for click and go to products, additionally we are skipping the object creation from next page in the test
		//because the method return an object of the next page, product cataloge
		//here we will assing it to a varuable productcataloge
		//and we will be able to use it in the test without creating object of prodyctcataloge class
		ProductCataloge productcataloge = formpage.submitForm();
		
		//calling method for add products from productcataloge class
		productcataloge.addItemToCartByIndex(0);
		productcataloge.addItemToCartByIndex(0);
		//calling method for got to cartpage
		//because the method return an object of the next page, cartpage
		//here we will assing it to a variable cartpage
		//and we will be able to use it in the test without creating object of cartpage class
		CartPage cartpage= productcataloge.goToCartPage();

		//using explicit wait method from cartpage for wait for the tittle of cartpage to appear
		cartpage.waitingForCartPageTittle();

		//calling the method for calculate products sum
		double totalSum=cartpage.getProductSum();
		//calling the method for get totalsum from the page
		double total=cartpage.getTotalAmmountDisplayed();

		Assert.assertEquals(totalSum, total);

		//calling the method for make longpress and accept the terms and conditions
		cartpage.acceptTermsConditions();
		
		//calling the method that will submit the order
		cartpage.submitOrder();
/*
		// giving a wait for wait a page to load
		Thread.sleep(6000);
		// by using getcontecthandles, we are indicating to pur driver to give us all context of what is currently running it means all available context
		Set<String> contexts = driver.getContextHandles();
		//here we are writing a for got get all the current contexts from the driver
		for(String context : contexts) {
			
			System.out.println(context);
		}
		// here we are indicating we will switch to the context of webdriver by getting the correct name from the for before
	  driver.context("WEBVIEW_com.androidsample.generalstore");
	  driver.findElement(By.name("q")).sendKeys("Andres");
	  driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	  driver.pressKey(new KeyEvent(AndroidKey.BACK));
	  //switching to the app context
	  driver.context("NATIVE_APP");
		*/
	}
	
	//using data provideer for sent data to the testcases
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//here we are using the method created in appiumutils to convert a json file into Hashmap
		List<HashMap<String,String>>data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//testData//eCommerce.json");
		//declaring a 2 dimensional arrays for set the data
		// here we are getting the Hashmap converted and retreiving the data into a 2 dimensional array
		//returning the array created, dataprovider expects to return always a 2 dimensional array
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	//here before everytestcase we are setting it to the homepage
	//we need to to this because we are using dataprovider with 2 set of data
	//so the test will run as many times as set of data is passed
	//and for this we need to ensure the driver points to the homepage
	@BeforeMethod(alwaysRun=true)
	public void preSetup() {
		
		formpage.setActivity();

	}
	
}
