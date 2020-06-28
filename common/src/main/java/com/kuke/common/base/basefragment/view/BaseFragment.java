

package com.kuke.common.base.basefragment.view;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kuke.common.base.basefragment.presenter.BaseFragmentPresenter;
import com.kuke.common.utils.LayoutSettingUtils;
import com.kuke.common.utils.MvpInstantiationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.viewbinding.ViewBinding;


/**
 * MVP框架 Fragment基类
 *
 * @date: 2020-03-25
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class BaseFragment<P extends BaseFragmentPresenter, V extends ViewBinding> extends AbstractBaseFragment<V> {
    protected P presenter;
    private LifecycleRegistry lifecycleRegistry;

    public BaseFragment() {
        initializePresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(presenter);
    }

    private void initializePresenter() {
        if (presenter == null) {
            presenter = MvpInstantiationUtils.getInstantiation(
                    this,
                    MvpInstantiationUtils.FIRSTGENERICITY
            );
            presenter.bindViewAndModel(this);
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        if (lifecycleRegistry == null) {
            lifecycleRegistry = new LifecycleRegistry(this);
        }
        return lifecycleRegistry;
    }
}
