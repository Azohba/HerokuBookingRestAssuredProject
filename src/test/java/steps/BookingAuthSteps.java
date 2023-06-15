package steps;

import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class BookingAuthSteps extends CommonHelper{

    BookingHelper bookingHelper = new BookingHelper();
    @When("get auth token with following credentials:")
    public void getAuthTokenWithFollowing(DataTable table) {
        Map<String,String> loginCredentialsMap = table.asMap();
        bookingHelper.getAuthToken(loginCredentialsMap.get("username"), loginCredentialsMap.get("password"));

    }

    @And("the token value should not be null")
    public void checkTokenIsNotNull() {
        logger.info("Check token and set it");
        Assert.assertNotNull("Check token is created", response.jsonPath().getString("token"));
    }

    @When("send {} and {}")
    public void sendAnd(String username, String password) {
    bookingHelper.getAuthToken(username, password);
    }
}
