

package com.kuke.common.base.baserecyclerfragment.view;

import android.app.Activity;
import android.app.ActivityOptions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.alibaba.android.arouter.launcher.ARouter;
import com.kuke.common.base.baserecyclerfragment.presenter.BaseRecyclerPresenter;
import com.kuke.common.utils.LayoutSettingUtils;
import com.kuke.common.utils.MvpInstantiationUtils;
import com.kuke.common.value.UiKit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @date: 2020-3-25
 * @version: 1.0
 * @author: wangchenxing
 */
public abstract class BaseRecyclerFragment<P extends BaseRecyclerPresenter> extends Fragment {
    public boolean isInitialized = false;
    protected P presenter;
    protected BaseRecyclerView recyclerView;
    protected Context context;
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private String title = "";
    private String fragmentTag = "";
    private BaseRecyclerAdapter adapter;
    private RelativeLayout total;

    public BaseRecyclerFragment() {
        initializePresenter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializePresenter();
        initView();
        isInitialized = true;
        getLifecycle().addObserver(presenter);
        ARouter.getInstance().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState
    ) {
        if (total == null) {
            total = new RelativeLayout(context);
            recyclerView.setBackgroundColor(UiKit.WHITE);
            total.addView(recyclerView);
            LayoutSettingUtils.setViewMatch(total);
            initializeData();
        }
        return total;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    private void initView() {
        if (recyclerView == null) {
            recyclerView = new BaseRecyclerView(context) {
                @Override
                public void loadData() {
                    super.loadData();
                    loadNewData();
                }

                @Override
                public void refresh() {
                    super.refresh();
                    refreshData();
                }
            };
        }
    }

    public abstract void initializeData();

    protected void initializePresenter() {
        if (presenter == null) {
            presenter = MvpInstantiationUtils.getInstantiation(
                    this,
                    MvpInstantiationUtils.FIRSTGENERICITY
            );
            presenter.bindViewAndModel(this);
        }
    }

    public void startActivityAndFinishThis(Activity activity, String path) {
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

    public void startActivity(Activity activity, String path) {
        ARouter.getInstance().build(path).navigation();
    }

    public void startActivity(Activity activity, String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation();
    }

    public String getFragmentTag() {
        return fragmentTag;
    }

    public void setFragmentTag(String fragmentTag) {
        this.fragmentTag = fragmentTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Context getSupperContext() {
        return context;
    }

    public abstract BaseRecyclerAdapter setRecyclerViewAdapter(List asyncData);

    public void notifyRecyclerView(List asyncData) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            this.adapter = setRecyclerViewAdapter(asyncData);
        } else {
            this.adapter.setDataSet(asyncData);
            adapter.notifyDataSetChanged();
        }
    }


    public void loadNewData() {
        if (presenter == null) {
            initializePresenter();
        }
    }

    public void refreshData() {
        if (presenter == null) {
            initializePresenter();
        }
    }

}
