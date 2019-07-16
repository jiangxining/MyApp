package com.example.myapp.data.okhttp;


import com.example.myapp.AppConstant;
import com.example.myapp.GongZhunHaoFragment.GongZhunHao;
import com.example.myapp.GongZhunHaoFragment.GongZhunHaoChilder;
import com.example.myapp.data.entity.ArticleData;
import com.example.myapp.data.entity.Banner;
import com.example.myapp.data.entity.HttpResult;
import com.example.myapp.data.entity.User;
import com.example.myapp.weichaData.DataBean;

import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 * created by taofu on 2019-06-11
 **/
public interface ApiService {

    @POST(AppConstant.WEB_SITE_REGISTER)
    @FormUrlEncoded
    Observable<HttpResult<User>> register(@FieldMap Map<String, String> params);

    @POST(AppConstant.WEB_SITE_LOGIN)
    @FormUrlEncoded
    Observable<HttpResult<User>> login(@FieldMap Map<String, String> params);

    @GET("banner/json")
    Observable<HttpResult<List<Banner>>> getBanners();

    @GET("article/top/json")
    Observable<HttpResult<List<ArticleData.Article>>> getTopArticles();

    @GET("article/list/{page}/json")
    Observable<HttpResult<ArticleData>> getArticleData(@Path("page") int page);

    //https://wanandroid.com/
    @GET("wxarticle/chapters/json")
    Observable<HttpResult<List<DataBean>>> getTabData();

    @GET("wxarticle/chapters/json")
    Observable<GongZhunHao> getGongZhunHaoList();

    @GET("wxarticle/list/{page}/1/json")
    Observable<GongZhunHaoChilder> getGongZhunHaoChilderList(@Path("page") int page);
}
