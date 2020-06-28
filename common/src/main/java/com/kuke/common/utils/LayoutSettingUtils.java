

package com.kuke.common.utils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 设置布局相关方法
 *
 * @date: 2018-12-03
 * @version: 1.0
 * @author: wangchenxing
 */
public class LayoutSettingUtils {
    /**
     * @param view     // 调整位置view
     * @param location // 相对布局位置常数
     * @param viewID   // 关联view的id
     */
    public static void setRelativeLayoutLocation(
            View view,
            int location,
            int viewID
    ) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.addRule(location, viewID);
    }

    public static void setLinearLayoutLocation(
            View view,
            int location
    ) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.gravity = location;
    }

    /**
     * 父布局为线性或相对布局时设置view边距
     */
    public static void setMargin(
            View view,
            int left,
            int top,
            int right,
            int bottom
    ) {
        /**
         * dp变px
         */
        left = ScreenUtils.dp2px(left);
        top = ScreenUtils.dp2px(top);
        right = ScreenUtils.dp2px(right);
        bottom = ScreenUtils.dp2px(bottom);
        if (view.getParent() == null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(
                    left,
                    top,
                    right,
                    bottom
            );
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(
                    left,
                    top,
                    right,
                    bottom
            );
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(
                    left,
                    top,
                    right,
                    bottom
            );
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置view宽高
     */
    public static void setViewWidthAndHigh(
            View view,
            int width,
            int height
    ) {
        if (view.getParent() == null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置view充满父view
     */
    public static void setViewMatch(View view) {
        LogManagementUtils.Companion.showLog("setViewMatch:" + view.getParent());
        if (view.getParent() == null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof LinearLayout) {
            LogManagementUtils.Companion.showLog("setViewMatch1:" + view.getParent());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof RelativeLayout) {
            LogManagementUtils.Companion.showLog("setViewMatch2:" + view.getParent());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            view.setLayoutParams(layoutParams);
        }

    }

    /**
     * 设置view自适应父view
     */
    public static void setViewWrap(View view) {
        if (view.getParent() == null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof LinearLayout) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            view.setLayoutParams(layoutParams);
        } else if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置view在LinearLayout中的权重
     */
    public static void setLinearLayoutWeight(View view, float weight) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = weight;
        view.setLayoutParams(layoutParams);
    }
}
