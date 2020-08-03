package com.RestAssuredFramework_Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDriveTest_Query {
	@Test
	void postNewEmployees()
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/employees";
		RequestSpecification httpRequest =RestAssured.given();
		
 
		
 
		Response response =httpRequest.request(Method.GET);
		
		//Validation Status
		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();
		System.out.println("Status Code is: " +statusCode );
		System.out.println("Status line is: " +statusLine );
		Assert.assertEquals(200, statusCode);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
		
		//Reponse body to perform validation
		String responseBody = response.getBody().asString();
		System.out.println("responseBody:" + responseBody);
		Assert.assertEquals(responseBody.contains("A1"), true);
		Assert.assertEquals(responseBody.contains("B1"), true);
		Assert.assertEquals(responseBody.contains("C1"), true);
		
		
		
	}
}
