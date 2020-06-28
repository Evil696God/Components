package com.kuke.kkpianoroom.module.main.view;

import android.Manifest;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kuke.common.base.baseactivity.view.BaseActivity;
import com.kuke.common.utils.FileUtils;
import com.kuke.common.utils.LogManagementUtils;
import com.kuke.common.value.RoutePath;
import com.kuke.kkpianoroom.databinding.ActivityMainBinding;
import com.kuke.kkpianoroom.module.main.presenter.MainPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * 主界面
 *
 * @date: 2020-03-30
 * @version: 1.0
 * @author: wangchenxing
 */
@Route(path = RoutePath.MAIN)
public class MainActivity extends BaseActivity<MainPresenter, ActivityMainBinding> {

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        viewBinding.testButton.setOnClickListener(view -> {
            LogManagementUtils.Companion.e("kuke-test");
            Bundle bundle = new Bundle();
            bundle.putString("test", "test");
            ARouter.getInstance().build(RoutePath.MY_LIBRARY).withBundle("bundle", bundle).navigation();
        });

    }

    @Override
    public void initializeData() {
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

}
