package za.co.jaredfishy.susan.rest;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.Susan;

public class LightServiceBuilder extends ServiceBuilder<LightService> {

    private static final String BASE_URL = Susan.getContext().getResources().getString(R.string.service_url);

    public LightServiceBuilder() {
        super(BASE_URL, LightService.class);
    }
}
