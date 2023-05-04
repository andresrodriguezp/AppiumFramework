package testUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;

//here we are implementing Itestlistener interface for use testng listeners
public class Listeners extends AppiumUtils implements ITestListener {
	
	ExtentTest test;
	AppiumDriver driver;
	
	//creating object fo extent reporter class
	//because method for create report configuration is static, no object of the class is nedded for acces it
	ExtentReports extent= ExtentReporterNG.getReporterObject();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		//initiating the test report generation
		//for giving the testname we will use the result object who is attached to everytestexecution
		test= extent.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//marking test as passed and creating a log
		test.log(Status.PASS, "test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//marking test as failed in the report and getting the error message
		test.fail(result.getThrowable());
		//here we will get the current testcase driver, for use in the screen chapture
		//for this we get the realclass that is executing the field driver and get his driver current instance
		try {
			driver=(AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		//here we are calling the methid that takes the screenshot and implementing it in the testng report with the addscreenchapturefrompath method
		//this method expected the screenshot path that we are takinf from the screenshot method from appiumutils package that we created
		//and also the screenshto tittle that we are getting from the testcase name
		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//indicating the testexecution is finished and report can be created
		extent.flush();
		
	}

}
