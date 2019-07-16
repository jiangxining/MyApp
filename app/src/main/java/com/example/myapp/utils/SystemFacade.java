package com.example.myapp.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;

import java.io.File;

/*
 * created by taofu on 2019-06-18
 **/
public class SystemFacade {


    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static float getDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }

    public static int getDensityDpi(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.densityDpi;
    }

    /**
     * dp  转 px
     * @param context
     * @param dp  传入多少dp
     * @return  返回dp 换算成px 后的值
     */
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);

    }
    public  static  long getAvailableSize()
    {

        if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            File file=Environment.getExternalStorageDirectory();
            StatFs statFs=new StatFs(file.getPath());

            //获得Sdcard上每个block的size
            long blockSize=statFs.getBlockSize();
            //获取可供程序使用的Block数量
            long blockavailable=statFs.getAvailableBlocks();
            //计算标准大小使用：1024，当然使用1000也可以
            long blockavailableTotal=blockSize*blockavailable/1024/1024;
            return blockavailableTotal;
        }else
        {
            return -1;
        }
    }

}
