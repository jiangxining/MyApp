package com.example.myapp.weichaData;

import android.util.Log;

import com.example.myapp.HomesData.HomeContract;
import com.example.myapp.base.IBaseCallBack;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class WeiChaPresenter implements WeiChaContract.WeiChaPresenter {
    private WeiChaContract.WeiChaSource mSource;
    private WeiChaContract.WeiChaView mView;
    public WeiChaPresenter() {
        mSource = new WeiChaModel(new WeiChaRemoteSource(), new WeiChaSqLocalSource());
    }

    @Override
    public void GetTabData() {
        mSource.GetTabData((LifecycleProvider) mView, new IBaseCallBack<List<DataBean>>() {
            private static final String TAG = "WeiChaPresenter";

            @Override
            public void onSuccess(List<DataBean> data) {
                if(mView != null){
                    mView.GetTabData(data);
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }


    @Override
    public void attachView(WeiChaContract.WeiChaView view) {
        mView = view;
    }

    @Override
    public void detachView(WeiChaContract.WeiChaView view) {
        mView = null;
    }
}
