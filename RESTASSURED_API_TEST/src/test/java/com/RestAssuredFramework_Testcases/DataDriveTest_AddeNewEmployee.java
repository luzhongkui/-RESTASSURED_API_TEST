package com.RestAssuredFramework_Testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestAssured_Framework_Utilities.XLUtilis;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class DataDriveTest_AddeNewEmployee {
	@Test(dataProvider ="empdataProvider" )
	void postNewEmployees(String ename,String esal,String eage)
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest =RestAssured.given();
		
		JSONObject requestParams = new JSONObject() ;		
		requestParams.put("employee_name", ename);
		requestParams.put("employee_salary", esal);
		requestParams.put("employee_age", eage);		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response =httpRequest.request(Method.POST,"/create");
		
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
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(eage), true);
	}		
	@DataProvider(name="empdataProvider")
		
	String [][] getEmpData() throws Exception
		{
		String path = "C:/Users/luzhongk/eclipse-workspace/RESTASSURED-API-TEST/src/test/java/DATA_DRIVEN_TEST/empdata.xlsx";
		int rownum = XLUtilis.getRowCount(path, "empdata");
		int colcount=XLUtilis.getCellCount(path,"empdata",1);
		String empdata[][]= new String[rownum][colcount];
		for (int i=1; i<= rownum; i++) 
			{
				for (int j =0; j<colcount; j++)
				{
					empdata[i-1][j]= XLUtilis.getCellData(path,"empdata",i,j);
				}
			}
			return empdata ;
		}

}
