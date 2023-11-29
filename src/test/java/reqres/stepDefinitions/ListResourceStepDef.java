package reqres.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.it.Ma;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ListResourceStepDef extends BaseStepDefs {
    @Then("All data with year should be listed")
    public void all_data_with_year_should_be_listed(List<Integer> years) {

        List<Map<Object, Object>> users = response.path("data");


        for (Map<Object, Object> each : users) {
            if (years.contains(each.get("year"))) {
                System.out.println("each = " + each);
            }
        }
    }

    @Then("all color codes starts with {string} and have {int} characters")
    public void all_color_codes_starts_with_and_have_characters(String chararacter, Integer num) {

        List<String> colors = response.path("data.color");

        for (String each : colors) {
            String eachColor = each;
            Assert.assertTrue(eachColor.startsWith(chararacter) && eachColor.length()== num);
        }
    }


    @Then("all the value of pantone_values in following format {string}")
    public void all_the_value_of_pantone_values_in_following_format(String string) {

        List<String> pantones = response.path("data.pantone_value");

        for (String each : pantones) {

      Assert.assertTrue(each.matches("[0-9]{2}-\\d{4}"));

        }
    }


    @Then("the year of second element of data should be {int}")
    public void the_year_of_second_element_of_data_should_be(Integer int1) {

        Integer actualNum = response.path("data[1].year");
        System.out.println("actualNum = " + actualNum);

        Assert.assertEquals(int1,actualNum);

    }


    @Then("the {string} of {int}. element of data should be {int}")
    public void the_of_element_of_data_should_be(String year, Integer index, Integer value) {

        Integer actual = response.path("data[" + (index - 1) + "]." + year);
        Assert.assertEquals(value,actual);
    }

    @Then("the value of text in support should be {string}")
    public void the_value_of_text_in_support_should_be(String expectText) {

        String actualText = response.path("support.text");
        Assert.assertEquals(expectText,actualText);


    }



}
