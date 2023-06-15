package steps;

import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingGetSteps {
    BookingHelper bookingHelper = new BookingHelper();
    private CommonHelper commonHelper;

    public BookingGetSteps(CommonHelper commonHelper) {
        this.commonHelper = commonHelper;
    }

    @When("users get all bookings")
    public void usersGetAllBookings() {
        CommonHelper.logger.info("Getting all booking IDs");
        commonHelper.response = bookingHelper.getBookingsID();
    }

    @And("the response body should contain ID field values")
    public void theResponseBodyShouldContainIDFieldValues() {
        CommonHelper.logger.info("Checking ID fields");
        int i = 0;
        while (i < commonHelper.response.jsonPath().getList(".").size()) {
            Assert.assertNotNull(commonHelper.response.jsonPath().getInt("[" + i + "].bookingid"));
            i++;
            if (i == 10) break;
        }
        CommonHelper.logger.info("Checked ID field " + i + " times");

    }

    @When("users get booking details with ID")
    public void usersGetBookingDetailsWithID() {
        CommonHelper.logger.info("Getting bookingID....");
        String bookingID = commonHelper.response.jsonPath().getString("[1].bookingid");
        Assert.assertNotNull("BookingId not found", bookingID);
        commonHelper.response = bookingHelper.getBookingsWithPathParam(bookingID);
        Assert.assertNotNull("firstname not found", commonHelper.response.jsonPath().getString("firstname"));
    }

    @When("users try to get booking with {string} and {string} parameters")
    public void usersTryToGetBookingWithAndParameters(String key, String value) {
        CommonHelper.logger.info("Getting book detail with query params");
        Map<String, Object> mapping = new HashMap<>();
        mapping.put(key, value);
        commonHelper.response = bookingHelper.getBookingsWithParams(mapping);
    }

    @And("the response body should contain ID that belongs to created booking")
    public void theResponseBodyShouldContainIDThatBelongsToCreatedBooking() throws IOException {
        CommonHelper.logger.info("Checking booking ID is the same with the getting one");
        ArrayList<Integer> responseBookingID = commonHelper.response.jsonPath().get("bookingid");
        if (responseBookingID.size() > 1) {
            int createdId = 0;
            for (int i = 0; i < responseBookingID.size(); i++) {
                createdId = responseBookingID.get(i);
                if (createdId == BookingCreateSteps.createBookingResponse.bookingid)
                    break;
            }
            Assert.assertEquals(BookingCreateSteps.createBookingResponse.bookingid, createdId);
        } else {
            Assert.assertEquals(String.valueOf(BookingCreateSteps.createBookingResponse.bookingid), responseBookingID.get(0).toString());
        }


    }
}
