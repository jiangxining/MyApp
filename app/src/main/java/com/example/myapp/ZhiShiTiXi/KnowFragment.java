package com.example.myapp.ZhiShiTiXi;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.R;
import com.example.myapp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowFragment extends BaseFragment {


    public KnowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_know, container, false);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}
