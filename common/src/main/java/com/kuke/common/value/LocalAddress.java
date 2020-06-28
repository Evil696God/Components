

package com.kuke.common.value;

import android.os.Environment;

import java.io.File;

/**
 *
 * @date: 2018-12-27
 * @version: 1.0
 * @author: wangchenxing
 */
public class LocalAddress {

    /**
     * 根目录
     */
    public static final String ROOTPATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "iot";
    /**
     * 上传图片存储路径
     */
    public static final String PHOTOPATH = ROOTPATH + File.separator + "photo";
    /**
     * 上传图片名称
     */
    public static final String PHOTO_FILE_NAME = PHOTOPATH + File.separator + "iot.png";
    /**
     * 裁剪的图片
     */
    public static final String PHOTO_CROP_FILE_NAME = PHOTOPATH + File.separator + "iot_crop.png";

    /**
     * 下载apk存储路径
     */
    public static final String APKPATH = ROOTPATH + File.separator + "apk";
    /**
     * 下载apk存储路径
     */
    public static final String STORE_APK_PATH = APKPATH + File.separator + "scm.apk";
}
