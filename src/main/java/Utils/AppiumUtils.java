package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	
	public double getFormattedAmmount(String ammount) {
		//here we will need to remove the sing $ from the price in order to sum it
		// for this we will use a java method for start from the 1 position of the string
		//where the ammount is "$160.67" and $ is the position 0 and the ammount start from 1 position
		//then we convert the string to a double in order to be able to sum them
		double price= Double.parseDouble(ammount.substring(1));
		return price;
	}
	
	public void waitForAttributeToContains(WebElement ele, String attribute, String value, AppiumDriver driver ) {
		
		//using implicit wait for wait a page finish to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//here we are waiting until an element be clickiable in order to ensure the page loaded correclty 
		wait.until(ExpectedConditions.attributeContains(ele, attribute, value));
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String jsonFilePath) throws IOException
	{
		
		//here we are importing a json file and converting it to string using method readfiletostring
		//the method expect to arguments, the file and the platfrom encoding default
		//we are creating a new file with the location of the file
		//by using systwm.getproperty(user.dir), eclipse by default situate in the working project and then we add the missing file to the json file
		// and by platfrom we set yft_8
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);
		
		// now we are going to convert the string to hashmap
		// first we create an object to the class object mapper, this class allows us to convert from json to javaobjects
		//we will read the json converted file previously and converte it into list of hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
	}
	
	//here we are passing as an input ip address and port where to run the appium server
	public AppiumDriverLocalService startAppiumServer(String IPAdress, int Port) {
		
		AppiumServiceBuilder builder = new AppiumServiceBuilder ();
	    builder.withIPAddress (IPAdress)
	            .usingPort (Port)
	            .withAppiumJS (
	                    new File ("C:\\Users\\ANDRES\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
	            .usingDriverExecutable (new File ("C:\\Program Files\\nodejs\\node.exe"));
	    

	    service = AppiumDriverLocalService.buildService (builder);
	  //here we are starting our appium service
	    service.start();
	    return service;
	}
	
	//method for take screenshot
	public String getScreenshotPath(String testCaseName,AppiumDriver driver) throws IOException {
	
	//for take a screenshot we make use of screenshot method, where we indicate which output type we want, in this case we wanna a file 
	File source=driver.getScreenshotAs(OutputType.FILE);
	//here we are going to copy the screenshot created a new file with it in a new location 
	String destinationFile =System.getProperty("user.dir")+"\\Reports"+testCaseName+".png";
	FileUtils.copyFile(source, new File(destinationFile));
	//here we are returning the path of the new locationof the screenshot for use it in the reports
	return destinationFile;
		
	}
	

}
