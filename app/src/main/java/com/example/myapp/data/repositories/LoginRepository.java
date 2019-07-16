package com.example.myapp.data.repositories;

import android.text.TextUtils;

import com.example.myapp.base.BaseRepository;
import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.data.entity.HttpResult;
import com.example.myapp.data.entity.User;
import com.example.myapp.data.okhttp.ApiService;
import com.example.myapp.data.okhttp.WADataService;
import com.example.myapp.login.LoginContract;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.w3c.dom.Text;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


/*
 * created by taofu on 2019-06-11
 **/
public class LoginRepository extends BaseRepository implements LoginContract.ILoginSource {

    private ApiService mApiService;


    public LoginRepository() {
        mApiService = WADataService.getService();
    }

    @Override
    public void register(LifecycleProvider provider, Map<String, String> params, final IBaseCallBack<User> callBack) {


        request(provider, mApiService.register(params), callBack);
    }

    @Override
    public void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack) {
        request(provider, mApiService.login(params), callBack);
    }


    private void request(LifecycleProvider provider, Observable<HttpResult<User>> observable, IBaseCallBack<User> callBack) {


        observer(provider, observable, new Function<HttpResult<User>, ObservableSource<User>>() {
            @Override
            public ObservableSource<User> apply(HttpResult<User> userHttpResult) throws Exception {
                if (userHttpResult.errorCode == 0 && userHttpResult.data != null) {
                    return Observable.just(userHttpResult.data);
                }
                String msg = "服务器返回数据为空";
                if (!TextUtils.isEmpty(userHttpResult.errorMsg)) {
                    msg = userHttpResult.errorMsg;
                }
                return Observable.error(new NullPointerException(msg));
            }
        }, callBack);
    }
}
