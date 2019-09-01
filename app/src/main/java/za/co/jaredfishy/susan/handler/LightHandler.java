package za.co.jaredfishy.susan.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.jaredfishy.susan.domain.Light;

public class LightHandler {

    private Map<String, Integer> lightIndex;
    private List<Light> lightList;

    public LightHandler() {
        this.lightList = new ArrayList<>();
        this.lightIndex = new HashMap<>();
    }

    public void add(Light light) {
        if (light == null)
            return;

        String id = light.getId();
        Integer index = lightIndex.get(id);
        if (index == null) {
            index = lightList.size();
            lightIndex.put(id, index);
            lightList.add(light);
        } else {
            index = lightIndex.get(id);
            lightList.set(index, light);
        }
    }

    public int size() {
        return lightList.size();
    }

    public Light get(int index) {
        return lightList.get(index);
    }

    public Light get(String id) {
        Integer index = lightIndex.get(id);
        if (index == null) return null;
        return lightList.get(index);
    }
}
