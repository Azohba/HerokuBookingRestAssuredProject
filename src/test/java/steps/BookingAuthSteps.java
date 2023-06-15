package steps;

import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.response.GetAuthResponse;
import org.junit.Assert;

import java.util.Map;

public class BookingAuthSteps {
    BookingHelper bookingHelper = new BookingHelper();
    private CommonHelper commonHelper;

    public BookingAuthSteps(CommonHelper commonHelper){this.commonHelper=commonHelper;}

    @When("get auth token with following credentials:")
    public void getAuthTokenWithFollowing(DataTable table) {
        Map<String,String> loginCredentialsMap = table.asMap();
        commonHelper.response=  bookingHelper.getAuthToken(loginCredentialsMap.get("username"), loginCredentialsMap.get("password"));
        commonHelper.response.body().as(GetAuthResponse.class);

    }

    @And("the token value should not be null")
    public void checkTokenIsNotNull() {
        CommonHelper.logger.info("Check token and set it");
        Assert.assertNotNull("Check token is created",commonHelper.response.jsonPath().getString("token"));
    }

    @When("send {} and {}")
    public void sendAnd(String username, String password) {
      commonHelper.response=bookingHelper.getAuthToken(username, password);
    }


    @And("error message should be {} for authentication")
    public void errorMessageShouldBe(String errorMessage) {
        CommonHelper.logger.info("Checking on response messages");
        Assert.assertEquals("Your response message is not true",errorMessage,commonHelper.response.jsonPath().getString("reason"));
               // then().extract().response().body().asString());
    }
}
