package com.neotech.api.lesson05;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;

public class GET_AllStudents {

	@Test
	public void getAllStudents() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		// BDD: given() --> when() --> then() --> and then()

		RestAssured.given()
				// .header("Authorization", "Bearer " + APIGlobalVariables.token)
				.auth().oauth2(APIGlobalVariables.token).when().get(APIConstants.GET_ALL_STUDENTS_ENDPOINT).prettyPeek()
				.then().assertThat().statusCode(200).and().body("result.totalCount", equalTo(74)).and()
				.body("result.items.size()", is(10));

	}
}
