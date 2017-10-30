package com.example.testing.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author Adegoke Obasa <goke@cottacush.com>
 */

public class PrefsUtils {

    public static final String GLOBAL_SHARED_PREFS = "global_shared_prefs";

    private Context context;


    public PrefsUtils(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(GLOBAL_SHARED_PREFS,
                Context.MODE_PRIVATE);
    }

    /**
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        getSharedPreferences().edit()
                .putString(key, value)
                .apply();
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        getSharedPreferences().edit()
                .putInt(key, value)
                .apply();
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(String key, boolean value) {
        getSharedPreferences().edit()
                .putBoolean(key, value)
                .apply();
    }

    /**
     * @param key
     * @param value
     */

    public void putFloat(String key, float value) {
        getSharedPreferences().edit()
                .putFloat(key, value)
                .apply();
    }

    /**
     * @param key
     * @param values
     */
    public void putStringSet(String key, Set<String> values) {
        getSharedPreferences().edit()
                .putStringSet(key, values)
                .apply();
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(String key, Long value) {
        getSharedPreferences().edit()
                .putLong(key, value)
                .apply();
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return getSharedPreferences().getStringSet(key, defaultValue);
    }

    /**
     * @param key
     */
    public void remove(String key) {
        getSharedPreferences().edit().remove(key);
    }

    /**
     * @param Key
     * @return
     */
    public Boolean doesContain(String Key) {
        return getSharedPreferences().contains(Key);
    }
}

