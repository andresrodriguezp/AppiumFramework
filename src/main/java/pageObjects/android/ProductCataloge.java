package pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCataloge extends AndroidActions {
	
	//creating a local object for the driver
	AndroidDriver driver;
	
	//creating a constructor for instantiate the variables
	public ProductCataloge(AndroidDriver driver) {
		//here we using super fro sent the driver to the parent class in order the parent class hask knowledge of it and cal use it
		super(driver);
		//initializing the local driver
		this.driver=driver;
		//in order to locate elements using page afctory we will need to initialize it in the conctructor
		//it expect two arguments, decorator and object
		//for the decorator we use an object of appiumfielddecorator class, like this we are decorating the driver and instantiate it as a mobileelement
		//for object we use this, it meants elements attached to the class will be set 
		//so by using pagefactory we will initiate the driver instance, in this case with the decorator will be mobile and we will get mobile elements for this class by using appiumby
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//here because we are getting a list of webelements we create our variable as a list of webelements
	//by doing it, it is treathed as driver.finElements. plural
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement Cart;
	
	public void addItemToCartByIndex(int index) {
		
		addToCart.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException {
		
		Cart.click();
		Thread.sleep(3000);
		//creating an object of cart page and returnig it
		//by doing this i dont need to create object of every page in the testcase
		//i create the object previous to go to the new page
		return new CartPage(driver);
	}

}
