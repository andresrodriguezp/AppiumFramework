package pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {
	
	//creating a local object for the driver
	AndroidDriver driver;
	
	//creating a constructor for instantiate the variables
	public FormPage(AndroidDriver driver) {
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
		
	//in page object factory there is an annonattion for find the elements
	//we are using this annotation for find the elements
	//and we will store it in the private webelement
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;//we create the variables as private for ensure they dont be used in the test for make actions of them, if an action is required a method should be used
	
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement spinnerCountry;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private List<WebElement> errorMessages;
	
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private WebElement errorMessage;
		
	//creating methods for execute actions in the elements
	public void setNameField(String name) {
		
		nameField.sendKeys(name);
		// here because at the moment i type the name the keyboard is present and in order to hide it for continues finding another elements
		//we will use a method HideKeyboard
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		
		if(gender.contains("female")) {
			femaleOption.click();
		}
		else {
			maleOption.click();
		}

	}
	
	public void setCountrySelection(String CountryName) {
		
		spinnerCountry.click();
		scrollToText(CountryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+CountryName+"']")).click();
		
	}
	
	public ProductCataloge submitForm() {
		
		shopButton.click();
		//creating an object of productcataloge page and returnig it
		//by doing this i dont need to create object of every page in the testcase
		//i create the object previous to go to the new page
		return new ProductCataloge(driver);
	}
	
	public int checkingSizeErrorMessage() {
		
		return errorMessages.size();
	}
	
	public String gettingErrorMessage(String attribute) {
		
		return errorMessage.getAttribute(attribute);
	}
	
	//setting the location where the app shoudl open
	public void setActivity() {
		
		// we can start our testcase in a specific window, without necessity to go until the window in the application that we will test
		// for this we will make use of the Activity class
		// in order to know the app package and the activity we will make use of a command in the cmd
		// the command is "adb shell dumpsys window | find "mCurrentFocus"" this is for windows
		// "adb shell dumpsys window | grep -E "mCurrentFocus"" this is for mac
		// the arguments of the activity classes are the package name and the activity name, which we take from the command
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		
		// then we start the dirver in the activity that we wants
		driver.startActivity(activity);
	}

}
