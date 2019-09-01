package za.co.jaredfishy.susan.task;

import android.os.AsyncTask;

import za.co.jaredfishy.susan.domain.PokeResponse;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.rest.LightServiceBuilder;
import za.co.jaredfishy.susan.rest.ServiceResponseProcessor;
import za.co.jaredfishy.susan.util.JsonParser;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public class LightOnTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... string) {

        ThreadSleeper.sleep(250);
        LightServiceBuilder lightServiceBuilder = new LightServiceBuilder();
        LightService service = lightServiceBuilder.build();

        ServiceResponseProcessor responseProcessor = new ServiceResponseProcessor();
        String response = responseProcessor.getString(service.turnOn());

        System.out.println("Light On response: " + response);
        return response;
    }
}
