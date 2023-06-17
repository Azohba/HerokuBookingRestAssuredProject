package features.steps;

import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.en.Then;
import models.response.CreateBookingResponse;

public class DeleteBookingSteps {
    CommonHelper context;
    BookingHelper bookingHelper = new BookingHelper();
    CreateBookingResponse createBookingResponse = ContextStore.get("CreateBookingResponse");

    public DeleteBookingSteps(CommonHelper context){this.context=context;}
    @Then("delete booking with ID")
    public void deleteBookingWithID() {
        context.logger.info("Deleting created data with ID");
        bookingHelper.deleteBooking(String.valueOf(createBookingResponse.bookingid));
    }
}
