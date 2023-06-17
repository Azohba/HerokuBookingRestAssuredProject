package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse {

	@JsonProperty("firstname")
	public String firstname;

	@JsonProperty("additionalneeds")
	public String additionalneeds;

	@JsonProperty("bookingdates")
	public Bookingdates bookingdates;

	@JsonProperty("totalprice")
	public int totalprice;

	@JsonProperty("depositpaid")
	public boolean depositpaid;

	@JsonProperty("lastname")
	public String lastname;

	public static class Bookingdates{

		@JsonProperty("checkin")
		public String checkin;

		@JsonProperty("checkout")
		public String checkout;
	}
}