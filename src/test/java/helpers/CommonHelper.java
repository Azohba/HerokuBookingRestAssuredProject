package helpers;

import io.restassured.response.Response;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class CommonHelper {
    public  Logger logger = Logger.getLogger(String.valueOf(CommonHelper.class));
    public Response response;

    public int getRandomNumber(Integer bound) {
        String SALTCHARS = "123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < bound) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return Integer.parseInt(saltStr);
    }

    public String getRandomString(Integer lenghtParam) {
        String SALTCHARS = "abcdefghijklmnoprstuvyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < lenghtParam) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

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
