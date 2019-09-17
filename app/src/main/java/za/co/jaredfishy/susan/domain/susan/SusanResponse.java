package za.co.jaredfishy.susan.domain.susan;

import com.google.gson.annotations.SerializedName;

public class SusanResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("timestamp")
    private String timestamp;

    public SusanResponse(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
