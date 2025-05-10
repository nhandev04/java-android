package com.tranbichlien.finalproject.api.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tranbichlien.finalproject.api.repository.TagRepository;
import com.tranbichlien.finalproject.entity.Tag;

import java.util.List;

/**
 * Example activity demonstrating how to use the Tag API
 */
public class TagApiExample extends AppCompatActivity {

    private static final String TAG = "TagApiExample";
    private TagRepository tagRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the repository
        tagRepository = new TagRepository();

        // Get all tags
        getAllTags();
    }

    /**
     * Example method to get all tags from the API
     */
    private void getAllTags() {
        // Call the getTags method with null parameters for default pagination
        tagRepository.getTags(null, null).observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                if (tags != null) {
                    // Success - handle the list of tags
                    Log.d(TAG, "Retrieved " + tags.size() + " tags");

                    // Example: Log each tag
                    for (Tag tag : tags) {
                        Log.d(TAG, "Tag: " + tag.getName() + " - " + tag.getDescription());
                    }

                    // Show success message
                    Toast.makeText(TagApiExample.this,
                            "Successfully loaded " + tags.size() + " tags",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Error - handle failure
                    Log.e(TAG, "Failed to load tags");
                    Toast.makeText(TagApiExample.this,
                            "Failed to load tags",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Example method to get a specific tag by ID
     * 
     * @param tagId The ID of the tag to retrieve
     */
    private void getSpecificTag(String tagId) {
        tagRepository.getTagById(tagId).observe(this, new Observer<Tag>() {
            @Override
            public void onChanged(Tag tag) {
                if (tag != null) {
                    // Success - handle the tag
                    Log.d(TAG, "Retrieved tag: " + tag.getName());

                    // Show success message
                    Toast.makeText(TagApiExample.this,
                            "Retrieved tag: " + tag.getName(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    // Error - handle failure
                    Log.e(TAG, "Failed to load tag with ID: " + tagId);
                    Toast.makeText(TagApiExample.this,
                            "Failed to load tag",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}