package pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.iosActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends iosActions {
	
	//creating a local object for the driver
	IOSDriver driver;
	
	//creating a constructor for instantiate the variables
	public AlertViews(IOSDriver driver) {
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
	
	//using the testing framework from ios for locate the elements
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`label=='Text Entry'`]")
	private WebElement textEntryMenu;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeCell")
	private WebElement textBox;
	
	@iOSXCUITFindBy(accessibility="OK")
	private WebElement acceptPopup;
	
	@iOSXCUITFindBy(iOSNsPredicate="type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;
	
	@iOSXCUITFindBy(iOSNsPredicate="label BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;
	
	@iOSXCUITFindBy(iOSNsPredicate="label=='Confirm'")
	private WebElement submit;
	
	
	
	public void fillTextLabel(String name) {
		
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopup.click();
	}
	
	public String getConfirmMessage() {
		
		confirmMenuItem.click();
		return confirmMessage.getText();
	}
	
	public void submittext() {
		
		submit.click();
	}

}
