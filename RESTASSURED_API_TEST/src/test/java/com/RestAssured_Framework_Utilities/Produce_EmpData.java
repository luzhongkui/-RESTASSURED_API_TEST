package com.RestAssured_Framework_Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class Produce_EmpData 
{
	public static String empName() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("John"+generatedString);
	}
	
	public static String empSal() 
	{
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}
	
	public static String empAge() 
	{
		Boolean flag = true;	
		String generatedString="0";
		while  (flag )  
		{
			generatedString = RandomStringUtils.randomNumeric(2);
			if (Integer.parseInt(generatedString) <100) 
				flag = false;		 
		}
		return generatedString;
		
	}
}
