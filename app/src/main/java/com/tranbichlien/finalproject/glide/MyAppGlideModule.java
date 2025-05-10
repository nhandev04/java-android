package com.tranbichlien.finalproject.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Configures Glide for the application with optimized settings
 */
@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    private static final int MEMORY_CACHE_SIZE = 1024 * 1024 * 20; // 20 MB
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 250; // 250 MB
    private static final String TAG = "MyAppGlideModule";
    private static final int TIMEOUT_SECONDS = 30;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // Set memory cache
        builder.setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE));

        // Set disk cache
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));

        // Set default request options
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565) // Uses less memory
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // Cache processed images
                        .centerCrop());

        // Set log level for debugging
        builder.setLogLevel(Log.INFO);
    }
}