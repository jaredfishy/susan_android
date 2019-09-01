package za.co.jaredfishy.susan.domain;

import com.google.gson.annotations.SerializedName;

public class LightLocation {

    @SerializedName("ip")
    private String ip;

    public LightLocation(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }
}
