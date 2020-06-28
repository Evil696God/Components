

package com.kuke.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.kuke.common.utils.DelayToolUtils.timeUpThen;


/**
 * view的相关拓展方法
 *
 * @date: 2018-01-12
 * @version: 1.0
 * @author: wangchenxing
 */
public class ViewExtensionUtils {
    public static void preventDuplicateClicks(final View view) {
        view.setClickable(false);
        timeUpThen(
                500L,
                () -> view.setClickable(true)
        );
    }

    public static View getViewFromXml(
            Context context,
            int resource
    ) {
        return LayoutInflater.from(context).inflate(
                resource,
                null
        );
    }

    public static View getViewFromXml(
            Context context,
            int resource,
            ViewGroup viewGroup
    ) {
        LayoutInflater from = LayoutInflater.from(context);
        View view;
        if (viewGroup == null) {
            view = from.inflate(
                    resource,
                    null
            );
            // 防止viewGroup为空时生成view宽高失效
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            view.setLayoutParams(layoutParams);
        } else {
            view = from.inflate(
                    resource,
                    viewGroup,
                    false
            );
        }
        return view;
    }

    /**
     * 设置textView单行显示
     *
     * @param textView
     */
    public static void setTextViewSingleLine(TextView textView) {
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(Gravity.CENTER_VERTICAL);
    }
}
