package com.dell.actapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class ShareReferenceUtils {

    private static final String KEY_SHARED_PREF = "gymmaster";

    private ShareReferenceUtils() {

    }

    private static SharedPreferences preferences;

    public static SharedPreferences getInstance() {
        if (preferences == null) {
            preferences = App.getAppContext().getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
        }

        return preferences;
    }


    public static String getStringPreference(String key) {
        String value = null;
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            value = preferences.getString(key, null);
        }
        return value;
    }

    public static boolean setStringPreference(String key, String value) {
        SharedPreferences preferences = getInstance();
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }
    public static boolean removeKey(String key) {
        SharedPreferences preferences = getInstance();
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            return editor.commit();
        }
        return false;
    }
}
