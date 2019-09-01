package za.co.jaredfishy.susan.rest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LightService {

    @GET("/poke")
    Call<String> poke();

    @GET("light/discover")
    Call<String> discover();

    @GET("light/on")
    Call<String> turnOn();

    @GET("light/off")
    Call<String> turnOff();

}
