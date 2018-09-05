package com.jarvis.veronica.user.entity;

import java.io.Serializable;

public class LatLong implements Serializable{
    public String latitude;
    public String longitude;

    public LatLong(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
