package models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CreateBookingRequest {
    String firstname;
    String lastname;
    Integer totalprice;
    Boolean depositpaid;
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



