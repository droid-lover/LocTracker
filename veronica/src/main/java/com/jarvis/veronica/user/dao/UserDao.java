package com.jarvis.veronica.user.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jarvis.veronica.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User userObj);

    @Query("SELECT * from user_table")
    LiveData<List<User>> usersList();

    @Update
    void updateUser(User userObj);

    @Query("DELETE FROM user_table")
    void deleteAll();

    /**
     * Updating only address
     * By name
     */
    @Query("UPDATE user_table SET addressList=:addressList WHERE name = :name")
    void updateAddressList(String addressList, String name);
}