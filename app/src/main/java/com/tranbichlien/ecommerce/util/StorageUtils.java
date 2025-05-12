package com.tranbichlien.ecommerce.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tranbichlien.ecommerce.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for handling local storage operations
 */
public class StorageUtils {
    private static final String PREF_NAME = "app_preferences";
    public static final String CART_ITEMS_KEY = "cart_items";
    private static final String FAVORITE_ITEMS_KEY = "favorite_items";

    // Authentication related keys
    private static final String AUTH_TOKEN_KEY = "auth_token";
    private static final String USER_DATA_KEY = "user_data";
    private static final String IS_LOGGED_IN_KEY = "is_logged_in";

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

    /**
     * Save the JWT token to SharedPreferences
     * 
     * @param context The context
     * @param token The JWT token
     */
    public static void saveAuthToken(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(AUTH_TOKEN_KEY, token);
        editor.apply();
    }

    /**
     * Get the JWT token from SharedPreferences
     * 
     * @param context The context
     * @return The JWT token, or null if not found
     */
    public static String getAuthToken(Context context) {
        return getPreferences(context).getString(AUTH_TOKEN_KEY, null);
    }

    /**
     * Save the user data to SharedPreferences
     * 
     * @param context The context
     * @param user The user data
     */
    public static void saveUserData(Context context, User user) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(USER_DATA_KEY, userJson);
        editor.apply();
    }

    /**
     * Get the user data from SharedPreferences
     * 
     * @param context The context
     * @return The user data, or null if not found
     */
    public static User getUserData(Context context) {
        String userJson = getPreferences(context).getString(USER_DATA_KEY, null);
        if (userJson == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(userJson, User.class);
    }

    /**
     * Set the login state
     * 
     * @param context The context
     * @param isLoggedIn The login state
     */
    public static void setLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(IS_LOGGED_IN_KEY, isLoggedIn);
        editor.apply();
    }

    /**
     * Check if the user is logged in
     * 
     * @param context The context
     * @return true if the user is logged in, false otherwise
     */
    public static boolean isLoggedIn(Context context) {
        return getPreferences(context).getBoolean(IS_LOGGED_IN_KEY, false);
    }

    /**
     * Clear all authentication data (logout)
     * 
     * @param context The context
     */
    public static void clearAuthData(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.remove(AUTH_TOKEN_KEY);
        editor.remove(USER_DATA_KEY);
        editor.putBoolean(IS_LOGGED_IN_KEY, false);
        editor.apply();
    }
}
