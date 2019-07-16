package com.example.myapp.GongZhunHaoFragment.contract;


import com.example.myapp.GongZhunHaoFragment.GongZhunHao;
import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.base.IBasePresenter;
import com.example.myapp.base.IBaseView;

import java.util.ArrayList;

public interface GongZhunHaoContract {
    interface Model {
            void getGongZhunHaoIm(IBaseCallBack baseCallBack);



    }

    interface View extends IBaseView<Presenter> {
        void getGongZhunHaoSuccess(ArrayList<GongZhunHao.DataBean> list);
        void getGongZhunHaoFalied(String error);


    }

    interface Presenter extends IBasePresenter<View> {
        void getGongZhunHaoIp();

    }

}
