package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import Utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import pageObjects.android.FormPage;

public class BaseTest extends AppiumUtils {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formpage;
	
//by setting always run we ensure that when we run test by groups this before and after class methods will run anyways because they are marked as always run
	@BeforeClass(alwaysRun=true)
	public void BaseConfiguration() throws IOException {
		
		//for set global properties it is recommended to use a properties file
		//this file will have all defautl properties like ipaddres, port, devicename
		//then using java class properties we will get those values for beint used in the configuration of our framewrok
		Properties prop = new Properties();
		//using the load method we will indicate the location of the properties file
		//because the input of the method load is inputstream, we will first convert the file into an input stream using FileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		//here we are using a ternary operation for check if we receive the ip addredd from maven or not
		// we are checking if the value from the property is different to null "!=null", if yes then use it "?", otherwise use the one from the property file ":"
		String iPAddress =System.getProperty("ipAddress") !=null ? System.getProperty("ipAddress") : prop.getProperty("ipAdress");

		String port=prop.getProperty("port");
		String AndroidDeviceName=prop.getProperty("AndroidDeviceName");
		
		//calling the method from the utils that starts appium server
		//because the port we get from the propertyfile is a string we will convert it to an int using integerparse  method
		service=startAppiumServer(iPAddress,Integer.parseInt(port));
	
	//creating object of uiautomator2 for provide the desired capabilities to the driver
	UiAutomator2Options options=new UiAutomator2Options();
	//setting the emulator, the emulator is the device emulator
	options.setDeviceName(AndroidDeviceName);
	//in order to be able to execute testing in web, we will need to indicate the location of the webdriver, in this case, chrome
	options.setChromedriverExecutable("C:\\Users\\ANDRES\\Documents\\Mobile automation\\chromedriver.exe");
		
	//setting the app
	//with this command we are installing the app autpmatically in the device
	// however also is possible to previous install the app using an adb command in the folder where the android platforms tools of the mobile are located
	//the command is "adb install C:\Users\ANDRES\Documents\Mobile automation\{here the app name}"
	options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");
	
	// creating object of android driver
	// for this we set the url of the server and the capabilities
	// for sent the URL we make use of class URL
	//for get the url and port we use a method from the service object service.getURL
	driver=new AndroidDriver(service.getUrl(), options);
	
	//creating and implicit wait
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//here we are creating object of the first page
	//formpage
	formpage=new FormPage(driver);

	}
	
//by setting always run we ensure that when we run test by groups this before and after class methods will run anyways because they are marked as always run
	@AfterClass(alwaysRun=true)
public void TearDown() {
	driver.quit();
	// finishing the service appium execution
	service.close();
	
}

}
