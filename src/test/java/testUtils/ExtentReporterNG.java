package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	//by marking a method as static i can access it without create object
	//creating a method for the configuration of the testreports
	public static ExtentReports getReporterObject() {
		
		//the extentsparkreporter is responsible to create the html file with the report and do the configurations
		//creating object for extendsparkreporter class, where the input is the path where the report will be created
		//for set the path we will create and string with the path where index.htm will be the report file
		//and by using system.getproperty("user.dir") we are always indicating the project path
		String path= System.getProperty("user.dir")+"\\Reports\\index.htm";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//indicating the reports name
		reporter.config().setReportName("Web Automation Results");
		
		//setting the documment tittle
		reporter.config().setDocumentTitle("Test Results");
		
		//extents reports is the class responsible to drive all the reports execution
		//creating object of extentsreport class
		extent = new ExtentReports();
		
		//we need to attach the report execution to the report we wanna to create, by this we use attach method
		extent.attachReporter(reporter);
		
		//indicating the tester name
		extent.setSystemInfo("Tester", "Andres");
		
		//returnning the extent object for be used in the testcases
		return extent;
	}

}
