package com.example.myapp.GongZhunHaoFragment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.GongZhunHaoFragment.GongZhunHao;
import com.example.myapp.GongZhunHaoFragment.adapter.Adapter;
import com.example.myapp.GongZhunHaoFragment.adapter.GongZhunHaoAdapter;
import com.example.myapp.GongZhunHaoFragment.presenter.GongZhunHaoPresenter;
import com.example.myapp.R;
import com.example.myapp.data.okhttp.ApiService;
import com.example.myapp.data.okhttp.WADataService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImgDate extends AppCompatActivity {

    private static final String TAG = "ImgDate";
    private RecyclerView mRecyDate;
    private GongZhunHaoPresenter gongZhunHaoPresenter;
    private GongZhunHaoAdapter gongZhunHaoAdapter;
    private Adapter adapter;
    private Adapter adapter1;
    private Toolbar mDateToobar;
    /**
     * 返回
     */
    private TextView mTvReturn;
    /**
     * 编辑
     */
    private TextView mTvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_date);
        initView();
        initData();
    }

    private void initData() {
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
                        adapter1.updataData((ArrayList<GongZhunHao.DataBean>) datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: e======" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void initView() {
        mRecyDate = (RecyclerView) findViewById(R.id.recy_date);
        //网格布局
        mRecyDate.setLayoutManager(new GridLayoutManager(this, 3));
        adapter1 = new Adapter(this);
        mRecyDate.setAdapter(adapter1);
        mDateToobar = (Toolbar) findViewById(R.id.date_toobar);

        mTvReturn = (TextView) findViewById(R.id.tv_return);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
