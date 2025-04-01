package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_AddStudentToClass {

	@Test
	public void addStudentToClass() {
		RestAssured.baseURI = APIConstants.BASE_URI;

		String payload = "{\r\n" + "  \"studentId\":" + APIGlobalVariables.studentId + ",\r\n" + "  \"classId\":"
				+ APIGlobalVariables.classId + "\r\n" + "}";

		RestAssured.given().auth().oauth2(APIGlobalVariables.token).body(payload).contentType(ContentType.JSON).when()
				.post(APIConstants.ADD_STUDENT_INTO_CLASS).prettyPeek().then().assertThat().statusCode(200)
				.body("result.action", Matchers.equalTo("Add student")).body("result.success", Matchers.is(true));

	}
}
