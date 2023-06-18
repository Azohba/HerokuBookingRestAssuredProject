package features.steps;

import helpers.BookingHelper;
import helpers.CommonHelper;
import io.cucumber.java.After;


public class HooksSteps {
    private CommonHelper context;

    public HooksSteps(CommonHelper context) {
        this.context = context;
    }

    @After
    public void deleteCreatedBookings() {
        BookingHelper bookingHelper = new BookingHelper();
        for (int i = 0; i < context.createdBookingIds.size(); i++) {
            bookingHelper.deleteBooking(String.valueOf(context.createdBookingIds.get(i)));
            context.logger.info(context.createdBookingIds.get(i) + "deleted");
        }
        context.logger.info("All bookings are deleted" + context.createdBookingIds );
    }
}
