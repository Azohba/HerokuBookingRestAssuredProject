package features.steps;

import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingGetSteps {
    BookingHelper bookingHelper = new BookingHelper();
    private CommonHelper context;

    public BookingGetSteps(CommonHelper context) {
        this.context = context;
    }

    @When("users get all bookings")
    public void usersGetAllBookings() {
        context.logger.info("Getting all booking IDs");
        context.response = bookingHelper.getBookingsID();
    }

    @And("the response body should contain ID field values")
    public void theResponseBodyShouldContainIDFieldValues() {
        context.logger.info("Checking ID fields");
        int i = 0;
        while (i < context.response.jsonPath().getList(".").size()) {
            Assert.assertNotNull(context.response.jsonPath().getInt("[" + i + "].bookingid"));
            i++;
            if (i == 10) break;
        }
        context.logger.info("Checked ID field " + i + " times");

    }

    @When("users get booking details with ID")
    public void usersGetBookingDetailsWithID() {
        context.logger.info("Getting bookingID....");
        context.response.jsonPath().getString("[1].bookingid");
        Assert.assertNotNull("BookingId not found", bookingID);
        context.response = bookingHelper.getBookingsWithPathParam(bookingID);
        Assert.assertNotNull("firstname not found", context.response.jsonPath().getString("firstname"));
    }

    @When("users try to get booking with {string} and {string} parameters")
    public void usersTryToGetBookingWithAndParameters(String key, String value) {
        context.logger.info("Getting book detail with query params");
        Map<String, Object> mapping = new HashMap<>();
        mapping.put(key, value);
        context.response = bookingHelper.getBookingsWithParams(mapping);
    }

    @And("the response body should contain ID that belongs to created booking")
    public void theResponseBodyShouldContainIDThatBelongsToCreatedBooking() throws IOException {
        context.logger.info("Checking booking ID is the same with the getting one");
        CreateBookingResponse createBookingResponse = ContextStore.get("CreateBookingResponse");
        ArrayList<Integer> responseBookingID = context.response.jsonPath().get("bookingid");
        if (responseBookingID.size() > 1) {
            int createdId = 0;
            for (int i = 0; i < responseBookingID.size(); i++) {
                createdId = responseBookingID.get(i);
                if (createdId == createBookingResponse.bookingid)
                    break;
            }
            Assert.assertEquals(createBookingResponse.bookingid, createdId);
        } else {
            Assert.assertEquals(String.valueOf(createBookingResponse.bookingid), responseBookingID.get(0).toString());
        }


    }
}
