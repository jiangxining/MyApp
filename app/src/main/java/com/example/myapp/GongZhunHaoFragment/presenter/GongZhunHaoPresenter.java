package com.example.myapp.GongZhunHaoFragment.presenter;

import com.example.myapp.GongZhunHaoFragment.GongZhunHao;
import com.example.myapp.GongZhunHaoFragment.GongZhunHaoModel;
import com.example.myapp.GongZhunHaoFragment.contract.GongZhunHaoContract;
import com.example.myapp.base.IBaseCallBack;

import java.util.ArrayList;


public class GongZhunHaoPresenter implements GongZhunHaoContract.Presenter {
    GongZhunHaoContract.View view;
    GongZhunHaoModel model;

    public GongZhunHaoPresenter() {
        model = new GongZhunHaoModel();
    }

    @Override
    public void getGongZhunHaoIp() {
       model.getGongZhunHaoIm(new IBaseCallBack() {
           @Override
           public void onSuccess(Object data) {
               view.getGongZhunHaoSuccess((ArrayList<GongZhunHao.DataBean>) data);
           }

           @Override
           public void onFail(String msg) {
            view.getGongZhunHaoFalied(msg);
           }
       });
    }


    @Override
    public void attachView(GongZhunHaoContract.View view1) {
        view=view1;
    }

    @Override
    public void detachView(GongZhunHaoContract.View view) {

    }
}
