

package com.kuke.common.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

/**
 * Dialog工具类
 *
 * @date: 2018-12-12
 * @version: 1.0
 * @author: wangchenxing
 */
public class AlertDialogUtils {

    public static AlertDialog getAlertDialog(
            Context context,
            String title,
            String message,
            String positiveMessage,
            String navigativeMessage,
            boolean focus,
            DialogInterface.OnClickListener positiveListener,
            DialogInterface.OnClickListener negativeListener
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(focus);
        builder.setPositiveButton(
                positiveMessage,
                positiveListener
        );
        builder.setNegativeButton(
                navigativeMessage,
                negativeListener
        );
        return builder.create();
    }
}
