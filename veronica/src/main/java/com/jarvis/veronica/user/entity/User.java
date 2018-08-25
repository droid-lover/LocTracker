package com.jarvis.veronica.user.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "user_table")
public class User implements Serializable{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    public String password;

    public User(@NonNull String name, @NonNull String password) {
        this.name = name;
        this.password = password;
    }

}
