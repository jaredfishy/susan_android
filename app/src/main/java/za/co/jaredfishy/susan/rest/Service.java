package za.co.jaredfishy.susan.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import za.co.jaredfishy.susan.util.BasicAuthHeaderGenerator;

public abstract class Service<T> {

    private static final long TIMEOUT = 3000;

    private String baseUrl;
    private Class<T> serviceDefinition;
    private T service;

    public Service(String baseUrl, Class<T> serviceDefinition) {
        this.baseUrl = baseUrl;
        this.serviceDefinition = serviceDefinition;
    }

    public T getService() {
        if (service == null)
            service = buildSecure();
        return service;
    }

    private T buildSecure() {
        System.out.println("Building service for " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(getAuthClient(getAppHeaders()))
                .build();
        return retrofit.create(serviceDefinition);
    }

    private Headers getAppHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Susan");
        headers.put("Authorization", BasicAuthHeaderGenerator.getHeader("jz", "fairy"));
        return Headers.of(headers);
    }

    private OkHttpClient getAuthClient(final Headers headers) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .headers(headers)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });

        return httpClient.build();
    }
}
