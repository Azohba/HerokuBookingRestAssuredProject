package steps;

import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;
import org.junit.Assert;

import java.util.Map;

public class BookingCreateSteps extends CommonHelper {
    BookingHelper bookingHelper = new BookingHelper();
    CreateBookingResponse createBookingResponse;

    @When("users create a new booking with the following details:")
    public void usersCreateANewBookingWithTheFollowingDetails(DataTable table) {
        Map<String, String> createBookingElements = table.asMaps().get(0);
        bookingHelper.createBooking(createBookingElements.get("firstname")
                , createBookingElements.get("lastname")
                , createBookingElements.get("totalprice")
                , createBookingElements.get("depositpaid")
                , createBookingElements.get("checkin")
                , createBookingElements.get("checkout")
                , createBookingElements.get("additionalneeds"));
    }

    @And("the response body should contain the following booking details:")
    public void theResponseBodyShouldContainTheFollowingBookingDetails(DataTable table) {
        Map<String, String> createBookingElements = table.asMaps().get(0);
        createBookingResponse = bookingHelper.createBooking(createBookingElements.get("firstname")
                , createBookingElements.get("lastname")
                , createBookingElements.get("totalprice")
                , createBookingElements.get("depositpaid")
                , createBookingElements.get("checkin")
                , createBookingElements.get("checkout")
                , createBookingElements.get("additionalneeds"));

        Assert.assertNotNull("BookingID is Null!!!", createBookingResponse.bookingid);
        Assert.assertEquals(createBookingElements.get("firstname"), createBookingResponse.booking.firstname);
        Assert.assertEquals(createBookingElements.get("lastname"), createBookingResponse.booking.lastname);
        Assert.assertEquals(createBookingElements.get("totalprice"), String.valueOf(createBookingResponse.booking.totalprice));
        Assert.assertEquals(createBookingElements.get("checkin"), createBookingResponse.booking.bookingdates.checkin);
        Assert.assertEquals(createBookingElements.get("checkout"), createBookingResponse.booking.bookingdates.checkout);
        Assert.assertEquals(createBookingElements.get("additionalneeds"), createBookingResponse.booking.additionalneeds);


    }


    @When("a user tries to create a booking with invalid data")
    public void aUserTriesToCreateABookingWithInvalidData(DataTable table) {
        logger.info("Trying to create booking with invalid data");
        Map<String, String> createBookingElements = table.asMaps().get(0);
        CommonHelper.response = bookingHelper.createBookingNegative(createBookingElements.get("firstname")
                , createBookingElements.get("lastname")
                , createBookingElements.get("totalprice")
                , createBookingElements.get("depositpaid")
                , createBookingElements.get("checkin")
                , createBookingElements.get("checkout")
                , createBookingElements.get("additionalneeds"));
    }


}
