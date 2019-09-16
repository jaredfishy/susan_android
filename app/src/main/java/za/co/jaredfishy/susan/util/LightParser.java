package za.co.jaredfishy.susan.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import za.co.jaredfishy.susan.domain.lights.Light;

public class LightParser {

    public static Light parse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Light.class);
    }

    public static Map<String, Light> parseMap(String json) {
        Type lightMapType = new TypeToken<Map<String, Light>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, lightMapType);
    }

}
