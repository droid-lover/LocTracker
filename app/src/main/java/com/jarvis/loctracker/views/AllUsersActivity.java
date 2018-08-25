package com.jarvis.loctracker.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jarvis.loctracker.R;

import butterknife.ButterKnife;

public class AllUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        ButterKnife.bind(this);
    }
}
