package com.RestAssured_Framework_Utilities;

 

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter 
             
{   
	public ExtentHtmlReporter htmlReporter ;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	
	{
		 
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Report/myReport.html") ;
		//htmlReporter = new ExtentHtmlReporter("C:/Users/luzhongk/eclipse-workspace/RESTASSURED_API_TEST/Test_output/myReport.html") ;
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest API Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports () ;
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment","QA" );
		extent.setSystemInfo("user", "pavan");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Testcase Passed is"+result.getName());
	}
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Testcase Failed is"+result.getName());
		test.log(Status.FAIL, "Testcase Failed is"+result.getThrowable());
	}
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Testcase Skipped is"+result.getName());
		
	}
	public void onFinish(ITestResult result)
	{
		extent.flush();
		
	}
}
