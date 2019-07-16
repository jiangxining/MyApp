package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.myapp.GongZhunHaoFragment.fragment.GongZhunHaoFragment;
import com.example.myapp.HomesData.HomeFragment;
import com.example.myapp.ZhiShiTiXi.KnowFragment;
import com.example.myapp.base.BaseActivity;
import com.example.myapp.base.BaseFragment;
import com.example.myapp.login.LoginRegisterActivity;
import com.example.myapp.utils.SPUtils;
import com.example.myapp.view.ButtonNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private NavigationView mNavView;
    private DrawerLayout mDrawerLayout;
    private ButtonNavigationView mButNaView;
    private FragmentManager manager;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        init();
        initView();
        if (!WAApplication.mIsLogin) {
            startActivity(new Intent(this, LoginRegisterActivity.class));
            finish();
        }

    }


    @SuppressLint("CutPasteId")
    private void initView() {
        Toolbar toolbar = findViewById(R.id.load_toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // NavigationView navigationView = findViewById(R.id.nav_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mToolbar = (Toolbar) findViewById(R.id.load_toolbar);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mButNaView = (ButtonNavigationView) findViewById(R.id.ButNaView);
        addFragment(manager, HomeFragment.class, R.id.main_fragment_container, null);
        mButNaView = findViewById(R.id.main_bottom_navigation);


        mButNaView.setOnTabChangedListener(new ButtonNavigationView.OnTabCheckedChangedListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int id = buttonView.getId();
                Class<? extends BaseFragment> aClass = null;
                switch (id) {
                    case R.id.radioButton: {
                        aClass = HomeFragment.class;

                        break;
                    }
                    case R.id.radioButton2: {
                        aClass=KnowFragment.class;
                        break;
                    }
                    case R.id.radioButton3: {
                        aClass = GongZhunHaoFragment.class;
                        break;
                    }
                    case R.id.radioButton4: {
                        aClass=KnowFragment.class;
                        break;
                    }

                }
                addFragment(getSupportFragmentManager(), aClass, R.id.main_fragment_container, null);
            }
        });

        mFab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
}
