package com.tranbichlien.finalproject.api;

import com.tranbichlien.finalproject.api.service.CategoryApiService;
import com.tranbichlien.finalproject.api.service.ProductApiService;
import com.tranbichlien.finalproject.api.service.TagApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API Client for handling Retrofit initialization and service creation
 */
public class ApiClient {
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    /**
     * Get the Retrofit instance
     * 
     * @return The Retrofit instance
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Get the OkHttpClient instance
     * 
     * @return The OkHttpClient instance
     */
    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            // Create a logging interceptor for debugging
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Build the OkHttpClient with timeouts and logging
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
        return okHttpClient;
    }

    /**
     * Get the ProductApiService instance
     * 
     * @return The ProductApiService instance
     */
    public static ProductApiService getProductApiService() {
        return getRetrofit().create(ProductApiService.class);
    }

    /**
     * Get the CategoryApiService instance
     * 
     * @return The CategoryApiService instance
     */
    public static CategoryApiService getCategoryApiService() {
        return getRetrofit().create(CategoryApiService.class);
    }

    /**
     * Get the TagApiService instance
     * 
     * @return The TagApiService instance
     */
    public static TagApiService getTagApiService() {
        return getRetrofit().create(TagApiService.class);
    }
}