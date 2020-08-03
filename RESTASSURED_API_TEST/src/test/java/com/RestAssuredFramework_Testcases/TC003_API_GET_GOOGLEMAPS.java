package com.RestAssuredFramework_Testcases;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_API_GET_GOOGLEMAPS{
	@Test
	void googleMapTest()
	{
		//Specify base URI
		RestAssured.baseURI ="https://maps.googleapis.com" ;
		//Request object
		RequestSpecification httpRequest = RestAssured.given();
		//Response object
		Response response = httpRequest.request(Method.GET );
		//Print Response message in Console window
		String responseBody = response.getBody().asString() ;
		System.out.println("Reponse Body is : " + responseBody );
		//Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is: " +statusCode );
		//Assert.assertArrayEquals(expecteds, actuals);
		
		
	}
}
