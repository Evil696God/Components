package com.kuke.common.utils;

import android.content.Context;
import android.content.res.Resources;

import static com.kuke.common.utils.ScreenAdapterUtils.sNoncompatDensity;
import static com.kuke.common.utils.ScreenAdapterUtils.sNoncompatScaleDensity;

/**
 * 屏幕像素转化工具类
 *
 * @date: 2018-12-03
 * @version: 1.0
 * @author: wangchenxing
 */
public class ScreenUtils {
    /**
     * @dec dp转px
     */
    public static int dp2px(
            Context context,
            float dipValue
    ) {
        final float scale = context.getResources().getDisplayMetrics().density;
        if (sNoncompatDensity == 0) {
            return (int) (dipValue * scale + 0.5f);
        } else {
            return (int) (sNoncompatDensity * scale + 0.5f);
        }
    }


    /**
     * @dec px转dp
     */
    public static int px2dp(
            Context context,
            float pxValue
    ) {
        final float scale = context.getResources().getDisplayMetrics().density;
        if (sNoncompatDensity == 0) {
            return (int) (pxValue / scale + 0.5f);
        } else {
            return (int) (sNoncompatDensity / scale + 0.5f);
        }
    }

    /**
     * @dec px转sp
     */
    public static int px2sp(Context context, float pxValue) {
//        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (pxValue / fontScale + 0.5f);

        if (sNoncompatScaleDensity == 0) {
            return (int) (pxValue / Resources.getSystem().getDisplayMetrics().scaledDensity + 0.5f);
        } else {
            return (int) (pxValue / sNoncompatScaleDensity + 0.5f);
        }
    }


    /**
     * @dec sp转px
     */
    public static int sp2px(
            Context context,
            float spValue
    ) {
        if (sNoncompatScaleDensity == 0) {
            return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
        } else {
            return (int) (spValue * sNoncompatScaleDensity + 0.5f);
        }

    }

    /**
     * @dec 另一种sp转px方法
     */
    public static int sp2px(int sp) {
        if (sNoncompatScaleDensity == 0) {
            int px = (int) (Resources.getSystem().getDisplayMetrics().density * sp);
            return (int) (px / Resources.getSystem().getDisplayMetrics().scaledDensity);
        } else {
            int px = (int) (sNoncompatDensity * sp);
            return (int) (px / sNoncompatScaleDensity);
        }
    }

    /**
     * @dec 另一种dp转px方法
     */
    public static int dp2px(int dp) {
        int dpFromUIPX = (int) (dp * 1.018f);
        if (sNoncompatDensity == 0) {
            return (int) (dpFromUIPX * Resources.getSystem().getDisplayMetrics().density);
        } else {
            return (int) (dpFromUIPX * sNoncompatDensity);
        }
    }
}
