package com.jarvis.loctracker.views;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jarvis.loctracker.R;
import com.jarvis.loctracker.utils.Sharedpreferences;
import com.jarvis.veronica.user.entity.User;
import com.jarvis.veronica.viewmodel.UserViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {

    private final String TAG = HomeActivity.class.getName();

    private Sharedpreferences mSharedpreferences;
    private String name = "", password = "";
    UserViewModel model;

    @BindView(R.id.tv_show_my_locations)
    TextView tvShowMyLocations;
    @BindView(R.id.tv_show_all_users)
    TextView tvShowAllUsers;
    @BindView(R.id.tv_delete_all_users)
    TextView tvDeleteAllUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initialization();
    }

    private void initialization() {
        mSharedpreferences = Sharedpreferences.getUserDataObj(HomeActivity.this);
        Intent intent = getIntent();
        if (intent.getStringExtra("name") != null) {
            name = intent.getStringExtra("name");
        }
        if (intent.getStringExtra("password") != null) {
            password = intent.getStringExtra("password");
        }
        model = new UserViewModel(getApplication());
        saveData(name, password);

        getUsers();

    }


    private void saveData(String name, String password) {
        User user = new User(name, password,null);
        model.insertUser(user);
    }

    private List<User> getUsers() {
        final List<User> usersList = new ArrayList<>();
        model.getmUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                for (int i = 0; i < users.size(); i++) {
                    Log.d("TAG", "data" + users.get(i).name+users.get(i).addressList);
                    usersList.add(users.get(i));
                }
            }
        });
        return usersList;
    }


    @OnClick({R.id.tv_show_my_locations, R.id.tv_show_all_users, R.id.tv_delete_all_users, R.id.tv_logout})
    public void login(View view) {
        switch (view.getId()) {
            case R.id.tv_show_all_users:
                showAllUsers();
                break;
            case R.id.tv_delete_all_users:
                deleteAllUsers();
                break;
            case R.id.tv_logout:
                logout();
                break;
        }
    }

    private void showAllUsers() {
        Intent intent = new Intent(HomeActivity.this, AllUsersActivity.class);
        intent.putExtra("users", (Serializable) getUsers());
        startActivity(intent);
    }

    private void deleteAllUsers() {
        model.deleteAllUser();
    }

    private void logout() {
        mSharedpreferences.setIsUserLoggedIn(false);
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
