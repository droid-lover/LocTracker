package com.jarvis.veronica.jobs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.jarvis.veronica.location.LocationActivity;
import com.jarvis.veronica.utility.Utils;

import java.util.Calendar;

/**
 * Created by Sachin
 */

public class SyncJobScheduler extends JobService {
    @Override
    public boolean onStartJob(final JobParameters job) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (("to_get_location").equalsIgnoreCase(job.getTag())) {
                        Log.d("Coming", "inside SyncJobScheduler here to get location");
                        LocationActivity locationActivity = new LocationActivity();
                        locationActivity.getLocation();

                    }
                } catch (Throwable ignored) {

                }
            }
        }).start();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
