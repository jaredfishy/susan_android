package za.co.jaredfishy.susan.rest;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.Susan;

public class LightService extends Service<LightServiceMethods> {

    private static final String BASE_URL = Susan.getContext().getResources().getString(R.string.susan_url);

    private static LightService instance;

    public static LightServiceMethods get() {
        if (instance == null)
            instance = new LightService();

        return instance.getService();
    }

    private LightService() {
        super(BASE_URL, LightServiceMethods.class);
    }
}
