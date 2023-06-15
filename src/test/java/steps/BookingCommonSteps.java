package steps;

import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;

public class BookingCommonSteps extends CommonHelper {

    @Then("the response status code should be {}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        logger.info("Check status code is "+statusCode+"");
        Assert.assertEquals("Response code is not "+statusCode +"", statusCode,String.valueOf(response.getStatusCode()));

    }


    @And("the response body should not be null")
    public void theResponseBodyShouldNotBeNull() {
        logger.info("Checking response body is not null");
        Assert.assertNotNull("Response body is null", response.body());
    }


    @And("error message should be {}")
    public void errorMessageShouldBe(String errorMessage) {
            logger.info("Checking on response messages");
            Assert.assertEquals("Your response message is not true",errorMessage,response.then().extract().response().body().asString());
        }


    @And("the response body should match the schema {string}")
    public void theResponseBodyShouldMatchTheSchema(String schemaName) {
        logger.info("Checking response schema is right");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+schemaName));
    }

}

