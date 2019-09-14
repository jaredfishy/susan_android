package za.co.jaredfishy.susan.util;


import android.util.Base64;

public class BasicAuthHeaderGenerator {

    public static String getHeader(String username, String password){
        String auth = username + ":" + password;
        String b64 = Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);
        return "Basic " + b64;
    }
}
