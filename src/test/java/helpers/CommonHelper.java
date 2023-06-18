package helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class CommonHelper {
    public Logger logger = Logger.getLogger(String.valueOf(CommonHelper.class));
    public Response response;
    public Response getBookingResp;


    public ArrayList<Integer> createdBookingIds = new ArrayList<>();

    public String reqBody(List<List<String>> dataList) {
        JSONObject reqBody = new JSONObject();
        int dataSize = dataList.size();
        String value;
        for (int i = 0; i < dataSize; i++) {
            if (dataList.get(i).get(0).equalsIgnoreCase("keys")){
                i++;
                dataSize--;
            }
            if (dataList.get(i).get(1)== null){
              value =String.valueOf(JSONObject.NULL) ;
            }else{
                value =dataList.get(i).get(1);
            }
            reqBody.put(String.valueOf(dataList.get(i).get(0)), value);
        }
        return reqBody.toString();
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
