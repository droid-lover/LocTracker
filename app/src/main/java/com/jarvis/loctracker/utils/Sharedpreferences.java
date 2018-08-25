package com.jarvis.loctracker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;


public class Sharedpreferences {

    Context context;
    private SharedPreferences pref; //added private
    public static Editor editor;
    private int PRIVATE_MODE = 0;
    private static Sharedpreferences userData = null;

    // Shared Preferences file name

    private static final String PREF_NAME = "com.jarvis.loctracker";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    private static final String TAG_USER_LOGGED_IN = "username";

    public static final String KEY_EMAIL = "email";

    public static final String TAG_IEMI_NUMBER = "imeiNumber";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    public static final String LOGINSTATUS = "login_status";

    public Sharedpreferences(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static Sharedpreferences getUserDataObj(Context c) {
        if (userData == null) {
            userData = new Sharedpreferences(c);
        }
        return userData;
    }

    public void clearAll(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        pref.edit().clear().commit();
    }


    /*
        *  Loggedin username
        **/
    public Boolean getIsUserLoggedIn() {
        return pref.getBoolean(TAG_USER_LOGGED_IN, false);
    }

    public void setIsUserLoggedIn(Boolean status) {
        try {
            editor.putBoolean(TAG_USER_LOGGED_IN, status);
            editor.commit();
        } catch (Exception e) {
        }
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }



    //LoginStatus
    public Boolean getLoginStatus() {
        return pref.getBoolean(LOGINSTATUS, false);
    }

    public void setLoginStatus(Boolean status) {
        try {
            editor.putBoolean(LOGINSTATUS, status);
            editor.commit();
        } catch (Exception e) {
        }
    }

}

