package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ResponseVerification {

	@Test
	public void verifyAllClassesRequest()
	{
		//set the base uri
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		
		RequestSpecification request = RestAssured.given();
		
		//any element of the request can be specified during the request specification, before we make the call
		request.queryParam("MaxResultCount", 100);
		
		//make the call 
		Response response = request.when().get("/api/services/app/Class/GetAll");
		
		//response validation 
		
		//Validate that the status code is 200
		response.then().assertThat().statusCode(200);
		
		//Verify status code using JUnit Assertion
		Assert.assertEquals(200, response.statusCode());
		
		//Verify the Content-Type is application/json...
		response.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
		
		//Verify using JUnit Assertion 
		Assert.assertEquals("application/json; charset=utf-8", response.header("Content-Type"));
		
		
		//Validate the response body contains Jenkins
		String body = response.body().asString();
		Assert.assertTrue("Jenkins not Found!!!", body.contains("Jenkins"));
		
		
		//using a one liner
		Assert.assertTrue(response.body().asString().contains("Jenkins"));
		
	}
}
