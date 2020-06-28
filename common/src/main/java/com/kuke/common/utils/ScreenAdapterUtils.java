
package com.kuke.common.utils;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * 屏幕适配工具类
 *
 * @date: 2018-12-3
 * @version: 1.0
 * @author: wangchenxing
 */
public class ScreenAdapterUtils {
    /**
     * 三星pad宽度1280dp (是dp，是不px) ，SCREEN_WIDTH_DP根据不同的设计图修改，手机一般是360
     */
    private final static int SCREEN_WIDTH_DP = 120;
    public static float sNoncompatDensity;
    public static float sNoncompatScaleDensity;

    private ScreenAdapterUtils() {
    }

    public static void setCustomDensity(final Activity activity) {
        final DisplayMetrics appDisplayMetrics = activity.getResources().getDisplayMetrics();
        sNoncompatDensity = appDisplayMetrics.density;
        sNoncompatScaleDensity = appDisplayMetrics.scaledDensity;
        //监听系统改变字体的大小
        activity.registerComponentCallbacks(new ComponentCallbacks() {
            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                if (newConfig != null && newConfig.fontScale > 0) {
                    sNoncompatScaleDensity = activity.getResources().getDisplayMetrics().scaledDensity;
                }
            }

            @Override
            public void onLowMemory() {

            }
        });

        //根据参考的适配宽度 计算新的Density、ScaleDensity、DensityDpi
        float targetDensity = (float) appDisplayMetrics.widthPixels / SCREEN_WIDTH_DP;
        float targetScaleDensity = targetDensity * (sNoncompatScaleDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        //修改全局的
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        //修改当前activity
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}