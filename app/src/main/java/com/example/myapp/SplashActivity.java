package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.myapp.base.BaseActivity;
import com.example.myapp.login.LoginRegisterActivity;
import com.example.myapp.utils.SPUtils;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        if (!WAApplication.mIsLogin) {
            startActivity(new Intent(this, LoginRegisterActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void init() {
        String cookie = SPUtils.getValue(AppConstant.LoginParamsKey.SET_COOKIE_KEY);

        if (!TextUtils.isEmpty(cookie)) {
            WAApplication.mIsLogin = true;
        } else {
            WAApplication.mIsLogin = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
