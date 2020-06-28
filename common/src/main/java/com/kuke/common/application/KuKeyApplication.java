package com.kuke.common.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kuke.common.BuildConfig;
import com.kuke.common.R;
import com.kuke.common.utils.ActivityManger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

import static com.kuke.common.BuildConfig.DEBUG;

/**
 * @date: 2020-3-26
 * @version: 1.0
 * @author: wangchenxing
 */
public class KuKeyApplication extends Application {

    private static KuKeyApplication instance;

    /**
     * 获取Application 实例
     *
     * @return Application 实例
     */
    public static KuKeyApplication getInstance() {
        return instance;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
        }
        ARouter.init(this);
        initAutoSize();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    private void initAutoSize() {
        // 适配初始化
        AutoSizeConfig.getInstance().setBaseOnWidth(true).setCustomFragment(true);
        AutoSize.initCompatMultiProcess(this);
    }

    public void exitApp() {
        removeAllActivity();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void removeAllActivity() {
        ArrayList<WeakReference<Activity>> arrayList = ActivityManger.Companion.getInstance().getTaskActivitys();
        if (arrayList != null && arrayList.size() > 0) {
            for (WeakReference<Activity> weak : arrayList) {
                if (weak != null && weak.get() != null) {
                    Activity activity = weak.get();
                    if (activity != null && !activity.isFinishing()) {
                        activity.finish();
                    }
                }
            }
        }
    }

    /**
     * 获取 app name 的本地存储目录
     *
     * @return app name
     */
    public String getAppNamePath() {
        return getResources().getString(R.string.app_name);
    }


}
