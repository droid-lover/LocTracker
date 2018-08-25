package com.jarvis.loctracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jarvis.veronica.user.entity.User;
import com.jarvis.veronica.viewmodel.UserViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    private String name = "", password = "";
    UserViewModel model;

    @BindView(R.id.tv_show_my_locations)
    TextView tvShowMyLocations;
    @BindView(R.id.tv_show_all_users)
    TextView tvShowAllUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initialization();
    }

    private void initialization() {
        Intent intent = getIntent();
        if (intent.getStringExtra("name") != null) {
            name = intent.getStringExtra("name");
        }
        if (intent.getStringExtra("password") != null) {
            password = intent.getStringExtra("password");
        }
        model = new UserViewModel(getApplication());
        saveData(name, password);
    }

    private void saveData(String name, String password) {
        User user = new User(name, password);
        model.insertUser(user);
        getUsers();
    }

    private void getUsers() {
        model.getmUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                for(int i=0;i<users.size();i++){
                    Log.d("TAG", "data" + users.get(i).name);
                }
            }
        });
    }

}
