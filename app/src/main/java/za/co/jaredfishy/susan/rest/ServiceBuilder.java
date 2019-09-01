package za.co.jaredfishy.susan.rest;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceBuilder<T> {

    private String baseUrl;
    private Class<T> serviceDefinition;

    public ServiceBuilder(String baseUrl, Class<T> serviceDefinition) {
        this.baseUrl = baseUrl;
        this.serviceDefinition = serviceDefinition;
    }

    public T build() {
        System.out.println("Building service for " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit.create(serviceDefinition);
    }
}
