package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import Utils.AppiumUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pageObjects.ios.HomePage;

public class IOSBaseTest extends AppiumUtils {
	//note for run ios tyesting we need a ios pc, so for now im just writting the code but wont be able to test it
	
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homepage;

	@BeforeClass
public void ConfigureAppium() throws IOException {
		
		//for set global properties it is recommended to use a properties file
		//this file will have all defautl properties like ipaddres, port, devicename
		//then using java class properties we will get those values for beint used in the configuration of our framewrok
		Properties prop = new Properties();
		//using the load method we will indicate the location of the properties file
		//because the input of the method load is inputstream, we will first convert the file into an input stream using FileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		//now we can get the values from the properties file using the getproperty method
		String iPAddress=prop.getProperty("ipAdress");
		String port=prop.getProperty("port");
		String IOSDeviceName=prop.getProperty("IOSdDeviceName");
		
		service=startAppiumServer(iPAddress, Integer.parseInt(port));
	
		//service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\ANDRES\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withIPAddress("127.0.0.1").usingPort(4723).usingDriverExecutable(new File("‪C:\\Program Files\\nodejs\\node.exe")).build();
	
	//creating object of xcuitest for provide the desired capabilities to the driver for ios automation
	XCUITestOptions options = new XCUITestOptions();
	//setting the emulator, the emulator is the device emulator
	options.setDeviceName(IOSDeviceName);
	//setting the app
	options.setApp(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\{IOSappname.app}");
	//setting the ios version number
	options.setPlatformVersion("{iosversionnumber}");
	
	/*//these 4 capabilities need to be set for run testing in a real device
	options.setCapability("xcodeOrgId", "here the value");//this value is get from the team
	options.setCapability("xcodeSigningId","iPhone Developer");//default value
	options.setCapability("udid","gere the value");//tied up to the real device
	options.setCapability("updateWDABundleId","here the value");// this value is get from the team
	*/
	//setting a timeout for the wedriver agent installation on the device
	//in ios when we will automate, automatically a webdriver agent is installed on the device, this agent will help in the automation
	//in order to give time this installation happens, it is recommened to give a timeout
	options.setWdaLaunchTimeout(Duration.ofSeconds(20));
	
	// creating object of android driver
	// for this we set the url of the server and the capabilities
	// for sent the URL we make use of class URL
	driver=new IOSDriver(service.getUrl(), options);
	
	//creating and implicit wait
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//creating object of the home page for dont need to create it in the testcase
	homepage = new HomePage(driver);
	
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

	@AfterClass
public void TearDown() {
	driver.quit();
	// finishing the service appium execution
	service.close();
	
}
}



