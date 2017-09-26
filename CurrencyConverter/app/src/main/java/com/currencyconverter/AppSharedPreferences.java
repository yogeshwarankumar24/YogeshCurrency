package com.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by Falconnect on 14-08-2017.
 */

public class AppSharedPreferences {
    private SharedPreferences preferences;
    Activity appContext;;
    private SharedPreferences.Editor mPrefsEditor;

    public AppSharedPreferences(Activity appContextv) {
        appContext = appContextv;
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        mPrefsEditor = preferences.edit();
    }
    public int GetInt(String key) {
        checkForNullKey(key);
        return preferences.getInt(key,0);
    }
    /**
     * Put int value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param value int value to be added
     */
    public void PutInt(String key, int value) {
        checkForNullKey(key);
        mPrefsEditor.putInt(key, value);
        mPrefsEditor.commit();
    }
    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     *  the pref key
     */
    public void checkForNullKey(String key){
        if (key == null){
            CommonFunctions.Error_Dialog(appContext,"Something went wrong.\nPlease try again.");
        }
    }

}
