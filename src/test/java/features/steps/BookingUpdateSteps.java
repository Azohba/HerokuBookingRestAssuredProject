package features.steps;

import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;
import models.response.BookingResponse;
import org.junit.Assert;

import java.util.Map;

public class BookingUpdateSteps {
    private CommonHelper context;
    BookingHelper bookingHelper = new BookingHelper();
    CreateBookingResponse createBookingResponse = ContextStore.get("CreateBookingResponse");

    public BookingUpdateSteps(CommonHelper context) {
        this.context = context;
    }

    @When("user update following fields:")
    public void userUpdateFollowingFields(DataTable table) {
        context.logger.info("Update partial fields");
        Map<String, String> updateElements = table.asMaps().get(0);
        context.response = bookingHelper.partialUpdateBooking(updateElements, String.valueOf(createBookingResponse.bookingid));
        BookingResponse bookingResponse = context.response.as(BookingResponse.class);
        ContextStore.put("UpdatedBookingResponse", bookingResponse);
    }


    @And("updated fields should be updated:")
    public void updatedFieldsShouldBeUpdated(DataTable table) {
        context.logger.info("Assert that updated fields are updated");
        BookingResponse bookingResponse = ContextStore.get("UpdatedBookingResponse");
        Map<String,String> updatedElements = table.asMaps().get(0);
        Assert.assertEquals(updatedElements.get("firstname"), bookingResponse.firstname);
        Assert.assertEquals(Integer.parseInt(updatedElements.get("totalprice")), bookingResponse.totalprice);

    }

    @When("user try to update following fields without token:")
    public void userTryToUpdateFollowingFieldsWithoutToken(DataTable table) {
        context.logger.info("Update partial fields");
        Map<String, String> updateElements = table.asMaps().get(0);
        context.response = bookingHelper.updateWithoutToken(updateElements, String.valueOf(createBookingResponse.bookingid));

    }
}
