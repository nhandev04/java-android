package com.tranbichlien.ecommerce.util;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.request.RequestOptions;

/**
 * Legacy Glide configuration class - NOT USED ANYMORE
 * This class has been replaced by
 * com.tranbichlien.ecommerce.glide.MyAppGlideModule
 * Keeping for reference only
 */
public class GlideConfigUtil {

    private static final String TAG = "GlideConfigUtil";

    /**
     * Apply configuration options to a GlideBuilder - for reference only
     */
    public static void applyOptions(Context context, GlideBuilder builder) {
        // Set higher quality image decoding
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565)
                        .disallowHardwareConfig());

        // Increase memory cache size
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(3)
                .setBitmapPoolScreens(3)
                .build();

        builder.setMemoryCache(new LruResourceCache((int) (calculator.getMemoryCacheSize() * 1.2)));
        builder.setBitmapPool(new LruBitmapPool((int) (calculator.getBitmapPoolSize() * 1.2)));

        // Enable logging for loading issues
        builder.setLogLevel(Log.DEBUG);

        // Log that the module is initialized
        Log.d(TAG, "Glide module initialized with enhanced image format support (.jpg, .png, .webp)");
    }
}
