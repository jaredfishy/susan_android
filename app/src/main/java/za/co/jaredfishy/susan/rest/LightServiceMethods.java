package za.co.jaredfishy.susan.rest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LightServiceMethods {

    @GET("poke")
    Call<String> poke();

    @GET("lights/prepare")
    Call<String> prepare();

    @GET("lights/auto-discover")
    Call<String> discoverAll();

    @GET("lights/all-on")
    Call<String> turnOn();

    @GET("lights/all-off")
    Call<String> turnOff();

}
