package za.co.jaredfishy.susan.task;

import android.os.AsyncTask;

import za.co.jaredfishy.susan.domain.SusanResponse;
import za.co.jaredfishy.susan.rest.LightServiceMethods;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.rest.ServiceResponseProcessor;
import za.co.jaredfishy.susan.util.JsonParser;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public class LightPokeTask extends AsyncTask<String, String, SusanResponse> {

    @Override
    protected SusanResponse doInBackground(String... string) {

        ThreadSleeper.sleep(250);
        LightServiceMethods service = LightService.get();

        ServiceResponseProcessor responseProcessor = new ServiceResponseProcessor();
        String response = responseProcessor.getString(service.poke());

        System.out.println("Poke response: " + response);
        return JsonParser.fromJson(response, SusanResponse.class);
    }
}
