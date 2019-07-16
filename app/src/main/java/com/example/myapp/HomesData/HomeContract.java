package com.example.myapp.HomesData;

import com.example.myapp.base.IBaseCallBack;
import com.example.myapp.base.IBasePresenter;
import com.example.myapp.base.IBaseView;
import com.example.myapp.data.entity.ArticleData;
import com.example.myapp.data.entity.Banner;
import com.example.myapp.data.repositories.HomeRepository;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;


public interface HomeContract {


    public interface IHomeView extends IBaseView<IHomePresenter> {
        void onBannerReceiveData(List<Banner> banners, String msg);

        void onTopArticlesReceiveData(List<ArticleData.Article> articles, String msg);

        void onArticlesReceiveData(ArticleData articleData, String msg);

        void onLoadMoreArticleReceiveData(ArticleData articleData, String msg);
    }


    public interface IHomePresenter extends IBasePresenter<IHomeView> {

        void getBanner(@HomeRepository.HomeLoadType int type);

        void getTopArticles(@HomeRepository.HomeLoadType int type);

        void getArticles(@HomeRepository.HomeLoadType int type);

        void loadMoreArticles(int page, @HomeRepository.HomeLoadType int type);

    }


    public interface IHomeSource {

        void getBanner(LifecycleProvider provider, @HomeRepository.HomeLoadType int type, IBaseCallBack<List<Banner>> callBack);

        void getTopArticles(LifecycleProvider provider, @HomeRepository.HomeLoadType int type, IBaseCallBack<List<ArticleData.Article>> callBack);

        void getArticles(LifecycleProvider provider, @HomeRepository.HomeLoadType int type, IBaseCallBack<ArticleData> callBack);

        void loadMoreArticles(LifecycleProvider provider, @HomeRepository.HomeLoadType int type, int page, IBaseCallBack<ArticleData> callBack);

    }

    public abstract class IHomeDataSource {
        public abstract Observable<List<Banner>> getBanner();


        public abstract Observable<List<ArticleData.Article>> getTopArticles();

        public abstract Observable<ArticleData> getArticles();

        public abstract Observable<ArticleData> loadMoreArticles(int page);


        public void saveBanner(List<Banner> banners) {

        }

        public void saveTopArticles(List<ArticleData.Article> articles) {

        }

        public void saveNormalArticles(ArticleData articleData) {

        }

    }


}
