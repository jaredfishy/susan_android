package za.co.jaredfishy.susan.task;

import android.os.AsyncTask;

import java.util.Map;

import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.domain.PokeResponse;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.rest.LightServiceBuilder;
import za.co.jaredfishy.susan.rest.ServiceResponseProcessor;
import za.co.jaredfishy.susan.util.JsonParser;
import za.co.jaredfishy.susan.util.LightParser;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public class LightPokeTask extends AsyncTask<String, String, PokeResponse> {

    @Override
    protected PokeResponse doInBackground(String... string) {

        ThreadSleeper.sleep(250);
        LightServiceBuilder lightServiceBuilder = new LightServiceBuilder();
        LightService service = lightServiceBuilder.build();

        ServiceResponseProcessor responseProcessor = new ServiceResponseProcessor();
        String response = responseProcessor.getString(service.poke());

        System.out.println("Poke response: " + response);
        return JsonParser.fromJson(response, PokeResponse.class);
    }
}
