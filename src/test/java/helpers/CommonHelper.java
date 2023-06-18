package helpers;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommonHelper {
    public Logger logger = Logger.getLogger(String.valueOf(CommonHelper.class));
    public Response response;
    public ArrayList<Integer> createdBookingIds = new ArrayList<>();

    public void compareDates(String checkIn, String checkOut) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date checkinDate = formatter.parse(checkIn);
        Date checkOutDate = formatter.parse(checkOut);
        Assert.assertFalse("Your check-in date cannot be before on today", formatter.format(today).compareTo(String.valueOf(checkinDate)) > 0);
        Assert.assertFalse("Your check-out date cannot be before on today", formatter.format(today).compareTo(String.valueOf(checkOutDate)) > 0);
        Assert.assertFalse("Your check-in date cannot be after on check-out", formatter.format(today).compareTo(String.valueOf(checkinDate)) > 0);
    }


}
