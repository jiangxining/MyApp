package com.example.myapp.weichaData;

import com.example.myapp.HomesData.HomeContract;
import com.example.myapp.base.BaseRepository;
import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.data.entity.Banner;
import com.example.myapp.data.entity.HttpResult;
import com.example.myapp.data.okhttp.ApiService;
import com.example.myapp.data.okhttp.WADataService;
import com.example.myapp.data.repositories.HomeRepository;
import com.example.myapp.utils.ObjectUtils;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WeiChaModel extends BaseRepository implements WeiChaContract.WeiChaSource {

    private WeiChaContract.IWeiChaDataSource mRemote;
    private WeiChaContract.IWeiChaDataSource mLocal;

    public WeiChaModel(WeiChaContract.IWeiChaDataSource remote, WeiChaContract.IWeiChaDataSource local) {
        ObjectUtils.requireNonNull(remote, "remote source is null");
        this.mRemote = remote;
        this.mLocal = local;
    }

    @Override
    public void GetTabData(LifecycleProvider provider, final IBaseCallBack<List<DataBean>> callBack) {
        Observable<List<DataBean>> localObservable = null;
        Observable<List<DataBean>> remoteObservable = mRemote.getTabData();
        if (mLocal != null) {
            localObservable = mLocal.getTabData();
        }
        process(provider, localObservable, remoteObservable, new Consumer<List<DataBean>>() {
            @Override
            public void accept(List<DataBean> mDataBean) throws Exception {
                if (mLocal != null && mDataBean != null && mDataBean.size() > 0) {
                    mLocal.sqlDataTab(mDataBean);
                }
            }
        }, callBack);

        ApiService service = WADataService.getService();
        Observable<HttpResult<List<DataBean>>> tabData = service.getTabData();
        tabData.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<HttpResult<List<DataBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<List<DataBean>> listHttpResult) {
                        List<DataBean> data = listHttpResult.data;
                        callBack.onSuccess(data);

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private <R> void process(LifecycleProvider provider, Observable<R> localObservable, Observable<R> remoteObservable, Consumer<R> doOnNext, IBaseCallBack<R> callBack) {

        if (mLocal != null && doOnNext != null) {
            // 服务器数据如果加载成功，不管是第一次还是刷新，再回调各 presenter 时先保存到本地，

            remoteObservable = remoteObservable.doOnNext(doOnNext);

        }

        // 通过 concat 连接操作符，实现 本地有数据先读本地，等本地读取完成后，再读取服务器，包装本地一定发发再服务器之前。
        // Observable<R> resultObservable = localObservable == null ? remoteObservable : Observable.concat(localObservable, remoteObservable);

        Observable<R> resultObservable = null;

        if (localObservable == null) {
            resultObservable = remoteObservable;
        } else {
            resultObservable = Observable.concat(localObservable, remoteObservable).firstOrError().toObservable();
        }
        observerNoMap(provider, resultObservable, callBack);
    }

}
