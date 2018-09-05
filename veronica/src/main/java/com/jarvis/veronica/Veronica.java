package com.jarvis.veronica;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.jarvis.veronica.jobs.SyncJobScheduler;
import com.jarvis.veronica.location.LocationActivity;

import java.util.Timer;
import java.util.TimerTask;


public class Veronica extends Application {

    private static Veronica mInstance;
    public static String userName = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        Intent intent = new Intent(this, LocationActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);


//        Timer myTimer = new Timer();
//        myTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {

        Log.d("Coming", "here");
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));
        Job myJob = dispatcher.newJobBuilder()
                .setService(SyncJobScheduler.class)
                .setTag("to_get_location")
                .setRecurring(false)
                .setTrigger(Trigger.executionWindow(0, 1))
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setReplaceCurrent(false)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL).build();

        dispatcher.mustSchedule(myJob);
//            }
//
//        }, 0, 5000);

    }

    public static synchronized Veronica getInstance() {
        return mInstance;
    }

}
