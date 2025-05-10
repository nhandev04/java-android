package com.tranbichlien.finalproject.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tranbichlien.finalproject.api.ApiClient;
import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Category;
import com.tranbichlien.finalproject.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for handling category-related API calls
 */
public class CategoryRepository {

    /**
     * Get a list of categories
     * 
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return LiveData containing the list of categories
     */
    public LiveData<List<Category>> getCategories(Integer page, Integer limit) {
        MutableLiveData<List<Category>> categoriesLiveData = new MutableLiveData<>();

        ApiClient.getCategoryApiService().getCategories(page, limit)
                .enqueue(new Callback<ApiResponse<List<Category>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Category>>> call,
                            Response<ApiResponse<List<Category>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            categoriesLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            categoriesLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Category>>> call, Throwable t) {
                        // Handle failure
                        categoriesLiveData.setValue(null);
                    }
                });

        return categoriesLiveData;
    }
}
