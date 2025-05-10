package com.tranbichlien.finalproject.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.tranbichlien.finalproject.R;

/**
 * Utility class for standardized image loading with Glide throughout the app
 */
public class GlideUtils {
    public static void loadImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView,
            @DrawableRes int placeholder, @DrawableRes int errorImage) {
        GlideApp.with(context)
                .load(url)
                .placeholder(placeholder)
                .error(errorImage)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    public static void loadImageWithCallback(Context context, String url, ImageView imageView,
            final ImageLoadCallback callback) {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                            Target<Drawable> target, boolean isFirstResource) {
                        if (callback != null) {
                            callback.onFailed(e);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                            Target<Drawable> target, DataSource dataSource,
                            boolean isFirstResource) {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * Load a circular cropped image (useful for profile pictures)
     *
     * @param context   Application context
     * @param url       Image URL to load
     * @param imageView Target ImageView
     */
    public static void loadCircularImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.placeholder_circular)
                .error(R.drawable.error_image_circular)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    /**
     * Interface for image loading callbacks
     */
    public interface ImageLoadCallback {
        void onSuccess();

        void onFailed(@Nullable GlideException e);
    }
}