package reqres.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @BeforeAll

    public static void init(){

        RestAssured.baseURI="https://reqres.in";

    }

    @After
    public void teardownScenario(Scenario scenario) {

    }



}
