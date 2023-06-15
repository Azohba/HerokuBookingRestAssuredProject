package helpers;

import client.RestAssuredClient;
import config.ApiConstant;
import io.restassured.response.Response;
import models.request.CreateBookingNegativeRequest;
import models.request.CreateBookingRequest;
import models.request.GetTokenRequest;
import models.response.CreateBookingResponse;
import models.response.GetAuthResponse;

import java.util.Map;

import static config.ApiConstant.BookingEndPoints.AUTH;
import static config.ApiConstant.BookingEndPoints.BOOKING;


public class BookingHelper extends RestAssuredClient {
    public BookingHelper() {
        super(ApiConstant.Booking.BASE_URL);
    }

    public GetAuthResponse getAuthToken(String username, String password) {
        CommonHelper.logger.info("Authentication");
        GetTokenRequest getTokenRequest = GetTokenRequest.builder().username(username).password(password).build();
        return post(AUTH, null, null, getTokenRequest).body().as(GetAuthResponse.class);
    }


    public CreateBookingResponse createBooking(String firstname,
                                               String lastname,
                                               String totalprice,
                                               String depositpaid,
                                               String checkin,
                                               String checkout,
                                               String additionalneeds) {
        CommonHelper.logger.info("Setting create booking req body ");
        CreateBookingRequest.BookingDates bookingDates = new CreateBookingRequest.BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        CreateBookingRequest createBookingRequest = CreateBookingRequest.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(Integer.parseInt(totalprice))
                .depositpaid(Boolean.parseBoolean(depositpaid))
                .bookingdates(bookingDates)
                .additionalneeds(additionalneeds).build();
        return post(BOOKING, null, null, createBookingRequest).body().as(CreateBookingResponse.class);
    }

    public Response createBookingNegative(String firstname,
                                          String lastname,
                                          String totalprice,
                                          String depositpaid,
                                          String checkin,
                                          String checkout,
                                          String additionalneeds) {

        CreateBookingNegativeRequest.BookingDates bookingDates = new CreateBookingNegativeRequest.BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        CreateBookingNegativeRequest createBookingRequest = CreateBookingNegativeRequest.builder().firstname(firstname)
                .lastname(lastname)
                .totalprice(totalprice)
                .depositpaid(depositpaid)
                .bookingdates(bookingDates)
                .additionalneeds(additionalneeds).build();

        return post(BOOKING, null, null, createBookingRequest);

    }

    public Response getBookingsID() {
        return get(BOOKING, null, null, null);
    }

    public Response getBookingsWithParams(Map<String,Object> params){
        return get(BOOKING,params,null,null);
    }

    public Response getBookingsWithPathParam(String params){
        return get(BOOKING +"/"+params,null,null,null);
    }


}
