

package com.kuke.common.base.baseactivity.presenter;


import com.kuke.common.base.baseactivity.model.BaseModel;
import com.kuke.common.base.baseactivity.view.BaseActivity;
import com.kuke.common.utils.MvpInstantiationUtils;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.Disposable;

/**
 * MVP框架 Presenter基类
 *
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BasePresenter<M extends BaseModel, V extends BaseActivity> implements LifecycleObserver {
    protected M model;
    protected V context;


    public void bindViewAndModel(V context) {
        this.context = context;
        model = MvpInstantiationUtils.getInstantiation(this, MvpInstantiationUtils.FIRSTGENERICITY);
        model.setPresenter(this);
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

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                      @NotNull Lifecycle.Event event) {

    }

}
