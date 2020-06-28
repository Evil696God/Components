

package com.kuke.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.kuke.common.value.ArgumentKey;

import java.io.File;

import androidx.core.content.FileProvider;

/**
 *
 * @date: 2018-01-21
 * @version: 1.0
 * @author: wangchenxing
 */
public class Provider {

    public static Uri getUriForFile(Context context, File file) {
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= ArgumentKey.FILE_BUILD_VERSION) {
            fileUri = getUriForFile24(context, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    public static Uri getUriForFile24(Context context, File file) {
        return FileProvider.getUriForFile(
                context,
                context.getPackageName() + ".common.utils.Provider",
                file
        );
    }

    public static void setIntentDataAndType(Context context, Intent intent, String type, File file, boolean writeAble) {
        if (Build.VERSION.SDK_INT >= ArgumentKey.FILE_BUILD_VERSION) {
            intent.setDataAndType(getUriForFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }
}