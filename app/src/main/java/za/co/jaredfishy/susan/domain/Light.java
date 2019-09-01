package za.co.jaredfishy.susan.domain;

import com.google.gson.annotations.SerializedName;

public class Light {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private LightLocation location;

    @SerializedName("model")
    private String model;

    @SerializedName("on")
    private boolean powered;

    public Light(String id, String name, LightLocation location, String model, boolean powered) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.model = model;
        this.powered = powered;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return location.getIp();
    }

    public String getModel() {
        return model;
    }

    public boolean isPowered() {
        return powered;
    }

    @Override
    public String toString() {
        if (name.length() == 0)
            return model;
        else
            return name;
    }
}
