package za.co.jaredfishy.susan.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonParser {

    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

}
