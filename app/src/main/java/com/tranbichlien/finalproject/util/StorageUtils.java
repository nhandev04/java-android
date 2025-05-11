package com.tranbichlien.finalproject.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for handling local storage operations
 */
public class StorageUtils {
    private static final String PREF_NAME = "app_preferences";
    public static final String CART_ITEMS_KEY = "cart_items";
    private static final String FAVORITE_ITEMS_KEY = "favorite_items";

    /**
     * Get SharedPreferences instance
     */
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Add a product ID to the cart
     */
    public static void addToCart(Context context, String productId) {
        Set<String> cartItems = getCartItems(context);
        cartItems.add(productId);

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putStringSet(CART_ITEMS_KEY, cartItems);
        editor.apply();
    }

    /**
     * Remove a product ID from the cart
     */
    public static void removeFromCart(Context context, String productId) {
        Set<String> cartItems = getCartItems(context);
        cartItems.remove(productId);

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putStringSet(CART_ITEMS_KEY, cartItems);
        editor.apply();
    }

    /**
     * Get all product IDs in the cart
     */
    public static Set<String> getCartItems(Context context) {
        return new HashSet<>(getPreferences(context).getStringSet(CART_ITEMS_KEY, new HashSet<>()));
    }

    /**
     * Check if a product ID is in the cart
     */
    public static boolean isInCart(Context context, String productId) {
        return getCartItems(context).contains(productId);
    }

    /**
     * Add a product ID to favorites
     */
    public static void addToFavorites(Context context, String productId) {
        Set<String> favoriteItems = getFavoriteItems(context);
        favoriteItems.add(productId);

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putStringSet(FAVORITE_ITEMS_KEY, favoriteItems);
        editor.apply();
    }

    /**
     * Remove a product ID from favorites
     */
    public static void removeFromFavorites(Context context, String productId) {
        Set<String> favoriteItems = getFavoriteItems(context);
        favoriteItems.remove(productId);

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putStringSet(FAVORITE_ITEMS_KEY, favoriteItems);
        editor.apply();
    }

    /**
     * Get all product IDs in favorites
     */
    public static Set<String> getFavoriteItems(Context context) {
        return new HashSet<>(getPreferences(context).getStringSet(FAVORITE_ITEMS_KEY, new HashSet<>()));
    }

    /**
     * Check if a product ID is in favorites
     */
    public static boolean isInFavorites(Context context, String productId) {
        return getFavoriteItems(context).contains(productId);
    }

    /**
     * Toggle favorite status for a product
     * 
     * @return true if the product is now a favorite, false if it was removed
     */
    public static boolean toggleFavorite(Context context, String productId) {
        if (isInFavorites(context, productId)) {
            removeFromFavorites(context, productId);
            return false;
        } else {
            addToFavorites(context, productId);
            return true;
        }
    }
}
