package com.example.myapp.HomesData;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.base.BaseFragment;
import com.example.myapp.data.entity.ArticleData;
import com.example.myapp.data.entity.Banner;
import com.example.myapp.data.repositories.HomeRepository;
import com.example.myapp.utils.Logger;
import com.example.myapp.view.LoadingPage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView {

    private HomeContract.IHomePresenter mPresenter;
    private RecyclerView mRvArticleList;
    private HomeArticleAdapter mHomeArticleAdapter;
    private SmartRefreshLayout mSmartRefreshLayout;
    private List<Banner> mBanners;
    private List<ArticleData.Article> mTopArticles;
    private List<ArticleData.Article> mNormalArticles;
    private List<ArticleData.Article> mAdapterArticles;

    private HashMap<String, Object> mResponseCounter = new HashMap<>(3);

    //private int mResponseCount;
    private int mPage = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(new HomePresenter());
    }

    @Override
    protected boolean isNeedToAddBackStack() {
        return false;

    }

    @Override
    public boolean isNeedAnimation() {
        return false;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvArticleList = view.findViewById(R.id.home_rv_article_list);
        mSmartRefreshLayout = view.findViewById(R.id.home_smart_refresh_layout);

        mRvArticleList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Logger.d("%s onScrolled", getLogTag());
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Logger.d("%s 上拉加载更多 ，当前已经展示到第 %s 页", getTag(), mPage);
                mPresenter.loadMoreArticles(mPage, HomeRepository.HomeLoadType.LOAD_TYPE_MORE);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Logger.d("%s 下拉刷新 当前已经展示了 %s 页", getTag(), mPage);

                getOrRefresh(HomeRepository.HomeLoadType.LOAD_TYPE_REFRESH);
            }
        });

        Logger.d("%s 第一次进来，显示loading 页面，开始获取banner,获取置顶文章，获取普通文章", getLogTag());
        showLoadingPage(LoadingPage.MODE_2);
        getOrRefresh(HomeRepository.HomeLoadType.LOAD_TYPE_LOAD);



    }

    private void getOrRefresh(@HomeRepository.HomeLoadType int type) {
        mPresenter.getBanner(type);
        mPresenter.getArticles(type);
        mPresenter.getTopArticles(type);


        //mSmartRefreshLayout.autoRefresh(5 * 1000);

    }


    /**
     * 如果 adapter 还没没new ，那么new 一个 并设置给 recycler view
     * 如果已经存在，这返回之前的。
     *
     * @return
     */
    private HomeArticleAdapter getOrCreateAdapter() {
        if (mHomeArticleAdapter == null) {
            Logger.d("%s HomeAdapter 还没有创建，因此需要new 一个新的，并设置给 recycler view", getLogTag());
            mHomeArticleAdapter = new HomeArticleAdapter();
            mRvArticleList.setAdapter(mHomeArticleAdapter);
            mRvArticleList.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvArticleList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        } else {
            Logger.d("%s HomeAdapter 已经存在，因此直接返回已有的 adpater", getTag());
        }

        return mHomeArticleAdapter;
    }


    @Override
    public void onBannerReceiveData(List<Banner> banners, String msg) {

        // 回来了先登记，累加器加一
        //mResponseCount++;
        mResponseCounter.put(Banner.class.getSimpleName(), null);


        if (mBanners == null) {
            mBanners = banners;
        } else {
            if (banners != null && banners.size() > 0) {
                mBanners = banners;
            }
        }

        Logger.d("%s Banner 加载回来了，是第 %s 个回来的。banner 个数等于 %s 错误消息 = %s ", getLogTag(), mResponseCounter.size(), Logger.getCollectionSize(mBanners), msg);

        handResponseData();


    }

    @Override
    public void onTopArticlesReceiveData(List<ArticleData.Article> articles, String msg) {
        // 回来了先登记，累加器加一
        // mResponseCount++;
        mResponseCounter.put(ArticleData.Article.class.getSimpleName(), null);
        if (mTopArticles == null) {
            mTopArticles = articles;
        } else {
            if (articles != null && articles.size() > 0) {
                mTopArticles = articles;
            }
        }


        Logger.d("%s 置顶加载回来了，是第 %s 个回来的。置顶文章个数等于 %s 错误消息 = %s ", getLogTag(), mResponseCounter.size(), Logger.getCollectionSize(mTopArticles), msg);

        handResponseData();

    }

    @Override
    public void onArticlesReceiveData(ArticleData articleData, String msg) {

        // 回来了先登记，累加器加一
        //mResponseCount++;
        mResponseCounter.put(ArticleData.class.getSimpleName(), null);

        if (mNormalArticles == null) {
            if (articleData != null) {
                mNormalArticles = articleData.getDatas();
                mPage = articleData.getCurPage();
            }

        } else {
            if (articleData != null && articleData.getDatas() != null && articleData.getDatas().size() > 0) {
                mNormalArticles = articleData.getDatas();
                mPage = articleData.getCurPage();
            }
        }

        Logger.d("%s 普通文章加载回来了，是第 %s 个回来的。普通文章个数等于 %s 错误消息 = %s", getLogTag(), mResponseCounter.size(), Logger.getCollectionSize(mNormalArticles), msg);
        handResponseData();

        if(articleData != null && articleData.getCurPage() == -1){
            mSmartRefreshLayout.autoRefresh(1000);
        }


    }

    @Override
    public void onLoadMoreArticleReceiveData(ArticleData articleData, String msg) {
        Logger.d("%s 在更多数据回来了。不管是否成功与失败都要调用 SmartRefreshLayout.finishLoadMore() 关闭加载更多动画", getLogTag());
        mSmartRefreshLayout.finishLoadMore();
        if (articleData != null) {
            Logger.d("%s 加载更多有数据，不为空，因此为adapter 里面追加，并且调用 adapter 的局部刷新，之刷新新增的item", getLogTag());
            mPage = articleData.getCurPage();
            HomeArticleAdapter adapter = getOrCreateAdapter();
            adapter.addArticle(articleData.getDatas());
        } else {
            Logger.d("%s 加载更多的请求没有数据回来，%s", getLogTag(), msg);
            //TODO  没有更多 或者加载失败
            showToast(msg);
        }
    }

    @Override
    public void setPresenter(HomeContract.IHomePresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Context getContextObject() {
        return null;
    }


    private void handResponseData() {
        /**
         * 由于第一个页面有的数据是由三个请求组成的，因此每个请求会来后都要判断一下自己
         * 是不是最后一个请求回来的，也不管自己是佛请求成功和失败，都要判断，如果自己是
         * 最后一个回来的，那么就要把数据展现在页面上。如果不是最后一个请求回来的那就不管，
         * 等着后面请求回来，让后面的请求回来的去做数据展现
         */
        // 如果等于3，表示自己是最后一个请求回来的。

        if (mResponseCounter.size() == 3) {
            Logger.d("%s 当前求情是最后一个回来的 因此需要让数据显示在 recycler view 上，并且把 mResponseCount 设置为 0 ", getLogTag());
            // 恢复城默认值，表示所有请求都回来了，之所以要恢复为默认值，是因为
            //下一次下拉刷新又得去请求三个接口的数据。
            mResponseCounter.clear();

            HomeArticleAdapter adapter = getOrCreateAdapter();


            if (adapter.getItemCount() != 0) {
                Logger.d("%s 本次为刷新求情，不是第一次进来时加载，因此不管成功还是失败都需要 调用 SmartRefreshLayout.finishRefresh() 完成刷新", getLogTag());
                mSmartRefreshLayout.finishRefresh();
            }

            Logger.d("%s 创建一个临时的 ArrayList mAdapterBannerList 用来装请求回来banner", getLogTag());
            List<Banner> adapterBannerList = new ArrayList<>();

            if (mBanners != null && mBanners.size() > 0) {
                Logger.d("%s 请求回来的置banner 不等于空，把banner 添加到 adapterBannerList 中,并清空 mBanners ", getLogTag());
                adapterBannerList.addAll(mBanners);
                mBanners.clear();
                mBanners = null;
            }else{
                Logger.d("%s 请求回来的banner数是空的，不需要把banner入加入到到临时 adapterBannerList 中", getLogTag());
            }

            Logger.d("%s 创建一个临时的 ArrayList adapterList 用来装请求回来的置顶文章和普通文章，置顶文章放在前面，普通文章放在后面", getLogTag());
            ArrayList<ArticleData.Article> adapterList = new ArrayList<>();

            // 如果有置顶文章，把置顶文章加入到 adapterList
            if (mTopArticles != null && mTopArticles.size() > 0) {
                Logger.d("%s 请求回来的置顶文章数不等于空，把置顶文章加入到临时 adapterList 中,并清空 mTopArticles ", getLogTag());
                adapterList.addAll(mTopArticles);
                mTopArticles.clear();
                mTopArticles = null;
            } else {
                Logger.d("%s 请求回来的置顶文章数是空的，不需要把把置顶文章加入到临时 adapterList 中", getLogTag());
            }

            // 如果有不普通文章，把普通文章加入到adapterList
            if (mNormalArticles != null && mNormalArticles.size() > 0) {
                Logger.d("%s 请求回来的普通文章数不等于空，把普通文章加入到临时 adapterList 中,并清空 mNormalArticles ", getLogTag());
                adapterList.addAll(mNormalArticles);
                mNormalArticles.clear();
                mNormalArticles = null;
            } else {
                Logger.d("%s 请求回来的普通文章数是空的，不需要把普通文章加入到临时 adapterList 中", getLogTag());
            }


            // 如果banner 也为空，置顶文章和普通文章都为空，那么加载失败。
            if ((adapterBannerList.size() == 0) && adapterList.size() == 0) {
                // 如果adapter get count == 0 说明之前没有显示过任何数据，说明不是刷新//
                // 不是刷新那么就要显示错误页面
                if (adapter.getItemCount() == 0) {
                    Logger.d("%s 请求回来的banner ，指定文章，普通文章都是空的，又是第一加载数据（数据还没有请求成功过），因此显示错也页面", getLogTag());
                    onError("加载失败，点击请重试", new LoadingPage.OnReloadListener() {
                        @Override
                        public void reload() {
                            getOrRefresh(HomeRepository.HomeLoadType.LOAD_TYPE_LOAD);
                        }
                    });
                } else {
                    Logger.d("%s 刷新回来的 的banner ，指定文章，普通文章都是空的，由于当前页面已经展示了之前的数据，只是刷新失败了，因此展示不做任何修改", getLogTag());
                    // 如果是刷新，那么就意味着刷新回来的数据都是空，因此就不做任何处理
                    //TODO ,以后可以在一个刷新失败
                }
            } else {

                // 不是刷新，并且有数据回来
                if (adapter.getItemCount() == 0) {
                    // 关闭loading 页面。
                    Logger.d("%s 进入主页第一请求加载数据，banner，置顶，普通文章至少以一个是有数据的，因此需要让界面显示数据,并且关掉loading页面", getLogTag());
                    dismissLoadingPage();
                }
                // 设置banner。
                Logger.d("%s 给 Adapter 设置 banner ，置顶文章和普通文章，并通知adapter 全部刷新", getLogTag());
                mHomeArticleAdapter.setBanner(adapterBannerList);
                mHomeArticleAdapter.setArticle(adapterList);
                mHomeArticleAdapter.notifyDataSetChanged();

            }

        } else {
            Logger.d("%s 由于本次请求不是最后一个回来的，后面还有请求没有回来，因此不做任何处理，等待后面的请求回来，让后面的请求处理数据显示", getLogTag());
        }
    }


}
