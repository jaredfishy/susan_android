package za.co.jaredfishy.susan.task;

import android.os.AsyncTask;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import za.co.jaredfishy.susan.domain.JZTaskResponse;
import za.co.jaredfishy.susan.rest.ServiceResponseProcessor;
import za.co.jaredfishy.susan.util.JsonParser;
import za.co.jaredfishy.susan.util.ThreadSleeper;

public abstract class JZTask<T> extends AsyncTask<String, String, JZTaskResponse<T>> {

    private Class<T> responseType;
    private Call<String> call;

    public JZTask(Class<T> responseType, Call<String> call) {
        this.responseType = responseType;
        this.call = call;
    }

    @Override
    protected final JZTaskResponse<T> doInBackground(String... string) {
        ThreadSleeper.sleep(250);
        JZTaskResponse<T> response = new JZTaskResponse<>();

        try {
            T callResponse = executeCall(call);
            response.success(callResponse);
        } catch (SocketTimeoutException exception) {
            response.fail("Cannot connect to server :(");
        } catch (Throwable exception) {
            response.fail(exception.getMessage());
        }
        return response;
    }

    @Override
    protected final void onPostExecute(JZTaskResponse<T> response) {
        if (response.isSuccess())
            onSuccess(response.getResult());
        else
            onFail(response.getError());
    }

    private T executeCall(Call<String> call) throws Throwable {
        ServiceResponseProcessor responseProcessor = new ServiceResponseProcessor();
        String stringResponse = responseProcessor.getString(call);
        return JsonParser.fromJson(stringResponse, responseType);
    }


    protected abstract void onSuccess(T response);

    protected abstract void onFail(String response);

}
