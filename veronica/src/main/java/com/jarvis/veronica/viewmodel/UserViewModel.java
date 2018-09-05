package com.jarvis.veronica.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jarvis.veronica.repository.UserRepository;
import com.jarvis.veronica.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private LiveData<List<User>> mUserList;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository=new UserRepository(application);
        mUserList=mRepository.getAllUsers();
    }

    public LiveData<List<User>> getmUserList() {
        return mUserList;
    }

    public void insertUser(User user){
        mRepository.insert(user);
        Log.d("TAG","inside insertUser"+ user.name + "=="+user.password );
    }

    public void updateUserAddress(String addressList,String name){
        mRepository.updateUserLocations(addressList,name);
    }

    public void deleteAllUser(){
        mRepository.deleteAllUser();
    }
}
