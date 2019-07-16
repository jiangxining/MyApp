package com.example.myapp.GongZhunHaoFragment.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.GongZhunHaoFragment.DaoHangXiangQing;
import com.example.myapp.GongZhunHaoFragment.adapter.GongZhunHaoAdapter;
import com.example.myapp.GongZhunHaoFragment.GongZhunHaoChilder;
import com.example.myapp.GongZhunHaoFragment.presenter.GongZhunHaoChilderPresenter;
import com.example.myapp.GongZhunHaoFragment.contract.GongZhunHaoChilerContract;
import com.example.myapp.R;
import com.example.myapp.base.BaseFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GongZhunHaoChilderFragment extends BaseFragment implements GongZhunHaoChilerContract.View {


    private static final String TAG = "B";
    private static RecyclerView mRecy;
    private GongZhunHaoAdapter gongZhunHaoAdapter;
    private GongZhunHaoChilderPresenter presenter1;
    private int cid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_gong_zhun_hao_childer, container, false);
        initView(inflate);
        initMVP();
        return inflate;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public static void oncliok() {
        mRecy.smoothScrollToPosition(0);
    }

    private void initMVP() {
        presenter1 = new GongZhunHaoChilderPresenter();
        setPresenter(presenter1);
    }

    private void initView(View inflate) {
        mRecy = (RecyclerView) inflate.findViewById(R.id.recy);
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecy.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        gongZhunHaoAdapter = new GongZhunHaoAdapter(getContext());
        mRecy.setAdapter(gongZhunHaoAdapter);
        gongZhunHaoAdapter.setOnItemClick(new GongZhunHaoAdapter.OnItemClick() {

            private String link;
            private Intent intent;

            @Override
            public void onClick(GongZhunHaoChilder.DataBean.DatasBean gongZhunHaoChilder) {
                intent = new Intent(getContext(), DaoHangXiangQing.class);
               link = gongZhunHaoChilder.getLink();
                intent.putExtra("link",link);
                startActivity(intent);
            }
        });
    }
    @Override
    public void getGongZhunHaoChilderSuccess(ArrayList<GongZhunHaoChilder.DataBean.DatasBean> list) {
        gongZhunHaoAdapter.updataData(list);
    }
    @Override
    public void getGongZhunHaoChilderFalied(String error) {
        Log.e(TAG, "getGongZhunHaoChilderFalied: "+error );
    }
    @Override
    public void setPresenter(GongZhunHaoChilerContract.Presenter presenter) {
        Bundle arguments = getArguments();
        cid = arguments.getInt("cid");

        presenter1= (GongZhunHaoChilderPresenter) presenter;
        presenter1.attachView(this);
        presenter1.getGongZhunHaoChilderIp();
    }

    @Override
    public Context getContextObject() {
        return null;
    }

    @Override
    public int page() {
        return cid;
    }
}
