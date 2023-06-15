package steps;

import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class BookingGetSteps extends CommonHelper {
    BookingHelper bookingHelper = new BookingHelper();

    @When("users get all bookings")
    public void usersGetAllBookings() {
        logger.info("Getting all booking IDs");
        response = bookingHelper.getBookingsID();
    }

    @And("the response body should contain ID field values")
    public void theResponseBodyShouldContainIDFieldValues() {
        logger.info("Checking ID fields");
        //I gave the condition 10 because there is lot of data to control
        for (int i = 0; i < 10; i++) {
            Assert.assertNotNull(response.jsonPath().getInt("[" + i + "].bookingid"));
        }

    }

    @When("users get booking details with ID")
    public void usersGetBookingDetailsWithID() {
        logger.info("Getting bookingID....");
        String bookingID = response.jsonPath().getString("[1].bookingid");
        Assert.assertNotNull("BookingId not found", bookingID);
        response = bookingHelper.getBookingsWithPathParam(bookingID);
        Assert.assertNotNull("firstname not found", response.jsonPath().getString("firstname"));
    }

    @When("users try to get booking with {string} and {string} parameters")
    public void usersTryToGetBookingWithAndParameters(String key, String value) {
        logger.info("Getting book detail with query params");
        Map<String,Object> mapping = new HashMap<>();
        mapping.put(key,value);
        response = bookingHelper.getBookingsWithParams(mapping);
    }

    @When("users try to get booking with specific parameters")
    public void usersTryToGetBookingWithSpecificParameters() {
        CreateBookingResponse createBookingResponse= new CreateBookingResponse();
        response = bookingHelper.getBookingsWithParams();
    }

    @When("users try to get booking with specific {} parameters")
    public void usersTryToGetBookingWithSpecificParameters(String key) {
        CreateBookingResponse createBookingResponse= new CreateBookingResponse();
        Map<String,Object> mapping = new HashMap<>();
        mapping.put(key,createBookingResponse.booking.);

    }
}
