package com.example.myapp.weichaData;

import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.base.IBasePresenter;
import com.example.myapp.base.IBaseView;
import com.example.myapp.data.entity.Banner;
import com.trello.rxlifecycle2.LifecycleProvider;


import java.util.List;

import io.reactivex.Observable;

public interface WeiChaContract  {
    interface  WeiChaView extends IBaseView<WeiChaPresenter>{
        void GetTabData(List<DataBean> mTabData);
    }
    interface  WeiChaPresenter extends IBasePresenter<WeiChaView> {
        void GetTabData();
    }
    interface  WeiChaSource  {
        void GetTabData(LifecycleProvider provider, IBaseCallBack<List<DataBean>> callBack);
    }

    abstract class IWeiChaDataSource {
        abstract Observable<List<DataBean>> getTabData();
        public void sqlDataTab(List<DataBean> mDataTab) {

        }
    }
}
