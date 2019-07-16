package com.example.myapp.GongZhunHaoFragment.contract;

import com.example.myapp.GongZhunHaoFragment.GongZhunHaoChilder;
import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.base.IBasePresenter;
import com.example.myapp.base.IBaseView;

import java.util.ArrayList;

public interface GongZhunHaoChilerContract {
    interface Model {
            void getGongZhunHaoChilderIm(int page, IBaseCallBack baseCallBack);



    }

    interface View extends IBaseView<Presenter> {
        void getGongZhunHaoChilderSuccess(ArrayList<GongZhunHaoChilder.DataBean.DatasBean> list);
        void getGongZhunHaoChilderFalied(String error);
        int page();

    }

    interface Presenter extends IBasePresenter<View> {
        void getGongZhunHaoChilderIp();

    }

}
