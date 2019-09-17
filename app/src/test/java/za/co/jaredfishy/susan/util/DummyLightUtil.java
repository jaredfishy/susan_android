package za.co.jaredfishy.susan.util;

import za.co.jaredfishy.susan.domain.lights.Light;
import za.co.jaredfishy.susan.domain.lights.LightLocation;

public class DummyLightUtil {

    public static Light getLight(String id) {
        return new Light(id, "Light", new LightLocation("192.168.0." + id), "COLOR",true);
    }
}
