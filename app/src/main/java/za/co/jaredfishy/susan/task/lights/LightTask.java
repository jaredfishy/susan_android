package za.co.jaredfishy.susan.task.lights;

import android.os.AsyncTask;

import retrofit2.Call;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.rest.LightServiceMethods;
import za.co.jaredfishy.susan.rest.ServiceResponseProcessor;
import za.co.jaredfishy.susan.util.JsonParser;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public abstract class LightTask<T> extends AsyncTask<String, String, T> {

    protected LightServiceMethods service;
    private Class<T> responseType;

    public LightTask(Class<T> responseType) {
        service = LightService.get();
        this.responseType = responseType;
    }

    @Override
    protected T doInBackground(String... string) {
        ThreadSleeper.sleep(250);
        return executeServiceCall(string);
    }

    protected T execute(Call<String> call) {
        System.out.println("Executing command...");
        ServiceResponseProcessor responseProcessor = new ServiceResponseProcessor();
        String response = responseProcessor.getString(call);
        return JsonParser.fromJson(response, responseType);
    }

    protected abstract T executeServiceCall(String... string);

}
