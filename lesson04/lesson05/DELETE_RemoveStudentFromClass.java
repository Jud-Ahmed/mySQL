package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;

public class DELETE_RemoveStudentFromClass {

	@Test
	public void removeStudentFromClass() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		RestAssured.given().auth().oauth2(APIGlobalVariables.token)
				.queryParam("StudentId", APIGlobalVariables.studentId).queryParam("ClassId", APIGlobalVariables.classId)
				.when().delete(APIConstants.REMOVE_STUDENT_FROM_CLASS).prettyPeek().then().assertThat().statusCode(200)
				.body("result.action", Matchers.equalTo("Remove student")).body("result.success", Matchers.is(true));
	}
}
