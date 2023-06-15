package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.util.Map;

public class BookingUpdateSteps {
    @When("user update following fields:")
    public void userUpdateFollowingFields(DataTable table) {
        Map<String,String> updateElements = table.asMap();

    }
}
