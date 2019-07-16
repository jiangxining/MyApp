package com.example.myapp.weichaData;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;
import com.example.myapp.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WeiChaFragment extends BaseFragment implements WeiChaContract.WeiChaView {
    private TabLayout mTablayout;
    private WeiChaContract.WeiChaPresenter mPresenter;
    private WeChaVpAdapter weChaVpAdapter;
    private ViewPager mViewPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(new WeiChaPresenter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weichalayout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenterData();
    }

    private void getPresenterData() {

        mPresenter.GetTabData();
    }

    @Override
    public void GetTabData(List<DataBean> mTabData) {
        if (mTabData != null) {
            int size = mTabData.size();
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<BaseFragment> baseFragments = new ArrayList<>();
            for (int i = 0; i < mTabData.size(); i++) {
                WeiChaFragment gongZhunHaoChilderFragment = new WeiChaFragment();
                int id = mTabData.get(i).getId();
                arrayList.add(mTabData.get(i).getName());
                Bundle bundle = new Bundle();
                bundle.putInt("cid", id);
                gongZhunHaoChilderFragment.setArguments(bundle);
                baseFragments.add(gongZhunHaoChilderFragment);

            }
            weChaVpAdapter = new WeChaVpAdapter(getFragmentManager(), getContext(), baseFragments, arrayList);
            mViewPage.setAdapter(weChaVpAdapter);
            mTablayout.setupWithViewPager(mViewPage);
        }
    }

    @Override
    public void setPresenter(WeiChaContract.WeiChaPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
        mPresenter.GetTabData();
    }

    @Override
    public Context getContextObject() {
        return null;
    }

    private void initView(View inflate) {
        mViewPage = (ViewPager) inflate.findViewById(R.id.viewPage);
    }
}
