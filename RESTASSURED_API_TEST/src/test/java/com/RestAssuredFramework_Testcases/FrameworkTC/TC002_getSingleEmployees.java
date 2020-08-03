package com.RestAssuredFramework_Testcases.FrameworkTC;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured_Framework.baseline.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_getSingleEmployees extends TestBase {
	@BeforeClass
	void getSingleEmployees() throws InterruptedException
	{
		logger.info("***************Started TC002_GetSingle_Employee_Record***************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee/"+empID);
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("***************Checking ReponseBody***************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body is ==>" +responseBody );
		Assert.assertEquals(responseBody.contains(empID),true);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("***************Checking checkStatusCode***************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code is ==>" +statusCode );
		Assert.assertEquals(200, statusCode);
	}
	@Test
	void checkResponseTime()
	{
		logger.info("***************Checking Response Time***************");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" +responseTime );
		if (responseTime >3000)
			logger.warn("Response Time is longer than 2000"); 
		Assert.assertTrue(responseTime<3000);
	}
	@Test
	void checkStatusLine()
	{
		logger.info("***************Checking Status Line***************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" +statusLine );
 
		Assert.assertEquals("HTTP/1.1 200 OK",statusLine);
	}
	@Test
	void checkContentType() 
	{
		logger.info("***************Checking Content Type***************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==>" +contentType ); 
		Assert.assertEquals("application/json",contentType);
	}
	@Test
	void checkServerType()
	{
		logger.info("***************Checking Server Type***************");
		String serverType = response.header("Server");
		logger.info("Server Type is ==>" +serverType ); 
		Assert.assertEquals("nginx/1.16.0",serverType);
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("***************Checking Content Encoding***************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" +contentEncoding ); 
		Assert.assertEquals(null,contentEncoding);
	}
	
	@Test
	void checkContentLength()
	{
		logger.info("***************Checking Content Length***************");
		String contentlength = response.header("Content-Length");
		if (Integer.parseInt(contentlength) <100)
			logger.warn("Content length is less than 100" ); 
		Assert.assertTrue(Integer.parseInt(contentlength)>100);
	}
	@AfterClass
	void tearDown()
	{
		logger.info("***************Finished TC002_GetSingle_Employee_Record**************");
	}
	
}
