package models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateBookingNegativeRequest {
    String firstname;
    String lastname;
    String totalprice;
    String depositpaid;
    BookingDates bookingdates;
    String additionalneeds;

    @Data
    public static class BookingDates{
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String checkin;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private String checkout;
    }
}



