package features.steps;

import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;

public class BookingDeleteSteps {
    CommonHelper context;
    BookingHelper bookingHelper = new BookingHelper();
    CreateBookingResponse createBookingResponse = ContextStore.get("CreateBookingResponse");

    public BookingDeleteSteps(CommonHelper context) {
        this.context = context;
    }

    @Then("user deletes booking with ID")
    public void deleteBookingWithID() {
        context.logger.info("Deleting created data with ID");
        context.response = bookingHelper.deleteBooking(String.valueOf(createBookingResponse.bookingid));
    }

    @When("user tries to delete booking with invalid {}")
    public void userTriesToDeleteBookingInvalidToken(String params) {
        context.logger.info("Deleting created data without token");
        context.response = bookingHelper.deleteBookingInvalidToken(String.valueOf(createBookingResponse.bookingid), params);
    }


    @When("user send invalid {} path parameter")
    public void userTriesToDeleteBookingInvalidID(String params) {
        context.logger.info("Deleting created data without token");
        context.response = bookingHelper.deleteBookingInvalidToken(String.valueOf(createBookingResponse.bookingid), params);
    }

}
