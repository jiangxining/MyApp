package com.example.myapp.GongZhunHaoFragment.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.GongZhunHaoFragment.GongZhunHao;
import com.example.myapp.GongZhunHaoFragment.adapter.GongZhunHaoVpAdapter;
import com.example.myapp.GongZhunHaoFragment.contract.GongZhunHaoContract;
import com.example.myapp.GongZhunHaoFragment.presenter.GongZhunHaoPresenter;
import com.example.myapp.R;
import com.example.myapp.base.BaseFragment;
import com.example.myapp.utils.Logger;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GongZhunHaoFragment extends BaseFragment implements GongZhunHaoContract.View {


    private GongZhunHaoPresenter gongZhunHaoPresenter;
    private TabLayout mTablayout;
    private ViewPager mViewPage;
    private GongZhunHaoVpAdapter gongZhunHaoVpAdapter;
    private ImageView mImgDate;

    public GongZhunHaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_gong_zhun_hao, container, false);
        initView(inflate);
        initmvp();
        return inflate;

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    private void initmvp() {
        gongZhunHaoPresenter = new GongZhunHaoPresenter();
        setPresenter(gongZhunHaoPresenter);
    }

    @Override
    public void getGongZhunHaoSuccess(ArrayList<GongZhunHao.DataBean> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<BaseFragment> baseFragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GongZhunHaoChilderFragment gongZhunHaoChilderFragment = new GongZhunHaoChilderFragment();
            int id = list.get(i).getId();
            arrayList.add(list.get(i).getName());
            Bundle bundle = new Bundle();
            bundle.putInt("cid", id);
            gongZhunHaoChilderFragment.setArguments(bundle);
            baseFragments.add(gongZhunHaoChilderFragment);

        }
        gongZhunHaoVpAdapter = new GongZhunHaoVpAdapter(getFragmentManager(), getContext(), baseFragments, arrayList);
        mViewPage.setAdapter(gongZhunHaoVpAdapter);
        mTablayout.setupWithViewPager(mViewPage);
    }

    @Override
    public void getGongZhunHaoFalied(String error) {
        Logger.e(error);
    }

    @Override
    public void setPresenter(GongZhunHaoContract.Presenter presenter) {
        gongZhunHaoPresenter = (GongZhunHaoPresenter) presenter;
        gongZhunHaoPresenter.attachView(this);
        gongZhunHaoPresenter.getGongZhunHaoIp();
    }

    @Override
    public Context getContextObject() {
        return null;
    }

    private void initView(View inflater) {
        mTablayout = (TabLayout) inflater.findViewById(R.id.tablayout);
        mViewPage = (ViewPager) inflater.findViewById(R.id.viewPage);
        mImgDate = (ImageView) inflater.findViewById(R.id.img_date);
        mImgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ImgDate.class));
            }
        });

    }
}
