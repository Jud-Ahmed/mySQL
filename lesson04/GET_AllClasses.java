package com.neotech.api.lesson04;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GET_AllClasses {

	public static void main(String[] args) {

		//set the base URI
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		System.out.println(RestAssured.baseURI);
		System.out.println("-----------------------");
		
		//Building a request: method, base uri, endpoint, parameters {if any}
		RequestSpecification request = RestAssured.given();

		//any parameter, auth would need to be specified here
		
		
		//send the request to the endpoint and get the response back
		Response response = request.when().get("/api/services/app/Class/GetAll");
		
		System.out.println(response.statusCode());
		//System.out.println(response.getStatusCode());
		
		System.out.println("-----------------------");
		
		//Get headers
		System.out.println(response.headers());
		
		System.out.println("-----------------------");
		
		//we can create an object and work with it
	//	Headers headers = response.headers();
		
		//we can get a particular header
		String contentType = response.header("Content-Type");
		System.out.println(contentType);
		
		System.out.println("-----------------------");
		
		ResponseBody body = response.getBody();
		
		System.out.println(body.asString());
		System.out.println("-----------------------");
		
		System.out.println(body.asPrettyString());
		System.out.println("-----------------------");
		
		//we can also print the response directly
		response.prettyPrint();
		
		
		
	}

}
