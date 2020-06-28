

package com.kuke.common.base.baserecyclerfragment.presenter;

import android.app.Activity;


import com.kuke.common.base.baserecyclerfragment.model.BaseRecyclerModel;
import com.kuke.common.base.baserecyclerfragment.recyclerinterface.RecyclerViewNotifyListener;
import com.kuke.common.base.baserecyclerfragment.view.BaseRecyclerFragment;
import com.kuke.common.utils.MvpInstantiationUtils;
import com.kuke.common.base.baserecyclerfragment.recyclerinterface.ObserverableRecyclerData;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.Disposable;

/**
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public class BaseRecyclerPresenter<M extends BaseRecyclerModel, F extends BaseRecyclerFragment> implements LifecycleObserver {
    protected M model;
    protected F fragment;

    /**
     * 用来跳转界面
     */
    protected Activity activity;
    protected ObserverableRecyclerData observerableRecyclerData = new ObserverableRecyclerData(new RecyclerViewNotifyListener() {
        @Override
        public void notifyUI() {
            fragment.notifyRecyclerView(observerableRecyclerData.getAsyncData());
        }
    });

    public void bindViewAndModel(F fragment) {
        this.fragment = fragment;
        activity = (Activity) (fragment.getSupperContext());
        model = MvpInstantiationUtils.getInstantiation(this, MvpInstantiationUtils.FIRSTGENERICITY);
        model.setPresenter(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate(@NotNull LifecycleOwner owner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(@NotNull LifecycleOwner owner) {

        if(model != null) {
            model.onDestroy();
        }
    }
}
