package za.co.jaredfishy.susan.task;

import android.os.AsyncTask;

import java.util.Map;

import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.rest.LightServiceBuilder;
import za.co.jaredfishy.susan.rest.ServiceResponseProcessor;
import za.co.jaredfishy.susan.util.LightParser;

public class LightDiscoveryTask extends AsyncTask<String, String, Map<String, Light>> {

    @Override
    protected Map<String, Light> doInBackground(String... string) {

        LightServiceBuilder lightServiceBuilder = new LightServiceBuilder();
        LightService service = lightServiceBuilder.build();

        ServiceResponseProcessor responseProcessor = new ServiceResponseProcessor();
        String response = responseProcessor.getString(service.discover());

        System.out.println("Discovery response: " + response);
        return LightParser.parseMap(response);
    }
}
