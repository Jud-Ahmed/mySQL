package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;

public class GET_ClassRoster {

	@Test
	public void getClassRoster()
	{
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		RestAssured.given()
			.auth().oauth2(APIGlobalVariables.token)
				.queryParam("classId", APIGlobalVariables.classId).when().get(APIConstants.GET_CLASS_ROSTER_ENDPOINT)
				.prettyPeek().then().assertThat().statusCode(200).body("result.studentCount", Matchers.equalTo(0))
				.body("result.students.size()", Matchers.is(0));

	}
}
