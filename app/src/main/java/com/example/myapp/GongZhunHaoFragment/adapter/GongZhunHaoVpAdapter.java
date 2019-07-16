package com.example.myapp.GongZhunHaoFragment.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapp.base.BaseFragment;

import java.util.ArrayList;

public class GongZhunHaoVpAdapter extends FragmentPagerAdapter {


    private final Context context;
    private final ArrayList<BaseFragment> list;
    private final ArrayList<String> arrayList;

    public GongZhunHaoVpAdapter(FragmentManager fm, Context context, ArrayList<BaseFragment> list, ArrayList<String> arrayList) {
        super(fm);

        this.context = context;
        this.list = list;
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
