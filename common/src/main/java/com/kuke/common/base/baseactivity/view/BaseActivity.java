

package com.kuke.common.base.baseactivity.view;

import android.os.Bundle;

import com.kuke.common.base.baseactivity.presenter.BasePresenter;
import com.kuke.common.utils.ActivityManger;
import com.kuke.common.utils.MvpInstantiationUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.viewbinding.ViewBinding;


/**
 * MVP框架 Activity基类
 *
 * @date: 2020-03-25
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class BaseActivity<P extends BasePresenter, V extends ViewBinding> extends AbstractBaseActivity<V> implements LifecycleOwner {
    protected P presenter;
    private LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = MvpInstantiationUtils.getInstantiation(
                this,
                MvpInstantiationUtils.FIRSTGENERICITY
        );
        if (presenter != null) {
            presenter.bindViewAndModel(this);
            getLifecycle().addObserver(presenter);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.Companion.getInstance().removeActivity(this);
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
