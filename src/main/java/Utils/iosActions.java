package Utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class iosActions extends AppiumUtils {
	
IOSDriver driver;
	
	public iosActions(IOSDriver driver) {
		this.driver=driver;
	}
	
public void IOSLongPress(WebElement ele, int duration) {
		
		//longpress ios gesture
		//first we create a map object where we will get key pais values
		Map <String,Object> params = new HashMap<>();
		//then we assing the values
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration", duration);
		//then we execute the script with the gesture and the map values
		//the gesture is touchAndHold
		((JavascriptExecutor)driver).executeScript("mobile:touchAndHold", params);
	}
	
	public void IOSScroll(WebElement ele, String direction) {
		//scrolling ios gesture
		//first we create a map object for past the keys
		Map<String, Object> params = new HashMap<>();
		//then we assign the values
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction", direction);
		//then we execute the script with the gesture and the map values
		//the gesture is scroll
		//additinoally it is not necessary to typecast to javascriptexecutor due this class extends from remotewebdriver who already implements the javascriptexecutor interface
		driver.executeScript("mobile: scroll", params);
	}
	
public void SwipeAction(String direction) {
		
	//here we are creating a map with the parameter to be used for the swipe
		//if only the direction of the swipe is set, without setting the webelement where to swipe, thw swipe will be done always on the center of the element
		Map<String,Object> params1 = new HashMap<String,Object>();
		//setting the direction of the swipe
		params1.put("direction", direction);
		//here we are executiong the script for the swipe
		//where we gesture is swipe
		driver.executeScript("mobile:swipe", params1);
		}
	

}
