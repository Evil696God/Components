

package com.kuke.common.base.basefragment.presenter;

import android.app.Activity;


import com.kuke.common.base.basefragment.model.BaseFragmentModel;
import com.kuke.common.base.basefragment.view.BaseFragment;
import com.kuke.common.utils.MvpInstantiationUtils;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.Disposable;

/**
 * MVP框架 Fragment Prensenter基类
 *
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseFragmentPresenter<M extends BaseFragmentModel, F extends BaseFragment> implements LifecycleObserver {
    protected M model;
    protected F fragment;
    /**
     * 用来跳转界面
     */
    protected Activity activity;

    public void bindViewAndModel(F fragment) {
        this.fragment = fragment;
        activity = (Activity) (fragment.getSupperContext());
        model = MvpInstantiationUtils.getInstantiation(this, MvpInstantiationUtils.FIRSTGENERICITY);
        if (model != null) {
            model.setPresenter(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate(@NotNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(@NotNull LifecycleOwner owner) {
        if (model != null) {
            model.onDestroy();
        }
    }
}
