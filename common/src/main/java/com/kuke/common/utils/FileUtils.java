

package com.kuke.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import com.kuke.common.BuildConfig;
import com.kuke.common.application.KuKeyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @date: 2020-04-01
 * @version: 1.0
 * @author: wangchenxing
 */
public class FileUtils {
    /**
     * 保存Image的目录名
     */
    public final static String FOLDER_IMAGE = "/image";
    /**
     * 保存logger的目录名
     */
    public final static String FOLDER_LOGGER = "/log";
    /**
     * apk文件存储目录
     */
    public final static String FOLDER_APK = "/update";
    /**
     * db 文件存储目录
     */
    public final static String FOLDER_DB = "/db";
    /**
     * APP 文件保存根目录
     */
    private final static String FOLDER_ROOT = "/" + KuKeyApplication.getInstance().getAppNamePath();
    /**
     * sd卡的根目录
     */
    private static String mSdRootPath = Environment.getExternalStorageDirectory().getPath();
    /**
     * 手机的缓存根目录
     */
    private static String mDataRootPath = null;

    /**
     * 旧版本apk保留版本个数
     */
    private static long OLD_VERSION_APK_NUMBER = 1;

    /**
     * APK名称
     */
    public final static String APK_NAME = "kuke";

    public static String getDataRootPath(Context context) {
        if (mDataRootPath == null) {
            mDataRootPath = context.getFilesDir().getAbsolutePath();
        }
        return mDataRootPath;
    }

