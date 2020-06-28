

package com.kuke.common.base.baseactivity.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kuke.common.base.baseactivity.presenter.BasePresenter;
import com.kuke.common.utils.ActivityManger;
import com.kuke.common.utils.FileUtils;
import com.kuke.common.utils.LogManagementUtils;
import com.kuke.common.utils.MvpInstantiationUtils;
import com.kuke.common.utils.ScreenAdapterUtils;
import com.kuke.common.utils.WindowStatusBarUtils;
import com.kuke.common.utils.language.LanContextWrapper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.viewbinding.ViewBinding;


/**
 * Activity基类
 *
 * @date: 2020-04-09
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class AbstractBaseActivity<V extends ViewBinding> extends AppCompatActivity {
    protected Activity context;
    protected LayoutInflater layoutInflater;
    protected V viewBinding;

    /**
     * 设置修改语言
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = LanContextWrapper.wrap(newBase);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        WindowStatusBarUtils.setStatusBarFullTransparent(this);
        WindowStatusBarUtils.setStatusBarTextColor(
                this,
                true
        );
        ActivityManger.Companion.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        context = this;
        layoutInflater = LayoutInflater.from(this);

        ARouter.getInstance().inject(this);
        viewBinding = getViewBinding();
        if (savedInstanceState == null) {
            // 初始化适配
            ScreenAdapterUtils.setCustomDensity(
                    this
            );

            setContentView(viewBinding.getRoot());
            initView(savedInstanceState);
            initializeData();
            requestPermissions();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.Companion.getInstance().removeActivity(this);
    }

    public void startActivityAndFinishThis(
            Activity activity,
            String path
    ) {
        ARouter.getInstance().build(path).navigation();
        activity.finish();
    }

    public void startActivityAndFinishThis(
            Activity activity,
            String path,
            Bundle bundle
    ) {
        ARouter.getInstance().build(path).with(bundle).navigation();
        activity.finish();
    }

    public void startActivity(
            Activity activity,
            String path
    ) {
        ARouter.getInstance().build(path).navigation();
    }

    public void startActivity(
            Activity activity,
            String path,
            Bundle bundle
    ) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    public abstract V getViewBinding();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initializeData();

    private void requestPermissions() {
        new RxPermissions(this).request(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).subscribe((isSuccess) -> {
            LogManagementUtils.Companion.showLog("isSuccess:" + isSuccess);
            if (isSuccess) {
                FileUtils.initialFileDirectory(this);
                LogManagementUtils.Companion.initLogger(this);
            }
        });
    }

    @Override
    public boolean onKeyDown(
            int keyCode,
            KeyEvent event
    ) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            this.finish();
            return true;
        }
        return super.onKeyDown(
                keyCode,
                event
        );
    }

}
