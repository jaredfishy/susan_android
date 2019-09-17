package za.co.jaredfishy.susan.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public class ServiceResponseProcessor {

    private static final long SLEEP_DURATION = 100;

    public String getString(final Call<String> call) throws Throwable {
        final CallResult callResult = new CallResult();
        callResult.running = true;
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                callResult.response = response.body();
                callResult.running = false;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                callResult.response = t.getMessage();
                callResult.throwable = t;
            }
        });

        while (callResult.isRunning()) {
            ThreadSleeper.sleep(SLEEP_DURATION);
            callResult.runtime += SLEEP_DURATION;
        }

        if (callResult.throwable == null)
            return callResult.response;

        throw callResult.throwable;
    }

    private class CallResult {

        private boolean running;
        private Throwable throwable;
        private String response;
        private long runtime;

        private CallResult() {
            running = false;
            throwable = null;
            response = "";
            runtime = 0;
        }

        private boolean isRunning() {
            return running && throwable == null && runtime < 10000;
        }
    }

}
