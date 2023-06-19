package features.steps;

import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;
import org.junit.Assert;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class BookingCreateSteps {
    BookingHelper bookingHelper = new BookingHelper();
    private CommonHelper context;
    private Map<String, String> createdBookingInputData = new HashMap<>();

    public BookingCreateSteps(CommonHelper context) {
        this.context = context;
    }

    @When("the user creates a new booking with the following details:")
    public void usersCreateANewBookingWithTheFollowingDetails(DataTable table) throws ParseException {
        Map<String, String> createBookingElements = table.asMaps().get(0);
        ContextStore.put("createBookingElements", createBookingElements);
        context.response = bookingHelper.createBooking(createBookingElements.get("firstname")
                , createBookingElements.get("lastname")
                , createBookingElements.get("totalprice")
                , createBookingElements.get("depositpaid")
                , createBookingElements.get("checkin")
                , createBookingElements.get("checkout")
                , createBookingElements.get("additionalneeds"));
        CreateBookingResponse createBookingResponse = context.response.body().as(CreateBookingResponse.class);
        ContextStore.put("CreateBookingResponse", createBookingResponse);
        createdBookingInputData.put("firstname", createBookingElements.get("firstname"));
        createdBookingInputData.put("lastname", createBookingElements.get("lastname"));
        createdBookingInputData.put("totalprice", createBookingElements.get("totalprice"));
        createdBookingInputData.put("depositpaid", createBookingElements.get("depositpaid"));
        createdBookingInputData.put("checkin", createBookingElements.get("checkin"));
        createdBookingInputData.put("checkout", createBookingElements.get("checkout"));
        createdBookingInputData.put("additionalneeds", createBookingElements.get("additionalneeds"));
        context.createdBookingIds.add(createBookingResponse.bookingid);

    }

    @And("the response body should contain created booking details")
    public void theResponseBodyShouldContainTheFollowingBookingDetails() {
        CreateBookingResponse createBookingResponse = ContextStore.get("CreateBookingResponse");
        Map<String, String> createdBookingInputData = ContextStore.get("createBookingElements");
        Assert.assertNotNull("BookingID is Null!!!", createBookingResponse.bookingid);
        Assert.assertEquals(createdBookingInputData.get("firstname"), createBookingResponse.booking.firstname);
        Assert.assertEquals(createdBookingInputData.get("lastname"), createBookingResponse.booking.lastname);
        Assert.assertEquals(createdBookingInputData.get("totalprice"), String.valueOf(createBookingResponse.booking.totalprice));
        Assert.assertEquals(createdBookingInputData.get("checkin"), createBookingResponse.booking.bookingdates.checkin);
        Assert.assertEquals(createdBookingInputData.get("checkout"), createBookingResponse.booking.bookingdates.checkout);
        Assert.assertEquals(createdBookingInputData.get("additionalneeds"), createBookingResponse.booking.additionalneeds);


    }


    @When("a user tries to create a booking with invalid data")
    public void aUserTriesToCreateABookingWithInvalidData(DataTable table) {
        context.logger.info("Trying to create booking with invalid data");
        Map<String, String> createBookingElements = table.asMaps().get(0);
        context.response = bookingHelper.createBookingNegative(createBookingElements.get("firstname")
                , createBookingElements.get("lastname")
                , createBookingElements.get("totalprice")
                , createBookingElements.get("depositpaid")
                , createBookingElements.get("checkin")
                , createBookingElements.get("checkout")
                , createBookingElements.get("additionalneeds"));
    }


}
