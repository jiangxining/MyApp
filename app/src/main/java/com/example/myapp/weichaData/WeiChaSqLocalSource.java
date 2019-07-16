package com.example.myapp.weichaData;

import android.os.Looper;
import com.example.myapp.DbUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeiChaSqLocalSource extends WeiChaContract.IWeiChaDataSource {
    @Override
    Observable<List<DataBean>> getTabData() {
        return Observable.create(new ObservableOnSubscribe<List<DataBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<DataBean>> emitter) throws Exception {
                List<DataBean> list = DbUtils.queryAll();
                if (list != null && list.size() > 0) {
                    emitter.onNext(list);
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public void sqlDataTab(List<DataBean> mDataTab) {
        if (mDataTab == null || mDataTab.size() == 0) {
            return;
        }
        saveDataBean(mDataTab);
    }
    private void saveDataBean(Object o) {
        // 如果是在主线程调用该方法，那么得开启新的现场去保存
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Observable.just(o).doOnNext(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    List mDataTab = (List) o;
                    if (mDataTab != null && mDataTab.size() > 0) {
                        for (Object dataBean : mDataTab) {
                            DbUtils.insert((DataBean) dataBean);
                        }
                    }
                }
            }).subscribeOn(Schedulers.io()).subscribe();
        } else {
            // 如果是在工作线程中，直接保存
            List mDataTab = (List) o;
            for (Object dataBean : mDataTab) {
                DbUtils.insert((DataBean) dataBean);
            }
        }
    }

}
