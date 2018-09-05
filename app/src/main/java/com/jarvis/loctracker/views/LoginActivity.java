package com.jarvis.loctracker.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.jarvis.loctracker.LocTracker;
import com.jarvis.loctracker.R;
import com.jarvis.loctracker.utils.Sharedpreferences;
import com.jarvis.veronica.Veronica;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private Sharedpreferences mSharedpreferences;
    /*-- views --*/
    @BindView(R.id.name)
    EditText etName;
    @BindView(R.id.password)
    EditText etPassword;
    @BindView(R.id.login_button)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initialization();
    }

    private void initialization() {
        mSharedpreferences = Sharedpreferences.getUserDataObj(LoginActivity.this);
    }


    @OnClick(R.id.login_button)
    public void login() {
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            etName.setError("Enter a Name");
        } else if (TextUtils.isEmpty(password)) {
            etName.setError("Enter a Password");
        } else {
            goToHomeActivity(name, password);
        }
    }

    private void goToHomeActivity(String name, String password) {
        mSharedpreferences.setIsUserLoggedIn(true);
        mSharedpreferences.setLoggedInUserName(name);
        Veronica.userName=name;
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("password", password);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}

