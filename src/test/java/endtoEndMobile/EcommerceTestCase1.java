package endtoEndMobile;

import java.security.KeyStore;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import testUtils.BaseTest;

public class EcommerceTestCase1 extends BaseTest {
	
	//here before everytestcase we are setting it to the homepage
	@BeforeMethod
	public void preSetup() {
		
		formpage.setActivity();

	}
	
	@Test
	public void ErrorMessage() {
		
		formpage.setGender("female");
		formpage.setCountrySelection("Colombia");
		formpage.submitForm();
		
		//validating the error message
		String toastmessage = formpage.gettingErrorMessage("name");
		Assert.assertEquals(toastmessage, "Please enter your name");
				
		}
	
	@Test
	public void Fillform() {
		
		formpage.setNameField("Andres");
		formpage.setGender("female");
		formpage.setCountrySelection("Colombia");
		formpage.submitForm();
		
		//by putting this assert we are checking no error message is displayed because the size will be 0
		Assert.assertTrue(formpage.checkingSizeErrorMessage()<1);
		
		}
	
}
