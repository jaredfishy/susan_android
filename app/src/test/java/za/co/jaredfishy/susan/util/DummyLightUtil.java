package za.co.jaredfishy.susan.util;

import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.domain.LightLocation;

public class DummyLightUtil {

    public static Light getLight(String id) {
        return new Light(id, "Light", new LightLocation("192.168.0." + id), "COLOR",true);
    }
}
