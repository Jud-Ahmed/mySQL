package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class POST_GenerateToken {
	
	//@Test
	public void generateToken()
	{
		//set the Base URI
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		
		//create a request 
		RequestSpecification request = RestAssured.given();
		
		//setting the request header content type to json
		request.header("Content-Type", "application/json");
		
		String payload = "{\r\n"
				+ "  \"userNameOrEmailAddress\": \"Tester\",\r\n"
				+ "  \"password\": \"Student@Neo\",\r\n"
				+ "  \"rememberClient\": true\r\n"
				+ "}";
		
		
		request.body(payload);
		
		//make the call
		Response response = request.when().post("/api/TokenAuth/Authenticate");
		
		//another way to sent a request
		//Response response = request.when().request(Method.POST, "/api/TokenAuth/Authenticate");
		
		System.out.println("--------------");
		System.out.println("Status code: " + response.statusCode());
		System.out.println("--------------");
		System.out.println(response.statusLine());
		
		response.prettyPrint();
		
		//assert
		response.then().assertThat().statusCode(200);
		Assert.assertEquals(200, response.statusCode());
		
	}
	
	@Test
	public void generateTokenShortWay()
	{
		//set the Base URI
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		
		String payload = "{\r\n"
				+ "  \"userNameOrEmailAddress\": \"Tester\",\r\n"
				+ "  \"password\": \"Student@Neo\",\r\n"
				+ "  \"rememberClient\": true\r\n"
				+ "}";
		
		
		
		RestAssured.given()
			.header("Content-Type", "application/json")
			.body(payload)
			.when().post("/api/TokenAuth/Authenticate")
		//	.prettyPrint() //-- this returns a String so it would break the chain
			.prettyPeek()
			.then().assertThat().statusCode(200);
	}
	
	
	
	
	

}
