

package com.kuke.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.kuke.common.value.ScreenSize;

/**
 * 吐司工具类
 *
 * @date: 2018-12-11
 * @version: 1.0
 * @author: wangchenxing
 */
public class ToastManagementUtils {
    public static final int LENGTHSHORT = Toast.LENGTH_SHORT;
    public static final int LENGTHLONG = Toast.LENGTH_LONG;

    public static void showToast(Context context, String message, int type) {
        Toast toast = Toast.makeText(context, message, type);
        TextView content = new TextView(context);
        toast.setView(content);
        toast.setGravity(Gravity.CENTER, 0, 0);
        content.setTextSize(ScreenSize.LOGIN_INPUT_SIZE);
        content.setText(message);
        toast.show();
    }
}
