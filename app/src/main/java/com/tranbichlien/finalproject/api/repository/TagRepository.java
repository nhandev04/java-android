package com.tranbichlien.finalproject.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tranbichlien.finalproject.api.ApiClient;
import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Tag;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for handling tag-related API calls
 */
public class TagRepository {

    /**
     * Get a list of all tags
     * 
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return LiveData containing the list of tags
     */
    public LiveData<List<Tag>> getTags(Integer page, Integer limit) {
        MutableLiveData<List<Tag>> tagsLiveData = new MutableLiveData<>();

        ApiClient.getTagApiService().getTags(page, limit)
                .enqueue(new Callback<ApiResponse<List<Tag>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Tag>>> call,
                            Response<ApiResponse<List<Tag>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            tagsLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            tagsLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Tag>>> call, Throwable t) {
                        // Handle failure
                        tagsLiveData.setValue(null);
                    }
                });

        return tagsLiveData;
    }

    /**
     * Get a tag by its ID
     * 
     * @param id The ID of the tag
     * @return LiveData containing the tag
     */
    public LiveData<Tag> getTagById(String id) {
        MutableLiveData<Tag> tagLiveData = new MutableLiveData<>();

        ApiClient.getTagApiService().getTagById(id)
                .enqueue(new Callback<ApiResponse<Tag>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<Tag>> call,
                            Response<ApiResponse<Tag>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            tagLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            tagLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Tag>> call, Throwable t) {
                        // Handle failure
                        tagLiveData.setValue(null);
                    }
                });

        return tagLiveData;
    }
}