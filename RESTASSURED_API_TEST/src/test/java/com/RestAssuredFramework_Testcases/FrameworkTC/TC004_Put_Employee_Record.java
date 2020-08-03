package com.RestAssuredFramework_Testcases.FrameworkTC;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured_Framework.baseline.TestBase;
import com.RestAssured_Framework_Utilities.Produce_EmpData;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase {
	String empName = Produce_EmpData.empName();
	String empSal = Produce_EmpData.empSal();
	String empAge = Produce_EmpData.empAge();
	
	@BeforeClass
	void getSingleEmployees() throws InterruptedException
	{
		logger.info("***************Started TC004_PUT_Employee_Record***************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		
		
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		String empID = jsonPathEvaluator.get("data[0].id");
		
		//System.out.println("empName: "+jsonPathEvaluator.get("data[0].employee_name"));
		//System.out.println(System.getProperty("user.dir"));
		JSONObject requestParams = new JSONObject();
		requestParams.put("employee_name", empName);
		requestParams.put("employee_salary", empSal);
		requestParams.put("employee_age", empAge);
		requestParams.put("profile_image", "");
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		 
		
		response = httpRequest.request(Method.PUT,"/update/"+empID);
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("***************Checking ReponseBody***************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body is ==>" +responseBody );
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSal),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
		 
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
		if (responseTime >5000)
			logger.warn("Response Time is longer than 2000"); 
		Assert.assertTrue(responseTime<5000);
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
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("***************Finished TC004_PUT_Employee_Record**************");
	}
	
}
