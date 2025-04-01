package com.neotech.api.lesson05;

import org.junit.Assert;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PUT_UpdateStudent {

	@Test
	public void createStudent() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		String payload = "{\r\n" + "  \"id\": " + APIGlobalVariables.studentId + ",\r\n"
				+ "  \"firstName\": \"Joe\",\r\n" + "  \"lastName\": \"Biden\",\r\n"
				+ "  \"email\": \"jbiden@neotech.com\",\r\n" + "  \"city\": \"DC\",\r\n" + "  \"state\": \"NY\",\r\n"
				+ "  \"studentNumber\": \"010\"\r\n"
				+ "}";

		String payload2 = String.format("""
				{
				  "id": %s,
				  "firstName": "%s",
				  "lastName": "%s",
				  "email": "%s",
				  "city": "%s",
				  "state": "%s",
				  "studentNumber": "%s"
				}
				""", APIGlobalVariables.studentId, APIGlobalVariables.firstName, APIGlobalVariables.lastName,
				APIGlobalVariables.email, "Las Vegas", APIGlobalVariables.state, APIGlobalVariables.studentNumber);

		System.out.println(payload);
		
		Response response = RestAssured.given().auth().oauth2(APIGlobalVariables.token).body(payload2)
				.contentType(ContentType.JSON).when().put(APIConstants.UPDATE_STUDENT_ENDPOINT).prettyPeek();

		int id = response.body().jsonPath().getInt("result.id");

		// lets validate the status code
		response.then().assertThat().statusCode(200);

		// verify the id of the updated student is equal to what returns in response
		Assert.assertEquals(APIGlobalVariables.studentId, id);

		// negative testing
		// verify that the response does not include a specific value
		Assert.assertFalse(response.body().jsonPath().getString("result").contains(APIGlobalVariables.city));
		
	}
}
