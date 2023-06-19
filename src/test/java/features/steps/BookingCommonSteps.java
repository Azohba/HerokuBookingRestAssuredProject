package features.steps;

import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;

public class BookingCommonSteps {

    private CommonHelper context;

    public BookingCommonSteps(CommonHelper context) {
        this.context = context;
    }

    @Then("the response status code should be {}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        context.logger.info("Check status code is " + statusCode + "");
        Assert.assertEquals("Response code is not " + statusCode + "", statusCode, String.valueOf(context.response.getStatusCode()));

    }


    @And("the response body should not be null")
    public void theResponseBodyShouldNotBeNull() {
        context.logger.info("Checking response body is not null");
        Assert.assertNotNull("Response body is null", context.response.body());
    }


    @And("the response body should match the schema {}")
    public void theResponseBodyShouldMatchTheSchema(String schemaName) {
        context.logger.info("Checking response schema is right");
        context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + schemaName));
    }

    @And("error message should be {}")
    public void errorMessageShouldBe(String errorMessage) {
        context.logger.info("Checking error message is true...");
        Assert.assertEquals(errorMessage, context.response.body().asString());

    }


}

