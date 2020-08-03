package com.RestAssuredFramework_Testcases;
import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_API_GET_REQUEST {
	@Test
	void getweatherDetails()
	{
		//Specify base URI
		RestAssured.baseURI ="http://api.weatherapi.com" ;
		//Request object
		RequestSpecification httpRequest = RestAssured.given();
		//Response object
		Response response = httpRequest.request(Method.GET,"/v1/current.json?key=a7fe4194e895440db18224538202807&q=Amsterdam" );
		//Print Response message in Console window
		String responseBody = response.getBody().asString() ;
		System.out.println("Reponse Body is : " + responseBody );
		//Status code validation
		int statusCode = response.getStatusCode();
		String statusLine = response.getStatusLine();
		System.out.println("Status Code is: " +statusCode );
		System.out.println("Status line is: " +statusLine );
		Assert.assertEquals(200, statusCode);
		//Verify the headers
	    String contentType = response.header("Content-Type");
	    System.out.println("Content-Type: " +contentType );
	    String Connection = response.getHeader("Connection");
	    System.out.println("Connection: " +Connection );
	   // get all items of headers
	   Headers allheaders=response.headers();
	   for (Header header:allheaders)
	   {
		   System.out.println(header.getName()+":"+header.getValue());
	   }
	   
	   Assert.assertEquals(responseBody.contains("Amsterdam"), true);
	   JsonPath jsonpath =response.jsonPath();
	   String location=jsonpath.get("location")  .toString();
	   System.out.println("location: " +location);
	  //JsonPath locationPath=jsonpath.get("location");
	   //System.out.println("Name: " +locationPath.get("name").toString());
	   
	 //
	  //PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme() ;
	   //SwaggerAPIKeyScheme authscheme = new PreemptiveAPIKeyScheme() ;
	//Data Driven testcase
	  
	  
	  
	}
}
