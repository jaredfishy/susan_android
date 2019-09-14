package za.co.jaredfishy.susan.domain;

import com.google.gson.annotations.SerializedName;

public class SusanResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("timestamp")
    private String timestamp;

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
