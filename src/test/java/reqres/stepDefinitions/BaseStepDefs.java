package reqres.stepDefinitions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseStepDefs  {

    String endPoint = "";

    RequestSpecification reqSpec = given().accept(ContentType.JSON);

    static Response response;
}
