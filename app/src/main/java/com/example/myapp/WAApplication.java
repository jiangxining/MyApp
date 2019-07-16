package com.example.myapp;

import android.app.Application;

import com.example.myapp.dao.DaoMaster;
import com.example.myapp.dao.DaoSession;

public class WAApplication extends Application {
    public static Application mApplicationContext;
    public static boolean mIsLogin = false;
    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        initDao();
    }

    private void initDao(){

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"tab.db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
