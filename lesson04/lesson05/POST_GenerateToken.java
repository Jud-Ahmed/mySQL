package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;

import io.restassured.RestAssured;


public class POST_GenerateToken {
	
	@Test
	public void generateTokenShortWay()
	{
		//set the Base URI
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		String payload = "{\r\n"
				+ "  \"userNameOrEmailAddress\": \"Tester\",\r\n"
				+ "  \"password\": \"Student@Neo\",\r\n"
				+ "  \"rememberClient\": true\r\n"
				+ "}";
		
		
		RestAssured.given()
			.header("Content-Type", "application/json")
			.body(payload)
				.when().post(APIConstants.GENERATE_TOKEN_ENDPOINT)
		//	.prettyPrint() //-- this returns a String so it would break the chain
			.prettyPeek()
			.then().assertThat().statusCode(200);
	}
	
	
	
	
	

}
