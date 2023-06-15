package steps;

import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;

public class BookingCommonSteps {

    private CommonHelper commonHelper;

    public BookingCommonSteps(CommonHelper commonHelper) {
        this.commonHelper = commonHelper;
    }

    @Then("the response status code should be {}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        CommonHelper.logger.info("Check status code is " + statusCode + "");
        Assert.assertEquals("Response code is not " + statusCode + "", statusCode, String.valueOf(commonHelper.response.getStatusCode()));

    }


    @And("the response body should not be null")
    public void theResponseBodyShouldNotBeNull() {
        CommonHelper.logger.info("Checking response body is not null");
        Assert.assertNotNull("Response body is null", commonHelper.response.body());
    }


    @And("the response body should match the schema {}")
    public void theResponseBodyShouldMatchTheSchema(String schemaName) {
        CommonHelper.logger.info("Checking response schema is right");
        commonHelper.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + schemaName));
    }

    @And("error message should be {}")
    public void errorMessageShouldBe(String errorMessage) {
        CommonHelper.logger.info("Checking error message is true...");
        Assert.assertEquals(errorMessage, commonHelper.response.body().asString());

    }
}

