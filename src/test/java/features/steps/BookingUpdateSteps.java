package features.steps;

import com.google.gson.JsonObject;
import context.ContextStore;
import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import models.response.CreateBookingResponse;
import models.response.BookingResponse;
import org.junit.Assert;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingUpdateSteps {
    private CommonHelper context;
    BookingHelper bookingHelper = new BookingHelper();
    CreateBookingResponse createBookingResponse = ContextStore.get("CreateBookingResponse");
    private Map<String, String> updatedData = new HashMap<>();

    public BookingUpdateSteps(CommonHelper context) {
        this.context = context;
    }

    @When("user updates the following fields:")
    public void userUpdateFollowingFields(DataTable table) {
        context.logger.info("Update partial fields");
        List<List<String>> dataList = table.asLists(String.class);
        context.response = bookingHelper.partialUpdateBooking(dataList,String.valueOf(createBookingResponse.bookingid));
        BookingResponse bookingResponse = context.response.as(BookingResponse.class);
        ContextStore.put("UpdatedBookingResponse", bookingResponse);
    }


    @And("updated fields should be changed")
    public void updatedFieldsShouldBeUpdated(DataTable table) {
        context.logger.info("Assert that updated fields are updated");
        List<List<String>> dataList = table.asLists(String.class);
        BookingResponse bookingResponse = ContextStore.get("UpdatedBookingResponse");
        Assert.assertEquals(dataList.get(0).get(1), bookingResponse.firstname);
        Assert.assertEquals(dataList.get(1).get(1),String.valueOf(bookingResponse.totalprice) );

    }

    @When("user tries to update the following fields without a token:")
    public void userTryToUpdateFollowingFieldsWithoutToken(DataTable table) {
        context.logger.info("Update partial fields");
        List<List<String>> dataList = table.asLists(String.class);
        context.response = bookingHelper.updateWithoutToken(dataList, String.valueOf(createBookingResponse.bookingid));

    }
}
