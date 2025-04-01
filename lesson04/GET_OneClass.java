package com.neotech.api.lesson04;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//we can import all Matchers 
//import static org.hamcrest.Matchers.*;
//or you can import the static methods you need
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;



public class GET_OneClass {

	@Test
	public void getOneClassTest()
	{
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		
		RequestSpecification request = RestAssured.given();
		
		//setting a path parameter
		request.pathParam("Id", 461);
		
		//make the call
		Response response = request.when().get("/api/services/app/Class/Get/{Id}");
		//can I make this call with the path param on it? YES
		//	request.when().get("/api/services/app/Class/Get/2");
		
		System.out.println("Status line: " + response.getStatusLine());
		
		//verify the status code
		response.then().assertThat().statusCode(200);
		System.out.println("--------------");
		response.prettyPeek();
		
		
		//Assert using Hamcrest Library
		response.then().assertThat().body("result.name", equalTo("Football 101"));
		response.then().assertThat().body("result.description", containsString("Learn"));
		response.then().assertThat().body("result.instructorEmail", endsWith("@gmail.com"));
		
		
	}
	
	
	
}