    /**
     * 初始化文件目录
     */
    public static void initialFileDirectory(Context context) {
        String loggerPath = getStorageLoggerDirectory(context) + File.separator + DateUtil.dateFormat(new Date(System.currentTimeMillis()), "yyyy-MM-dd");
        String imagePath = getStorageImageDirectory(context);
        String updatePath = getStorageUpdateDirectory(context);

        File logDir = new File(loggerPath);
        if (!logDir.exists()) {
            try {
                //按照指定的路径创建文件夹
                logDir.mkdirs();
            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
        File imageDir = new File(imagePath);
        if (!imageDir.exists()) {
            try {
                //按照指定的路径创建文件夹
                imageDir.mkdirs();
            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
        File updateDir = new File(updatePath);
        if (!updateDir.exists()) {
            try {
                //按照指定的路径创建文件夹
                updateDir.mkdirs();
            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
    }

    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    public static boolean copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * 要删除的文件夹的所在位置
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 清理旧apk文件
     */
    public static void cleanupApk(Context context) {
        File errorFile = null;
        try {
            // 获取log文件夹下所有文件
            File[] fileList = new File(FileUtils.getStorageUpdateDirectory(context)).listFiles();
            for (File file : fileList) {
                errorFile = file;
                String name = file.getName();
                LogManagementUtils.Companion.e("cleanupApk:" + name);
                // 切除文件名后缀
                String[] tempStrings = name.split(".apk");
                if (tempStrings.length >= 1) {
                    // 切除文件名前缀
                    tempStrings = tempStrings[0].split(APK_NAME);
                    if (tempStrings.length >= 1) {
                        long apkVersion = getApkVersion(tempStrings[0]);
                        long nowApkVersion = getApkVersion(BuildConfig.VERSION_NAME);
                        if (nowApkVersion - apkVersion > OLD_VERSION_APK_NUMBER || nowApkVersion - apkVersion < -OLD_VERSION_APK_NUMBER) {
                            FileUtils.deleteFile(file);
                        }
                    } else {
                        FileUtils.deleteFile(file);
                    }
                } else {
                    FileUtils.deleteFile(file);
                }
            }
        } catch (Exception exception) {
            LogManagementUtils.Companion.e("存在异常的apk文件");
            if (errorFile != null) {
                FileUtils.deleteFile(errorFile);
            }
        }
    }

    private static long getApkVersion(String content) {
        String[] tempStrings = content.split("\\.");
        StringBuffer fileVersion = new StringBuffer();
        for (String tempString : tempStrings) {
            fileVersion.append(tempString);
        }
        return Long.parseLong(fileVersion.toString().trim());
    }

    /**
     * 获取储存root的目录
     *
     * @return
     */
    public static String getStorageDirectory(Context context) {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ?
                mSdRootPath + FOLDER_ROOT : getDataRootPath(context) + FOLDER_ROOT;
    }

    /**
     * 获取储存image的目录
     *
     * @param context
     */
    public static String getStorageImageDirectory(Context context) {
        return getStorageDirectory(context) + FOLDER_IMAGE;
    }

    /**
     * 获取储存apk 存储的目录
     *
     * @param context
     */
    public static String getStorageUpdateDirectory(Context context) {
        return getStorageDirectory(context) + FOLDER_APK;
    }

    /**
     * 获取储存db 存储的目录
     */
    public static String getStorageDBDirectory(Context context) {
        return getStorageDirectory(context) + FOLDER_DB;
    }

    /**
     * 获取储存logger的目录
     *
     * @param context
     * @return
     */
    public static String getStorageLoggerDirectory(Context context) {
        return getStorageDirectory(context) + FOLDER_LOGGER;
    }

    /**
     * 从手机或者sd卡获取Bitmap
     *
     * @param fileName
     * @return
     */
    public static Bitmap getBitmap(String fileName, Context context) {
        return BitmapFactory.decodeFile(getStorageImageDirectory(context) + File.separator + fileName);
    }

    /**
     * 获取 文件 路径
     *
     * @param fileId
     * @return
     */
    public static String getFilePath(String fileId, Context context) {
        return getStorageDirectory(context) + File.separator + fileId;
    }

    /**
     * 获取文件名称
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        String[] fileSplit = filePath.split("/");
        String fileName = fileSplit[fileSplit.length - 1];
        return fileName;
    }

    /**
     * 获取 文件 url 路径
     *
     * @param fileId
     * @return
     */
    public static String getImageUrl(String fileId, Context context) {
        return "file://" + getStorageImageDirectory(context) + File.separator + getFileName(fileId);
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExists(String fileName, Context context) {
        return new File(getStorageDirectory(context) + File.separator + fileName).exists();
    }

    /**
     * 获取文件的大小
     *
     * @param fileName
     * @return
     */
    public static long getFileSize(String fileName, Context context) {
        return new File(getStorageDirectory(context) + File.separator + fileName).length();
    }

    /**
     * 删除SD卡或者手机的缓存图片和目录
     */
    public static void deleteFile(Context context) {
        File dirFile = new File(getStorageDirectory(context));
        if (!dirFile.exists()) {
            return;
        }
        if (dirFile.isDirectory()) {
            String[] children = dirFile.list();
            for (int i = 0; i < children.length; i++) {
                new File(dirFile, children[i]).delete();
            }
        }

        dirFile.delete();
    }

    /**
     * 删除单个文件
     *
     * @param filePathName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static void deleteSingleFile(String filePathName) {
        try {
            File file = new File(filePathName);
            // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        } catch (Exception e) {
            LogManagementUtils.Companion.e(e.getMessage());
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param filePath 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static void deleteDirectory(String filePath) {
        try {
            // 如果dir不以文件分隔符结尾，自动添加文件分隔符
            if (!filePath.endsWith(File.separator)) {
                filePath = filePath + File.separator;
            }
            File dirFile = new File(filePath);
            // 如果dir对应的文件不存在，或者不是一个目录，则退出
            if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
                return;
            }
            // 删除文件夹中的所有文件包括子目录
            File[] files = dirFile.listFiles();
            for (File file : files) {
                // 删除子文件
                if (file.isFile()) {
                    deleteSingleFile(file.getAbsolutePath());
                } else if (file.isDirectory()) {
                    // 删除子目录
                    deleteDirectory(file.getAbsolutePath());
                }
            }
            // 删除当前目录
            dirFile.delete();
        } catch (Exception e) {
            LogManagementUtils.Companion.e(e.getMessage());
        }
    }

    /**
     * 删除本地文件
     *
     * @param type
     */
    public static void deleteLocalFile(String type, Context context) {
        if (TextUtils.isEmpty(type)) {
            //如果没有传类型，删除全部文件
            deleteFile(context);
            return;
        }
        // 图片
        if (type.equals(FOLDER_IMAGE)) {
            deleteDirectory(getStorageImageDirectory(context));
            return;
        }
        //日志
        if (type.equals(FOLDER_LOGGER)) {
            deleteDirectory(getStorageLoggerDirectory(context));
            return;
        }
        //apk
        if (type.equals(FOLDER_APK)) {
            deleteDirectory(getStorageUpdateDirectory(context));
            return;
        }
        //数据库
        if (type.equals(FOLDER_DB)) {
            deleteDirectory(getStorageDBDirectory(context));
        }
    }

    /**
     * 图片转化成base64字符串
     *
     * @param path
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            File file = new File(path);
            is = new FileInputStream(file);
            // 创建一个字符流大小的数组。
            data = new byte[(int) file.length()];
            // 写入数组
            is.read(data);
            // 用默认的编码格式进行编码
            result = Base64.encodeToString(
                    data,
                    Base64.DEFAULT
            );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * @param path 文件夹路径
     */
    public static void isExist(String path) {
        File file = new File(path);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
    }

    //保存为sd卡图片
    public static boolean saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        boolean compress = newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
        return compress;
    }


    public static Bitmap base64ToImage(String imgStr) {

        if (TextUtils.isEmpty(imgStr)) // 图像数据为空
            return null;
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(imgStr, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
