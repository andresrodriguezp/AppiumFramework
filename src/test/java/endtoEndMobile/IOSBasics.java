package endtoEndMobile;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import pageObjects.ios.AlertViews;
import testUtils.IOSBaseTest;

public class IOSBasics extends IOSBaseTest {
	
	@Test
	public void IOSBasicTest(){
		
		//calling the method for make click in the alert views
		//here we dont need to create objects because it was created in the iosbasetest and we extend from it
		//additionally because win the home page we returning object of alertviewpage
		//here we will create an alertview variable and will have access to all the methods in alert views class
		AlertViews alertViews= homepage.selectAlertViews();
		
		//calling the method that will entrer the text and click ok
		alertViews.fillTextLabel("hello");

		//calling the method that will get the confirmmessage
		String actualMessage= alertViews.getConfirmMessage();
		Assert.assertEquals(actualMessage, "A message should be a short, complete sentence");
		
		//calling the method for confirm
		alertViews.submittext();
	}

}
