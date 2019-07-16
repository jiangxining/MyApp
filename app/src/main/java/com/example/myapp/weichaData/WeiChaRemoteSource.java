package com.example.myapp.weichaData;

import com.example.myapp.WAApplication;
import com.example.myapp.base.ServerException;
import com.example.myapp.data.entity.HttpResult;
import com.example.myapp.data.okhttp.WADataService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class WeiChaRemoteSource extends WeiChaContract.IWeiChaDataSource {
    @Override
    Observable<List<DataBean>> getTabData() {

        return WADataService.getService().getTabData().flatMap(new Function<HttpResult<List<DataBean>>, ObservableSource<List<DataBean>>>() {
            @Override
            public ObservableSource<List<DataBean>> apply(HttpResult<List<DataBean>> listHttpResult) throws Exception {
                if (listHttpResult.errorCode == 0 && listHttpResult.data != null && listHttpResult.data.size() > 0) {
                    return Observable.just(listHttpResult.data);
                }
                return Observable.error(new ServerException(listHttpResult.errorMsg));
            }
        });
    }
}
