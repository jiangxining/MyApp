package com.example.myapp.GongZhunHaoFragment;


import android.util.Log;


import com.example.myapp.GongZhunHaoFragment.contract.GongZhunHaoContract;
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

public class GongZhunHaoModel implements GongZhunHaoContract.Model {

    private ApiService apiService;


    @Override
    public void getGongZhunHaoIm(final IBaseCallBack baseCallBack) {
        ApiService apiService = WADataService.getService();
        Observable<GongZhunHao> gongZhunHaoList = apiService.getGongZhunHaoList();
        gongZhunHaoList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<GongZhunHao>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GongZhunHao gongZhunHao) {
                        List<GongZhunHao.DataBean> datas = gongZhunHao.getData();
                        baseCallBack.onSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: e======"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
