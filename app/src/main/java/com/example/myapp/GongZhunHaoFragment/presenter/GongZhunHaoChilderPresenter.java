package com.example.myapp.GongZhunHaoFragment.presenter;

import com.example.myapp.GongZhunHaoFragment.GongZhunHaoChilder;
import com.example.myapp.GongZhunHaoFragment.GongZhunHaoChilderModel;
import com.example.myapp.GongZhunHaoFragment.contract.GongZhunHaoChilerContract;
import com.example.myapp.base.IBaseCallBack;

import java.util.ArrayList;


public class GongZhunHaoChilderPresenter implements GongZhunHaoChilerContract.Presenter {
    GongZhunHaoChilerContract.View view;
    GongZhunHaoChilderModel model;

    public GongZhunHaoChilderPresenter() {
        model = new GongZhunHaoChilderModel();
    }

    @Override
    public void getGongZhunHaoChilderIp() {
        int page = view.page();
        model.getGongZhunHaoChilderIm(page, new IBaseCallBack() {
            @Override
            public void onSuccess(Object data) {
                view.getGongZhunHaoChilderSuccess((ArrayList<GongZhunHaoChilder.DataBean.DatasBean>) data);
            }

            @Override
            public void onFail(String msg) {
                view.getGongZhunHaoChilderFalied(msg);
            }
        });
    }


    @Override
    public void attachView(GongZhunHaoChilerContract.View view1) {
        view=view1;
    }

    @Override
    public void detachView(GongZhunHaoChilerContract.View view) {

    }
}
