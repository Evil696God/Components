

package com.kuke.common.utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 *
 * @date: 2018-12-21
 * @version: 1.0
 * @author: wangchenxing
 */
public class PopupWindowUtils {
    public static void setBackgroundAlpha(
            Activity activity,
            float bgAlpha
    ) {
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }
}
