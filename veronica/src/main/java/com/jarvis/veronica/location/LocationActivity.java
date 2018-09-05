package com.jarvis.veronica.location;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jarvis.veronica.Veronica;
import com.jarvis.veronica.db.UserDB;
import com.jarvis.veronica.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements LocationListener {


    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocation();
    }

    public void getLocation() {
        Log.d("Coming", "DATA==");

        if (ContextCompat.checkSelfPermission(LocationActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }else{
            //TODO
            //for now just assuming user will provide information in first shot.
            try {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Toast.makeText(LocationActivity.this, "Location is=" + location, Toast.LENGTH_SHORT).show();
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2500, 2, this);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onLocationChanged(Location location) {
        //TODO use this commented code ,it works fine.
//        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
//
//        try {
//            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//            List<LatLong> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n" + addresses.get(0).getAddressLine(0) + ", " +
//                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
//        } catch (Exception e) {
//
//        }

//        ArrayList<String> addressList = new ArrayList<>();
//        addressList.add("Address 1");
//        addressList.add("Address 2");

        String addressList = "Address 1";

        UserRepository userRepository = new UserRepository(getApplication());
        userRepository.updateUserLocations(addressList, Veronica.userName);


    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(LocationActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}
