package com.jarvis.loctracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

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
    }

    @OnClick(R.id.login_button)
    public void login(){
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();
        if(TextUtils.isEmpty(name)){
            etName.setError("Enter a Name");
        }else if(TextUtils.isEmpty(password)){
            etName.setError("Enter a Password");
        }else{
            goToHomeActivity(name,password);
        }
    }

    private void goToHomeActivity(String name, String password) {
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("password",password);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}

