package com.neotech.api.lesson05;

import org.junit.Assert;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GET_OneStudent {

	@Test
	public void getOneStudent() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		Response response = RestAssured.given().auth().oauth2(APIGlobalVariables.token)
				.queryParam("Id", APIGlobalVariables.studentId).when()
				.get(APIConstants.GET_ONE_STUDENT_ENDPOINT).prettyPeek();

		System.out.println("--------------------");
		// verify that status code is 200
		response.then().assertThat().statusCode(200);

		// print the content type
		System.out.println(response.header("Content-type"));

		// get something from the body
		int id = response.body().jsonPath().getInt("result.id");
		System.out.println("id: " + id);

		// Verify that the id of the student in response is equal to the id in our
		// request
		Assert.assertEquals(APIGlobalVariables.studentId, id);

		// response.then().body("result.id", Matchers.equalTo(id));
	}
}
