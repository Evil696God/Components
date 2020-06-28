

package com.kuke.common.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;


import com.kuke.common.value.ArgumentKey;

import java.io.File;
import java.io.IOException;

import static com.kuke.common.value.AppReturnedConstant.PHOTO_REQUEST_CUT;
import static com.kuke.common.value.AppReturnedConstant.PICK_SYSTEM_PHOTO;
import static com.kuke.common.value.AppReturnedConstant.PICK_TACK_ALBUM;
import static com.kuke.common.value.LocalAddress.PHOTO_CROP_FILE_NAME;
import static com.kuke.common.value.LocalAddress.PHOTO_FILE_NAME;

/**
 * 相机相关的工具类
 *
 * @date: 2018-12-05
 * @version: 1.0
 * @author: wangchenxing
 */

public class CameraUtils {

    public static void selectImage(Activity context) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                null
        );
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
        );
        context.startActivityForResult(
                intent,
                PICK_TACK_ALBUM
        );
    }

    public static void initCamera(Activity context) {

        // 指定调用相机拍照后照片的储存路径
        File file = new File(PHOTO_FILE_NAME);
        // 获取Uri:适配7.0
        Uri imgUri = Provider.getUriForFile(
                context,
                file
        );

        // 跳转相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 拍照才需要传入URI
        intent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                imgUri
        );
        context.startActivityForResult(
                intent,
                PICK_SYSTEM_PHOTO
        );
    }

    public static String getRealFilePath(
            final Context context,
            final Uri uri
    ) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{
                            MediaStore.Images.ImageColumns.DATA},
                    null,
                    null,
                    null
            );
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static void cropImage(
            Uri inputUri,
            Activity context,
            int type
    ) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (type == PICK_SYSTEM_PHOTO) {
            // tempFile需要与拍照openCamera传入的文件一致
            File tempFile = new File(PHOTO_FILE_NAME);
            if (Build.VERSION.SDK_INT >= ArgumentKey.CAMERA_BUILD_VERSION) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            inputUri = Provider.getUriForFile(
                    context,
                    tempFile
            );
        }


        // 设置裁剪之后的图片路径文件
        File cutFile = new File(PHOTO_CROP_FILE_NAME);
        if (cutFile.exists()) { cutFile.delete(); }

        try {
            cutFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Uri outputUri = Uri.fromFile(cutFile);

        intent.putExtra("crop", true);

        intent.putExtra("aspectX", 1);

        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 708);

        intent.putExtra("outputY", 708);

        // 是否保留比例
        intent.putExtra("scale", true);

        // true返回bitmap，false返回URI
        intent.putExtra("return-data", false);

        if (inputUri != null) {
            intent.setDataAndType(
                    inputUri,
                    "image/*"
            );
        }
        // 将URI指向相应的file
        if (outputUri != null) {
            intent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    outputUri
            );
        }
        // 是否取消人脸识别功能
        intent.putExtra("noFaceDetection", false);

        // 输出格式，一般设为Bitmap格式：Bitmap.CompressFormat.JPEG.toString()
        intent.putExtra(
                "outputFormat",
                Bitmap.CompressFormat.JPEG.toString()
        );
        context.startActivityForResult(
                intent,
                PHOTO_REQUEST_CUT
        );
    }
}
