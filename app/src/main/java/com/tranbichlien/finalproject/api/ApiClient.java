package com.tranbichlien.finalproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.api.service.CategoryApiService;
import com.tranbichlien.finalproject.api.service.ProductApiService;
import com.tranbichlien.finalproject.api.service.TagApiService;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
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
    private static Gson gson = null;

    private static class ApiResponseDeserializer implements JsonDeserializer<ApiResponse<?>> {
        @SuppressWarnings("unchecked")
        @Override
        public ApiResponse<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            try {
                // Create a raw ApiResponse that we'll use with unchecked assignments
                ApiResponse rawResponse = new ApiResponse<>();

                // Get data type from generic parameter
                Type dataType = null;
                if (typeOfT instanceof ParameterizedType) {
                    Type[] typeArguments = ((ParameterizedType) typeOfT).getActualTypeArguments();
                    if (typeArguments != null && typeArguments.length > 0) {
                        dataType = typeArguments[0];
                    }
                }

                // If response is a JSON array, treat it as successful data
                if (json.isJsonArray()) {
                    rawResponse.setStatus(true);
                    if (dataType != null) {
                        // Use unchecked assignment via the raw type
                        rawResponse.setData(context.deserialize(json, dataType));
                    } else {
                        // Fallback - use raw ArrayList if type info is missing
                        rawResponse.setData(context.deserialize(json, ArrayList.class));
                    }
                    // Return the raw response which will be implicitly cast to ApiResponse<?>
                    return rawResponse;
                }
                // If response is a JSON object, handle normally
                else if (json.isJsonObject()) {
                    try {
                        // Try standard deserialization first
                        return context.deserialize(json, typeOfT);
                    } catch (JsonParseException e) {
                        // Create default empty response if parsing fails
                        rawResponse.setStatus(false);
                        rawResponse.setData(createEmptyResponseData(dataType));
                        return rawResponse;
                    }
                }

                // Default case - handle unknown JSON format
                rawResponse.setStatus(false);
                rawResponse.setData(createEmptyResponseData(dataType));
                return rawResponse;

            } catch (Exception e) {
                // Global exception handler
                e.printStackTrace();

                // Create a raw response for error case
                ApiResponse rawResponse = new ApiResponse<>();
                rawResponse.setStatus(false);
                rawResponse.setData(null);
                return rawResponse;
            }
        }

        /**
         * Create appropriate empty data based on expected type
         */
        private Object createEmptyResponseData(Type dataType) {
            if (dataType == null) {
                return null;
            }

            try {
                if (dataType instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) dataType).getRawType();
                    if (rawType == List.class || rawType == ArrayList.class) {
                        return new ArrayList<>();
                    }
                }
            } catch (Exception e) {
                System.out.println("Error creating empty response: " + e.getMessage());
            }

            return null;
        }
    }

    /**
     * Get the Gson instance with custom type adapters
     * 
     * @return The configured Gson instance
     */
    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeHierarchyAdapter(ApiResponse.class, new ApiResponseDeserializer())
                    .create();
        }
        return gson;
    }

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
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
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