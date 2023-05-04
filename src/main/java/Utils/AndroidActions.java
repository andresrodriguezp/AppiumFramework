package Utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver=driver;
	}
	
public void LonPressAction(WebElement ele) {
		
		//executing scripts for being able to execute gestures
		//for this we typecast our driver to javascript executor for execute a java script
		// then we indicate the gesture to execute
		// where to execute and the duration, these in key value pais due this is a tablemap
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));

	}
	
	public void SwipeAction(WebElement firstImage, String direction) {
		
		// performing a swipe
		//executing scripts for being able to execute gestures
		//for this we typecast our driver to javascript executor for execute a java script
		// then we indicate the gesture to execute
		// where to execute, the direction and the percentage that is the size of the swipe, these in key value pais due this is a tablemap
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) firstImage).getId(), "direction", direction,
			    "percent", 0.75
			));
	}
	
	public void DragandDrope(WebElement source, int endx, int endy) {
		
		//draganddrope
		//executing scripts for being able to execute gestures
		//for this we typecast our driver to javascript executor for execute a java script
		// then we indicate the gesture to execute
		// where to execute and the coordinates where to drope, these in key value pais due this is a tablemap
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) source).getId(),
			    "endX", endx,
			    "endY", endy
			));
	}
	
	public void ScrolltoEndByArea() {

		//other way to scroll is using gestures
		//here with the use of do-while we will scroll until there is not more where to scroll
		boolean canScrollMoreArea;
		do {

		canScrollMoreArea = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		
		}while (canScrollMoreArea);

	}
	
	public void ScrolltoEndByElement(WebElement elemento) {
		
		//other way to scroll is using gestures
		//here with the use of do-while we will scroll until there is not more where to scroll
		boolean canScrollMore;
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) elemento).getId(),
			    "direction", "down",
			    "percent", 6.0
			));
		
		}while (canScrollMore);
	}
	
	public void scrollToText(String text) {
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
}
