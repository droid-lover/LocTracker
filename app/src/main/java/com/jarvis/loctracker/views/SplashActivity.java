package com.jarvis.loctracker.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jarvis.loctracker.R;
import com.jarvis.loctracker.utils.Sharedpreferences;

import butterknife.ButterKnife;

/**
 * Created by Sachin
 */


public class SplashActivity extends Activity {

    private Sharedpreferences mSharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initialization();
    }

    private void initialization() {
        mSharedpreferences = Sharedpreferences.getUserDataObj(SplashActivity.this);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        goToNextScreen();
                    }
                }, 2500);
    }

    private void goToNextScreen() {
        Intent intent;
        if (mSharedpreferences.getIsUserLoggedIn()) {
            intent = new Intent(SplashActivity.this, HomeActivity.class);//user is logged in redirect him to home_menu activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //    public void checkLogin() {
//        // Check login status
//        boolean log = this.isLoggedIn();
//        if (this.isLoggedIn()) {
//            Intent i = new Intent(context, HomeActivity.class);//user is logged in redirect him to home_menu activity
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            // Staring Login Activity
//            context.startActivity(i);
//        } else {//not loggedin
//            Intent i = new Intent(context, SignupLoginActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
//        }
//    }
//
//    public boolean isLoggedIn() {
//        return pref.getBoolean(IS_LOGIN, false);
//    }

}