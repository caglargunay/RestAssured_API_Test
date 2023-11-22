package reqres.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import reqres.stepDefinitions.BaseStepDefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListUsersStepDefs extends BaseStepDefs {
    @Given("I use this path {string}")
    public void i_use_this_path(String path) {

        endPoint += "/" + path;

    }

    @Given("I use this query {string} {string}")
    public void i_use_this_query(String keyword, String value) {

        reqSpec = given().spec(reqSpec)
                .queryParam(keyword, value);

    }

    @When("I use get method")
    public void i_use_get_method() {

        response = given().spec(reqSpec).when().get(endPoint);

    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCodeValue) {
        response.then().statusCode(statusCodeValue);
    }

    @Then("{string} url should be working")
    public void url_should_be_working(String path) {
       given().spec(reqSpec).get(response.path(path).toString()).then().statusCode(200);
    }

    @Then("response headers {string} should have this value {string}")
    public void response_headers_should_have_this_value(String header, String value) {
        response.then().header(header,value);
    }

    @Then("request headers {string} should be {string}")
    public void request_headers_should_be(String headerName, String value) {
        given().spec(reqSpec).request().header(headerName, value);
    }

    @Then("check response time less than {int} ms")
    public void check_response_time_less_than_ms(Integer responseTime) {

        assertThat(response.getTime(), lessThan((long) (responseTime)));
    }

    @Then("verify the value of {string} element from response is {int}")
    public void verify_the_value_of_element_from_response_is(String key, Integer value) {
        response.then().body(key, is(value));
    }

    @Then("verify the value of {string} element from response is {string}")
    public void verify_the_value_of_element_from_response_is(String key, String value) {
        response.then().body(key, is(value));
    }


    @Then("print each element of {string} array from response")
    public void print_each_element_of_array_from_response(String dataOfList) {

        List<Object> datas = response.path(dataOfList);
        for (Object eachData : datas) {
            System.out.println(eachData);
        }
    }

    @Then("verify if {string} under {string} element from response is working")
    public void verify_if_under_element_from_response_is_working(String key1, String key2) {
        String url = response.path(key2 + "." + key1);
        given().get(url).then().statusCode(200);
    }

    @Then("print each {string} of {string} list from response")
    public void print_each_of_list_from_response(String key1, String key2) {
        List<Object> elements = response.path(key2 + "." + key1);

        for (Object eachElement : elements) {
            System.out.println(eachElement);
        }

    }

    @Then("print all usernames whose {string} of {string} are odd")
    public void print_all_usernames_whose_of_are_odd(String key1, String key2) {

        List<Map<Object, Object>> elements = response.path(key2);

//        elements.stream()
//                .filter(i->((int)i.get(key1)%2 !=0))
//                .forEach(System.out::println);

        for (Map<Object, Object> each : elements) {
            if((Integer) each.get(key1) %2 !=0) System.out.println(each);
        }
    }

    @Then("check {string} contains user's name {string} under {string} from response")
    public void check_contains_user_s_name_under_from_response(String key1, String key2, String key3) {

        List<Map<Object, Object>> elements = response.path(key3);

        for (Map<Object, Object> eachElement : elements) {

            //System.out.println(eachElement.get(key1).toString());
           // System.out.println(eachElement.get(key2).toString());

            Assert.assertTrue(eachElement.get(key1).toString().contains(eachElement.get(key2).toString().toLowerCase()));
        }
    }
    @Then("check {string} equals to {string} and {string} equals to {string} inside {string}")
    public void check_equals_to_and_equals_to_inside(String key1, String key2, String key3, String key4, String key5) {

        List<Map<Object, Object>> elements = response.path(key5);

        for (Map<Object, Object> each : elements) {

            System.out.println(each.get(key1)+" apidan ID");
            System.out.println(each.get(key3)+" apidanisim");
         //   Assert.assertEquals(each.get(key1).toString(),key2);
          //  Assert.assertEquals(each.get(key3).toString(),key4);


        }



    }


}
