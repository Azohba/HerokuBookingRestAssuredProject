package helpers;

import client.RestAssuredClient;
import config.ApiConstant;
import context.ContextStore;
import io.restassured.response.Response;
import models.request.CreateBookingNegativeRequest;
import models.request.CreateBookingRequest;
import models.request.GetTokenRequest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class BookingHelper extends RestAssuredClient {
    public BookingHelper() {
        super(ApiConstant.Booking.BASE_URL);
    }

    CommonHelper commonHelper = new CommonHelper();
    String endpoint = ContextStore.get("endpoint");
    String token = ContextStore.get("token");


    public Response getAuthToken(String username, String password) {
        commonHelper.logger.info("Authentication");

        GetTokenRequest getTokenRequest = GetTokenRequest.builder().username(username).password(password).build();
        return post(endpoint, null, null, getTokenRequest);
    }

    public Response createBooking(String firstname,
                                  String lastname,
                                  String totalprice,
                                  String depositpaid,
                                  String checkin,
                                  String checkout,
                                  String additionalneeds) throws ParseException {
        commonHelper.logger.info("Setting create booking req body ");
        commonHelper.compareDates(checkin, checkout);
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
        return post(endpoint, null, null, createBookingRequest);
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

        return post(endpoint, null, null, createBookingRequest);

    }

    public Response getBookingsID() {
        return get(endpoint, null, null, null);
    }

    public Response getBookingsWithParams(Map<String, Object> params) {
        return get(endpoint, params, null, null);
    }

    public Response getBookingsWithPathParam(String params) {
        return get(endpoint + "/" + params, null, null, null);
    }

    public Response partialUpdateBooking(Map<String, String> body, String bookingId) {
        Map<String, Object> tokenCookie = new HashMap<>();
        tokenCookie.put("Cookie", "token=" + token);
        return patch(endpoint + "/" + bookingId, null, tokenCookie, body);
    }

    public Response updateWithoutToken(Map<String, String> body, String bookingId) {
        return patch(endpoint + "/" + bookingId, null, null, body);
    }

    public Response deleteBooking(String bookingId){
        Map<String, Object> tokenCookie = new HashMap<>();
        tokenCookie.put("Cookie", "token=" + token);
        return delete(endpoint+"/" + bookingId, null, tokenCookie, null);
    }
}
