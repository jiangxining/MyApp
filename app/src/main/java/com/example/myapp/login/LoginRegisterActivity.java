package com.example.myapp.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapp.base.BaseActivity;


public class LoginRegisterActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.example.myapp.R.layout.activity_login_register);
        addFragment(getSupportFragmentManager(), LoginFragment.class, com.example.myapp.R.id.login_fragment_container, null);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
