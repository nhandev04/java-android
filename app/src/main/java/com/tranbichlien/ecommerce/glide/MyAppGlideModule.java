package com.tranbichlien.ecommerce.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
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
        // Set optimal memory cache size based on device
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(3)
                .setBitmapPoolScreens(3)
                .build();

        // Use the larger of calculated size or fixed size
        int memoryCacheSize = Math.max(MEMORY_CACHE_SIZE, (int) (calculator.getMemoryCacheSize() * 1.2));
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));

        // Set bitmap pool for recycling
        builder.setBitmapPool(new LruBitmapPool((int) (calculator.getBitmapPoolSize() * 1.2)));

        // Set disk cache
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));

        // Set default request options
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565) // Uses less memory
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // Cache processed images
                        .centerCrop()
                        .disallowHardwareConfig()); // Better compatibility with all image formats

        // Set log level for debugging
        builder.setLogLevel(Log.DEBUG);

        Log.d(TAG, "Glide module initialized with enhanced image format support (.jpg, .png, .webp)");
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        // Register the OkHttpClient for network operations with increased timeout
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

        // Replace the default HttpUrlConnection with OkHttp
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    }
}