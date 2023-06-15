package helpers;

import io.restassured.response.Response;

import java.util.Random;
import java.util.logging.Logger;

public class CommonHelper {
    public static Logger logger = Logger.getLogger(String.valueOf(CommonHelper.class));
    public static Response response;
    public static String token;

    public int getRandomNumber(Integer bound){
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


}
