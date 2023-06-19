package features.steps;

import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class BookingAuthSteps {
    BookingHelper bookingHelper = new BookingHelper();
    private CommonHelper context;

    public BookingAuthSteps(CommonHelper context) {
        this.context = context;
    }

    @And("the token value should not be null")
    public void checkTokenIsNotNull() {
        context.logger.info("Check token and set it");
        Assert.assertNotNull("Check token is created", ContextStore.get("token"));
    }

    @When("send {} and {}")
    public void sendAnd(String username, String password) {
        context.response = bookingHelper.getAuthToken(username, password);
    }


    @And("authentication errors should be {}")
    public void authenticationErrorsShouldBe(String errorMessage) {
        context.logger.info("Checking on response messages");
        Assert.assertEquals("Your response message is not true", errorMessage, context.response.jsonPath().getString("reason"));

    }

    @When("get auth token with following credentials {} & {}")
    public void getAuthTokenWithFollowingCredentials(String username, String password) {
        context.response = bookingHelper.getAuthToken(username, password);
        ContextStore.put("token", context.response.jsonPath().getString("token"));
    }
}
