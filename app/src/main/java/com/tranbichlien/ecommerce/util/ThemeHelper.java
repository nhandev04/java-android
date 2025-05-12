package com.tranbichlien.ecommerce.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatDelegate;

/**
 * Utility class to help with theme management
 */
public class ThemeHelper {
    private static final String PREFERENCES_NAME = "app_preferences";
    private static final String THEME_MODE_KEY = "theme_mode";

    // Theme mode constants
    public static final int MODE_AUTO = 0;
    public static final int MODE_LIGHT = 1;
    public static final int MODE_DARK = 2;

    /**
     * Set the theme mode for the app (light, dark, or auto/system default)
     * 
     * @param context   The application context
     * @param themeMode The theme mode to set (MODE_AUTO, MODE_LIGHT, or MODE_DARK)
     */
    public static void setThemeMode(Context context, int themeMode) {
        // Save the theme mode selection to SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(THEME_MODE_KEY, themeMode);
        editor.apply();

        // Apply the theme mode
        applyThemeMode(themeMode);
    }

    /**
     * Apply the saved theme mode when the app starts
     * 
     * @param context The application context
     */
    public static void applyThemeFromPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int themeMode = prefs.getInt(THEME_MODE_KEY, MODE_AUTO); // Default to auto mode
        applyThemeMode(themeMode);
    }

    /**
     * Apply the specified theme mode
     * 
     * @param themeMode The theme mode to apply
     */
    private static void applyThemeMode(int themeMode) {
        switch (themeMode) {
            case MODE_LIGHT:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case MODE_DARK:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case MODE_AUTO:
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
        }
    }

    /**
     * Get the currently selected theme mode
     * 
     * @param context The application context
     * @return The selected theme mode (MODE_AUTO, MODE_LIGHT, or MODE_DARK)
     */
    public static int getThemeMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(THEME_MODE_KEY, MODE_AUTO); // Default to auto mode
    }
}
