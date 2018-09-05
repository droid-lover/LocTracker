package com.jarvis.veronica.user.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "user_table")
public class User implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    public String password;
    public String addressList;
//    public ArrayList<LatLong> latLongList;

    public User(@NonNull String name, @NonNull String password,String addressList) {
        this.name = name;
        this.password = password;
        this.addressList=addressList;
    }

}
