package com.example.myapp.GongZhunHaoFragment;


import android.util.Log;


import com.example.myapp.GongZhunHaoFragment.contract.GongZhunHaoChilerContract;
import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.data.okhttp.ApiService;
import com.example.myapp.data.okhttp.WADataService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class GongZhunHaoChilderModel implements GongZhunHaoChilerContract.Model {


    @Override
    public void getGongZhunHaoChilderIm(int page,final IBaseCallBack baseCallBack) {
        ApiService apiService = WADataService.getService();
        Observable<GongZhunHaoChilder> gongZhunHaoChilderList = apiService.getGongZhunHaoChilderList(page);
        gongZhunHaoChilderList.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GongZhunHaoChilder>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GongZhunHaoChilder gongZhunHaoChilder) {
                        List<GongZhunHaoChilder.DataBean.DatasBean> datas = gongZhunHaoChilder.getData().getDatas();
                        baseCallBack.onSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: e===="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
