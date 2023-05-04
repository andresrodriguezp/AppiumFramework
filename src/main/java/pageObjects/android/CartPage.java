package pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	
	//creating a local object for the driver
	AndroidDriver driver;
	
	//creating a constructor for instantiate the variables
	public CartPage(AndroidDriver driver) {
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
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> prices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement tittle;
	
	public double getProductSum() {
		
		int count =prices.size();
		double totalsum=0;
		for(int i=0; i<count; i++) {
			
			String ammountstring =prices.get(i).getText();
           // here we are calling the method from basetest that convert the string ammount received in the correct format for being able to summ them
			double price= getFormattedAmmount(ammountstring);
			totalsum= totalsum+price;
		}
		return totalsum;
	}
	
	public double getTotalAmmountDisplayed() {
		
	return	getFormattedAmmount(totalAmount.getText());
		
	}
	
	public void acceptTermsConditions() {
		
		LonPressAction(termsButton);
		acceptButton.click();
	}
	
	public void submitOrder() {
		
		checkBox.click();
		proceed.click();
	}
	
	public void waitingForCartPageTittle() {
		
		waitForAttributeToContains(tittle, "text", "Cart", driver );
	}

}
