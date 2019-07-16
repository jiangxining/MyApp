package com.example.myapp;

import android.util.Log;

import com.example.myapp.dao.DaoSession;
import com.example.myapp.dao.DataBeanDao;
import com.example.myapp.weichaData.DataBean;

import java.util.List;


public class DbUtils {

    private static final String TAG = "DbUtils";
    private static DaoSession daoSession = WAApplication.getDaoSession();

    /**
     * 插入数据
     *
     * @param dataBean
     */
    public static void insert(DataBean dataBean) {

        if (queryOne(dataBean) == null) {

            daoSession.insert(dataBean);
        } else {
            Log.d(TAG, "insert: 已经存在");
        }
    }

    public static void delete(DataBean dataBean) {
        if (queryOne(dataBean) != null) {

            daoSession.delete(dataBean);
        } else {
            Log.d(TAG, "insert: 已经不存在");
        }
    }

    /**
     * 获取数据库全部数据
     *
     * @return
     */
    public static List<DataBean> queryAll() {

        return daoSession.loadAll(DataBean.class);
    }

    /**
     * 查询单个数据
     *
     * @param dataBean
     * @return
     */
    public static DataBean queryOne(DataBean dataBean) {
        DaoSession daoSession = WAApplication.getDaoSession();

        DataBean dBean = daoSession.queryBuilder(DataBean.class)
                .where(DataBeanDao.Properties.Id.eq(dataBean.getId()))
                .build()
                .unique();

        return dBean;
    }
}
