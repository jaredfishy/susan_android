package za.co.jaredfishy.susan.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public class ServiceResponseProcessor {

    private static final long SLEEP_DURATION = 100;

    public String getString(final Call<String> call) {
        final CallStatus callStatus = new CallStatus();
        callStatus.running = true;
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callStatus.response = response.body();
                callStatus.running = false;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                callStatus.response = t.getMessage();
                callStatus.error = true;
            }
        });

        while (callStatus.isRunning()) {
            ThreadSleeper.sleep(SLEEP_DURATION);
            callStatus.runtime += SLEEP_DURATION;
        }

        if (!callStatus.error)
            return callStatus.response;

        throw new RuntimeException(callStatus.response);
    }

    private class CallStatus {

        private boolean running;
        private boolean error;
        private String response;
        private long runtime;

        private CallStatus() {
            running = false;
            error = false;
            response = "";
            runtime = 0;
        }

        private boolean isRunning() {
            return running && !error && runtime < 10000;
        }
    }

}
